package Mundo;

public class Neuronio {
	private int[][] entradas = {
		{1,1,1,1},
		{1,1,1,0},
		{1,1,0,1},
		{1,1,0,0},
		{1,0,1,1},
		{1,0,1,0},
		{1,0,0,1},
		{1,0,0,0},
	};
	private double[] pesosAnterior = {0.0, 0.0, 0.0, 0.0};
	private double[] pesos = {0.0, 0.0, 0.0, 0.0};
	private static boolean VALIDACAO = true;
	private double limiar;
	public double taxaAprendizado;
	
	public Neuronio(double limiar, double taxaAprendizado) {
		this.limiar = limiar;
		this.taxaAprendizado = taxaAprendizado;
	}
	
	public int perceptor() {
		double yent = 0.0;
		int hebb = 0;
		int cont = -1;
		while(VALIDACAO) {
			//System.out.println("contador" + cont++);
			//Verifica a variação dos pesos
			for(int i=0; i<this.pesos.length; i++) {
				VALIDACAO = !(VALIDACAO && (this.pesos[i] == this.pesosAnterior[i]));
				if(VALIDACAO) {
					System.out.println("Parou!");
					break;
				}
					
					
			}
			
			//somatorio das entradas no neuronio
			for(int i=0; i<this.entradas.length; i++) {
				for(int j=0; j<this.entradas[0].length; j++) {
					yent += this.entradas[i][j] * this.pesos[j];
				}
			}
			
			//calculo da saída do neuronio
			hebb = this.hebb(yent);
			
			//redistribuir peso
			for(int i=0; i<this.entradas.length; i++) {
				for(int j=0; j<this.entradas[0].length; j++) {
					this.pesosAnterior[j] = this.pesos[j];
					this.pesos[j] += (this.pesos[j] - this.atualizaPesos(hebb, this.limiar, this.entradas[i][j]));
				}
				
			}
		}
		return 0;
	}
	
	private int hebb(double yent) {
		if(yent>this.limiar) 
			return 1;
		else if(yent<-this.limiar)
			return -1;
		else
			return 0;
	}
	
	private double atualizaPesos(double hebb, double limiar, int xis) {
		System.out.println(hebb);
		if(hebb != limiar) {
			return this.taxaAprendizado * (this.limiar - hebb) * xis;
		}
		return hebb;
	}
}
