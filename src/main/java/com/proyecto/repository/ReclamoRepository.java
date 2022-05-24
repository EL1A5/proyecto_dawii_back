package com.proyecto.repository;




import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.proyecto.entidad.Reclamo;

public interface ReclamoRepository extends JpaRepository<Reclamo, Integer> {
	
@Query("select x from Reclamo x where (?1 is '' or x.descripcion like ?1) and (?2 is -1 or x.cliente.idCliente = ?2) and (?3 is -1 or x.tipoReclamo.idTipoReclamo = ?3) and (?4 is null or ?4 is '1900-01-01' or x.fechaCompra = ?4) and x.estado = ?5")	
public  List<Reclamo> ListaReclamoDescricionClienteTiporeclamo(String descripcion ,int idCliente , int idTipoReclamo  , Date fechaCompra , int estado);
	
}
