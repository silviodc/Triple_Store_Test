package endpoints;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import type_query.Query;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Strabon {
	

	private final String URL_endpoint="http://biomac.icmc.usp.br:8080/strabon/Query";
	private Query q;
	private boolean execute = true;
	public Strabon(){}
	
	public void setQuery(Query q){
		this.q = q;
	}
	public void setExecute(boolean test){
		execute = test;
	}
	public void test_Strabon(Query q1){
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
		
		System.out.println("START!!! query "+q.getNumber()+" from Server STRABON .... \n");
		
		Client client = Client.create();
		 
		String query = q.getQuery();					

		WebResource webResource = client.resource(URL_endpoint);
 					
		String input = "query="+query+"&view=HTML&format=HTML&handle=plain&submit=Query";

		q.start();
		
		ClientResponse response = webResource.type("application/x-www-form-urlencoded").post(ClientResponse.class, input);
		
		if (response.getStatus() != 200) {
			q.dontRun();
			throw new RuntimeException("Failed : HTTP error code : "
			     + response.getStatus()+" endpoint: "+q.getEndPoint()+" query number: "+q.getNumber());
		}
		q.stop();
		setExecute(false);
 		String output = response.getEntity(String.class);
		System.out.println("Output query "+q.getNumber()+" from Server Strabon .... \n");
		q.setResult(output);

	}
		  
		  
}
