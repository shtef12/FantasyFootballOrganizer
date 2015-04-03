package fantasyFootball.model;

import java.util.ArrayList;

public class Division {

	private String name;
	private ArrayList<Team> teamsInDiv = new ArrayList<Team>();
	
	public Division(String divName) {
		// TODO Auto-generated constructor stub
		name = divName;
	}
	
	/**
	 * add a team to the division
	 * @param newTeam -->the team to add to the division
	 */
	public void addToDiv(Team newTeam){
		
		if(!teamsInDiv.contains(newTeam)){
			for(Team team : teamsInDiv){
				if(team.getName().equals(newTeam.getName())){
					return;
				}
			}
			teamsInDiv.add(newTeam);
		}
	}
	
	/**
	 * Remove a team from the division
	 * @param teamToRemove -->name of the team to remove
	 */
	public void removeFromDiv(String teamToRemove){
		for(int i = 0; i < teamsInDiv.size()-1;i++){
			if(teamsInDiv.get(i).getName().equals(teamToRemove)){
				teamsInDiv.remove(i);
			}
		}
	}
	
	/**
	 * Get an arrayList of all the teams in the division
	 * @return
	 */
	public ArrayList<Team> getTeamsInDiv(){
		return teamsInDiv;
	}

	/**
	 * Returns the name of the division
	 * @return
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Set the name of the division
	 * @param divName -->new name to give to the division
	 */
	public void setName(String divName){
		name = divName;
	}
	
	public String toString(){
		return "this is " + name;
	}
}
