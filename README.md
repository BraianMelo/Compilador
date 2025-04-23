# ☕ JavaMoji - Compilador

Projeto desenvolvido para a disciplina de **Compiladores**, no 6º período do curso de Ciência da Computação.

O **JavaMoji** é uma linguagem de programação que utiliza **emojis** como palavras-chave. O objetivo principal é implementar um compilador funcional, passando pelas etapas de análise léxica, análise sintática e demais fases do front-end de compiladores.

## 📁 Estrutura do Projeto


```bash
📦 Compilador
├── 📂 src
│   └── 📂 main
│       └── 📂 java
│           └── 📂 com.braian
│               ├── App.java                # Classe principal
│               ├── 📂 io
│               │   ├── AnalisadoresIO.java        # Interface para I/O dos analisadores
│               │   ├── ArquivosIO.java            # Leitura e escrita em arquivos
│               │   └── AnalisadorLexicoIO.java    # Saída formatada do analisador léxico
│               └── 📂 lexico
│                   ├── AnalisadorLexico.java      # Implementação da análise léxica
│                   ├── Token.java                 # Representação de um token
│                   ├── TipoToken.java             # Enum dos tipos de tokens
│                   ├── RegraToken.java            # Classe de regra para reconhecimento de tokens
│                   └── ExpressoesRegulares.java   # Regras com expressões regulares para tokens
├── 📂 output                                      # Diretório de saída dos arquivos gerados
├── pom.xml                                        # Arquivo de configuração do Maven
└── README.md                                      # Documentação do projeto
```

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
O código aceita como entrada o caminho do arquivo que deve ser compilado. Como estamos usando o **Maven**, o argumento padrão está definido no '*pom.xml*'. Caso queira compilar outro arquivo, será necessário alterar esse argumento diretamente no *pom.xml*.

4. **Sáida do programa:**

As análises léxicas e sintáticas estarão nos arquivos na pasta *output/*. Apenas os erros serão mostrados pelo terminal.
 
---
## 💡 Funcionalidades Implementadas
Até o momento, apenas as seguintes funcionalidades foram implementadas:

- [x] Analisador Léxico
- [ ] Analisador Sintático
- [ ] Analisador Semântico

## 📖 Dicionário de Emojis
Abaixo estão alguns dos emojis usados no projeto.

| Emoji | Significado (TipoToken)            |
|-------|------------------------------------|
| ☕    | DECLARACAO VARIAVEL                |
| 🖨️    | IMPRIMIR                           |
| ❓    | CONDICIONAL IF                     |
| ✅    | BLOCO VERDADEIRO                   |
| ❌    | BLOCO FALSO                        |
| 🔁    | FOR                                |
| 🛑    | INSTRUCAO BREAK                    |
| 🆕    | DECLARACAO FUNCAO                  |
| 🔚    | RETORNO FUNCAO                     |
| 👍    | VERDADEIRO                         |
| 👎    | FALSO                              |
| 🕳️    | NULO                               |
| ⌨️    | ENTRADA                            |
| 📑    | SWITCH                             |
| ✏️    | CASE                               |
| 📝    | DEFAULT                            |
| ⏳    | ESTRUTURA WHILE                    |
| ⏩    | INSTRUCAO CONTINUE                 |

---

## 👨‍💻 Autor

Braian - Estudante de Ciência da Computação  
**Instituição:** UFSJ
**Período:** 6º

---

## 📂 Licença

Este projeto está licenciado. Consulte o arquivo `LICENSE` para mais detalhes.