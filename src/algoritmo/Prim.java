package algoritmo;

import java.util.ArrayList;
import java.util.Arrays;

import data.Aresta;
import data.Grafo;
import data.Vertice;

	public class Prim extends AlgoritmoGrafo{
	
		private Vertice verticeOrigem;
		private ArrayList<Vertice> vertice_ausenteArvore;
		private ArrayList<Aresta> arestaArvoreGeradora;
		private int[] predecessor;
		private int[] key;
		private int pesoTotal;
		
		public Prim() {
			super();
		}
		
		public Prim(Grafo grafo, int vertice) {
			super(grafo,"Árvore Geradora Mínima - Prim");
			verticeOrigem = this.getGrafo().getVerticeEspecifico(vertice);
			arestaArvoreGeradora = new ArrayList<>();
			vertice_ausenteArvore = new ArrayList<>();
			predecessor = new int[this.getGrafo().getQtdVertice()];
			key = new int[this.getGrafo().getQtdVertice()];
			pesoTotal = 0;
		}
		
		public Vertice getVerticeOrigem() {
			return verticeOrigem;
		}

		public void setVerticeOrigem(Vertice verticeOrigem) {
			this.verticeOrigem = verticeOrigem;
		}

		public ArrayList<Aresta> getArestaArvoreGeradora() {
			return arestaArvoreGeradora;
		}

		public void setArestaArvoreGeradora(ArrayList<Aresta> arestaArvoreGeradora) {
			this.arestaArvoreGeradora = arestaArvoreGeradora;
		}

		public int getPesoTotal() {
			return pesoTotal;
		}

		public void setPesoTotal(int pesoTotal) {
			this.pesoTotal = pesoTotal;
		}
		
		public void inicializaOrigem() {
			
			for(Vertice v : this.getGrafo().getListaVertice()) {
				key[v.getNroVertice()] = Integer.MAX_VALUE;
				vertice_ausenteArvore.add(v);
			}
			key[verticeOrigem.getNroVertice()] = 0;
			predecessor[verticeOrigem.getNroVertice()] = -1;
			vertice_ausenteArvore.remove(verticeOrigem);
			vertice_ausenteArvore.add(0,verticeOrigem);
		}
		
		public Vertice extraiVertice_MinimoKey(ArrayList<Vertice> list) {
			
			int menor = Integer.MAX_VALUE;
			Vertice verticeMenor = null;
			for(Vertice v : list) {
				if(key[v.getNroVertice()] < menor) {
					menor = key[v.getNroVertice()];
					verticeMenor = v;
				}
			}
			return verticeMenor;
		}
		
		public void arvoreGeradoraMinima_Prim() {
			
			inicializaOrigem();
			while(!vertice_ausenteArvore.isEmpty()) {
				System.out.println(Arrays.toString(key));
				Vertice u = extraiVertice_MinimoKey(vertice_ausenteArvore);
				vertice_ausenteArvore.remove(u);
				for(Vertice adj : this.getGrafo().getMapaAdjacencia().get(u)) {
					Aresta a = this.getGrafo().getArestaEspecifica(u,adj);
					if(!vertice_ausenteArvore.contains(adj) &&
					   a.getPeso() < key[adj.getNroVertice()]) {
						predecessor[adj.getNroVertice()] = u.getNroVertice();
						key[adj.getNroVertice()] = a.getPeso();
//						arestaArvoreGeradora.add(a);
					// Verificar algoritmo
					}	
				}
			}
			calculaPesoTotal();
		}
		
		public void calculaPesoTotal() {
			
			for(Aresta a : arestaArvoreGeradora) {
				pesoTotal += a.getPeso();
			}
		}
		
		@Override
		public void imprimeResultado() {

			StringBuilder str = new StringBuilder();
			str.append("vértice inicial: ").append(this.getVerticeOrigem().getNroVertice()).append("\n")
			   .append("peso total: ").append(this.getPesoTotal()).append("\n")
			   .append("arestas: ");
			for(Aresta a : this.getArestaArvoreGeradora()) {
				str.append(a.uvRepresentation()).append(" ");
			}
			str.append("\n");
			System.out.println(str.toString());
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			arvoreGeradoraMinima_Prim();
			imprimeResultado();
		}
		
	}
