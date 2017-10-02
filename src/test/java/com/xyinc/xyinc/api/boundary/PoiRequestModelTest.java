package com.xyinc.xyinc.api.boundary;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.xyinc.xyinc.api.core.boundary.PoiRequestModel;

public class PoiRequestModelTest {

	@Test
	public void criar() {
		String nome = "Poi Teste";
		Integer coordenadaX = 27;
		Integer coordenadaY = 12;
		
		PoiRequestModel requestModel = new PoiRequestModel
				.Builder()
				.nome(nome)
				.coordenadaX(coordenadaX)
				.coordenadaY(coordenadaY)
				.build();
		
		assertEquals(nome, requestModel.getNome());
		assertEquals(coordenadaX, requestModel.getCoordenadaX());
		assertEquals(coordenadaY, requestModel.getCoordenadaY());
	}
}
