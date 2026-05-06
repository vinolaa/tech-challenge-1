-- Inserção de usuários de teste para a coleção Postman
-- Senha: Senha@123 (BCrypt hash)

-- Inserindo um Dono
INSERT INTO users (
    tipo, id, name, email, login, password, created_at, created_by, 
    end_logradouro, end_numero, end_bairro, end_cidade, end_uf, end_cep, cnpj
) VALUES (
    'DONO', 
    'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 
    'Admin Sistema', 
    'admin@teste.com', 
    'admin', 
    '$2a$10$R0HOdqSlM6ZM410jtT.QRuDgUs9QraRAab0cUdxo62XiyMU5wH4lO',
    CURRENT_TIMESTAMP, 
    'system',
    'Rua Admin', '1', 'Centro', 'Sâo Paulo', 'SP', '01001000',
    '12345678000199'
);

-- Inserindo um Cliente
INSERT INTO users (
    tipo, id, name, email, login, password, created_at, created_by, 
    end_logradouro, end_numero, end_bairro, end_cidade, end_uf, end_cep, cpf
) VALUES (
    'CLIENTE', 
    'b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a22', 
    'João Cliente', 
    'joao@teste.com', 
    'joaosilva', 
    '$2a$10$R0HOdqSlM6ZM410jtT.QRuDgUs9QraRAab0cUdxo62XiyMU5wH4lO',
    CURRENT_TIMESTAMP, 
    'system',
    'Rua Cliente', '10', 'Bairro Novo', 'Rio de Janeiro', 'RJ', '20001000',
    '12345678901'
);
