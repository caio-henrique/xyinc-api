package com.xyinc.xyinc.api.core.gateway;

import java.util.Collection;

import com.xyinc.xyinc.api.core.entity.Poi;

public interface PoiGateway {

	public long gravar(Poi poi);

	public Collection<Poi> listar();
	
	public Poi buscar(long identificador);

	public Poi atualizar(long identificador, Poi poi);
	
	public void excluir(long identificador);
}
