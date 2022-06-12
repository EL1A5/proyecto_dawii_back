package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.entidad.Cliente;
import com.proyecto.entidad.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
	
	
	public abstract List<Producto> findByNombre(String nombre);
	
	@Query("Select p from Producto p where p.serie=?1")
	public abstract List<Producto> listarporserie(String id);
	
	@Query("select x from Producto x where (?1 is '' or x.nombre like ?1) and (?2 is '' or x.serie = ?2) and (?3 is -1 or x.pais.idPais = ?3) and x.estado = ?4")       
	public List<Producto> listaProductoPorNombreSeriePaisEstado(String nombre, String serie, int idpais, int estado);
	
	
	@Query("select x from Producto x where x.nombre like ?1")
	public List<Producto> productoPorNombre(String nombres);
}
