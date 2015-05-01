package fantasyFootball.model;

import java.util.Random;

import fantasyFootball.MainApp;

public class DraftClass {

	private MainApp mainApp;
	private Team[] TeamList;
	private Team[] DraftOrder;
	
	public DraftClass(MainApp mainapp) {
		// TODO Auto-generated constructor stub
		this.mainApp = mainapp;
		TeamList = new Team[mainApp.getLeagueManager().getTeams().size()];
		DraftOrder = new Team[mainApp.getLeagueManager().getTeams().size()];
	}

	private void DraftOrder(int runTimes){
		
		int start = 1;
		int end = DraftOrder.length;
		
		for(int j = 0; j < runTimes; j++){
			for(int i = 1; i <= TeamList.length; i++){
				//System.out.println(TeamList.length);
				CheckIfEmpty(start,end,i);
			}
			//resets the array
			DraftOrder = new Team[DraftOrder.length];
		}
		
		SortTeams();
		
		//prints out the contents of the teamlist array to show the draft order
		System.out.println("Draft Order is:");
		for(int i = 0; i < TeamList.length; i++){
			System.out.println(TeamList[i].ToString() + " at index " + i);
		}
		
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
			DraftOrder[draftNum] = TeamList[index - 1];
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
			for(j = 0; j < TeamList.length - 1; j++){
				
				//if draft order is greater than draft order of next team, swap them
				if(TeamList[j].getDraftOrder() > TeamList[j + 1].getDraftOrder()){
					temp = TeamList[j];
					TeamList[j] = TeamList[j+1];
					TeamList[j+1] = temp;
					swapped = true;
				}
				
			}
		}
		
	}
	public Team[] getDraftOrder(){
		return DraftOrder;
	}
	
}
