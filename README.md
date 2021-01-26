<!--
  MIT License
  
  Copyright (c) 2021 Bruno Andrade
  
  Permission is hereby granted, free of charge, to any person obtaining a copy
  of this software and associated documentation files (the "Software"), to deal
  in the Software without restriction, including without limitation the rights
  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
  copies of the Software, and to permit persons to whom the Software is
  furnished to do so, subject to the following conditions:
  
  The above copyright notice and this permission notice shall be included in all
  copies or substantial portions of the Software.
  
  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
  SOFTWARE
  -->
## Como usar:
  - Criar a instância do MariaDB no Docker:
      - `docker run -p 3306:3306 --name mariadb10.4 -e MYSQL_ROOT_PASSWORD=root -d mariadb:10.4`
  - Realizar o clone do projeto.
  - Inicializar o projeto:
      - `mvn spring-boot:run`
  - Importar a `Collection do Postman` neste [link](https://www.getpostman.com/collections/b5d93fe2eb9da9a692d7).

# Cadastro de cervejas artesanais

O objetivo deste projeto é avaliar o desenvolvimento de uma aplicação java utilizando Spring e APIs.

### Baseado no [Building an Application with Spring Boot](https://spring.io/guides/gs/spring-boot/).

## Set up environment:

Para iniciar o projeto é necessário realizar o clone deste repositório:

```bash
    $ git clone https://github.com/Sensedia/craftbeer.git
```

Você deverá compartilhar no seu repositório do GitHub as alterações solicitadas para o projeto.<br />
O endereço deste repositório deverá ser enviado para `rh@sensedia.com`.

## Especificação do projeto:

A Beer House é uma empresa que possui um catálogo de cervejas artesanais e está buscando entrar no mundo digital.<br />
Para isto, a Beer House dicidiu começar pelas APIs.<br />
As APIs serão utilizadas para compartilhar dados com os parceiros e também para o seu sistema web.

Para atender a esta demanda será necessário que você implemente as APIs do projeto Beer House.

Para implementar estas APIs você deverá seguir a especificação do Swagger que está neste projeto:

* craftbeer
  * docs
    * swagger-craftbeer

## Requisitos do projeto:

1. Administrar cervejas:

  - O sistema deverá ter um cadastro de cervejas artesanais por API.
  - O sistema deverá ser capaz de criar, excluir e alterar as cervejas.


2. Sistema deverá armazenar os dados em banco de dados:

  - Poderá ser utilizado MYSQL ou qualquer banco de dados embbeded.
    - Caso seja utilizado o MYSQL, adicionar o script para criação do banco.
  - A comunicação com o sistema deverá ser feita através de JPA.


3. O sistema deve conter testes unitários com JUnit.

4. O sistema deve conter uma forma de validar o funcionamento.

5. Deverá ser diponibilizado uma coleção do Postman ou SoapUI para testar todos os recursos.

## O que será avaliado no projeto:

- Qualidade de código.
- Design Patterns utilizados.
- O sistema tem que estar completo e possuir todos os scripts necessários para a execução.
- A utilização do JPA de forma correta.
- A criação de testes unitários.

## O que você deve fazer:

- Utilizar java ao máximo e mostrar todo o seu conhecimento.
- Entregar o projeto completo.
- Usar Java 8 e deixar a gente bem feliz com isso!

## O que você pode fazer:

- Utilizar frameworks.
- Utilizar Spring Data ou qualquer outro framework para JPA.
- Alterar e criar o código à vontade.
- Consultar tutoriais, consultar fóruns e tirar dúvidas.
- Você pode aprender com código de outras pessoas, utilizar trechos, mas não usar tudo igual.

## O que você não pode fazer:

- Copiar de outros candidatos.
- Pedir alguém para fazer o projeto para você.

## Links de sugestão:

### [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/).
### [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/).
