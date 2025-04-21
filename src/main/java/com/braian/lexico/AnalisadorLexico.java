package com.braian.lexico;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import com.braian.io.ArquivoIO;

public class AnalisadorLexico {

	private final ArquivoIO arquivoIO;
	
    private final String codigo;
    
    private final List<Token> tokens = new ArrayList<>();
    
    private int linha = 1;
    private int coluna = 1;
    private int posicao = 0;

    public AnalisadorLexico(String codigo) {
        this.codigo = codigo;
        arquivoIO = new ArquivoIO();
    }

    public List<Token> analiseLexica() throws IOException {
        while (!EOF()) {
            escanearTokens();
        }

        tokens.add(new Token(TipoToken.EOF, "", null, linha, coluna));

        StringBuilder sb = new StringBuilder();
        
        for (Token token : tokens) {
            sb.append(token + "\n");
        }
        
        arquivoIO.escreverArquivo("output/AnaliseLexica.txt", sb.toString());
        
        return tokens;
    }

    private void escanearTokens() {
        String restante = codigo.substring(posicao);

        for (RegraToken regra : ExpressoesRegulares.REGRAS) { // Procura se o char bate com alguma regra
        	
            Matcher matcher = regra.getPadrao().matcher(restante);

            if (matcher.find()) {
                String lexema = matcher.group();

                // Se for nulo, ignorar (comentário, espaço, etc)
                if (regra.getTipo() != null) {
                    Object literal = null;

                    if (regra.getTipo() == TipoToken.NUMERO) {
                        literal = Integer.parseInt(lexema);
                    }

                    Token token = new Token(regra.getTipo(), lexema, literal, linha, coluna);
                    tokens.add(token);
                }

                // Atualiza posição, linha e coluna
                int linhasPuladas = contarQuebrasDeLinha(lexema);

                if (linhasPuladas > 0) {
                    linha += linhasPuladas;
                    coluna = lexema.length() - lexema.lastIndexOf("\n");
                    
                } else {
                    coluna += lexema.length();
                    
                }

                posicao += lexema.length();
                return;
            }
        }

        // Se nenhuma regra bateu:
        int codePoint = codigo.codePointAt(posicao);
        String caractereInvalido = new String(Character.toChars(codePoint));
        tokens.add(new Token(TipoToken.ERRO, caractereInvalido, null, linha, coluna));
        
        StringBuilder erro = new StringBuilder();
        erro.append("ERRO: '" + caractereInvalido +"' não foi reconhecido! \n");
        erro.append("Linha: " + linha + " | Coluna: " + coluna);
        
        System.out.println(erro.toString());
        
        posicao += Character.charCount(codePoint);
        coluna++;

        
    }

    private boolean EOF() {
        return posicao >= codigo.length();
    }

    private int contarQuebrasDeLinha(String texto) {
        int contador = 0;
        
        for (char letra : texto.toCharArray()) {
       
            if (letra == '\n')
            	++contador;   
        }
        
        return contador;
    }
}
