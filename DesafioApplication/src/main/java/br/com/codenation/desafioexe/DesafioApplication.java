package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {


	public static List<Integer> fibonacci() {
		List<Integer> fibonacciList = new ArrayList<>();
		Integer sum = 0;
		Integer stopAt = 377;

		fibonacciList.add(0);
		fibonacciList.add(1);

		//A série de Fibonacci é a seguinte: 0, 1, 1, 2, 3, 5, 8, 13, etc…

		while (sum < stopAt) {
			sum = fibonacciList.get(fibonacciList.size() - 2) + fibonacciList.get(fibonacciList.size() - 1);

			fibonacciList.add(sum);
		}


		return fibonacciList;
	}

	public static Boolean isFibonacci(Integer a) {
		List<Integer> fibonacciList;

		fibonacciList = DesafioApplication.fibonacci();

		return fibonacciList.contains(a);
	}

}