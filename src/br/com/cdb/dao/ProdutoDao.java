package br.com.cdb.dao;

import java.util.ArrayList;
import java.util.Scanner;

import br.com.cdb.model.Produto;

public class ProdutoDao {

	private ArrayList<Produto> listaProdutos = new ArrayList<>();

	public boolean adicionarProduto(Produto produto) {
		listaProdutos.add(produto);
		return true;
	}

	public boolean retirarProduto(int codigoBarra) {
		for(Produto produto : listaProdutos) {
			if(produto.getCodigoBarra() == codigoBarra) {
				listaProdutos.remove(produto);
				return true;
			}
		}
		return false;
	}

    public ArrayList<Produto> listaProduto () {
	return listaProdutos;
  }
    
    public Produto buscarProduto(int codigoBarra) {
    	for( Produto produto : listaProdutos ) {
    		{
    			if( produto.getCodigoBarra() == codigoBarra) 
    			return produto;
    		}
    		
    	}
		return null;
    }

}
