package util;

import java.util.Comparator;

import data.Aresta;

	public class SorterAresta_Peso implements Comparator<Aresta>{
	
		@Override
		public int compare(Aresta o1, Aresta o2) {
			return o1.getPeso() - o2.getPeso();
		}
				
	}
