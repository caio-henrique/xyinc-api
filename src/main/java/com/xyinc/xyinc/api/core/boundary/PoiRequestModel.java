package com.xyinc.xyinc.api.core.boundary;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class PoiRequestModel {

	@NotBlank
	private String nome;
	
	@Min(1)
	@NotNull
	private Integer coordenadaX;
	
	@Min(1)
	@NotNull
	private Integer coordenadaY;
	
	@SuppressWarnings("unused")
	private PoiRequestModel() {
	}

	public PoiRequestModel(final String nome, final Integer coordenadaX, final Integer coordenadaY) {
		this.nome = nome;
		this.coordenadaX = coordenadaX;
		this.coordenadaY = coordenadaY;
	}

	public String getNome() {
		return nome;
	}

	public Integer getCoordenadaX() {
		return coordenadaX;
	}

	public Integer getCoordenadaY() {
		return coordenadaY;
	}
	
	public static class Builder {
		
		private String nome;
		private Integer coordenadaX;
		private Integer coordenadaY;
		
		public Builder nome(String nome) {
			this.nome = nome;
			return this;
		}
		
		public Builder coordenadaX(Integer coordenadaX) {
			this.coordenadaX = coordenadaX;
			return this;
		}

		public Builder coordenadaY(Integer coordenadaY) {
			this.coordenadaY = coordenadaY;
			return this;
		}
		
		public PoiRequestModel build() {
			return new PoiRequestModel(nome, coordenadaX, coordenadaY);
		}
	}
}
