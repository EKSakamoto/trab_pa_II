package data;

/*
 * Estrutura de cor para auxiliar na implementação dos algoritmos de busca (DFS e BFS)
 * 
 * @author Eduardo Sakamoto
 */

	public enum Cor {
	
		BRANCO(1),
		CINZA(0),
		PRETO(-1);
		
		private int valor;
		
		Cor(int valor) {
			this.valor = valor;
		}
		
		public int getValor() {
			return valor;
		}
	}