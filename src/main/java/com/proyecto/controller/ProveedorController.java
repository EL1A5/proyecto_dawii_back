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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entidad.Proveedor;
import com.proyecto.service.ProveedorService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/proveedor")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ProveedorController {
	@Autowired
	private ProveedorService proveedorService;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Proveedor>> listaProveedor() {
		List<Proveedor> lista = proveedorService.listar();
		return ResponseEntity.ok(lista);
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaProveedor(@RequestBody Proveedor obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			List<Proveedor> listproduct = proveedorService.listadoporRuc(obj.getRuc());

			if (CollectionUtils.isEmpty(listproduct)) {

				obj.setIdProveedor(0);
				obj.setEstado(1);
				obj.setFechaRegistro(new Date());
				Proveedor objsalida = proveedorService.insertaActualiza(obj);

				if (objsalida == null) {
					salida.put("mensaje", "error en el registro");
				} else {
					salida.put("mensaje", "REGISTRO EXITOSO");
				}

			} else {
				salida.put("mensaje", "EL PROVEEDOR YA EXISTE " + obj.getRuc());
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			salida.put("mensaje", "error en el registro " + e.getMessage());
		}
		return ResponseEntity.ok(salida);
	}

}
