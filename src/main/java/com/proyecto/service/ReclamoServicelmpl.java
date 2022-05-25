package com.proyecto.service;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proyecto.entidad.Reclamo;
import com.proyecto.repository.ReclamoRepository;

@Service
public class ReclamoServicelmpl implements ReclamoService {

	@Autowired
	private ReclamoRepository repo;
	@Override
	public List<Reclamo> listaReclamo() {
		return repo.findAll();
	}
	@Override
	public Reclamo insertaActualizaReclamo(Reclamo obj) {
		return repo.save(obj);
	}
	
	@Override
	public List<Reclamo> ListaReclamoDescricionClienteTiporeclamo(String descripcion, int idCliente, int idTipoReclamo,
			Date fechaCompra, int estado) {
		return repo.ListaReclamoDescricionClienteTiporeclamo(descripcion, idCliente, idTipoReclamo,fechaCompra, estado);
	}
	
	

	
}
