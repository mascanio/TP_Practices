package tp.pr0;

public class Pr0Main {
	
	public static void escribeSaludo(String nombre){
		System.out.println("Hola, " + nombre);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pr0Main.escribeSaludo("Miguel");
		System.out.println(FuncsMatematicas.factorial(1));
		System.out.println(FuncsMatematicas.combinatorio(1, 1));
	
		for (int i = 0; i < 6; ++i) { 
			for (int j = 0; j <= i; ++j) System.out.print(FuncsMatematicas.combinatorio(i,j) + " "); System.out.println(); 
			}
	}
	
}
