package rb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class _test {
	public static void main(String[] args) throws IOException {
		int total_Node = 0;
		RBT rbt = new RBT();
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		while(true) {
			String line = br.readLine();
			if (line == null)
				break;
			int num = Integer.parseInt(line.trim());
			if(num > 0) {
				rbt.Insert(new Node(num));
				total_Node++;
			}
			else if(num < 0) {
				Node n;
				n = rbt.tree_search(rbt.root, -1*num);
				if(n.val == 0)
					System.out.println("Couldn't find the node "+ (-1*num));
				else {
					rbt.Delete(n);
					total_Node--;
				}
			}
			else break;
		}
		br.close();
		System.out.println("total = " + total_Node);
		System.out.println("nb = " + rbt.Black_Node_Count(rbt.root));
		System.out.println("bh = " + rbt.BlackHeight(rbt.root));
		rbt.inorder(rbt.root);

	}
}