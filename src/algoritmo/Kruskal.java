package algoritmo;

import java.util.ArrayList;

import data.Aresta;
import data.ConjuntoVertice;
import data.Grafo;
import data.Vertice;
import util.SorterAresta_Peso;

/*
 * Classe que implementa o algoritmo "Árvore Geradora Mínima - Kruskal"
 * 
 * @author Eduardo Sakamoto
 */

	public class Kruskal extends AlgoritmoGrafo{

		private ArrayList<Aresta> arestaArvoreGeradora;
		private ArrayList<ConjuntoVertice> listaConjuntoVertice;
		private int pesoTotal;
		
		public Kruskal() {
			super();
		}

		/*
		 * Construtor de inicilização para o algortitmo 'Árvore Geradora Mínima - Kruskal'
		 * 
		 * @param grafo - Parâmetro referente ao grafo a ser processado
		 */
		public Kruskal(Grafo grafo) {
			super(grafo,"Árvore Geradora Mínima - Kruskal");
			arestaArvoreGeradora = new ArrayList<>();
			pesoTotal = 0;
		}
		
		/*
		 * Método getter voltado para lista de arestas da árvore geradora mínima
		 * 
		 * @return arestaArvoreGeradora - Estrutura da lista de arestas da árvore geradora mínima
		 */
		public ArrayList<Aresta> getArestaArvoreGeradora() {
			return arestaArvoreGeradora;
		}

		/*
		 * Método setter referente a lista de arestas da árvore geradora mínima
		 * 
		 * @param arestaArvoreGeradora - Parâmetro refernte a lista de arestas da árvore geradora mínima
		 */
		public void setArestaArvoreGeradora(ArrayList<Aresta> arestaArvoreGeradora) {
			this.arestaArvoreGeradora = arestaArvoreGeradora;
		}

		/*
		 * Método que realiza a busca por um conjunto de vértice(s), a partir de um determinado vértice
		 * Pré-condição: Número de vértice deve estar contido na lista de vértices do grafo
		 * Pós-condição: Definição do conjunto de vértice requerido
		 * 
		 * @param  nroVertice - Parâmetro referente ao número de um determinado vértice do grafo
		 * @return conjunto   - Conjunto de vértice que contém o vértice parametrizado
		 */
		public ConjuntoVertice procuraConjuntoVertice(int nroVertice) {
			
			for(ConjuntoVertice conjunto : listaConjuntoVertice) {
				for(Vertice v : conjunto) {
					if(v.getNroVertice() == nroVertice)		return conjunto;
				}
			}
			return null;
		}
		
		/*
		 * Método de inicialização que cria a lista de conjuntos de vértices para cada vértice do grafo
		 * Pré-condição: Grafo não nulo
		 * Pós-condição: Definição de lista de conjuntos de vértices
		 */
		public void criaConjuntoVertice() {
			
			listaConjuntoVertice = new ArrayList<>();
			for(Vertice v : this.getGrafo().getListaVertice()) {
				listaConjuntoVertice.add(new ConjuntoVertice(v));
			}
		}
		
		/*
		 * Método que realiza a execução do algoritmo "Árvore Geradora Mínima - Kruskal"
		 * Pré-condição: Grafo não nulo
		 * Pós-condição: Definição da lista de arestas de árvore geradora mínima
		 */
		public void arvoreGeradoraMinima_Kruskal() {
			
			criaConjuntoVertice();
			ArrayList<Aresta> listaPeso = this.getGrafo().getListaAresta();
			listaPeso.sort(new SorterAresta_Peso());
			for(Aresta a : listaPeso) {
				ConjuntoVertice u, v;
				u = procuraConjuntoVertice(a.getVerticeOrigem());
				v = procuraConjuntoVertice(a.getVerticeDestino());
				if(u.distintoConjuntoVertice(v)) {
					listaConjuntoVertice.remove(u);
					listaConjuntoVertice.remove(v);
					u.union(v);
					listaConjuntoVertice.add(u);
					arestaArvoreGeradora.add(a);
				}
			}
			calculaPesoTotal();
		}
		
		/*
		 * Método que realiza o cálculo do peso total da árvore geradora mínima
		 * Pré-condição: Lista de arestas de árvore geradora mínima não nula
		 * Pós-condição: Atribuição do peso total da árvore
		 */
		public void calculaPesoTotal() {
			
			for(Aresta a : arestaArvoreGeradora) {
				pesoTotal += a.getPeso();
			}
		}
		
		/*
		 * Método que realiza a impressão do resultado para o algoritmo Kruskal, conforme instruído na especificação do trabalho
		 * Pré-condição: Execução de algoritmo já realizada
		 * Pós-condiçao: Impressão de resultado no console
		 */
		@Override
		public void imprimeResultado() {

			super.imprimeResultado();
			StringBuilder str = new StringBuilder();
			str.append("\t")
			   .append("peso total: ").append(pesoTotal)
			   .append("\n\t")
			   .append("arestas: ");
			for(Aresta a : this.arestaArvoreGeradora) {
				str.append(a.uvRepresentation()).append(" ");
			}
			str.append("\n");
			System.out.println(str.toString());
		}
		
		/*
		 * Método implementado pelo Runnable, responsável pela execução do algoritmo Kruskal
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			arvoreGeradoraMinima_Kruskal();
			imprimeResultado();
		}
		
	}
