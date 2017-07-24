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

import com.testemontreal.produto.business.ImagemBusiness;
import com.testemontreal.produto.domain.Imagem;
import com.testemontreal.produto.repository.ImagemRepository;

import flexjson.JSONSerializer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/imagem")
@Api(value = "imagem")
@Component
public class ImagemEndpoint {
	
	@Autowired
	private ImagemRepository imagemRepository; 
	
	@Autowired
	private ImagemBusiness imagemBusiness; 
	
	@GET
	@ApiOperation(value = "Listar")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
	public Response list() {
		
		List<Imagem> list = imagemRepository.findAll();
		String json = null;
		
		if(!list.isEmpty()){
			JSONSerializer serializer = new JSONSerializer();
			serializer.exclude("*.class");

			serializer.exclude("produto");

			json = serializer.serialize(list);
		}
		
		return Response.status(Status.OK.getStatusCode()).entity(json != null ? json : list).build();		
	}
	
	@POST
	@Path("/save")
	@ApiOperation(value = "Salvar/Atualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
	public Response save(@ApiParam(value = "objeto json para salvar o imagem", required = true) @RequestBody Imagem imagem) {

		imagemBusiness.save(imagem);
		
		return Response.status(Status.OK.getStatusCode()).build();
	}
	
	@POST
	@ApiOperation(value = "Excluir")
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
	public Response delete(@ApiParam(value = "ID do objeto para excluir o imagem. Formato: {id: number}", required = true) @RequestBody Imagem imagem) {	
		
		imagemBusiness.delete(imagem.getId());
		
		return Response.status(Status.OK.getStatusCode()).build();
	}
	
	@GET
	@Transactional
	@ApiOperation(value = "Pesquisar Imagens a partir do Id do Produto")
	@Path("/searchImagemByProduct/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
	public Response searchImagemByProduct(@ApiParam(value = "id do produto", required = false) @PathParam("id") Long idProduto) {
		
		Collection<Imagem> list = imagemRepository.findByIdProduto(idProduto);
		
		String json = null;
		
		if(!list.isEmpty()){
			JSONSerializer serializer = new JSONSerializer();
			serializer.exclude("*.class");

			serializer.exclude("produto");

			json = serializer.serialize(list);
		}
		
		return Response.status(Status.OK.getStatusCode()).entity(json != null ? json : list).build();
		
	}
}
