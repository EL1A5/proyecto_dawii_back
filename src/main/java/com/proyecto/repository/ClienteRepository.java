package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.proyecto.entidad.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	

}
