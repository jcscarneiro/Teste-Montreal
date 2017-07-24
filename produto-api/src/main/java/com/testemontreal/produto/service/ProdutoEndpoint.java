package com.testemontreal.produto.service;


import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.testemontreal.produto.business.ProdutoBusiness;
import com.testemontreal.produto.domain.Produto;
import com.testemontreal.produto.repository.ProdutoRepository;

import flexjson.JSONSerializer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/produto")
@Api(value = "produto")
@Component
public class ProdutoEndpoint {
	
	@Autowired
	private ProdutoRepository produtoRepository; 
	
	@Autowired
	private ProdutoBusiness produtoBusiness; 
	
	@GET
	@Transactional
	@ApiOperation(value = "Listar")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
	public Response list() {
		
		List<Produto> list = produtoRepository.findAll();

		JSONSerializer serializer = new JSONSerializer();
		String json =  serializer.serialize(list);
		
		return Response.status(Status.OK.getStatusCode()).entity(json != null ? json : list).build();
		
	}
	
	@POST
	@Path("/save")
	@ApiOperation(value = "Salvar/Atualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
	public Response save(@ApiParam(value = "objeto json para salvar o produto", required = true) @RequestBody Produto produto) {

		produtoBusiness.save(produto);
		
		return Response.status(Status.OK.getStatusCode()).build();
	}
	
	@POST
	@ApiOperation(value = "Excluir")
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
	public Response delete(@ApiParam(value = "ID do objeto para excluir o produto. Formato: {id: number}", required = true) @RequestBody Produto produto) {	
		
		produtoBusiness.delete(produto.getId());
		
		return Response.status(Status.OK.getStatusCode()).build();
	}
	
	@GET
	@Transactional
	@ApiOperation(value = "Listar todos sem os relacionamentos")
	@Path("/listAllWithoutRelationships")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
	public Response listAllWithoutRelationships() {
		
		List<Produto> list = produtoRepository.findAll();
		
		String json = null;
		
		if(!list.isEmpty()){
			JSONSerializer serializer = new JSONSerializer();
			serializer.exclude("*.class");

			serializer.exclude("imagens");
			serializer.exclude("produtosFilhos");
			serializer.exclude("produtoPai");
			serializer.exclude("imagem");

			json = serializer.serialize(list);
		}
		
		return Response.status(Status.OK.getStatusCode()).entity(json != null ? json : list).build();
		
	}
	
	@GET
	@Transactional
	@ApiOperation(value = "Listar todos com um relacionamento")
	@Path("/listAllIncludingRelationship")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
	public Response listAllIncludingRelationship() {
		
		List<Produto> list = produtoRepository.findAll();
		
		String json = null;
		
		if(!list.isEmpty()){
			JSONSerializer serializer = new JSONSerializer();
			serializer.exclude("*.class");

			serializer.exclude("imagens");
			serializer.exclude("produtosFilhos");
			serializer.exclude("imagem");

			serializer.include("produtoPai");

			json = serializer.serialize(list);
		}
		
		return Response.status(Status.OK.getStatusCode()).entity(json != null ? json : list).build();
		
	}
	
	@GET 
	@Transactional
	@Path("/findByIdWithoutRelationships/{id}")
	@ApiOperation(value = "Buscar produto sem os relacionamentos")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "Not Found") })
	public Response findByIdWithoutRelationships(@ApiParam(value = "id do produto", required = false) @PathParam("id") Long id) {
		
		Produto produto = produtoRepository.findOne(id);
		
		String json = null;
		int responseStatus = Status.NOT_FOUND.getStatusCode();

		if(produto != null) {
			JSONSerializer serializer = new JSONSerializer();
			serializer.exclude("*.class");

			serializer.exclude("imagens");
			serializer.exclude("produtosFilhos");
			serializer.exclude("produtoPai");
			serializer.exclude("imagem");

			json = serializer.serialize(produto);
			responseStatus = Status.OK.getStatusCode();
		} 

		return produto != null ? Response.status(responseStatus).entity(json).build() : Response.status(responseStatus).entity(produto).build();
	}
	
	@GET 
	@Transactional
	@Path("/findByIdIncludingRelationship/{id}")
	@ApiOperation(value = "Buscar produto com relacionamento")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "Not Found") })
	public Response findByIdIncludingRelationship(@ApiParam(value = "id do produto", required = false) @PathParam("id") Long id) {
		
		Produto produto = produtoRepository.findOne(id);
		
		String json = null;
		int responseStatus = Status.NOT_FOUND.getStatusCode();

		if(produto != null) {
			JSONSerializer serializer = new JSONSerializer();
			serializer.exclude("*.class");

			serializer.exclude("imagens");
			serializer.exclude("produtosFilhos");
			serializer.exclude("imagem");

			serializer.include("produtoPai");

			json = serializer.serialize(produto);
			responseStatus = Status.OK.getStatusCode();
		} 

		return produto != null ? Response.status(responseStatus).entity(json).build() : Response.status(responseStatus).entity(produto).build();
	}
	
	@GET
	@Transactional
	@ApiOperation(value = "Pesquisar Produtos Filhos a partir do Id do Produto Pai")
	@Path("/searchChildByParent/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
	public Response searchChildByParent(@ApiParam(value = "id do produto Pai", required = false) @PathParam("id") Long idProdutoPai) {
		
		Collection<Produto> list = produtoRepository.findByIdProdutoPai(idProdutoPai);
		
		String json = null;
		
		if(!list.isEmpty()){
			JSONSerializer serializer = new JSONSerializer();
			serializer.exclude("*.class");

			serializer.exclude("imagens");
			serializer.exclude("produtosFilhos");
			serializer.exclude("imagem");
			serializer.exclude("produtoPai");

			json = serializer.serialize(list);
		}
		
		return Response.status(Status.OK.getStatusCode()).entity(json != null ? json : list).build();
		
	}
}
