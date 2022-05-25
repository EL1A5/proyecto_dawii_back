package com.proyecto.service;
import java.util.List;

import com.proyecto.entidad.Producto;
public interface ProductoService {

	public abstract List<Producto>ListarProducto();
	public abstract Producto RegistrarProducto(Producto obj);
	public abstract List<Producto> listadoporserie(String id);
	public abstract List<Producto> listaProductoPorFiltros(String nombre,String serie,int pais, int estado);
}
