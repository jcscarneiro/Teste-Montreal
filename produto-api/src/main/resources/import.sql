Insert into produto (id,nome,descricao) values (1,'Pacote Office','Pacote Office');
Insert into produto (id,nome,descricao) values (2,'Navegador','Navegador');

Insert into produto (id,nome,descricao,id_produto_pai) values (3,'Word','Editor de Textos', 1);
Insert into produto (id,nome,descricao,id_produto_pai) values (4,'Power Point','Criação de Apresentações', 1);
Insert into produto (id,nome,descricao,id_produto_pai) values (5,'Excel','Editor de Planilhas', 1);
Insert into produto (id,nome,descricao,id_produto_pai) values (6,'One Note','Criação de Anotações', 1);
Insert into produto (id,nome,descricao,id_produto_pai) values (7,'Access','Banco de Dados', 1);


Insert into imagem (id,tipo,id_produto) values (1,'doc',3);
Insert into imagem (id,tipo,id_produto) values (2,'ppt',4);
Insert into imagem (id,tipo,id_produto) values (3,'xls',5);
Insert into imagem (id,tipo,id_produto) values (4,'one',6);