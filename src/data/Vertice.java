package data;

import java.util.ArrayList;

import sorter.SorterAresta_VerticeOrigem;
import sorter.SorterVertice_NroVertice;

/**
 * Classe que define a estrutura de um vértice
 * 
 * @author Eduardo Sakamoto
 */

	public class Vertice {

		private int nroVertice;
		private ArrayList<Aresta> listaArestaIncidente;
		
		/**
		 * Construtor de inicialização de um vértice
		 */
		public Vertice() {
			this.nroVertice = -1;
			listaArestaIncidente = null;
		}
		
		/**
		 * Construtor de inicialização de um vértice
		 * 
		 * @param nroVertice - Parâmetro referente ao número do vértice
		 */
		public Vertice(int nroVertice) {
			this.nroVertice = nroVertice;
			this.listaArestaIncidente = new ArrayList<>();
		}
		
		/**
		 * Método getter voltado para o número do vértice
		 * 
		 * @return Inteiro referente ao número do vértice
		 */
		public int getNroVertice() {
			return nroVertice;
		}
		
		/**
		 * Método setter referente ao número do vértice
		 * 
		 * @param nroVertice - Parâmetro referente ao número do vértice
		 */
		public void setNroVertice(int nroVertice) {
			this.nroVertice = nroVertice;
		}

		/**
		 * Método getter voltado para lista de arestas incidentes de um determinado vértice
		 * 
		 * @return Lista de arestas incidentes de um determinado vértice
		 */
		public ArrayList<Aresta> getListaArestaIncidente() {
			return listaArestaIncidente;
		}

		/**
		 * Método setter referente a lista de arestas incidentes de um determaindo vértice
		 * 
		 * @param listaArestaIncidente - Parâmetro referente a lista de arestas incidentes de um determinado vértice
		 */
		public void setListaArestaIncidente(ArrayList<Aresta> listaArestaIncidente) {
			this.listaArestaIncidente = listaArestaIncidente;
		}

		/**
		 * Método de definição de arestas incidentes para um determinado vértice
		 * 
		 * @Precondition  Lista de arestas não nulo e orientação de grafo definida
		 * @Postcondition Definição de arestas incidentes
		 * @param 		  listaAresta    - Parâmetro referente a lista de arestas do grafo
		 * @param 		  grafoOrientado - Parâmetro referente a orientação do grafo
		 */
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
				incidente.sort(new SorterAresta_VerticeOrigem());
				this.listaArestaIncidente = incidente;
			}catch(Exception e) {
//				e.printStackTrace();
			}
		}
		
		/**
		 * Método de definição de vértices adjacentes para um determinado vértice
		 * 
		 * @Precondition  Lista de vértices e lista de arestas incidentes nao nulos
		 * @Postcondition Definição de vértices adjacentes
		 * @param  		  listaTotal - Parâmetro referente a lista de vértices do grafo
		 * @return 		  Lista de vértices adjacentes 
		 */
		public ArrayList<Vertice> defineVerticeAdjacente(ArrayList<Vertice> listaTotal){
			
			try {
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
			}catch(Exception e) {
//				e.printStackTrace();
				return null;
			}
		}
	}