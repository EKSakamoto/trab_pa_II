package data;

import java.util.ArrayList;

/*
 * Classe que define a estrutura do conjunto de vértice(s), voltado para auxiliar na implementação do algoritmo Kruskal
 * 
 * @author Eduardo Sakamoto
 */

	@SuppressWarnings("serial")
	public class ConjuntoVertice extends ArrayList<Vertice>{
	
		public ConjuntoVertice() {
			super();
		}
		
		public ConjuntoVertice(Vertice v) {
			super();
			this.add(v);
		}
		
		public ConjuntoVertice(ArrayList<Vertice> list) {
			super(list);
		}
			
		/*
		 * Método que realiza a operação de união entre dois conjuntos de vértices
		 * Pré-condição: Conjunto de vértice(s) não nulo
		 * Pós-condição: União resultante dos conjuntos
		 */
		public void union(ConjuntoVertice conj) {
			
			// Se não for funcional, utilizar comparação com dois for
			for(Vertice v : conj) {
				if(!this.contains(v))	this.add(v);
			}
		}
		
		/*
		 * Método que verifica a distinção entre dois conjuntos de vértices
		 * Pré-condição: Conjunto de vértice(s) não nulo
		 * Pós-condição: Resultado de distinção
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
