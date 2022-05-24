package data;

import java.util.ArrayList;

/**
 * Classe que define a estrutura do conjunto de vértice(s), voltado para auxiliar na implementação do algoritmo Kruskal
 * 
 * @author Eduardo Sakamoto
 */

	@SuppressWarnings("serial")
	public class ConjuntoVertice extends ArrayList<Vertice>{
	
		public ConjuntoVertice() {
			super();
		}
		
		/**
		 * Construtor de inicialização de um conjunto de vértice
		 * 
		 * @param v - Parâmetro referente a um único vértice do grafo
		 */
		public ConjuntoVertice(Vertice v) {
			super();
			this.add(v);
		}
		
		/**
		 * Construtor de inicialização de um conjunto de vértice
		 * 
		 * @param list - Parâmetro refernete a uma lista de vértices presentes no grafo
		 */
		public ConjuntoVertice(ArrayList<Vertice> list) {
			super(list);
		}
			
		/**
		 * Método que realiza a operação de união entre dois conjuntos de vértices
		 * 
		 * @Precondition  Conjunto de vértice(s) não nulo
		 * @Postcondition União resultante dos conjuntos
		 * @param 		  conj - Parâmetro referente a um conjunto de vértices
		 */
		public void union(ConjuntoVertice conj) {
			
			for(Vertice v : conj) {
				if(!this.contains(v))	this.add(v);
			}
		}
		
		/**
		 * Método que verifica a distinção entre dois conjuntos de vértices
		 * 
		 * @Precondition  Conjunto de vértice(s) não nulo
		 * @Postcondition Resultado de distinção
		 * @param  		  conj - Parâmetro referente a um conjunto de vértices
		 * @return        Boolean voltado para resultado de distinção de dois conjuntos de vértices
		 */
		public boolean distintoConjuntoVertice(ConjuntoVertice conj) {
			
			for(Vertice v : this) {
				for(Vertice u : conj) {
					if(v.getNroVertice() == u.getNroVertice())	return false;
				}
			}
			return true;
		}	
	}