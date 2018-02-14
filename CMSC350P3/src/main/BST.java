package main;

public class BST<T extends Comparable<T>>{
	BSTnode root;
	
	public BST() {
		
	}
	
	class BSTnode {
		private T value;
		private BSTnode left;
		private BSTnode right;
		
		BSTnode() {
			
		}
	}
}
