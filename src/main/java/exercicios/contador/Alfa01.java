package exercicios.contador;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class Alfa01 {
    public void leiteIntegral() {
		/*
		 * 1 - Imagine que você tem uma lista de strings.
		 * Algumas das strings são números, mas outras não.
		 * Queremos converter a lista de strings para uma lista de números.
		 * Se a conversão falhar, você deve ignorar o valor.
		 * Por exemplo, na lista a seguir, a saída deve ser [10, 20]:
		 */
		List<String> input = Arrays.asList("10", "abc", "20", "30x");
		List<Integer> numbers = input.stream()
				.map(str -> {
					try {
						return Optional.of(Integer.parseInt(str));
					} catch (NumberFormatException e) {
						return Optional.<Integer>empty();
					}
				}).filter(Optional::isPresent)
				.map(Optional::get)
				.toList();// .stream().forEach(System.out::println);;
		numbers.stream().forEach(System.out::println);
		input.stream().forEach(System.out::println);

		/*
		 * 2 - Implemente um método que recebe um número inteiro dentro de um Optional.
		 * Se o número estiver presente e for positivo, calcule seu quadrado.
		 * Caso contrário, retorne Optional.empty.
		 */
		System.out.println(processaNumero(Optional.of(5))); // Saída: Optional[25]
		System.out.println(processaNumero(Optional.of(-3))); // Saída: Optional.empty
		System.out.println(processaNumero(Optional.empty())); // Saída: Optional.empty

		/*
		 * 3 - Implemente um método que recebe uma String representando um nome completo
		 * separado por espaços.
		 * O método deve retornar o primeiro e o último nome após remover os espaços
		 * desnecessários.
		 */
		System.out.println(obterPrimeiroEUltimoNome("  João Carlos Silva   ")); // Saída: "João Silva"
		System.out.println(obterPrimeiroEUltimoNome("Maria   ")); // Saída: "Maria"
		// num fiz

		/*
		 * 4 - Implemente um método que verifica se uma frase é um palíndromo.
		 * Um palíndromo é uma palavra/frase que, quando lida de trás pra frente, é
		 * igual à leitura normal.
		 * Um exemplo:
		 */
		System.out.println(ehPalindromo("socorram me subi no onibus em marrocos")); // Saída: true
		System.out.println(ehPalindromo("Java")); // Saída: false
		// num dfiz

		/*
		 * 5 - Implemente um método que recebe uma lista de e-mails (String) e retorna
		 * uma nova lista onde cada e-mail está convertido para letras minúsculas.
		 */
		List<String> emails = Arrays.asList("TESTE@EXEMPLO.COM", "exemplo@Java.com ", "Usuario@teste.Com");
		System.out.println(normalizeEmails(emails));

		/*
		 * 6 - Crie um enum Mes que represente os meses do ano.
		 * Adicione um método que retorna o número de dias de cada mês, considerando
		 * anos não bissextos.
		 */
		System.out.println(Mes.FEVEREIRO.getNumeroDeDias()); // 28
		System.out.println(Mes.JULHO.getNumeroDeDias()); // 31

		/*
		 * 7 - Crie um enum Moeda com valores como DOLAR, EURO, REAL.
		 * Cada moeda deve ter uma taxa de conversão para reais.
		 * Adicione um método que recebe um valor em reais e retorna o valor convertido
		 * para a moeda.
		 */
		System.out.println(Moeda.DOLAR.converterPara(100)); // 19.60 (aproximado)
		System.out.println(Moeda.EURO.converterPara(100)); // 18.18 (aproximado)

		/*
		 * 8 - Crie um enum CodigoErro com valores de erros HTTP, como NOT_FOUND,
		 * BAD_REQUEST, INTERNAL_SERVER_ERROR.
		 * Cada valor deve ter um código numérico e uma descrição associados.
		 * Adicione métodos para acessar o código e a descrição.
		 * Dica: consulte o site https://http.cat/ para conhecer os vários erros HTTP e
		 * conseguir descrevê-los melhor.
		 */
		System.out.println(CodigoErro.NOT_FOUND.getCodigo()); // 404
		System.out.println(CodigoErro.BAD_REQUEST.getDescricao()); // Requisição inválida
	}

    public static Optional<Integer> processaNumero(Optional<Integer> numero) {
		/*
		 * if (numero.isPresent() && numero.get() >= 0){
		 * return numero;
		 * }
		 * return Optional.<Integer>empty();
		 */
		return numero.filter(n -> n > 0).map(n -> n * n);
	}

	public static String obterPrimeiroEUltimoNome(String nomeCompleto) {
		String[] nomes = nomeCompleto.trim().split(" ");
		if (nomes.length == 1) {
			return nomes[0];
		}
		return nomes[0] + " " + nomes[nomes.length - 1];
	}

	public static boolean ehPalindromo(String palavra) {
		String semEspacos = palavra.replace(" ", "").toLowerCase();
		return new StringBuilder(semEspacos).reverse().toString().equalsIgnoreCase(semEspacos);
	}

	public List<String> normalizeEmails(List<String> emails) {
		return emails.stream()
				.map(email -> email.trim().toLowerCase())
				.toList();
	}

    public enum Mes {
		JANEIRO(31), FEVEREIRO(28), MARCO(31), ABRIL(30),
		MAIO(31), JUNHO(30), JULHO(31), AGOSTO(31),
		SETEMBRO(30), OUTUBRO(31), NOVEMBRO(30), DEZEMBRO(31);

		private final int dias;

		Mes(int dias) {
			this.dias = dias;
		}

		public int getNumeroDeDias() {
			return dias;
		}
	}

	public enum Moeda {
		DOLAR(5.10), EURO(5.50), REAL(1.0);

		private final double taxa;

		Moeda(double taxa) {
			this.taxa = taxa;
		}

		public double converterPara(double valorEmReais) {
			return valorEmReais / taxa;
		}
	}

	public enum CodigoErro {
		NOT_FOUND(404, "Recurso não encontrado"),
		BAD_REQUEST(400, "Requisição inválida"),
		INTERNAL_SERVER_ERROR(500, "Erro interno do servidor");

		private final int codigo;
		private final String descricao;

		CodigoErro(int codigo, String descricao) {
			this.codigo = codigo;
			this.descricao = descricao;
		}

		public int getCodigo() {
			return codigo;
		}

		public String getDescricao() {
			return descricao;
		}
	}
}
