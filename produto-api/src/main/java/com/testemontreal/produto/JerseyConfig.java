package com.testemontreal.produto;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.testemontreal.produto.service.ImagemEndpoint;
import com.testemontreal.produto.service.ProdutoEndpoint;

import io.swagger.jaxrs.config.BeanConfig;

@Component
@ApplicationPath("/produtoapi")
public class JerseyConfig extends ResourceConfig {
	
	private static final String RESOURCE_PACKAGE = "com.testemontreal.produto";

	public JerseyConfig() {
		register(ProdutoEndpoint.class);
		register(ImagemEndpoint.class);
		
        // Swagger
        register(io.swagger.jaxrs.listing.ApiListingResource.class);
        register(io.swagger.jaxrs.listing.SwaggerSerializers.class);

        // allows @RolesAllowed
        register(RolesAllowedDynamicFeature.class);
	}
	
    @Bean
    public BeanConfig swaggerConfig() {
        final BeanConfig swaggerConfig = new BeanConfig();
        swaggerConfig.setBasePath("/produtoapi");
        swaggerConfig.setVersion("1.0.0");
        swaggerConfig.setTitle("Produto API");
        swaggerConfig.setResourcePackage(RESOURCE_PACKAGE);
        swaggerConfig.setScan(true);
        return swaggerConfig;
    }

}
