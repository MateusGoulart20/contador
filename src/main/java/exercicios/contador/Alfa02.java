package exercicios.contador;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import exercicios.contador.models.Pessoa;
import exercicios.contador.models.Produto;
import exercicios.contador.models.ProdutoClassificado;
import exercicios.contador.models.Tarefa;

public class Alfa02 {
public void salivadas() {
		Optional<Integer> retornoOptionalInteger;

		System.out.println("\n#1  Dada a lista de números inteiros a seguir, encontre o maior número dela.");
		List<Integer> numeros1 = Arrays.asList(10, 20, 30, 40, 50);
		retornoOptionalInteger = numeros1.stream()
				.sorted((p1, p2) -> Integer.compare(p2, p1))
				.findFirst();
		if (retornoOptionalInteger.isPresent())
			System.out.println(retornoOptionalInteger.get());

		System.out.println(
				"\n#2  Dada a lista de palavras (strings) abaixo, agrupe-as pelo seu tamanho. No código a seguir, há um exemplo prático do resultado esperado.");
		List<String> palavras = Arrays.asList("java", "stream", "lambda", "code");
		Map<Integer, List<String>> retornoLista = palavras.stream()
				.collect(Collectors.groupingBy(String::length));
		System.out.println(retornoLista);

		System.out.println(
				"\n#3  Dada a lista de nomes abaixo, concatene-os separados por vírgula. No código a seguir, há um exemplo prático do resultado esperado.");
		List<String> nomes = Arrays.asList("Alice", "Bob", "Charlie");
		Optional<String> retorno = nomes.stream()
				.reduce((p1, p2) -> p1 + ", " + p2);
		if (retorno.isPresent())
			System.out.println(retorno.get());

		System.out.println(
				"\n#4  Dada a lista de números inteiros abaixo, calcule a soma dos quadrados dos números pares.");
		List<Integer> numeros2 = Arrays.asList(1, 2, 3, 4, 5, 6);
		retornoOptionalInteger = numeros2.stream()
				.filter(n -> n % 2 == 0)
				.map(p -> p * p)
				.peek(System.out::println)
				.reduce((p1, p2) -> p1 + p2); // .reduce(0, Integer::sum);
		if (retornoOptionalInteger.isPresent())
			System.out.println(retornoOptionalInteger.get());

		System.out.println("\n#5  Dada uma lista de números inteiros, separe os números pares dos ímpares.");
		List<Integer> numeros3 = Arrays.asList(1, 2, 3, 4, 5, 6);
		Map<Boolean, List<Integer>> particionado = numeros3.stream()
				.collect(Collectors.partitioningBy(n -> n % 2 == 0));
		System.out.println("Pares: " + particionado.get(true)); // Esperado: [2, 4, 6]
		System.out.println("Ímpares: " + particionado.get(false)); // Esperado: [1, 3, 5]

		System.out.println("\n#Lista de Produtos.");
		List<ProdutoClassificado> produtos = Arrays.asList(
				new ProdutoClassificado("Smartphone", 800.0, "Eletrônicos"),
				new ProdutoClassificado("Notebook", 1500.0, "Eletrônicos"),
				new ProdutoClassificado("Teclado", 200.0, "Eletrônicos"),
				new ProdutoClassificado("Cadeira", 300.0, "Móveis"),
				new ProdutoClassificado("Monitor", 900.0, "Eletrônicos"),
				new ProdutoClassificado("Mesa", 700.0, "Móveis"));
		System.out.println(
				"\n#6  Dada a lista de produtos acima, agrupe-os por categoria em um Map<String, List<Produto>>.");
		Map<String, List<ProdutoClassificado>> mapaProdutos = produtos.stream()
				.collect(Collectors.groupingBy(ProdutoClassificado::getCategoria));
		System.out.println(mapaProdutos);

		System.out.println(
				"\n#7  Dada a lista de produtos acima, conte quantos produtos há em cada categoria e armazene em um Map<String, Long>.");
		Map<String, Long> quantidadeProdutos = produtos.stream()
				.collect(Collectors.groupingBy(ProdutoClassificado::getCategoria,
						Collectors.counting()));
		System.out.println(quantidadeProdutos);

		System.out.println(
				"\n#8  Dada a lista de produtos acima, encontre o produto mais caro de cada categoria e armazene o resultado em um Map<String, Optional<Produto>>.");
		Map<String, Optional<ProdutoClassificado>> maisCaroPorCategoria = produtos.stream()
				.collect(Collectors.groupingBy(
                        ProdutoClassificado::getCategoria,
						Collectors.maxBy(
								Comparator.comparingDouble(ProdutoClassificado::getPreco))));
		System.out.println(maisCaroPorCategoria);

		System.out.println(
				"\n#9  Dada a lista de produtos acima, calcule o total dos preços dos produtos em cada categoria e armazene o resultado em um Map<String, Double>.");
		Map<String, Double> precoProdutos = produtos.stream()
				.collect(Collectors.groupingBy(
                        ProdutoClassificado::getCategoria, Collectors.summingDouble(ProdutoClassificado::getPreco)));
		System.out.println(precoProdutos);
	}

	public void lambidas() {
		System.out.println("\n#1 Filtrar pares.");
		List<Integer> numeros0 = Arrays.asList(1, 2, 3, 4, 5, 6);
		numeros0.stream()
				.filter(n -> n % 2 == 0)
				.forEach(System.out::println);

		System.out.println("\n#2 colocar maiusculas");
		List<String> palavras1 = Arrays.asList("java", "stream", "lambda");
		palavras1.stream()
				.map(String::toUpperCase)
				.forEach(System.out::println);

		System.out.println("\n#3 numeros impares multiplicados por dois");
		List<Integer> numeros1 = Arrays.asList(1, 2, 3, 4, 5, 6);
		List<Integer> numerosResultados = numeros1.stream()
				.filter(n -> n % 2 == 1)
				.map(n -> n *= 2)
				.collect(Collectors.toList());
		numerosResultados.forEach(System.out::println);

		System.out.println("\n#4 tirar os repetidos");
		List<String> palavras2 = Arrays.asList("apple", "banana", "apple", "orange", "banana");
		palavras2.stream().distinct().forEach(System.out::println);

		System.out.println(
				"\n#5 Dada a lista de sublistas de números inteiros abaixo, extraia todos os números primos em uma única lista e os ordene em ordem crescente.");
		List<List<Integer>> listaDeNumeros = Arrays.asList(
				Arrays.asList(1, 2, 3, 4),
				Arrays.asList(5, 6, 7, 8),
				Arrays.asList(9, 10, 11, 12));

		List<Integer> numerosPrimos = listaDeNumeros.stream()
				.flatMap(List::stream) // Achatar as sublistas em uma única stream
				.filter(Alfa02::ehPrimo) // Filtrar apenas números primos
				.sorted() // Ordenar em ordem crescente
				.collect(Collectors.toList()) // Coletar em uma lista
		;

		System.out.println(numerosPrimos); // Saída esperada: [2, 3, 5, 7, 11]

		System.out.println(
				"\n#6 Dado um objeto Pessoa com os campos nome e idade, filtre as pessoas com mais de 18 anos, extraia os nomes e imprima-os em ordem alfabética. A classe Pessoa está definida abaixo.");
		List<Pessoa> pessoas = Arrays.asList(
				new Pessoa("Alice", 22),
				new Pessoa("Bob", 17),
				new Pessoa("Charlie", 19));
		pessoas.stream()
				.filter(p -> p.getIdade() > 18)
				.map(Pessoa::getNome)
				.sorted()
				.forEach(System.out::println);

		System.out.println(
				"\n#7 Você tem uma lista de objetos do tipo Produto, onde cada produto possui os atributos nome (String), preco (double) e categoria (String). Filtre todos os produtos da categoria \"Eletrônicos\" com preço menor que R$ 1000, ordene-os pelo preço em ordem crescente e colete o resultado em uma nova lista.");
		List<ProdutoClassificado> produtos = Arrays.asList(
				new ProdutoClassificado("Smartphone", 800.0, "Eletrônicos"),
				new ProdutoClassificado("Notebook", 1500.0, "Eletrônicos"),
				new ProdutoClassificado("Teclado", 200.0, "Eletrônicos"),
				new ProdutoClassificado("Cadeira", 300.0, "Móveis"),
				new ProdutoClassificado("Monitor", 900.0, "Eletrônicos"),
				new ProdutoClassificado("Mesa", 700.0, "Móveis"));

		List<ProdutoClassificado> outrosProdutos = produtos.stream()
				.filter(p -> p.getCategoria().equals("Eletrônicos") && p.getPreco() < 1000)
				.sorted((p1, p2) -> Double.compare(p1.getPreco(), p2.getPreco()))
				.collect(Collectors.toList());
		outrosProdutos.forEach(System.out::println);
		System.out.println(
				"\n#8 Tomando o mesmo código do exercício anterior como base, modifique o código para que a saída mostre apenas os três produtos mais baratos da categoria \"Eletrônicos\".");
		List<ProdutoClassificado> maisProdutos = produtos.stream()
				.filter(p -> p.getCategoria().equals("Eletrônicos"))
				.sorted((p1, p2) -> Double.compare(p1.getPreco(), p2.getPreco()))
				.limit(3)
				.collect(Collectors.toList());
		maisProdutos.forEach(System.out::println);

	}

	public void somar() {
		// utilizando interface funcional, o que fora substituido por lambda
		Operacao soma = new Operacao() {
			public int executar(int a, int b) {
				return a * b;
			}
		};
		System.out.println(soma.executar(4, 27));
	}

	public void contagem() {
		var leitura = new Scanner(System.in);
		var i = leitura.nextInt();
		System.out.println("Contando até " + i);
		for (int j = 1; j <= i; j++) {
			System.out.print(j + " ");
		}
	}

	public void criarTarefas() {
		var tarefa = new Tarefa("assistir aula 1", false, "João");
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new File("tarefa.json"), tarefa);
			System.out.println("Dados salvos no arquivo tarefa.json!");
		} catch (Exception e) {
			System.out.println("Falha:" + e);
		}
	}

	public void lerTarefas() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			var tarefaLida = objectMapper.readValue(new File("tarefa.json"), Tarefa.class);
			System.out.println("Tarefa lida do JSON:");
			System.out.println(tarefaLida);
		} catch (Exception e) {
			System.out.println("Falha:" + e);
		}
	}

	// Função para verificar se um número é primo
	private static boolean ehPrimo(int numero) {
		if (numero < 2)
			return false; // Números menores que 2 não são primos
		for (int i = 2; i <= Math.sqrt(numero); i++) {
			if (numero % i == 0) {
				return false; // Divisível por outro número que não 1 e ele mesmo
			}
		}
		return true;
	}
}
