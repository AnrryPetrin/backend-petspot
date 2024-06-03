# PetSpot API

PetSpot é uma API RESTful desenvolvida usando Spring Boot para o cadastro e gerenciamento de pets.

## Endpoints

### Registro de Pets

#### Registrar um novo Pet
- **URL:** `/petspot/pet-register/{id}`
- **Método:** `POST`
- **Descrição:** Registra um novo pet associado a um dono específico.
- **Parâmetros:**
  - `id`: ID do dono do pet.
- **Corpo da Requisição:**
  ```json
  {
    "nome": "string",
    "especie": "string",
    "genero": "string",
    "raca": "string",
    "castrado": "boolean",
    "comportamento": "string",
    "porte": "string",
    "vacinado": "boolean",
    "dataNascimento": "date"
  }
  ```
- **Resposta de Sucesso:** `201 Created`
- **Resposta de Erro:** `409 Conflict` se um pet duplicado já existir.

### Consultas de Pets

#### Listar Todos os Pets de um Dono
- **URL:** `/petspot/meuspets/{ownerId}`
- **Método:** `GET`
- **Descrição:** Retorna todos os pets associados a um dono específico.
- **Parâmetros:**
  - `ownerId`: ID do dono.
- **Resposta de Sucesso:** `200 OK`
- **Resposta de Erro:** `404 Not Found` se o dono não for encontrado.

#### Buscar Pets por Nome
- **URL:** `/petspot/meuspets/{ownerId}/buscarpet`
- **Método:** `GET`
- **Descrição:** Busca pets por nome parcial para um dono específico.
- **Parâmetros:**
  - `ownerId`: ID do dono.
  - `petName`: Nome parcial do pet.
- **Resposta de Sucesso:** `200 OK`
- **Resposta de Erro:** `404 Not Found` se nenhum pet for encontrado.

### Atualizações de Pets

#### Renomear um Pet
- **URL:** `/petspot/meuspets/{ownerId}/renomear/{petId}`
- **Método:** `PATCH`
- **Descrição:** Atualiza o nome de um pet específico.
- **Parâmetros:**
  - `ownerId`: ID do dono.
  - `petId`: ID do pet.
- **Corpo da Requisição:**
  ```json
  {
    "petName": "string"
  }
  ```
- **Resposta de Sucesso:** `200 OK`
- **Resposta de Erro:** `403 Forbidden` se o pet não pertencer ao dono.

#### Atualizar Peso de um Pet
- **URL:** `/petspot/meuspets/{ownerId}/atualizarpeso/{petId}`
- **Método:** `PATCH`
- **Descrição:** Atualiza o peso de um pet específico.
- **Parâmetros:**
  - `ownerId`: ID do dono.
  - `petId`: ID do pet.
- **Corpo da Requisição:**
  ```json
  {
    "peso": "float"
  }
  ```
- **Resposta de Sucesso:** `200 OK`
- **Resposta de Erro:** `403 Forbidden` se o pet não pertencer ao dono.

## Instalação e Execução

### Pré-requisitos
- Java 17 ou superior
- Maven 3.6.3 ou superior

### Instalação
1. Clone o repositório:
   ```bash
   git clone https://github.com/AnrryPetrin/backend_petspot.git
   ```
2. Navegue até o diretório do projeto:
   ```bash
   cd petspot
   ```

### Compilação e Execução
1. Compile o projeto:
   ```bash
   mvn clean install
   ```
2. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```

## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.

## Licença
Este projeto está licenciado sob os termos da licença MIT. Veja o arquivo `LICENSE` para mais detalhes.
