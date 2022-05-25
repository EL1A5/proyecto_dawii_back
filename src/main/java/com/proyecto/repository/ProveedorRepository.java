package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.entidad.*;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer>{

	@Query("Select p from Proveedor p where p.ruc=?1")
	public abstract List<Proveedor> listarporRuc(String id);
	
	@Query("select x from Proveedor x where (?1 is '' or x.razonsocial like ?1) and (?2 is '' or x.ruc=?2) and (?3 is -1 or x.ubigeo.idUbigeo =?3) and x.estado=?4")
	public List<Proveedor> listaProveedorConParametros(String razonsocial, String ruc, int ubigeo, int estado);
	
	
	
}
