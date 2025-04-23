package com.braian;

/*
 * Copyright (c) 2025 Braian Melo
 * Veja o arquivo LICENSE.txt para mais detalhes.
 */

import java.util.Scanner;

import com.braian.io.ArquivoIO;
import com.braian.lexico.AnalisadorLexico;

public class App {
	
    public static void main(String[] args) {
    	
        Scanner sc = null;
        ArquivoIO arquivoIO = new ArquivoIO();

        try {
        	
            if (args.length != 1) {
                throw new IllegalArgumentException("Precisa informar o caminho do arquivo");
            }

            String codigo = arquivoIO.lerArquivo(args[0]);
            
            AnalisadorLexico analisador = new AnalisadorLexico(codigo.toString());
            analisador.analiseLexica();

        } catch (Exception e) {
            System.out.println("+ ERRO: " + e.getMessage());

        } finally {
            if (sc != null)
                sc.close();
            
        }
    }
}
