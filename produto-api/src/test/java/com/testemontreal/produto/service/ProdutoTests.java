package com.testemontreal.produto.service;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.testemontreal.produto.domain.Produto;
import com.testemontreal.produto.service.ProdutoEndpoint;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:application-scratch.properties")
public class ProdutoTests extends TestCase {
	
	@Autowired
	ProdutoEndpoint produtoEndPoint;
		
	@Test
	@Rollback
	public void testCreateProduct() throws JSONException {
		Produto produto = new Produto(7L, "Outlook", "Mensagens Eletronicas", 1L);
		Response response = produtoEndPoint.save(produto);
		Assert.assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	@Rollback
	public void testReadProduct() throws JSONException {
		Response response = produtoEndPoint.list();
		Assert.assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	@Rollback
	public void testUpdateProduct() throws JSONException {
		Produto produto = new Produto(7L, "Outlook", "E-mails", 1L);
		Response response = produtoEndPoint.save(produto);
		Assert.assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	@Rollback
	public void testDeleteProduct() throws JSONException {
		Produto produto = new Produto(7L);
		Response response = produtoEndPoint.delete(produto);
		Assert.assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void testListAllWithoutRelationships() throws JSONException {
		Response response = produtoEndPoint.listAllWithoutRelationships();
		JSONArray jsonArray = new JSONArray(response.getEntity().toString());
		Assert.assertEquals(jsonArray.length(), 7);
	}
	
	@Test
	public void testListAllIncludingRelationship() throws JSONException {
		Response response = produtoEndPoint.listAllIncludingRelationship();
		JSONArray jsonArray = new JSONArray(response.getEntity().toString());
		Assert.assertEquals(jsonArray.length(), 7);
	}
	
	@Test
	public void testFindByIdWithoutRelationships() throws JSONException {
		Response response = produtoEndPoint.findByIdWithoutRelationships(3L);
		Assert.assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void testFindByIdIncludingRelationship() throws JSONException {
		Response response = produtoEndPoint.findByIdIncludingRelationship(2L);
		Assert.assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void testSearchChildByParent() throws JSONException {
		Response response = produtoEndPoint.searchChildByParent(1L);
		JSONArray jsonArray = new JSONArray(response.getEntity().toString());
		Assert.assertEquals(jsonArray.length(), 5);
	}
}
