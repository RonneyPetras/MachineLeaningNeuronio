package Mundo;

public class Main {

	public static void main(String[] args) {
		Neuronio n = new Neuronio();
			n.setLimiar(1);
			n.setTaxaAprendizado(0.2);
			n.perceptor();
	}

}
