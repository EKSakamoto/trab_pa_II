package sorter;

import java.util.Comparator;

import data.Vertice;

/**
 * Classe auxiliar de ordenação de lista de vértices, com base nos números dos vértices
 * 
 * @author Eduardo Sakamoto
 */

	public class SorterVertice_NroVertice implements Comparator<Vertice>{
	
		@Override
		public int compare(Vertice o1, Vertice o2) {
			// TODO Auto-generated method stub
			return o1.getNroVertice() - o2.getNroVertice();
		}	
	}