package highOrderFunctions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.sql.rowset.Predicate;

/*
 * Una función de order superior es una función que puede
 * recibir una o dos funciones como parametro de entrada 
 * que puede retornar otra función
 */
public class HighOrderFunctions implements SumaInterface {
	
	// Método Tradicional
	public int suma(int a, int b) {
		return a + b;
	}

	// Método sobreescrito de Interfaz
	@Override
	public int apply(int a, int b) {
		return a + b;
	}
	
	// High Order Function
	public int sumaHOF(SumaInterface suma, int a, int b) {
		return suma.apply(a, b);
	}
	
	public void imprimirMayuscula(Function<String, String> function, String nombre)  {
		System.out.println(function.apply(nombre));
	}

	public Predicate logicaPredicado(int maximoCaracteres) {
		return e -> e.length() < maximoCaracteres;
	}
	
	public void filtrar(List<String> lista, Consumer<String> consumer, int maximoCaracteres) {
		lista.stream().filter(logicaPredicado(maximoCaracteres).forEach(consumer));
	}
	
	public static void main(String[] args) {

		// Forma tradicional
		HighOrderFunctions hof = new HighOrderFunctions();
		// Función
		System.out.println("----- Función -----");
		System.out.println("hof.suma(2, 3): " + hof.suma(2, 3));
		
		// Interfaz
		System.out.println("----- Interfaz -----");
		System.out.println("hof.apply(3, 4): " + hof.apply(3, 4));
		
		// High Order Functions
		System.out.println("----- High Order Functions -----");
		// Forma tradicional
		/*
		 * SumaInterface sumaInterface = new SumaInterface() {
		 * 
		 * @Override public int apply(int a, int b) { return a + b; } };
		 */
		SumaInterface sumaInterface = (a, b) -> a + b;
		System.out.println("HOF: " + sumaInterface.apply(2, 5));
		
		// Interfaz Funcional
		/*
		 * interface Function<T t, R r> { R apply(T t); }
		 */
		Function<String, String> convertirAMayusculas = string -> string.toUpperCase();
		hof.imprimirMayuscula(convertirAMayusculas, "eimer");
		
		// Interfaz Functional BiFunction<T, U, R>
		/*
		 * interfaz BiFunction<T, U, R> { R apply(T t, U u); }
		 */
		
		// Interfaz Functional Predicate<T>
		/*
		 * interface Predicate<T> { Boolean text(T t); }
		 */
		List<Integer> numeros = Arrays.asList(5, -5, 2 -6, 7, -4);
		BiFunction<List<Integer>,
					Predicate,
					List<Integer>> filtrada;
		filtrada = (lista,  predicado) -> lista.stream()
				.filter(e -> predicado.test(e))
				.collect(Collectors.toList());
		System.out.println(filtrada.apply(numeros, e -> e > 0));
		
		// Interfaz Consumer
		/*
		 * interface Consumer<T> { void accept(T t); }
		 */
		List<String> nombres = new ArrayList<>();
		nombres.add("Eimer");
		nombres.add("Alejandra");
		nombres.add("Wilfer");
		nombres.add("Maria");
		hof.filtrar(nombres, e -> System.out.println(e), 5);
		
	}

}
