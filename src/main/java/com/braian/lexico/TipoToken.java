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
	RETORNO_FUNCAO, // 🔚​ return​

	// 📦 Identificadores e literais
	IDENTIFICADOR, NUMERO, DECIMAl,

	// ➕ Operadores
	OP_IGUAL, // =
	OP_IGUAL_IGUAL, // ==
	OP_DIFERENTE, // !=
	OP_MAIOR, // >
	OP_MENOR, // <
	OP_MAIOR_IGUAL, // >=
	OP_MENOR_IGUAL, // <=
	OP_MAIS, // +
	OP_MENOS, // -
	OP_MULTIPLICACAO, // *
	OP_DIVISAO, // /

	// 📐 Delimitadores e símbolos
	ABRE_PARENTESE, // (
	FECHA_PARENTESE, // )
	ABRE_CHAVE, // {
	FECHA_CHAVE, // }
	VIRGULA, // ,
	PONTO_VIRGULA, // ;
	PONTO,

	// 🧯 Outros
	EOF, // Fim do arquivo
	ERRO // Token inválido

}
