package com.proyecto.service;

import java.util.List;
import com.proyecto.entidad.Cliente;

public interface ClienteService{

	public abstract List<Cliente> listaCliente();
	public abstract Cliente insertaActualizaCliente(Cliente obj);
	public abstract List<Cliente> listaClientePorNombreDniUbigeo(String nombres, String dni, int idUbigeo, int estado);
	

}
