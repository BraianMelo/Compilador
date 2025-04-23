package com.braian.lexico;

import java.util.List;
import java.util.regex.Pattern;

public class ExpressoesRegulares {

    public static final List<RegraToken> REGRAS = List.of(

        // Palavras-chave
        new RegraToken(Pattern.compile("^☕"), TipoToken.PC_DECLARACAO_VARIAVEL),
        new RegraToken(Pattern.compile("^🖨️"), TipoToken.PC_IMPRIMIR),
        new RegraToken(Pattern.compile("^❓"), TipoToken.PC_CONDICIONAL_IF),
        new RegraToken(Pattern.compile("^✅"), TipoToken.PC_BLOCO_VERDADEIRO),
        new RegraToken(Pattern.compile("^❌"), TipoToken.PC_BLOCO_FALSO),
        new RegraToken(Pattern.compile("^🔁"), TipoToken.PC_ESTRUTURA_FOR),
        new RegraToken(Pattern.compile("^🛑"), TipoToken.PC_INSTRUCAO_BREAK),
        new RegraToken(Pattern.compile("^🆕"), TipoToken.PC_DECLARACAO_FUNCAO),
        new RegraToken(Pattern.compile("^🔚"), TipoToken.PC_RETORNO_FUNCAO),
        new RegraToken(Pattern.compile("^👍"), TipoToken.PC_VERDADEIRO),
        new RegraToken(Pattern.compile("^👎"), TipoToken.PC_FALSO),
        new RegraToken(Pattern.compile("^🕳️"), TipoToken.PC_NULO),
        new RegraToken(Pattern.compile("^⌨️"), TipoToken.PC_ENTRADA),
        new RegraToken(Pattern.compile("^📑"), TipoToken.PC_CONDICIONAL_SWITCH),
        new RegraToken(Pattern.compile("^✏️"), TipoToken.PC_CASE),
        new RegraToken(Pattern.compile("^📝"), TipoToken.PC_DEFAULT),
        new RegraToken(Pattern.compile("^⏳"), TipoToken.PC_ESTRUTURA_WHILE),
        new RegraToken(Pattern.compile("^⏩"), TipoToken.PC_INSTRUCAO_CONTINUE),

        // Tipos de dados
        new RegraToken(Pattern.compile("^int\\b"), TipoToken.PC_TIPO_INTEIRO),
        new RegraToken(Pattern.compile("^short\\b"), TipoToken.PC_TIPO_SHORT),
        new RegraToken(Pattern.compile("^long\\b"), TipoToken.PC_TIPO_LONG),
        new RegraToken(Pattern.compile("^float\\b"), TipoToken.PC_TIPO_FLOAT),
        new RegraToken(Pattern.compile("^double\\b"), TipoToken.PC_TIPO_DOUBLE),
        new RegraToken(Pattern.compile("^string\\b"), TipoToken.PC_TIPO_STRING),
        new RegraToken(Pattern.compile("^bool\\b"), TipoToken.PC_TIPO_BOOLEANO),

        // Operadores de comparação
        new RegraToken(Pattern.compile("^=="), TipoToken.OP_IGUAL_IGUAL),
        new RegraToken(Pattern.compile("^!="), TipoToken.OP_DIFERENTE),
        new RegraToken(Pattern.compile("^>="), TipoToken.OP_MAIOR_IGUAL),
        new RegraToken(Pattern.compile("^<="), TipoToken.OP_MENOR_IGUAL),
        new RegraToken(Pattern.compile("^>"), TipoToken.OP_MAIOR),
        new RegraToken(Pattern.compile("^<"), TipoToken.OP_MENOR),

        // Operadores de atribuição
        new RegraToken(Pattern.compile("^\\+="), TipoToken.OP_MAIS_IGUAL),
        new RegraToken(Pattern.compile("^-="), TipoToken.OP_MENOS_IGUAL),
        new RegraToken(Pattern.compile("^\\*="), TipoToken.OP_MULTIPLICACAO_IGUAL),
        new RegraToken(Pattern.compile("^/="), TipoToken.OP_DIVISAO_IGUAL),
        new RegraToken(Pattern.compile("^="), TipoToken.OP_IGUAL),
        
        // Operadores aritméticos e incremento/decremento
        new RegraToken(Pattern.compile("^\\+\\+"), TipoToken.OP_MAIS_MAIS),
        new RegraToken(Pattern.compile("^--"), TipoToken.OP_MENOS_MENOS),
        new RegraToken(Pattern.compile("^\\+"), TipoToken.OP_MAIS),
        new RegraToken(Pattern.compile("^-"), TipoToken.OP_MENOS),
        new RegraToken(Pattern.compile("^\\*"), TipoToken.OP_MULTIPLICACAO),
        new RegraToken(Pattern.compile("^/"), TipoToken.OP_DIVISAO),

        // Operadores lógicos
        new RegraToken(Pattern.compile("^&&"), TipoToken.OP_AND),
        new RegraToken(Pattern.compile("^\\|\\|"), TipoToken.OP_OR),
        new RegraToken(Pattern.compile("^!"), TipoToken.OP_NOT),

        // Separadores/delimitadores
        new RegraToken(Pattern.compile("^\\("), TipoToken.SE_ABRE_PARENTESE),
        new RegraToken(Pattern.compile("^\\)"), TipoToken.SE_FECHA_PARENTESE),
        new RegraToken(Pattern.compile("^\\{"), TipoToken.SE_ABRE_CHAVE),
        new RegraToken(Pattern.compile("^\\}"), TipoToken.SE_FECHA_CHAVE),
        new RegraToken(Pattern.compile("^,"), TipoToken.SE_VIRGULA),
        new RegraToken(Pattern.compile("^;"), TipoToken.SE_PONTO_VIRGULA),
        new RegraToken(Pattern.compile("^\\."), TipoToken.SE_PONTO),

        // Literais
        new RegraToken(Pattern.compile("^[0-9]+\\.[0-9]+"), TipoToken.DECIMAl),
        new RegraToken(Pattern.compile("^\\d+"), TipoToken.NUMERO),
        new RegraToken(Pattern.compile("^\".*?\""), TipoToken.TEXTO),
        new RegraToken(Pattern.compile("^[a-zA-Z_][a-zA-Z_0-9]*"), TipoToken.IDENTIFICADOR),

        // Comentário e espaços (ignorados)
        new RegraToken(Pattern.compile("^💭.*"), null),
        new RegraToken(Pattern.compile("^\\s+"), null)
    );
}
