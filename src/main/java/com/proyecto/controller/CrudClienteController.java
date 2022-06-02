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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entidad.Cliente;
import com.proyecto.service.ClienteService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/rest/cliente")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class CrudClienteController {
	@Autowired
	private ClienteService cliente;
	
	
	@GetMapping("/listaClientePorNombreLike/{nom}")
	@ResponseBody
	public ResponseEntity<List<Cliente>> listaClientesPorNombreLike(@PathVariable("nom") String nom) {
		List<Cliente> lista  = null;
		try {
			if (nom.equals("todos")) {
				lista = cliente.listaDClientesPorNombreLike("%");
			}else {
				lista = cliente.listaDClientesPorNombreLike("%" + nom + "%");	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping("/registraCliente")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaCliente(@RequestBody Cliente obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setIdCliente(0);
			obj.setFechaRegistro(new Date());
			obj.setEstado(1);
			Cliente objSalida =  cliente.insertaActualizaClientes(obj);
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
	@PutMapping("/actualizaCliente")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaCliente(@RequestBody Cliente obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Cliente objSalida =  cliente.insertaActualizaClientes(obj);
			if (objSalida == null) {
				salida.put("mensaje", "No se Actualizo cliente");
			} else {
				salida.put("mensaje", "Se Actualizo correctamente el cliente.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se registró");
		}
		return ResponseEntity.ok(salida);
	}	
	
}
	
