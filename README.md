###  ProjectJavaSpringboot4
Api java com Backend que realiza chamadas REST ao banco dados relacional
Com Documentação, Autenticação, Autorização e Teste.

### 🔨 DEPENDÊNCIAS:

### JAVA 17:
spring-boot-starter-web
spring-boot-devtools
spring-boot-starter-data-jpa
Lombok
spring-boot-maven-plugin

### Teste em Junit, Mock (Mokito):
spring-boot-starter-test
spring-security-test

### Tratamento de erros, Validação exceptions, Usuários e serviço de autenticação:
spring-boot-starter-validation
spring-boot-starter-security

### MySql/Migrations:
flyway-mysql
mysql-connector-j

### Serviços com Securty, TokenJwt, TokenService:
com.auth0 - java-jwt - 4.4.0

### Documentação:
springdoc-openapi-starter-webmvc-ui - 2.6.0

### 🔨 passo a passo do projeto

- Realizar um start no Mysql com localhost:3306
- No java Springboot, ir no arquivo aplication.properties e ajustar os valores de login e senha.
- Subir a aplicação java Springboot - http://localhost:8080/api - starter no Application (spring boot dashboard)
- acessar o http://localhost:8080/swagger-ui/index.html#/
- ir em /login e efetuar o login gerando o TOKEN

  "login": "a.souza@voll.med",
  "senha": "123456"

- Clicar no botão Authorize do Swagger e informar o TOKEN
- Agora será possível realizar todas as ações.

Obs 1: caso obtenha um erro na criação das migrations
talvez seja necessário ir no Mysql e criar o banco de dados: vollmed_api

Obs 2: caso obtenha um erro na utilizacao do usuario informado anteriormente
Poderá criá-lo diretamente na tabela usuarios no Mysql 
o login no padrão de email e uma senha preferenciamente com o formato Bcript.

###
