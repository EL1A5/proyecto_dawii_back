package com.proyecto.service;

import java.util.List;
import java.util.Optional;
import com.proyecto.entidad.Proveedor;

public interface ProveedorService {

	public abstract List<Proveedor> listar();
	public abstract Proveedor insertaActualiza(Proveedor obj);
	public abstract List<Proveedor> listadoporRuc(String id);
	public abstract List<Proveedor> listaProveedorConParametros(String razonsocial,String ruc,int ubigeo, int estado);
	//CRUD
	public abstract List<Proveedor> listaProveedorPorRazonSocial(String razonSocial);
	public abstract void eliminaProveedor(int idProveedor);
	public abstract Optional<Proveedor> buscaProveedor(int idProveedor);

}
