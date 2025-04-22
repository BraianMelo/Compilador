package com.braian.io;

public interface AnalisadoresIO {
    void imprimirInicio(); // Imprime cabeçalho
    void imprimirErro(String mensagemErro); // Método para imprimir erro
    void imprimirFim(); // Método para imprimir o final da análise
}
