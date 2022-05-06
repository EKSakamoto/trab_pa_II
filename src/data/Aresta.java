package data;

	public class Aresta {

		private int verticeOrigem;
		private int verticeDestino;
		private int peso;	// Número inteiro ou número real
		
		public Aresta() {
			
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

		public boolean equals(Aresta a) {
			return (this.verticeOrigem == a.getVerticeOrigem() &&
					this.verticeDestino == a.getVerticeDestino() &&
					this.peso == a.getPeso());
		}

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
