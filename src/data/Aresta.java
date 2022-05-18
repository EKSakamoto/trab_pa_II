package data;

/*
 * Classe que define a estrutura de uma aresta
 * 
 * @author Eduardo Sakamoto
 */

	public class Aresta {

		private int verticeOrigem;
		private int verticeDestino;
		private int peso;	// Número inteiro ou número real
		
		public Aresta() {
			
		}
		
		/*
		 * Construtor de inicialização de uma aresta
		 * 
		 * @param verticeOrigem  - Parâmetro referente ao número do vértice de origem
		 * @param verticeDestino - Parâmetro referente ao número do vértice de destino
		 */
		public Aresta(int verticeOrigem, int verticeDestino) {
			this.verticeOrigem = verticeOrigem;
			this.verticeDestino = verticeDestino;
		}
		
		/*
		 * Construtor de inicialização de uma aresta
		 * 
		 * @param verticeOrigem  - Parâmetro referente ao número do vértice de origem
		 * @param verticeDestino - Parâmetro referente ao número do vértice de destino
		 * @param peso			 - Parâmetro referente ao peso da aresta
		 */
		public Aresta(int verticeOrigem, int verticeDestino, int peso) {
			this.verticeOrigem = verticeOrigem;
			this.verticeDestino = verticeDestino;
			this.peso = peso;
		}
		
		/*
		 * Método getter voltado para o número do vértice de origem
		 * 
		 * @return verticeOrigem - Inteiro voltado para o número do vértice de origem
		 */
		public int getVerticeOrigem() {
			return verticeOrigem;
		}
		
		/*
		 * Método setter voltado ao número do vértice de origem
		 * 
		 * @param verticeOrigem - Parâmetro referente ao número do vértice de origem 
		 */
		public void setVerticeOrigem(int verticeOrigem) {
			this.verticeOrigem = verticeOrigem;
		}

		/*
		 * Método getter voltado para o número do vértice de destino
		 * 
		 * @return verticeDestino - Inteiro voltado para o número do vértice de destino
		 */
		public int getVerticeDestino() {
			return verticeDestino;
		}

		/*
		 * Método setter voltado para o número do vértice de destino
		 * 
		 * @param verticeDestino - Parâmetro referente ao número do vértice de destino
		 */
		public void setVerticeDestino(int verticeDestino) {
			this.verticeDestino = verticeDestino;
		}

		/*
		 * Método getter voltado para o peso da aresta
		 * 
		 * @return peso - Inteiro voltado para o peso da aresta
		 */
		public int getPeso() {
			return peso;
		}

		/*
		 * Método setter voltado para o peso da aresta
		 *
		 * @param peso - Parâmetro referente ao peso da aresta
		 */
		public void setPeso(int peso) {
			this.peso = peso;
		}

		/*
		 * Método de igualdade com uma aresta
		 * Pré-condição: Aresta nao nula
		 * Pós-condição: Resultado de comparação
		 * 
		 * @param  a 	   - Parâmetro referente a uma determinada aresta
		 * @return boolean - Booleano referente ao resultado de comparação entre duas arestas
		 */
		public boolean equals(Aresta a) {
			return (this.verticeOrigem == a.getVerticeOrigem() &&
					this.verticeDestino == a.getVerticeDestino() &&
					this.peso == a.getPeso());
		}

		/*
		 * Método auxiliar para representação de aresta no padrão (u,v)
		 * Pré-condição: Nenhum
		 * Pós-condição: Representação (u,v)
		 * 
		 * @return String - Texto voltado para representação da aresta 
		 */
		public String uvRepresentation() {
			
			return "(" + this.getVerticeOrigem() + "," 
					   + this.getVerticeDestino() +
					")";
		}
		
		@Override
		public String toString() {
			return "Aresta [verticeOrigem=" + verticeOrigem + ", verticeDestino=" + verticeDestino + ", peso=" + peso
					+ "]";
		}
	
	}
