package sorter;

import java.util.Comparator;

import data.Aresta;

/**
 * Classe auxiliar de ordenação de lista de arestas, com base nos pesos das arestas
 * 
 * @author Eduardo Sakamoto
 */

	public class SorterAresta_Peso implements Comparator<Aresta>{
	
		@Override
		public int compare(Aresta o1, Aresta o2) {
			return o1.getPeso() - o2.getPeso();
		}	
	}