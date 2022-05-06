package data;

import java.util.ArrayList;

	@SuppressWarnings("serial")
	public class ConjuntoVertice extends ArrayList<Vertice>{
	
		public ConjuntoVertice() {
			super();
		}
		
		public ConjuntoVertice(Vertice v) {
			super();
			this.add(v);
		}
		
		public ConjuntoVertice(ArrayList<Vertice> list) {
			super(list);
		}
			
		public void union(ConjuntoVertice conj) {
			
			// Se não for funcional, utilizar comparação com dois for
			for(Vertice v : conj) {
				if(!this.contains(v))	this.add(v);
			}
		}
		
		public boolean distintoConjuntoVertice(ConjuntoVertice conj) {
			
			for(Vertice v : this) {
				for(Vertice u : conj) {
					if(v.getNroVertice() == u.getNroVertice())	return false;
				}
			}
			return true;
		}
		
	}
