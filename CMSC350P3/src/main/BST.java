package main;

public class BST<T extends Comparable<T>>{
	BSTNode root;
	
	public BST(T value) {
		root = new BSTNode<T>(value);
	}
	
	
	public void insertNode(T value) {
		insertNodeRecursive(value, root);
	}
	
	public void insertNodeRecursive(T value, BSTNode root) {
		BSTNode<T> node = new BSTNode<T>(value);
	}
	
	public String inOrderTraversal() {
		return "";
	}
	
	
	class BSTNode<T extends Comparable<T>>{
		private T value;
		private BSTNode left;
		private BSTNode right;
		
		BSTNode(T value) {
			this.value = value;
		}
	}
}
