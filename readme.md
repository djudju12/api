## Endpoints

### Produto

| Method | Endpoint          | Descricao                  |
| ------ | ----------------- | -------------------------- |
| GET    | /api/produtos     | Lista todos os produtos    | -> Paginação |
| GET    | /api/produtos/:id | Retorna um produto produto |
| POST   | /api/produtos     | Cria um produto            |
| PUT    | /api/produtos     | Edita um produto           |
| DELETE | /api/produtos/:id | Deleta um produto          |


### categorias/statusPedidos/tiposPagamentos

| Method | Endpoint                  | Descricao                  |
| ------ | ------------------------- | -------------------------- |
| GET    | /api/categorias           | Lista todos os categorias  | -> Paginação |
| GET    | /api/categorias/descricao | Retorna um produto produto |
| POST   | /api/categorias           | Cria um produto            |
| DELETE | /api/categorias/descricao | Deleta um produto          |

### pedidos

| Method | Endpoint                   | Descricao                               |
| ------ | -------------------------- | --------------------------------------- |
| GET    | /api/pedidos               | Lista todos os pedidos                  | -> Paginação |
| GET    | /api/pedidos/:id           | Retorna um produto produto              |
| POST   | /api/pedidos               | Cria um produto                         |
| DELETE | /api/pedido                | Deleta um produto                       |
| GET    | /api/pedidos/:id/itens     | Retorna um produto produto e seus itens | -> Paginação |
| POST   | /api/pedidos/:id/itens     | Adiciona um novo produto ao pedido      | -> Paginação |
| Delete | /api/pedidos/:id/itens/:id | Deleta um item produto ao pedido        | -> Paginação |





Fiquei na dúvida sobre quando usar o Spring Data Rest.
Devo usar os endpoints que ele cria automaticamente ou seria melhor
codar esses endpoits? 

Repositório e DAOs, eu escolho um desses padrões pra usar? Uso misturado?
Quando devo usar um ou outro? Eu nao compreendi bem a diferença entre eles.

Exceptions. Qual seria a melhor prática, criar uma classe mais generica 
tipo NotFound ou criar algo mais especifico tipo ProdutoNotFound e CategoriaNotFound?
