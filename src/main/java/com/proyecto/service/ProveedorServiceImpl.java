package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidad.Proveedor;
import com.proyecto.repository.ProveedorRepository;

@Service
public class ProveedorServiceImpl  implements ProveedorService{

	@Autowired
	private ProveedorRepository repository;

	@Override
	public List<Proveedor> listar() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Proveedor insertaActualiza(Proveedor obj) {
		// TODO Auto-generated method stub
		return repository.save(obj);
	}

	@Override
	public List<Proveedor> listadoporRuc(String id) {
		// TODO Auto-generated method stub
		return repository.listarporRuc(id);
	}
	

}
