package com.braian;

import java.util.Scanner;

import com.braian.lexico.AnalisadorLexico;

public class App {
	
    public static void main( String[] args ) {
    	
    	Scanner sc = new Scanner(System.in, "UTF-8");
    
    	
    	try {
    		
    		if(args.length != 1) {
    			throw new IllegalArgumentException("Precisa informar o caminho do arquivo");
    		}
    		
    		AnalisadorLexico analisador = new AnalisadorLexico("☕ x = 10;\n"
    				+ "☕y = 5;\n"
    				+ "\n"
    				+ "🖨️ x;\n"
    				+ "🖨️ y;\n"
    				+ "\n"
    				+ "❓(x == y)\n"
    				+ "✅ y = y + 1;\n"
    				+ "❌ y = x;\n"
    				+ "\n"
    				+ "🆕 soma(a, b) {\n"
    				+ "  🚚 a + b;\n"
    				+ "}\n"
    				+ "\n"
    				+ "🔁 (i = 0; i < 10; i = i + 1) {\n"
    				+ "  🖨️ i;\n"
    				+ "  🛑;\n"
    				+ "}\n"
    				+ "");
    		
    		analisador.imprimirTokens();
    		
    	} catch (Exception e ) {
    		System.out.println("ERRO: "+ e.getMessage());
    		
    	} finally {
			sc.close();
			
		}
    }
}
