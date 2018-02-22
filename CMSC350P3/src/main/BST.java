package main;

public class BST<T extends Comparable<T>>{
	private BSTNode<T> root;
	
	//Constructor which sets the root of the tree.
	public BST(T value) {
		root = new BSTNode<T>(value);
	}
	
	//Basic structure for a node.
	class BSTNode<T2 extends Comparable<T2>>{
		private T2 value;
		private BSTNode<T2> left;
		private BSTNode<T2> right;
		
		BSTNode(T2 value) {
			this.value = value;
		}
	}
	
	//Recursive helper method for inserting nodes.
	public void insertNode(T value) {
		insertNodeRecursive(value, root);
	}
	
	//Cascades lower numbers to the left and higher numbers to the right.
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
	
	//Recursive helper method for reading the tree in order.
	public String inOrderTraversal(String sortOrder) {
		if (sortOrder.equals("ascend"))
			return ascendingOrderRecursive(root);
		else
			return descendingOrderRecursive(root);
	}
	
	//Reads tree in ascending order.
	private String ascendingOrderRecursive(BSTNode<T> node) {
		String leftValue = "";
		String rightValue = "";
		if(node.left != null)
			leftValue = ascendingOrderRecursive(node.left) + " ";
		if(node.right != null)
			rightValue = " " + ascendingOrderRecursive(node.right); 
		return leftValue + node.value + rightValue;
	}
	
	
	//Reads tree in descending order.
	private String descendingOrderRecursive(BSTNode<T> node) {
		String leftValue = "";
		String rightValue = "";
		if(node.left != null)
			leftValue = " " + descendingOrderRecursive(node.left);
		if(node.right != null)
			rightValue = descendingOrderRecursive(node.right) + " ";
		return rightValue + node.value + leftValue;
			
	}
	
}
