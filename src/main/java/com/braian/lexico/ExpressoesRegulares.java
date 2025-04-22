package com.braian.lexico;

import java.util.List;
import java.util.regex.Pattern;

public class ExpressoesRegulares {

    public static final List<RegraToken> REGRAS = List.of(

        // Palavras-chave
        new RegraToken(Pattern.compile("^☕"), TipoToken.DECLARACAO_VARIAVEL),
        new RegraToken(Pattern.compile("^🖨️"), TipoToken.IMPRIMIR),
        new RegraToken(Pattern.compile("^❓"), TipoToken.CONDICIONAL_IF),
        new RegraToken(Pattern.compile("^✅"), TipoToken.BLOCO_VERDADEIRO),
        new RegraToken(Pattern.compile("^❌"), TipoToken.BLOCO_FALSO),
        new RegraToken(Pattern.compile("^🔁"), TipoToken.ESTRUTURA_FOR),
        new RegraToken(Pattern.compile("^🛑"), TipoToken.INSTRUCAO_BREAK),
        new RegraToken(Pattern.compile("^🆕"), TipoToken.DECLARACAO_FUNCAO),
        new RegraToken(Pattern.compile("^🔚"), TipoToken.RETORNO_FUNCAO),
        new RegraToken(Pattern.compile("^👍"), TipoToken.VERDADEIRO),
        new RegraToken(Pattern.compile("^👎"), TipoToken.FALSO),
        new RegraToken(Pattern.compile("^🕳️"), TipoToken.NULO),
        new RegraToken(Pattern.compile("^⌨️"), TipoToken.ENTRADA),
        new RegraToken(Pattern.compile("^📑"), TipoToken.CONDICIONAL_SWITCH),
        new RegraToken(Pattern.compile("^✏️"), TipoToken.CASE),
        new RegraToken(Pattern.compile("^📝"), TipoToken.DEFAULT),
        new RegraToken(Pattern.compile("^⏳"), TipoToken.ESTRUTURA_WHILE),
        new RegraToken(Pattern.compile("^⏩"), TipoToken.INSTRUCAO_CONTINUE),

        // Tipos de dados
        new RegraToken(Pattern.compile("^int\\b"), TipoToken.TIPO_INTEIRO),
        new RegraToken(Pattern.compile("^short\\b"), TipoToken.TIPO_SHORT),
        new RegraToken(Pattern.compile("^long\\b"), TipoToken.TIPO_LONG),
        new RegraToken(Pattern.compile("^float\\b"), TipoToken.TIPO_FLOAT),
        new RegraToken(Pattern.compile("^double\\b"), TipoToken.TIPO_DOUBLE),
        new RegraToken(Pattern.compile("^string\\b"), TipoToken.TIPO_STRING),
        new RegraToken(Pattern.compile("^bool\\b"), TipoToken.TIPO_BOOLEANO),

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
        new RegraToken(Pattern.compile("^&&"), TipoToken.AND),
        new RegraToken(Pattern.compile("^\\|\\|"), TipoToken.OR),
        new RegraToken(Pattern.compile("^!"), TipoToken.NOT),

        // Separadores/delimitadores
        new RegraToken(Pattern.compile("^\\("), TipoToken.ABRE_PARENTESE),
        new RegraToken(Pattern.compile("^\\)"), TipoToken.FECHA_PARENTESE),
        new RegraToken(Pattern.compile("^\\{"), TipoToken.ABRE_CHAVE),
        new RegraToken(Pattern.compile("^\\}"), TipoToken.FECHA_CHAVE),
        new RegraToken(Pattern.compile("^,"), TipoToken.VIRGULA),
        new RegraToken(Pattern.compile("^;"), TipoToken.PONTO_VIRGULA),
        new RegraToken(Pattern.compile("^\\."), TipoToken.PONTO),

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
