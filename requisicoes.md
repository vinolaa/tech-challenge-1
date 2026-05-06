# Exemplos de Requisições (curl)

Este arquivo contém exemplos de comandos `curl` para interagir com a API do projeto TC1.

## Variáveis
```bash
BASE_URL="http://localhost:8080/api/v1"
# O token JWT deve ser obtido através da rota de login e substituído abaixo
TOKEN="seu_token_jwt_aqui"
```

---

## Autenticação (AuthController)

### Login
Realiza a autenticação e retorna o token JWT.
```bash
curl -X POST "$BASE_URL/auth/login" \
     -H "Content-Type: application/json" \
     -d '{
       "login": "joaosilva",
       "password": "Senha@123"
     }'
```

---

## Usuários (UserController)

### Listar todos os usuários
```bash
curl -X GET "$BASE_URL/user" \
     -H "Authorization: Bearer $TOKEN"
```

### Buscar usuários pelo nome
```bash
curl -X GET "$BASE_URL/user/search?name=Joao" \
     -H "Authorization: Bearer $TOKEN"
```

### Criar um novo Dono
```bash
curl -X POST "$BASE_URL/user/dono" \
     -H "Authorization: Bearer $TOKEN" \
     -H "Content-Type: application/json" \
     -d '{
       "name": "Dono Teste",
       "email": "dono@teste.com",
       "login": "donoteste",
       "password": "password123",
       "cnpj": "12345678000199",
       "endereco": {
         "logradouro": "Rua das Flores",
         "numero": "123",
         "bairro": "Centro",
         "cidade": "São Paulo",
         "uf": "SP",
         "cep": "01001000"
       }
     }'
```

### Criar um novo Cliente
```bash
curl -X POST "$BASE_URL/user/cliente" \
     -H "Authorization: Bearer $TOKEN" \
     -H "Content-Type: application/json" \
     -d '{
       "name": "Cliente Teste",
       "email": "cliente@teste.com",
       "login": "clienteteste",
       "password": "password123",
       "cpf": "12345678901",
       "endereco": {
         "logradouro": "Avenida Paulista",
         "numero": "1000",
         "bairro": "Bela Vista",
         "cidade": "São Paulo",
         "uf": "SP",
         "cep": "01310100"
       }
     }'
```

### Atualizar dados do usuário
Substitua `{id}` pelo UUID do usuário.
```bash
curl -X PUT "$BASE_URL/user/{id}" \
     -H "Authorization: Bearer $TOKEN" \
     -H "Content-Type: application/json" \
     -d '{
       "name": "Nome Atualizado",
       "email": "atualizado@email.com",
       "login": "loginatualizado",
       "endereco": {
         "logradouro": "Nova Rua",
         "numero": "456",
         "bairro": "Novo Bairro",
         "cidade": "Curitiba",
         "uf": "PR",
         "cep": "80000000"
       }
     }'
```

### Atualizar senha do usuário
Substitua `{id}` pelo UUID do usuário.
```bash
curl -X PATCH "$BASE_URL/user/{id}/password" \
     -H "Authorization: Bearer $TOKEN" \
     -H "Content-Type: application/json" \
     -d '{
       "currentPassword": "password123",
       "newPassword": "newpassword456",
       "confirmPassword": "newpassword456"
     }'
```

### Remover um usuário
Substitua `{id}` pelo UUID do usuário.
```bash
curl -X DELETE "$BASE_URL/user/{id}" \
     -H "Authorization: Bearer $TOKEN"
```
