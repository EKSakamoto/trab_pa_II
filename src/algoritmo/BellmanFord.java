package algoritmo;

import java.util.ArrayList;

import data.Aresta;
import data.Grafo;
import data.Vertice;
import util.ErrorCodes;
import util.SorterAresta_VerticeOrigem;

/*
 * Classe que implementa o algoritmo 'Menor Caminho: Bellman-Ford'
 * 
 * @author Eduardo Sakamoto
 */

	public class BellmanFord extends AlgoritmoGrafo{

		private Vertice verticeOrigem;
		private int[] d;
		private int[] predecessor;
		private ArrayList<Aresta> listaAresta;
		
		public BellmanFord() {
			super();
		}
		
		/*
		 * Construtuor de inicialização para o algoritmo 'Menor Caminho: Bellman-Ford'
		 * 
		 * @param grafo   - Parâmetro referente ao grafo a ser processado pelo algoritmo
		 * @param vertice - Parâmetro referente ao número do vértice de origem, no contexto do algoritmo
		 */
		public BellmanFord(Grafo grafo, int vertice) {
			super(grafo,"Menor Caminho - Bellman-Ford");
			verticeOrigem = grafo.getVerticeEspecifico(vertice);
			d = new int[grafo.getQtdVertice()];
			predecessor = new int[grafo.getQtdVertice()];
			listaAresta = new ArrayList<>();
		}
		
		/*
		 * Método que realiza a definição da lista de arestas, ordenada em ordem numerológica
		 * Pré-condição: Listas de vértices e arestas Adjacentes não vazia
		 * Pós-condição: Atribuição de lista de arestas ordenadas
		 */
		public void defineListaAresta() {		
			
			for(Vertice v : this.getGrafo().getListaVertice()) {
				listaAresta.addAll(v.getListaArestaIncidente());
			}
			listaAresta.sort(new SorterAresta_VerticeOrigem());
		}
		
		/*
		 * Método de relaxação...
		 * Pré-condição: Entrada de aresta não nula
		 * Pós-condição: Atualização de vetores de distância e predecessor
		 * 
		 * @param a - Parâmetro referente a uma determinada aresta do grafo
		 */
		public void relax(Aresta a) {
			
			if(d[a.getVerticeOrigem()] != Integer.MAX_VALUE && 
			   d[a.getVerticeDestino()] > (d[a.getVerticeOrigem()] + a.getPeso())) {
				d[a.getVerticeDestino()] = d[a.getVerticeOrigem()] + a.getPeso();
				predecessor[a.getVerticeDestino()] = a.getVerticeOrigem();
			}
		}
		
		/*
		 * Método de inicialização de estruturas essenciais para execução do algoritmo
		 * Pré-condição: Grafo não nulo
		 * Pós-condição: Definição de estruturas essenciais
		 */
		public void inicializaOrigem() {
			
			defineListaAresta();
			for(Vertice v : getGrafo().getListaVertice()) {
				d[v.getNroVertice()] = Integer.MAX_VALUE;
				predecessor[v.getNroVertice()] = -1;
			}
			d[verticeOrigem.getNroVertice()] = 0;
		}
		
		/*
		 * Método que realiza a execução do algoritmo Bellman-Ford
		 * Pré-condição: Grafo não nulo e grafo deve ser orientado
		 * Pós-condição: Execução do algoritmo, podendo verificar existência de ciclo negativo
		 * 
		 * @return boolean - Booleano referente a existência de ciclo negativo no grafo
		 */
		public boolean bellmanFord() {
			
			inicializaOrigem();
			for(int i = 0 ; i < this.getGrafo().getQtdVertice() ; i++) {
				for(Aresta a : listaAresta) {
					relax(a);
				}
			}
			for(Aresta a : this.getGrafo().getListaAresta()) {
				if(d[a.getVerticeDestino()] > d[a.getVerticeOrigem()] + a.getPeso())	return false;
			}
			
			return true;
			/*
			 * figura1: Origem Vértice 1
			 * Destino: 0 Dist.: -3 Caminho: 1 - 3 - 0
			 * Destino: 1 Dist.: 0  Caminho: 1 
			 * Destino: 2 Dist.: -7 Caminho: 1 - 3 - 0 - 2
			 * Destino: 3 Dist.: -5 Caminho: 1 - 3
			 * Destino: 4 Dist.: 0  Caminho: 1 - 3 - 0 - 2 - 4
			 */
			/*
			 * testeBellmanFord : Origem Vértice 0
			 * Destino: 0 Dist.: 0 Caminho: 0
			 * Destino: 1 Dist.: 8 Caminho: 0 - 4 - 1
			 * Destino: 2 Dist.: 5 Caminho: 0 - 4 - 1 - 2
			 * Destino: 3 Dist.: 2 Caminho: 0 - 4 - 3
			 * Destino: 4 Dist.: 3 Caminho: 0 - 4
			 * Destino: 5 Dist.: 2 Caminho: 0 - 4 - 5
			 * Destino: 6 Dist.: 7 Caminho: 0 - 4 - 6
			 * Destino: 7 Dist.: 0 Caminho: 0 - 4 - 3 - 5 - 7
			 */
			
		}
		
		/*
		 * Método recursivo que realiza a construção de caminho, a partir de um determinado vértice de 'origem'
		 * Pré-condição: Número de vértice deve estar contido na lista de vértices 
		 * Pós-condição: Definição de caminho
		 * 
		 * @param  nroVertice - Parâmetro referente ao número de um determinado vértice do grafo
		 * @return String     - Texto referente ao menor caminho, a partir do vértice parametrizado
		 */
		public String defineCaminho(int nroVertice) {
			
			if(predecessor[nroVertice] == -1 &&
			   nroVertice != verticeOrigem.getNroVertice()) {
				return "Não Existe Caminho p/ Vertice " + nroVertice;
			}else {
				return constroiCaminho(nroVertice);
			}
		}
		
		/*
		 * Método que define o início do caminho de um determinado vértice
		 * Pré-condição: Número de vértice deve estar contido na lista de vértices 
		 * Pós-condição: Definição de caminho
		 * 
		 * @param  nroVertice - Parâmetro referente ao número de um determinado vértice do grafo
		 * @return String     - Texto referente ao menor caminho, a partir do vértice parametrizado
		 */
		public String constroiCaminho(int nroVertice) {
			
			String caminho = String.valueOf(nroVertice);
			if(predecessor[nroVertice] == -1)		return caminho;
			return constroiCaminho(predecessor[nroVertice]) + " - " + caminho;
		}
		
		/*
		 * Método que realiza a impressão do resultado para o algoritmo Bellman-Ford, conforme instruído na especificação do trabalho
		 * Pré-condição: Execução de algoritmo já realizada
		 * Pós-condiçao: Impressão de resultado no console
		 */
		@Override
		public void imprimeResultado() {

			try {
				StringBuilder str = new StringBuilder();
				str.append("origem: ").append(verticeOrigem.getNroVertice()).append("\n");
				for(Vertice v : this.getGrafo().getListaVertice()) {
					str.append("destino: ").append(v.getNroVertice()).append("\t")
					   .append("dist.: ").append(d[v.getNroVertice()]).append("\t")
					   .append("caminho: ").append(defineCaminho(v.getNroVertice()))
					   .append("\n");
				}
				System.out.println(str.toString());
			}catch(Exception e) {
				// e.printStackTrace();
			}
		}
		
		/*
		 * Método implementado do Runnable, responsável pela execução do algoritmo Bellman-Ford
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(bellmanFord())	imprimeResultado();
			else {
				System.err.println(ErrorCodes.NEGATIVE_CYCLE_BELLMAN_FORD.getMessage());
			}
		}
		
	}
