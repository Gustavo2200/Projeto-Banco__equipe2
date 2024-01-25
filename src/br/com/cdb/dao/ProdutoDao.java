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

	public void removeProdutoEstoque(int codigoBarra, int quantidade) {
for (Produto produto : listaProdutos) {
	if (produto.getCodigoBarra() == codigoBarra && produto.getQuantidadeEstoque()>=quantidade) {
		int quantidadeRemover = produto.getQuantidadeComprada();
		produto.setQuantidadeEstoque(produto.getQuantidadeEstoque()-quantidade);
		
		if (produto.getQuantidadeEstoque()  < 1) {
			retirarProduto(codigoBarra);
		}
	
	
	}
}
	}

	public boolean retirarProduto(int codigoBarra) {
		Produto produto2 = null; //MEXER NO PRODUTO
		for (Produto produto : listaProdutos) {
			produto2 = produto;
			if (produto.getCodigoBarra() != codigoBarra) {
				
				return false;
			}
		}
		listaProdutos.remove(produto2);
		return true;
	}

	public ArrayList<Produto> listaProduto() {
		return listaProdutos;
	}

	public Produto buscarProduto(int codigoBarra) {
		for (Produto produto : listaProdutos) {
			{
				if (produto.getCodigoBarra() == codigoBarra)
					return produto;
			}

		}
		return null;
	}

}
