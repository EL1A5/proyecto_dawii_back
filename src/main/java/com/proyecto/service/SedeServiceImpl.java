package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidad.Sede;
import com.proyecto.repository.SedeRepository;

@Service
public class SedeServiceImpl implements SedeService {
	
	@Autowired
	private SedeRepository repositorio;

	@Override
	public List<Sede> listaSede() {
		return repositorio.findAll();
	}

	@Override
	public Sede insertaActualizaSede(Sede obj) {
		return repositorio.save(obj);
	}

	@Override
	public List<Sede> listaSedePorNombreDireccionPaisEstado(String nombre, String direccion, int idPais, int estado) {
		return repositorio.listaSedePorNombreDireccionPaisEstado(nombre, direccion, idPais, estado);
	}

	@Override
	public void eliminaSede(int id) {
		repositorio.deleteById(id);
		
	}

	@Override
	public Optional<Sede> buscaSede(int id) {
		return repositorio.findById(id);
	}

	@Override
	public List<Sede> listaSedePorNombreLike(String nombre) {
		return repositorio.listaPorNombreLike(nombre);
	}

}
