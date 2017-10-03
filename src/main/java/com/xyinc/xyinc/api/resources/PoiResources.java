package com.xyinc.xyinc.api.resources;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xyinc.xyinc.api.core.boundary.PoiBoundary;
import com.xyinc.xyinc.api.core.boundary.PoiRequestModel;
import com.xyinc.xyinc.api.core.entity.Poi;
import com.xyinc.xyinc.api.core.usercase.CadastrarPoi;
import com.xyinc.xyinc.api.event.RecursoCriadoEvent;
import com.xyinc.xyinc.api.repository.PoiRepositoryImplementation;

@RestController
@RequestMapping("/poi")
public class PoiResources {
	
	@Autowired
	private PoiRepositoryImplementation repositorio;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	private PoiBoundary boundary;
	
	@PostConstruct
	private void inicializarBoundary() {
		boundary = new CadastrarPoi(repositorio);
	}

	@GetMapping
	public Collection<Poi> listar(){
		return boundary.listar();
	}
	
	@PostMapping
	public ResponseEntity<Long> criar(@Valid @RequestBody PoiRequestModel requestModel, HttpServletResponse response) {
		long identificador = boundary.criar(requestModel);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, identificador));
		return ResponseEntity.status(HttpStatus.CREATED).body(identificador);
	}
	
	@GetMapping("/{identificador}")
	public ResponseEntity<Poi> buscar(@PathVariable long identificador) {
		Poi poiResponse = boundary.buscar(identificador);
		return poiResponse != null ? ResponseEntity.ok(poiResponse) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/ponto-de-interesse")
	public Collection<Poi> buscar(@RequestParam("coordenadaX") Integer coordenadaX, 
			@RequestParam("coordenadaY") Integer coordenadaY, @RequestParam("distancia") Integer distancia) {
		return boundary.listar(coordenadaX, coordenadaY, distancia);
	}
	
	@DeleteMapping("/{identificador}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long identificador) {
		boundary.excluir(identificador);
	}
	
	@PutMapping("/{identificador}")
	public Poi atualizar(@PathVariable Long identificador, @Valid @RequestBody PoiRequestModel requestModel) {
		return boundary.atualizar(identificador, requestModel);
	}
}
