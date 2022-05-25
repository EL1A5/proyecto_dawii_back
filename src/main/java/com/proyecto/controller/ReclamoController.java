package com.proyecto.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entidad.Reclamo;
import com.proyecto.service.ReclamoService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/reclamo")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ReclamoController {

	@Autowired
	private ReclamoService reclamoService;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Reclamo>> listaReclamo() {
		List<Reclamo> lista = reclamoService.listaReclamo();
		return ResponseEntity.ok(lista);

	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaActualizaReclamo(@RequestBody Reclamo obj) {
		Map<String, Object> salida = new HashMap<>();

		try {

			obj.setFechaRegistro(new Date());
			obj.setEstado(1);
			Reclamo objSalida = reclamoService.insertaActualizaReclamo(obj);
			if (objSalida == null) {
				salida.put("mensaje", "No se registró el reclamo");
			} else {
				salida.put("mensaje", "Se registró correctamente.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se registró");
		}
		return ResponseEntity.ok(salida);
	
	}

	
	
	@GetMapping("/listaReclamoConParametros")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaReclamoDescricionClienteTiporeclamoFecha(
			@RequestParam(name = "descripcion",required = false , defaultValue = " ")String descripcion,
			@RequestParam(name = "idCliente",required = false , defaultValue = "-1")int idCliente,
			@RequestParam(name = "idTipoReclamo",required = false , defaultValue = "-1")int idTipoReclamo,
			@RequestParam(name = "fechaCompra",required = false)Date fechaCompra,
	        @RequestParam(name = "estado", required = true, defaultValue = "1") int estado){
		Map<String, Object> salida = new HashMap<>();
		try {
			if(fechaCompra.getYear()==0) {
				fechaCompra = null;
			}
			List<Reclamo> lista =reclamoService.ListaReclamoDescricionClienteTiporeclamo("%"+ descripcion +"%", idCliente, idTipoReclamo,fechaCompra, estado);
			
			if(CollectionUtils.isEmpty(lista)){
				
				salida.put("mensaje", "No existen datos para mostrar ");
			}else {
				salida.put("lista", lista);	
				salida.put("mensaje", " Existen " + lista.size() + " para mostrar ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se registró, consulte con el administrador ");
		}
		
		return ResponseEntity.ok(salida);

	
	}
	
	
}
