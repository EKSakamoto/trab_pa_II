package data;

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