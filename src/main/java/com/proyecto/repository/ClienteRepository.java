package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.proyecto.entidad.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

		
		@Query("select x from Cliente x where (?1 is '' or x.nombres like ?1) and (?2 is '' or x.dni = ?2) and (?3 is -1 or x.ubigeo.idUbigeo = ?3) and x.estado = ?4")       
		public List<Cliente> listaClientePorNombreDniUbigeo(String nombres, String dni, int idUbigeo, int estado);
		

		@Query("select x from Cliente x where x.nombres like ?1")
		public List<Cliente> listaPorNombreLike(String nombres);
		
		
	}

