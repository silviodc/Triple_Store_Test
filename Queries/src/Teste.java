import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import endpoints.Parliament;
import endpoints.Strabon;
import endpoints.UseekM;
import read_query_create_file.Create_File;
import read_query_create_file.Read_XML_File;
import type_query.Query;


public class Teste  {
	public static void main(String args[]) throws IOException{
		
		/******************************
		 * 
		 * INITIAL OBJECTS TO MANIPULATE TRIPLE STORE
		 * 
		 * INITIAL OBJECTS TO STORE QUERIES
		 * 
		 * OBJECT TO MANIPULATE FILES
		 * ********************************/
		
		
		Strabon st = new Strabon();
		Parliament pl = new Parliament(); //Objects to access webServices
		UseekM us = new UseekM();
			
		ArrayList<Query> strabon = new ArrayList<Query>();
		ArrayList<Query> useekM = new ArrayList<Query>(); //array list to queries
		ArrayList<Query> parliament = new ArrayList<Query>();
		
		Create_File file = new Create_File(); 
		
		/******************************
		 * 
		 * READ XML FILE WITH ALL QUERIES
		 * 
		 * ********************************/
		Read_XML_File reader = new Read_XML_File();
		
		try {
			String path = "files/Strabon_query.xml"; //path with file to strabon		
			strabon.addAll(reader.read_Query(path));
			
			path ="files/Parliament_query.xml"; //path with file to parliament
			parliament.addAll(reader.read_Query(path));
			
			path ="files/UseekM_query.xml"; //path with file to UseekM
			useekM.addAll(reader.read_Query(path));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		/**
		 *  CREATE FILE TO SAVE TIME RUN FROM QUERIES IN TRIPLE STORE
		 * 
		 * */
		
		BufferedWriter times = file.buffer("result/times.txt");
		
		/******************************
		 * 
		 * RUN QUERIES IN TRIPLE STORES AND SAVE TIMES IN FILE result/times.txt
		 * 
		 * ********************************/
		
		times.write("============== STRABON ====================");
		times.newLine();
		
		//Strabon run queries
		for(Query q: strabon){
			st.test_Strabon(q);
		} 
		//Save time strabon
		for(Query q: strabon){
				times.write("Query number: "+q.getNumber()+" time: "+q.getTime()+" type: "+q.getType());
				times.newLine();
		}
		
		
		times.write("============== USEEKM ====================");
		times.newLine();
		//UseekM run queries
		for(Query q: useekM){
			us.test_UseekM(q);
		}
		//Save time useekM
		for(Query q: useekM){
			times.write("Query number: "+q.getNumber()+" time: "+q.getTime()+" type: "+q.getType());
			times.newLine();
		}
		
		times.write("============== PARLIAMENT ====================");
		times.newLine();
		//Parliament run queries
		for(Query q: parliament){
			pl.test_Parliament(q);
		}   
		//Save time parliament
		for(Query q: parliament){
			times.write("Query number: "+q.getNumber()+" time: "+q.getTime()+" type: "+q.getType());
			times.newLine();
		}
		times.close();

		
		/******************************
		 * 
		 * SHOW THE RESULTS IN CONSOLE
		 * 
		 * ********************************/
		System.out.println("\n\n============== STRABON ==============");
		for(Query q: strabon){
			System.out.println("Query n: "+q.getNumber()+" time: "+q.getTime()+" type: "+q.getType());
		}
		
		System.out.println("\n\n============== USEEKM ==============");
		for(Query q: useekM){
			System.out.println("Query n: "+q.getNumber()+" time: "+q.getTime()+" type: "+q.getType());
		}  
		
		 
		System.out.println("\n\n============== PARLIAMENT ==============");
		for(Query q: parliament){
			System.out.println("Query n: "+q.getNumber()+" time: "+q.getTime()+" type: "+q.getType());
		}  
	
		/******************************
		 * 
		 * SAVE THE RESULTS RETURN BY TRIPLES STORES IN FILE
		 * 
		 * ********************************/
		
		/**
		 *  CREATE FILES TO STORE THE RESULT AFTER RUN THE QUERIES IN TRIPLE STORE
		 * 
		 * */
		BufferedWriter arquivoSt = file.buffer("result/strabon.txt");
		
		//Strabon save results in file
		for(Query q: strabon){
			arquivoSt.write("query number="+q.getNumber()+" type= "+q.getType());
			arquivoSt.newLine();
			arquivoSt.write(q.getResult());
			arquivoSt.newLine();
			arquivoSt.newLine();
		}
		arquivoSt.close();
	
		BufferedWriter arquivoPl = file.buffer("result/parliament.txt");
	
		//Parliament save results in file
		for(Query q: parliament){
			arquivoPl.write("query number="+q.getNumber()+" type= "+q.getType());
			arquivoPl.newLine();
			arquivoPl.write(q.getResult());
			arquivoPl.newLine();
			arquivoPl.newLine();
		}
		arquivoPl.close();
	
		BufferedWriter arquivoUs = file.buffer("result/useekm.txt");
		//UseekM save results in file
		for(Query q: useekM){
			arquivoUs.write("query number="+q.getNumber()+" type= "+q.getType());
			arquivoUs.newLine();
			System.out.println(q.getResult());
			System.out.println("Query n: "+q.getNumber()+" time: "+q.getTime()+" type: "+q.getType());
			arquivoUs.write(q.getResult());
			arquivoUs.newLine();
			arquivoUs.newLine();
		}
		arquivoUs.close();
	
	
	}
	

}
