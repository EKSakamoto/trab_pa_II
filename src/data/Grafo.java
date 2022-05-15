package data;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import util.SorterAresta_VerticeOrigem;
import util.SorterVertice_NroVertice;

	public class Grafo {

		private String nomeGrafo;
		private int qtdVertice;
		private boolean orientado;
		private ArrayList<Vertice> listaVertice = new ArrayList<>();
		private ArrayList<Aresta> listaAresta = new ArrayList<>();
		private LinkedHashMap<Vertice,ArrayList<Vertice>> mapaAdjacencia;
		private LinkedHashMap<Vertice,ArrayList<Aresta>> mapaIncidencia;
		
		public Grafo() {
			
		}
		
		public Grafo(boolean orientado, int qtdVertice, ArrayList<Aresta> listaAresta) {
			mapaAdjacencia = new LinkedHashMap<>();
			mapaIncidencia = new LinkedHashMap<>();
			this.orientado = orientado;
			this.listaAresta = listaAresta;
			this.qtdVertice = qtdVertice;
			defineVertice();
			defineMapaAdjacencia();
			defineMapaIncidencia();
		}
		
		public Grafo(String nomeGrafo, boolean orientado, int qtdVertice, ArrayList<Aresta> listaAresta) {
			mapaAdjacencia = new LinkedHashMap<>();
			mapaIncidencia = new LinkedHashMap<>();
			this.nomeGrafo = nomeGrafo;
			this.orientado = orientado;
			this.listaAresta = listaAresta;
			this.qtdVertice = qtdVertice;
			defineVertice();
			defineMapaAdjacencia();
			defineMapaIncidencia();
		}
			
		public String getNomeGrafo() {
			return nomeGrafo;
		}

		public void setNomeGrafo(String nomeGrafo) {
			this.nomeGrafo = nomeGrafo;
		}

		public int getQtdVertice() {
			return qtdVertice;
		}

		public void setQtdVertice(int qtdVertice) {
			this.qtdVertice = qtdVertice;
		}

		public boolean isOrientado() {
			return orientado;
		}

		public void setOrientado(boolean orientado) {
			this.orientado = orientado;
		}

		public ArrayList<Vertice> getListaVertice() {
			return listaVertice;
		}

		public void setListaVertice(ArrayList<Vertice> listaVertice) {
			this.listaVertice = listaVertice;
		}

		public ArrayList<Aresta> getListaAresta() {
			return listaAresta;
		}

		public void setListaAresta(ArrayList<Aresta> listaAresta) {
			this.listaAresta = listaAresta;
		}

		public LinkedHashMap<Vertice, ArrayList<Vertice>> getMapaAdjacencia() {
			return mapaAdjacencia;
		}

		public void setMapaAdjacencia(LinkedHashMap<Vertice, ArrayList<Vertice>> mapaAdjacencia) {
			this.mapaAdjacencia = mapaAdjacencia;
		}

		public LinkedHashMap<Vertice, ArrayList<Aresta>> getMapaIncidencia() {
			return mapaIncidencia;
		}

		public void setMapaIncidencia(LinkedHashMap<Vertice, ArrayList<Aresta>> mapaIncidencia) {
			this.mapaIncidencia = mapaIncidencia;
		}

		public void defineVertice() {
			
			// TODO Realizar definição, dependendo da orientação do grafo
			listaAresta.sort(new SorterAresta_VerticeOrigem());
			ArrayList<Vertice> list = new ArrayList<>();
			for(Aresta a : listaAresta) {
				Vertice u, v;
				u = new Vertice(a.getVerticeOrigem());
				v = new Vertice(a.getVerticeDestino());
				// Verificar confiabilidade de definição de arestas incidentes para grafos não orientados
				u.defineArestaIncidente(listaAresta,orientado);
				v.defineArestaIncidente(listaAresta,orientado);
				if(!containsVertice(list,u))		list.add(u);
				if(!containsVertice(list,v))		list.add(v);
			}
			for(Vertice v : list) {
				v.getListaArestaIncidente().sort(new SorterAresta_VerticeOrigem());
			}
			list.sort(new SorterVertice_NroVertice());
			this.listaVertice = list;
		}
		
		public boolean containsVertice(ArrayList<Vertice> list, Vertice target) {
			
			for(Vertice v : list) {
				if(v.getNroVertice() == target.getNroVertice())		return true;
			}
			return false;
		}
		
		public void defineMapaAdjacencia() {
			
			for(Vertice v : listaVertice) {
				mapaAdjacencia.put(v,v.getVerticeAdjacente(listaVertice));
			}
		}
		
		public void defineMapaIncidencia() {
			
			for(Vertice v : listaVertice) {
				mapaIncidencia.put(v,v.getListaArestaIncidente());
			}
		}
		
		public Vertice getVerticeEspecifico(int nroVertice) {
			
			for(Vertice v : this.getListaVertice()) {
				if(v.getNroVertice() == nroVertice)		return v;
			}
			return null;
		}
		
		public Aresta getArestaEspecifica(Vertice u, Vertice v) {
			
			for(Aresta a : this.getMapaIncidencia().get(u)) {
				if(a.getVerticeOrigem() == u.getNroVertice() &&
				   a.getVerticeDestino() == v.getNroVertice()) {
					return a;
				}
			}
			return null;
		}
		
		public String getGrafoInfo() {
			
			StringBuilder str = new StringBuilder();
			str.append("Grafo Orientado = ");
			if(this.isOrientado())	str.append("Sim");
			else					str.append("Não");
			str.append("\n")
			   .append("Quantidade de Vértices = ")
			   .append(this.getQtdVertice());
			for(Vertice v : this.getListaVertice()) {
				str.append("\nVertice ").append(v.getNroVertice()).append("\n");
				str.append("Arestas Incidentes = ");
				for(Aresta a : v.getListaArestaIncidente()) {
					str.append("(").append(a.getVerticeOrigem())
					   .append(",")
					   .append(a.getVerticeDestino()).append(")")
					   .append(":").append(a.getPeso())
					   .append(" | ");
				}
			}
			return str.toString();
		}
	}
