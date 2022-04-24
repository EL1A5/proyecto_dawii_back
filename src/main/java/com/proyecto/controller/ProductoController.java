package com.proyecto.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

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


import com.proyecto.entidad.Producto;
import com.proyecto.service.ProductoService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/producto")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ProductoController {

	
	@Autowired
	private ProductoService service;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Producto>> listadoProducto() {
		return ResponseEntity.ok(service.ListarProducto());
	}
	
	
	@PostMapping
	@ResponseBody
	
	public ResponseEntity<HashMap<String	, Object>> registrarProducto(@RequestBody Producto obj){
		HashMap<String, Object> salida=new HashMap<String, Object>();
		
		try {
			List<Producto> listproduct=service.listadoporserie(obj.getSerie());
			
			if (CollectionUtils.isEmpty(listproduct)) {
				
				obj.setIdProducto(0);
				obj.setFechaRegistro(new Date());
				Producto objsalida=service.RegistrarProducto(obj);
				
				if (objsalida==null) {
					salida.put("mensaje", "error en el registro");
				} else {
					salida.put("mensaje", "REGISTRO EXITOSO");
				}
				
			} else {
				salida.put("mensaje", "EL PPRODUCTO YA EXISTE"+ obj.getSerie());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			salida.put("mensaje", "error en el registro "+e.getMessage());
		}
		
		
		return ResponseEntity.ok(salida);
	}
	
	
}
