package util;

import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.mutGraph;
import static guru.nidi.graphviz.model.Factory.node;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

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
		
		public boolean isArestaGeradora(Aresta target) {
			
			for(Aresta a : getArvoreGeradoraMinima()) {
				if(target.getVerticeOrigem() == a.getVerticeOrigem() &&
				   target.getVerticeDestino() == a.getVerticeDestino())	return true;
			}
			return false;
		}
			
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
					System.out.println("Node " + nodeKey + " ==> " + node);
				}
				
				MutableGraph subgraph = mutGraph().add(targetNode)
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
							System.out.println("(" + start + "," + end + ")");
						}
					}
				}
				System.out.println("Graph Generated");
				return subgraph;
			}catch(Exception e) {
				// e.printStackTrace();
				return mutGraph();
			}
		}
		
		public void drawGeneralGraph() {
			
			try {
				Graphviz.useDefaultEngines();
//				Graphviz.useEngine(Arrays.asList((GraphvizEngine) new V8JavascriptEngine()));
				if(getDadoGrafo().isOrientado()) ;
				graph = graph(getDadoGrafo().getNomeGrafo()).graphAttr()
						.with(Rank.dir(RankDir.LEFT_TO_RIGHT))
						.linkAttr().with("class", "link-class")
						.with(iterativeNodeMontage());
				System.out.println(graph);
				renderGraphFile(graph);
			}catch(Exception e) {
				// e.printStackTrace();
			}
		}
		
		public void renderGraphFile(Graph g) {
			
			try {
				File file = Graphviz.fromGraph(g)
								    .height(1000)
								    .render(Format.PNG)
								    .toFile(new File("image/" + getDadoGrafo().getNomeGrafo() + ".png"));
				System.out.println("Arquivo '" + file.getAbsolutePath() + "' Gerado com Sucesso!");
			}catch(Exception e) {
				// e.printStackTrace();
				System.err.println("Error ao Gerar arquivo");
			}
			try {

				System.out.println("bbb");
//				System.setProperty("java.awt.headless","false");
				SwingUtilities.invokeLater(() -> {
					System.setProperty("java.awt.headless","true");
					JFrame f = new JFrame("myframe");
				    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				    f.setVisible(true);
				});
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
	
	}
