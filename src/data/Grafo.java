package data;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import util.SorterAresta_VerticeOrigem;
import util.SorterVertice_NroVertice;

/*
 * Classe que define a estrutura de um grafo
 * 
 * @author Eduardo Sakamoto
 */

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

		/*
		 * Método que define a lista de vértices do grafo
		 * Pré-condição: Lista de arestas do grafo não nulo
		 * Pós-condição: Definição de lista ordenada de vértices
		 */
		public void defineVertice() {
			
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
		
		/*
		 * Método que verifica a presença de um determinado vértice numa lista de vértices
		 * Pré-condição: Vértice e lista de vértices não nulos
		 * Pós-condição: Booleano de presença do vértice na lista
		 */
		public boolean containsVertice(ArrayList<Vertice> list, Vertice target) {
			
			for(Vertice v : list) {
				if(v.getNroVertice() == target.getNroVertice())		return true;
			}
			return false;
		}
		
		/*
		 * Método que define o mapa de vértices adjacentes para cada vértice do grafo
		 * Pré-condição: Lista de vértices não nulo
		 * Pós-condição: Definição de mapa de adjacência
		 */
		public void defineMapaAdjacencia() {
			
			for(Vertice v : listaVertice) {
				mapaAdjacencia.put(v,v.getVerticeAdjacente(listaVertice));
			}
		}
		
		/*
		 * Método que define o mapa de arestas incidentes para cada vértice do grafo
		 * Pré-condição: Lista de vértices não nulo
		 * Pós-condição: Definição de mapa de incidência
		 */
		public void defineMapaIncidencia() {
			
			for(Vertice v : listaVertice) {
				mapaIncidencia.put(v,v.getListaArestaIncidente());
			}
		}
		
		/*
		 * Método que busca por um determinado vértice no grafo
		 * Pré-condição: Lista de vértices não nulo
		 * Pós-condição: Resultado de busca de um determinado vértice
		 */
		public Vertice getVerticeEspecifico(int nroVertice) {
			
			for(Vertice v : this.getListaVertice()) {
				if(v.getNroVertice() == nroVertice)		return v;
			}
			return null;
		}
		
		/*
		 * Método que busca por uma determinada aresta no grafo
		 * Pré-condição: Vértice u, v e mapa de incidência não nulos
		 * Pós-condição: Resultado de busca de uma determinada aresta
		 */
		public Aresta getArestaEspecifica(Vertice u, Vertice v) {
			
			for(Aresta a : this.getMapaIncidencia().get(u)) {
				if(a.getVerticeOrigem() == u.getNroVertice() &&
				   a.getVerticeDestino() == v.getNroVertice()) {
					return a;
				}
			}
			return null;
		}
		
		/*
		 * Método auxiliar que realiza a impressão das informações do grafo
		 * Pré-condição: Grafo não nulo
		 * Pós-condição: Impressão de informações do grafo
		 */
		public String getGrafoInfo() {
			
			StringBuilder str = new StringBuilder();
			str.append("Grafo Orientado = ");
			if(this.isOrientado())	str.append("Sim");
			else					str.append("Não");
			str.append("\n")
			   .append("Quantidade de Vértices = ")
			   .append(this.getQtdVertice());
			str.append("\n============================================");
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
				str.append("============================================");
			}
			return str.toString();
		}
	}
