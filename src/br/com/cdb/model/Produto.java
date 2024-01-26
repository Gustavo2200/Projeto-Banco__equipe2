package br.com.cdb.model;

public class Produto {

	private String nome;
	private double preco;
	private int codigoBarra;
	private int quantidadeEstoque;
	private int quantidadeComprada;
	
	
	
	public int getQuantidadeComprada() {
		return quantidadeComprada;
	}

	public void setQuantidadeComprada(int quantidadeComprada) {
		this.quantidadeComprada = quantidadeComprada;
	}

	public Produto() {}
	
	public Produto(String nome, double preco, int codigoBarra, int quantidadeEstoque) {
		this.nome = nome;
		this.preco = preco;
		this.codigoBarra = codigoBarra;
		this.quantidadeEstoque = quantidadeEstoque;
		
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double precoProduto) {
		this.preco = precoProduto;
	}
	public int getCodigoBarra() {
		return codigoBarra;
	}
	public void setCodigoBarra(int codigoBarra) {
		this.codigoBarra = codigoBarra;
	}
	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	public void setQuantidadeEstoque(int quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	
	
}
