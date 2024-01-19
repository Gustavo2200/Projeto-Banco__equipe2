package br.com.cdb.dao;
 
import java.util.ArrayList;
 
import br.com.cbd.model.Produto;
 
public class PedidoDao {
	private ArrayList<Produto> listaPedido = new ArrayList<Produto>();
 
	public boolean adicionarProduto(Produto produto) {
		listaPedido.add(produto);
		return true;
 
	}
 
	public void removerProduto(int codigoBarra) {
		for (Produto produto : listaPedido) {
			{
				if (produto.getCodigoBarra() == codigoBarra)
					listaPedido.remove(produto);
			}
		}
	}
 
	public ArrayList<Produto> ListaProduto() {
		return listaPedido;
	}
 
	public double calcularTotal() {
		double total = 0.0;
		for (Produto produto : listaPedido) {
			total += produto.getPreco();
		}
		return total;
	}
 
}
