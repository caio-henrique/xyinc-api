package com.xyinc.xyinc.api.repository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.xyinc.xyinc.api.core.entity.Poi;
import com.xyinc.xyinc.api.core.gateway.PoiGateway;

@Controller
public class Repositorio implements PoiGateway {
	
	@Autowired
	private PoiRepository repository;

	@Override
	public long gravar(Poi poi) {
		Poi poiResponse = repository.save(poi);
		return poiResponse.getId();
	}

	@Override
	public Collection<Poi> listar() {
		return repository.findAll();
	}

	@Override
	public Poi buscar(long identificador) {
		return repository.findOne(identificador);
	}

	@Override
	public void atualizar(long identificador, Poi poi) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(long identificador) {
		// TODO Auto-generated method stub
		
	}

}
