package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidad.Producto;
import com.proyecto.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository repositoryproducto;
	
	@Override
	public List<Producto> ListarProducto() {
		
		return repositoryproducto.findAll();
	}

	@Override
	public Producto RegistrarProducto(Producto obj) {
		return repositoryproducto.save(obj);
	}

	

	@Override
	public List<Producto> listadoporserie(String id) {
		// TODO Auto-generated method stub
		return repositoryproducto.listarporserie(id);
	}

}
