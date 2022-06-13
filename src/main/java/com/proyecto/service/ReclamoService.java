package com.proyecto.service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.proyecto.entidad.Reclamo;

public interface ReclamoService {

	public List<Reclamo> listaReclamo();
	public Reclamo insertaActualizaReclamo(Reclamo obj);
	public  List<Reclamo> ListaReclamoDescricionClienteTiporeclamo(String descripcion ,int idCliente , int idTipoReclamo  , Date fechaCompra , int estado);
	
	//Para el Crud
	public abstract List<Reclamo> listaPorDescripcionLike(String descripcion);
	public abstract void eliminareclamo(int id);
	public abstract Optional<Reclamo> buscaReclamo(int id);

}
