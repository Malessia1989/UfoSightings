package it.polito.tdp.ufo.model;

import java.time.Year;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.traverse.DepthFirstIterator;

import it.polito.tdp.ufo.db.SightingsDAO;

public class Model {
	
	SightingsDAO dao;
	Graph<String,DefaultEdge> grafo;
	List<String> stati;
	
	
	public Model() {
		this.dao= new SightingsDAO();
		
		
	}

	public List<AnnoCount> getAnnoCount() {
		
		return dao.getAnnoCount();
	}
	
	public void creaGrafo(Year anno) {
		this.grafo=new SimpleDirectedGraph<>(DefaultEdge.class);
		this.stati=dao.getStati(anno);
		Graphs.addAllVertices(grafo, this.stati);
		
		for(String s1: grafo.vertexSet()) {
			for(String s2: grafo.vertexSet()) {
				if(!s1.equals(s2)) {
					if(dao.esisteArco(s1,s2,anno)) {
						grafo.addEdge(s1, s2);
					}
				}
			}
		}
		System.out.println("numero vertici : "+grafo.vertexSet().size());
		System.out.println("numero archi : "+grafo.edgeSet().size());
	}

	public List <String> getStati() {
		return this.stati;
	}
	
	public List<String > getSuccessori(String stato){
		return Graphs.successorListOf(grafo, stato);
	}
	
	public List<String > getPredecessori(String stato){
		return Graphs.predecessorListOf(grafo, stato);
	}
	public List<String> getRaggiungibili(String stato){
		List<String> raggiungibili = new LinkedList<>();
		DepthFirstIterator<String, DefaultEdge> dp = new DepthFirstIterator<String, DefaultEdge>(this.grafo,stato);
		
		dp.next();	//escludo lo stato di partenza
		while(dp.hasNext()) {
			raggiungibili.add(dp.next());
		}
		
	
		
		return raggiungibili;
	}

}
