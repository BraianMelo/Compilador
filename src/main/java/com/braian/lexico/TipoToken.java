package com.braian.lexico;

public enum TipoToken {

    // Palavras-chave
    PC_DECLARACAO_VARIAVEL,      // ☕ let
    PC_IMPRIMIR,                 // 🖨️ print
    PC_CONDICIONAL_IF,           // ❓ if
    PC_BLOCO_VERDADEIRO,         // ✅ then
    PC_BLOCO_FALSO,              // ❌ else
    PC_ESTRUTURA_FOR,            // 🔁 for
    PC_INSTRUCAO_BREAK,          // 🛑 break
    PC_DECLARACAO_FUNCAO,        // 🆕 def
    PC_RETORNO_FUNCAO,           // 🔚 return
    PC_VERDADEIRO,               // 👍 true
    PC_FALSO,                    // 👎 false
    PC_NULO,                     // 🕳️ null
    PC_ENTRADA,                  // ⌨️ input
    PC_CONDICIONAL_SWITCH,       // 📑 switch
    PC_CASE,                     // ✏️ case
    PC_DEFAULT,                  // 📝 default
    PC_ESTRUTURA_WHILE,          // ⏳ while
    PC_INSTRUCAO_CONTINUE,       // ⏩ continue
    
    // Tipos de dados
    PC_TIPO_INTEIRO,   			 // int
    PC_TIPO_SHORT,               // short
    PC_TIPO_LONG,                // long
    PC_TIPO_FLOAT,               // float
    PC_TIPO_DOUBLE,              // double
    PC_TIPO_STRING,              // string
    PC_TIPO_BOOLEANO,            // bool

    // Operadores de comparação
    OP_IGUAL_IGUAL,              // ==
    OP_DIFERENTE,                // !=
    OP_MAIOR_IGUAL,              // >=
    OP_MENOR_IGUAL,              // <=
    OP_MAIOR,                    // >
    OP_MENOR,                    // <

    // Operadores aritméticos e incremento/decremento
    OP_MAIS_MAIS,                // ++
    OP_MENOS_MENOS,              // --
    OP_MAIS,                     // +
    OP_MENOS,                    // -
    OP_MULTIPLICACAO,            // *
    OP_DIVISAO,                  // /

    // Operadores de atribuição
    OP_MAIS_IGUAL,               // +=
    OP_MENOS_IGUAL,              // -=
    OP_MULTIPLICACAO_IGUAL   ,   // *=
    OP_DIVISAO_IGUAL,            // /=
    OP_IGUAL,                    // =

    // Operadores lógicos
    OP_AND,                      // &&
    OP_OR,                       // ||
    OP_NOT,                      // !

    // Separadores
    SE_ABRE_PARENTESE,           // (
    SE_FECHA_PARENTESE,          // )
    SE_ABRE_CHAVE,               // {
    SE_FECHA_CHAVE,              // }
    SE_VIRGULA,                  // ,
    SE_PONTO_VIRGULA,            // ;
    SE_PONTO,                    // .

    // Identificadores
    IDENTIFICADOR,
    NUMERO,
    DECIMAl,
    TEXTO,

    // Outros
    EOF,                         // Fim do arquivo
    ERRO                         // Token inválido
}
