package rb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class test {
	public static void main(String[] args) throws IOException 
	{

		File dir = new File("./input/"); 
		File[] fileList = dir.listFiles(); 
		String file_name=" ";

		for(int i = 0; i < fileList.length ; i++){
			File file = fileList[i]; 
			if(file.isFile()){
				RBT rbt = new RBT();
				int total_Node = 0;
				int insert_Node = 0;
				int delete_Node = 0;
				int miss_Node = 0;

				file_name = file.getName();

				BufferedReader br = new BufferedReader(new FileReader("./input/"+file_name));
				while(true) 
				{
					String line = br.readLine();
					if (line == null) 
						break;
					int num = Integer.parseInt(line.trim());
					if(num > 0) {
						rbt.Insert(new Node(num));
						total_Node++;
						insert_Node++;
					}
					else if(num < 0) {
						Node n;
						n = rbt.tree_search(rbt.root, -1*num);
						if(n.val == 0)
							miss_Node++;
						else {
							rbt.Delete(n);
							total_Node--;
							delete_Node++;
						}
					}
					else break;
				}
				br.close();
				System.out.println("filename = "+file_name);
				System.out.println("total = " + total_Node);
				System.out.println("insert = "+ insert_Node);
				System.out.println("delete = "+delete_Node);
				System.out.println("miss = "+miss_Node);
				System.out.println("nb = " + rbt.Black_Node_Count(rbt.root));
				System.out.println("bh = " + rbt.BlackHeight(rbt.root));
				rbt.inorder(rbt.root);
			}	
		}
	}
}