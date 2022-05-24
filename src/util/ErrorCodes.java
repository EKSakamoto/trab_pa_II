package util;

/**
 * Estrutura auxiliar que define determinados erros no programa
 * 
 * @author Eduardo Sakamoto
 */

	public enum ErrorCodes {
	
		ERROR_EXECUTION_ALGORITHM("Erro na Execução do Algoritmo"),
		ERROR_GRAPH_DRAW_FAILED("Erro ao Desenhar Grafo"),
		ERROR_GRAPH_NOT_PROCESSED("Grafo Não Processado por Algoritmo!"),
		ERROR_NO_GRAPH_LOADED("Nenhum Grafo Carregado!"),
		INVALID_COMMAND_INPUT("Comando Inválido"),
		INVALID_FILE_INPUT("Arquivo Inválido"),
		INVALID_GRAPH_ORIENTATION_BELLMAN_FORD("Orientação de Grafo Inválida! (algoritmo Bellman-Ford)"),
		INVALID_VERTEX_INPUT("Número de Vértice Inválido"),
		NEGATIVE_CYCLE_BELLMAN_FORD("Existe Ciclo Negativo! (algoritmo Bellman-Ford)"),
		;
		
		private String message;
	
		/**
		 * Construtor de inicialização de um ErroCode
		 * 
		 * @param message - Texto referente a mensagem de um erro
		 */
		ErrorCodes(String message) {
			this.message = message;
		}

		/**
		 * Método getter voltado para uma mensagem de erro
		 * 
		 * @return Texto referente a uma mensagem de erro
		 */
		public String getMessage() {
			return message;
		}

		/**
		 * Método setter referente a uma mensagem de erro
		 * 
		 * @param message - Parâmetro referente a uma mensagem de erro
		 */
		public void setMsg(String message) {
			this.message = message;
		}
	}