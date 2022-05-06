package algoritmo;

import data.Grafo;

	public class AlgoritmoGrafo implements Runnable{

		private Grafo grafo;
		private String tipoAlgoritmo;
		
		public AlgoritmoGrafo() {
			this.grafo = null;
			this.tipoAlgoritmo = "";
		}
		
		public AlgoritmoGrafo(Grafo grafo, String tipoAlgoritmo) {
			this.grafo = grafo;
			this.tipoAlgoritmo = tipoAlgoritmo;
		}

		public Grafo getGrafo() {
			return grafo;
		}

		public void setGrafo(Grafo grafo) {
			this.grafo = grafo;
		}

		public String getTipoAlgoritmo() {
			return tipoAlgoritmo;
		}

		public void setTipoAlgoritmo(String tipoAlgoritmo) {
			this.tipoAlgoritmo = tipoAlgoritmo;
		}
		
		public void imprimeResultado() {
			// Relizar a impressão dos resultados para cada classe de algoritmo distinto
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub	
		}
		
	}
