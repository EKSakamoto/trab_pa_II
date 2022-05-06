package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import algoritmo.Prim;
import data.Aresta;
import data.Grafo;

	public class CarregarArquivo {

		private File file;
		
		public CarregarArquivo() {
			
		}
		
		public CarregarArquivo(File file) {
			this.file = file;
		}
		
		public File getFile() {
			return file;
		}

		public void setFile(File file) {
			this.file = file;
		}

		public Grafo converteArquivo() {
			
			if(!file.exists()) {
				return null;
			}else {
				try {
					FileReader fr = new FileReader(file);
					BufferedReader br = new BufferedReader(fr);
					boolean orientado = true;
					int qtdVertice = 0;
					String fileLine;
					
					fileLine = br.readLine();
					if(fileLine.contains("nao"))	orientado = false;
					
					fileLine = br.readLine();
					qtdVertice = Integer.valueOf(fileLine.substring(fileLine.indexOf("=")+1));
					
					int origem, destino, peso;
					ArrayList<Aresta> listaAresta = new ArrayList<>();
					while((fileLine = br.readLine()) != null) {
						origem = Integer.valueOf(fileLine.substring(fileLine.indexOf("(")+1,fileLine.indexOf(",")));
						destino = Integer.valueOf(fileLine.substring(fileLine.indexOf(",")+1,fileLine.indexOf(")")));
						peso = Integer.valueOf(fileLine.substring(fileLine.indexOf(":")+1));
						listaAresta.add(new Aresta(origem,destino,peso));
					}
					Grafo grafo = new Grafo(orientado,qtdVertice,listaAresta);
					
					return grafo;
				}catch(Exception e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		
		public static void main(String[] strgs) {
			
			CarregarArquivo a = new CarregarArquivo();
			a.setFile(new File("C:\\Users\\Eduar\\paa\\TrabalhoII_PAA\\src\\util\\testeKruskal"));
			Grafo g = a.converteArquivo();
			System.out.println(g.getGrafoInfo());
			
			Prim p = new Prim(g,2);
			p.run();
			StringBuilder str = new StringBuilder();
			int peso = 0;
			for(Aresta aresta : p.getArestaArvoreGeradora()) {
				str.append("(").append((char) (aresta.getVerticeOrigem() + 97))
				   .append(",")
				   .append((char) (aresta.getVerticeDestino() + 97)).append(")")
				   .append(" | ");
				peso += aresta.getPeso();
			}
			System.out.println("Vértice Inicial = " + p.getVerticeOrigem().getNroVertice());
			System.out.println("Peso Total = " + peso);
			System.out.println("Arestas = " + str.toString());
			
			/*
			Kruskal k = new Kruskal(g);
			k.run();
			StringBuilder str = new StringBuilder();
			int peso = 0;
			for(Aresta aresta : k.getArestaArvoreGeradora()) {
				str.append("(").append(aresta.getVerticeOrigem())
				   .append(",")
				   .append(aresta.getVerticeDestino()).append(")")
				   .append(" | ");
				peso += aresta.getPeso();
			}
			System.out.println("Peso Total = " + peso);
			System.out.println("Arestas = " + str.toString());
			*/
			/*
			 * BellmanFord bf = new BellmanFord(g,1);
			 * bf.run();
			 */
			/*
			BuscaProfundidade dfs = new BuscaProfundidade(g,0);
			BuscaLargura bfs = new BuscaLargura(g,0);
			dfs.run();
			bfs.run();
			System.out.println("Profundidade = " + dfs.getOrdemVertice());
			System.out.println("Largura = " + bfs.getOrdemVertice());
			*/
		}
	}
