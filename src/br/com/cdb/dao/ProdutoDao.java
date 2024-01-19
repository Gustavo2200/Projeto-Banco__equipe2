package br.com.cdb.dao;
import java.util.ArrayList;
import java.util.Scanner;

import br.com.cbd.model.Produto;

public class ProdutoDao {
	
	ArrayList<Produto> listaProdutos= new ArrayList<>();
	
	public boolean adicionarProduto(Produto produto) 
	{
		listaProdutos.add(produto);
		return true;
	}
	
	public boolean retirarProduto(Produto produto) 
	{
		listaProdutos.remove(0);
		return true;
	}
	

}
