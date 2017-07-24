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

import com.testemontreal.produto.domain.Imagem;
import com.testemontreal.produto.service.ImagemEndpoint;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:application-scratch.properties")
public class ImagemTests extends TestCase {
	
	@Autowired
	ImagemEndpoint imagemEndPoint;
		
	@Test
	@Rollback
	public void testCreateImagem() throws JSONException {
		Imagem imagem = new Imagem(5L, "xlsx", 5L);
		Response response = imagemEndPoint.save(imagem);
		Assert.assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	@Rollback
	public void testReadImagem() throws JSONException {
		Response response = imagemEndPoint.list();
		Assert.assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	@Rollback
	public void testUpdateImagem() throws JSONException {
		Imagem imagem = new Imagem(2L, "PPT", 1L);
		Response response = imagemEndPoint.save(imagem);
		Assert.assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	@Rollback
	public void testDeleteImagem() throws JSONException {
		Imagem imagem = new Imagem(4L);
		Response response = imagemEndPoint.delete(imagem);
		Assert.assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void testSearchImagemByProduct() throws JSONException {
		Response response = imagemEndPoint.searchImagemByProduct(3L);
		JSONArray jsonArray = new JSONArray(response.getEntity().toString());
		Assert.assertEquals(jsonArray.length(), 1);
	}
}
