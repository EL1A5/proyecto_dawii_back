package com.proyecto.service;

import java.util.List;

import com.proyecto.entidad.Cliente;
import com.proyecto.entidad.Producto;

public interface ClienteService{

	public abstract List<Cliente> listaCliente();
	public abstract Cliente insertaActualizaCliente(Cliente obj);
	public abstract List<Cliente>listadoDNI(String dni);
	

}
