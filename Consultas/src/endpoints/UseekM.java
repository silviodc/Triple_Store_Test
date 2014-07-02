package endpoints;

import type_query.Query;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class UseekM {
	

	private final String URL_endpoint="http://biomac.icmc.usp.br:8080/useekm-workbench/repositories/SYSTEM/query";
	
	
	public UseekM(){}

	public void test_UseekM(Query q){
		
		  try {
			   
			  
			   String query = q.getQuery();
			   String input = "query="+query+"&view=HTML&format=SPARQL%2FJSON&handle=plain&submit=Query";
			   			   
			//  String input = "select+*+where+%7B%3Fs+%3Fp+%3Fo%7D+limit+100";
				Client client = Client.create();
				 
				WebResource webResource = client.resource(URL_endpoint);
		 
				q.start();
				
				ClientResponse response = webResource.accept("application/xml").post(ClientResponse.class, input);
		 
				if (response.getStatus() != 200) {
					q.dontRun();
					throw new RuntimeException("Failed : HTTP error code : "
					     + response.getStatus()+" endpoint: "+q.getEndPoint()+" query number: "+q.getNumber());
				}
		 
				q.stop();
				
				String output = response.getEntity(String.class);
		 
				System.out.println("Output from Server .... \n");
				System.out.println(output);
			  } catch (Exception e) {
		 
				e.printStackTrace();
		 
			  }
		  
	  }
	 
}
