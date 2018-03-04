/*
 * File Name: DGraph.java
 * Name: Nick Mills
 * Date: 2/28/18
 * Purpose: Contains the structure for building the directed graph and providing the dependency lists.
 */
package main;

import java.util.*;
import java.util.Map.*;

public class DGraph<T> {
	private List<LinkedList<Integer>> adjacencyList;
	private Map<T, Integer> mapTtoInteger;
	private int index;
	private StringBuffer topOrd;
	private List<Integer> seen;
	
	//Basic constructor initializes local variables to default values.
	public DGraph() {
		adjacencyList = new ArrayList<LinkedList<Integer>>();
		mapTtoInteger = new HashMap<T, Integer>();
		index = 0;
		topOrd = new StringBuffer("");
		seen = new ArrayList<Integer>();
	}
	
	//Builds a directional graph based on the passed list.
	public void buildDGraph(List<ArrayList<T>> spec) throws InvalidClassName {
		int specSize = spec.size();
		
		//Reset the graph in case this isn't the first one built.
		adjacencyList.clear();
		mapTtoInteger.clear();
		index = 0;
		
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
	}
	
	//Adds a vertex if it does not already exist.
	public void addVertex(T vertex) {
		if(!mapTtoInteger.containsKey(vertex)){
			mapTtoInteger.put(vertex, index);
			adjacencyList.add(index, new LinkedList<Integer>());
			index++;
		}
	}
	
	//Adds an edge between passed vertices if they exist.
	public void addEdge(T vertexFrom, T vertexTo) throws InvalidClassName {
		if(mapTtoInteger.containsKey(vertexFrom) && mapTtoInteger.containsKey(vertexTo))
			adjacencyList.get(mapTtoInteger.get(vertexFrom)).add(mapTtoInteger.get(vertexTo));
		else
			throw new InvalidClassName();
	}
	
	//Contains logic to create the topological order and returns it.
	public String topOrdGeneration(T startVertex) throws CycleDetected, InvalidClassName {
		
		//Make sure the class is in the map.
		if(mapTtoInteger.containsKey(startVertex)) {
			
			int startIndex = mapTtoInteger.get(startVertex);
			
			//Clears data from previous orders.
			seen.clear();
			topOrd.setLength(0);
			
			//Adds start point to the seen list and adds the class name to the string.
			seen.add(startIndex);
			topOrd.append(keyFromValue(startIndex) + " ");
			
			//Begins recursion to find dependencies.
			topOrdRecursive(startIndex);
			
			//Returns the class compilation order.
			return topOrd.toString();
		}
		
		//If no match, throw InvalidClassName.
		else {
			throw new InvalidClassName();
		}
	}
	
	//Finds dependencies in the list and appends them to the list of classes that need to be compiled.
	public void topOrdRecursive(int index) throws CycleDetected {
		int size = adjacencyList.get(index).size();
		int currentContent;
		
		//Checks each class encountered for dependencies and adds it and dependent classes to the list.
		for(int x = 0; x < size; x++) {
			currentContent = adjacencyList.get(index).get(x);
			
			//Checks to see if a class has already been encountered. If not, continue.
			if (!seen.contains(currentContent)){
				seen.add(currentContent);
				topOrd.append(keyFromValue(currentContent) + " ");
				topOrdRecursive(currentContent);
			}
			
			//If so, detect a cycle.
			else {
				throw new CycleDetected();
			}
		}
	}
	
	//Retrieves the class name from the passed value. If it's not there, returns null.
	private T keyFromValue(int value) {
		for(Entry<T, Integer> entry : mapTtoInteger.entrySet()) {
			if(value == entry.getValue())
				return entry.getKey();	
		}
		return null;
	}
}
