package endpoints;

import javax.ws.rs.core.MediaType;

import type_query.Query;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Parliament {

	 private final String URL_endpoint="http://biomac.icmc.usp.br:8080/parliament/sparql";
	  
	 public Parliament (){}
	 
	  public  void test_Parliament(Query q){
		  try {
			  
				Client client = Client.create();		 
				WebResource webResource = client.resource(URL_endpoint);				
				String query = q.getQuery();
				String input = "query="+query+"&custom=&display=json&stylesheet=&output=json";
				
				q.start();				
				
				ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED) 
						.post(ClientResponse.class, input);
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
