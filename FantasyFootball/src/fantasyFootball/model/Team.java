package fantasyFootball.model;

//import java.util.HashMap;
//import java.util.Map.Entry;

public class Team {

	private int draftOrder = 0;
	private String name = "none";
	private String owner = " ";
	private int rank = 0;
	private String[] startQB;
	private String[] startRB;
	private String[] startWR;
	private String[] startTE;
	private String[] startK;
	private String[] startDEF;
	private String[] startFlex;
	private String[] bench;
	private int teamSize;
	private LeagueSettingsManager lsm;
	
	public Team(String owner, String name, int rank) {
		// TODO Auto-generated constructor stub
		this.owner = owner;
		this.name = name;
		this.rank = rank;
		
	}
	
	public Team(String owner){
		this.owner = owner;
		//setPositionSizes();
	}
	
	public Team() {
		// TODO Auto-generated constructor stub
	}

	public void setPositionSizes(LeagueSettingsManager settings){
		lsm = settings;
		setPositionSizes();
	}
	
	private void setPositionSizes(){
		startQB = new String[lsm.getNumQB()];
		startRB = new String[lsm.getNumRB()];
		startWR = new String[lsm.getNumWR()];
		startTE = new String[lsm.getNumTE()];
		startK = new String[lsm.getNumK()];
		startDEF = new String[lsm.getNumDEF()];
		startFlex = new String[lsm.getNumFlex()];
		bench = new String[lsm.getNumBench()];
		teamSize = lsm.getTeamSize();
	}
	
	//====================================================//
	//Roster functions//
	//====================================================//
	
	/**
	 * Adds a player to the qb array if there is an open spot.
	 * @param player -> player to add to the array
	 * @return Returns true if there is an open spot in the start QB array
	 */
	public boolean addToQB(String player){
		for(int i = 0; i < startQB.length; i++){
			if(startQB[i] == "" || startQB[i] == null){
				startQB[i] = player;
				return true;
			}
		}
		return false;
	}
	/**
	 * Adds a player to the rb array if there is an open spot.
	 * @param player -> player to add to the array
	 * @return Returns true if there is an open spot in the start RB array
	 */
	public boolean addToRB(String player){
		for(int i = 0; i < startRB.length; i++){
			if(startRB[i] == "" || startRB[i] == null){
				startRB[i] = player;
				return true;
			}
		}
		return false;
	}
	/**
	 * Adds a player to the wr array if there is an open spot.
	 * @param player -> player to add to the array
	 * @return Returns true if there is an open spot in the start WR array
	 */
	public boolean addToWR(String player){
		for(int i = 0; i < startWR.length; i++){
			if(startWR[i] == "" || startWR[i] == null){
				startWR[i] = player;
				return true;
			}
		}
		return false;
	}
	/**
	 * Adds a player to the te array if there is an open spot.
	 * @param player -> player to add to the array
	 * @return Returns true if there is an open spot in the start TE array
	 */
	public boolean addToTE(String player){
		for(int i = 0; i < startTE.length; i++){
			if(startTE[i] == "" || startTE[i] == null){
				startTE[i] = player;
				return true;
			}
		}
		return false;
	}
	/**
	 * Adds a player to the k array if there is an open spot.
	 * @param player -> player to add to the array
	 * @return Returns true if there is an open spot in the start K array
	 */
	public boolean addToK(String player){
		for(int i = 0; i < startK.length; i++){
			if(startK[i] == "" || startK[i] == null){
				startK[i] = player;
				return true;
			}
		}
		return false;
	}
	/**
	 * Adds a player to the def array if there is an open spot.
	 * @param player -> player to add to the array
	 * @return Returns true if there is an open spot in the start DEF array
	 */
	public boolean addToDEF(String player){
		for(int i = 0; i < startDEF.length; i++){
			if(startDEF[i] == "" || startDEF[i] == null){
				startDEF[i] = player;
				return true;
			}
		}
		return false;
	}
	/**
	 * Adds a player to the flex array if there is an open spot.
	 * @param player -> player to add to the array
	 * @return Returns true if there is an open spot in the start FLEX array
	 */
	public boolean addToFlex(String player){
		for(int i = 0; i < startFlex.length; i++){
			if(startFlex[i] == "" || startFlex[i] == null){
				startFlex[i] = player;
				return true;
			}
		}
		return false;
	}
	/**
	 * Adds a player to the bench array if there is an open spot.
	 * @param player -> player to add to the array
	 * @return Returns true if there is an open spot in the start bench array
	 */
	public boolean addToBench(String player){
		for(int i = 0; i < bench.length; i++){
			if(bench[i] == "" || bench[i] == null){
				bench[i] = player;
				return true;
			}
		}
		return false;
	}
	//======================================================//
	//functions for setting the draft order//
	//======================================================//
	/**
	 * Returns the current draft order
	 * @return
	 */
	public int getDraftOrder(){
		return draftOrder;
	}

	/**
	 * Set the current draft order to the parameter given
	 * @param newOrder
	 */
	public void setDraftOrder(int newOrder){
		draftOrder = newOrder;
	}
	
	/**
	 * Add to the current draft order by the parameter given
	 * @param numToAdd
	 */
	public void addToDraftOrder(int numToAdd){
		draftOrder += numToAdd;
	}
	
	//========================================================//
	//Getters and Setters//
	//========================================================//
	/**
	 * Returns the name of the team
	 * @return
	 */
	public String getName(){
		return name;
	}
	
	public void setName(String newName){
		name = newName;
	}
	
	public String getOwner(){
		return owner;
	}
	
	public void setOwner(String newOwner){
		owner = newOwner;
	}
	
	public int getRank() {
		return rank;
	}
	
	public void setRank(int rank){
		this.rank = rank;
	}
	
	public String[] getStartQB(){
		return startQB;
	}
	
	public String[] getStartRB(){
		return startRB;
	}
	
	public String[] getStartWR(){
		return startWR;
	}
	
	public String[] getStartTE(){
		return startTE;
	}
	
	public String[] getStartDef(){
		return startDEF;
	}
	
	public String[] getStartK(){
		return startK;
	}
	
	public String[] getStartFlex(){
		return startFlex;
	}
	
	public String[] getBench(){
		return bench;
	}
	//========================================================//
	public String ToString(){
		return (name + "'s owner is: " + owner + ", " + "current draft order is " + (draftOrder + 1));
	}

}
