package fantasyFootball;

import java.util.ArrayList;
import java.util.Arrays;

import fantasyFootball.model.DatabaseManager;
import fantasyFootball.model.LeagueManager;
import fantasyFootball.model.LeagueSettingsManager;
import fantasyFootball.model.TablePlayer;
import fantasyFootball.model.Team;

public class TestClass {

	private static LeagueManager lm = new LeagueManager();
	private static DatabaseManager db = new DatabaseManager();
	private static LeagueSettingsManager lsm = new LeagueSettingsManager();
	//private ArrayList<Player> keepers = lm.getKeepers();
	
	public TestClass() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args){
		Team tm = new Team("bob");
		tm.setPositionSizes(lsm);
		tm.addToQB("bob");
		tm.addToQB("jim");
		System.out.println(Arrays.toString(tm.getStartQB()));
	}

}
