import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;




import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;
	 
	public class JerseyClientGet {
	 
	  public static void main(String[] args) {
		 // Post_Strabon();
		  //Post_Parliament();
		  Get_UseekM();
		}
	  
	
	public static void Get_UseekM(){
		
		  try {
				 
				Client client = Client.create();
		 
				WebResource webResource = client.resource("http://biomac.icmc.usp.br:8080/useekm-http-workbench-1.2.1/repositories/");
		 
				webResource.cookie(new Cookie("Last-Modified","1404186962000"));
				webResource.cookie(new Cookie("limit","100"));
				webResource.cookie(new Cookie("queryLn", "SPARQL"));
				webResource.cookie(new Cookie("workbench-server","\"http://biomac.icmc.usp.br:8080/useekm-http-server-1.2.1\""));
				webResource.cookie(new Cookie("__utma","128681236.142880318.1402850254.1403803053.1403993438.4"));
				webResource.cookie(new Cookie("__utmz","128681236.1402850254.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)"));

			    @SuppressWarnings("rawtypes")
				MultivaluedMap queryParams = new MultivaluedMapImpl();
		        queryParams.add("queryLn", "SPARQL");
		        queryParams.add("query", "select%20*%20where%20%7B%3Fs%20%3Fp%20%3Fo%7D%20limit%20100");
		        queryParams.add("limit", "100");
		        queryParams.add("queryLn", "SPARQL");
		      
		        ClientResponse response =  webResource.queryParams(queryParams).type("application/x-www-form-urlencoded").get(ClientResponse.class);
			       
				
				if (response.getStatus() != 200) {
				    
					throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
				   
				}
				System.out.println(response.getHeaders());
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
		 
				String query = "select+*+where+%7B%3Fs+%3Fp+%3Fo%7D+limit+100";
				
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