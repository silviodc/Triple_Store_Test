package endpoints;

import type_query.Query;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Strabon {
	

	private final String URL_endpoint="http://biomac.icmc.usp.br:8080/strabon/Query";
	
	
	public Strabon(){}
	

	public void test_Strabon(Query q){
			  try {
				  
					Client client = Client.create();
			 
					String query = q.getQuery();					

					WebResource webResource = client.resource("http://biomac.icmc.usp.br:8080/strabon/Query");
			 					
					String input = "query="+query+"&view=HTML&format=HTML&handle=plain&submit=Query";

					q.start();
					
					ClientResponse response = webResource.type("application/x-www-form-urlencoded").post(ClientResponse.class, input);
					
					if (response.getStatus() != 200) {
						q.dontRun();
						throw new RuntimeException("Failed : HTTP error code : "
						     + response.getStatus()+" endpoint: "+q.getEndPoint()+" query number: "+q.getNumber());
					}
			 
					q.stop();
					
					System.out.println("Output from Server .... \n");
					String output = response.getEntity(String.class);
					System.out.println(output);
			 
				  } catch (Exception e) {
			 
					e.printStackTrace();
			 
				  }
			 			
		  }
		  
		  
}
