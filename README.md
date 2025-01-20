# Spring Boot to do list

Este é um projeto criado para um teste técnico. Abaixo estão os detalhes sobre os comandos disponíveis, documentação no Postman e vídeos explicativos sobre como rodar o projeto em localhost, suas features e o processo de deploy.

## Descrição do Projeto

Este projeto foi desenvolvido utilizando Java com o framework Spring Boot, com o objetivo de criar uma aplicação de lista de tarefas. Durante o desenvolvimento, tive a oportunidade de aprender e aprimorar diversas habilidades técnicas, incluindo a configuração do Spring Boot, a criação de endpoints RESTful, a integração com bancos de dados e a implementação de testes automatizados. Este projeto não só me ajudou a entender melhor o ecossistema Java/Spring, mas também me proporcionou um valioso aprendizado prático que será útil em futuros projetos.

# Requisitos

- **Java 21**: Certifique-se de ter o JDK 21 instalado. Você pode baixá-lo do site oficial da Oracle ou usar um gerenciador de pacotes como SDKMAN.
- **SQL Server**: Um banco de dados SQL Server deve estar disponível e configurado. Você pode usar uma instância local ou um serviço em nuvem.
- **DevContainer**: O projeto utiliza DevContainers para desenvolvimento em ambientes isolados. Certifique-se de ter o Visual Studio Code e a extensão Remote - Containers instalada.
- **Docker**: Docker é necessário para criar e gerenciar os contêineres do DevContainer. Instale o Docker Desktop ou Docker Engine.
- **Node.js**: Node.js é necessário para executar scripts e ferramentas de desenvolvimento. Instale a versão mais recente do Node.js.

## Comandos Disponíveis

- **docker**: `docker-compose -f docker-compose-dev.yml up -d`
  - Este comando inicia a stack do docker com o projeto e o sql server.

- **npm run build**: `mvn package shade:shade -DskipTests -Pprod`
  - Este comando empacota o projeto, criando um arquivo JAR, sem executar os testes, e utilizando o perfil de produção.

- **npm run deploy**: `serverless deploy`
  - Este comando realiza o deploy do projeto utilizando o framework Serverless.

- **npm install:java:dependencies**: `mvn install -DskipTests`
  - Este comando instala todas as dependências do projeto, sem executar os testes.

- **npm run start:dev**: `mvn spring-boot:run`
  - Este comando inicia o projeto em modo de desenvolvimento utilizando o Spring Boot.

- **npm run db:migrate**: `mvn flyway:migrate`
  - Este comando aplica as migrações de banco de dados utilizando o Flyway.

- **npm run lint:test**: `mvn spotless:check`
  - Este comando verifica se o código está formatado corretamente utilizando o Spotless.

- **npm run lint:fix**: `mvn spotless:apply`
  - Este comando formata o código automaticamente utilizando o Spotless.

- **npm run test**: `mvn test`
  - Este comando executa os testes do projeto.

- **npm run test:coverage**: `mvn test jacoco:report`
  - Este comando executa os testes e gera um relatório de cobertura de testes utilizando o JaCoCo.

## Documentação no Postman

A documentação da API pode ser encontrada no Postman através do seguinte link: [Documentação Postman](https://documenter.getpostman.com/view/37022898/2sAYQamBFK). Esta documentação inclui todos os endpoints disponíveis, métodos HTTP suportados, parâmetros necessários e exemplos de respostas.

## Vídeos Explicativos

### Rodando o Projeto em Localhost

[![Rodando em Localhost](https://img.youtube.com/vi/cTVy5op7oSU/0.jpg)](https://www.youtube.com/watch?v=cTVy5op7oSU)

### Features do Projeto

[![Features do Projeto](https://img.youtube.com/vi/DfWLC354qDI/0.jpg)](https://www.youtube.com/watch?v=DfWLC354qDI)

### Processo de Deploy

[![Processo de Deploy](https://img.youtube.com/vi/9o80Xi5_MjY/0.jpg)](https://www.youtube.com/watch?v=9o80Xi5_MjY)

### By Gabriel Soares Maciel
