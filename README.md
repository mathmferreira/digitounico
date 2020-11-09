# API Digito Unico

## Descrição

O projeto é uma API cuja sua função é receber um número inteiro e, através de um cálculo, retornar qual o digito único correspondente ao número inteiro recebido.

**n**: uma string representado um inteiro. **1 <= n <= 10ˆ1000000**

**k**: um inteiro representando o número de vezes da concatenação **1 <= k <= 10ˆ5**

*Exemplo:*

```
n = 9875, k = 4
digitoUnico(n, k) = 9875 9875 9875 9875
calculoDigitoUnico(9875987598759875) = 9 + 8 + 7 + 5 + 9 + 8 + 7 + 5 + 9 + 8 + 7 + 5 + 9 + 8 + 7 + 5 = 116
calculoDigitoUnico(116) = 1 + 1 + 6 = 8

Portanto o digito único de 9875 na concatenação 4 é igual a 8.
```
### Linguagem utilizada

- Java 8

### Frameworks utilizados

- SpringBoot
- Lombok

### Banco de Dados

- H2 (Embedded)

### DevOPS/Infra Stack

- Git
- SonarLint
- Swagger

### Testes

Foi utilizado o JUnit para montagem dos testes em backend, existe também o arquivo **postman_collection.json** na raiz do projeto para realizar os testes integrados com o software Postman

Para testes com o Postman, se faz necessário colocar a ordem de execução conforme a imagem disponivel em https://imgur.com/a/fGjCKLi

Para rodar os testes unitários da aplicação basta utilizar o comando padrão do maven

```
mvn test
```

ou acesse as classes abaixo e rode a aplicação pelo JUnit no Eclipse ou sua IDE de preferência.

```
digitounico-api > src > test > java > br > com > digitounico > test > UsuarioControllerTests.java
digitounico-api > src > test > java > br > com > digitounico > test > DigitoUnicoControllerTests.java
```

### Instruções para a execução

Para iniciar aplicação basta ir a classe principal dentro do módulo da API (digitounico-api) e rodar como uma aplicação SpringBoot

```
digitounico-api > br > com > digitounico > DigitoUnicoApplication.java
```

### Documentação da API

Realizada através do Swagger e disponível em execução na URL **http://localhost:8080/documentation/swagger-ui/index.html**


### Conclusão

Qualquer dúvida, sugestão ou caso queira entrar em contato, basta enviar um e-mail para **mathmferreira@hotmail.com** (Matheus Maia). 
