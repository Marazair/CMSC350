package main;

import java.io.*;
import java.util.*;

public class DGraph<T> {
	private List<LinkedList<Integer>> adjacencyList;
	private Map<T, Integer> mapTtoInteger;
	private int index;
	
	public DGraph() {
		adjacencyList = new ArrayList<LinkedList<Integer>>();
		mapTtoInteger = new HashMap<T, Integer>();
		index = 0;
	}
	
	public void buildDGraphFromFile(File spec) throws FileNotFoundException {
		if (spec.canRead()) {
			Scanner fileScanner = new Scanner(spec);
			
			while(fileScanner.hasNext()) {
				Scanner lineScanner = new Scanner(fileScanner.nextLine());
				boolean isFirst = true;
				
				while(lineScanner.hasNext()) {
					String token = lineScanner.next();
					if(isFirst) {
						isFirst = false;
					}
				}
				
				lineScanner.close();
				
			}
			fileScanner.close();
		}
		else {
			throw new FileNotFoundException();
		}
	}
	
	public void addVertex(T vertex) {
		if(!mapTtoInteger.containsKey(vertex)){
			mapTtoInteger.put(vertex, index);
			index++;
		}
	}
	
	public void addEdge(T vertexFrom, T vertexTo) {
		
		if(mapTtoInteger.containsKey(vertexFrom) && mapTtoInteger.containsKey(vertexTo))
			adjacencyList.get(mapTtoInteger.get(vertexFrom)).add(mapTtoInteger.get(vertexTo));
	}
	
	public String topOrdGeneration(T startVertex) {
		return "";
	}
}
