package eins.ei.storage.neo4j;

import java.io.File;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class Test {
	public static void main(String[] args) {
		GraphDatabaseService  service=new GraphDatabaseFactory().newEmbeddedDatabase(new File("c:/neo4j"));
		
		
		
		try ( Transaction tx = service.beginTx() )
		{
			Result result=service.execute("match (a) return a");
			System.out.println(result.resultAsString());
		    tx.success();
		}
	}
}
