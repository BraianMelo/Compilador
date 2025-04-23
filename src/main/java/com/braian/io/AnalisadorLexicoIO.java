package com.braian.io;

public class AnalisadorLexicoIO implements AnalisadoresIO {

    @Override
    public void imprimirInicio() {
        StringBuilder cabecalho = new StringBuilder();
        
        cabecalho.append("+----------------------------------------------+\n");
        cabecalho.append("|               Analisador Léxico              |\n");
        cabecalho.append("+----------------------------------------------+");
        
        System.out.println(cabecalho.toString());
    }

    @Override
    public void imprimirErro(String mensagemErro) {
        System.out.println("+ "+ mensagemErro);
    }

    @Override
    public void imprimirFim() {
        StringBuilder rodape = new StringBuilder();
        
        rodape.append("+----------------------------------------------+\n");
        rodape.append("|               Análise Finalizada!            |\n");
        rodape.append("+----------------------------------------------+");
        
        System.out.println(rodape.toString());
    }
}
