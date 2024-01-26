package br.com.cdb.dao;

public class PagamentoDao {
	
	PedidoDao pedidoDao = new PedidoDao();
	
	public double calcularValorTotal() {
		
		return pedidoDao.calcularTotal();
	}
	
	public boolean formaPagamento(String escolha) {
		
		if (escolha.equals("pix")) {
			
			return true;
		}
		else {
			return false;
	}
	
	}
}
