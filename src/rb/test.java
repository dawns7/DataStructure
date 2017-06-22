package rb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class test {
	public static void main(String[] args) throws IOException {
		RBT rbt = new RBT();
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		while(true) {
			String line = br.readLine();
			if (line == null)
				break;
			String str = line.trim();
			int num = -1;
			if(!"".equals(str))
				num = Integer.parseInt(str);
			if(num > 0)
				rbt.Insert(new Node(num));

			else if(num < 0) {
				Node n;
				n = rbt.tree_search(rbt.root, -1*num);
				if(n.val != 0) 
					rbt.Delete(n);
			}
			else break;
		}
		br.close();

		BufferedReader br_ = new BufferedReader(new FileReader("search.txt"));
		while(true) {
			String line = br_.readLine();
			if (line == null)
				break;
			String str = line.trim();
			int num = -1;
			if(!"".equals(str)) {
				num = Integer.parseInt(str);
				if (num == 0) break;
				rbt.PrintFinalProj(num);
			}
		}
		br_.close();
	}
}