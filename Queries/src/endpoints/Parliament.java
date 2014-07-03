package endpoints;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.core.MediaType;

import type_query.Query;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Parliament {

	 private final String URL_endpoint="http://biomac.icmc.usp.br:8080/parliament/sparql";
	 private Query q;
	private boolean execute = true;
	 public Parliament (){} 
	 
	 public void setQuery(Query q){
		 this.q = q;
	 }
	public void setExecute(boolean test){
			execute = test;
	}

	  public  void test_Parliament(Query q1){
		  
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
			
		  System.out.println("START!!! query "+q.getNumber()+" from Server Parliament .... \n");
		  
		  	Client client = Client.create();		 
			WebResource webResource = client.resource(URL_endpoint);				
			
			String query = q.getQuery();
			
			String input = "query="+query+"&custom=&display=json&stylesheet=&output=json";
			
			q.start();				

			ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, input);
							
			if (response.getStatus() != 200) {
				q.dontRun();
				throw new RuntimeException("Failed : HTTP error code : "
				     + response.getStatus()+" endpoint: "+q.getEndPoint()+" query number: "+q.getNumber());
			}

	 		q.stop();
	 		setExecute(false);
			
	 		String output = response.getEntity(String.class);
			System.out.println("Output query "+q.getNumber()+" from Server Parliament .... \n");
			q.setResult(output);

	  }
}
