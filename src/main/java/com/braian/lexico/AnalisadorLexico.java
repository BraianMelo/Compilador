package com.braian.lexico;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Matcher;

import com.braian.io.AnalisadorLexicoIO;
import com.braian.io.ArquivoIO;

public class AnalisadorLexico {
	
    private final String codigo;

    private int linha = 1;
    private int coluna = 1;
    private int posicao = 0;
    private boolean erroEncontrado = false;
    
    private final List<Token> tokens = new ArrayList<>();
    private final AnalisadorLexicoIO analisadorLexicoIO = new AnalisadorLexicoIO();
    private final ArquivoIO arquivoIO = new ArquivoIO();

    public AnalisadorLexico(String codigo) {
        this.codigo = codigo;
    }

    public List<Token> analisar() throws IOException {
        analisadorLexicoIO.imprimirInicio(); 

        while (!EOF()) {
            escanearTokens();
        }

        tokens.add(new Token(TipoToken.EOF, "", null, linha, coluna));

        StringBuilder sb = new StringBuilder();
        for (Token token : tokens) {
            sb.append(token + "\n");
        }
        
        if(erroEncontrado) {
        	throw new InputMismatchException("o código possui alguns erros léxicos!");
        }

        analisadorLexicoIO.imprimirFim();
        salvarTokensEmArquivo();

        return tokens;
    }

    /* Compara o restante do código não analisado para ver se bate com alguma regra. Caso não
     coincidir com uma regra, ele reporta o erro. */
    private void escanearTokens() {
        String restante = codigo.substring(posicao);

        for (RegraToken regra : ExpressoesRegulares.REGRAS) {
            Matcher matcher = regra.getPadrao().matcher(restante);

            if (matcher.find()) {
                String lexema = matcher.group();

                if (regra.getTipo() != null) {
                    Object literal = null;

                    if (regra.getTipo() == TipoToken.NUMERO) {
                        literal = Integer.parseInt(lexema);
                    }

                    Token token = new Token(regra.getTipo(), lexema, literal, linha, coluna);
                    
                    if(regra.getTipo() == TipoToken.IDENTIFICADOR && (tokens.getLast().getTipo() == TipoToken.NUMERO)) {
                    	imprimirErro(token);
                    	erroEncontrado = true;
                    	
                    }
                    
                    tokens.add(token);
                }

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
        
        Token erroToken = new Token(TipoToken.ERRO, caractereInvalido, null, linha, coluna);
        
        // Verifica se o caractere anterior estava errado. Ele ignora o erro até achar um
		// caracter válido
        if(tokens.isEmpty() || tokens.getLast().getTipo() != TipoToken.ERRO ) { 
        	imprimirErro(erroToken);

        }
        
        tokens.add(erroToken);
        
        posicao += Character.charCount(codePoint);
        coluna++;
        erroEncontrado = true;
    }

    // Final do arquivo
    private boolean EOF() {
        return posicao >= codigo.length();
    }

    // Conta quantos '\n' no texto analisado
    private int contarQuebrasDeLinha(String texto) {
        int contador = 0;

        for (char letra : texto.toCharArray()) {
            if (letra == '\n')
                ++contador;
        }

        return contador;
    }
    
    // Imprime o erro no terminal com o AnalisadorLexicoIO
    private void imprimirErro(Token erro) {
    	
    	switch(erro.getTipo()) {
    	case IDENTIFICADOR:
    		analisadorLexicoIO.imprimirErro(
            		"Identificador inválido: '" + tokens.getLast().getLexema() + erro.getLexema() + 
            		"' na linha " + erro.getLinha() + 
            		", coluna " + erro.getColuna()); 
    		break;
    		
    	default:
    		analisadorLexicoIO.imprimirErro(
            		"Caracter inválido: '" + erro.getLexema() + 
            		"' na linha " + erro.getLinha() + 
            		", coluna " + erro.getColuna()); 
    		break;
    	}

    
    }
    
    // Salva a lista de tokens com o ArquivoIO
    private void salvarTokensEmArquivo() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (Token token : tokens) {
            sb.append(token.toString() + "\n");
        }
        arquivoIO.escreverArquivo("output/AnaliseLexica.txt" ,sb.toString());
    }
}
