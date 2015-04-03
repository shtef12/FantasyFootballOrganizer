package fantasyFootball.controller;

import org.controlsfx.dialog.Dialogs;

import fantasyFootball.MainApp;
import fantasyFootball.model.LeagueManager;
import fantasyFootball.model.TablePlayer;
import fantasyFootball.model.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class GUIController {
	
	private MainApp mainApp;
	
	//======================//
	//Keeper table variables//
	//======================//
	@FXML
	private TableView<TablePlayer> keepersTable;
	@FXML
	private TableColumn<TablePlayer, String> firstNameCol;
	@FXML
	private TableColumn<TablePlayer, String> lastNameCol;
	@FXML
	private TableColumn<TablePlayer, String> teamNameCol;
	@FXML
	private TableColumn<TablePlayer, String> yearsLeftCol;
	@FXML
	private TableColumn<TablePlayer, String> pickUsedCol;
	@FXML
	private TableColumn<TablePlayer, String> positionCol;
	
	//====================//
	//Team table variables//
	//====================//
	@FXML
	private TableView<Team> teamTable;
	@FXML
	private TableColumn<Team, Integer> rankCol;
	@FXML
	private TableColumn<Team, String> nameCol;
	@FXML
	private TableColumn<Team, String> ownerCol;
	
	//=============================//
	//League Settings gui variables//
	//=============================//
	@FXML
	private TextField numQBField;
	@FXML
	private TextField numRBField;
	@FXML
	private TextField numWRField;
	@FXML
	private TextField numTEField;
	@FXML
	private TextField numKField;
	@FXML
	private TextField numDEFField;
	@FXML
	private TextField numFLEXField;
	@FXML
	private TextField numBENCHField;
	
	
	public GUIController() {
		// TODO Auto-generated constructor stub
	}
	
	@FXML
	private void initialize(){
		//sets the table listing the keepers
		firstNameCol.setCellValueFactory(new PropertyValueFactory<TablePlayer,String>("firstName"));
		lastNameCol.setCellValueFactory(new PropertyValueFactory<TablePlayer,String>("lastName"));
		teamNameCol.setCellValueFactory(new PropertyValueFactory<TablePlayer,String>("team"));
		yearsLeftCol.setCellValueFactory(new PropertyValueFactory<TablePlayer,String>("yearsLeft"));
		pickUsedCol.setCellValueFactory(new PropertyValueFactory<TablePlayer,String>("pickUsed"));
		positionCol.setCellValueFactory(new PropertyValueFactory<TablePlayer, String>("position"));
		
		//sets the table showing the teams in the league
		rankCol.setCellValueFactory(new PropertyValueFactory<Team, Integer>("rank"));
		nameCol.setCellValueFactory(new PropertyValueFactory<Team, String>("name"));
		ownerCol.setCellValueFactory(new PropertyValueFactory<Team, String>("owner"));
		
	}
	
	public void setMainApp(MainApp main){
		mainApp = main;
		keepersTable.setItems(mainApp.getKeeperObservableList());
		teamTable.setItems(mainApp.getTeamObservableList());
		
		//sets league settings text fields
		numQBField.setText(Integer.toString(mainApp.getLeagueManager().getSettingsManager().getNumQB()));
		numRBField.setText(Integer.toString(mainApp.getLeagueManager().getSettingsManager().getNumRB()));
		numWRField.setText(Integer.toString(mainApp.getLeagueManager().getSettingsManager().getNumWR()));
		numTEField.setText(Integer.toString(mainApp.getLeagueManager().getSettingsManager().getNumTE()));
		numKField.setText(Integer.toString(mainApp.getLeagueManager().getSettingsManager().getNumK()));
		numDEFField.setText(Integer.toString(mainApp.getLeagueManager().getSettingsManager().getNumDEF()));
		numFLEXField.setText(Integer.toString(mainApp.getLeagueManager().getSettingsManager().getNumFlex()));
		numBENCHField.setText(Integer.toString(mainApp.getLeagueManager().getSettingsManager().getNumBench()));
	}

	//==============================//
	//Handle keeper button functions//
	//==============================//
	@FXML
	private void handleAddKeeper(){
		TablePlayer newPlayer = new TablePlayer();
		boolean okClicked = mainApp.showEditPlayerDialog(newPlayer);
		if(okClicked){
			mainApp.getKeeperObservableList().add(newPlayer);
			mainApp.getLeagueManager().addKeeperPlayer(newPlayer);
		}
	}
	
	@FXML
	private void handleRemoveKeeper(){
		
		int selection = keepersTable.getSelectionModel().getSelectedIndex();
		if(selection >= 0){
			mainApp.getLeagueManager().removeKeeperPlayer(mainApp.getKeeperObservableList().get(selection));
			keepersTable.getItems().remove(selection);
			//System.out.println(mainApp.getLeagueManager().getKeepers());
		}
	}
	
	@FXML
	private void handleEditPlayer(){
		TablePlayer selectedPerson = keepersTable.getSelectionModel().getSelectedItem();
		int selection = keepersTable.getSelectionModel().getSelectedIndex();
		
		if(selectedPerson != null){
			boolean okClicked = mainApp.showEditPlayerDialog(selectedPerson);
			if(okClicked){
				//updates the table and the files
				mainApp.getLeagueManager().removeKeeperPlayer(selectedPerson);
				mainApp.getLeagueManager().addKeeperPlayer(selectedPerson);
				keepersTable.getColumns().get(0).setVisible(false);
				keepersTable.getColumns().get(0).setVisible(true);	
			}
		}else {
			//nothing selected
			Dialogs.create()
					.title("No Selection")
					.masthead("No person selected")
					.message("Please select a person in table")
					.showWarning();
		}
	}
	
	//=====================//
	//Handle team functions//
	//=====================//
	@FXML
	private void handleAddTeam(){
		Team newTeam = new Team();
		boolean okClicked = mainApp.showEditTeamDialog(newTeam);
		if(okClicked){
			mainApp.getTeamObservableList().add(newTeam);
			mainApp.getLeagueManager().addTeam(newTeam);
		}
	}
	
	@FXML
	private void handleRemoveTeam(){
		
		int selection = teamTable.getSelectionModel().getSelectedIndex();
		if(selection >= 0){
			mainApp.getLeagueManager().removeTeam(mainApp.getTeamObservableList().get(selection).getOwner());
			teamTable.getItems().remove(selection);
			//System.out.println(mainApp.getLeagueManager().getKeepers());
		}
	}
	
	@FXML
	private void handleEditTeam(){
		Team selectedTeam = teamTable.getSelectionModel().getSelectedItem();
		int selection = teamTable.getSelectionModel().getSelectedIndex();
		
		if(selectedTeam != null){
			boolean okClicked = mainApp.showEditTeamDialog(selectedTeam);
			if(okClicked){
				//updates the table and the files
				mainApp.getLeagueManager().removeTeam(selectedTeam.getOwner());
				mainApp.getLeagueManager().addTeam(selectedTeam);
				teamTable.getColumns().get(0).setVisible(false);
				teamTable.getColumns().get(0).setVisible(true);	
			}
		}else {
			//nothing selected
			Dialogs.create()
					.title("No Selection")
					.masthead("No team selected")
					.message("Please select a team in table")
					.showWarning();
		}
	}
	
	//================================//
	//Handle league settings functions//
	//================================//
	@FXML
	private void handleSettingsSave(){
		mainApp.getLeagueManager().getSettingsManager().setNumQB(Integer.parseInt(numQBField.getText()));
		mainApp.getLeagueManager().getSettingsManager().setNumRB(Integer.parseInt(numRBField.getText()));
		mainApp.getLeagueManager().getSettingsManager().setNumWR(Integer.parseInt(numWRField.getText()));
		mainApp.getLeagueManager().getSettingsManager().setNumTE(Integer.parseInt(numTEField.getText()));
		mainApp.getLeagueManager().getSettingsManager().setNumK(Integer.parseInt(numKField.getText()));
		mainApp.getLeagueManager().getSettingsManager().setNumDEF(Integer.parseInt(numDEFField.getText()));
		mainApp.getLeagueManager().getSettingsManager().setNumFlex(Integer.parseInt(numFLEXField.getText()));
		mainApp.getLeagueManager().getSettingsManager().setNumBench(Integer.parseInt(numBENCHField.getText()));
		
	}
	
	@FXML
	private void handleSettingsCancel(){
		numQBField.setText(Integer.toString(mainApp.getLeagueManager().getSettingsManager().getNumQB()));
		numRBField.setText(Integer.toString(mainApp.getLeagueManager().getSettingsManager().getNumRB()));
		numWRField.setText(Integer.toString(mainApp.getLeagueManager().getSettingsManager().getNumWR()));
		numTEField.setText(Integer.toString(mainApp.getLeagueManager().getSettingsManager().getNumTE()));
		numKField.setText(Integer.toString(mainApp.getLeagueManager().getSettingsManager().getNumK()));
		numDEFField.setText(Integer.toString(mainApp.getLeagueManager().getSettingsManager().getNumDEF()));
		numFLEXField.setText(Integer.toString(mainApp.getLeagueManager().getSettingsManager().getNumFlex()));
		numBENCHField.setText(Integer.toString(mainApp.getLeagueManager().getSettingsManager().getNumBench()));
	}
	
	public TableView<TablePlayer> getTable(){
		return keepersTable;
	}
}
