package com.braian.io;

public class AnalisadoresIO {
	
	protected final int TAMANHO_DA_LINHA = 70; 
	
    protected final String ANSI_VERMELHO = "\u001B[31m";
    protected final String ANSI_RESET = "\u001B[0m";
    protected final String ANSI_VERDE = "\u001B[32m";
    protected final String ANSI_AMARELO = "\u001B[33m";
    protected final String ANSI_AZUL = "\u001B[34m";
    
    private String repete(String mensagem, int qtdVezes) {
    	return mensagem.repeat(qtdVezes);
    }

    private String escreverCentralizado(String mensagem) {
    	int qtdEspacos = TAMANHO_DA_LINHA - mensagem.length() - 2;
    	
    	int parteInicial =  (int) Math.ceil(qtdEspacos / 2.0);
    	int parteFinal =  (int) Math.floor(qtdEspacos / 2.0);
    	
    	String centralzado = " ".repeat(parteInicial);
    	centralzado += mensagem;
    	centralzado += " ".repeat(parteFinal);
    	
    	return centralzado;
    }
    
    public void imprimirCabecalho(String titulo) {
    	 StringBuilder cabecalho = new StringBuilder();
         
    	 cabecalho.append("+");
    	 cabecalho.append(repete("-", TAMANHO_DA_LINHA - 2));
    	 cabecalho.append("+\n");
         cabecalho.append("|");
         cabecalho.append(escreverCentralizado(titulo));
         cabecalho.append("|\n");
         
         System.out.print(cabecalho.toString());
    	
    }
    
    public void imprimirRodape(Boolean erro) {
        StringBuilder rodape = new StringBuilder();
        
        rodape.append("|");
        
        if(!erro) {
        	rodape.append(ANSI_VERDE);
        } else {
        	rodape.append(ANSI_VERMELHO);
        }
        
    	rodape.append(escreverCentralizado("Análise Finalizada!"));
    	rodape.append(ANSI_RESET + "|");
    	rodape.append("\n");
        
        
        rodape.append("+");
   	    rodape.append(repete("-", TAMANHO_DA_LINHA - 2));
   	    rodape.append("+\n");
        
        System.out.println(rodape.toString());
    	
    }   

    protected String formatarString(String mensagem) {
        StringBuilder sb = new StringBuilder();
        
        int inicio = 0;
        int fim;
        boolean primeiraLinha = true;

        while (inicio < mensagem.length()) {
            fim = Math.min(inicio + TAMANHO_DA_LINHA - 4, mensagem.length());
            String linha = mensagem.substring(inicio, fim);

            if (linha.length() < TAMANHO_DA_LINHA) {
                int qtdEspacos = TAMANHO_DA_LINHA - linha.length() - 3;
                linha += repete(" ", qtdEspacos);
                linha += "|";
            }

            if (primeiraLinha) {
                sb.append("+ ").append(linha).append("\n");
                primeiraLinha = false;
            } else {
                sb.append("| ").append(linha).append("\n");
            }

            inicio = fim;
        }

        return sb.toString();
    }

    
    public void imprimirErro(String mensagemErro) {
        System.out.print(ANSI_VERMELHO + formatarString(mensagemErro) + ANSI_RESET);
    }
    
    public void imprimirFim() {
    	System.out.println("+------------------------------------------------+");
    }
}
