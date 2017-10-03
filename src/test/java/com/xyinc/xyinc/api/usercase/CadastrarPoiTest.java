package com.xyinc.xyinc.api.usercase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.xyinc.xyinc.api.core.boundary.PoiBoundary;
import com.xyinc.xyinc.api.core.boundary.PoiRequestModel;
import com.xyinc.xyinc.api.core.entity.Poi;
import com.xyinc.xyinc.api.core.usercase.CadastrarPoi;
import com.xyinc.xyinc.api.mock.PoiMock;

public class CadastrarPoiTest {
	
	private PoiRequestModel requestModel;
	private String nome = "Teste Poi";
	private Integer coordenadaX = 27;
	private Integer coordenadaY = 12;
	
	@Before
	public void inicializar() {
		requestModel = new PoiRequestModel.Builder()
				.nome(nome)
				.coordenadaX(coordenadaX)
				.coordenadaY(coordenadaY)
				.build();
	}

	@Test
	public void criar() {
		PoiMock mock = new PoiMock();
		PoiBoundary iterator = new CadastrarPoi(mock);
		
		long identificador = iterator.criar(requestModel);
		
		assertEquals(1, identificador);
	}
	
	@Test
	public void criarPoiNulo() {
		PoiMock mock = new PoiMock();
		CadastrarPoi iterator = new CadastrarPoi(mock);
		
		long identificador = iterator.criar(null);
		
		assertEquals(0, identificador);
	}
	
	@Test
	public void criarPoiCoordenadasNegativas() {
		PoiMock mock = new PoiMock();
		CadastrarPoi iterator = new CadastrarPoi(mock);
		
		long identificador = iterator.criar(new PoiRequestModel.Builder()
				.nome("Teste Poi 2")
				.coordenadaX(-12)
				.coordenadaY(27)
				.build());
		
		assertEquals(0, identificador);
	}
	
	@Test
	public void criarPoiNomeNulo() {
		PoiMock mock = new PoiMock();
		CadastrarPoi iterator = new CadastrarPoi(mock);
		
		long identificador = iterator.criar(new PoiRequestModel.Builder()
				.nome(null)
				.coordenadaX(12)
				.coordenadaY(27)
				.build());
		
		assertEquals(0, identificador);
	}
	
	@Test
	public void atualizar() {
		PoiMock mock = new PoiMock();
		PoiBoundary iterator = new CadastrarPoi(mock);
		
		long identificador = iterator.criar(new PoiRequestModel.Builder()
				.nome("Teste Poi 2")
				.coordenadaX(12)
				.coordenadaY(27)
				.build());
		
		assertEquals(1, identificador);
		
		Poi poi = iterator.atualizar(identificador, requestModel);
		
		assertNotNull(poi);
		assertEquals(nome, poi.getNome());
		assertEquals(coordenadaX, poi.getCoordenadaX());
		assertEquals(coordenadaY, poi.getCoordenadaY());
	}
	
	@Test
	public void excluir() {
		PoiMock mock = new PoiMock();
		PoiBoundary iterator = new CadastrarPoi(mock);
		
		long identificador = iterator.criar(requestModel);
		
		assertEquals(1, identificador);
		
		iterator.excluir(identificador);
		
		Poi poi = iterator.buscar(identificador);
		assertEquals(null, poi);
	}
	
	@Test
	public void listar() {
		PoiMock mock = new PoiMock();
		PoiBoundary iterator = new CadastrarPoi(mock);
		
		String nomeTeste2 = "Teste Poi 2";
		
		iterator.criar(new PoiRequestModel.Builder()
				.nome(nomeTeste2)
				.coordenadaX(12)
				.coordenadaY(27)
				.build());
		
		iterator.criar(requestModel);
		
		Collection<Poi> pois = iterator.listar();

		assertNotNull(pois);
		assertEquals(2, pois.size());
		
		Poi poi1 = (Poi) pois.toArray()[0];
		Poi poi2 = (Poi) pois.toArray()[1];
		
		List<String> nomesPoisAdicionados = Arrays.asList(nome, nomeTeste2);
		
		assertTrue(nomesPoisAdicionados.contains(poi1.getNome()));
		assertTrue(nomesPoisAdicionados.contains(poi2.getNome()));
	}
	
	@Test
	public void listarPorPontoDeReferencia() {
		PoiMock mock = new PoiMock();
		PoiBoundary iterator = new CadastrarPoi(mock);
		
		iterator.criar(new PoiRequestModel.Builder()
				.nome("Poi 1")
				.coordenadaX(21)
				.coordenadaY(12)
				.build());
		
		iterator.criar(new PoiRequestModel.Builder()
				.nome("Poi 2")
				.coordenadaX(31)
				.coordenadaY(18)
				.build());
		
		iterator.criar(new PoiRequestModel.Builder()
				.nome("Poi 3")
				.coordenadaX(15)
				.coordenadaY(12)
				.build());
		
		iterator.criar(new PoiRequestModel.Builder()
				.nome("Poi 4")
				.coordenadaX(19)
				.coordenadaY(21)
				.build());
		
		iterator.criar(new PoiRequestModel.Builder()
				.nome("Poi 5")
				.coordenadaX(12)
				.coordenadaY(8)
				.build());
		
		Collection<Poi> pois = iterator.listar(20, 10, 10);

		assertNotNull(pois);
		assertEquals(3, pois.size());
		
		Poi poi1 = (Poi) pois.toArray()[0];
		Poi poi2 = (Poi) pois.toArray()[1];
		Poi poi3 = (Poi) pois.toArray()[2];
		
		List<String> nomesPoisAdicionados = Arrays.asList("Poi 1", "Poi 3", "Poi 5");
		
		assertTrue(nomesPoisAdicionados.contains(poi1.getNome()));
		assertTrue(nomesPoisAdicionados.contains(poi2.getNome()));
		assertTrue(nomesPoisAdicionados.contains(poi3.getNome()));
	}
}
