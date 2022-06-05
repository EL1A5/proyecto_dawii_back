package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidad.Cliente;
import com.proyecto.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Override
	public List<Cliente> listaCliente() {
		return repository.findAll();
	}

	@Override
	public Cliente insertaActualizaCliente(Cliente obj) {
		return repository.save(obj);
	}

	@Override
	public List<Cliente> listaClientePorNombreDniUbigeo(String nombres, String dni, int idUbigeo, int estado) {
		return repository.listaClientePorNombreDniUbigeo(nombres, dni, idUbigeo, estado);
	}

	@Override
	public Cliente insertaActualizaClientes(Cliente obj) {
		return repository.save(obj);
	}

	@Override
	public List<Cliente> listaDClientesPorNombreLike(String nombre) {
		return repository.listaPorNombreLike(nombre);
	
	}

	@Override
	public void eliminaCliente(int id) {
		repository.deleteById(id);
		
	}

	@Override
	public Optional<Cliente> buscaCliente(int id) {
		return repository.findById(id);
		}
	}
	

