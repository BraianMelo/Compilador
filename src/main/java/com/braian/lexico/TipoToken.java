package com.braian.lexico;

public enum TipoToken {

	// 🔑 Palavras-chave (com emojis)
	DECLARACAO_VARIAVEL, // ☕ let
	IMPRIMIR, // 🖨️​ print
	CONDICIONAL_IF, // ❓ if
	BLOCO_VERDADEIRO, // ✅ then
	BLOCO_FALSO, // ❌ else
	ESTRUTURA_FOR, // 🔁 for
	INSTRUCAO_BREAK, // 🛑 break
	DECLARACAO_FUNCAO, // 🆕 def
	RETORNO_FUNCAO, // 🚚​ return​

	// 📦 Identificadores e literais
	IDENTIFICADOR, NUMERO,

	// ➕ Operadores
	IGUAL, // =
	IGUAL_IGUAL, // ==
	DIFERENTE, // !=
	MAIOR, // >
	MENOR, // <
	MAIOR_IGUAL, // >=
	MENOR_IGUAL, // <=
	MAIS, // +
	MENOS, // -
	MULTIPLICACAO, // *
	DIVISAO, // /

	// 📐 Delimitadores e símbolos
	ABRE_PARENTESE, // (
	FECHA_PARENTESE, // )
	ABRE_CHAVE, // {
	FECHA_CHAVE, // }
	VIRGULA, // ,
	PONTO_VIRGULA, // ;

	// 🧯 Outros
	EOF, // Fim do arquivo
	ERRO // Token inválido

}
