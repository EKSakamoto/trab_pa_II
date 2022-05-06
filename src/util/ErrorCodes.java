package util;

	public enum ErrorCodes {
		
		INVALID_FILE_INPUT("Arquivo Inválido"),
		INVALID_COMMAND_INPUT("Comando Inválido"),
		INVALID_VERTEX_INPUT("Número de Vértice Inválido"),
		ERROR_EXECUTION_ALGORITHM("Erro na Execução do Algoritmo"),
		INVALID_GRAPH_ORIENTATION_BELLMAN_FORD("Orientação de Grafo Inválida (p/ algoritmo Bellman-Ford)")
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
