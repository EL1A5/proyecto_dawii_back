package com.proyecto.service;

import java.util.List;
import java.util.Optional;

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

	@Override
	public List<Producto> listaProductoPorFiltros(String nombre, String serie, int pais, int estado) {
		// TODO Auto-generated method stub
		return repositoryproducto.listaProductoPorNombreSeriePaisEstado(nombre, serie, pais, estado);
	}

	@Override
	public List<Producto> listaProductoPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return repositoryproducto.productoPorNombre(nombre);
	}

	@Override
	public void eliminaproducto(int id) {
		repositoryproducto.deleteById(id);
		
	}

	@Override
	public Optional<Producto> buscaproducto(int id) {
		// TODO Auto-generated method stub
		return repositoryproducto.findById(id);
	}

	
	

}
