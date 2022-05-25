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
}
