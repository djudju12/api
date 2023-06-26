# Comida de rua API

## Sobre
___

Ao iniciar o PFS decidimos por utilizar a ferramenta Spring Boot para o desenvolvimento da API. 
Essa escolha se da pelos seguintes fatores: 


1. A ferramenta já era objeto de estudo dos integrantes;
2. A ferramente é uma das principais do mercado quando o assunto é webserviços;
3. Facilidade na implementação comparado a outros frameworks e bibliotecas.

Para a persistência dos dados utilizamos o banco de dados fornecido pela plataforma [Supabase](https://supabase.com/).
Um dos principais motivos para a escolha do Supabase foi a facilidade na configuração e a possibilidade de hostear um banco sem adicionar um método de pagamento.
Dessa forma, conseguimos desenvolver de forma gratuita com um minímo de configuração, o que nos possibilitária migrar para outra plataforma
ou banco sem muitos problemas. 

<br>

## Banco de Dados
___
Como explicado anteriormente, o banco esta hospedado no Supabase e consiste em uma instância de Postgresql. 
Em seguida está o Schema das tabelas em UML:

![schema](src%2Fmain%2Fresources%2Fmedia%2Fdbschema.jfif "db schema")

Optamos por três entidades principais, Produtos, Pedidos e Item Pedido e três secundárias, Tipo de Pagamento, Status do pedido e categorias. 
No seguinte repositório consta o script usado para criar as tabelas, além de algumas anotações que serão usadas para futuros aperfeiçoamentos: [dbschema](https://github.com/djudju12/dbschema).


<br>

## Endpoints
___

Os seguintes endpoints foram definidos a partir de reuniões feitas com o grupo responsável pelo frontend.
A documentação dos endpoints também foi feita através da ferramente Swagger-ui. O link para a documentação será enviado junto do trabalho.

### Produto

| Method | Endpoint                  | Descricao                  |
|--------|---------------------------|----------------------------|
| GET    | /api/produtos             | Lista todos os produtos    |
| GET    | /api/produtos/categorias  | Lista todas as categorias  |
| GET    | /api/produtos/{produtoId} | Retorna um produto produto |
| POST   | /api/produtos             | Cria um produto            |
| PUT    | /api/produtos             | Edita um produto           |
| DELETE | /api/produtos/{produtoId} | Deleta um produto          |

### Pedidos

| Method | Endpoint                     | Descricao                                       |
|--------|------------------------------|-------------------------------------------------|
| GET    | /api/pedidos                 | Lista todos os pedidos sem a lista de produtos  |
| GET    | /api/pedidos/{pedidoId}      | Lista um pedido com seus produtos               |
| GET    | /api/pedidos/detalhes        | Lista todos os pedidos com detalhe dos produtos |
| GET    | /api/pedidos/tiposPagamentos | Lista todas as formas de pagamento disponiveis  |
| POST   | /api/pedidos                 | Adiciona o produto ao pedido                    |
| DELETE | /api/pedidos/{pedidoId}      | Deleta um pedido                                |
