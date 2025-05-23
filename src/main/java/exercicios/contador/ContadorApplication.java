package exercicios.contador;


import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import exercicios.contador.models.Categoria;
import exercicios.contador.models.Fornecedor;
import exercicios.contador.models.Pedido;
import exercicios.contador.models.Produto;
import exercicios.contador.models.ProdutoCategoria;
import exercicios.contador.repository.CategoriaRepository;
import exercicios.contador.repository.FornecedorRepository;
import exercicios.contador.repository.PedidoRepository;
import exercicios.contador.repository.ProdutoCategoriaRepository;
import exercicios.contador.repository.ProdutoRepository;


@SpringBootApplication
public class ContadorApplication implements CommandLineRunner {
	private static ContadorApplication singleton;
	

	//@Autowired
	//@Autowired
	private final PedidoRepository pedidoRepository;
	private final CategoriaRepository categoriaRepository;
	private final ProdutoRepository produtoRepository;
	private final ProdutoCategoriaRepository produtoCategoriaRepository;
	private final FornecedorRepository fornecedorRepository;
	private Scanner leitura = new Scanner(System.in);
	
	public static ContadorApplication get(){return singleton;}
	public PedidoRepository getPedidoRepository(){return pedidoRepository;}
	public CategoriaRepository getCategoriaRepository(){return categoriaRepository;}
	public ProdutoRepository getProdutoRepository(){return produtoRepository;}
	public ProdutoCategoriaRepository getProdutoCategoriaRepository(){return produtoCategoriaRepository;}
	public FornecedorRepository getFornecedorRepository(){return fornecedorRepository;}
	
	//@Autowired
	ContadorApplication(
		PedidoRepository pedidoRepository
		, CategoriaRepository categoriaRepository
		, ProdutoRepository produtoRepository
		, ProdutoCategoriaRepository produtoCategoriaRepository
		, FornecedorRepository fornecedorRepository
	){
		this.pedidoRepository = pedidoRepository;
		this.categoriaRepository = categoriaRepository;
		this.produtoRepository = produtoRepository;
		this.produtoCategoriaRepository = produtoCategoriaRepository;
		this.fornecedorRepository = fornecedorRepository;
		singleton = this;
	}

	public static void main(String[] args) {
		SpringApplication.run(ContadorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Alfa02.lambidas();
		// Alfa02.salivadas();
		// Alfa01.leiteIntegral();
		persistencia();
	}

	void persistencia(){
		var opcao = -1;
        while(opcao != 0){
            var menu = 
"""
	 1 - Listar Produtos
	 2 - Listar Pedidos
	 3 - Listar Categorias
	 4 - Registrar Produtos
	 5 - Registrar Pedidos
	 6 - Registrar Categorias
	 7 - Relacionar Produto & Categoria
	 8 -
	 9 -
	10 -
	11 - Editar Produtos
	12 - Registrar Fornecedor
	13 - Listar Fornecedor
	14 - Listar produtos por categoria
	15 - Listar produtos por preco minimo.
	16 - Listar produtos por preco maximo.
	17 - Listar produtos por trecho de nome.
	18 - Listar produtos por Categoria e preco crescente.
	19 - Listar produtos por Categoria e preco decrescente.
	20 - Contar produtos por Categoria.
	21 - Contar produtos por preco minimo
	22 - Listar produtos por preco maximo ou trecho do nome
	23 - Top 3 produtos caros.
	24 - Top 5 produtos baratos por categoria.
	0 - Sair                                 
""";
    
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();
    
            switch (opcao) {
                case 1:
					listarProduto();
                    break;
                case 2:
					listarPedidos();
                    break;
                case 3: 
					listarCategoria();
                    break;
                case 4: 
                    registrarProduto();
				break;
				case 5: 
                    //listarSeriesBuscadas();
				break;
				case 6: 
                    registrarCategoria();
                    break;
				case 7: 
                    demoRelacao();
					registrarRelacao();
                    break;
				case 11:
					editarProduto();
				break;
				case 12: 
					registrarFornecedor();
				break;
                case 13:
					listarFornecedor();
				break;
				case 14: 
					listarProdutosPorCategoria();
				break;
				case 15:
					listarProdutosPorPrecoMinimo();
				break;
				case 16:
					listarProdutosPorPrecoMaximo();
				break;
				case 17:
					listarProdutosPorTrechoNome();
				break;
				case 18:
					listarProdutosPorCategoriaPorPrecoCrescente();
				break;
				case 19:
					listarProdutosPorCategoriaPorPrecoDecrescente();
				break;
				case 20:
					contarProdutosPorCategoria();
				break;
				case 21:
					contarProdutosPorPrecoMinimo();
				break;
				case 22:
					listarProdutoPorPrecoMaximoOuPorNome();
				break;
				case 23:
					top3ProdutosCaros();
				break;
				case 24:
					top5ProdutosBaratosPorCategoria();
				break;
				case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
	}
	private Double lerDouble(String mensagem){
		String t;
		Double valor;
		while(true){
			System.out.println(mensagem+" (ex: 2.0)");
			t = leitura.nextLine();
			if(t.equals("sair"))return null;
			try{
				valor = Double.parseDouble(t);
				break;
			} catch (NumberFormatException e){
				System.out.println("?");
			}
		}
		return valor;
	}
	private Long lerLong(String mensagem){
		String t;
		Long valor;
		while(true){
			System.out.println(mensagem);
			t = leitura.nextLine();
			if(t.equals("sair"))return null;
			try{
				valor = Long.parseLong(t);
				break;
			} catch (NumberFormatException e){
				System.out.println("?");
			}
		}
		return valor;
	}

	private void listarProduto(){
        List<Produto> series = produtoRepository.findAll();
        series.stream()
            	.sorted(Comparator.comparing(Produto::getNome))
            	.forEach(System.out::println);
    }
	private void listarPedidos(){
        List<Pedido> series = pedidoRepository.findAll();
        series.stream()
           		.sorted(Comparator.comparing(Pedido::getData))
           		.forEach(System.out::println);
    }
	private void listarCategoria() {
		List<Categoria> series = categoriaRepository.findAll();
        series.stream()
            	.sorted(Comparator.comparing(Categoria::getNome))
            	.forEach(System.out::println);
	}
	private void listarFornecedor() {
		List<Fornecedor> series = fornecedorRepository.findAll();
        series.stream()
            	.sorted(Comparator.comparing(Fornecedor::getNome))
            	.forEach(System.out::println);
	}	

	private void registrarProduto(){
		String nome, t;
		
		System.out.println("Insira o nome");
		nome = leitura.nextLine();
		
		Double valor = lerDouble("Insira o valor");
		if(valor==null) return;

		Produto produto = new Produto(nome,valor);
		produtoRepository.save(produto);
	}	
	private void registrarCategoria(){
		String nome;

		System.out.println("Insira o nome da categoria");
		nome = leitura.nextLine();

		Categoria categoria = new Categoria(nome);
		categoriaRepository.save(categoria);
	}
	private void registrarFornecedor(){
		String nome;

		System.out.println("Insira o nome da fornecedor");
		nome = leitura.nextLine();

		Fornecedor fornecedor = new Fornecedor(nome);
		fornecedorRepository.save(fornecedor);
	}

	private void demoRelacao(){
		// Listagem
		System.out.println();
		
		demoProduto();
		demoCategoria();

		List<ProdutoCategoria> produtoCategorias = produtoCategoriaRepository.findAll();
		produtoCategorias.stream()
				.forEach(System.out::println);

		System.out.println();

	}
	private void demoProduto(){
		List<Produto> produtos = produtoRepository.findAll();
        produtos.stream()
            	.sorted(Comparator.comparing(Produto::getNome))
				.map(Produto::demo)
            	.forEach(System.out::println);
				
		System.out.println();
	}
	private void demoCategoria(){
		List<Categoria> categorias = categoriaRepository.findAll();
		categorias.stream()
				.sorted(Comparator.comparing(Categoria::getNome))
				.map(Categoria::demo)
				.forEach(System.out::println);
				
		System.out.println();
	}

	
	private void registrarRelacao(){

		Long produtoId = lerLong("Insira o id de produto");
		if(produtoId==null)return;
		
		Long categoriaId = lerLong("Insira o id de categoria");
		if(categoriaId==null)return;
		
	
		Produto produto = produtoRepository.findById(produtoId)
        		.orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
		Categoria categoria = categoriaRepository.findById(categoriaId)
        		.orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));
		

		ProdutoCategoria produtoCategoria = new ProdutoCategoria();
		produtoCategoria.setProduto(produto);
		produtoCategoria.setCategoria(categoria);
		produtoCategoriaRepository.save(produtoCategoria);
		// Adiciona a relação nos dois lados para consistência
		//System.out.println("p:"+produto.getCategorias().size());
		//System.out.println("c:"+categoria.getProdutos().size());
		
		//List<ProdutoCategoria> a = produto.getCategorias();
		//a.add(produtoCategoria);
		//produto.setCategorias(a);

		//List<ProdutoCategoria> b = categoria.getProdutos();
		//b.add(produtoCategoria);
		//categoria.setProdutos(b);

		//produto.addCategoria(produtoCategoria);
		//categoria.addProduto(produtoCategoria);
		
		// Persiste a relação (pode ser feito em qualquer um dos lados)
		produtoRepository.save(produto);
		
		List<ProdutoCategoria> produtoCategoriasRefresh = produtoCategoriaRepository.findAll();
		produtoCategoriasRefresh.stream()
				.forEach(System.out::println);

		System.out.println();
	}


	private void editarProduto(){
		listarProduto();
		Produto produto;
		Optional<Produto> produtoOpcional;

		String nome, t;
		Double valor;
		Integer inteiro;
		Long longo;

		List<Produto> produtos = produtoRepository.findAll();
        produtos.stream()
            	.sorted(Comparator.comparing(Produto::getNome))
				.map(Produto::demo)
            	.forEach(System.out::println);

		
		while(true){
			System.out.println("Qual deseja buscar?");
			t = leitura.nextLine();
			if(t.equals("sair")) return;
			try{
				longo = Long.parseLong(t);
				produtoOpcional = produtoRepository.findById(longo);
				if(produtoOpcional.isPresent())
					break;
				System.out.println("Nao encontrado.");
			} catch (NumberFormatException e){
				System.out.println("?");
			}
		}
		produto = produtoOpcional.get();

		System.out.println("Confirma nome? "+produto.getNome());
		t = leitura.nextLine();
		if(!t.isEmpty()) produto.setNome(t);
		
		while(true){
			System.out.println("Confirma o valor? "+produto.getPreco());
			t = leitura.nextLine();
			if(t.isEmpty()) break;

			try{
				valor = Double.parseDouble(t);
				produto.setPreco(valor);
				break;
			} catch (NumberFormatException e){
				System.out.println("?");
			}
		}
		
		List<Fornecedor> fornecedores = fornecedorRepository.findAll();
        fornecedores.stream()
            	.sorted(Comparator.comparing(Fornecedor::getNome))
				.map(Fornecedor::demo)
            	.forEach(System.out::println);
		Optional<Fornecedor> fornecedorOpcional = Optional.empty();
		while(true){
			System.out.println("Confirma o Fornecedor? "+(
				(produto.getFornecedor() == null)
				? "[Inexistente]" 
				: produto.getFornecedor().toString()
			));
			t = leitura.nextLine();
			if(t.isEmpty()) break;
			if(t.equals("sair")) return;

			try{
				longo = Long.parseLong(t);
				
				fornecedorOpcional = fornecedorRepository.findById(longo);
				if(fornecedorOpcional.isPresent()) break;

				System.out.println("Nao encontrado.");

			} catch (NumberFormatException e){
				System.out.println("?");
			}
		}
		
		if(fornecedorOpcional.isPresent())produto.setFornecedor(fornecedorOpcional.get());
		produtoRepository.save(produto);
		
		
		listarProduto();
	}

	// JPA.04.02 - Retorne todos os produtos associados a uma categoria específica.
	private void listarProdutosPorCategoria(){
		List<Categoria> categorias = categoriaRepository.findAll();
		categorias.stream()
				.sorted(Comparator.comparing(Categoria::getNome))
				.map(Categoria::demo)
				.forEach(System.out::println);
		String t;
		Long categoriaId;
		while(true){
			System.out.println("Insira o id de categoria");
			t = leitura.nextLine();
			if(t.equals("sair"))return;
			try{
				categoriaId = Long.parseLong(t);
				break;
			} catch (NumberFormatException e){
				System.out.println("?");
			}
		}
	
		Categoria categoria = categoriaRepository.findById(categoriaId)
        		.orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));

		System.out.println(categoria.getProdutos());
	}
	// JPA.04.03 Retorne produtos com preço maior que o valor fornecido.
	private void listarProdutosPorPrecoMinimo(){
		String t;
		Double valor;
		while(true){
			System.out.println("Insira o valor minimo de preco para buscar produtos");
			t = leitura.nextLine();
			if(t.equals("sair"))return;
			try{
				valor = Double.parseDouble(t);
				break;
			} catch (NumberFormatException e){
				System.out.println("?");
			}
		}
		List<Produto> produtos = produtoRepository.findByPrecoGreaterThanOrderByPrecoDesc(valor);
		produtos.stream().forEach(System.out::println);
	}
	// JPA.04.04 Retorne produtos com preço menor que o valor fornecido.
	private void listarProdutosPorPrecoMaximo(){
		String t;
		Double valor;
		while(true){
			System.out.println("Insira o valor maximo de preco para buscar produtos");
			t = leitura.nextLine();
			if(t.equals("sair"))return;
			try{
				valor = Double.parseDouble(t);
				break;
			} catch (NumberFormatException e){
				System.out.println("?");
			}
		}
		List<Produto> produtos = produtoRepository.findByPrecoLessThanOrderByPrecoAsc(valor);
		produtos.stream().forEach(System.out::println);
	}
	//JPA.04.05 - Retorne produtos cujo nome contenha o termo especificado.
	private void listarProdutosPorTrechoNome(){
		String t;
		System.out.println("Insira trecho do nome");
		t = leitura.nextLine();
		List<Produto> produtos = produtoRepository.findByNomeContaining(t);
		System.out.println("Lista por trecho de nome "+t);
		produtos.stream().forEach(System.out::println);
	}
	//JPA.04.06 - Retorne pedidos que ainda não possuem uma data de entrega. 
	//JPA.04.07 - Retorne pedidos com data de entrega preenchida. 
	//JPA.04.08 - Retorne produtos de uma categoria ordenados pelo preço de forma crescente.
	private void listarProdutosPorCategoriaPorPrecoCrescente(){
		String t;
		Long idCategoria;
		demoCategoria();
		while(true){
			System.out.println("Insira o numero da categoria");
			t = leitura.nextLine();
			if(t.equals("sair"))return;
			try{
				idCategoria = Long.parseLong(t);
				break;
			} catch (NumberFormatException e){
				System.out.println("?");
			}
		}
		List<Produto> produtos = produtoRepository.findProdutosByCategoriaPorPrecoAsc(idCategoria);
		produtos.stream().forEach(System.out::println);
	}
	//JPA.04.09 - Retorne produtos de uma categoria ordenados pelo preço de forma decrescente.
	private void listarProdutosPorCategoriaPorPrecoDecrescente(){
		String t;
		Long idCategoria;
		demoCategoria();
		while(true){
			System.out.println("Insira o numero da categoria");
			t = leitura.nextLine();
			if(t.equals("sair"))return;
			try{
				idCategoria = Long.parseLong(t);
				break;
			} catch (NumberFormatException e){
				System.out.println("?");
			}
		}
		List<Produto> produtos = produtoRepository.findByCategoriasCategoriaIdOrderByPrecoDesc(idCategoria);
		produtos.stream().forEach(System.out::println);
	}
	//JPA.04.10 - Retorne a contagem de produtos em uma categoria específica.
	private void contarProdutosPorCategoria(){
		String t;
		demoCategoria();
		System.out.println("Insira o nome da categoria");
		t = leitura.nextLine();
		if(t.equals("sair"))return;
		
		Long cont = produtoRepository.countByCategoriasCategoriaNome(t);
		System.out.println(cont);
	}
	//JPA.04.11 - Retorne a contagem de produtos cujo preço seja maior que o valor fornecido.
	private void contarProdutosPorPrecoMinimo(){
		Double valor = lerDouble("Insira o valor minimo");
		if(valor == null)return;
		Long cont = produtoRepository.countByPrecoGreaterThan(valor);
		System.out.println(cont);
	}
	//JPA.04.12 - Retorne produtos com preço menor que o valor fornecido ou cujo nome contenha o termo especificado.
	private void listarProdutoPorPrecoMaximoOuPorNome(){
		String t;
		Double valor = lerDouble("Insira o preco maximo");
		if(valor == null)return;

		System.out.println("Insira o termo");
		t = leitura.nextLine();
		if(t.equals("sair"))return;
		List<Produto> produtos = produtoRepository.findByPrecoLessThanOrNomeContainingIgnoreCase(valor,t);
		produtos.stream().forEach(System.out::println);
	}
	//JPA.04.13 - Retorne pedidos feitos após uma data específica.
	//JPA.04.14 - Retorne pedidos feitos antes de uma data específica.
	//JPA.04.15 - Retorne pedidos feitos em um intervalo de datas.
	//JPA.04.16 - Retorne os três produtos mais caros.
	private void top3ProdutosCaros(){
		List<Produto> produtos = produtoRepository.findTop3ByOrderByPrecoDesc();
		produtos.stream().forEach(System.out::println);
	}
	//JPA.04.17 - Retorne os cinco produtos mais baratos de uma categoria.
	private void top5ProdutosBaratosPorCategoria(){
		String t;
		demoCategoria();
		System.out.println("Insira o termo");
		t = leitura.nextLine();
		if(t.equals("sair"))return;
		List<Produto> produtos = produtoRepository.findTop5ByCategoriasCategoriaNomeContainingIgnoreCaseOrderByPrecoAsc(t);
		produtos.stream().forEach(System.out::println);
	}
	//JPA.05.01 - Crie uma consulta que retorne os produtos com preço maior que um valor
	private void listarProdutosPrecoMinimoJPQL(){
		Double precoMinimo;
		List<Produto> produtos = produtoRepository.listarProdutosPrecoMinimoJPQL(precoMinimo);
	}
	//JPA.05.02 - Crie uma consulta que retorne os produtos ordenados pelo preço crescente.
	private void listarProdutosOrdenadosPrecoCrescenteJPQL(){
		List<Produto> produtos = produtoRepository.listarProdutosOrdenadosPrecoCrescenteJPQL();
	}
	//JPA.05.03 - Crie uma consulta que retorne os produtos ordenados pelo preço decrescente.
	private void listarProdutosOrdenadosPrecoDerescenteJPQL(){
		List<Produto> produtos = produtoRepository.listarProdutosOrdenadosPrecoDerescenteJPQL();
	}
	//JPA.05.04 - Crie uma consulta que retorne os produtos que comecem com uma letra específica.
	private void listarProdutosComecadosComLetraJPQL(){
		var letra = leitura.nextLine();
		List<Produto> produtos = produtoRepository.listarProdutosComecadosComLetraJPQL(letra);
	}
	//JPA.05.05 - Crie uma consulta que retorne os pedidos feitos entre duas datas.
	// pedidos
	//JPA.05.06 - Crie uma consulta que retorne a média de preços dos produtos.
	private void mediaProdutosJPQL(){
		Double media = pedidoRepository.mediaProdutosJPQL();
	}
	//JPA.05.07 - Crie uma consulta que retorne o preço máximo de um produto em uma categoria
	private void buscarProdutoComMaiorPrecoJPQL(){
		Double maiorPreco = produtoRepository.buscarProdutoComMaiorPrecoJPQL();
	}
	//JPA.05.08 - Crie uma consulta para contar o número de produtos por categoria.
	private void contarProdutoPorCategoriaJPQL(){
		Categoria categoria = buscarCategoria();
		Integer contagem = produtoRepository.contarProdutoPorCategoriaJPQL(categoria);
	}
	//JPA.05.09 - Crie uma consulta para filtrar categorias com mais de 10 produtos.
	private void listarCategoriasComMaisDe10JPQL(){
		List<Categoria> categorias = categoriaRepository.listarCategoriasComMaisDe10JPQL();
	}
	//JPA.05.10 - Crie uma consulta para retornar os produtos filtrados por nome ou por categoria.
	private void listarProdutosPorCategoriaOuNomeJPQL(){
		Categoria categoria = buscarCategoria();
		System.out.println("Qual o nome do produto?");
		String nome = leitura.nextLine();
		List<Produto> produtos = produtoRepository.listarProdutosPorCategoriaOuNomeJPQL(categoria, nome);
	}
	//JPA.05.11 - Crie uma consulta nativa para buscar os cinco produtos mais caros
	private void listarTop5ProdutosCarosNATIVE(){
		List<Produto> produtos = produtoRepository.listarTop5ProdutosCarosNATIVE();
	}

}
