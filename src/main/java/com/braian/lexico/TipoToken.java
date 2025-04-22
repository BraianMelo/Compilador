package com.braian.lexico;

public enum TipoToken {

    // Palavras-chave
    DECLARACAO_VARIAVEL,      // ☕ let
    IMPRIMIR,                 // 🖨️ print
    CONDICIONAL_IF,           // ❓ if
    BLOCO_VERDADEIRO,         // ✅ then
    BLOCO_FALSO,              // ❌ else
    ESTRUTURA_FOR,            // 🔁 for
    INSTRUCAO_BREAK,          // 🛑 break
    DECLARACAO_FUNCAO,        // 🆕 def
    RETORNO_FUNCAO,           // 🔚 return
    VERDADEIRO,               // 👍 true
    FALSO,                    // 👎 false
    NULO,                     // 🕳️ null
    ENTRADA,                  // ⌨️ input
    CONDICIONAL_SWITCH,       // 📑 switch
    CASE,                     // ✏️ case
    DEFAULT,                  // 📝 default
    ESTRUTURA_WHILE,          // ⏳ while
    INSTRUCAO_CONTINUE,       // ⏩ continue
    
    // Tipos de dados
    TIPO_INTEIRO,   // int
    TIPO_SHORT,   // short
    TIPO_LONG,   // long
    TIPO_FLOAT,   // float
    TIPO_DOUBLE, // double
    TIPO_STRING,     // string
    TIPO_BOOLEANO,  // bool

    // Operadores de comparação
    OP_IGUAL_IGUAL,           // ==
    OP_DIFERENTE,             // !=
    OP_MAIOR_IGUAL,           // >=
    OP_MENOR_IGUAL,           // <=
    OP_MAIOR,                 // >
    OP_MENOR,                 // <

    // Operadores aritméticos e incremento/decremento
    OP_MAIS_MAIS,             // ++
    OP_MENOS_MENOS,           // --
    OP_MAIS,                  // +
    OP_MENOS,                 // -
    OP_MULTIPLICACAO,         // *
    OP_DIVISAO,               // /

    // Operadores de atribuição
    OP_MAIS_IGUAL,            // +=
    OP_MENOS_IGUAL,           // -=
    OP_MULTIPLICACAO_IGUAL,   // *=
    OP_DIVISAO_IGUAL,         // /=
    OP_IGUAL,                 // =

    // Operadores lógicos
    AND,                      // &&
    OR,                       // ||
    NOT,                      // !

    // Separadores/delimitadores
    ABRE_PARENTESE,           // (
    FECHA_PARENTESE,          // )
    ABRE_CHAVE,               // {
    FECHA_CHAVE,              // }
    VIRGULA,                  // ,
    PONTO_VIRGULA,            // ;
    PONTO,                    // .

    // Identificadores e literais
    IDENTIFICADOR,
    NUMERO,
    DECIMAl,
    TEXTO,

    // Outros
    EOF,                      // Fim do arquivo
    ERRO                      // Token inválido
}
