package br.com.cdb.dao;
import java.util.ArrayList;

import br.com.cdb.model.Cliente;


public class ClienteDao {
	
	ArrayList<Cliente> listaCliente= new ArrayList<Cliente>();
	
	public boolean adicionarCliente(Cliente cliente)
	{
		listaCliente.add(cliente);
		return true;
	}
	
	public void retirarCliente(String cpf)
	{
		for(Cliente cliente : listaCliente){
			{
				if(cliente.getCpf().equals(cpf))
				listaCliente.remove(cliente);
				
			}
				
		}
		
	}
	
	public ArrayList<Cliente> ListaCliente(){
		return listaCliente;
	}
	


}
