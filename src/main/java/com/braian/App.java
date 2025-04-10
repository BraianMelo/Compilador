package com.braian;

import java.io.File;

/*
 * Copyright (c) 2025 Braian
 *
 * Licensed under a "Licença de Uso Restrito Pessoal".
 * Leonardo Ribeiro, atualmente matriculado no curso de Ciência da Computação da UFSJ,
 * está explicitamente proibido de usar, modificar ou redistribuir este código.
 *
 * Veja o arquivo LICENSE.txt para mais detalhes.
 */

import java.util.Scanner;

import com.braian.lexico.AnalisadorLexico;

public class App {
	
    public static void main(String[] args) {
        Scanner sc = null;

        try {
        	
            if (args.length != 1) {
                throw new IllegalArgumentException("Precisa informar o caminho do arquivo");
            }

            File arquivo = new File(args[0]);
            sc = new Scanner(arquivo);
            StringBuilder codigo = new StringBuilder();

            while (sc.hasNextLine()) {
                codigo.append(sc.nextLine());
            }

            AnalisadorLexico analisador = new AnalisadorLexico(codigo.toString());
            analisador.imprimirTokens();

        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            
        } finally {
            if (sc != null)
                sc.close();
            
        }
    }
}
