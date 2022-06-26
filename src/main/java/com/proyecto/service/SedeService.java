package com.proyecto.service;

import java.util.List;
import java.util.Optional;
import com.proyecto.entidad.Sede;


public interface SedeService {
	
	public abstract List<Sede> listaSede();

	public abstract Sede insertaActualizaSede(Sede obj);
	
	public List<Sede> listaSedePorNombreDireccionPaisEstado(String nombre, String direccion, int idPais, int estado);
	
	public abstract List<Sede> listaSedePorNombreLike(String nombre);
	
	public abstract void eliminaSede(int id);
	
	public abstract Optional<Sede> buscaSede(int id);

}
