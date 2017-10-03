package com.xyinc.xyinc.api.repository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyinc.xyinc.api.core.entity.Poi;
import com.xyinc.xyinc.api.core.gateway.PoiGateway;

@Service
public class PoiRepositoryImplementation implements PoiGateway {
	
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
	public Poi atualizar(long identificador, Poi poi) {
		
		this.excluir(identificador);
		this.gravar(poi);
		return poi;
	}

	@Override
	public void excluir(long identificador) {
		repository.delete(identificador);
	}
}
