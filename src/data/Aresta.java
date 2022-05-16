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
		
		public Aresta(int verticeOrigem, int verticeDestino) {
			this.verticeOrigem = verticeOrigem;
			this.verticeDestino = verticeDestino;
		}
		
		public Aresta(int verticeOrigem, int verticeDestino, int peso) {
			this.verticeOrigem = verticeOrigem;
			this.verticeDestino = verticeDestino;
			this.peso = peso;
		}
		
		public int getVerticeOrigem() {
			return verticeOrigem;
		}
		
		public void setVerticeOrigem(int verticeOrigem) {
			this.verticeOrigem = verticeOrigem;
		}

		public int getVerticeDestino() {
			return verticeDestino;
		}

		public void setVerticeDestino(int verticeDestino) {
			this.verticeDestino = verticeDestino;
		}

		public int getPeso() {
			return peso;
		}

		public void setPeso(int peso) {
			this.peso = peso;
		}

		/*
		 * Método de igualdade com uma aresta
		 * Pré-condição: Aresta nao nula
		 * Pós-condição: Resultado de comparação
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
