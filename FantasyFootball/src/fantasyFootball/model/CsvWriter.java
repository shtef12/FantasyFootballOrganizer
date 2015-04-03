package fantasyFootball.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CsvWriter {

	
	public CsvWriter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * adds the line to the csv file
	 * player string should be in format lastname,firstname,years left,final year,pick
	 * @param player
	 */
	public void addToFile(String line, File file){
		
		PrintWriter out;
		try {
			
			out = new PrintWriter(new FileWriter(file,true));
			out.println(line);
			out.close();
			
		}catch(IOException ex){
			System.out.println("failed to add to file");
		}
	}
	
	public void addToFile(TablePlayer player, File file){
		
		PrintWriter out;
		try {
			
			out = new PrintWriter(new FileWriter(file,true));
			out.println(player.getFirstName() + "," + player.getLastName() + "," + player.getTeam() + "," + player.getYearsLeft() + "," + player.getPickUsed()
						+ "," + player.getPosition());
			out.close();
			
		}catch(IOException ex){
			System.out.println("failed to add to file");
		}
	}
		
	/**
	 * reads the file line by line
	 */
	public void readFile(File fileName){
	
		BufferedReader in;
		try {
			
			in = new BufferedReader(new FileReader(fileName));
		
		String line = null;
		
			while((line = in.readLine()) != null){
				
				Scanner scanner = new Scanner(line);
				
				while(scanner.hasNext()){
					
					System.out.println(scanner.next());	
				}
			}
	
			in.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * returns the contents of the csv file as an arrayList<string>
	 * @return
	 */
	public ArrayList<String> getContents(File fileName){
		ArrayList<String> players = new ArrayList<String>();
		
		BufferedReader rd;
		try{
			rd = new BufferedReader(new FileReader(fileName));
			String line;
			while((line = rd.readLine()) != null){
				Scanner scan = new Scanner(line);
				//scan.useDelimiter(",");
				while(scan.hasNext()){
					players.add(scan.next());
				}
			}
			rd.close();
			return players;
		}catch(IOException ex){
			System.out.println("failed to read file");
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * erases name from file
	 * @param fileName -->file to erase from
	 * @param playerName -->name to erase
	 */
	public void eraseFromFile(File fileName, String playerName){
		try{
			//create temporary file to write to
			File tempFile = new File("tempFile.csv");
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
			BufferedReader rd = new BufferedReader(new FileReader(fileName));
			String line;
			//read from file
			while((line = rd.readLine()) != null){
				if(line.contains(playerName)){
					//do nothing
				}else{
					//add to temp file
					pw.println(line);
				}
			}
			rd.close();
			pw.close();
			//delete original file
			fileName.delete();
			//rename temp to the original file
			tempFile.renameTo(fileName);
			
		}catch(IOException ex){
			System.out.println("failed to read file");
		}
	}
	
	/**
	 * returns a line from the file if it has the string given
	 * @param fileName -->file to search from
	 * @param playerName -->name to search for
	 * @return -->line from the file that contains the given string
	 */
	public String getPlayerLineFromFile(File fileName, String playerName){
		try{
			BufferedReader rd = new BufferedReader(new FileReader(fileName));
			String line;
			while((line = rd.readLine()) != null){
				if(line.contains(playerName)){
					return line;
				}
			}
			rd.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return null;
	}
}
