package fantasyFootball.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class for managing the league settings
 * @author Stefan
 *
 */
public class LeagueSettingsManager {

	//file for the settings
	//teamSize,numQB,numRB,numWR,numTE,numK,numDEF,numBench,numFlex
	private File settingsFile = new File("files/LeagueSettings.csv");
	private int teamSize;
	private int numQB;
	private int numRB;
	private int numWR;
	private int numTE;
	private int numK;
	private int numDEF;
	private int numBench;
	private int numFlex;
	
	public LeagueSettingsManager() {
		// TODO Auto-generated constructor stub
		refreshSettings();
	}

	private ArrayList<String> readLeagueSettings(){
		CsvWriter wr = new CsvWriter();
		ArrayList<String> contents = wr.getContents(settingsFile);
		ArrayList<String> settings = new ArrayList<String>();
		
		for(int i = 0; i < contents.size(); i++){
			String[] split = contents.get(i).split(",");
			String size = split[1];
			settings.add(size);
		}
		
		return settings;
	}
	
	private void setLeagueSettings(ArrayList<String> settings){
		
		setTeamSize(Integer.parseInt(settings.get(0)));
		setNumQB(Integer.parseInt(settings.get(1)));
		setNumRB(Integer.parseInt(settings.get(2)));
		setNumWR(Integer.parseInt(settings.get(3)));
		setNumTE(Integer.parseInt(settings.get(4)));
		setNumK(Integer.parseInt(settings.get(5)));
		setNumDEF(Integer.parseInt(settings.get(6)));
		setNumBench(Integer.parseInt(settings.get(7)));
		setNumFlex(Integer.parseInt(settings.get(8)));
	}
	
	/**
	 * reads from the settings file and updates any changes
	 */
	public void refreshSettings(){
		ArrayList<String> settings = readLeagueSettings();
		setLeagueSettings(settings);
		
	}
	
	private void writeToFile(String pos, int num){
		String posUpper = pos.toUpperCase();
		//copy file into new file up until the change then fill in rest and replace old with new file
		try{
			Scanner scanner = new Scanner(settingsFile);
			File tempFile = new File("files/temp.csv");
			FileWriter wr = new FileWriter(tempFile);
			String line;
			while(scanner.hasNext()){
				line = scanner.next();
				if(line.contains(pos)){
					wr.write(pos + "," + num + "\n");
				}else{
					wr.write(line + "\n");
				}
			}
			scanner.close();
			wr.close();
			settingsFile.delete();
			tempFile.renameTo(settingsFile);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	//============================//
	///Getters and Setters///
	//============================//
	
	public int getTeamSize(){
		return teamSize;
	}
	/**
	 * manually set the team size
	 * @param size
	 */
	public void setTeamSize(int size){
		teamSize = size;
		writeToFile("TOTAL", size);
	}
	/**
	 * sets the team size by adding the amount of each position together
	 */
	public void setTeamSize(){
		teamSize = getNumQB() + getNumRB() + getNumWR() + getNumTE() + getNumK() + getNumDEF() + getNumBench() + getNumFlex();
	}
	public int getNumQB(){
		return numQB;
	}
	public void setNumQB(int num){
		numQB = num;
		writeToFile("QB", num);
	}
	public int getNumRB(){
		return numRB;
	}
	public void setNumRB(int num){
		numRB = num;
		writeToFile("RB", num);
	}
	public int getNumWR(){
		return numWR;
	}
	public void setNumWR(int num){
		numWR = num;
		writeToFile("WR", num);
	}
	public int getNumTE(){
		return numTE;
	}
	public void setNumTE(int num){
		numTE = num;
		writeToFile("TE", num);
	}
	public int getNumK(){
		return numK;	
	}
	public void setNumK(int num){
		numK = num;
		writeToFile("K", num);
	}
	public int getNumDEF(){
		return numDEF;
	}
	public void setNumDEF(int num){
		numDEF = num;
		writeToFile("DEF", num);
	}
	public int getNumBench(){
		return numBench;
	}
	public void setNumBench(int num){
		numBench = num;
		writeToFile("BENCH", num);
	}
	public int getNumFlex(){
		return numFlex;
	}
	public void setNumFlex(int num){
		numFlex = num;
		writeToFile("FLEX", num);
	}
}
