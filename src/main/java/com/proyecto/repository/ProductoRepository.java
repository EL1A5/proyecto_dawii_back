package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.proyecto.entidad.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	@Query("Select p from Producto p where p.serie=?1")
	public abstract List<Producto> listarporserie(String id);
}
