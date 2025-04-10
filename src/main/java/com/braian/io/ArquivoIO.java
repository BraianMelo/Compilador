package com.braian.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ArquivoIO {
	
	public String lerArquivo(String caminho) throws IOException {
		Path path = Paths.get(caminho);
		return Files.readString(path);
	}
	
	public void escreverArquivo(String caminho, String conteudo) throws IOException {
		Path path = Paths.get(caminho);
		Files.writeString(path, conteudo);
	}

}
