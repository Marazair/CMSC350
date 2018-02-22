package main;

public class BST<T extends Comparable<T>>{
	BSTNode<T> root;
	
	public BST(T value) {
		root = new BSTNode<T>(value);
	}
	
	
	public void insertNode(T value) {
		if (root == null)
			root = new BSTNode<T>(value);
		else
			insertNodeRecursive(value, root);
	}
	
	public void insertNodeRecursive(T value, BSTNode<T> root) {
		BSTNode<T> node = new BSTNode<T>(value);
		
		if (value.compareTo(node.value) < 0) {
			if (node.left == null) {
				node.left = new BSTNode<T>(value);
			}
			else {
				insertNodeRecursive(value, node.left);
			}
		}
		else {
			if (node.right == null) {
				node.right = new BSTNode<T>(value);
			}
			else {
				insertNodeRecursive(value, node.right);
			}
		}
	}
	
	public String inOrderTraversal() {
		return "";
	}
	
	
	class BSTNode<T extends Comparable<T>>{
		private T value;
		private BSTNode<T> left;
		private BSTNode<T> right;
		
		BSTNode(T value) {
			this.value = value;
		}
	}
}
