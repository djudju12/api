## Endpoints

### Produto

| Method | Endpoint                                      | Descricao                  |
| ------ | --------------------------------------------- | -------------------------- |
| GET    | /api/produtos                                 | Lista todos os produtos    |
| GET    | /api/produtos/categorias                      | Lista todas as categorias  |
| GET    | /api/produtos/{produtoId}                     | Retorna um produto produto |
| POST   | /api/produtos                                 | Cria um produto            |
| POST   | /api/produtos/categorias                      | cria uma categoria         |
| PUT    | /api/produtos                                 | Edita um produto           |
| DELETE | /api/produtos/{produtoId}                     | Deleta um produto          |
| DELETE | /api/produtos/categorias/{categoriaDescricao} | deleta uma categoria       |



### Pedidos

| Method | Endpoint                                              | Descricao                                           |
| ------ | ----------------------------------------------------- | --------------------------------------------------- |
| GET    | /api/pedidos                                          | Lista todos os pedidos sem detalhe dos itens        |
| GET    | /api/pedidos/{pedidoId}                               | Retorna um pedido sem detalhe dos itens             |
| GET    | /api/pedidos/itens                                    | Lista todos os pedidos com deltahes dos itens       |
| GET    | /api/pedidos/{pedidoId}/itens                         | Retorna um pedido com deltahes dos itens            |
| GET    | /api/pedidos/status                                   | Lista os status possiveis de um pedido              |
| GET    | /api/pedidos/tiposPagamentos                          | Lista os tipos de pagamentos possiveis de um pedido |
| PUT    | /api/pedidos                                          | Edita o pedido                                      |
| POST   | /api/pedidos                                          | Cria um novo pedido                                 |
| POST   | /api/pedidos/{pedidoId}/itens/{produtoId}             | Adiciona o produto ao pedido                        |
| POST   | /api/pedidos/status                                   | Cria um novo status                                 |
| POST   | /api/pedidos/tiposPagamentos                          | cria um novo tipo de pagamento                      |
| DELETE | /api/pedidos/{pedidoId}/itens/{produtoId}             | Delete todos os produto do pedido                   |
| DELETE | /api/pedidos/status/{descricaoStatus}                 | Deleta o status                                     |
| DELETE | /api/pedidos/tiposPagamentos/{descricaoTipoPagamento} | Deleta o tipo pagamento                             |
