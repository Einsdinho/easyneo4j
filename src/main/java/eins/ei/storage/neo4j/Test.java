package eins.ei.storage.neo4j;

public class Test {
	public static void main(String[] args) {
	String str=DaoRest.sendCypher("create (a:CAR{no:'NB888'})");
	System.out.println(str);
	}
}
