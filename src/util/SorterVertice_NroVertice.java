package util;

import java.util.Comparator;

import data.Vertice;

	public class SorterVertice_NroVertice implements Comparator<Vertice>{
	
		@Override
		public int compare(Vertice o1, Vertice o2) {
			// TODO Auto-generated method stub
			return o1.getNroVertice() - o2.getNroVertice();
		}
		
	}
