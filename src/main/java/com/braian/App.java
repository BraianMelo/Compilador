package com.braian;

import java.util.List;

import com.braian.io.AnalisadoresIO;
import com.braian.io.ArquivoIO;
import com.braian.lexico.AnalisadorLexico;
import com.braian.lexico.Token;
import com.braian.sintatico.AnalisadorSintatico;

public class App {
	
    public static void main(String[] args) {
    	
    	List<Token> listaDeTokens;
  
    	AnalisadoresIO io = new AnalisadoresIO();
        ArquivoIO arquivoIO = new ArquivoIO();
        AnalisadorLexico lexico;
        AnalisadorSintatico sintatico;
      
        try {
        	
            if (args.length != 1) {
                throw new IllegalArgumentException("Precisa informar o caminho do arquivo");
            }

            String codigo = arquivoIO.lerArquivo(args[0]);
            
            lexico = new AnalisadorLexico(codigo.toString());
            listaDeTokens = lexico.analisar();
            
            sintatico = new AnalisadorSintatico();
            sintatico.analisar(listaDeTokens);
            

        } catch (Exception e) {
        	io.imprimirErro("ERRO: " + e.getMessage());
        	io.imprimirFim();
        } finally {
            
        }
    }
}
