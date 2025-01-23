# Spring Boot Base Project

Este é um projeto base para aplicações Spring Boot, configurado com PostgreSQL, Swagger para documentação da API e integração contínua com GitHub Actions.

## Comandos

- `npm run build`: Executa o comando `mvn clean package -DskipTests` para compilar o projeto sem executar os testes.
- `npm run install:java:dependencies`: Executa o comando `mvn install -DskipTests` para instalar as dependências Java sem executar os testes.
- `npm run start:dev`: Executa o comando `mvn spring-boot:run` para iniciar a aplicação em modo de desenvolvimento.
- `npm run db:migrate`: Executa o comando `mvn flyway:migrate` para aplicar as migrações do banco de dados.
- `npm run lint:test`: Executa o comando `mvn spotless:check` para verificar o estilo do código.
- `npm run lint:fix`: Executa o comando `mvn spotless:apply` para corrigir o estilo do código.
- `npm run test`: Executa o comando `mvn test` para rodar os testes.
- `npm run test:coverage`: Executa o comando `mvn test jacoco:report` para rodar os testes e gerar um relatório de cobertura.

## Documentação da API

A documentação da API está disponível no Swagger ao rodar o projeto. Acesse [Swagger UI](http://localhost:8080/docs) para visualizar todos os endpoints disponíveis, métodos HTTP suportados, parâmetros necessários e exemplos de respostas.

## Passo a Passo para Rodar o Projeto

1. **Instale a extensão Dev Containers no VS Code**:
   - Abra o VS Code e vá para a aba de extensões.
   - Procure por "Dev Containers" e instale a extensão.

2. **Rode o Docker Compose**:
   - No terminal, execute o comando:
     ```sh
     docker-compose -f docker-compose-dev.yml up -d
     ```

3. **Entre no Container usando a Extensão Dev Containers**:
   - No VS Code, abra o comando `Dev Containers: Attach to Running Container...` e selecione o container do projeto.

4. **Crie o Banco de Dados do Projeto**:
   - Entre na sua IDE de banco de dados e crie o banco do projeto

5. **Rode o Comando de Migração**:
   - Ainda no terminal dentro do container, execute o comando de migração:
     ```sh
     npm run db:migrate
     ```

6. **Startar o Projeto**:
   - No terminal dentro do container, execute o comando para iniciar o projeto:
     ```sh
     npm run start:dev
     ```

7. **Acesse o Projeto**:
   - O projeto estará disponível na porta 8080. Acesse [http://localhost:8080](http://localhost:8080) no seu navegador.
