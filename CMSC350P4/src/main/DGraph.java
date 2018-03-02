package main;

import java.io.*;
import java.util.*;

public class DGraph<T> {
	private List<LinkedList<Integer>> adjacencyList;
	private Map<T, Integer> mapTtoInteger;
	
	public DGraph() {
		adjacencyList = new ArrayList<LinkedList<Integer>>();
		mapTtoInteger = new HashMap<T, Integer>();
	}
	
	public void buildDGraphFromFile(File specification) {
		
	}
	
	public void addVertex(T vertex) {
		
	}
	
	public void addEdge(T vertexFrom, T vertexTo) {
		
	}
	
	public String topOrdGeneration(T startVertex) {
		return "";
	}
}
