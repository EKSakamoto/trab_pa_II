package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import algoritmo.BellmanFord;
import algoritmo.BuscaLargura;
import algoritmo.BuscaProfundidade;
import algoritmo.Kruskal;
import algoritmo.Prim;
import data.Aresta;
import data.Grafo;

/**
 * Classe responsável pela carga de um grafo, a partir de um arquivo txt
 * 
 * @author Eduardo Sakamoto
 */

	public class CarregarArquivo {

		private File file;
		
		public CarregarArquivo() {
			
		}
		
		/**
		 * Construtor de inicialização para carga de arquivo
		 * 
		 * @param file - Parâmetro referente ao arquivo alvo para processo de conversão em grafo
		 */
		public CarregarArquivo(File file) {
			this.file = file;
		}

		/**
		 * Método que realiza a conversão de um arquivo txt numa estrutura de grafo
		 * 
		 * @Precondition  Arquivo não nulo
		 * @Postcondition Resultado de conversão de arquivo
		 * @return 		  Estrutura de grafo resultante da leitura de arquivo
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
	}
