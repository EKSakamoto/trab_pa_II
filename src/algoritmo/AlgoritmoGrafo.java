package algoritmo;

import data.Grafo;

/*
 * Classe genérica referente aos tipos de algoritmos em grafos
 * 
 * @author Eduardo Sakamoto
 */

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
			
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub	
		}
		
	}
