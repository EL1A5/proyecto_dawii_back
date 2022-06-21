package com.proyecto.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.proyecto.entidad.Marca;
import com.proyecto.entidad.Pais;
import com.proyecto.entidad.Producto;
import com.proyecto.repository.ProductoRepository;

@SpringBootTest
class ProductoTest {

	@Autowired
	ProductoRepository repo;

	@Test
	public void TestRegistroProducto() {

		Producto produc = new Producto();
		Marca marca = new Marca();
		marca.setIdMarca(3);
		Pais pais = new Pais();
		pais.setIdPais(16);

		
		produc.setNombre("PRUEBA");
		produc.setSerie("8474778682");
		produc.setDurabilidad("Fragil");
		produc.setFechaVigencia(new Date());
		produc.setPrecio(99);
		produc.setEstado(1);
		produc.setStock(10);
		produc.setMarca(marca);
		produc.setPais(pais);
		produc.setFechaRegistro(new Date());

		repo.save(produc);
		assertNotNull(repo.findById(99));
		
	}

	@Test
	public void TestListarProductos() {
		List<Producto> list = repo.findAll();
		assertThat(list).size().isGreaterThan(0);
	}

	@Test
	public void testBuscarPorId() {
		Producto product = repo.findById(1).get();
		System.out.println("NOMBRE " + product.getNombre());
		assertEquals("Martillo de fierro", product.getNombre());
	}

	@Test 
	public void testActualizarProducto(){ 
		Producto p= repo.findById(1).get() ; 
	     p.setStock(1000); 
	     repo.save(p) ; 
	     assertNotEquals (200.00,repo.findById(1).get().getStock()); 
	     
	}

	@Test
	public void testEliminarProducto() {
		
		int id=3;
		
		repo.deleteById(id);
		assertThat(repo.existsById(id)).isFalse();
	}
}
