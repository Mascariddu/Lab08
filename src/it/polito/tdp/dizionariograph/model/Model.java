package it.polito.tdp.dizionariograph.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionariograph.db.WordDAO;

public class Model {
	
	WordDAO dao = new  WordDAO();
	List<String> parole = new ArrayList<String>();
	Graph<String, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);
	
	public void createGraph(int numeroLettere) {
		
		parole = dao.getAllWordsFixedLength(numeroLettere);
		
		Graphs.addAllVertices(g, parole);
		
		for(String s : parole) {
			
			for(String s2 : parole) {
				
				if(!s.equals(s2)) {
					if(controlla(s,s2))
						g.addEdge(s, s2);
				}
			}
		}
		
		System.out.println(g);

	}

	private boolean controlla(String s, String s2) {
		// TODO Auto-generated method stub
		int conto = 0;
		
		for(int i = 0 ; i < s.length() ; i++) {
			
			if(s.charAt(i) != s2.charAt(i))
				conto++;
			
			if(conto > 1)
				return false;
		}
		
		return true;
	}

	public List<String> displayNeighbours(String parolaInserita) {

		List<String> vicini = new ArrayList<String>();
		for(String s : this.parole) {
			
			if(g.containsEdge(parolaInserita, s))
				vicini.add(s);
			
		}
		
		if(vicini.size() == 0) {
			vicini.add("nessuna parola");
		}
		return vicini;
	}

	public String findMaxDegree() {
		
		int max = 0;
		String migliore = "";
		
		for(String s : g.vertexSet()) {
			 
			if(g.degreeOf(s) > max ) {
				max = g.degreeOf(s);
				migliore = s;
			}
		}
		
		return migliore;
			
	}
}
