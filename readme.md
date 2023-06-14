## Endpoints

### Produto

| Method | Endpoint                  | Descricao                  |
| ------ | ------------------------- | -------------------------- |
| GET    | /api/produtos             | Lista todos os produtos    |
| GET    | /api/produtos/categorias  | Lista todas as categorias  |
| GET    | /api/produtos/{produtoId} | Retorna um produto produto |
| POST   | /api/produtos             | Cria um produto            |
| PUT    | /api/produtos             | Edita um produto           |
| DELETE | /api/produtos/{produtoId} | Deleta um produto          |



### Pedidos

| Method | Endpoint                | Descricao                                    |
| ------ | ----------------------- | -------------------------------------------- |
| GET    | /api/vendas             | Lista todos os pedidos sem detalhe dos itens |
| POST   | /api/vendas/{produtoId} | Adiciona o produto ao pedido                 |
| DELETE | /api/vendas/{pedidoId}  | Delete um pedido                             |
