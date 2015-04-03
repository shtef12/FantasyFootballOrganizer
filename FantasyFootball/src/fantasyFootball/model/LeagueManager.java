package fantasyFootball.model;

import java.io.File;
import java.util.ArrayList;

import fantasyFootball.MainApp;

public class LeagueManager {

	private ArrayList<Team> teamList = new ArrayList<Team>();
	private ArrayList<Division> divisionList = new ArrayList<Division>();
	private ArrayList<TablePlayer> keeperList = new ArrayList<TablePlayer>();
	private static DatabaseManager dbManager = new DatabaseManager();
	private static LeagueSettingsManager settings;
	private File teamsFile = new File("files/Teams.csv");
	private MainApp main;
	
	public LeagueManager() {
		// TODO Auto-generated constructor stub
		settings  = new LeagueSettingsManager();
		createKeepers();
		createTeams();
	}
	
	///Functions for handling teams///
	//==============================//
	private void createTeams(){
		
		ArrayList<String[]> teams = dbManager.getData(teamsFile);
		
		for(int i = 0; i < teams.size(); i++){
			String[] temp = teams.get(i);
			int rank = Integer.parseInt(temp[2]);
			
			Team newTeam = new Team(temp[0],temp[1],rank);
			teamList.add(newTeam);
		}
		
		for(Team tm : teamList){
			tm.setPositionSizes(settings);
			
		}
	}
	
	/**
	 * adds a new team to the team list if it is not in the list already
	 * and the new team does not have the same name as any other team in 
	 * the list
	 * @param teamToAdd ->new team to add to the team list
	 */
	public void addTeam(Team teamToAdd){
		
		for(Team team : teamList){
			if(team.getOwner().equals(teamToAdd.getOwner())){
				return;
			}
		}
		//writing to the file does weird things for adding a new player but not editing an existing player
		teamList.add(teamToAdd);
		dbManager.addToDatabase(teamToAdd.getOwner() + "," + teamToAdd.getName() + "," + teamToAdd.getRank(), teamsFile);
		
	}

	/**
	 * Removes a team from the team list given the string name of the team to remove
	 * @param teamToRemove -->string name of the team to remove from the list
	 */
	public void removeTeam(String teamToRemove){
		CsvWriter wr = new CsvWriter();
		for(int i = 0; i < teamList.size();i++){
			if(teamList.get(i).getOwner().equals(teamToRemove)){
				wr.eraseFromFile(teamsFile, teamList.get(i).getOwner());
				teamList.remove(i);
			}
		}
	}
	
	//==================================//
	///Functions for handling divisions///
	//==================================//
	
	/**
	 * Creates a number of divisions equal to the parameter given
	 * @param numOfDivisions -->amount of divisions to create
	 */
	public void createDivisions(int numOfDivisions){
		
		for(int i = 0; i < numOfDivisions; i++){
			Division newDiv = new Division("Division " + i);
			divisionList.add(newDiv);
		}
	}
	
	/**
	 * Adds a team to the division
	 * @param divisionName -->the name of the division to add to
	 * @param teamToAdd -->the team to add to the division
	 */
	public void addToDivision(String divisionName, Team teamToAdd){
		
		for(int i = 0; i < divisionList.size(); i++){
			if(divisionList.get(i).getName().equals(divisionName)){
				divisionList.get(i).addToDiv(teamToAdd);
			}
		}
	}
	
	//============================================//
	/// Functions for dealing with keeper players ///
	//============================================//
	
	/**
	 * creates keeper players from file of keepers and adds them to keeperList
	 */
	public void createKeepers(){
		
		ArrayList<String[]> names = dbManager.getKeeperData();
		
		for(int i = 0; i < names.size(); i++){
			String[] temp = names.get(i);
			int years = Integer.parseInt(temp[3]);
			int pickUsed = Integer.parseInt(temp[4]);
			
			TablePlayer keeper = new TablePlayer(temp[0],temp[1],true,temp[2],years,pickUsed,temp[5]);
			//System.out.println(keeper.toString());
			keeperList.add(keeper);
		}
	}
	
	/**
	 * returns the list of keepers
	 * @return
	 */
	public ArrayList<TablePlayer> getKeepers(){
		return keeperList;
	}
	
	/**
	 * adds a new keeper player to the file and adds player to keeper list
	 * @param player
	 */
	public void addKeeperPlayer(TablePlayer player){
		dbManager.addToDatabase(player, dbManager.getKeeperFile());
		keeperList.add(player);
	}
	
	public void removeKeeperPlayer(TablePlayer player){
		dbManager.removeFromDatabase(dbManager.getKeeperFile(), player.getFirstName() + "," + player.getLastName());
		keeperList.remove(player);
	}
	
	//================================================//
	
	public DatabaseManager getDatabaseManager(){
		return dbManager;
	}
	
	public LeagueSettingsManager getSettingsManager(){
		return settings;
	}
	
	public ArrayList<Team> getTeams(){
		return teamList;
	}
}
