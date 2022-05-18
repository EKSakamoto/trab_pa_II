package algoritmo;

import java.util.ArrayList;
import java.util.Arrays;

import data.Aresta;
import data.Grafo;
import data.Vertice;
import util.SorterAresta_VerticeOrigem;

	public class Prim extends AlgoritmoGrafo{
	
		private Vertice verticeOrigem;
		private ArrayList<Vertice> vertice_ausenteArvore;
		private ArrayList<Aresta> arestaArvoreGeradora;
		private int[] predecessor;
		private int[] key;
		private int pesoTotal;
		
		public Prim() {
			super();
		}
		
		/*
		 * Construtor de inicialização para o algoritmo 'Árvore Geradora Mínima - Prim'
		 * 
		 * @param grafo   - Parâmetro referente ao grafo a ser processado
		 * @param vertice - Parâmetro referente ao número do vértice de origem, no contexto do algoritmo
		 */
		public Prim(Grafo grafo, int vertice) {
			super(grafo,"Árvore Geradora Mínima - Prim");
			verticeOrigem = this.getGrafo().getVerticeEspecifico(vertice);
			arestaArvoreGeradora = new ArrayList<>();
			vertice_ausenteArvore = new ArrayList<>();
			predecessor = new int[this.getGrafo().getQtdVertice()];
			key = new int[this.getGrafo().getQtdVertice()];
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
		 * Método de inicialização de estruturas essenciais para execução do algoritmo
		 * Pré-condição: Grafo e vértice de origem não nulos
		 * Pós-condição: Nenhum
		 */
		public void inicializaOrigem() {
			
			for(Vertice v : this.getGrafo().getListaVertice()) {
				key[v.getNroVertice()] = Integer.MAX_VALUE;
				vertice_ausenteArvore.add(v);
				predecessor[v.getNroVertice()] = -2;			// Não possui predecessor definido
			}
			key[verticeOrigem.getNroVertice()] = 0;
			predecessor[verticeOrigem.getNroVertice()] = -1;	// Predecessor = NIL
			vertice_ausenteArvore.remove(verticeOrigem);
			vertice_ausenteArvore.add(0,verticeOrigem);
		}
		
		/*
		 * Método que extrai o vértice com o menor valor no vetor key
		 * Pré-condição: Lista de vértices não nulos
		 * Pós-condição: Nenhum
		 * 
		 * @param  list 	    - Parâmetro referente a lista de vértices (lista de vértices que não estão presentes na árvore geradora)
		 * @return verticeMenor - Vértice do grafo referente ao menor valot do vetor key;
		 */
		public Vertice extraiVertice_MinimoKey(ArrayList<Vertice> list) {
			
			int menor = Integer.MAX_VALUE;
			Vertice verticeMenor = null;
			for(Vertice v : list) {
				if(key[v.getNroVertice()] <= menor) {
					menor = key[v.getNroVertice()];
					verticeMenor = v;
				}
			}
			if(verticeMenor == null)	verticeMenor = list.get(0);
			return verticeMenor;
		}
		
		/*
		 * Método que realiza a execução do algoritmo 'Árvore Geradora Mínima - Prim'
		 * Pré-condição: Grafo e vértice de origem não nulos
		 * Pós-condição: Lista de arestas de árvore geradora mínima definida
		 */
		public void arvoreGeradoraMinima_Prim() {
			
			inicializaOrigem();
			while(!vertice_ausenteArvore.isEmpty()) {
				Vertice u = extraiVertice_MinimoKey(vertice_ausenteArvore);
				System.out.println("Vertice " + ((char) (u.getNroVertice() + 97)));
				System.out.println(Arrays.toString(key));
				vertice_ausenteArvore.remove(u);
				for(Vertice adj : this.getGrafo().getMapaAdjacencia().get(u)) {
					Aresta a = this.getGrafo().getArestaEspecifica(u,adj);
					if(vertice_ausenteArvore.contains(adj) &&
					   a.getPeso() < key[adj.getNroVertice()]) {
						predecessor[adj.getNroVertice()] = u.getNroVertice();
						key[adj.getNroVertice()] = a.getPeso();
						/*
						arestaArvoreGeradora.add(a);
						// Problema está no add da lista de arestas (adicionando arestas a mais)
						System.out.println("(" +
								(char) (a.getVerticeOrigem() + 97) + "," +
								(char) (a.getVerticeDestino() + 97) + 
								")" + " Passo");
								*/
					// Verificar algoritmo
					}	
				}
			}
			/*
			arestaArvoreGeradora.sort(new SorterAresta_VerticeOrigem());
			for(Aresta a : arestaArvoreGeradora) {
				System.out.print("(" +
									(char) (a.getVerticeOrigem() + 97) + "," +
									(char) (a.getVerticeDestino() + 97) + 
									")" + " | ");
				
			}
			*/
			defineArvoreGeradora();
			calculaPesoTotal();
		}
		
		/*
		 * Método que define a lista de arestas da árvore geradora mínima, tendo como referência o vetor predecessor dos vértices
		 * Pré-condição: Fila de Vértice Ausente na Árvore Vazia (algoritmo 'semi-executado')
		 * Pós-condição: Definição de lista de arestas da árvore geradora mínima
		 */
		public void defineArvoreGeradora() {
			
			for(int i = 0 ; i < predecessor.length ; i++) {
				if(predecessor[i] >= 0) {
					arestaArvoreGeradora.add(this.getGrafo().getArestaEspecifica(this.getGrafo().getVerticeEspecifico(i),
							 this.getGrafo().getVerticeEspecifico(predecessor[i])));
				}
			}
		}
		
		/*
		 * Método que define o valor do peso total das arestas da árvore geradora mínima
		 * Pré-condição: Lista de arestas da árvore geradora não nulo
		 * Pós-condição: Definição do valor de peso total
		 */
		public void calculaPesoTotal() {
			
			for(Aresta a : arestaArvoreGeradora) {
				pesoTotal += a.getPeso();
			}
		}
		
		/*
		 * Método que realiza a impressão do resultado para o algoritmo Prim, conforme instruído na especificação do trabalho
		 * Pré-condição: Execução do algoritmo já realizada
		 * Pós-condição: Impressão do resultado no console
		 */
		@Override
		public void imprimeResultado() {

			StringBuilder str = new StringBuilder();
			str.append("vértice inicial: ").append(this.verticeOrigem.getNroVertice()).append("\n")
			   .append("peso total: ").append(this.pesoTotal).append("\n")
			   .append("arestas: ");
			for(Aresta a : this.arestaArvoreGeradora) {
				str.append(a.uvRepresentation()).append(" ");
			}
			str.append("\n");
			System.out.println(str.toString());
		}
		
		/*
		 * Método implementado pelo Runnable, responsável pela execução do algoritmo Prim
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			arvoreGeradoraMinima_Prim();
			imprimeResultado();
		}
		
	}
