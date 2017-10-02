package com.xyinc.xyinc.api.mock;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.xyinc.xyinc.api.core.entity.Poi;
import com.xyinc.xyinc.api.core.gateway.PoiGateway;

public class PoiMock implements PoiGateway {
	
	private final Map<Long, Poi> todosPois = new HashMap<>();
	private long identificador = 1;

	@Override
	public long gravar(Poi poi) {
		todosPois.put(identificador, poi);
		return identificador++;
	}
	
	@Override
	public Collection<Poi> listar() {
		return new HashSet<Poi>(todosPois.values());
	}

	@Override
	public void atualizar(long identificador, Poi poi) {
		todosPois.replace(identificador, poi);
	}

	@Override
	public void excluir(long identificador) {
		todosPois.remove(identificador);
	}

	@Override
	public Poi buscar(long identificador) {
		return todosPois.get(identificador);
	}
}
