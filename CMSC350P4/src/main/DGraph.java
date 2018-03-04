package main;

import java.util.*;
import java.util.Map.*;

public class DGraph<T> {
	private List<LinkedList<Integer>> adjacencyList;
	private Map<T, Integer> mapTtoInteger;
	private int index;
	private StringBuffer topOrd;
	List<Integer> seen;
	
	public DGraph() {
		adjacencyList = new ArrayList<LinkedList<Integer>>();
		mapTtoInteger = new HashMap<T, Integer>();
		index = 0;
		topOrd = new StringBuffer("");
		seen = new ArrayList<Integer>();
	}
	
	public void buildDGraph(List<ArrayList<T>> spec) {
		int specSize = spec.size();
		
		adjacencyList.clear();
		for(int x = 0; x < specSize; x++) {
			List<T> line = spec.get(x);
			int lineSize = line.size();
			
			T first = line.get(0);
			addVertex(first);
			
			for(int y = 1; y < lineSize; y++) {
				T token = line.get(y);
				
				addVertex(token);
				addEdge(first, token);
			}
		}
		
		System.out.println(adjacencyList);
	}
	
	public void addVertex(T vertex) {
		if(!mapTtoInteger.containsKey(vertex)){
			mapTtoInteger.put(vertex, index);
			adjacencyList.add(mapTtoInteger.get(vertex), new LinkedList<Integer>());
			index++;
		}
	}
	
	public void addEdge(T vertexFrom, T vertexTo) {
		
		if(mapTtoInteger.containsKey(vertexFrom) && mapTtoInteger.containsKey(vertexTo))
			adjacencyList.get(mapTtoInteger.get(vertexFrom)).add(mapTtoInteger.get(vertexTo));
	}
	
	public String topOrdGeneration(T startVertex) throws CycleDetected, InvalidClassName {
		if(mapTtoInteger.containsKey(startVertex)) {
			int startIndex = mapTtoInteger.get(startVertex);
			seen.clear();
			topOrd.setLength(0);
			
			seen.add(startIndex);
			topOrd.append(keyFromValue(startIndex) + " ");
			
			topOrdRecursive(startIndex);
			
			System.out.println(topOrd);
			return topOrd.toString();
		}
		
		//If no match, throw InvalidClassName.
		else {
			throw new InvalidClassName();
		}
	}
	
	public void topOrdRecursive(int index) throws CycleDetected {
		int size = adjacencyList.get(index).size();
		int currentContent;
		
		for(int x = 0; x < size; x++) {
			currentContent = adjacencyList.get(index).get(x);
			
			if (!seen.contains(currentContent)){
				seen.add(currentContent);
				topOrd.append(keyFromValue(currentContent) + " ");
				topOrdRecursive(currentContent);
			}
			else {
				throw new CycleDetected();
			}
		}
	}
	
	private T keyFromValue(int value) {
		for(Entry<T, Integer> entry : mapTtoInteger.entrySet()) {
			if(value == entry.getValue())
				return entry.getKey();	
		}
		return null;
	}
}
