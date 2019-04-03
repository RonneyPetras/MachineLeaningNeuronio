package Mundo;

import java.util.Scanner;

public class Neuronio {
	private int[][] entradas = {
		{1,1,1,1},
		{1,1,0,1},
		{1,0,1,1},
		{1,0,0,1},
		{0,1,1,1},
		{0,1,0,1},
		{0,0,1,1},
		{0,0,0,1},
	};

	private double limiar;
	private double yEnt;
	public double taxaAprendizado;
	private int[] valoresEsperadosOR = {1, 1, 1, 1, 1, 1, 1, 0};
	private double[] pesos = {0.0, 0.0, 0.0, 0.0};
	private int[] fYents= new int[8];
	private static boolean VALIDACAO = true;
	private int f;
	
	public void setLimiar(double limiar) {
		this.limiar = limiar;
	}

	public void setTaxaAprendizado(double taxaAprendizado) {
		this.taxaAprendizado = taxaAprendizado;
	}
	
	public void perceptor() {

		while(VALIDACAO) {
			int count = 0;
			for(int i = 0; i < this.entradas.length; i++) {
				
				this.yEnt = 0;
				
				for(int j = 0; j < this.entradas[0].length; j++) {
					this.yEnt += this.entradas[i][j] * this.pesos[j];
				}
				
				f = this.funcaoDoYent(this.yEnt);
				this.fYents[i] = f;
				
				if(f!=this.valoresEsperadosOR[i]) {
					for(int a=0; a<this.pesos.length;a++) {
						this.pesos[a] += this.taxaAprendizado * (this.valoresEsperadosOR[i]-f) * this.entradas[i][a];
					}
				}else {
					count++;
				}
			}
			if(count == 8) {
				VALIDACAO=false;
				System.out.print("Pesos corretos: ");
				for(double peso: this.pesos) {
					System.out.print(peso +" ");
				}
				System.out.println();
				
			}
			
		}
		
		Scanner sc = new Scanner(System.in);
		int[] entradas = new int[3];
		double produto = 0.0;
		for(int i =0;i< entradas.length; i++) {
			System.out.println("Informe numero: ");
			entradas[i] =sc.nextInt();
		}
		
		for( int i =0;i< entradas.length; i++) {
			produto += entradas[i] * this.pesos[i];
		}
		produto += 1 * this.pesos[3];
		System.out.println("Produto " + produto);
		System.out.println("Resposta: ");
		System.out.println(this.funcaoDoYent(produto));
	}
	
	private int funcaoDoYent(double yaet) {
		if(yaet>this.limiar) 
			return 1;
		else if((yaet <= this.limiar) && (yaet >= -this.limiar))
			return 0;
		else
			return -1;
	}
}
