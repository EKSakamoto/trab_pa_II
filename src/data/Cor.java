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
		
		/*
		 * Construtor de incialização de uma cor
		 * 
		 * @param valor - Parâmetro referente ao valor atribuído a uma cor
		 */
		Cor(int valor) {
			this.valor = valor;
		}
		
		/*
		 * Método getter referente ao valor de uma cor
		 * 
		 * @return valor - Inteiro voltado para valor de uma cor
		 */
		public int getValor() {
			return valor;
		}
	}