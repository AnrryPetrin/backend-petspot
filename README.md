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
![Screenshot from 2024-06-03 08-42-45](https://github.com/AnrryPetrin/backend_petspot/assets/108158140/2fe31f04-fe49-4d60-9cbc-121094191b08)

### Consultas de Pets

#### Listar Todos os Pets de um Dono
- **URL:** `/petspot/meuspets/{ownerId}`
- **Método:** `GET`
- **Descrição:** Retorna todos os pets associados a um dono específico.
- **Parâmetros:**
  - `ownerId`: ID do dono.
- **Resposta de Sucesso:** `200 OK`
- **Resposta de Erro:** `404 Not Found` se o dono não for encontrado.
![Screenshot from 2024-06-03 08-42-56](https://github.com/AnrryPetrin/backend_petspot/assets/108158140/7af34913-bb97-4920-a55a-119572e2aede)

#### Buscar Pets por Nome
- **URL:** `/petspot/meuspets/{ownerId}/buscarpet`
- **Método:** `GET`
- **Descrição:** Busca pets por nome parcial para um dono específico.
- **Parâmetros:**
  - `ownerId`: ID do dono.
  - `petName`: Nome parcial do pet.
- **Resposta de Sucesso:** `200 OK`
- **Resposta de Erro:** `404 Not Found` se nenhum pet for encontrado.
![Screenshot from 2024-06-03 08-43-02](https://github.com/AnrryPetrin/backend_petspot/assets/108158140/0ffa996a-e743-4084-bb53-6dadfb09ab71)

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
![Screenshot from 2024-06-03 08-43-07](https://github.com/AnrryPetrin/backend_petspot/assets/108158140/56665322-be72-437a-bb2a-ffb318391a76)

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
![Screenshot from 2024-06-03 08-43-12](https://github.com/AnrryPetrin/backend_petspot/assets/108158140/e31cf346-4301-44e5-b2e1-b71f8d4b9c69)

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
   cd backend_petspot
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
