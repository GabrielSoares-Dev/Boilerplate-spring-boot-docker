# Projeto de Teste Técnico

Este é um projeto criado para um teste técnico. Abaixo estão os detalhes sobre os comandos disponíveis, documentação no Postman e vídeos explicativos sobre como rodar o projeto em localhost, suas features e o processo de deploy.

## Comandos Disponíveis

- **build**: `mvn package shade:shade -DskipTests -Pprod`
  - Este comando empacota o projeto, criando um arquivo JAR, sem executar os testes, e utilizando o perfil de produção.

- **deploy**: `serverless deploy`
  - Este comando realiza o deploy do projeto utilizando o framework Serverless.

- **install:java:dependencies**: `mvn install -DskipTests`
  - Este comando instala todas as dependências do projeto, sem executar os testes.

- **start:dev**: `mvn spring-boot:run`
  - Este comando inicia o projeto em modo de desenvolvimento utilizando o Spring Boot.

- **db:migrate**: `mvn flyway:migrate`
  - Este comando aplica as migrações de banco de dados utilizando o Flyway.

- **lint:test**: `mvn spotless:check`
  - Este comando verifica se o código está formatado corretamente utilizando o Spotless.

- **lint:fix**: `mvn spotless:apply`
  - Este comando formata o código automaticamente utilizando o Spotless.

- **test**: `mvn test`
  - Este comando executa os testes do projeto.

- **test:coverage**: `mvn test jacoco:report`
  - Este comando executa os testes e gera um relatório de cobertura de testes utilizando o JaCoCo.

## Documentação no Postman

A documentação da API pode ser encontrada no Postman através do seguinte link: [Documentação Postman](https://www.postman.com/collections/your-collection-link). Esta documentação inclui todos os endpoints disponíveis, métodos HTTP suportados, parâmetros necessários e exemplos de respostas.

## Vídeos Explicativos

### Rodando o Projeto em Localhost

[![Rodando em Localhost](https://img.youtube.com/vi/cTVy5op7oSU/0.jpg)](https://www.youtube.com/watch?v=cTVy5op7oSU)

### Features do Projeto

[![Features do Projeto](https://img.youtube.com/vi/DfWLC354qDI/0.jpg)](https://www.youtube.com/watch?v=DfWLC354qDI)

### Processo de Deploy

[![Processo de Deploy](https://img.youtube.com/vi/9o80Xi5_MjY/0.jpg)](https://www.youtube.com/watch?v=9o80Xi5_MjY)

## Contribuição

Sinta-se à vontade para contribuir com este projeto. Para isso, faça um fork do repositório, crie uma branch para sua feature ou correção, e envie um pull request.

## Licença

Este projeto está licenciado sob a Licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.