package br.com.cdb.service;

import java.util.ArrayList;

import br.com.cdb.dao.ClienteDao;

import br.com.cdb.model.Cliente;

public class ClienteService {
	
	ClienteDao clienteDao = new ClienteDao();
	
	public boolean verificarClienteExiste(String cpf) {
		for(Cliente cliente : clienteDao.ListaCliente()) {
			if(cliente.getCpf() == cpf) {
				return true;
			}
		}
		return false;
	}
	
	public boolean verificarClienteNome(String nome) {
		for(int i = 0; i < nome.length(); i++) {
			if(nome.substring(i).matches("[a-zA-Z]*")== false){
				return false;
				
			}
		}
		return true;
		
	}
	public boolean verificarClienteSenha(String senha) {
		if(senha.length() < 4 || senha.length() > 10) {
			return false;
		}
		
		return true;
	}
	
	public boolean verificarClienteCpf(String cpf) {
		if(cpf.length() != 11) {
			return false;
		}
		for(int i = 0;i <cpf.length();i++) {
			if(cpf.substring(i).matches("[0-9]*")== false) {
				return false;
			}
		}
		return true;
	}
	
	public boolean adicionarCliente(Cliente cliente) {
		if(verificarClienteNome(cliente.getNome())) {
			if(verificarClienteCpf(cliente.getCpf())) {
				clienteDao.adicionarCliente(cliente);
				return true;
			}
		}
		return false;
	}
	
	public void retirarCliente(String cpf) {
		clienteDao.retirarCliente(cpf);
	}
	
	public ArrayList<Cliente> ListaCliente(){
		return clienteDao.ListaCliente();
		
	}


}
