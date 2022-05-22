package com.proyecto.service;

import java.util.List;

import com.proyecto.entidad.Reclamo;

public interface ReclamoService {

	public List<Reclamo> listaReclamo();

	public Reclamo insertaActualizaReclamo(Reclamo obj);
	

}
