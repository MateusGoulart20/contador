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
	
	11 - Editar Produtos
	12 - Registrar Fornecedor
	13 - Listar Fornecedor

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
                case 13:
					listarFornecedor();
				break;
				case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
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
		Double valor;

		System.out.println("Insira o nome");
		nome = leitura.nextLine();
		
		while(true){
			System.out.println("Insira o valor (ex: 2.0)");
			t = leitura.nextLine();
			try{
				valor = Double.parseDouble(t);
				break;
			} catch (NumberFormatException e){
				System.out.println("?");
			}
		}
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
		
		List<Produto> produtos = produtoRepository.findAll();
        produtos.stream()
            	.sorted(Comparator.comparing(Produto::getNome))
				.map(Produto::demo)
            	.forEach(System.out::println);
				
		System.out.println();
				
		List<Categoria> categorias = categoriaRepository.findAll();
		categorias.stream()
				.sorted(Comparator.comparing(Categoria::getNome))
				.map(Categoria::demo)
				.forEach(System.out::println);
				
		System.out.println();

		List<ProdutoCategoria> produtoCategorias = produtoCategoriaRepository.findAll();
		produtoCategorias.stream()
				.forEach(System.out::println);

		System.out.println();

	}

	
	@Transactional
	public void registrarRelacao(){

		String t;
		Long produtoId, categoriaId;
		while(true){
			System.out.println("Insira o id de produto");
			t = leitura.nextLine();
			try{
				produtoId = Long.parseLong(t);
				break;
			} catch (NumberFormatException e){
				if(t.equals("sair"))return;
				System.out.println("?");
			}
		}
		while(true){
			System.out.println("Insira o id de categoria");
			t = leitura.nextLine();
			try{
				categoriaId = Long.parseLong(t);
				if(t.equals("sair"))return;
				break;
			} catch (NumberFormatException e){
				System.out.println("?");
			}
		}
	
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


	public void editarProduto(){
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
}
