package com.braian.sintatico;

import java.io.IOException;
import java.util.List;

import com.braian.io.AnalisadoresIO;
import com.braian.io.ArquivoIO;
import com.braian.lexico.TipoToken;
import com.braian.lexico.Token;

public class AnalisadorSintatico {
	
    private List<Token> tokens;
    private int posicao;
    private Token tokenAtual;
    
    private final StringBuilder relatorio = new StringBuilder();
    private final AnalisadoresIO io = new AnalisadoresIO();
    private boolean erroSintaticoEncontrado = false;
    private NoArvoreSintatica raizArvore;

    public void analisar(List<Token> tokens) throws IOException {
        this.tokens = tokens;
        this.posicao = 0;
        this.tokenAtual = tokens.isEmpty() ? null : tokens.get(0);
        this.erroSintaticoEncontrado = false;
        relatorio.setLength(0);
        
        io.imprimirCabecalho("Analisador Sintático");
        raizArvore = new NoArvoreSintatica("Programa");
        
        programa();
        
        relatorio.append("\nÁrvore Sintática:\n");
        relatorio.append(raizArvore.toString());
        relatorio.append("\nAnálise sintática ");
        
        if (erroSintaticoEncontrado) {
            relatorio.append("concluída com erros.");
            io.imprimirErro("A análise sintática encontrou erros!");
            io.imprimirRodape(true);
        } else {
            relatorio.append("concluída com sucesso!");
            io.imprimirRodape(false);
        }
        
        salvarRelatorioEmArquivo();
    }

    private void avancar() {
        posicao++;
        if (posicao < tokens.size()) {
            tokenAtual = tokens.get(posicao);
        } else {
            tokenAtual = null;
        }
    }

    private boolean verificar(TipoToken tipo) {
        return tokenAtual != null && tokenAtual.getTipo() == tipo;
    }

    private void consumir(TipoToken tipoEsperado, String mensagemErro) {
        if (tokenAtual == null) {
            reportarErro("Fim inesperado do arquivo. " + mensagemErro);
            return;
        }
        
        if (tokenAtual.getTipo() == tipoEsperado) {
            relatorio.append("[S] Token esperado encontrado: ")
                     .append(tipoEsperado)
                     .append(" ('").append(tokenAtual.getLexema()).append("')")
                     .append(" na linha ").append(tokenAtual.getLinha())
                     .append(", coluna ").append(tokenAtual.getColuna())
                     .append("\n");
            avancar();
            
        } else {
            reportarErro(mensagemErro + ". Encontrado: " + 
                         tokenAtual.getTipo() + " ('" + tokenAtual.getLexema() + "')");
        }
    }

    private void reportarErro(String mensagem) {
        erroSintaticoEncontrado = true;
        String erro = "ERRO SINTÁTICO [Linha " + tokenAtual.getLinha() + 
                      ", Coluna " + tokenAtual.getColuna() + "]: " + mensagem;
        relatorio.append("[E] ").append(erro).append("\n");
        io.imprimirErro(erro);
    }

    private void programa() {
        NoArvoreSintatica noDeclaracoes = new NoArvoreSintatica("Declarações");
        raizArvore.adicionarFilho(noDeclaracoes);
        
        while (tokenAtual != null && tokenAtual.getTipo() != TipoToken.EOF) {
            if (verificar(TipoToken.PC_DECLARACAO_VARIAVEL) || 
                verificar(TipoToken.PC_DECLARACAO_FUNCAO)) {
                NoArvoreSintatica noDeclaracao = declaracao();
                noDeclaracoes.adicionarFilho(noDeclaracao);
            } else {
                NoArvoreSintatica noComando = comando();
                noDeclaracoes.adicionarFilho(noComando);
            }
        }
        consumir(TipoToken.EOF, "Esperado fim de arquivo");
    }

    private NoArvoreSintatica declaracao() {
        if (verificar(TipoToken.PC_DECLARACAO_VARIAVEL)) {
            return declaracaoVariavel();
        } else if (verificar(TipoToken.PC_DECLARACAO_FUNCAO)) {
            return declaracaoFuncao();
        } else {
            reportarErro("Esperado declaração de variável ou função");
            return new NoArvoreSintatica("DeclaraçãoInválida");
        }
    }

    private NoArvoreSintatica declaracaoVariavel() {
        NoArvoreSintatica noDeclVar = new NoArvoreSintatica("DeclaraçãoVariável");
        
        consumir(TipoToken.PC_DECLARACAO_VARIAVEL, "Esperado '☕'");
        noDeclVar.adicionarFilho(new NoArvoreSintatica("PalavraChave", tokenAtual));
        
        NoArvoreSintatica noTipo = tipo();
        noDeclVar.adicionarFilho(noTipo);
        
        consumir(TipoToken.IDENTIFICADOR, "Esperado identificador");
        noDeclVar.adicionarFilho(new NoArvoreSintatica("Identificador", tokenAtual));
        
        if (verificar(TipoToken.OP_IGUAL)) {
            avancar();
            NoArvoreSintatica noExpressao = expressao();
            NoArvoreSintatica noAtribuicao = new NoArvoreSintatica("Atribuição");
            noAtribuicao.adicionarFilho(noExpressao);
            noDeclVar.adicionarFilho(noAtribuicao);
        }
        
        consumir(TipoToken.SE_PONTO_VIRGULA, "Esperado ';'");
        noDeclVar.adicionarFilho(new NoArvoreSintatica("PontoVírgula", tokenAtual));
        
        return noDeclVar;
    }

    private NoArvoreSintatica tipo() {
        NoArvoreSintatica noTipo = new NoArvoreSintatica("Tipo");
        if (verificar(TipoToken.PC_TIPO_INTEIRO) || 
            verificar(TipoToken.PC_TIPO_SHORT) ||
            verificar(TipoToken.PC_TIPO_LONG) ||
            verificar(TipoToken.PC_TIPO_FLOAT) ||
            verificar(TipoToken.PC_TIPO_DOUBLE) ||
            verificar(TipoToken.PC_TIPO_STRING) ||
            verificar(TipoToken.PC_TIPO_BOOLEANO)) {
            noTipo.adicionarFilho(new NoArvoreSintatica("TipoDado", tokenAtual));
            avancar();
        } else {
            reportarErro("Esperado tipo de dado");
        }
        return noTipo;
    }

    private NoArvoreSintatica declaracaoFuncao() {
        NoArvoreSintatica noDeclFuncao = new NoArvoreSintatica("DeclaraçãoFunção");
        
        consumir(TipoToken.PC_DECLARACAO_FUNCAO, "Esperado '🆕'");
        noDeclFuncao.adicionarFilho(new NoArvoreSintatica("PalavraChave", tokenAtual));
        
        NoArvoreSintatica noTipo = tipo();
        noDeclFuncao.adicionarFilho(noTipo);
        
        consumir(TipoToken.IDENTIFICADOR, "Esperado nome da função");
        noDeclFuncao.adicionarFilho(new NoArvoreSintatica("NomeFunção", tokenAtual));
        
        consumir(TipoToken.SE_ABRE_PARENTESE, "Esperado '('");
        noDeclFuncao.adicionarFilho(new NoArvoreSintatica("AbreParentese", tokenAtual));
        
        // Parâmetros
        if (!verificar(TipoToken.SE_FECHA_PARENTESE)) {
            NoArvoreSintatica noParametros = parametros();
            noDeclFuncao.adicionarFilho(noParametros);
        }
        
        consumir(TipoToken.SE_FECHA_PARENTESE, "Esperado ')'");
        noDeclFuncao.adicionarFilho(new NoArvoreSintatica("FechaParentese", tokenAtual));
        
        NoArvoreSintatica noBloco = bloco();
        noDeclFuncao.adicionarFilho(noBloco);
        
        return noDeclFuncao;
    }

    private NoArvoreSintatica parametros() {
        NoArvoreSintatica noParametros = new NoArvoreSintatica("Parâmetros");
        do {
            NoArvoreSintatica noParametro = new NoArvoreSintatica("Parâmetro");
            
            NoArvoreSintatica noTipo = tipo();
            noParametro.adicionarFilho(noTipo);
            
            consumir(TipoToken.IDENTIFICADOR, "Esperado identificador do parâmetro");
            noParametro.adicionarFilho(new NoArvoreSintatica("Identificador", tokenAtual));
            
            noParametros.adicionarFilho(noParametro);
            
            if (verificar(TipoToken.SE_VIRGULA)) {
                avancar();
                noParametros.adicionarFilho(new NoArvoreSintatica("Vírgula", tokenAtual));
            } else {
                break;
            }
        } while (true);
        return noParametros;
    }

    private NoArvoreSintatica bloco() {
        NoArvoreSintatica noBloco = new NoArvoreSintatica("Bloco");
        
        consumir(TipoToken.SE_ABRE_CHAVE, "Esperado '{'");
        noBloco.adicionarFilho(new NoArvoreSintatica("AbreChave", tokenAtual));
        
        while (tokenAtual != null && 
               !verificar(TipoToken.SE_FECHA_CHAVE) && 
               !verificar(TipoToken.EOF)) {
            NoArvoreSintatica noComando = comando();
            noBloco.adicionarFilho(noComando);
        }
        
        consumir(TipoToken.SE_FECHA_CHAVE, "Esperado '}'");
        noBloco.adicionarFilho(new NoArvoreSintatica("FechaChave", tokenAtual));
        
        return noBloco;
    }

    private NoArvoreSintatica comando() {
        if (verificar(TipoToken.PC_IMPRIMIR)) {
            return comandoImprimir();
        } else if (verificar(TipoToken.PC_CONDICIONAL_IF)) {
            return comandoIf();
        } else if (verificar(TipoToken.PC_ESTRUTURA_WHILE)) {
            return comandoWhile();
        } else if (verificar(TipoToken.PC_RETORNO_FUNCAO)) {
            return comandoReturn();
        } else if (verificar(TipoToken.PC_DECLARACAO_VARIAVEL)) {
            return declaracaoVariavel();
        } else if (verificar(TipoToken.SE_ABRE_CHAVE)) {
            return bloco();
        } else if (verificar(TipoToken.IDENTIFICADOR)) {
            return comandoAtribuicao();
        } else {
            reportarErro("Comando inválido");
            avancar();
            return new NoArvoreSintatica("ComandoInválido");
        }
    }

    private NoArvoreSintatica comandoImprimir() {
        NoArvoreSintatica noImprimir = new NoArvoreSintatica("ComandoImprimir");
        
        consumir(TipoToken.PC_IMPRIMIR, "Esperado '🖨️'");
        noImprimir.adicionarFilho(new NoArvoreSintatica("PalavraChave", tokenAtual));
        
        consumir(TipoToken.SE_ABRE_PARENTESE, "Esperado '('");
        noImprimir.adicionarFilho(new NoArvoreSintatica("AbreParentese", tokenAtual));
        
        NoArvoreSintatica noExpressao = expressao();
        noImprimir.adicionarFilho(noExpressao);
        
        while (verificar(TipoToken.SE_VIRGULA)) {
            avancar();
            noImprimir.adicionarFilho(new NoArvoreSintatica("Vírgula", tokenAtual));
            noExpressao = expressao();
            noImprimir.adicionarFilho(noExpressao);
        }
        
        consumir(TipoToken.SE_FECHA_PARENTESE, "Esperado ')'");
        noImprimir.adicionarFilho(new NoArvoreSintatica("FechaParentese", tokenAtual));
        
        consumir(TipoToken.SE_PONTO_VIRGULA, "Esperado ';'");
        noImprimir.adicionarFilho(new NoArvoreSintatica("PontoVírgula", tokenAtual));
        
        return noImprimir;
    }

    private NoArvoreSintatica comandoIf() {
        NoArvoreSintatica noIf = new NoArvoreSintatica("ComandoIf");
        
        consumir(TipoToken.PC_CONDICIONAL_IF, "Esperado '❓'");
        noIf.adicionarFilho(new NoArvoreSintatica("PalavraChave", tokenAtual));
        
        consumir(TipoToken.SE_ABRE_PARENTESE, "Esperado '('");
        noIf.adicionarFilho(new NoArvoreSintatica("AbreParentese", tokenAtual));
        
        NoArvoreSintatica noCondicao = expressao();
        noIf.adicionarFilho(new NoArvoreSintatica("Condição"));
        noIf.adicionarFilho(noCondicao);
        
        consumir(TipoToken.SE_FECHA_PARENTESE, "Esperado ')'");
        noIf.adicionarFilho(new NoArvoreSintatica("FechaParentese", tokenAtual));
        
        consumir(TipoToken.PC_BLOCO_VERDADEIRO, "Esperado '✅'");
        noIf.adicionarFilho(new NoArvoreSintatica("PalavraChave", tokenAtual));
        
        NoArvoreSintatica noBlocoIf = bloco();
        noIf.adicionarFilho(noBlocoIf);
        
        if (verificar(TipoToken.PC_BLOCO_FALSO)) {
            avancar();
            noIf.adicionarFilho(new NoArvoreSintatica("PalavraChave", tokenAtual));
            
            NoArvoreSintatica noBlocoElse = bloco();
            noIf.adicionarFilho(noBlocoElse);
        }
        
        return noIf;
    }

    private NoArvoreSintatica comandoWhile() {
        NoArvoreSintatica noWhile = new NoArvoreSintatica("ComandoWhile");
        
        consumir(TipoToken.PC_ESTRUTURA_WHILE, "Esperado '⏳'");
        noWhile.adicionarFilho(new NoArvoreSintatica("PalavraChave", tokenAtual));
        
        consumir(TipoToken.SE_ABRE_PARENTESE, "Esperado '('");
        noWhile.adicionarFilho(new NoArvoreSintatica("AbreParentese", tokenAtual));
        
        NoArvoreSintatica noCondicao = expressao();
        noWhile.adicionarFilho(new NoArvoreSintatica("Condição"));
        noWhile.adicionarFilho(noCondicao);
        
        consumir(TipoToken.SE_FECHA_PARENTESE, "Esperado ')'");
        noWhile.adicionarFilho(new NoArvoreSintatica("FechaParentese", tokenAtual));
        
        NoArvoreSintatica noBloco = bloco();
        noWhile.adicionarFilho(noBloco);
        
        return noWhile;
    }

    private NoArvoreSintatica comandoReturn() {
        NoArvoreSintatica noReturn = new NoArvoreSintatica("ComandoReturn");
        
        consumir(TipoToken.PC_RETORNO_FUNCAO, "Esperado '🔚'");
        noReturn.adicionarFilho(new NoArvoreSintatica("PalavraChave", tokenAtual));
        
        if (!verificar(TipoToken.SE_PONTO_VIRGULA)) {
            NoArvoreSintatica noExpressao = expressao();
            noReturn.adicionarFilho(noExpressao);
        }
        
        consumir(TipoToken.SE_PONTO_VIRGULA, "Esperado ';'");
        noReturn.adicionarFilho(new NoArvoreSintatica("PontoVírgula", tokenAtual));
        
        return noReturn;
    }

    private NoArvoreSintatica comandoAtribuicao() {
        NoArvoreSintatica noAtribuicao = new NoArvoreSintatica("ComandoAtribuição");
        
        consumir(TipoToken.IDENTIFICADOR, "Esperado identificador");
        noAtribuicao.adicionarFilho(new NoArvoreSintatica("Identificador", tokenAtual));
        
        consumir(TipoToken.OP_IGUAL, "Esperado '='");
        noAtribuicao.adicionarFilho(new NoArvoreSintatica("Operador", tokenAtual));
        
        NoArvoreSintatica noExpressao = expressao();
        noAtribuicao.adicionarFilho(noExpressao);
        
        consumir(TipoToken.SE_PONTO_VIRGULA, "Esperado ';'");
        noAtribuicao.adicionarFilho(new NoArvoreSintatica("PontoVírgula", tokenAtual));
        
        return noAtribuicao;
    }

    private NoArvoreSintatica expressao() {
        return expressaoLogica();
    }

    private NoArvoreSintatica expressaoLogica() {
        NoArvoreSintatica noExpressao = expressaoComparacao();
        
        while (verificar(TipoToken.OP_AND) || verificar(TipoToken.OP_OR)) {
            Token operador = tokenAtual;
            avancar();
            NoArvoreSintatica noDireita = expressaoComparacao();
            
            NoArvoreSintatica noOperacao = new NoArvoreSintatica("OperaçãoLógica");
            noOperacao.adicionarFilho(noExpressao);
            noOperacao.adicionarFilho(new NoArvoreSintatica("Operador", operador));
            noOperacao.adicionarFilho(noDireita);
            
            noExpressao = noOperacao;
        }
        
        return noExpressao;
    }

    private NoArvoreSintatica expressaoComparacao() {
        NoArvoreSintatica noExpressao = expressaoAditiva();
        
        while (verificar(TipoToken.OP_IGUAL_IGUAL) || 
               verificar(TipoToken.OP_DIFERENTE) ||
               verificar(TipoToken.OP_MAIOR) ||
               verificar(TipoToken.OP_MAIOR_IGUAL) ||
               verificar(TipoToken.OP_MENOR) ||
               verificar(TipoToken.OP_MENOR_IGUAL)) {
            Token operador = tokenAtual;
            avancar();
            NoArvoreSintatica noDireita = expressaoAditiva();
            
            NoArvoreSintatica noOperacao = new NoArvoreSintatica("OperaçãoComparação");
            noOperacao.adicionarFilho(noExpressao);
            noOperacao.adicionarFilho(new NoArvoreSintatica("Operador", operador));
            noOperacao.adicionarFilho(noDireita);
            
            noExpressao = noOperacao;
        }
        
        return noExpressao;
    }

    private NoArvoreSintatica expressaoAditiva() {
        NoArvoreSintatica noExpressao = expressaoMultiplicativa();
        
        while (verificar(TipoToken.OP_MAIS) || verificar(TipoToken.OP_MENOS)) {
            Token operador = tokenAtual;
            avancar();
            NoArvoreSintatica noDireita = expressaoMultiplicativa();
            
            NoArvoreSintatica noOperacao = new NoArvoreSintatica("OperaçãoAditiva");
            noOperacao.adicionarFilho(noExpressao);
            noOperacao.adicionarFilho(new NoArvoreSintatica("Operador", operador));
            noOperacao.adicionarFilho(noDireita);
            
            noExpressao = noOperacao;
        }
        
        return noExpressao;
    }

    private NoArvoreSintatica expressaoMultiplicativa() {
        NoArvoreSintatica noExpressao = expressaoPrimaria();
        
        while (verificar(TipoToken.OP_MULTIPLICACAO) || verificar(TipoToken.OP_DIVISAO)) {
            Token operador = tokenAtual;
            avancar();
            NoArvoreSintatica noDireita = expressaoPrimaria();
            
            NoArvoreSintatica noOperacao = new NoArvoreSintatica("OperaçãoMultiplicativa");
            noOperacao.adicionarFilho(noExpressao);
            noOperacao.adicionarFilho(new NoArvoreSintatica("Operador", operador));
            noOperacao.adicionarFilho(noDireita);
            
            noExpressao = noOperacao;
        }
        
        return noExpressao;
    }

    private NoArvoreSintatica expressaoPrimaria() {
        if (verificar(TipoToken.IDENTIFICADOR)) {
            Token identificador = tokenAtual;
            avancar();
            
            if (verificar(TipoToken.SE_ABRE_PARENTESE)) {
                NoArvoreSintatica noChamada = new NoArvoreSintatica("ChamadaFunção");
                noChamada.adicionarFilho(new NoArvoreSintatica("Identificador", identificador));
                
                avancar();
                noChamada.adicionarFilho(new NoArvoreSintatica("AbreParentese", tokenAtual));
                
                if (!verificar(TipoToken.SE_FECHA_PARENTESE)) {
                    NoArvoreSintatica noArgumentos = argumentos();
                    noChamada.adicionarFilho(noArgumentos);
                }
                
                consumir(TipoToken.SE_FECHA_PARENTESE, "Esperado ')'");
                noChamada.adicionarFilho(new NoArvoreSintatica("FechaParentese", tokenAtual));
                
                return noChamada;
            } else {
                return new NoArvoreSintatica("Identificador", identificador);
            }
        } else if (verificar(TipoToken.NUMERO) || 
                   verificar(TipoToken.DECIMAl) ||
                   verificar(TipoToken.TEXTO) ||
                   verificar(TipoToken.PC_VERDADEIRO) ||
                   verificar(TipoToken.PC_FALSO) ||
                   verificar(TipoToken.PC_NULO)) {
            NoArvoreSintatica noLiteral = new NoArvoreSintatica("Literal", tokenAtual);
            avancar();
            return noLiteral;
        } else if (verificar(TipoToken.SE_ABRE_PARENTESE)) {
            NoArvoreSintatica noParentese = new NoArvoreSintatica("ExpressãoEntreParênteses");
            
            avancar();
            noParentese.adicionarFilho(new NoArvoreSintatica("AbreParentese", tokenAtual));
            
            NoArvoreSintatica noExpressao = expressao();
            noParentese.adicionarFilho(noExpressao);
            
            consumir(TipoToken.SE_FECHA_PARENTESE, "Esperado ')'");
            noParentese.adicionarFilho(new NoArvoreSintatica("FechaParentese", tokenAtual));
            
            return noParentese;
        } else {
            reportarErro("Expressão primária inválida");
            return new NoArvoreSintatica("ExpressãoInválida");
        }
    }

    private NoArvoreSintatica argumentos() {
        NoArvoreSintatica noArgumentos = new NoArvoreSintatica("Argumentos");
        
        NoArvoreSintatica noExpressao = expressao();
        noArgumentos.adicionarFilho(noExpressao);
        
        while (verificar(TipoToken.SE_VIRGULA)) {
            avancar();
            noArgumentos.adicionarFilho(new NoArvoreSintatica("Vírgula", tokenAtual));
            
            noExpressao = expressao();
            noArgumentos.adicionarFilho(noExpressao);
        }
        
        return noArgumentos;
    }
    
    private void salvarRelatorioEmArquivo() throws IOException {
        ArquivoIO arquivoIO = new ArquivoIO();
        arquivoIO.escreverArquivo("output/AnaliseSintatica.txt", relatorio.toString());
    }
}