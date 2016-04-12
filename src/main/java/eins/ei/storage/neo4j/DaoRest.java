package eins.ei.storage.neo4j;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class DaoRest {
public final static String SERVER_ROOT_URI="http://localhost:7474/db/data/";
	
	public final static String SEND_CYPHER_URI = SERVER_ROOT_URI + "transaction/commit";
	
	public final static String  NODE_ENTRY_POINT_URI = SERVER_ROOT_URI + "node";
	
	
	
	private static String Authorization ;
	
	
	
	public final static WebResource resourceAddNode = Client.create()
	        .resource( NODE_ENTRY_POINT_URI );
	
	public final static WebResource resourceSendCypher = Client.create()
	        .resource( SEND_CYPHER_URI );
	
	public final static Gson gson = new Gson();

	
	static{
		try {
			Authorization ="Basic "+Base64.getEncoder().encodeToString("neo4j:qweewq".getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public static String addNode(String payload){
		String response = resourceAddNode
				.accept( MediaType.APPLICATION_JSON ,"UTF-8")		
		        .type( MediaType.APPLICATION_JSON )
		        .header("Authorization",Authorization )
		        .entity( payload )
		        .post( String.class );
		
		return response;
	}
	
	public static String sendCypher(String... statement){
		
		if(statement==null||statement.length<=0){
			return "{}";
		}
		
		StringBuilder sb=new StringBuilder();
		
		sb.append("{\"statements\" : [ ");
		
		for (int i = 0; i < statement.length; i++) {
			sb.append("{\"statement\" : \"");
			sb.append(statement[i].replaceAll("\\\"", "\\\\\""));
			sb.append("\"},");
		}
		
		sb=sb.deleteCharAt(sb.length()-1);
		
		sb.append("]}");
		
		String response = resourceSendCypher
				.accept( MediaType.APPLICATION_JSON ,"UTF-8")		
		        .type( MediaType.APPLICATION_JSON )
		        .header("Authorization",Authorization )
		        .entity( sb.toString() )
		        .post( String.class );
		
		return response;
	}
	
}
