package br.com.cdb.service;

import java.util.ArrayList;

import br.com.cdb.dao.ProdutoDao;
import br.com.cdb.model.Produto;

public class ProdutoService {

	ProdutoDao produtoDao = new ProdutoDao();

	public boolean verificarProdutoExiste(int codigoBarra) {

		for (Produto produto : produtoDao.listaProduto()) {

			if (produto.getCodigoBarra() == codigoBarra) {
				return true;
			}

		}
		return false;

	}

	public boolean verificarTamanhoCodigo(int codigoBarra) {

		if (Integer.toString(codigoBarra).length() == 7) {
			return true;
		}
		return false;
	}

	public boolean adicionarProduto(Produto produto) {
		Produto produto1 = new Produto();
		
		produto1.setNome(produto.getNome());
		produto1.setCodigoBarra(produto.getCodigoBarra());
		produto1.setPreco(produto.getPreco());
		produto1.setQuantidadeEstoque(produto.getQuantidadeEstoque());
		
		produtoDao.adicionarProduto(produto1);
		
		if (verificarTamanhoCodigo(produto1.getCodigoBarra())) {
			return true;
		}
		return false;
	}
	
	public boolean retirarProduto(int codigoBarra) {	
		return produtoDao.retirarProduto(codigoBarra);
	}
	
	public ArrayList<Produto> listaProduto (){
		return produtoDao.listaProduto();
	}
	
	 public Produto buscarProduto(int codigoBarra) {
		 return produtoDao.buscarProduto(codigoBarra);
	 }

}
