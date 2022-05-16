package util;

import java.util.Comparator;

import data.Aresta;

/*
 * Classe auxiliar de ordenação de lista de arestas, com base nos números de vértice de origem das arestas
 * 
 * @author Eduardo Sakamoto
 */

	public class SorterAresta_VerticeOrigem implements Comparator<Aresta>{

		@Override
		public int compare(Aresta o1, Aresta o2) {
			// TODO Auto-generated method stub
			if((o1.getVerticeOrigem() - o2.getVerticeOrigem()) != 0) {
				return o1.getVerticeOrigem() - o2.getVerticeOrigem();
			}
			return o1.getVerticeDestino() - o2.getVerticeDestino();
		}
		
	
	}
