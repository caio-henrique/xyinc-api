package com.xyinc.xyinc.api.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.xyinc.xyinc.api.core.entity.Coordenada;
import com.xyinc.xyinc.api.core.entity.Poi;

public class PoiTest {
	
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
}
