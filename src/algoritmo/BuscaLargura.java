package algoritmo;

import java.util.ArrayList;

import data.Cor;
import data.Grafo;
import data.Vertice;

/**
 * Classe que implementa o algoritmo "Busca em Largura (BFS)"
 * 
 * @author Eduardo Sakamoto
 */

	public class BuscaLargura extends AlgoritmoGrafo{
	
		private Vertice verticeOrigem;
		private ArrayList<Integer> ordemVertice;
		private int[] distancia;
		private Cor[] cor;
		
		public BuscaLargura() {
			super();
		}
		
		/**
		 * Construtor de inicialização para o algoritmo 'Busca em Largura (BFS)'
		 * 
		 * @param grafo			   - Parâmetro referente ao grafo a ser processado
		 * @param nroVerticeOrigem - Parâmetro referente ao número de vértice de origem, no contexto do algoritmo 
		 */
		public BuscaLargura(Grafo grafo, int nroVerticeOrigem) {
			super(grafo,"Busca em Largura (BFS)");
			this.ordemVertice = new ArrayList<>();
			distancia = new int[this.getGrafo().getQtdVertice()];
			cor = new Cor[this.getGrafo().getQtdVertice()];
			this.verticeOrigem = this.getGrafo().getVerticeEspecifico(nroVerticeOrigem);
		}
		
		/**
		 * Método de inicialização de estruturas essenciais para execução do algoritmo
		 * 
		 * @Precondition  Grafo e vértice de origem não nulos
		 * @Postcondition Nenhum
		 */
		public void inicializaBFS() {
			
			for(int i = 0 ; i < this.getGrafo().getQtdVertice() ; i++) {
				cor[i] = Cor.BRANCO;
				distancia[i] = Integer.MAX_VALUE; 		// Alternativa para Infinito (contexto do algoritmo)
			}
			cor[this.verticeOrigem.getNroVertice()] = Cor.CINZA;
			distancia[this.verticeOrigem.getNroVertice()] = 0;
			ordemVertice.add(this.verticeOrigem.getNroVertice());
		}
		
		/**
		 * Método que realiza a execução do algoritmo "Busca em Largura (BFS)"
		 * 
		 * @Precondition  Vértice de origem válido e grafo não nulo
		 * @Postcondition Ordem de visita de vértices definida
		 */
		public void BFS_visit() {
			
			try {
				inicializaBFS();
				ArrayList<Vertice> fila = new ArrayList<>();
				fila.add(this.verticeOrigem);
				while(!fila.isEmpty()) {
					Vertice v = fila.get(0);
					ArrayList<Vertice> listaAdjacente = this.getGrafo().getMapaAdjacencia().get(v);
					for(Vertice adj : listaAdjacente) {
						if(cor[adj.getNroVertice()] == Cor.BRANCO) {
							cor[adj.getNroVertice()] = Cor.CINZA;
							distancia[adj.getNroVertice()] = distancia[v.getNroVertice()] + 1;
							ordemVertice.add(adj.getNroVertice());
							fila.add(adj);
						}
					}
					fila.remove(0);
					cor[v.getNroVertice()] = Cor.PRETO;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * Método que realiza a impressão do resultado para o algoritmo BFS, conforme instruído na especificação do trabalho
		 * 
		 * @Precondition  Execução de algoritmo já realizada
		 * @Postcondition Impressão de resultado no console
		 */
		@Override
		public void imprimeResultado() {
		
			super.imprimeResultado();
			StringBuilder str = new StringBuilder("\t");
			for(Integer nroVertice : ordemVertice) {
				str.append(nroVertice).append(" - ");
			}
			str.replace(str.lastIndexOf(" - "),str.toString().length()-1,"");
			System.out.println(str.toString());
		}
		
		/**
		 * Método implementado pelo Runnable, responsável pela execução do algoritmo BFS
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			BFS_visit();
			imprimeResultado();
		}
	}
