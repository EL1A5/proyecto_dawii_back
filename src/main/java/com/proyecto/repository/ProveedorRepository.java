package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.entidad.*;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer>{

	@Query("Select p from Proveedor p where p.ruc=?1")
	public abstract List<Proveedor> listarporRuc(String id);
	
	
}
