package util;

import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.mutGraph;
import static guru.nidi.graphviz.model.Factory.node;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import data.Aresta;
import data.Grafo;
import data.Vertice;
import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Rank.RankDir;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Link;
import guru.nidi.graphviz.model.LinkSource;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;
import guru.nidi.graphviz.model.Node;

/*
 * Classe que realiza o desenho do grafo, considerando a documentação do Graphviz
 * URL: https://github.com/nidi3/graphviz-java
 * 		https://graphviz.org/
 * 
 * @author Eduardo Sakamoto
 */

	public class GraphDrawer{

		private Graph graph;
		private Grafo dadoGrafo;
		private ArrayList<Aresta> arvoreGeradoraMinima;
		private boolean todoMST;
		
		public GraphDrawer() {
		
		}
		
		/*
		 * Construtor de inicialização de estrutura de desenho de grafo
		 * 
		 * @param dadoGrafo - Parâmetro referente a estrutura de grafo
		 */
		public GraphDrawer(Grafo dadoGrafo) {
			this.dadoGrafo = dadoGrafo;
			arvoreGeradoraMinima = new ArrayList<>();
			this.todoMST = false;
			graph = null;
		}
		
		/*
		 * Construtor de inicialização de estrutura de desenho de grafo
		 * 
		 * @param dadoGrafo 		   - Parâmetro referente a estrutura de grafo
		 * @param arvoreGeradoraMinima - Parâmetro referente a lista de arestas da árvore geradora mínima
		 */
		public GraphDrawer(Grafo dadoGrafo, ArrayList<Aresta> arvoreGeradoraMinima) {
			this.dadoGrafo = dadoGrafo;
			this.arvoreGeradoraMinima = arvoreGeradoraMinima;
			this.todoMST = true;
			graph = null;
		}
		
		/*
		 * Método que verifica se uma aresta é uma aresta de árvore geradora mínima
		 * Pré-condição: Aresta não nula
		 * Pós-condição: Resultado de comparação
		 * 
		 * @param  target  - Parâmetro referente a uma determinada aresta
		 * @return boolean - Booleano referente a comparação se a aresta parâmetro é uma aresta geradora
		 */
		public boolean isArestaGeradora(Aresta target) {
			
			if(todoMST) {
				for(Aresta a : arvoreGeradoraMinima) {
					if(target.getVerticeOrigem() == a.getVerticeOrigem() &&
					   target.getVerticeDestino() == a.getVerticeDestino())	return true;
				}
			}
			return false;
		}
			
		/*
		 * Método que realiza a geração de mapa inicial de Node, considerando a lista de vértices do grafo
		 * Pré-condição: Grafo não nulo
		 * Pós-condição: Mapa inicial de Nodes
		 * 
		 * @return nodeMap - Estrutura de mapa inicial de Nodes, considetrando todos os vértices do grafo 
		 */
		public LinkedHashMap<Integer,ArrayList<Node>> generateInitialNodeMap(){
			
			LinkedHashMap<Integer,ArrayList<Node>> nodeMap = new LinkedHashMap<>();
			for(int i = 0 ; i < this.dadoGrafo.getQtdVertice() ; i++) {
				int keyNode = i;
				ArrayList<Node> nodeList = new ArrayList<>();
				for(Aresta a : this.dadoGrafo.getListaAresta()) {
					Vertice v = this.dadoGrafo.getVerticeEspecifico(a.getVerticeOrigem());
					if(keyNode == v.getNroVertice()) {
						nodeList.add(node(String.valueOf(a.getVerticeDestino())));	// node().with(Color.RED) --> Realiza coloração de Vértice
					}
				}  
				nodeMap.put(keyNode, nodeList);
			}
			return nodeMap;
		}
		
		/*
		 * Método iterativo de montagem dos Nodes do grafo
		 * Pré-condição: Estrutura de grafo, mapa inicial de Nodes e booleano para MST não nulos
		 * Pós-condição: Configuração de Nodes do grafo
		 * 
		 * @return subgraph - LinkSource referente a um subgrafo composto pelos conjunto de Nodes (Vértice) e seus respectivos Links (Aresta)
		 */
		public LinkSource iterativeNodeMontage() {

			try {
				LinkedHashMap<Integer,ArrayList<Node>> nodeMap = generateInitialNodeMap();
				Node[] targetNode = new Node[nodeMap.size()];
				System.out.println(nodeMap);
				for(Integer nodeKey : nodeMap.keySet()) {
					Node node = node(nodeKey.toString());
					ArrayList<Node> nodeList = nodeMap.get(nodeKey);
					for(int i = 0 ; i < nodeList.size() ; i++) {
						node = node.link(nodeList.get(i));
					}
					targetNode[nodeKey] = node;
//					System.out.println("Node " + nodeKey + " ==> " + node);
				}
				
				MutableGraph subgraph = mutGraph().setName("edges")
												  .add(targetNode)
												  .setDirected(this.dadoGrafo.isOrientado())
												  .setStrict(true)
												  .setCluster(true);
				
				for(MutableNode n : subgraph.nodes()) {
					for(Link l : n.links()) {
						Integer start, end;
						String[] split = l.name().value().split("--");
						start = Integer.valueOf(split[0]);
						end = Integer.valueOf(split[1]);
						if(isArestaGeradora(new Aresta(start,end))) {
							l = l.linkTo().add(Color.RED);
						}
						l = l.add(l.with("label", String.valueOf(this.dadoGrafo.getArestaEspecifica(this.dadoGrafo.getVerticeEspecifico(start), 
									  this.dadoGrafo.getVerticeEspecifico(end))
										.getPeso()))
									
									);
							System.out.println("(" + start + "," + end + ")");
					}
				}
				
//				System.out.println("Graph Generated");
				return subgraph;
			}catch(Exception e) {
				// e.printStackTrace();
				return mutGraph();
			}
		}
		
		/*
		 * Método que define a estrutura do desenho de grafo
		 * Pré-condição: Estrutura de grafo não nula
		 * Pós-condição: Definição de Graph para realizar sua renderização
		 */
		public void drawGeneralGraph() {
			
			try {
				Graphviz.useDefaultEngines();
//				Graphviz.useEngine(Arrays.asList((GraphvizEngine) new V8JavascriptEngine()));
				graph = graph(this.dadoGrafo.getNomeGrafo())
						.graphAttr().with(Rank.dir(RankDir.LEFT_TO_RIGHT))
						.graphAttr().with("splines","true")
//						.linkAttr().with("class", "link-class")
						.with(iterativeNodeMontage());
				System.out.println(graph);
				renderGraphFile(graph);
			}catch(Exception e) {
				// e.printStackTrace();
			}
		}
		
		/*
		 * Método que realiza a renderização de um grafo para arquivos dos formatos .txt e .png
		 * Pré-condição: 'Graph' não nulo
		 * Pós-condição: Geração de arquivos de grafo
		 * 
		 * @param g - Parâmetro referente a estrutura Graph, contendo as informações da estrutura Grafo (processada pelo algoritmo em grafo)
		 */
		public void renderGraphFile(Graph g) {
			
			try {
				File image, dot;	
				image = Graphviz.fromGraph(g)
								    .height(1000)
								    .render(Format.PNG)
								    .toFile(new File("image/" + this.dadoGrafo.getNomeGrafo() + ".png"));
				dot = Graphviz.fromGraph(g)
					    .render(Format.DOT)
					    .toFile(new File("dot/" + this.dadoGrafo.getNomeGrafo() + ".txt"));
				System.out.println("\tArquivo '" + image.getAbsolutePath() + "' Gerado com Sucesso!");
				System.out.println("\tAs imagens estão no diretório ${project_basedir}/image");
				
				System.out.println("\tArquivo '" + dot.getAbsolutePath() + "' Gerado com Sucesso!");
				System.out.println("\tOs arquivos txt dos DOTs estão no diretório ${project_basedir}/dot");
			}catch(Exception e) {
				// e.printStackTrace();
				System.err.println("\tError ao Gerar arquivo");
			}	
		}
		
	}
