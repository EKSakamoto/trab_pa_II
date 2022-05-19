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
		
		/*
		 * Construtor de inicialização de um algoritmo em grafo
		 * 
		 * @param grafo 		- Estrutura referente ao grafo a ser processado pelo algoritmo
		 * @param tipoAlgoritmo - Parâmetro referente ao tipo de algoritmo a ser utilizado
		 */
		public AlgoritmoGrafo(Grafo grafo, String tipoAlgoritmo) {
			this.grafo = grafo;
			this.tipoAlgoritmo = tipoAlgoritmo;
		}

		/*
		 * Método getter voltado para estrutura de grafo
		 * 
		 * @return grafo - Estrutura referente ao grafo
		 */
		public Grafo getGrafo() {
			return grafo;
		}

		/*
		 * Método setter voltado para estrutura de grafo
		 * 
		 * @param grafo - Estrutura referente ao grafo
		 */
		public void setGrafo(Grafo grafo) {
			this.grafo = grafo;
		}

		/*
		 * Método getter voltado para tipo de algoritmo
		 * 
		 * @return tipoAlgoritmo - String referente ao tipo de algoritmo
		 */
		public String getTipoAlgoritmo() {
			return tipoAlgoritmo;
		}

		/*
		 * Método setter voltado para tipo de algoritmo
		 * 
		 * @param tipoAlgoritmo - String referente ao tipo de algoritmo
		 */
		public void setTipoAlgoritmo(String tipoAlgoritmo) {
			this.tipoAlgoritmo = tipoAlgoritmo;
		}
		
		/*
		 * Método genérico voltado para impressão de resultado pós-processamento de algoritmo em grafo
		 */
		public void imprimeResultado() {
			
			System.out.println("\t===========================================");
			System.out.println("\t| Resultado de Processamento de Algoritmo |");
			System.out.println("\t===========================================");
			System.out.println("\t");
		}
		
		/*
		 * Método implementado do RUnnable, utilizado para incializar execução de algoritmo de grafo
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub	
		}
		
	}
