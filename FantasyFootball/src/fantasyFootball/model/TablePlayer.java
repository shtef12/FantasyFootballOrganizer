package fantasyFootball.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TablePlayer {

	private StringProperty firstName = new SimpleStringProperty();
	private StringProperty lastName = new SimpleStringProperty();
	private boolean isKeeper = false;
	private StringProperty team = new SimpleStringProperty();
	private IntegerProperty yearsLeft = new SimpleIntegerProperty();
	private IntegerProperty pickUsed = new SimpleIntegerProperty();
	private StringProperty position = new SimpleStringProperty();
	
	public TablePlayer(){
		
	}
	
	/**
	 * constructor used for a keeper player
	 * @param name   -->players name
	 * @param keeper -->is a keeper
	 * @param team   -->name of team keeper is on
	 * @param yearsLeft -->amount of years left for keeper
	 * @param pick   -->pick used for keeper
	 */
	public TablePlayer(String firstName, String lastName, boolean keeper, String team, int yearsLeft, int pick, String position){
		this.firstName.set(firstName);
		this.lastName.set(lastName);
		isKeeper = keeper;
		this.team.set(team);
		this.yearsLeft.set(yearsLeft);
		pickUsed.set(pick);
		this.position.set(position);
	}
	
	public String getFullName(){
		return firstName + " " + lastName;
	}
	
	public String getFirstName(){
		return firstName.get();
	}
	
	public void setFirstName(String name){
		firstName.set(name);
	}
	
	public String getLastName(){
		return lastName.get();
	}

	public void setLastName(String name){
		lastName.set(name);
	}
	
	public void setFullName(String first, String last){
		firstName.set(first);
		lastName.set(last);
	}
	
	public boolean isPlayerKeeper(){
		return isKeeper;
	}
	
	public void setIsKeeper(boolean keeper){
		isKeeper = keeper;
	}
	
	public String getTeam(){
		return team.get();
	}
	
	public void setTeam(String team){
		this.team.set(team);
	}
	
	public int getYearsLeft(){
		return yearsLeft.get();
	}
	
	public void setYearsLeft(int years){
		yearsLeft.set(years);
	}
	
	public int getPickUsed(){
		return pickUsed.get();
	}
	
	public void setPickUsed(int pick){
		pickUsed.set(pick);
	}
	
	public String getPosition(){
		return position.get();
	}
	
	public void setPosition(String position){
		this.position.set(position);
	}
	
	public String toString(){
		return (firstName.get() + " " + lastName.get() + " is on the team: " + team.get() + "," + " is a keeper? " + isKeeper + "," + "has " + yearsLeft.get() + " years left"
				+ ", " + "Pick used: " + pickUsed.get() + ", " + "Position is: " + position.get());
	}

}
