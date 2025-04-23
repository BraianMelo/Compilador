# ☕ JavaMoji - Compilador com Emojis

Projeto desenvolvido para a disciplina de **Compiladores**, no 6º período do curso de Ciência da Computação.

O **JavaMoji** é uma linguagem de programação fictícia que utiliza **emojis** como palavras-chave. O objetivo principal é implementar um compilador funcional, passando pelas etapas de análise léxica, análise sintática e demais fases do front-end de compiladores.

---

## 🚀 Requisitos

Antes de compilar ou executar o projeto, certifique-se de ter o seguinte instalado:

- **Java JDK 21+**
- **Apache Maven**

---

## ⚙️ Como Compilar e Executar

1. **Clone o repositório:**

```bash
git clone https://github.com/BraianMelo/Compilador.git
cd Compilador
```

2. **Compile o projeto:**

```bash
mvn compile
```

3. **Execute o analisador léxico:**

```bash
mvn exec:java
```

### Observações
O código aceita como entrada o caminho do arquivo que deve ser compilado. Como estamos usando o **Maven**, o argumento padrão está definido no *pom.xml*. Caso queira compilar outro arquivo, será necessário alterar esse argumento diretamente no *pom.xml*.

4. **Sáida do programa:**

As análises léxicas e sintáticas estarão nos arquivos na pasta *output/*. Apenas os erros serão mostrados pelo terminal.
 
---

## 📖 Dicionário de Emojis

| Emoji | Significado (TipoToken)            |
|-------|-------------------------------------|
| ☕    | DECLARACAO_VARIAVEL                |
| 🖨️    | IMPRIMIR                           |
| ❓    | CONDICIONAL_IF                     |
| ✅    | BLOCO_VERDADEIRO                   |
| ❌    | BLOCO_FALSO                        |
| 🔁    | ESTRUTURA_FOR                      |
| 🛑    | INSTRUCAO_BREAK                    |
| 🆕    | DECLARACAO_FUNCAO                  |
| 🔚    | RETORNO_FUNCAO                     |
| 👍    | VERDADEIRO                         |
| 👎    | FALSO                              |
| 🕳️    | NULO                               |
| ⌨️    | ENTRADA                            |
| 📑    | CONDICIONAL_SWITCH                 |
| ✏️    | CASE                               |
| 📝    | DEFAULT                            |
| ⏳    | ESTRUTURA_WHILE                    |
| ⏩    | INSTRUCAO_CONTINUE                 |

---

## 👨‍💻 Autor

Braian - Estudante de Ciência da Computação  
**Instituição:** UFSJ
**Período:** 6º

---

## 📂 Licença

Este projeto está licenciado. Consulte o arquivo `LICENSE` para mais detalhes.