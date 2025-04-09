package com.braian.lexico;

import java.util.List;
import java.util.regex.Pattern;

public class ExpressoesRegulares {

    public record RegraToken(Pattern padrao, TipoToken tipo) {}

    public static final List<RegraToken> REGRAS = List.of(
        // Emojis - palavras-chave
        new RegraToken(Pattern.compile("^☕"), TipoToken.DECLARACAO_VARIAVEL),
        new RegraToken(Pattern.compile("^🖨️"), TipoToken.IMPRIMIR),
        new RegraToken(Pattern.compile("^❓"), TipoToken.CONDICIONAL_IF),
        new RegraToken(Pattern.compile("^✅"), TipoToken.BLOCO_VERDADEIRO),
        new RegraToken(Pattern.compile("^❌"), TipoToken.BLOCO_FALSO),
        new RegraToken(Pattern.compile("^🔁"), TipoToken.ESTRUTURA_FOR),
        new RegraToken(Pattern.compile("^🛑"), TipoToken.INSTRUCAO_BREAK),
        new RegraToken(Pattern.compile("^🆕"), TipoToken.DECLARACAO_FUNCAO),
        new RegraToken(Pattern.compile("^🚚"), TipoToken.RETORNO_FUNCAO),

        // Operadores
        new RegraToken(Pattern.compile("^=="), TipoToken.IGUAL_IGUAL),
        new RegraToken(Pattern.compile("^="), TipoToken.IGUAL),
        new RegraToken(Pattern.compile("^!="), TipoToken.DIFERENTE),
        new RegraToken(Pattern.compile("^>="), TipoToken.MAIOR_IGUAL),
        new RegraToken(Pattern.compile("^<="), TipoToken.MENOR_IGUAL),
        new RegraToken(Pattern.compile("^>"), TipoToken.MAIOR),
        new RegraToken(Pattern.compile("^<"), TipoToken.MENOR),
        new RegraToken(Pattern.compile("^\\+"), TipoToken.MAIS),
        new RegraToken(Pattern.compile("^-"), TipoToken.MENOS),
        new RegraToken(Pattern.compile("^\\*"), TipoToken.MULTIPLICACAO),
        new RegraToken(Pattern.compile("^/"), TipoToken.DIVISAO),

        // Delimitadores
        new RegraToken(Pattern.compile("^\\("), TipoToken.ABRE_PARENTESE),
        new RegraToken(Pattern.compile("^\\)"), TipoToken.FECHA_PARENTESE),
        new RegraToken(Pattern.compile("^\\{"), TipoToken.ABRE_CHAVE),
        new RegraToken(Pattern.compile("^\\}"), TipoToken.FECHA_CHAVE),
        new RegraToken(Pattern.compile("^,"), TipoToken.VIRGULA),
        new RegraToken(Pattern.compile("^;"), TipoToken.PONTO_VIRGULA),

        // Literais
        new RegraToken(Pattern.compile("^\\d+"), TipoToken.NUMERO),
        new RegraToken(Pattern.compile("^[a-zA-Z_][a-zA-Z_0-9]*"), TipoToken.IDENTIFICADOR),

        // Comentário e espaços (ignorados)
        new RegraToken(Pattern.compile("^//.*"), null),
        new RegraToken(Pattern.compile("^\\s+"), null)
    );
}
