== Produto API == 
1.	Compilar a aplicação:
Importe e execute o arquivo produto-api-compile.launch, presente no projeto produto-api.
2.	Executar a aplicação:
Importe e execute o arquivo produto-api-start.launch, presente no projeto produto-api.
3.	Empacotar a Aplicação:
Importe e execute o arquivo produto-api-package.launch, presente no projeto produto-api.
4.	Executar testes automatizados:
Importe e execute os arquivos ProdutoTests.launch e ImagemTests.launch, presente no projeto produto-api.

Exemplos de chamadas dos métodos da aplicação:

//Lista todos produtos
http://localhost:8080/produtoapi/produto

// Recupera todos os Produtos excluindo os relacionamentos
http://localhost:8080/produtoapi/produto/listAllWithoutRelationships

// Recupera todos os Produtos incluindo um relacionamento específico
http://localhost:8080/produtoapi/produto/listAllIncludingRelationship

// Recupera todos os Produtos excluindo os relacionamentos utilizando um id de produto específico
http://localhost:8080/produtoapi/produto/findByIdWithoutRelationships/3

// Recupera todos os Produtos incluindo um relacionamento específico utilizando um id de produto específico
http://localhost:8080/produtoapi/produto/findByIdIncludingRelationship/2

// Recupera a coleção de produtos filhos por um id de produto específico
http://localhost:8080/produtoapi/produto/searchChildByParent/1

//Lista todas Imagens
http://localhost:8080/produtoapi/imagem

// Recupera a coleção de Imagens para um id de produto específico
http://localhost:8080/produtoapi/imagem/searchImagemByProduct/3

Obs.: Os métodos foram documentados na ferramenta Swagger e a mesma pode ser acessada através da URL abaixo:
http://localhost:8080/swagger/index.html
