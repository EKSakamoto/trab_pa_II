package algoritmo;

import java.util.ArrayList;

import data.Aresta;
import data.Grafo;
import data.Vertice;
import util.SorterAresta_VerticeOrigem;

	public class BellmanFord extends AlgoritmoGrafo{

		private Vertice verticeOrigem;
		private int[] d;
		private int[] predecessor;
		private ArrayList<Aresta> listaAresta;
		
		// TODO Considerar uso de predecessor para montagem de caminho
		public BellmanFord() {
			super();
		}
		
		public BellmanFord(Grafo grafo, int vertice) {
			super(grafo,"Menor Caminho - Bellman-Ford");
			verticeOrigem = grafo.getVerticeEspecifico(vertice);
			d = new int[grafo.getQtdVertice()];
			predecessor = new int[grafo.getQtdVertice()];
			listaAresta = new ArrayList<>();
		}
		
		public void defineListaAresta() {		
			
			for(Vertice v : this.getGrafo().getListaVertice()) {
				for(Aresta a : v.getListaArestaIncidente()) {
					listaAresta.add(a);
				}
			}
			listaAresta.sort(new SorterAresta_VerticeOrigem());
		}
		
		public void relax(Aresta a) {
			
			System.out.println((d[a.getVerticeOrigem()] + a.getPeso()));
			if(d[a.getVerticeDestino()] > (d[a.getVerticeOrigem()] + a.getPeso())) {
				d[a.getVerticeDestino()] = d[a.getVerticeOrigem()] + a.getPeso();
				predecessor[a.getVerticeDestino()] = a.getVerticeOrigem();
			}
		}
		
		public void inicializaOrigem() {
			
			defineListaAresta();
			for(Vertice v : getGrafo().getListaVertice()) {
				d[v.getNroVertice()] = Integer.MAX_VALUE;
				predecessor[v.getNroVertice()] = -1;
			}
			d[verticeOrigem.getNroVertice()] = 0;
		}
		
		public boolean bellmanFord() {
			
			inicializaOrigem();
			System.out.println("Vértice Origem = " + verticeOrigem.getNroVertice());
			for(int i = 0 ; i < this.getGrafo().getQtdVertice() - 1 ; i++) {
				for(Aresta a : listaAresta) {
					System.out.println("Aresta " + a);
					relax(a);
				}
			}
			
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
			
			for(Vertice v : this.getGrafo().getListaVertice()) {
				System.out.println("Vertice " + v.getNroVertice() + " -> " + "Dist. = " + d[v.getNroVertice()] + " | " + "Pred. = " + predecessor[v.getNroVertice()] 
//						+ " | Caminho = " + defineCaminho(v.getNroVertice())
						);
			}
			// System.out.println(gerarResposta());
			
			
			for(Aresta a : this.getGrafo().getListaAresta()) {
				if(d[a.getVerticeOrigem()] > d[a.getVerticeDestino()] + a.getPeso())	return false;
			}
			
			return true;
		}
		
		public String defineCaminho(int nroVertice) {
			
			String caminho = String.valueOf(nroVertice);
			if(predecessor[nroVertice] == -1)		return caminho;
			return defineCaminho(predecessor[nroVertice]) + " - " + caminho;
		}
		
		@Override
		public void imprimeResultado() {

			StringBuilder str = new StringBuilder();
			str.append("origem: ").append(verticeOrigem.getNroVertice()).append("\n");
			for(Vertice v : this.getGrafo().getListaVertice()) {
				str.append("destino: ").append(v.getNroVertice()).append(" ")
				   .append("dist.: ").append(d[v.getNroVertice()]).append(" ")
				   .append("caminho: ").append(defineCaminho(v.getNroVertice()));
			}
			System.out.println(str.toString());
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			bellmanFord();
		}
		
	}
