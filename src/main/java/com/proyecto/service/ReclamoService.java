package com.proyecto.service;


import java.util.Date;
import java.util.List;

import com.proyecto.entidad.Reclamo;

public interface ReclamoService {

	public List<Reclamo> listaReclamo();

	public Reclamo insertaActualizaReclamo(Reclamo obj);
	
	public  List<Reclamo> ListaReclamoDescricionClienteTiporeclamo(String descripcion ,int idCliente , int idTipoReclamo  , Date fechaCompra , int estado);
	

}
