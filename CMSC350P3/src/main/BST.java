package main;

public class BST<T extends Comparable<T>>{
	private BSTNode<T> root;
	
	
	public BST(T value) {
		root = new BSTNode<T>(value);
	}
	
	class BSTNode<T2 extends Comparable<T2>>{
		private T2 value;
		private BSTNode<T2> left;
		private BSTNode<T2> right;
		private boolean read;
		
		BSTNode(T2 value) {
			this.value = value;
			read = false;
		}
		
		public boolean beenRead() {
			return read;
		}
		
		public void setRead() {
			read = true;
		}
	}
	
	
	public void insertNode(T value) {
		insertNodeRecursive(value, root);
	}
	
	public void insertNodeRecursive(T value, BSTNode<T> node) {
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
		return inOrderRecursive(root);
	}
	
	private String inOrderRecursive(BSTNode<T> node) {
		String leftValue = "";
		String rightValue = "";
		if(node.left != null)
			leftValue = inOrderRecursive(node.left) + " ";
		if(node.right != null)
			rightValue = " " + inOrderRecursive(node.right); 
		return leftValue + node.value + rightValue;
	}
	
	public String reverseOrderTraversal() {
		return reverseOrderRecursive(root);
	}
	
	private String reverseOrderRecursive(BSTNode<T> node) {
		String leftValue = "";
		String rightValue = "";
		if(node.left != null)
			leftValue = " " + reverseOrderRecursive(node.left);
		if(node.right != null)
			rightValue = reverseOrderRecursive(node.right) + " ";
		return rightValue + node.value + leftValue;
			
	}
	
}
