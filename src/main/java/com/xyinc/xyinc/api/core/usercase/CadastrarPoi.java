package com.xyinc.xyinc.api.core.usercase;

import java.util.Collection;
import java.util.HashSet;

import com.xyinc.xyinc.api.core.boundary.PoiBoundary;
import com.xyinc.xyinc.api.core.boundary.PoiRequestModel;
import com.xyinc.xyinc.api.core.entity.Coordenada;
import com.xyinc.xyinc.api.core.entity.Poi;
import com.xyinc.xyinc.api.core.gateway.PoiGateway;

public class CadastrarPoi implements PoiBoundary{
	
	private final PoiGateway gateway;
	
	public CadastrarPoi(final PoiGateway gateway) {
		this.gateway = gateway;
	}

	@Override
	public long criar(final PoiRequestModel requestModel) {
		
		if(!this.requestModelValido(requestModel))
			return 0;
		
		Coordenada coordenada = new Coordenada(requestModel.getCoordenadaX(), 
				requestModel.getCoordenadaY());
		Poi poi = new Poi(requestModel.getNome(), coordenada);
		
		return gateway.gravar(poi);
	}

	@Override
	public Collection<Poi> listar() {
		return gateway.listar();
	}

	@Override
	public Poi atualizar(final long identificador, 
			final PoiRequestModel requestModel) {
		Coordenada coordenada = new Coordenada(requestModel.getCoordenadaX(), 
				requestModel.getCoordenadaY());
		Poi poi = new Poi(requestModel.getNome(), coordenada);
		
		return gateway.atualizar(identificador, poi);
	}

	@Override
	public void excluir(final long identificador) {
		gateway.excluir(identificador);
	}

	@Override
	public Poi buscar(final long identificador) {
		return gateway.buscar(identificador);
	}

	@Override
	public Collection<Poi> listar(final Integer coordenadaX, 
			final Integer coordenadaY, final Integer distanciaMaxima) {
		Collection<Poi> poisCadastrados = this.listar();
		Collection<Poi> poisLocalizados = new HashSet<Poi>();
		
		for (Poi poi : poisCadastrados) {
			
			if((coordenadaX + distanciaMaxima) >= poi.getCoordenadaX() && 
					(coordenadaY + distanciaMaxima) >= poi.getCoordenadaY() &&
					(coordenadaX - distanciaMaxima) <= poi.getCoordenadaX() &&
					(coordenadaY - distanciaMaxima) <= poi.getCoordenadaY()) {
				
				poisLocalizados.add(poi);
			}
		}
		
		return poisLocalizados;
	}
	
	private boolean requestModelValido(final PoiRequestModel requestModel) {
		
		if(requestModel == null || requestModel.getNome() == null || requestModel.getNome().isEmpty() ||
				requestModel.getCoordenadaX() == null || requestModel.getCoordenadaX() <= 0 ||
				requestModel.getCoordenadaY() == null || requestModel.getCoordenadaY() <= 0 )
			return false;
		
		return true;
	}
}
