package com.proyecto.service;

import java.util.List;

import com.proyecto.entidad.Sede;


public interface SedeService {
	
	public abstract List<Sede> listaSede();

	public abstract Sede insertaActualizaSede(Sede obj);
	
	public List<Sede> listaSedePorNombreDireccionPaisEstado(String nombre, String direccion, int idPais, int estado);

}
