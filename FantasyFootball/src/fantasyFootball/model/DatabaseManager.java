package fantasyFootball.model;

import java.io.File;
import java.util.ArrayList;

public class DatabaseManager {

	private File keepersFile = new File("files/Keepers.csv");
	private static final CsvWriter Writer = new CsvWriter();
	
	public DatabaseManager() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * returns the file where the keepers are stored
	 * @return
	 */
	public File getKeeperFile(){
		return keepersFile;
	}
	
	/**
	 * adds a player to the file that is specified
	 * @param player -->the player to add to the database
	 * 					if the player is a keeper player make sure the string is in
	 * 					the format: lastname,firstname,yearsleft(0-3),finalyear(2016,etc...),pickdrafted
	 * @param file   -->the file to add the player to
	 * @param isKeeper -->is the player a keeper player?
	 */
	public void addToDatabase(String player, File file, boolean isKeeper){
		if(isKeeper == true){
			Writer.addToFile(player, keepersFile);
		}else{
			Writer.addToFile(player,file);
		}
	}
	
	public void addToDatabase(TablePlayer player, File file){
		if(player.isPlayerKeeper() == true){
			Writer.addToFile(player, keepersFile);
		}else{
			Writer.addToFile(player,file);
		}
	}
	
	/**adds a player to the file that is specified
	 * @param player -->the player to add to the database
	 * 					if the player is a keeper player use: 
						addToDatabase(string player,file file,boolean iskeeper)
	 * @param file   -->the file to add the player to
	 */
	public void addToDatabase(String player, File file){
		
		Writer.addToFile(player,file);
	}
	
	/**
	 * Returns the lastname,firstname of all the players in the file
	 * @return  -->list of all the player names in file
	 */
	public ArrayList<String[]> getPlayerNames(File file){
		
		ArrayList<String> players = Writer.getContents(file);
		ArrayList<String[]> names = new ArrayList<String[]>();
		
		for(int i = 0; i < players.size()-1; i++){
			//splits the names by the comma
			String[] split = players.get(i).split(",");
			String[] name = {split[0], split[1]};
			names.add(name);
			
		}
		return names;
	}
	
	public void removeFromDatabase(File file, String playerName){
		Writer.eraseFromFile(file, playerName);
	}
	
	public ArrayList<String> getKeepers(){
		return Writer.getContents(keepersFile);
	}
	
	/**
	 * returns the contents of keeper database
	 * @return -->list of keepers
	 */
	public ArrayList<String[]> getKeeperData(){
		ArrayList<String> contents = Writer.getContents(keepersFile);
		ArrayList<String[]> database = new ArrayList<String[]>();
		
		for(int i = 0; i < contents.size(); i++){
			String[] split = contents.get(i).split(",");
			database.add(split);
		}
		return database;
	}
	
	public ArrayList<String[]> getData(File file){
		ArrayList<String> contents = Writer.getContents(file);
		ArrayList<String[]> data = new ArrayList<String[]>();
		
		for(int i = 0; i < contents.size(); i++){
			String[] split = contents.get(i).split(",");
			data.add(split);
		}
		return data;
	}
	
}
