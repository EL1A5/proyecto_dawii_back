package com.proyecto.service;
import java.util.List;
import java.util.Optional;

import com.proyecto.entidad.Cliente;
import com.proyecto.entidad.Producto;
public interface ProductoService {

	public abstract List<Producto>ListarProducto();
	public abstract Producto RegistrarProducto(Producto obj);
	public abstract List<Producto> listadoporserie(String id);
	public abstract List<Producto> listaProductoPorFiltros(String nombre,String serie,int pais, int estado);
	
	
	public abstract List<Producto> listaProductoPorNombre(String nombre);
	public abstract void eliminaproducto(int id);
	public abstract Optional<Producto> buscaproducto(int id);
}
