## Endpoints

### Produto

| Method | Endpoint          | Descricao                  |
| ------ | ----------------- | -------------------------- |
| GET    | /api/produtos     | Lista todos os produtos    | -> Paginação
| GET    | /api/produtos/:id | Retorna um produto produto |
| POST   | /api/produtos     | Cria um produto            |
| PUT    | /api/produtos     | Edita um produto           |
| DELETE | /api/produtos/:id | Deleta um produto          |


### Categoria

| Method | Endpoint                  | Descricao                  |
| ------ | ------------------------- | -------------------------- |
| GET    | /api/categorias           | Lista todos os categorias  | -> Paginação
| GET    | /api/categorias/descricao | Retorna um produto produto |
| POST   | /api/categorias           | Cria um produto            |
| DELETE | /api/categorias/descricao | Deleta um produto          |




Fiquei na dúvida sobre quando usar o Spring Data Rest.
Devo usar os endpoints que ele cria automaticamente ou seria melhor
codar esses endpoits? 

Repositório e DAOs, eu escolho um desses padrões pra usar? Uso misturado?
Quando devo usar um ou outro? Eu nao compreendi bem a diferença entre eles.

Exceptions. Qual seria a melhor prática, criar uma classe mais generica 
tipo NotFound ou criar algo mais especifico tipo ProdutoNotFound e CategoriaNotFound?
