import java.util.List;
import java.util.Scanner;

import br.com.cdb.dao.PagamentoDao;
import br.com.cdb.dao.PedidoDao;
import br.com.cdb.model.Produto;
import br.com.cdb.service.ProdutoService;

public class Main {
	
	private static final String SENHA_VENDEDOR = "senha";
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		PagamentoDao pagamentoDao = new PagamentoDao();
		ProdutoService produtoService = new ProdutoService();

		System.out.println("Bem-vindo a loja!");
		System.out.print("Você é (1) Vendedor ou (2) Comprador? Escolha: ");
		int tipoUsuario = input.nextInt();

		if (tipoUsuario == 1) {
			System.out.print("Digite a senha do vendedor: ");
			String senhaDigitada = input.next();

			if (senhaDigitada.equals(SENHA_VENDEDOR)) {
				menuProduto();
			} else {
				System.out.println("Senha incorreta. Saindo do programa.");
			}
		} else if (tipoUsuario == 2) {
			menuPedido(input, produtoService);
		} else {
			System.out.println("Opção inválida. Saindo do programa.");
		}
	}

	

	public static void menuProduto() {
		Scanner input = new Scanner(System.in);
		ProdutoService produtoService = new ProdutoService();
		
		boolean menu = true;

		int escolha = 0;

		while (menu) {
			
			System.out.println("Olá! Digite o que voce quer fazer:\n" + "1- Adicionar produto no estoque\n"
					+ "2- Remover produto do estoque\n" + "3- Exibir estoque\n"
					+ "4- Buscar produto pelo código de barras\n" + "5- Voltar\n");


			escolha = input.nextInt();

			switch (escolha) {
			case 1:
				input.nextLine();

				input = new Scanner(System.in);

				Produto produto = new Produto();

				System.out.println("Escreva o nome do produto:\n");
				String nomeProduto = input.nextLine();

				System.out.println("Escreva o preço do produto:\n");
				double precoProduto = input.nextDouble();

				System.out.println("Escreva o código de barra do produto:\n");
				int codigoProduto = input.nextInt();

				System.out.println("Qual a quantidade de produtos:\n");
				int quantidadeProduto = input.nextInt();

				produto.setNome(nomeProduto);
				produto.setCodigoBarra(codigoProduto);
				produto.setPreco(precoProduto);
				produto.setQuantidadeEstoque(quantidadeProduto);

				if (produtoService.adicionarProduto(produto)) {
					System.out.println("Produto adicionado com sucesso!");
				}
				else {
					System.out.println("Erro ao salvar produto.");
				}

				break;

			case 2:

				System.out.println("Digite o código do produto que você quer remover:\n");
				int codigoRemover = input.nextInt();
				produtoService.retirarProduto(codigoRemover);

				break;

			case 3:

				listarProdutos(produtoService);

				break;
			case 4:

				System.out.println("Digite o código do produto que você quer buscar:\n");
				int codigoBuscar = input.nextInt();
				Produto produto2 = produtoService.buscarProduto(codigoBuscar);
				System.out.println(produto2.getNome() + "\n" + produto2.getPreco() + "\n" + produto2.getCodigoBarra()
						+ "\n" + produto2.getQuantidadeEstoque() + "\n" + ";");

				break;
				
			case 5:
				System.out.println("saindo");
				menu = false;
				
				break;
			default: System.out.println("opcao invalida!");

			}

		}
	}

	public static void menuPedido( Scanner input, ProdutoService produtoService  ) {
		PedidoDao pedidoDao = new PedidoDao();
		
	System.out.println("Olá! Digite o que voce quer fazer:\n" 
			+"1- Adicionar produto no carrinho \n"
			+"2- Remover produto do carrinho\n"
			+"3- Exibir valor total do pedido\n"
			+"4- Exibir carrinho"
			+"5- Voltar");
	
		int escolha1 = 0;
		
		boolean menu = true;
		
		while (menu) {
			escolha1 = input.nextInt();
			
			switch(escolha1){
				
			case(1):
				
				listarProdutos(produtoService);
				
				System.out.println("Digite o nome do produto desejado:\n");
				String nomeProdutoAdicionar = input.nextLine();
				
				for (Produto produto : produtoService.listaProduto()) {
					if (nomeProdutoAdicionar.equals(produto.getNome())) {
						pedidoDao.adicionarProduto(produto);
					}
				}
				break;
				
			case(2):
				
				for (Produto produto1 : pedidoDao.ListaProduto()) {
					System.out.println(produto1.getNome() + "\n" + produto1.getPreco() + "\n"
							+ produto1.getCodigoBarra() + "\n" + produto1.getQuantidadeEstoque() + "\n");
					
					System.out.println("Digite o código de barra do produto que voce quer remover:\n");
					int codigoProdutoRemover = input.nextInt();
					
					if (codigoProdutoRemover == produto1.getCodigoBarra()) {
						pedidoDao.removerProduto(produto1.getCodigoBarra());
					}
				}
				
				break;
				
			case(3):
				double total = pedidoDao.calcularTotal();
				System.out.println(total);
				
				break;
			
			case(4):
				
				for (Produto produto1 : pedidoDao.ListaProduto()) {
					System.out.println(produto1.getNome() + "\n" + produto1.getPreco() + "\n"
							+ produto1.getCodigoBarra() + "\n" + produto1.getQuantidadeEstoque() + "\n");
				}
				break;
			
			case(5):
			
				System.out.println("Voltando para o menu anterior.");
				menu = false;
				break;
				
			default: System.out.println("opcao invalida!");
			}
			
		}
	}
	
	public static void listarProdutos (ProdutoService produtoService) {

		
		for (int i=0; i<produtoService.listaProduto().size(); i++) {
			System.out.println(produtoService.listaProduto().get(i).getNome() + "\n" +produtoService.listaProduto().get(i).getCodigoBarra() + "\n" + produtoService.listaProduto().get(i).getPreco() + "\n" + produtoService.listaProduto().get(i).getQuantidadeEstoque() + "\n"  );
			
		}
	}
	}
