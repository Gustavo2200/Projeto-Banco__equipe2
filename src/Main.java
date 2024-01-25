import java.util.List;
import java.util.Scanner;

import br.com.cdb.dao.PagamentoDao;
import br.com.cdb.dao.PedidoDao;
import br.com.cdb.model.Cliente;
import br.com.cdb.model.Produto;
import br.com.cdb.service.ClienteService;
import br.com.cdb.service.ProdutoService;

public class Main {

	private static final String SENHA_VENDEDOR = "senha";
	static Scanner input = new Scanner(System.in);
	PagamentoDao pagamentoDao = new PagamentoDao();
	static ProdutoService produtoService = new ProdutoService();
	static ClienteService clienteService = new ClienteService();

	public static void main(String[] args) {

		boolean repete = true;
		while (repete) {
			System.out.println("Bem-vindo a loja!");
			System.out.println("(1) Vendedor ");
			System.out.println("(2) Checkout");
			System.out.println("(3) Sair ");
			System.out.println("Digite o número da opção desejada: ");
			int tipoUsuario = input.nextInt();
			input.nextLine();

			if (tipoUsuario == 1) {
				System.out.print("Digite a senha do vendedor: ");
				String senhaDigitada = input.next();

				if (senhaDigitada.equals(SENHA_VENDEDOR)) {
					menuProduto();
				} else {
					System.out.println("Senha incorreta");
				}
			} else if (tipoUsuario == 2) {
				login();
			} else {
				System.out.println("Saindo do programa.");
				repete = false;
			}
		}

	}

	public static void login() {
		input = new Scanner(System.in);
		boolean repete = true;
		while (repete) {
			System.out.println("(1) Criar cadastro");
			System.out.println("(2) Fazer Login");
			System.out.println("(3) Sair ");
			System.out.println("Digite o número da opção desejada: ");

			int escolha = input.nextInt();
			input.nextLine();

			if (escolha == 1) {
				cadastrarCliente();
			} else if (escolha == 2) {
				System.out.println("Olá! Digite o seu email:\n");
				String email = input.nextLine();

				input.nextLine();

				System.out.println("Digite sua senha:\n");
				String senha = input.nextLine();

				for (Cliente cliente : clienteService.ListaCliente()) {
					if (cliente.getEmail().equals(email) && cliente.getSenha().equals(senha)) {
						menuPedido();
					} else {
						System.out.println("Informação incorreta, digite novamente!");
					}
				}
			} else if (escolha == 3) {
				System.out.println("Saindo!:\n");
				repete = false;
			}
		}
	}

	public static void cadastrarCliente() {
		input = new Scanner(System.in);
		System.out.println("Olá! Digite o seu nome:\n");
		String nome = input.nextLine();

		System.out.println("Qual é a sua data de nascimento?:\n");
		String dataNascimento = input.nextLine();

		System.out.println("Digite o seu email?:\n");
		String email = input.nextLine();

		System.out.println("Qual é o seu cpf?:\n");
		String cpf = input.nextLine();

		System.out.println("Crie uma senha:\n");
		String senha = input.nextLine();

		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setDataDeNascimento(dataNascimento);
		cliente.setEmail(email);
		cliente.setCpf(cpf);
		cliente.setSenha(senha);
		cliente.setSaldo(100);
		if (clienteService.adicionarCliente(cliente)) {
			System.out.println("Cliente registrado com sucesso!");
		}

	}

	public static void menuProduto() {

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
				while (!produtoService.verificarTamanhoCodigo(codigoProduto)) {
					System.out.println("Digite um codigo com 7 números!");
					codigoProduto = input.nextInt();
				}

				System.out.println("Qual a quantidade de produtos:\n");
				int quantidadeProduto = input.nextInt();

				produto.setNome(nomeProduto);
				produto.setCodigoBarra(codigoProduto);
				produto.setPreco(precoProduto);
				produto.setQuantidadeEstoque(quantidadeProduto);

				if (produtoService.adicionarProduto(produto)) {
					System.out.println("Produto adicionado com sucesso!");
				} else {
					System.out.println("Erro ao salvar produto.");
				}

				break;

			case 2:

				System.out.println("Digite o código do produto que você quer remover:\n");
				int codigoRemover = input.nextInt();
				produtoService.retirarProduto(codigoRemover);
				System.out.println("Produto removido!");

				break;

			case 3:

				listarProdutos();

				break;
			case 4:

				System.out.println("Digite o código do produto que você quer buscar:\n");
				int codigoBuscar = input.nextInt();
				Produto produto2 = produtoService.buscarProduto(codigoBuscar);
				System.out.println( "Nome : " + produto2.getNome() + "\n" + "R$: " + produto2.getPreco() + "\n" + "Código de barras: " + produto2.getCodigoBarra()
						+ "\n" + "Quantidade de Estoque: " + produto2.getQuantidadeEstoque() + "\n" + ";");

				break;

			case 5:
				System.out.println("saindo");
				menu = false;

				break;
			default:
				System.out.println("opcao invalida!");

			}

		}
	}

	public static void menuPedido() {
		PedidoDao pedidoDao = new PedidoDao();

		int escolha1 = 0;

		boolean menu = true;

		while (menu) {

			System.out.println("Olá! Digite o que voce quer fazer:\n" + "1- Adicionar produto no carrinho \n"
					+ "2- Remover produto do carrinho\n" + "3- Exibir valor total do pedido\n" + "4- Exibir carrinho \n"
					+ "5- Voltar \n");

			escolha1 = input.nextInt();
			switch (escolha1) {

			case (1):

				listarProdutos();

				System.out.println("Digite o nome do produto desejado:\n");
				String nomeProdutoAdicionar = input.nextLine();
				
				input.nextLine();
				
				System.out.println("Qual a quantidade?:\n");
				int quantidade = input.nextInt();

				for (Produto produto : produtoService.listaProduto()) {
					if (nomeProdutoAdicionar.equals(produto.getNome())) {
						produto.setQuantidadeComprada(quantidade);
						pedidoDao.adicionarProduto(produto);

					}
				}
				break;

			case (2):
				int quantidadeComprada = 0;
				int codigoProdutoRemover = 0;
				for (Produto produto1 : pedidoDao.ListaProduto()) {
					System.out.println("Nome: " + produto1.getNome() + "\n" + "R$ :" + produto1.getPreco() + "\n"
							+"Código de barra do produto : " + produto1.getCodigoBarra() + "\n" + "Quantidade: " + produto1.getQuantidadeComprada() + "\n");

					System.out.println("Digite o código de barra do produto que voce quer remover:\n");
					codigoProdutoRemover = input.nextInt();
					if (codigoProdutoRemover == produto1.getCodigoBarra()) {
						if (produto1.getQuantidadeComprada() != 1) {
							System.out.println("Quantos produtos você quer remover?:\n");
							int quantidadeRemover = input.nextInt();
							quantidadeComprada = produto1.getQuantidadeComprada() - quantidadeRemover;
							produto1.setQuantidadeComprada(quantidadeComprada);

						}
					}

				}
				pedidoDao.removerProduto(codigoProdutoRemover);
				break;

			case (3):
				double total = pedidoDao.calcularTotal();
				System.out.println(total);

				break;

			case (4):

				for (Produto produto1 : pedidoDao.ListaProduto()) {
					System.out.println("Nome: " + produto1.getNome() + "\n" + "R$ :" + produto1.getPreco() + "\n"
							+"Código de barra do produto : " + produto1.getCodigoBarra() + "\n" + "Quantidade: " + produto1.getQuantidadeComprada() + "\n");
				}
				break;

			case (5):

				System.out.println("Voltando para o menu anterior.");
				menu = false;
				break;

			default:
				System.out.println("opcao invalida!");
			}

		}
	}

	public static void listarProdutos() {

		for (int i = 0; i < produtoService.listaProduto().size(); i++) {
			System.out.println("Nome: " + produtoService.listaProduto().get(i).getNome() + "\n"
					+ "Código de barras: " + produtoService.listaProduto().get(i).getCodigoBarra() + "\n"
					+ "R$: " + produtoService.listaProduto().get(i).getPreco() + "\n"
					+ "Quantidade de estoque: " +produtoService.listaProduto().get(i).getQuantidadeEstoque() + "\n");

		}
	}
}
