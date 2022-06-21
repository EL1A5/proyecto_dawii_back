package com.proyecto.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.service.ReclamoService;
import com.proyecto.entidad.Reclamo;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/CrudReclamo")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class CrudReclamoController {
	
	@Autowired
	private ReclamoService Reclamo;
	
	@GetMapping("/listaPorDescripcionLike/{des}")
	@ResponseBody
	public ResponseEntity<List<Reclamo>> listaPorDescripcionLike(@PathVariable("des") String des) {
		List<Reclamo> lista  = null;
		try {
			if (des.equals("todos")) {
				lista = Reclamo.listaPorDescripcionLike("%");
			}else {
				lista = Reclamo.listaPorDescripcionLike("%" + des + "%");	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(lista);
	}
	@PostMapping("/registraReclamo")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaReclamo(@RequestBody Reclamo obj) {
		Map<String, Object> salida =new HashMap<>();
		try {
			obj.setFechaRegistro(new Date());
			obj.setEstado(1);
			Reclamo objSalida =  Reclamo.insertaActualizaReclamo(obj);
			if (objSalida == null) {
				salida.put("mensaje", "No se registró Reclamo");
			} else {
				salida.put("mensaje", "Se registró correctamente el Reclamo.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se registró");
		}
		return ResponseEntity.ok(salida);
	}	
	
	@PutMapping("/actualizaReclamo")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaCliente(@RequestBody Reclamo obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Reclamo objSalida =  Reclamo.insertaActualizaReclamo(obj);
			if (objSalida == null) {
				salida.put("mensaje", "No se Actualizo Reclamo");
			} else {
				salida.put("mensaje", "Se Actualizo correctamente el Reclamo.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se registró");
		}
		return ResponseEntity.ok(salida);
	}	
	@DeleteMapping("/eliminaReclamo/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaCliente(@PathVariable("id")int id) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Optional<Reclamo> opt = Reclamo. buscaReclamo(id);
			if (opt.isPresent()) {
				Reclamo.eliminareclamo(id);
				Optional<Reclamo> optreclamo = Reclamo. buscaReclamo(id);
				if (optreclamo.isEmpty()) {
					salida.put("mensaje","Eliminscion exitosa");
				} else {
					salida.put("mensaje","Error al Eliminar");
				}
			}else {
				salida.put("mensaje","ID no existe, no se pudo eliminar");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje","Error al eliminar");
		}
		return ResponseEntity.ok(salida);
	}
}
