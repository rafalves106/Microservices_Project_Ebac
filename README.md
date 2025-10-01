# Projeto de Microserviços: Cliente & Produto

Este é um projeto de microserviços simples, mas robusto, desenvolvido em **Java com Spring Boot (versão 3.x)**, persistência com **MongoDB** e orquestração via **Docker Compose**.  
O projeto demonstra uma arquitetura moderna com a implementação do **Spring Cloud Config Server** para gerenciamento centralizado de configurações.

O tema é inspirado no universo **Pokémon**, onde **Clientes são Treinadores** e **Produtos são Pokémons e Itens**, tornando o aprendizado mais divertido e memorável.

---

## 🌟 Arquitetura da Solução

A aplicação é dividida em três serviços de aplicação e duas instâncias de banco de dados isoladas:

| Serviço            | Porta Externa | Função                              | Banco de Dados        |
|--------------------|---------------|-------------------------------------|-----------------------|
| **clientes-service** | 8083          | Gerencia dados dos Treinadores      | MongoDB (clientes_db) |
| **produtos-service** | 8082          | Gerencia dados dos Pokémons e Itens | MongoDB (produtos_db) |
| **config-server**    | 8888          | Servidor de configuração            | N/A                   |
| **MongoDB (2x)**     | 27017/27018   | Persistência de dados isolada       | N/A                   |

---

## 🛠 Tecnologias Utilizadas

- **Linguagem:** Java (JDK 17+)
- **Framework:** Spring Boot 3.x
- **Microserviços:** Spring Cloud Config
- **Banco de Dados:** MongoDB
- **Orquestração:** Docker e Docker Compose
- **Documentação API:** Springdoc OpenAPI (Swagger UI)

---

## ⚙️ Pré-requisitos

Para rodar este projeto, você precisará ter as seguintes ferramentas instaladas:

- Java **JDK 17** ou superior
- Apache **Maven**
- **Docker** e **Docker Compose**
- Um cliente **Git**

---

## 🚀 Como Executar o Projeto

> ⚠️ **Importante:** Este projeto usa um repositório Git privado e separado (`config-repo`) para gerenciar as configurações.

### 1. Preparação do Repositório de Configurações

Crie a pasta `config-repo` na raiz do projeto:

```bash
mkdir config-repo
cd config-repo
```

Dentro da pasta `config-repo`, crie os seguintes arquivos de configuração:

- `clientes-service.yml`
```bash
server:
  port: 8083
spring:
  data:
    mongodb:
      host: mongodb_clientes
      port: 27017
      database: clientes_db
springdoc:
  swagger-ui:
    server-url: http://localhost:8083
```

- `produtos-service.yml`
```bash
server:
  port: 8082
spring:
  data:
    mongodb:
      host: mongodb_produtos
      port: 27017
      database: produtos_db
springdoc:
  swagger-ui:
    server-url: http://localhost:8082
```

### 2. Compilação das Aplicações

Antes de iniciar o Docker, compile os projetos para gerar os arquivos .jar mais recentes:

```bash
cd clientes-service
mvn clean package
cd ..

cd produtos-service
mvn clean package
cd ..
```

### 3. Iniciar os Serviços com Docker Compose

Na raiz do projeto, execute o seguinte comando para iniciar todos os serviços:

```bash
docker-compose up --build
```

> Nota: O comando `--build` garante que as imagens Docker sejam reconstruídas com as últimas alterações.

## 🧪 Como Testar a API

### Swagger UI
Acesse as URLs abaixo no navegador para ver a documentação interativa da API e testar todos os endpoints (POST, GET, PUT, DELETE):

>- Clientes Service (Treinadores): [http://localhost:8083/swagger-ui.html](http://localhost:8083/swagger-ui.html)
>- Produtos Service (Pokémons e Itens): [http://localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html)

### Exemplo de Teste do Endpoint (POST)
Use o Insomnia ou Postman para testar o endpoint de criação de um novo Treinador:

#### Cliente (Porta 8083):

- Método: POST
- URL: http://localhost:8083/clientes
- Body (JSON):

```json
{
  "nome": "Ash Ketchum",
  "regiaoOrigem": "Kanto",
  "quantidadeInsignias": 8,
  "dataRegistro": "1997-04-01"
}
```

#### Produto (Porta 8082):

- Método: POST
- URL: http://localhost:8082/produtos
- Body (JSON):

```json
{
  "nome": "Master Ball",
  "descricao": "A melhor Pokébola, garante captura sem falha.",
  "preco": 99999.99
}
```

## Autor
- **Nome:** Rafael (Falves) Alves

## Licença
Este projeto está licenciado sob a **Licença MIT**.