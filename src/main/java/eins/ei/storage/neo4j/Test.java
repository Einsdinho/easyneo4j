package eins.ei.storage.neo4j;

public class Test {
	public static void main(String[] args) {
	DaoRest.sendCypher("create (a:CAR{no:'NB118'})");
	}
}
