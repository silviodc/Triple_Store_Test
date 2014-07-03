package read_query_create_file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Create_File {
	
	public BufferedWriter buffer(String name){
		File arquivo = new File(name).getAbsoluteFile();
		try {
			if (!arquivo.exists()) {//cria um arquivo (vazio)
				arquivo.createNewFile();
			}else{
				arquivo.delete();
				arquivo.createNewFile();
			}
			FileWriter fw = new FileWriter(arquivo,true);
			return new BufferedWriter(fw);
		}catch(Exception ex){ 
			ex.printStackTrace();
		}
		return null;
	}
	
}
