package com.proyecto.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
				obj.setEstado(1);
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
	
	
	
	@GetMapping("/porSerie/{numSerie}")
	@ResponseBody
	public ResponseEntity<List<Producto>> listaProductoPorNumSerie(@PathVariable("numSerie")String serie) {
		List<Producto> lista = service.listadoporserie(serie);
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/listaProductosPorFiltros")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaDocenteNombreDniUbigeo(
			@RequestParam(name = "nombre", required = false, defaultValue = "") String nombre,
			@RequestParam(name = "serie", required = false, defaultValue = "") String serie,
			@RequestParam(name = "idPais", required = false, defaultValue = "-1") int idPais,
			@RequestParam(name = "estado", required = true, defaultValue = "1") int estado) {
		Map<String, Object> salida = new HashMap<>();
		try {
			List<Producto> lista = service.listaProductoPorFiltros("%"+nombre+"%", serie, idPais, estado);
			if (CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "No existen datos para mostrar");
			}else {
				salida.put("lista", lista);
				salida.put("mensaje", "Existen " + lista.size() + " elementos para mostrar");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "ERROR AL LISTAR");
		}
		return ResponseEntity.ok(salida);
	}
	

	
	
	
	//CRUD
	
	
	
	
	@GetMapping("/listaProductosPorNombre/{nom}")
	@ResponseBody
	public ResponseEntity<List<Producto>> listaProductosPorNombre(@PathVariable("nom") String nom) {
		List<Producto> lista  = null;
		try {
			if (nom.equals("todos")) {
				lista = service.listaProductoPorNombre("%");
			}else {
				lista = service.listaProductoPorNombre("%" + nom + "%");	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(nom);
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping("/registraProducto")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaProducto(@RequestBody Producto obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setIdProducto(0);
			obj.setFechaRegistro(new Date());
			obj.setEstado(1);
			Producto objSalida =  service.RegistrarProducto(obj);
			if (objSalida == null) {
				salida.put("mensaje", "No se registró PRODUCTO");
			} else {
				salida.put("mensaje", "Se registró correctamente el PRODUCTO.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se registró PRODUCTO");
		}
		return ResponseEntity.ok(salida);
	}	
	
	
	
	
	@PutMapping("/actualizaProducto")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaProducto(@RequestBody Producto obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Producto objSalida =  service.RegistrarProducto(obj);
			if (objSalida == null) {
				salida.put("mensaje", "No se Actualizo cliente");
			} else {
				salida.put("mensaje", "Se Actualizo correctamente el PRODUCTO.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se registró");
		}
		return ResponseEntity.ok(salida);
	}	
	
	@DeleteMapping("/eliminaProducto/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaProducto(@PathVariable("id")int id) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Optional<Producto> opt = service.buscaproducto(id);
			if (opt.isPresent()) {
				service.eliminaproducto(id);
				Optional<Producto> productos = service.buscaproducto(id);
				if (productos.isEmpty()) {
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
