package fantasyFootball.model;

import java.util.ArrayList;
import java.util.Random;

import fantasyFootball.MainApp;

public class DraftClass {

	private MainApp mainApp;
	private ArrayList<Team> TeamList;
	private Team[] DraftOrder;
	
	public DraftClass(MainApp mainapp) {
		// TODO Auto-generated constructor stub
		this.mainApp = mainapp;
		TeamList = mainApp.getLeagueManager().getTeams();
		DraftOrder = new Team[mainApp.getLeagueManager().getTeams().size()];
	}

	public void DraftOrder(int runTimes){
		
		int start = 1;
		int end = DraftOrder.length;
		
		for(int j = 0; j < runTimes; j++){
			for(int i = 1; i <= TeamList.size(); i++){
				//System.out.println(TeamList.length);
				CheckIfEmpty(start,end,i);
			}
			//resets the array
			DraftOrder = new Team[DraftOrder.length];
		}
		
		SortTeams();
		
		//prints out the contents of the teamlist array to show the draft order
		//System.out.println("Draft Order is:");
		/*for(int i = 0; i < TeamList.size(); i++){
			System.out.println(TeamList.get(i).ToString() + " at index " + i);
		}*/
		
		
	}
	
	private void CheckIfEmpty(int start, int end, int index){
		
		//sets up a random number between start and end
		Random randomGen = new Random();
		int RandomNum = randomGen.nextInt((end - start) + 1) + start;
		
		//takes one away from random number to keep it in bounds of the array
		int draftNum = RandomNum - 1;
		
		//if index draftnum is null, add the team to that spot in the draft order arrayList
		//else do the function again 
		if(DraftOrder[draftNum] == null){
			DraftOrder[draftNum] = TeamList.get(index - 1);
			DraftOrder[draftNum].addToDraftOrder(draftNum);
		}
		else{
			CheckIfEmpty(start,end,index);
		}
	}
	
	
	//sorts teams from lowest total number of draft order to the highest number
	private void SortTeams(){
		int j;
		boolean swapped = true;
		Team temp;
		
		while(swapped){
			swapped = false;
			for(j = 0; j < TeamList.size() - 1; j++){
				
				//if draft order is greater than draft order of next team, swap them
				if(TeamList.get(j).getDraftOrder() > TeamList.get(j + 1).getDraftOrder()){
					temp = TeamList.get(j);
					TeamList.set(j, TeamList.get(j+1));
					TeamList.set(j+1,temp);
					swapped = true;
				}
				
			}
		}
		
	}
	/**
	 * Returns the team list
	 * @return Team[] -> the team list
	 */
	public ArrayList<Team> getTeamList(){
		return TeamList;
	}
	
	public Team[] getDraftOrder(){
		return DraftOrder;
	}
	
}
