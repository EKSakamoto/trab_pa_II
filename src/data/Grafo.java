package data;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import sorter.SorterAresta_VerticeOrigem;
import sorter.SorterVertice_NroVertice;

/**
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
	
		/**
		 * Construtor de inicialização de um grafo
		 * 
		 * @param nomeGrafo   - Parâmetro de texto referente ao nome do grafo
		 * @param orientado   - Parâmetro referente a orientação do grafo
		 * @param qtdVertice  - Parâmetro referente a quantidade total de vértices do grafo
		 * @param listaAresta - Parâmetro referente a lista de arestas presentes no grafo
		 */
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
			
		/**
		 * Método getter voltado para nome do grafo
		 * 
		 * @return Texto referente ao nome do grafo
		 */
		public String getNomeGrafo() {
			return nomeGrafo;
		}

		/**
		 * Método setter referente ao nome do grafo
		 * 
		 * @param nomeGrafo - Parâmetro referente ao nome do grafo
		 */
		public void setNomeGrafo(String nomeGrafo) {
			this.nomeGrafo = nomeGrafo;
		}

		/**
		 * Método getter voltado para quantidade de vértices do grafo
		 * 
		 * @return Inteiro referente a quantidade de vértices do grafo
		 */
		public int getQtdVertice() {
			return qtdVertice;
		}

		/**
		 * Método setter referente a quantidade de vértices do grafo
		 * 
		 * @param qtdVertice - Parâmetro referente a quantidade de vértices do grafo
		 */
		public void setQtdVertice(int qtdVertice) {
			this.qtdVertice = qtdVertice;
		}

		/**
		 * Método getter referente a orientação do grafo
		 * 
		 * @return Booleano voltado para orientação do grafo
		 */
		public boolean isOrientado() {
			return orientado;
		}

		/**
		 * Método setter referente a orientação do grafo
		 * 
		 * @param orientado - Parâmetro referente a orientação do grafo
		 */
		public void setOrientado(boolean orientado) {
			this.orientado = orientado;
		}

		/**
		 * Método getter voltado para lista de vértices do grafo
		 * 
		 * @return Lista de vértices do grafo
		 */
		public ArrayList<Vertice> getListaVertice() {
			return listaVertice;
		}

		/**
		 * Método setter referente a lista de vértices do grafo
		 * 
		 * @param listaVertice - Parâmetro referente a lista de vértices do grafo
		 */
		public void setListaVertice(ArrayList<Vertice> listaVertice) {
			this.listaVertice = listaVertice;
		}

		/**
		 * Método getter voltado para lista de vértices do grafo
		 * 
		 * @return Lista de arestas do grafo
		 */
		public ArrayList<Aresta> getListaAresta() {
			return listaAresta;
		}

		/**
		 * Método setter referente a lista de arestas do grafo
		 * 
		 * @param listaAresta - Parâmetro referente a lista de arestas do grafo
		 */
		public void setListaAresta(ArrayList<Aresta> listaAresta) {
			this.listaAresta = listaAresta;
		}

		/**
		 * Método getter voltado para mapa de adjacência do grafo (lista de vértices adjacentes de cada vértice)
		 * 
		 * @return Estrutura auxiliar do mapa de adjacência do grafo
		 */
		public LinkedHashMap<Vertice, ArrayList<Vertice>> getMapaAdjacencia() {
			return mapaAdjacencia;
		}

		/**
		 * Método setter referente ao mapa de adjacência do grafo (lista de vértices adjacentes para cada vértice)
		 * 
		 * @param mapaAdjacência - Parâmetro referente a estrutura do mapa de adjacência
		 */
		public void setMapaAdjacencia(LinkedHashMap<Vertice, ArrayList<Vertice>> mapaAdjacencia) {
			this.mapaAdjacencia = mapaAdjacencia;
		}

		/**
		 * Método getter voltado para o mapa de incidência do grafo (lista de arestas incidentes para cada vértice)
		 * 
		 * @return Estrutura auxiliar do mapa de incidência do grafo
		 */
		public LinkedHashMap<Vertice, ArrayList<Aresta>> getMapaIncidencia() {
			return mapaIncidencia;
		}

		/**
		 * Método setter referente ao mapa de incidência do grafo (lista de arestas incidentes para cada vértice)
		 * 
		 * @param mapaIncidencia - Parâmetro referente a estrutura do mapa de incidência
		 */
		public void setMapaIncidencia(LinkedHashMap<Vertice, ArrayList<Aresta>> mapaIncidencia) {
			this.mapaIncidencia = mapaIncidencia;
		}

		/**
		 * Método que define a lista de vértices do grafo
		 * 
		 * @Precondition  Lista de arestas do grafo não nulo
		 * @Postcondition Definição de lista ordenada de vértices
		 */
		public void defineVertice() {
			
			listaAresta.sort(new SorterAresta_VerticeOrigem());
			ArrayList<Vertice> list = new ArrayList<>();
			for(Aresta a : listaAresta) {
				Vertice u, v;
				u = new Vertice(a.getVerticeOrigem());
				v = new Vertice(a.getVerticeDestino());
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
		
		/**
		 * Método que verifica a presença de um determinado vértice numa lista de vértices
		 * 
		 * @Precondition  Vértice e lista de vértices não nulos
		 * @Postcondition Nenhuma
		 * @param  		  list   - Parâmetro referente a uma lista de vértices
		 * @param  		  target - Parâmetro referente a um determinado vértice
		 * @return 		  Booleano voltado para existência de um determinado vértice na lista de vértices
		 */
		public boolean containsVertice(ArrayList<Vertice> list, Vertice target) {
			
			for(Vertice v : list) {
				if(v.getNroVertice() == target.getNroVertice())		return true;
			}
			return false;
		}
		
		/**
		 * Método que define o mapa de vértices adjacentes para cada vértice do grafo
		 * 
		 * @Precondition  Lista de vértices não nulo
		 * @Postcondition Definição de mapa de adjacência
		 */
		public void defineMapaAdjacencia() {
			
			for(Vertice v : listaVertice) {
				mapaAdjacencia.put(v,v.defineVerticeAdjacente(listaVertice));
			}
		}
		
		/**
		 * Método que define o mapa de arestas incidentes para cada vértice do grafo
		 * 
		 * @Precondition  Lista de vértices não nulo
		 * @Postcondition Definição de mapa de incidência
		 */
		public void defineMapaIncidencia() {
			
			for(Vertice v : listaVertice) {
				mapaIncidencia.put(v,v.getListaArestaIncidente());
			}
		}
		
		/**
		 * Método que busca por um determinado vértice no grafo
		 * 
		 * @Precondition  Lista de vértices não nulo
		 * @Postcondition Resultado de busca de um determinado vértice
		 * @param  		  nroVertice - Parâmetro referente ao número do vértice
		 * @return 		  Vértice do grafo, a partir do número do vértice
		 */
		public Vertice getVerticeEspecifico(int nroVertice) {
			
			for(Vertice v : this.getListaVertice()) {
				if(v.getNroVertice() == nroVertice)		return v;
			}
			return null;
		}
		
		/**
		 * Método que busca por uma determinada aresta no grafo
		 * 
		 * @Precondition  Vértice u, v e mapa de incidência não nulos
		 * @Postcondition Resultado de busca de uma determinada aresta
		 * @param  		  u - Parâmetro referente ao vértice de origem
		 * @param  		  v - Parâmetro referente ao vértice de destino
		 * @return 		  Determinada aresta do grafo, dado os vértices parametrizados
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
		
		/**
		 * Método auxiliar que realiza a impressão das informações do grafo
		 * 
		 * @Precondition  Grafo não nulo
		 * @Postcondition Impressão de informações do grafo
		 * @return 		  Texto referente às informações do grafo
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