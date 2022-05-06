package data;

import java.util.ArrayList;

import util.SorterVertice_NroVertice;

	public class Vertice {

		private int nroVertice;
		private ArrayList<Aresta> listaArestaIncidente;
		
		public Vertice() {
			this.nroVertice = -1;
			listaArestaIncidente = null;
		}
		
		public Vertice(int nroVertice) {
			this.nroVertice = nroVertice;
			this.listaArestaIncidente = new ArrayList<>();
		}
		
		public int getNroVertice() {
			return nroVertice;
		}

		public void setNroVertice(int nroVertice) {
			this.nroVertice = nroVertice;
		}

		public ArrayList<Aresta> getListaArestaIncidente() {
			return listaArestaIncidente;
		}

		public void setListaArestaIncidente(ArrayList<Aresta> listaArestaIncidente) {
			this.listaArestaIncidente = listaArestaIncidente;
		}

		public void defineArestaIncidente(ArrayList<Aresta> listaAresta, boolean grafoOrientado) {
			
			try {
				ArrayList<Aresta> incidente = new ArrayList<>();
				for(Aresta a : listaAresta) {
					if(!incidente.contains(a)) {
						if(this.nroVertice == a.getVerticeOrigem())		incidente.add(a);
						else {
							if(this.nroVertice == a.getVerticeDestino() &&
							   !grafoOrientado) {
								incidente.add(new Aresta(a.getVerticeDestino(),a.getVerticeOrigem(),a.getPeso()));
							}
						}
					}
				}
				this.listaArestaIncidente = incidente;
				// TODO ORDENAR EM ORDEM NUMERICA?
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public ArrayList<Vertice> getVerticeAdjacente(ArrayList<Vertice> listaTotal){
			
			ArrayList<Vertice> list = new ArrayList<>();
			for(Aresta a : listaArestaIncidente) {
				for(Vertice v : listaTotal) {
					if(a.getVerticeDestino() == v.getNroVertice() && !list.contains(v)) {
						list.add(v);
					}
				}
			}
			list.sort(new SorterVertice_NroVertice());
			return list;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "Vertice[nroVertice = " + this.nroVertice + "]";
		}
	}
