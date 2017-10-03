package com.xyinc.xyinc.api.core.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "poi")
public class Poi {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@Embedded
	@JsonIgnore
	private Coordenada coordenada;
	
	@SuppressWarnings("unused")
	private Poi() {
	}

	public Poi(String nome, Coordenada coordenada) {
		this.nome = nome;
		this.coordenada = coordenada;
	}
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Integer getCoordenadaX() {
		return coordenada.getCoordenadaX();
	}
	
	public Integer getCoordenadaY() {
		return coordenada.getCoordenadaY();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coordenada == null) ? 0 : coordenada.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Poi other = (Poi) obj;
		if (coordenada == null) {
			if (other.coordenada != null)
				return false;
		} else if (!coordenada.equals(other.coordenada))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Poi [id=" + id + ", nome=" + nome + ", coordenada=" + coordenada + "]";
	}
}
