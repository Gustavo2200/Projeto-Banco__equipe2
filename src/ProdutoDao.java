import java.util.ArrayList;
import java.util.Scanner;

public class ProdutoDao {
	
	ArrayList<Produto> listaProdutos= new ArrayList<>();
	Scanner input = new Scanner(System.in);
	
	int escolha = 0;
	
	while (escolha != 4) 
	{
		System.out.println("Olá! Digite o que voce quer fazer:\n" 
							+"1- Adicionar item na lista\n"
							+"2- Remover item da lista\n"
							+"3- Exibir lista\n"
							+"4- Sair\n");
		
			escolha = input.nextInt();
			
			switch (escolha) {
			
			case(1): 
				input = new Scanner(System.in);
				
				
			}
	}
	
	public void adicionarProduto(Produto produto) 
	{
		listaProdutos.add(produto);
	}
	
	public void retirarProduto(Produto produto) 
	{
		listaProdutos.remove(0);
	}
	

}
