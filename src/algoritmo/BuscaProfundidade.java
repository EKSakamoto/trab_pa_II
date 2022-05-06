package algoritmo;

import java.util.ArrayList;

import data.Cor;
import data.Grafo;
import data.Vertice;

	public class BuscaProfundidade extends AlgoritmoGrafo{

		private Vertice verticeOrigem;
		private ArrayList<Integer> ordemVertice;
		private Cor[] cor;
		
		public BuscaProfundidade() {
			super();
		}
		
		public BuscaProfundidade(Grafo grafo, int nroVerticeOrigem) {
			super(grafo,"Busca em Profundidade (DFS)");
			if(nroVerticeOrigem >= grafo.getQtdVertice()) {
				// ERROR CODE -> INPUT >= VERTEX QUANTITY
				// ERRO ou considerar último vértice?
			}
			this.ordemVertice = new ArrayList<>();
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

		public Cor[] getCor() {
			return cor;
		}

		public void setCor(Cor[] cor) {
			this.cor = cor;
		}

		public void inicializaDFS() {
			
			for(Vertice v : this.getGrafo().getListaVertice()) {
				cor[v.getNroVertice()] = Cor.BRANCO;
			}
		}
		
		public void DFS_visit(Vertice v) {
			
			try {
				cor[v.getNroVertice()] = Cor.CINZA;
				ArrayList<Vertice> listaAdjacente = this.getGrafo().getMapaAdjacencia().get(v);
				for(Vertice adj : listaAdjacente) {
					if(cor[adj.getNroVertice()] == Cor.BRANCO) {
						ordemVertice.add(adj.getNroVertice());
						DFS_visit(adj);
					}
				}
				cor[v.getNroVertice()] = Cor.PRETO;
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public ArrayList<Vertice> gerarListaVertice_Origem(){
			
			ArrayList<Vertice> list = this.getGrafo().getListaVertice();
			list.remove(verticeOrigem);
			list.add(0, verticeOrigem);
			return list;
		}
		
		public void DFS() {
			
			try {
				inicializaDFS();
				ArrayList<Vertice> list = gerarListaVertice_Origem();
				for(Vertice u : list) {
					if(cor[u.getNroVertice()] == Cor.BRANCO) {
						ordemVertice.add(u.getNroVertice());
						DFS_visit(u);
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
				// ERROR CODE?
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
			DFS();
		}
	}
