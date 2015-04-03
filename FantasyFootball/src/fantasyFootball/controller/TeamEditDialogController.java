package fantasyFootball.controller;

import org.controlsfx.dialog.Dialogs;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import fantasyFootball.model.TablePlayer;
import fantasyFootball.model.Team;

public class TeamEditDialogController extends PlayerEditDialogController {

	@FXML
	private TextField ownerField;
	@FXML
	private TextField teamNameField;
	@FXML
	private TextField rankField;
	
	private boolean okClicked;
	
	private Stage dialogStage;
	
	private Team team;
	
	public TeamEditDialogController() {
		// TODO Auto-generated constructor stub
	}

	public void setTeam(Team team){
		this.team = team;
		
		ownerField.setText(team.getOwner());
		teamNameField.setText(team.getName());
		rankField.setText(Integer.toString(team.getRank()));
	}
	
	@FXML
	private void handleOk(){
		if(isInputValid()){
			team.setOwner(ownerField.getText());
			team.setName(teamNameField.getText());
			team.setRank(Integer.parseInt(rankField.getText()));
		
			okClicked = true;
			dialogStage.close();
		}
	}
	
	private boolean isInputValid(){
		String errorMessage = "";
		if(ownerField.getText() == null || ownerField.getText().length() == 0){
			errorMessage += "No valid owner name!\n";
		}if (teamNameField.getText() == null || teamNameField.getText().length() == 0) {
            errorMessage += "No valid team name!\n"; 
        }
        if (rankField.getText() == null || rankField.getText().length() == 0) {
            errorMessage += "No valid rank!\n"; 
        }else {
        	//try to parse rank to int
        	try{
        		Integer.parseInt(rankField.getText());
        	}catch(NumberFormatException e){
        		errorMessage += "No valid number (must be an integer)!\n";
        	}
        }
        if(errorMessage.length() == 0){
        	return true;
        }else{
        	//show error message
        	Dialogs.create()
        			.title("invalid fields")
        			.masthead("please correct invalid fields")
        			.message(errorMessage)
        			.showError();
        	return false;
        }
	}

	public void setDialogStage(Stage stage){
		dialogStage = stage;
	}
	
	public boolean isOkClicked(){
		return okClicked;
	}
	
	@FXML
	private void handleCancel(){
		dialogStage.close();
	}

}
