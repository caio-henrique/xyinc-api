package com.xyinc.xyinc.api.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.hibernate.validator.HibernateValidator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.xyinc.xyinc.api.core.entity.Coordenada;
import com.xyinc.xyinc.api.core.entity.Poi;

public class PoiTest {
	
	private LocalValidatorFactoryBean localValidatorFactory;
	
	@Before
	public void setup() {
	    localValidatorFactory = new LocalValidatorFactoryBean();
	    localValidatorFactory.setProviderClass(HibernateValidator.class);
	    localValidatorFactory.afterPropertiesSet();
	}

	@Test
	public void criarPoi() {
		String nome = "Poi Teste";
		Integer coordenadaX = 27;
		Integer coordenadaY = 12;
		
		Coordenada coordenada = new Coordenada(coordenadaX, coordenadaY);
		Poi poi = new Poi(nome, coordenada);
		
		assertEquals(nome, poi.getNome());
		assertEquals(coordenadaX, poi.getCoordenadaX(), 27);
		assertEquals(coordenadaY, poi.getCoordenadaY(), 12);
	}
	
	@Test
	public void criarCoordenadasNegativas() {
		Integer coordenadaX = -27;
		Integer coordenadaY = -12;
		
		Coordenada coordenada = new Coordenada(coordenadaX, coordenadaY);
		assertFalse(localValidatorFactory.validate(coordenada).isEmpty());
	}
	
	@Test
	public void criarPoiNomeVazio() {
		String nome = "";
		Integer coordenadaX = 27;
		Integer coordenadaY = 12;
		
		Coordenada coordenada = new Coordenada(coordenadaX, coordenadaY);
		Poi poi = new Poi(nome, coordenada);
		
		assertFalse(localValidatorFactory.validate(poi).isEmpty());
	}
}
