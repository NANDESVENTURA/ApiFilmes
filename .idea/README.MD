# Api filmes

## Sobre
Esta api funciona como uma lista de filmes para assistir. Nela, o usuario salva os filmes, passando o nome,
a plataforma no qual o filme esta disponivel além do genero. Podendo depois de assistir, marcar o filme como assistido
e dar uma nota de 0 a 5.
<br>

## Requisitos
- [x] CRUD de Filmes.

- [x] Autenticação de usuário.

- [x] Conexão MySQL.




## Começando
- Primeiramente, instale as dependências utilizando ```maven```. 
- Antes de começar dever ter:
    - Uma conexão MySQL.
- Colocar no arquivo ```application.properties``` os dados sensiveis a aplicação, como o secret para o token e a conexão MySQL.
```application.properties
spring.datasource.url= < URL DE CONEXÃO DO BANCO >
spring.datasource.username=< USER DE CONEXÃO DO BANCO >
spring.datasource.password= < SENHA DE CONEXÃO DO BANCO >

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.error.include-stacktrace=never

api.security.token.secret= < SECRET DO TOKEN JWT >
```
Com essas variáveis de ambiente configuradas podemos executar nossa aplicação.

## Executando
Para a execução, basta dar run no arquivo ```ApiAplication```. 
- Para criar as migrations no banco, basta criar os aquivos ```.sql``` na pasta ```db``` e rodar a aplicação,
as micrations são criadas automaticamente.

- As rotas dispostas pela API são:
- ```/users  POST``` : Para cadastro do usuário,informando o ```name```,```email``` e ```password``` pelo body.
- ```/login POST``` : Para autenticação do usuário,  passando o ```name``` e ```password``` pelo body.

<br>Todas abaixo precisam de autenticação


- ```/films POST```: Cadastra um filme no banco de dados, passando os campos
"name","plataform" e "gender".
- ```/films/userid?watched=:true/false GET``` : Retorna todos os filmes do usuário logado, diferenciando entre assistido ou não, pelo campo watched como true ou false passado pela rota.
- ```/films/:id GET``` : Retorna o filme de acordo com o id passado pela rota.
- ```/films PUT``` : Altera o nome, a plataforma ou o genero do filme selecionado.
- ```/films/watching PUT``` : Atualiza o filme selecionado, como assistido além de adicionar uma nota ao filme.
- ```/films/:id DELETE``` : Deleta o filme de acordo com o ID passado na rota.


## Autor
*Ernandes Ventura Silva Neto*

[![Linkedin Badge](https://img.shields.io/badge/-Ernandes%20Ventura-6633cc?style=flat-square&logo=Linkedin&logoColor=black&link=https://www.linkedin.com/in/ernandes-ventura-892a88119/)](https://www.linkedin.com/in/ernandes-ventura-892a88119/)