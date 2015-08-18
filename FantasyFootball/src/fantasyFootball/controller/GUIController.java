package fantasyFootball.controller;

import java.util.ArrayList;

import org.controlsfx.dialog.Dialogs;

import fantasyFootball.MainApp;
import fantasyFootball.model.LeagueManager;
import fantasyFootball.model.TablePlayer;
import fantasyFootball.model.Team;
import fantasyFootball.model.DraftClass;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

public class GUIController {
	
	private MainApp mainApp;
	private LeagueManager leagueManager;
	private DraftClass draftClass;
	
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
	
	//============================//
	//Draft settings variables//
	//============================//
	@FXML
	private ComboBox<Team> teamCombo1;
	@FXML
	private ComboBox<Team> teamCombo2;
	@FXML
	private ComboBox<Team> teamCombo3;
	@FXML
	private ComboBox<Team> teamCombo4;
	@FXML
	private ComboBox<Team> teamCombo5;
	@FXML
	private ComboBox<Team> teamCombo6;
	@FXML
	private ComboBox<Team> teamCombo7;
	@FXML
	private ComboBox<Team> teamCombo8;
	@FXML
	private ComboBox<Team> teamCombo9;
	@FXML
	private ComboBox<Team> teamCombo10;
	@FXML
	private ComboBox<Team> teamCombo11;
	@FXML
	private ComboBox<Team> teamCombo12;
	@FXML
	private ComboBox<Team> teamCombo13;
	@FXML
	private ComboBox<Team> teamCombo14;
	@FXML
	private ComboBox<Team> teamCombo15;
	@FXML
	private ComboBox<Team> teamCombo16;
	
	//=================================
	//Random draft button
	//=================================
	@FXML
	private Button randomDraftButton;
	
	//================================
	//Random draft text fields
	//================================
	@FXML
	private TextField teamField1;
	@FXML
	private TextField teamField2;
	@FXML
	private TextField teamField3;
	@FXML
	private TextField teamField4;
	@FXML
	private TextField teamField5;
	@FXML
	private TextField teamField6;
	@FXML
	private TextField teamField7;
	@FXML
	private TextField teamField8;
	@FXML
	private TextField teamField9;
	@FXML
	private TextField teamField10;
	@FXML
	private TextField teamField11;
	@FXML
	private TextField teamField12;
	@FXML
	private TextField teamField13;
	@FXML
	private TextField teamField14;
	@FXML
	private TextField teamField15;
	@FXML
	private TextField teamField16;
	
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
		
		//initializeComboboxes();
		
	}
	
	public void setMainApp(MainApp main){
		mainApp = main;
		leagueManager = main.getLeagueManager();
		draftClass = new DraftClass(main);
		
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
		
		initializeComboboxes();
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
	
	private void initializeComboboxes(){
		ComboBox[] comboBoxArray = {teamCombo1,teamCombo2,teamCombo3,teamCombo4,teamCombo5,teamCombo6,teamCombo7,teamCombo8,teamCombo9,
				teamCombo10,teamCombo11,teamCombo12,teamCombo13,teamCombo14,teamCombo15,teamCombo16};
		ObservableList<Team> teamData = mainApp.getTeamObservableList();
		
		//sets the team names into the options for the combobox
		for(int i = 0; i < teamData.size(); i++){
			for(int j = 0; j < teamData.size(); j++){
				comboBoxArray[i].getItems().add(teamData.get(j));
				//comboBoxArray[i].setItems(teamData);
			}
		}
		
		//set action events on the comboboxes
		setComboBoxEvents();
		
		//show team name in the list of the combobox
		//code.makery Marko Jakob
		for(int i = 0; i < comboBoxArray.length; i++){
			comboBoxArray[i].setCellFactory((comboBox)->{
				return new ListCell<Team>(){
					@Override
					protected void updateItem(Team item, boolean empty){
						super.updateItem(item,empty);
						if(item == null || empty){
							setText(null);
						}else{
							setText(item.getName());
						}
					}
				};
			});
		}
		
		//show team name when an item is selected in combobox
		//code.makery Marko Jakob
		for(int i = 0; i < comboBoxArray.length; i++){
			comboBoxArray[i].setConverter(new StringConverter<Team>(){
				@Override
				public String toString(Team team){
					if(team == null){
						return null;
					}else{
						return team.getName();
					}
				}
				@Override
				public Team fromString(String teamString){
					return null;
				}
			});
		}
	}
	
	/**
	 * adds on action events to the comboboxes
	 * the events adds the teams to the draft order array in the draft class
	 */
	private void setComboBoxEvents(){
		Team[] order = draftClass.getDraftOrder();
		
		teamCombo1.setOnAction((event)->{
			Team selectedTeam = teamCombo1.getSelectionModel().getSelectedItem();
			order[0] = selectedTeam;
		});
		teamCombo2.setOnAction((event)->{
			Team selectedTeam = teamCombo2.getSelectionModel().getSelectedItem();
			order[1] = selectedTeam;
		});
		teamCombo3.setOnAction((event)->{
			Team selectedTeam = teamCombo3.getSelectionModel().getSelectedItem();
			order[2] = selectedTeam;
		});
		teamCombo4.setOnAction((event)->{
			Team selectedTeam = teamCombo4.getSelectionModel().getSelectedItem();
			order[3] = selectedTeam;
		});
		teamCombo5.setOnAction((event)->{
			Team selectedTeam = teamCombo5.getSelectionModel().getSelectedItem();
			order[4] = selectedTeam;
		});
		teamCombo6.setOnAction((event)->{
			Team selectedTeam = teamCombo6.getSelectionModel().getSelectedItem();
			order[5] = selectedTeam;
		});
		teamCombo7.setOnAction((event)->{
			Team selectedTeam = teamCombo7.getSelectionModel().getSelectedItem();
			order[6] = selectedTeam;
		});
		teamCombo8.setOnAction((event)->{
			Team selectedTeam = teamCombo8.getSelectionModel().getSelectedItem();
			order[7] = selectedTeam;
		});
		teamCombo9.setOnAction((event)->{
			Team selectedTeam = teamCombo9.getSelectionModel().getSelectedItem();
			order[8] = selectedTeam;
		});
		teamCombo10.setOnAction((event)->{
			Team selectedTeam = teamCombo10.getSelectionModel().getSelectedItem();
			order[9] = selectedTeam;
		});
		teamCombo11.setOnAction((event)->{
			Team selectedTeam = teamCombo11.getSelectionModel().getSelectedItem();
			order[10] = selectedTeam;
		});
		teamCombo12.setOnAction((event)->{
			Team selectedTeam = teamCombo12.getSelectionModel().getSelectedItem();
			order[11] = selectedTeam;
		});
		teamCombo13.setOnAction((event)->{
			Team selectedTeam = teamCombo13.getSelectionModel().getSelectedItem();
			order[12] = selectedTeam;
		});
		teamCombo14.setOnAction((event)->{
			Team selectedTeam = teamCombo14.getSelectionModel().getSelectedItem();
			order[13] = selectedTeam;
		});
		teamCombo15.setOnAction((event)->{
			Team selectedTeam = teamCombo15.getSelectionModel().getSelectedItem();
			order[14] = selectedTeam;
		});
		teamCombo16.setOnAction((event)->{
			Team selectedTeam = teamCombo16.getSelectionModel().getSelectedItem();
			order[15] = selectedTeam;
		});
	}
	
	@FXML
	private void handleRandomDraft(){
		draftClass.DraftOrder(1000);
		ArrayList<Team> draftOrder = draftClass.getTeamList();
		teamField1.setText(draftOrder.get(0).getOwner());
		teamField2.setText(draftOrder.get(1).getOwner());
		teamField3.setText(draftOrder.get(2).getOwner());
		teamField4.setText(draftOrder.get(3).getOwner());
		teamField5.setText(draftOrder.get(4).getOwner());
		teamField6.setText(draftOrder.get(5).getOwner());
		teamField7.setText(draftOrder.get(6).getOwner());
		teamField8.setText(draftOrder.get(7).getOwner());
		teamField9.setText(draftOrder.get(8).getOwner());
		teamField10.setText(draftOrder.get(9).getOwner());
		teamField11.setText(draftOrder.get(10).getOwner());
		teamField12.setText(draftOrder.get(11).getOwner());
		if(draftOrder.size() == 13 && draftOrder.get(12) != null)
			teamField13.setText(draftOrder.get(12).getOwner());
		if(draftOrder.size() == 14 && draftOrder.get(13) != null)
			teamField14.setText(draftOrder.get(13).getOwner());
		if(draftOrder.size() == 15 && draftOrder.get(14) != null)
			teamField15.setText(draftOrder.get(14).getOwner());
		if(draftOrder.size() == 16 && draftOrder.get(15) != null)
			teamField16.setText(draftOrder.get(15).getOwner());
		
	}
	public TableView<TablePlayer> getTable(){
		return keepersTable;
	}
}


