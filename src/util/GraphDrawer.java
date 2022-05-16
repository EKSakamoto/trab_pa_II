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
			graph = null;
			arvoreGeradoraMinima = new ArrayList<>();
		}
		
		public GraphDrawer(Grafo dadoGrafo) {
			this.dadoGrafo = dadoGrafo;
			arvoreGeradoraMinima = new ArrayList<>();
			this.todoMST = false;
			graph = null;
		}
		
		public GraphDrawer(Grafo dadoGrafo, ArrayList<Aresta> arvoreGeradoraMinima) {
			this.dadoGrafo = dadoGrafo;
			this.arvoreGeradoraMinima = arvoreGeradoraMinima;
			this.todoMST = true;
			graph = null;
		}

		public Graph getGraph() {
			return graph;
		}

		public void setGraph(Graph graph) {
			this.graph = graph;
		}
	
		public Grafo getDadoGrafo() {
			return dadoGrafo;
		}

		public void setDadoGrafo(Grafo dadoGrafo) {
			this.dadoGrafo = dadoGrafo;
		}

		public ArrayList<Aresta> getArvoreGeradoraMinima() {
			return arvoreGeradoraMinima;
		}

		public void setArvoreGeradoraMinima(ArrayList<Aresta> arvoreGeradoraMinima) {
			this.arvoreGeradoraMinima = arvoreGeradoraMinima;
		}

		public boolean isTodoMST() {
			return todoMST;
		}

		public void setTodoMST(boolean todoMST) {
			this.todoMST = todoMST;
		}
		
		/*
		 * Método que verifica se uma aresta é uma aresta de árvore geradora mínima
		 * Pré-condição: Aresta não nula
		 * Pós-condição: Resultado de comparação
		 */
		public boolean isArestaGeradora(Aresta target) {
			
			for(Aresta a : getArvoreGeradoraMinima()) {
				if(target.getVerticeOrigem() == a.getVerticeOrigem() &&
				   target.getVerticeDestino() == a.getVerticeDestino())	return true;
			}
			return false;
		}
			
		/*
		 * Método que realiza a geração de mapa inicial de Node, considerando a lista de vértices do grafo
		 * Pré-condição: Grafo não nulo
		 * Pós-condição: Mapa inicial de Nodes
		 */
		public LinkedHashMap<Integer,ArrayList<Node>> generateNodeMap(){
			
			LinkedHashMap<Integer,ArrayList<Node>> nodeMap = new LinkedHashMap<>();
			for(int i = 0 ; i < getDadoGrafo().getQtdVertice() ; i++) {
				int keyNode = i;
				ArrayList<Node> nodeList = new ArrayList<>();
				for(Aresta a : getDadoGrafo().getListaAresta()) {
					Vertice v = getDadoGrafo().getVerticeEspecifico(a.getVerticeOrigem());
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
		 */
		public LinkSource iterativeNodeMontage() {

			try {
				LinkedHashMap<Integer,ArrayList<Node>> nodeMap = generateNodeMap();
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
												  .setDirected(getDadoGrafo().isOrientado())
												  .setStrict(true)
												  .setCluster(true);
				if(isTodoMST()) {
					for(MutableNode n : subgraph.nodes()) {
						for(Link l : n.links()) {
							Integer start, end;
							String[] split = l.name().value().split("--");
							start = Integer.valueOf(split[0]);
							end = Integer.valueOf(split[1]);
							if(isArestaGeradora(new Aresta(start,end))) {
								l = l.linkTo().add(Color.RED);	
							}
//							System.out.println("(" + start + "," + end + ")");
						}
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
				graph = graph(getDadoGrafo().getNomeGrafo())
						.graphAttr().with(Rank.dir(RankDir.LEFT_TO_RIGHT))
						.graphAttr().with("splines","spline")
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
		 */
		public void renderGraphFile(Graph g) {
			
			try {
				File image, dot;	
				image = Graphviz.fromGraph(g)
								    .height(1000)
								    .render(Format.PNG)
								    .toFile(new File("image/" + getDadoGrafo().getNomeGrafo() + ".png"));
				dot = Graphviz.fromGraph(g)
					    .render(Format.DOT)
					    .toFile(new File("dot/" + getDadoGrafo().getNomeGrafo() + ".txt"));
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
