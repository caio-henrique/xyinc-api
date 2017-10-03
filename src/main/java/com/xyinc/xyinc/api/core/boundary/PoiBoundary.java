package com.xyinc.xyinc.api.core.boundary;

import java.util.Collection;

import com.xyinc.xyinc.api.core.entity.Poi;

public interface PoiBoundary {

	public long criar(PoiRequestModel poiRequestModel);
	
	public Collection<Poi> listar();
	
	public Collection<Poi> listar(Integer coordenadaX, Integer coordenadaY, Integer distanciaMaxima);
	
	public Poi buscar(long identificador);
	
	public Poi atualizar(long identificador, PoiRequestModel poiRequestModel);
	
	public void excluir(long identificador);
}
