package br.com.cdb.service;

import br.com.cdb.dao.ProdutoDao;
import br.com.cdb.model.Produto;

public class ProdutoService {
	
	ProdutoDao produtoDao = new ProdutoDao();
	
	public boolean verificarProdutoExiste(int codigoBarra) {
		
		for (Produto produto : produtoDao.listaProduto() ) {
			
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

}


