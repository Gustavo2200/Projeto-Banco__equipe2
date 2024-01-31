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
	static PedidoDao pedidoDao = new PedidoDao(); // CLASSE NOME new METODO CONSTRUTOR

	public static void main(String[] args) {

		Produto p1 = new Produto("blusa", 150, 3471883, 20);
		Produto p2 = new Produto("camiseta", 50, 6529961, 20);
		Produto p3 = new Produto("bermuda", 70, 2978546, 20);
		Produto p4 = new Produto("calca", 90, 1035792, 20);
		Produto p5 = new Produto("bone", 60, 3421402, 20);

		produtoService.adicionarProduto(p1);
		produtoService.adicionarProduto(p2);
		produtoService.adicionarProduto(p3);
		produtoService.adicionarProduto(p4);
		produtoService.adicionarProduto(p5);

		int tipoUsuario = 0;

		boolean repete = true;
		while (repete) {
			boolean enquanto = true;
			while (enquanto) {
				try {

					System.out.println("Bem-vindo a loja!");
					System.out.println("(1) Check-in Vendedor");
					System.out.println("(2) Check-in Cliente");
					System.out.println("(3) Sair ");
					System.out.println("Digite o número da opção desejada: ");
					tipoUsuario = input.nextInt();
					input.nextLine();
					enquanto = false;
				} catch (Exception e) {
					System.out.println("Digite novamente somente os números listados");
					input.nextLine();

				}
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
				} else if (tipoUsuario == 3) {
					System.out.println("Saindo do programa.");
					repete = false;
				} else {
					System.out.println("Número inválido, digite novamente somente os números listados ");
					input.nextLine();
				}
			}

		}
	}

	public static void login() {

		input = new Scanner(System.in);
		boolean repete = true;
		Cliente Yago = new Cliente("Yago", 100, "yago.piovarczik1@gmail.com", "12121212121", "16 de fevereiro", "1616");
		Cliente Victor = new Cliente("Victor", 100, "victaohugoresende@gmail.com", "12345678998", "04 de maio", "4978");
		Cliente Davi = new Cliente("Davi", 100, "davi.rodrigues@gmail.com", "98765432112", "06 de setembro", "3958");
		Cliente Lucas = new Cliente("Lucas", 100, "lucas.ferraz@gmail.com", "10293847561", "11 de maio", "2784");

		clienteService.adicionarCliente(Yago);
		clienteService.adicionarCliente(Victor);
		clienteService.adicionarCliente(Davi);
		clienteService.adicionarCliente(Lucas);
		int escolha = 0;
		while (repete) {
			boolean enquanto = true;
			while (enquanto) {
				try {
					System.out.println("(1) Criar cadastro");
					System.out.println("(2) Fazer Login");
					System.out.println("(3) Sair ");
					System.out.println("Digite o número da opção desejada: ");

					escolha = input.nextInt();
					input.nextLine();
					enquanto = false;
				} catch (Exception e) {
					System.out.println("Digite novamente somente os números listados");
					input.nextLine();

				}
			}

			if (escolha == 1) {
				cadastrarCliente();
			} else if (escolha == 2) {
				System.out.println("Olá! Digite o seu email:\n");
				String email = input.nextLine();

				input.nextLine();

				System.out.println("Digite sua senha:\n");
				String senha = input.nextLine();
				Cliente cliente1 = null;
				for (Cliente cliente : clienteService.ListaCliente()) {
					if (cliente.getEmail().equals(email) && cliente.getSenha().equals(senha)) {
						cliente1 = cliente;
						break;

					}
				}
				if (cliente1 != null) {
					menuPedido(cliente1);
				} else {
					System.out.println("Informação incorreta, digite novamente!");

				}
			} else if (escolha == 3) {
				System.out.println("Saindo!:\n");
				repete = false;
			}
			else {
				System.out.println("Numero inválido, digite somente os números listados acima!");
				input.nextLine();
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
		while (clienteService.verificarClienteCpf(cpf) == false) {
			System.out.println("CPF inválido, digite os 11 dígitos");
			cpf = input.nextLine();

		}

		System.out.println("Crie uma senha de 4 a 10 caracteres:\n");
		String senha = input.nextLine();
		while (clienteService.verificarClienteSenha(senha) == false) {
			System.out.println("Senha inválida, a senha deve ter entre 4 a 10 caracteres");
			senha = input.nextLine();
		}
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
			boolean entrada = true;
			while (entrada) {
				try {

					escolha = input.nextInt();
					input.nextLine();
					entrada = false;
				} catch (Exception e) {
					System.out.println("Digite novamente somente os números listados");
					input.nextLine();
				}

			}

			

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
				System.out.println("Nome : " + produto2.getNome() + "\n" + "R$: " + produto2.getPreco() + "\n"
						+ "Código de barras: " + produto2.getCodigoBarra() + "\n" + "Quantidade de Estoque: "
						+ produto2.getQuantidadeEstoque() + "\n" + ";");

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

	public static void menuPedido(Cliente cliente) {
		int escolha1 = 0;

		boolean menu = true;

		while (menu) {

			System.out.println("\nOlá! " + cliente.getNome() + " digite o que voce quer fazer:\n"
					+ "1- Adicionar produto no carrinho \n" + "2- Remover produto do carrinho\n"
					+ "3- Exibir carrinho \n" + "4- Ver saldo\n" + "5- Depositar \n" + "6- Finalizar compra\n"
					+ "7- Voltar \n");
			boolean entrada = true;
			while (entrada) {
				try {

					escolha1 = input.nextInt();
					input.nextLine();
					entrada = false;
				} catch (Exception e) {
					System.out.println("Digite novamente somente os números listados");
					input.nextLine();
				}

			}
			switch (escolha1) {

			case (1):

				listarProdutos();
				input.nextLine();
				System.out.println("Digite o nome do produto desejado:\n");
				String nomeProdutoAdicionar = input.nextLine();

				System.out.println("Qual a quantidade?:\n");
				int quantidade = input.nextInt();
				Produto p = null;

				for (Produto produto : produtoService.listaProduto()) {
					if (nomeProdutoAdicionar.equals(produto.getNome())) {
						if (quantidade < produto.getQuantidadeEstoque()) {

							boolean noCarrinho = false;

							for (Produto pedido : pedidoDao.ListaProduto()) {
								if (nomeProdutoAdicionar.equals(pedido.getNome())) {
									pedido.setQuantidadeComprada(pedido.getQuantidadeComprada() + quantidade);
									noCarrinho = true;
									if (pedido.getQuantidadeComprada() > pedido.getQuantidadeEstoque()) {
										pedido.setQuantidadeComprada(pedido.getQuantidadeEstoque());
										System.out.println("Quantidade ajustada para " + pedido.getQuantidadeEstoque()
												+ " ,pois a quantidade de estoque foi ultrapassada ");
									}
									System.out.println("Quantidade adicionada no carrinho!\n ");
									break;
								}
							}
							if (noCarrinho == false) {
								p = produto;
								p.setQuantidadeComprada(quantidade);
								pedidoDao.adicionarProduto(p);
								System.out.println("Produto adicionado ao carrinho!\n ");
							}
						} else {
							System.out.println("Quantidade do produto não disponivel!\n ");
						}
					}
				}

				break;

			case (2):

				int quantidadeComprada = 0;
				int codigoProdutoRemover = 0;
				Produto p7 = null;
				for (Produto produto1 : pedidoDao.ListaProduto()) {
					System.out.println("Nome: " + produto1.getNome() + "\n" + "R$ :" + produto1.getPreco() + "\n"
							+ "Código de barra do produto : " + produto1.getCodigoBarra() + "\n" + "Quantidade: "
							+ produto1.getQuantidadeComprada() + "\n");

					System.out.println("Digite o código de barra do produto que voce quer remover:\n");
					codigoProdutoRemover = input.nextInt();
					if (codigoProdutoRemover == produto1.getCodigoBarra()) {
						p7 = produto1;
						if (produto1.getQuantidadeComprada() != 1) {
							System.out.println("Quantos produtos você quer remover?:\n");
							int quantidadeRemover = input.nextInt();
							quantidadeComprada = produto1.getQuantidadeComprada() - quantidadeRemover;
							produto1.setQuantidadeComprada(quantidadeComprada);
							System.out.println("Foram removidos: " + quantidadeRemover + " itens do seu carrinho");

						}
					}

				}

				if (p7.getQuantidadeComprada() < 1) {
					pedidoDao.removerProduto(codigoProdutoRemover);
					System.out.println("Item removido com sucesso");
				}
				break;

			case (3):
				double total = 0;
				for (Produto produto1 : pedidoDao.ListaProduto()) {
					System.out.println("Nome: " + produto1.getNome() + "\n" + "R$ :" + produto1.getPreco() + "\n"
							+ "Código de barra do produto : " + produto1.getCodigoBarra() + "\n" + "Quantidade: "
							+ produto1.getQuantidadeComprada() + "\n");
					total = pedidoDao.calcularTotal();

				}
				System.out.println("Valor do carrinho R$: " + total);
				break;

			case (4):

				System.out.println("Saldo: " + cliente.getSaldo() + "\n\n");
				break;

			case (5):

				System.out.println("Quanto você quer depositar?");
				double valor = input.nextDouble();
				cliente.depositar(valor);
				System.out.println("Seu saldo agora, é igual a : " + cliente.getSaldo());
				break;

			case (6):
				if (pedidoDao.ListaProduto().isEmpty()) {
					System.out.println("Seu carrinho está vazio");
				}

				else if (pedidoDao.calcularTotal() > cliente.getSaldo()) {
					System.out.println("Saldo insuficiente");
				} else {
					compraEstoque(cliente, pedidoDao.calcularTotal());
					System.out.println("Seu saldo atual: " + cliente.getSaldo());
				}
				break;

			case (7):

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
			System.out.println("Nome: " + produtoService.listaProduto().get(i).getNome() + "\n" + "Código de barras: "
					+ produtoService.listaProduto().get(i).getCodigoBarra() + "\n" + "R$: "
					+ produtoService.listaProduto().get(i).getPreco() + "\n" + "Quantidade de estoque: "
					+ produtoService.listaProduto().get(i).getQuantidadeEstoque() + "\n");
		}

	}

	public static void compraEstoque(Cliente cliente, double valorTotal) {
		for (Produto produto : pedidoDao.ListaProduto()) { // CLASSE MAE objeto : ONDE VAI BUSCAR
			produtoService.removeProdutoEstoque(produto.getCodigoBarra(), produto.getQuantidadeComprada());

		}
		cliente.setSaldo(cliente.getSaldo() - valorTotal);
		System.out.println("Compra realizada com sucesso!");
		pedidoDao.ListaProduto().clear();

	}

}
