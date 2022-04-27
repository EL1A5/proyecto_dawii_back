package com.proyecto.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entidad.Sede;
import com.proyecto.service.SedeService;

@RestController
@RequestMapping("/url/sede")
@CrossOrigin(origins = "http://localhost:4200")
public class SedeController {
	
	//Autor : Hernan Cardenas Breña
	
	@Autowired
	private SedeService sedeService;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Sede>> listaSede(){
		List<Sede> lista = sedeService.listaSede();
		return ResponseEntity.ok(lista);
	}

	@PostMapping
	@ResponseBody
	public  ResponseEntity<Map<String, Object>> insertaSede(@RequestBody Sede obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setEstado(1);
			obj.setFechaRegistro(new Date());
			Sede objSalida = sedeService.insertaActualizaSede(obj);
			if (objSalida == null) {
				salida.put("mensaje", "No se registró, consulte con el administrador.");
			}else {
				salida.put("mensaje", "Se registró correctamente.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se registró, consulte con el administrador.");
		}
		return ResponseEntity.ok(salida);
	}

}
