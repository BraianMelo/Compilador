package com.braian.sintatico;

import java.util.List;
import java.util.Iterator;

import com.braian.lexico.Token;
import com.braian.lexico.TipoToken;

public class AnalisadorSintatico {

    private Iterator<Token> tokens;
    private Token atual;

    public void analisar(List<Token> listaDeTokens) throws Exception {
        this.tokens = listaDeTokens.iterator();
        avancar();

        while (atual.getTipo() != TipoToken.EOF) {
           declaracao();
        }
    }

    private void declaracao() throws Exception {
    	 switch(atual.getTipo()) {
         	case PC_DECLARACAO_FUNCAO:
         		declaracaoFuncao();
         		break;
         		
         	case PC_DECLARACAO_VARIAVEL:
         		declaracaoVariavel();
         		break;
         	
         	case IDENTIFICADOR:
         		atribuicao();
         		break;
         		
         	case PC_ESTRUTURA_WHILE:
         		estruturaWhile();
         		break;
         	
         	case PC_CONDICIONAL_IF:
         		estruturaCondicional();
         		break;
         		
         	default:
         		comando();
         
         }
    }

    private void declaracaoVariavel() throws Exception {
    	avancar();
    	
    	switch(atual.getTipo()) {
	    	case PC_TIPO_INTEIRO:
	    	case PC_TIPO_DOUBLE:
	    	case PC_TIPO_FLOAT:
	    	case PC_TIPO_LONG:
	    	case PC_TIPO_SHORT:
	    	case PC_TIPO_STRING:
	    		avancar();
	    		break;
	    	default:
	    		 throw new Exception("Token inesperado (encontrado: " + atual.getLexema() + ", linha " + atual.getLinha() + ")");
    		
    	}
    	
        consumir(TipoToken.IDENTIFICADOR, "Esperado nome da variável.");
        if (verifica(TipoToken.OP_IGUAL)) {
            avancar();
            expressao();
        }
        consumir(TipoToken.SE_PONTO_VIRGULA, "Esperado ';' após declaração.");
    }
    
    private void atribuicao() throws Exception {
    	avancar();
    	
        consumir(TipoToken.OP_IGUAL, "Esperado '=' após atribuição.");
        
        expressao();

        consumir(TipoToken.SE_PONTO_VIRGULA, "Esperado ';' após declaração.");
    }
    
    private void estruturaCondicional() throws Exception {
        consumir(TipoToken.PC_CONDICIONAL_IF, "Esperado '❓'.");
        consumir(TipoToken.SE_ABRE_PARENTESE, "Esperado '(' após '❓'.");
        
        expressao();
        
        consumir(TipoToken.SE_FECHA_PARENTESE, "Esperado ')' após condição.");
        consumir(TipoToken.PC_BLOCO_VERDADEIRO, "Esperado '✅' após condição.");
        bloco();

        if (verifica(TipoToken.PC_BLOCO_FALSO)) {
            avancar();
            bloco();
            
        }
    }
    
    private void declaracaoFuncao() throws Exception {
    	avancar();
        consumir(TipoToken.PC_TIPO_INTEIRO, "Esperado tipo de retorno.");
        consumir(TipoToken.IDENTIFICADOR, "Esperado nome da função.");
        consumir(TipoToken.SE_ABRE_PARENTESE, "Esperado '(' após o nome da função.");
        parametros();
        consumir(TipoToken.SE_FECHA_PARENTESE, "Esperado ')' após parâmetros.");
        bloco();
    }


    private void parametros() throws Exception {
        if (verifica(TipoToken.PC_TIPO_INTEIRO)) {
            avancar(); // tipo
            consumir(TipoToken.IDENTIFICADOR, "Esperado nome do parâmetro.");
        }
    }

    private void bloco() throws Exception {
        consumir(TipoToken.SE_ABRE_CHAVE, "Esperado '{' no início do bloco.");
        while (!verifica(TipoToken.SE_FECHA_CHAVE) && atual.getTipo() != TipoToken.EOF) {
            declaracao();
        }
        consumir(TipoToken.SE_FECHA_CHAVE, "Esperado '}' no fim do bloco.");
    }

    private void comando() throws Exception {
        if (verifica(TipoToken.PC_ESTRUTURA_WHILE)) {
            estruturaWhile();
        } else if (verifica(TipoToken.PC_RETORNO_FUNCAO)) {
            retorno();
        } else if (verifica(TipoToken.PC_IMPRIMIR)) {
            imprimir();
        } else {
            expressao();
            consumir(TipoToken.SE_PONTO_VIRGULA, "Esperado ';' após comando.");
        }
    }

    private void estruturaWhile() throws Exception {
    	avancar();
        consumir(TipoToken.SE_ABRE_PARENTESE, "Esperado '(' após while.");
        expressao();
        consumir(TipoToken.SE_FECHA_PARENTESE, "Esperado ')' após condição.");
        bloco();
    }

    private void retorno() throws Exception {
        consumir(TipoToken.PC_RETORNO_FUNCAO, null);
        expressao();
        consumir(TipoToken.SE_PONTO_VIRGULA, "Esperado ';' após retorno.");
    }

    private void imprimir() throws Exception {
        consumir(TipoToken.PC_IMPRIMIR, null);
        consumir(TipoToken.SE_ABRE_PARENTESE, "Esperado '(' após 🖨️.");
        expressao();
        consumir(TipoToken.SE_FECHA_PARENTESE, "Esperado ')' após expressão.");
        consumir(TipoToken.SE_PONTO_VIRGULA, "Esperado ';' após 🖨️.");
    }

    private void expressao() throws Exception {
        expressaoLogica();
    }

    private void expressaoLogica() throws Exception {
        expressaoIgualdade();
        while (verifica(TipoToken.OP_AND) || verifica(TipoToken.OP_OR)) {
            avancar();
            expressaoIgualdade();
        }
    }

    private void expressaoIgualdade() throws Exception {
        expressaoRelacional();
        while (verifica(TipoToken.OP_IGUAL_IGUAL) || verifica(TipoToken.OP_DIFERENTE)) {
            avancar();
            expressaoRelacional();
        }
    }

    private void expressaoRelacional() throws Exception {
        expressaoAditiva();
        while (verifica(TipoToken.OP_MAIOR) || verifica(TipoToken.OP_MENOR) ||
               verifica(TipoToken.OP_MAIOR_IGUAL) || verifica(TipoToken.OP_MENOR_IGUAL)) {
            avancar();
            expressaoAditiva();
        }
    }

    private void expressaoAditiva() throws Exception {
        expressaoMultiplicativa();
        while (verifica(TipoToken.OP_MAIS) || verifica(TipoToken.OP_MENOS)) {
            avancar();
            expressaoMultiplicativa();
        }
    }

    private void expressaoMultiplicativa() throws Exception {
        expressaoUnaria();
        while (verifica(TipoToken.OP_MULTIPLICACAO) || verifica(TipoToken.OP_DIVISAO)) {
            avancar();
            expressaoUnaria();
        }
    }

    private void expressaoUnaria() throws Exception {
        if (verifica(TipoToken.OP_NOT) || verifica(TipoToken.OP_MENOS)) {
            avancar();
            expressaoUnaria();
        } else {
            expressaoPrimaria();
        }
    }

    private void expressaoPrimaria() throws Exception {
        if (verifica(TipoToken.NUMERO) || verifica(TipoToken.TEXTO) || verifica(TipoToken.PC_VERDADEIRO) || verifica(TipoToken.PC_FALSO)) {
            avancar();
        } else if (verifica(TipoToken.IDENTIFICADOR)) {
            avancar();
            if (verifica(TipoToken.SE_ABRE_PARENTESE)) { // chamada de função
                avancar();
                if (!verifica(TipoToken.SE_FECHA_PARENTESE)) {
                    expressao(); // argumento(s)
                }
                consumir(TipoToken.SE_FECHA_PARENTESE, "Esperado ')' após chamada de função.");
            }
        } else if (verifica(TipoToken.SE_ABRE_PARENTESE)) {
            avancar();
            expressao();
            consumir(TipoToken.SE_FECHA_PARENTESE, "Esperado ')' após expressão.");
        } else {
            throw new Exception("Expressão primária inválida na linha " + atual.getLinha());
        }
    }


    private boolean verifica(TipoToken tipo) {
        return atual.getTipo() == tipo;
    }

    private void avancar() {
        if (tokens.hasNext()) {
            atual = tokens.next();
        }
    }

    private void consumir(TipoToken tipoEsperado, String mensagemErro) throws Exception {
        if (verifica(tipoEsperado)) {
            avancar();
        } else {
            throw new Exception((mensagemErro != null ? mensagemErro : "Token inesperado") +
                    " (encontrado: " + atual.getLexema() + ", linha " + atual.getLinha() + ")");
        }
    }
}
