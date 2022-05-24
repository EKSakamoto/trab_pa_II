package util;

import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.mutGraph;
import static guru.nidi.graphviz.model.Factory.node;
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

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import data.Aresta;
import data.Grafo;
import data.Vertice;

/**
 * Classe que realiza o desenho do grafo, considerando a documentação do Graphviz e a estrutura de grafo, 
 * conforme especificação do trabalho
 * 
 * URL(s) de referência: https://github.com/nidi3/graphviz-java
 * 						 https://graphviz.org/documentation/
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
		
		/**
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
		
		/**
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
		
		/**
		 * Método que realiza a impressão de título voltado para informações do desenho do grafo
		 * 
		 * @Precondition  Nenhuma
		 * @Postcondition Nenhuma
		 */
		public void tituloDesenhoGrafo() {
			
			System.out.println();
			System.out.println("\t===================================");
			System.out.println("\t| Informações de Desenho de Grafo |");
			System.out.println("\t===================================");
			System.out.println();
		}
				
		/**
		 * Método que realiza a geração de mapa inicial de Node, considerando a lista de vértices do grafo
		 * 
		 * @Precondition  Grafo não nulo
		 * @Postcondition Mapa inicial de Nodes
		 * @return 		  Estrutura de mapa inicial de Nodes, considetrando todos os vértices do grafo 
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
		
		/**
		 * Método que verifica se uma aresta é uma aresta de árvore geradora mínima
		 * 
		 * @Precondition  Aresta não nula
		 * @Postcondition Resultado de comparação
		 * @param  		  target - Parâmetro referente a uma determinada aresta
		 * @return 		  Booleano referente a comparação se a aresta parâmetro é uma aresta geradora
		 */
		public boolean isArestaGeradora(Aresta target) {
			
			if(todoMST) {
				for(Aresta a : arvoreGeradoraMinima) {
					// (u,v) == (u,v) ou Grafo Não Orientado e (u,v) == (v,u)
					if(target.getVerticeOrigem() == a.getVerticeOrigem() && target.getVerticeDestino() == a.getVerticeDestino() ||
						(!this.dadoGrafo.isOrientado() && 
						(a.getVerticeOrigem() == target.getVerticeDestino() && a.getVerticeDestino() == target.getVerticeOrigem())))	
						return true;
				}
			}
			return false;
		}
		
		/**
		 * Método iterativo de montagem dos Nodes do grafo, com base nos dados do grafo processado anteriormente
		 * 
		 * @Precondition  Estrutura de grafo, mapa inicial de Nodes e booleano para MST não nulos
		 * @Postcondition Configuração de Nodes do grafo
		 * @return 		  LinkSource referente a um subgrafo composto pelos conjunto de Nodes (Vértice) e seus respectivos Links (Aresta)
		 */
		public LinkSource iterativeNodeMontage() {

			try {
				LinkedHashMap<Integer,ArrayList<Node>> nodeMap = generateInitialNodeMap();
				Node[] targetNode = new Node[nodeMap.size()];
				for(Integer nodeKey : nodeMap.keySet()) {
					Node node = node(nodeKey.toString());
					ArrayList<Node> nodeList = nodeMap.get(nodeKey);
					for(int i = 0 ; i < nodeList.size() ; i++) {
						node = node.link(nodeList.get(i));
					}
					targetNode[nodeKey] = node;
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
						l = l.add(l.with("label", 
										 String.valueOf(this.dadoGrafo.getArestaEspecifica(this.dadoGrafo.getVerticeEspecifico(start), 
												 										   this.dadoGrafo.getVerticeEspecifico(end))
										.getPeso())));
					}
				}
				return subgraph;
			}catch(Exception e) {
				// e.printStackTrace();
				return mutGraph();
			}
		}
		
		/**
		 * Método que define a estrutura do desenho de grafo
		 * 
		 * @Precondition  Estrutura de grafo não nula
		 * @Postcondition Definição de Graph para realizar sua renderização
		 */
		public void drawGeneralGraph() {
			
			try {
				Graphviz.useDefaultEngines();
				tituloDesenhoGrafo();
				graph = graph(this.dadoGrafo.getNomeGrafo())
						.graphAttr().with(Rank.dir(RankDir.LEFT_TO_RIGHT))
						.graphAttr().with("splines","true")
//						.linkAttr().with("class", "link-class")
						.with(iterativeNodeMontage());
				System.out.println(graph);
				renderGraphFile(graph);
			}catch(Exception e) {
				 e.printStackTrace();
			}
		}
		
		/**
		 * Método que realiza a renderização de um grafo para arquivos dos formatos .txt e .png
		 * 
		 * @Precondition  Abstração de Graph não nulo
		 * @Postcondition Geração de arquivos de grafo
		 * @param 		  g - Parâmetro referente a estrutura Graph, contendo as informações da estrutura Grafo (processada pelo algoritmo em grafo)
		 */
		public void renderGraphFile(Graph g) {
			
			try {
				System.out.println("\n\tAguardando Renderização...\n");
				File image, dot;
				String newFileName = this.dadoGrafo.getNomeGrafo();
				if(todoMST)		newFileName += " - Árvore Geradora";
				image = Graphviz.fromGraph(g)
								    .height(1000)
								    .render(Format.PNG)
								    .toFile(new File("image/" + newFileName + ".png"));
				dot = Graphviz.fromGraph(g)
					    .render(Format.DOT)
					    .toFile(new File("dot/" + newFileName + ".txt"));
				
				System.out.println("\tArquivo '" + image.getAbsolutePath() + "' Gerado com Sucesso!");
				System.out.println("\tAs imagens de grafos estão no diretório ${project_dir}/image\n");
				System.out.println("\tArquivo '" + dot.getAbsolutePath() + "' Gerado com Sucesso!");
				System.out.println("\tOs arquivos txt dos DOTs dos grafos estão no diretório ${project_dir}/dot");
			}catch(Exception e) {
				// e.printStackTrace();
				System.err.println("\tError ao Gerar arquivo");
			}	
		}
	}