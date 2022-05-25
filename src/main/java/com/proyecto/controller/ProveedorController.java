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

import com.proyecto.entidad.Proveedor;
import com.proyecto.service.ProveedorService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/rest/proveedor")
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
	
	
	@GetMapping("/listaProveedorConParametros")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaProveedorConParametros(
			@RequestParam(name ="razonsocial", required = false, defaultValue = "") String razonsocial,
			@RequestParam(name ="ruc", required = false, defaultValue = "") String ruc,
			@RequestParam(name="idUbigeo", required = false, defaultValue = "-1") int idUbigeo,
			@RequestParam(name="estado", required = true, defaultValue = "1") int estado
			){
		
		System.out.println("RAZON SOCIAL : "  + razonsocial);
		System.out.println("ruc : "  + ruc);
		System.out.println("idUbigeo : "  + idUbigeo);
		System.out.println("estado : "  + estado);
		
		
		Map<String, Object> salida = new HashMap<>();
		try {
			List<Proveedor> lista = proveedorService.listaProveedorConParametros("%"+razonsocial+"%", ruc, idUbigeo, estado);
			
			if (CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "No existen registros para mostrar." );
			}else{
				salida.put("lista", lista);
				salida.put("mensaje", "Existen " + lista.size() + " registros para mostrar.");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			salida.put("mensaje", "No se registro, ocurrio un error.");
		}
		return ResponseEntity.ok(salida);
	}
	

}
