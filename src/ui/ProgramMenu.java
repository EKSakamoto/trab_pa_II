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
import util.GraphDrawer;

/*
 * Clase inicializadora de programa, sendo também resposável pela interface do programa, que permite a execução 
 * de algoritmos em grafos, conforme a especificação do trabalho 2 da disciplina de Projeto e Análise de Algoritmos 
 * 
 * @author Eduardo Sakamoto
 */

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
		
		public int readInt() {
			int n = scan().nextInt();
			scan().nextLine();
			return n;
		}
	
		/*
		 * Método que realiza a 'limpeza' do console
		 * Pré-condição: Nenhuma
		 * Pós-condição: Nenhuma
		 */
		public static void clear() {
			
			System.out.println(new String(new char[30]).replace("\0", "\r\n"));
		}
		
		/*
		 * Método que realiza a requisição de interação com o usuário
		 * Pré-condição: Nenhuma
		 * Pós-condição: Interação com usuário
		 */
		public static void pressEnterToContinue(){ 
		       
			System.out.println("\n\tPressione 'Enter' para continuar...");
		    try{
		    	System.in.read();
		    }catch(Exception e){
		    	;
		    }  
		 }
		
		/*
		 * Método que realiza a impressão de uma mensagem, limpando o console em seguida
		 * Pré-condição: Nenhuma
		 * Pós-condição: Nenhuma
		 */
		public static void printMessageAndClear(String msg) {
			
			System.out.println("\n\t" + msg);
			pressEnterToContinue();
			clear();
		}
		
		/*
		 * Método que realiza a impressão de uma mensagem (como erro), limpando o console em seguida
		 * Pré-condição: Nenhuma
		 * Pós-condição: Nenhuma
		 */
		public static void printErrorAndClear(String msg) {
			
			System.err.println("\n\t" + msg);
			pressEnterToContinue();
			clear();
		}
		
		/*
		 * Método que reliza a impressão de label voltado para um comando no menu
		 * Pré-condição: Nenhuma
		 * Pós-condição: Nenhuma
		 */
		public void selecionarComando() {
			
			System.out.print("\n\tComando: ");
		}
		
		/*
		 * Método que realiza a impressão de label voltado para um número de vértice de origem no menu
		 * Pré-condição: Nenhuma
		 * Pós-condição: Nenhuma
		 */
		public void selecionarNumeroVertice() {
			
			System.out.print("\n\tNúmero de Vértice de Origem: ");
		}
		
		/*
		 * Método que realiza a impressão de label voltado para um caminho de arquivo no menu
		 * Pré-condição: Nenhuma
		 * Pós-condição: Nenhuma
		 */
		public void digitarCaminhoArquivoGrafo() {
			
			System.out.print("\n\tCaminho do Arquivo: ");
		}
		
		/*
		 * Método que realiza a impressão do título do menu principal
		 * Pré-condição: Nenhuma
		 * Pós-condição: Nenhuma
		 */
		public void tituloMenuPrincipal() {
			
			System.out.println("\t============================================");
			System.out.println("\t| Trabalho II (PAA) - Algoritmos em Grafos |");
			System.out.println("\t============================================");
			System.out.println();
		}
		
		/*
		 * Metodo que realiza a impressão do título do menu de algoritmo
		 * Pré-condição: Nenhuma
		 * Pós-condição: Nenhuma
		 */
		public void tituloMenuAlgoritmo() {
			
			System.out.println("\t=================================");
			System.out.println("\t| Opções de Algoritmo em Grafos |");
			System.out.println("\t=================================");
			System.out.println();	
		}
		
		/*
		 * Método que realiza a impressão de opções de comando do menu principal
		 * Pré-condição: Nenhuma
		 * Pós-condição: Nenhuma
		 */
		public void opcaoMenuPrincipal() {
			
			System.out.println("\t\t1 - Carregar Grafo");
			System.out.println("\t\t2 - Executar Algoritmo para Grafo");
			System.out.println("\t\t3 - Desenhar Grafo");
			System.out.println("\t\t0 - Finalizar Programa");
		}
		
		/*
		 * Método que realiza a impressão de opções de comando do menu de algoritmo
		 * Pré-condição: Nenhuma
		 * Pós-condição: Nenhuma
		 */
		public void opcaoMenuAlgoritmo() {
			
			System.out.println("\t\t1 - Busca em Profundidade (DFS)");
			System.out.println("\t\t2 - Busca em Largura (BFS)");
			System.out.println("\t\t3 - Menor Caminho: Bellman-Ford");
			System.out.println("\t\t4 - Árvore Geradora Mínima: Kruskal");
			System.out.println("\t\t5 - Árvore Geradora Mínima: Prim");
			System.out.println("\t\t0 - Voltar");
		}
		
		/*
		 * Método que realiza as interações presentes no menu de algoritmo
		 * Pré-condição: Grafo não nulo
		 * Pós-condição: Nenhuma
		 */
		public void menuAlgoritmo() {
			
			tituloMenuAlgoritmo();
			opcaoMenuAlgoritmo();
			selecionarComando();
			int comando, nroVertice;
			try {
				comando = readInt();
			}catch(Exception e) {
				comando = -1;
			}
			AlgoritmoGrafo g = null;
			switch(comando) {
				case 1:		try {
					selecionarNumeroVertice();			
					nroVertice = scan().nextInt();
								if(nroVertice >= grafo.getQtdVertice()) {
									printErrorAndClear(ErrorCodes.INVALID_VERTEX_INPUT.getMessage());
								}else {
									g = new BuscaProfundidade(grafo,nroVertice);
								}
							}catch(Exception e) {
								printErrorAndClear(ErrorCodes.INVALID_COMMAND_INPUT.getMessage());
							}						
				break;
				case 2:		try {
								selecionarNumeroVertice();
								nroVertice = scan().nextInt();
								if(nroVertice >= grafo.getQtdVertice()) {
									printErrorAndClear(ErrorCodes.INVALID_VERTEX_INPUT.getMessage());
								}else {
									g = new BuscaLargura(grafo,nroVertice);
								}
							}catch(Exception e) {
								printErrorAndClear(ErrorCodes.INVALID_COMMAND_INPUT.getMessage());
							}
				break;
				case 3:		if(!grafo.isOrientado()) {
								printErrorAndClear(ErrorCodes.INVALID_GRAPH_ORIENTATION_BELLMAN_FORD.getMessage());
							}else {
								try {
									selecionarNumeroVertice();
									nroVertice = readInt();
									if(nroVertice >= grafo.getQtdVertice()) {
										printErrorAndClear(ErrorCodes.INVALID_VERTEX_INPUT.getMessage());
									}else {
										g = new BellmanFord(grafo,nroVertice);
									}
								}catch(Exception e) {
									printErrorAndClear(ErrorCodes.INVALID_COMMAND_INPUT.getMessage());
								}	
							}
				break;
				case 4:		g = new Kruskal(grafo);
				break;
				case 5:		try {
								selecionarNumeroVertice();
								nroVertice = readInt();
								if(nroVertice >= grafo.getQtdVertice()) {
									printErrorAndClear(ErrorCodes.INVALID_VERTEX_INPUT.getMessage());
								}else {
									g = new Prim(grafo,nroVertice);
								}
							}catch(Exception e) {
								printErrorAndClear(ErrorCodes.INVALID_COMMAND_INPUT.getMessage());
							}
				break;
				case 0:		printMessageAndClear("");			
							menuPrincipal();
				break;
				default:	printErrorAndClear(ErrorCodes.INVALID_COMMAND_INPUT.getMessage());
							menuAlgoritmo();
				break;
			}
			if(g != null) {
				this.algoritmoGrafo = g;
				try {
					clear();
					this.algoritmoGrafo.run();
					printMessageAndClear("Algoritmo \'" + g.getTipoAlgoritmo() + "\' Executado com Sucesso!");
					menuAlgoritmo();
				}catch(Exception e) {
					printErrorAndClear(ErrorCodes.ERROR_EXECUTION_ALGORITHM.getMessage());
					menuAlgoritmo();
					// e.printStackTrace();
				}
			}else {
				printErrorAndClear(ErrorCodes.ERROR_NO_GRAPH_LOADED.getMessage());
				menuAlgoritmo();
			}
		}
		
		/*
		 * Método que realiza as interações presentes no menu principal
		 * Pré-condição: Nenhuma
		 * Pós-condição: Nenhuma
		 */
		public void menuPrincipal() {
			
			tituloMenuPrincipal();
			opcaoMenuPrincipal();
			selecionarComando();
			int comando;
			try {
				comando = readInt();
			} catch (Exception e) {
				comando = -1;
			}
			switch(comando) {
				case 1:		digitarCaminhoArquivoGrafo();
							String caminho = scan().nextLine();
							CarregarArquivo ca = new CarregarArquivo(new File(caminho));
							grafo = ca.converteArquivo();
							if(grafo == null) {
								printErrorAndClear(ErrorCodes.INVALID_FILE_INPUT.getMessage());
							}else {
								printMessageAndClear("Grafo carregado com sucesso!");
							}
				break;
				case 2:		if(grafo == null) {
								printErrorAndClear(ErrorCodes.ERROR_NO_GRAPH_LOADED.getMessage());
							}else {
								clear();
								menuAlgoritmo();
							}
				break;
				case 3:		if(grafo == null) {
								printErrorAndClear(ErrorCodes.ERROR_NO_GRAPH_LOADED.getMessage());
							}else {
								GraphDrawer drawer;
								switch(algoritmoGrafo.getTipoAlgoritmo()) {
								case "Árvore Geradora Mínima - Kruskal":		
									drawer = new GraphDrawer(grafo,((Kruskal) algoritmoGrafo).getArestaArvoreGeradora());
								break;
								case "Árvore Geradora Mínima - Prim":
									drawer = new GraphDrawer(grafo,((Prim) algoritmoGrafo).getArestaArvoreGeradora());
								break;
								default:
									drawer = new GraphDrawer(grafo);
								break;
								}
								try {
									clear();
									drawer.drawGeneralGraph();
									printMessageAndClear("");
								}catch(Exception e) {
									// e.printStackTrace();
									printErrorAndClear(ErrorCodes.ERROR_GRAPH_DRAW_FAILED.getMessage());
								}
							}
				break;
				case 0:		System.out.println("\n\tPrograma Finalizado com Sucesso!");
							Runtime.getRuntime().exit(0);
				break;
				default:	printErrorAndClear(ErrorCodes.INVALID_COMMAND_INPUT.getMessage());
				break;
			}
			menuPrincipal();
			
		}

		/* TODO
		 * Testar os algoritmos				-> Falta o Prim e testes básicos para outros algoritmos
		 * DFS, BFS e Kruskal aparentemente -> OK: Testar para grafos orientados
		 * Arrumar Prim
		 * Arrumar Interface				
		 * Desenhar grafo 					-> OK
		 */
		
		/*
		 * Método main, responsável pela inicialização do programa principal
		 * Pré-condição: Nenhuma
		 * Pós-condição: Nenhuma
		 */
		public static void main(String[] strgs) {
			
			ProgramMenu p = new ProgramMenu();
			p.menuPrincipal();
		}
	}
