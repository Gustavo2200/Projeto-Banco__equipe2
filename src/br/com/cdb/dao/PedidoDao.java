package br.com.cdb.dao;
 
import java.util.ArrayList;

import br.com.cdb.model.Produto;
 

 
public class PedidoDao {
	private ArrayList<Produto> listaPedido = new ArrayList<Produto>();
 
	
	public boolean adicionarProduto(Produto produto) {
		
		listaPedido.add(produto);
		return true;
 
	}
 
	public void removerProduto(int codigoBarra) {
		Produto produto1 = new Produto();
		for (Produto produto : listaPedido) {
			{
				if (produto.getCodigoBarra() == codigoBarra)
					produto1 = produto;
			}
		}
		listaPedido.remove(produto1);
	}
 
	public ArrayList<Produto> ListaProduto() {
		return listaPedido;
	}
 
	public double calcularTotal() {
		double total = 0.0;
		for (Produto produto : listaPedido) {
			total += produto.getPreco() * produto.getQuantidadeComprada();
		}
		return total;
	}
 
}
