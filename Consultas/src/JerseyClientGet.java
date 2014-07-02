
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;


import javax.ws.rs.core.NewCookie;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;
	 
	public class JerseyClientGet {
	 
	  public static void main(String[] args) {
		 Post_Strabon();
		  //Post_Parliament();
		  //Get_UseekM();
		}
	  
	
	public static void Get_UseekM(){
		
		  try {
			    String input = "select%20*%20where%20%7B%3Fs%20%3Fp%20%3Fo%7D%20limit%20100";
			  
				Client client = Client.create();
				 
				WebResource webResource = client.resource("http://biomac.icmc.usp.br:8080/useekm-workbench/repositories/SYSTEM/query?queryLn=SPARQL&query="+input+"&limit=100&infer=true");
		 
				ClientResponse response = webResource.accept("application/xml")
		                   .get(ClientResponse.class);
		 
				if (response.getStatus() != 200) {
				   throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
				}
		 
				String output = response.getEntity(String.class);
		 
				System.out.println("Output from Server .... \n");
				System.out.println(output);
			  } catch (Exception e) {
		 
				e.printStackTrace();
		 
			  }
		  
	  }
	  
	   public static void Post_Strabon(){
		  try {
			  
				Client client = Client.create();
		 
				WebResource webResource = client.resource("http://biomac.icmc.usp.br:8080/strabon/Query");
		 
			//	String query = "select+*+where+%7B%3Fs+%3Fp+%3Fo%7D+limit+100";
			
				String query ="PREFIX%20geof%3A%20%3Chttp%3A%2F%2Fwww.opengis.net%2Fdef%2Ffunction%2Fgeosparql%2F%3E%0A%09%09%20%20%20PREFIX%20datasets%3A%20%3Chttp%3A%2F%2Fgeographica.di.uoa.gr%2Fdataset%2F%3E%0A%09%09%20%20%20PREFIX%20geonames%3A%20%3Chttp%3A%2F%2Fwww.geonames.org%2Fontology%23%3E%0A%09%09%20%20%20PREFIX%20opengis%3A%20%3Chttp%3A%2F%2Fwww.opengis.net%2Fdef%2Fuom%2FOGC%2F1.0%2F%3E%0A%09%09%20%20%20SELECT%20(geof%3AconvexHull(%3Fo1)%20AS%20%3Fret)%20%0A%09%09%20%20%20WHERE%20%7B%20GRAPH%20datasets%3Ageonames%20%7B%3Fs1%20geonames%3AasWKT%20%3Fo1%7D%20%7D";
				
				String input = "query="+query+"&view=HTML&format=SPARQL%2FJSON&handle=plain&submit=Query";
				
				
				//application/x-www-form-urlencoded
				ClientResponse response = webResource.type("application/x-www-form-urlencoded").post(ClientResponse.class, input);
				
				if (response.getStatus() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
					     + response.getStatus());
				}
		 
				System.out.println("Output from Server .... \n");
				String output = response.getEntity(String.class);
				System.out.println(output);
		 
			  } catch (Exception e) {
		 
				e.printStackTrace();
		 
			  }
		 			
	  }
	  
	  
	  public static void Post_Parliament(){
		  try {
			  
				Client client = Client.create();
		 
				WebResource webResource = client.resource("http://biomac.icmc.usp.br:8080/parliament/sparql");
		 

				String input = "query=select+*+where+%7B%3Fs+%3Fp+%3Fo%7D+limit+100&custom=&display=json&stylesheet=&output=json";

				ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED) 
						.post(ClientResponse.class, input);
				
				if (response.getStatus() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
					     + response.getStatus());
				}
		 
				System.out.println("Output from Server .... \n");
				String output = response.getEntity(String.class);
				System.out.println(output);
		 
			  } catch (Exception e) {
		 
				e.printStackTrace();
		 
			  }
		 			
	  }

}