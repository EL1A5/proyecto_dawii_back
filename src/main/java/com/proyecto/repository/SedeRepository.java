package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.entidad.Sede;

public interface SedeRepository extends JpaRepository<Sede, Integer> {
	
	@Query("select s from Sede s where (?1 is '' or s.nombre like ?1) and (?2 is '' or s.direccion=?2) and (?3 is -1 or s.pais.idPais = ?3) and s.estado= ?4")
	public List<Sede> listaSedePorNombreDireccionPaisEstado(String nombre, String direccion, int idPais, int estado);

}
