package endpoints;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import type_query.Query;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class UseekM {
	

	private final String URL_endpoint="http://biomac.icmc.usp.br:8080/useekm-workbench/repositories/SYSTEM/query";
	private Query q;
	private boolean execute = true;
	public UseekM(){}
	
	public void setQuery(Query q){
		this.q = q;
	}
	public void setExecute(boolean test){
		execute = test;
	}
	
	public void test_UseekM(Query q1){
		
		setQuery(q1);
		setExecute(true);
		  try {
				ExecutorService executor = Executors.newSingleThreadExecutor();
				executor.execute(new Runnable(){
					@Override
					public void run() {
					
						webClient();
						
					}});
				executor.shutdown();
				// wait for termination
				executor.awaitTermination(30, TimeUnit.MINUTES);
			
				if(execute)
					q.stop();	
	
		  } catch (Exception e) {
		 		e.printStackTrace();
		 	  }  
	  }
	  public void webClient(){
		   
		  System.out.println("START!!! query "+q.getNumber()+" from Server UseekM .... \n");
		  
		  String input = q.getQuery();
		  
	        Client client = Client.create();
			 
			WebResource webResource = client.resource(URL_endpoint);
	 
			MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
			queryParams.add("queryLn", "SPARQL");
			queryParams.add("limit", "0");
			queryParams.add("infer", "true");
			queryParams.add("query", input);
			
			q.start();
			
			ClientResponse response = webResource.queryParams(queryParams).accept(MediaType.APPLICATION_XHTML_XML).get(ClientResponse.class);
		
			if (response.getStatus() != 200) {
				q.dontRun();
				throw new RuntimeException("Failed : HTTP error code : "
				     + response.getStatus()+" endpoint: "+q.getEndPoint()+" query number: "+q.getNumber());
			}
	 		q.stop();
	 		setExecute(false);
			
	 		String output = response.getEntity(String.class);
			System.out.println("Output query "+q.getNumber()+" from Server UseekM .... \n");
			q.setResult(output);
	  }
}
