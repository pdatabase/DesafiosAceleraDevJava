package br.com.codenation.calculadora;


public class CalculadoraSalario {

	public long calcularSalarioLiquido(double salarioBase) {
		double salarioLiquido = 0.0;

		if(salarioBase >= 1039.00) {
			double salarioSemInss = this.calcularInss(salarioBase);

			salarioLiquido = this.calcularIrpf(salarioSemInss);
		}

		return Math.round(salarioLiquido);
	}

	private double calcularInss(double salarioBase) {
		double pctDesconto;

		if(salarioBase <= 1500.00)
			pctDesconto = 8.0;
		else if(salarioBase >= 1500.01 && salarioBase <= 4000.00)
			pctDesconto = 9.0;
		else // salarioBase > 4000.00
			pctDesconto = 11.0;

		return (salarioBase * (1 - (pctDesconto / 100)));
	}

	private double calcularIrpf(double salario) {
		double pctDesconto;

		if(salario < 3000.00)
			pctDesconto = 0.0; // ISENTO
		else if (salario >= 3000.01 && salario <= 6000.00)
			pctDesconto = 7.5;
		else // salario > 6000.00
			pctDesconto = 15.0;

		return (salario * (1 - (pctDesconto/100)));
	}

}
/*Dúvidas ou Problemas?
Manda e-mail para o meajuda@codenation.dev que iremos te ajudar! 
*/