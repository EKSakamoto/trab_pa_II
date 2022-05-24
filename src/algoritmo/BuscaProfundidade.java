package algoritmo;

import java.util.ArrayList;

import data.Cor;
import data.Grafo;
import data.Vertice;

/**
 * Classe que implementa o algoritmo "Busca em Profundidade (DFS)"
 * 
 * @author Eduardo Sakamoto
 */

	public class BuscaProfundidade extends AlgoritmoGrafo{

		private Vertice verticeOrigem;
		private ArrayList<Integer> ordemVertice;
		private Cor[] cor;
		
		public BuscaProfundidade() {
			super();
		}
		
		/**
		 * Construtor de inicialização para o algoritmo 'Busca em Profundidade (DFS)'
		 * 
		 * @param grafo			   - Parâmetro referente ao grafo a ser processado
		 * @param nroVerticeOrigem - Parâmetro referente ao número do vértice de origem, no contexto do algoritmo
		 */
		public BuscaProfundidade(Grafo grafo, int nroVerticeOrigem) {
			
			super(grafo,"Busca em Profundidade (DFS)");
			this.ordemVertice = new ArrayList<>();
			cor = new Cor[this.getGrafo().getQtdVertice()];
			this.verticeOrigem = this.getGrafo().getVerticeEspecifico(nroVerticeOrigem);
		}
		
		/**
		 * Método que inicializa as estruturas essenciais para execução do algoritmo
		 * 
		 * @Precondition  Vértice de origem pré-definido válido
		 * @Postcondition Nenhuma
		 */
		public void inicializaDFS() {
			
			for(int i = 0 ; i < this.getGrafo().getQtdVertice() ; i++) {
				cor[i] = Cor.BRANCO;
			}
			ordemVertice.add(verticeOrigem.getNroVertice());
		}
				
		/**
		 * Método que realiza a execução do algoritmo "Busca em Profundidade (DFS)"
		 * 
		 * @Precondition  Vértice de origem válido e grafo não nulo
		 * @Postcondition Ordem de visita de vértices definida
		 */
		public void DFS() {
			
			try {
				inicializaDFS();
				DFS_visit(verticeOrigem);
			}catch(Exception e) {
//				e.printStackTrace();
			}
		}
	
		/**
		 * Método que realiza a visita recursiva dos vértices, conforme o algoritmo DFS
		 * 
		 * @Precondition  Vértice de parâmetro válido
		 * @Postcondition Nenhuma
		 * @param 		  v - Parâmetro referente a um determinado vértice do grafo
		 */
		public void DFS_visit(Vertice v) {
			
			try {
				cor[v.getNroVertice()] = Cor.CINZA;
				ArrayList<Vertice> listaAdjacente = this.getGrafo().getMapaAdjacencia().get(v);
				for(Vertice adj : listaAdjacente) {
					if(cor[adj.getNroVertice()] == Cor.BRANCO) {
						ordemVertice.add(adj.getNroVertice());
						DFS_visit(adj);
					}
				}
				cor[v.getNroVertice()] = Cor.PRETO;
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * Método que realiza a impressão do resultado para o algoritmo DFS, conforme instruído na especificação do trabalho
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
		 * Método implementado pelo Runnable, responsável pela execução do algoritmo DFS
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			DFS();
			imprimeResultado();
		}
	}
