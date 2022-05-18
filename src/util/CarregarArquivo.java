package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import algoritmo.Prim;
import data.Aresta;
import data.Grafo;

/*
 * Classe responsável pela carga de um grafo, a partir de um arquivo txt
 * 
 * @author Eduardo Sakamoto
 */

	public class CarregarArquivo {

		private File file;
		
		public CarregarArquivo() {
			
		}
		
		/*
		 * Construtor de inicialização para carga de arquivo
		 * 
		 * @param file - Parâmetro referente ao arquivo alvo para processo de conversão em grafo
		 */
		public CarregarArquivo(File file) {
			this.file = file;
		}

		/*
		 * Método que realiza a conversão de um arquivo txt numa estrutura de grafo
		 * Pré-condição: Arquivo não nulo
		 * Pós-condição: Resultado de conversão de arquivo
		 * 
		 * @return grafo - Estrutura de grafo resultante da leitura de arquivo
		 */
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
					br.close();
					return new Grafo(file.getName(),orientado,qtdVertice,listaAresta);
				}catch(Exception e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		
		public static void main(String[] strgs) {
			
			CarregarArquivo a = new CarregarArquivo(new File("C:\\Users\\Eduar\\paa\\TrabalhoII_PAA\\src\\util\\figura2"));
			Grafo g = a.converteArquivo();
			
			/*
			BuscaProfundidade b = new BuscaProfundidade(g,8);
			b.run();
			
			*/
			
			Prim p = new Prim(g,2);
			p.run();


			GraphDrawer drawer = new GraphDrawer(g,p.getArestaArvoreGeradora());
			drawer.drawGeneralGraph();
			
			/*
			System.out.println(g.getNomeGrafo());
			Kruskal k = new Kruskal(g);
			k.run();
			GraphDrawer drawer = new GraphDrawer(g,k.getArestaArvoreGeradora());
			drawer.drawGeneralGraph();
			*/
			/*
			System.out.println(g.getGrafoInfo());
			
			Prim p = new Prim(g,2);
			p.run();
			System.out.println("Prim = ");		p.imprimeResultado();
			*/
			/*
			BuscaProfundidade dfs = new BuscaProfundidade(g,0);
			BuscaLargura bfs = new BuscaLargura(g,0);
			BellmanFord b = new BellmanFord(g,0);
			Kruskal k = new Kruskal(g);
			dfs.run();
			bfs.run();
			b.run();
			k.run();
			
			System.out.println("Profundidade = ");	dfs.imprimeResultado();
			System.out.println("Largura = ");		bfs.imprimeResultado();
			System.out.println("BellmanFord = ");	b.imprimeResultado();
			System.out.println("Kruskal = ");		k.imprimeResultado();
			*/
		}
	}
