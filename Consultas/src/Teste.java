import java.util.ArrayList;

import endpoints.Parliament;
import endpoints.Strabon;
import endpoints.UseekM;
import read_query.Read_XML_File;
import type_query.Query;


public class Teste {
	public static void main(String args[]){
		
		/******************************
		 * 
		 * INITIAL OBJECTS TO MANIPULATE TRIPLE STORE
		 * 
		 * INITIAL OBJECTS TO STORE QUERIES
		 * 
		 * ********************************/
		
		
		Strabon st = new Strabon();
		Parliament pl = new Parliament();
		UseekM us = new UseekM();
			
		ArrayList<Query> strabon = new ArrayList<Query>();
		ArrayList<Query> useekM = new ArrayList<Query>();
		ArrayList<Query> parliament = new ArrayList<Query>();
		
		
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
		/******************************
		 * 
		 * RUN QUERIES IN TRIPLE STORES
		 * 
		 * ********************************/
		//Strabon
/*		for(Query q: strabon){
			st.test_Strabon(q);
		} */
		//UseekM
		for(Query q: useekM){
			us.test_UseekM(q);
		}
		
		
		//Parliament
	/*	for(Query q: parliament){
			pl.test_Parliament(q);
		} */  
		/******************************
		 * 
		 * SHOW THE RESULTS
		 * 
		 * ********************************/
	/*	System.out.println("\n\n============== STRABON ==============");
		for(Query q: strabon){
			System.out.println("Query n: "+q.getNumber()+" time: "+q.getTime()+" type: "+q.getType());
		}*/
		
		System.out.println("\n\n============== USEEKM ==============");
		for(Query q: useekM){
			System.out.println("Query n: "+q.getNumber()+" time: "+q.getTime()+" type: "+q.getType());
		} 
		
		 
	/*	System.out.println("\n\n============== PARLIAMENT ==============");
		for(Query q: parliament){
			System.out.println("Query n: "+q.getNumber()+" time: "+q.getTime()+" type: "+q.getType());
		} */ 
		
	}
}
