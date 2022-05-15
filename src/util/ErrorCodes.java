package util;

	public enum ErrorCodes {
	
		ERROR_EXECUTION_ALGORITHM("Erro na Execução do Algoritmo"),
		ERROR_GRAPH_DRAW_FAILED("Erro ao Desenhar Grafo"),
		ERROR_NO_GRAPH_LOADED("Nenhum Grafo Carregado!"),
		INVALID_COMMAND_INPUT("Comando Inválido"),
		INVALID_FILE_INPUT("Arquivo Inválido"),
		INVALID_GRAPH_ORIENTATION_BELLMAN_FORD("Orientação de Grafo Inválida! (algoritmo Bellman-Ford)"),
		INVALID_VERTEX_INPUT("Número de Vértice Inválido"),
		NEGATIVE_CYCLE_BELLMAN_FORD("Existe Ciclo Negativo! (algoritmo Bellman-Ford)"),
		;
		
		private String message;
	
		ErrorCodes(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

		public void setMsg(String message) {
			this.message = message;
		}
		
	}
