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
import com.proyecto.entidad.Cliente;
import com.proyecto.entidad.Reclamo;
import com.proyecto.service.ClienteService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/rest/cliente")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ClienteController {
	@Autowired
	private ClienteService cliente;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Cliente>> listaCliente() {
		List<Cliente> lista = cliente.listaCliente();
		return ResponseEntity.ok(lista);
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<Map<String, Object>> registrarCliente(@RequestBody Cliente obj) {
		Map<String, Object> salida = new HashMap<>();

		try {
			obj.setIdCliente(0);
			obj.setEstado(1);
			obj.setFechaRegistro(new Date());
			
			Cliente objSalida = cliente.insertaActualizaCliente(obj);
			if (objSalida == null) {
				salida.put("mensaje", "No se registró cliente");
			} else {
				salida.put("mensaje", "Se registró correctamente el cliente.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se registró");
		}
		return ResponseEntity.ok(salida);
	}
}

	

