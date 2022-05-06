package algoritmo;

import java.util.ArrayList;

import data.Cor;
import data.Grafo;
import data.Vertice;

	public class BuscaLargura extends AlgoritmoGrafo{
	
		private Vertice verticeOrigem;
		private ArrayList<Integer> ordemVertice;
		private int[] distancia;
		private Cor[] cor;
		
		public BuscaLargura() {
			super();
		}
		
		public BuscaLargura(Grafo grafo, int nroVerticeOrigem) {
			super(grafo,"Busca em Largura (BFS)");
			this.ordemVertice = new ArrayList<>();
			distancia = new int[this.getGrafo().getQtdVertice()];
			cor = new Cor[this.getGrafo().getQtdVertice()];
			this.verticeOrigem = this.getGrafo().getVerticeEspecifico(nroVerticeOrigem);
		}
		
		public Vertice getVerticeOrigem() {
			return verticeOrigem;
		}

		public void setVerticeOrigem(Vertice verticeOrigem) {
			this.verticeOrigem = verticeOrigem;
		}

		public ArrayList<Integer> getOrdemVertice() {
			return ordemVertice;
		}

		public void setOrdemVertice(ArrayList<Integer> ordemVertice) {
			this.ordemVertice = ordemVertice;
		}

		public int[] getDistancia() {
			return distancia;
		}

		public void setDistancia(int[] distancia) {
			this.distancia = distancia;
		}

		public Cor[] getCor() {
			return cor;
		}

		public void setCor(Cor[] cor) {
			this.cor = cor;
		}

		public void inicializaBFS() {
			
			for(int i = 0 ; i < this.getGrafo().getQtdVertice() ; i++) {
				cor[i] = Cor.BRANCO;
				distancia[i] = Integer.MAX_VALUE; // Infinito
			}
			cor[this.verticeOrigem.getNroVertice()] = Cor.CINZA;
			distancia[this.verticeOrigem.getNroVertice()] = 0;
			ordemVertice.add(this.verticeOrigem.getNroVertice());
		}
		
		public void BFS_visit() {
			
			try {
				inicializaBFS();
				ArrayList<Vertice> fila = new ArrayList<>();
				fila.add(this.verticeOrigem);
				while(!fila.isEmpty()) {
					Vertice v = fila.get(0);
					ArrayList<Vertice> listaAdjacente = this.getGrafo().getMapaAdjacencia().get(v);
					for(Vertice adj : listaAdjacente) {
						if(cor[adj.getNroVertice()] == Cor.BRANCO) {
							cor[adj.getNroVertice()] = Cor.CINZA;
							distancia[adj.getNroVertice()] = distancia[v.getNroVertice()] + 1;
							ordemVertice.add(adj.getNroVertice());
							fila.add(adj);
						}
					}
					fila.remove(0);
					cor[v.getNroVertice()] = Cor.PRETO;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void imprimeResultado() {
		
			StringBuilder str = new StringBuilder();
			for(Integer nroVertice : ordemVertice) {
				str.append(nroVertice).append(" - ");
			}
			str.replace(str.lastIndexOf(" - "),str.toString().length()-1,"");
			System.out.println(str.toString());
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			BFS_visit();
		}
	}
