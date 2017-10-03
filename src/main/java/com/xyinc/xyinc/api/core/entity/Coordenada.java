package com.xyinc.xyinc.api.core.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Coordenada {

	private Integer coordenadaX;
	
	private Integer coordenadaY;
	
	@SuppressWarnings("unused")
	private Coordenada() {
	}
	
	public Coordenada(Integer coordenadaX, Integer coordenadaY) {
		this.coordenadaX = coordenadaX;
		this.coordenadaY = coordenadaY;
	}
	
	public Integer getCoordenadaX() {
		return coordenadaX;
	}

	public Integer getCoordenadaY() {
		return coordenadaY;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coordenadaX == null) ? 0 : coordenadaX.hashCode());
		result = prime * result + ((coordenadaY == null) ? 0 : coordenadaY.hashCode());
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
		Coordenada other = (Coordenada) obj;
		if (coordenadaX == null) {
			if (other.coordenadaX != null)
				return false;
		} else if (!coordenadaX.equals(other.coordenadaX))
			return false;
		if (coordenadaY == null) {
			if (other.coordenadaY != null)
				return false;
		} else if (!coordenadaY.equals(other.coordenadaY))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cordenada [coordenadaX=" + coordenadaX + ", coordenadaY=" + coordenadaY + "]";
	}
}
