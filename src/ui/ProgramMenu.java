package ui;

import java.io.File;
import java.util.Scanner;

import algoritmo.AlgoritmoGrafo;
import algoritmo.BellmanFord;
import algoritmo.BuscaLargura;
import algoritmo.BuscaProfundidade;
import algoritmo.Kruskal;
import algoritmo.Prim;
import data.Grafo;
import util.CarregarArquivo;
import util.ErrorCodes;

	public class ProgramMenu {

		private Scanner scan;
		private AlgoritmoGrafo algoritmoGrafo;
		private Grafo grafo;
		
		public ProgramMenu() {
			scan = new Scanner(System.in);
			grafo = null;
			algoritmoGrafo = null;
		}
		
		public Scanner scan() {
			return scan;
		}
		
		public static void clear() {
			
			System.out.println(new String(new char[30]).replace("\0", "\r\n"));
		}
		
		public static void pressEnterToContinue(){ 
		       
			System.out.println("Pressione 'Enter' para continuar...");
		    try{
		    	System.in.read();
		    }catch(Exception e){
		    	;
		    }  
		 }
		
		public static void printMessageAndClear(String msg) {
			
			System.out.println("\t" + msg);
			pressEnterToContinue();
			clear();
		}
		
		public void selecionarComando() {
			
			System.out.print("\n\tComando: ");
		}
		
		public void selecionarNumeroVertice() {
			
			System.out.print("\tNúmero de Vértice: ");
		}
		
		public void digitarCaminhoArquivoGrafo() {
			
			System.out.print("Caminho do Arquivo: ");
		}
		
		public void tituloMenuPrincipal() {
			
			System.out.println("\t============================================");
			System.out.println("\t| Trabalho II (PAA) - Algoritmos em Grafos |");
			System.out.println("\t============================================");
			System.out.println("\n");
		}
		
		public void tituloMenuAlgoritmo() {
			
			System.out.println("\t=================================");
			System.out.println("\t| Opções de Algoritmo em Grafos |");
			System.out.println("\t=================================");
			System.out.println("\n");	
		}
		
		public void opcaoMenuPrincipal() {
			
			System.out.println("\t\t1 - Carregar Grafo");
			System.out.println("\t\t2 - Executar Algoritmo para Grafo");
			System.out.println("\t\t3 - Desenhar Grafo");
			System.out.println("\t\t0 - Finalizar Programa");
		}
		
		public void opcaoMenuAlgoritmo() {
			
			System.out.println("\t\t1 - Busca em Profundidade (DFS)");
			System.out.println("\t\t2 - Busca em Largura (BFS)");
			System.out.println("\t\t3 - Menor Caminho: Bellman-Ford");
			System.out.println("\t\t4 - Árvore Geradora Mínima: Kruskal");
			System.out.println("\t\t5 - Árvore Geradora Mínima: Prim");
			System.out.println("\t\t0 - Voltar");
		}
		
		public void menuAlgoritmo() {
			
			tituloMenuAlgoritmo();
			opcaoMenuAlgoritmo();
			selecionarComando();
			int comando, nroVertice;
			try {
				comando = scan().nextInt();
			}catch(Exception e) {
				comando = -1;
			}
			AlgoritmoGrafo g = null;
			switch(comando) {
				case 1:		try {
								nroVertice = scan().nextInt();
								if(nroVertice >= grafo.getQtdVertice()) {
									printMessageAndClear(ErrorCodes.INVALID_VERTEX_INPUT.getMessage());
								}else {
									g = new BuscaProfundidade(grafo,nroVertice);
								}
							}catch(Exception e) {
								printMessageAndClear(ErrorCodes.INVALID_COMMAND_INPUT.getMessage());
							}						
				break;
				case 2:		try {
								nroVertice = scan().nextInt();
								if(nroVertice >= grafo.getQtdVertice()) {
									printMessageAndClear(ErrorCodes.INVALID_VERTEX_INPUT.getMessage());
								}else {
									g = new BuscaLargura(grafo,nroVertice);
								}
							}catch(Exception e) {
								printMessageAndClear(ErrorCodes.INVALID_COMMAND_INPUT.getMessage());
							}
				break;
				case 3:		if(!grafo.isOrientado()) {
								printMessageAndClear(ErrorCodes.INVALID_GRAPH_ORIENTATION_BELLMAN_FORD.getMessage());
							}else {
								try {
									nroVertice = scan().nextInt();
									if(nroVertice >= grafo.getQtdVertice()) {
										printMessageAndClear(ErrorCodes.INVALID_VERTEX_INPUT.getMessage());
									}else {
										g = new BellmanFord(grafo,nroVertice);
									}
								}catch(Exception e) {
									printMessageAndClear(ErrorCodes.INVALID_COMMAND_INPUT.getMessage());
								}	
							}
				break;
				case 4:		g = new Kruskal(grafo);
				break;
				case 5:		try {
								nroVertice = scan().nextInt();
								if(nroVertice >= grafo.getQtdVertice()) {
									printMessageAndClear(ErrorCodes.INVALID_VERTEX_INPUT.getMessage());
								}else {
									g = new Prim(grafo,nroVertice);
								}
							}catch(Exception e) {
								printMessageAndClear(ErrorCodes.INVALID_COMMAND_INPUT.getMessage());
							}
				break;
				case 0:		menuPrincipal();
				break;
				default:	printMessageAndClear(ErrorCodes.INVALID_COMMAND_INPUT.getMessage());
							menuAlgoritmo();
				break;
			}
			if(g != null) {
				this.algoritmoGrafo = g;
				try {
					g.run();
					g.imprimeResultado();
				}catch(Exception e) {
					printMessageAndClear(ErrorCodes.ERROR_EXECUTION_ALGORITHM.getMessage());
					// e.printStackTrace();
				}
			}
			menuAlgoritmo();
		}
		
		public void menuPrincipal() {
			
			tituloMenuPrincipal();
			opcaoMenuPrincipal();
			selecionarComando();
			int comando;
			try {
				comando = scan().nextInt();
			} catch (Exception e) {
				comando = -1;
			}
			switch(comando) {
				case 1:		digitarCaminhoArquivoGrafo();
							String caminho = scan().nextLine();
							CarregarArquivo ca = new CarregarArquivo(new File(caminho));
							grafo = ca.converteArquivo();
							String msg;
							if(grafo == null) {
								msg = ("\t" + ErrorCodes.INVALID_FILE_INPUT.getMessage());
							}else {
								msg = "\tGrafo carregado com sucesso!";
							}
							printMessageAndClear(msg);
				break;
				case 2:		menuAlgoritmo();
				break;
				case 3:		// Desenhar Grafo
				break;
				case 0:		Runtime.getRuntime().exit(0);
				break;
				default:	printMessageAndClear(ErrorCodes.INVALID_COMMAND_INPUT.getMessage());
				break;
			}
			menuPrincipal();
			
		}
		
		public static void main(String[] strgs) {
			
			ProgramMenu p = new ProgramMenu();
			p.menuPrincipal();
		}
	}
