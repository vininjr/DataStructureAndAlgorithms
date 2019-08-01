package br.ufc.crateus.eda.heaps;


public class LeftHeap<K extends Comparable<K>> {
	Node root;
	public class Node {
		K key;
		int ccn;
		Node left, right;
		
		public Node(K key) {
			this.key = key;
		}
		
		public Node() { }
	}
	
	public Node get(Node node, K key){
		if(node == null) return null;
		if(node.key == key)
			return node;
		else if(key.compareTo(node.key) < 0)
			get(node.left, key);
		else
			get(node.right, key);
		return root;
	}
	
	public Node merge(Node heap1, Node heap2){
		if(heap1 == null) return heap2;
		if(heap2 == null) return heap1;
		Node least;
		Node most;
		if(heap1.key.compareTo(heap2.key) < 0){
			least = heap1;
			most = heap2;
		}
		else{
			least = heap2;
			most = heap1;
		}
		
		least.right = merge(least.right, most);
		if(least.left == null || least.left.ccn < least.right.ccn){
			Node aux = least.right;
			least.right = least.left;
			least.left = aux;
			least.ccn = least.right.ccn + 1;
		}
		else least.ccn = 1 + least.left.ccn;
		return least;
	}
	
	public K findMin(){
		return root.key;
	}
	
	public Node makeHeap(){
		return root;
	}
	
	public Node insert(K key, Node heap){
		Node newb = new Node(key);
		return merge(heap, newb);
	}
	
	public Node decreaseKey(K key, K newKey){
		Node aux = get(root, key);
		Node aox = aux;
		aux.key = newKey;
		return merge(aux,delete(aox));
	}
	
	public Node delete(Node heap){
		return merge(heap.left, heap.right);
	}
	
	public Node ExtractMin(){
		Node aux = root;
		merge(root.right, root.left);
		return aux;
	}
	
	public boolean isEmpty(){
		 return root == null;
	}
	
}
