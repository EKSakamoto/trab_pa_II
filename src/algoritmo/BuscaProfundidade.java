package algoritmo;

import java.util.ArrayList;

import data.Cor;
import data.Grafo;
import data.Vertice;

/*
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
		
		/*
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
		
		/*
		 * Método que inicializa as estruturas essenciais para execução do algoritmo
		 * Pré-condição: Vértice de origem pré-definido válido
		 * Pós-condição: Nenhum
		 */
		public void inicializaDFS() {
			
			for(int i = 0 ; i < this.getGrafo().getQtdVertice() ; i++) {
				cor[i] = Cor.BRANCO;
			}
			ordemVertice.add(verticeOrigem.getNroVertice());
		}
		

		/*
		 * Método que devolve uma lista de vértices, colocando o vértice de origem no início da lista
		 * 
		 * @return list - Lista de vértices, organizado para processamento de algoritmo
		 */
		/*
		public ArrayList<Vertice> gerarListaVertice_Origem(){
			
			ArrayList<Vertice> list = this.getGrafo().getListaVertice();
			list.remove(verticeOrigem);
			list.add(0, verticeOrigem);
			return list;
		}
		*/
		
		
		/*
		 * Método que realiza a execução do algoritmo "Busca em Profundidade (DFS)"
		 * Pré-condição: Vértice de origem válido
		 * Pós-condição: Ordem de visita de vértices definida
		 */
		public void DFS() {
			
			try {
				inicializaDFS();
				DFS_visit(verticeOrigem);
				
				/* Implementação considerada caso fosse percorrer todos os vértices, independente do grafo ser conexo ou não	
				ArrayList<Vertice> list = gerarListaVertice_Origem();
				for(Vertice u : list) {
					if(cor[u.getNroVertice()] == Cor.BRANCO) {
						ordemVertice.add(u.getNroVertice());
						DFS_visit(u);
					}
				}
				*/
			}catch(Exception e) {
//				e.printStackTrace();
			}
		}
	
		/*
		 * Método que realiza a visita recursiva dos vértices, conforme o algoritmo DFS
		 * Pré-condição: Vértice de parâmetro válido
		 * Pós-condição: Nenhum
		 * 
		 * @param v - Parâmetro referente a um determinado vértice do grafo
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
		
		/*
		 * Método que realiza a impressão do resultado para o algoritmo DFS, conforme instruído na especificação do trabalho
		 * Pré-condição: Execução de algoritmo já realizada
		 * Pós-condiçao: Impressão de resultado no console
		 */
		@Override
		public void imprimeResultado() {
		
			StringBuilder str = new StringBuilder();
			for(Integer nroVertice : ordemVertice) {
				str.append(nroVertice).append(" - ");
			}
			str.replace(str.lastIndexOf(" - "),str.toString().length()-1,"");
			System.out.println(str.toString());
		}
		
		/*
		 * Método implementado pelo RUnnable, responsável pela execução do algoritmo DFS
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			DFS();
			imprimeResultado();
		}
	}
