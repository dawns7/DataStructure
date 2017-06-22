package rb;


class RBT {
	public Node root;

	public RBT() {
		root = Node.Tree_NIL();
	}

	public void Insert(Node z) {
		Node y = Node.Tree_NIL();
		Node x = root;
		while (x.val != 0) {
			y = x;
			if (z.val < x.val)
				x = x.left;
			else
				x = x.right;
		}
		z.p = y;
		if (y.val == 0)
			root = z;
		else if (z.val < y.val)
			y.left = z;
		else
			y.right=z;

		Insert_fixup(z);
	}


	public void left_rotate(Node x) {
		Node y = x.right;
		x.right = y.left;
		if(y.left.val != 0)
			y.left.p = x;
		y.p = x.p;
		if(x.p.val == 0)
			root = y;
		else if(x==x.p.left)
			x.p.left =y;
		else
			x.p.right = y;
		y.left = x;
		x.p = y;
	}

	public void right_rotate(Node x) {
		Node y = x.left;
		x.left = y.right;
		if(y.right.val != 0)
			y.right.p = x;
		y.p = x.p;
		if(x.p.val == 0)
			root = y;
		else if(x == x.p.right)
			x.p.right = y;
		else
			x.p.left = y;
		y.right = x;
		x.p = y;
	}

	public void Insert_fixup(Node n) {
		Node y;
		while(n.p.color) {
			if(n.p == n.p.p.left) {
				y = n.p.p.right; 
				if(y.color) {
					n.p.color = false;
					y.color = false;
					n.p.p.color = true;
					n = n.p.p;
				}
				else {
					if (n == n.p.right) { 
						n = n.p;
						left_rotate(n);
					}
					n.p.color = false;
					n.p.p.color = true;
					right_rotate(n.p.p);
				}

			}
			else {
				y = n.p.p.left; 
				if(y.color) {
					n.p.color = false;
					y.color = false;
					n.p.p.color = true;
					n = n.p.p;
				}
				else {
					if (n == n.p.left) {
						n = n.p;
						right_rotate(n);
					}
					n.p.color = false;
					n.p.p.color = true;
					left_rotate(n.p.p);
				}
			}

		}
		root.color = false; 
	}


	public void Delete(Node n)
	{
		Node y = n;
		boolean y_originalColor = y.color;
		Node x;
		if(n.left.val == 0) {
			x = n.right;
			RBT_Trans(n,n.right);
		}
		else if(n.right.val == 0) {
			x = n.left;
			RBT_Trans(n, n.left);
		}
		else {
			y = Tree_minimum(n.right);
			y_originalColor = y.color;
			x = y.right;
			if(y.p == n)
				x.p=y;
			else {
				RBT_Trans(y, y.right);
				y.right = n.right;
				y.right.p =y;
			}
			RBT_Trans(n, y);
			y.left = n.left;
			y.left.p = y;
			y.color = n.color;
		}
		if(!y_originalColor)
			Delete_fixup(x);
	}


	public void Delete_fixup(Node x) {
		Node w;
		while(x != root && !x.color) {
			if(x == x.p.left) {
				w = x.p.right;
				if(w.color) {
					w.color = false;
					x.p.color = true;
					left_rotate(x.p);
					w = x.p.right;
				}
				if(!w.left.color && !w.right.color) {
					w.color = true;
					x = x.p;
				}
				else {
					if(!w.right.color) {
						w.left.color = false;
						w.color = true;
						right_rotate(w);
						w = x.p.right;
					}
					w.color = x.p.color;
					x.p.color = false;
					w.right.color = false;
					left_rotate(x.p);
					x = root;
				}
			}
			else {
				w = x.p.left;
				if(w.color) {
					w.color = false;
					x.p.color = true;
					right_rotate(x.p);
					w = x.p.left;
				}
				if(!w.right.color && !w.left.color) {
					w.color = true;
					x = x.p;
				}
				else {
					if(!w.left.color) {
						w.right.color = false;
						w.color = true;
						left_rotate(w);
						w = x.p.left;
					}
					w.color = x.p.color;
					x.p.color = false;
					w.left.color = false;
					right_rotate(x.p);
					x = root;
				}
			}
		}
		x.color = false;
	}

	public Node Tree_minimum(Node n) {
		while(n.left.val != 0)
			n = n.left;
		return n;
	}
	
	Node Tree_maximum(Node n) {
		while (n.right.val != 0)
			n = n.right;
		return n;
	}

	public void RBT_Trans(Node u, Node v) {
		if(u.p.val == 0)
			root = v;
		else if(u == u.p.left)
			u.p.left = v;
		else
			u.p.right = v;
		v.p = u.p;
	}

	public Node tree_search(Node tree, int val) {
		if (tree.val == 0)
			return Node.Tree_NIL();
		else if (val == tree.val)
			return tree;
		else if (val < tree.val)
			return tree_search(tree.left,val);
		else
			return tree_search(tree.right,val);
	}

	public void inorder(Node tree) {
		if (tree.val == 0)
			return;
		else {
			inorder(tree.left);
			System.out.print(tree.val);
		      if(tree.color)
		    	  System.out.println(" R");
		      else
		    	  System.out.println(" B");
		      inorder(tree.right);
		}
	}

	public int Black_Node_Count(Node tree) {	
		int bn = 0;
		if(tree.left.val != 0) 
			bn += Black_Node_Count(tree.left);
		if(tree.right.val != 0) 
			bn += Black_Node_Count(tree.right);
		if(!tree.color) 
			bn += 1;
		return bn;
	}

	public int BlackHeight(Node tree) {
		if(tree.val == 0)
			return 0;
		else if(!tree.color)
			return BlackHeight(tree.left) + 1;
		else
			return BlackHeight(tree.left);
	}
}