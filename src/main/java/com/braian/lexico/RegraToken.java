package com.braian.lexico;

import java.util.regex.Pattern;

public class RegraToken {
	
	private Pattern padrao;
	private TipoToken tipo;
	
	public RegraToken() {}

	public RegraToken(Pattern padrao, TipoToken tipo) {
		this.padrao = padrao;
		this.tipo = tipo;
	}

	public Pattern getPadrao() {
		return padrao;
	}

	public void setPadrao(Pattern padrao) {
		this.padrao = padrao;
	}

	public TipoToken getTipo() {
		return tipo;
	}

	public void setTipo(TipoToken tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "RegraToken [padrao=" + padrao + ", tipo=" + tipo + "]";
	}

}
