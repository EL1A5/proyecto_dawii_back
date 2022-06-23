package com.proyecto.service;

import java.util.List;
import java.util.Optional;

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

	@Override
	public List<Proveedor> listaProveedorConParametros(String razonsocial, String ruc, int ubigeo, int estado) {
		return repository.listaProveedorConParametros(razonsocial, ruc, ubigeo, estado);
	}

	@Override
	public List<Proveedor> listaProveedorPorRazonSocial(String razonSocial) {
		return repository.proveedorPorRazonSocial(razonSocial);
	}

	@Override
	public void eliminaProveedor(int idProveedor) {
		repository.deleteById(idProveedor);
	}

	@Override
	public Optional<Proveedor> buscaProveedor(int idProveedor) {
		return repository.findById(idProveedor);
	}
	

}
