package tp.pr0;

public class FuncsMatematicas {
	/**
	 * 
	 * @param n
	 * @return factorial de n
	 */
	public static int factorial(int n){
		int aux = (n < 0 ? 0 : 1); //Inicializo y devuelvo 0 si negativo
		
		for(int i = 1; i <= n; i++){
			aux *= i;
		}		
		return aux;
	}
	/**
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public static int combinatorio(int n, int k){
	int salida = 0; // 0 por defecto
		if(0 <= k){
			if(k <= n){
				long factN = factorial(n);
				long factK = factorial(k);
				long factN_min_K = factorial(n-k);
				salida = (int) (factN / (factK * factN_min_K));
			}		
		}
	return salida;
	}
}
