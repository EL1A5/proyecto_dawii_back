package com.proyecto.service;

import java.util.List;


import com.proyecto.entidad.Proveedor;

public interface ProveedorService {

	public abstract List<Proveedor> listar();
	public abstract Proveedor insertaActualiza(Proveedor obj);
	public abstract List<Proveedor> listadoporRuc(String id);
}
