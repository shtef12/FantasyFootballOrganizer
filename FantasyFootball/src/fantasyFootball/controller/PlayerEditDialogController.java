package fantasyFootball.controller;

import org.controlsfx.dialog.Dialogs;

import fantasyFootball.model.TablePlayer;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PlayerEditDialogController {

	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField teamField;
	@FXML
	private TextField yearsLeftField;
	@FXML
	private TextField pickUsedField;
	@FXML
	private TextField positionField;
	
	private boolean okClicked;
	
	private Stage dialogStage;
	
	private TablePlayer player;
	
	public PlayerEditDialogController() {
		// TODO Auto-generated constructor stub
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
	
	public void setPerson(TablePlayer player){
		this.player = player;
		
		firstNameField.setText(player.getFirstName());
		lastNameField.setText(player.getLastName());
		teamField.setText(player.getTeam());
		yearsLeftField.setText(Integer.toString(player.getYearsLeft()));
		pickUsedField.setText(Integer.toString(player.getPickUsed()));
		positionField.setText(player.getPosition());
		
	}
	
	@FXML
	private void handleOk(){
		if(isInputValid()){
			player.setFirstName(firstNameField.getText());
			player.setLastName(lastNameField.getText());
			player.setTeam(teamField.getText());
			player.setYearsLeft(Integer.parseInt(yearsLeftField.getText()));
			player.setPickUsed(Integer.parseInt(pickUsedField.getText()));
			player.setPosition(positionField.getText());
			
			okClicked = true;
			dialogStage.close();
		}
	}
	
	private boolean isInputValid(){
		String errorMessage = "";
		if(firstNameField.getText() == null || firstNameField.getText().length() == 0){
			errorMessage += "No valid first name!\n";
		}if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n"; 
        }
        if (teamField.getText() == null || teamField.getText().length() == 0) {
            errorMessage += "No valid team!\n"; 
        }

        if (yearsLeftField.getText() == null || yearsLeftField.getText().length() == 0) {
            errorMessage += "No valid years left!\n"; 
        }else {
        	//try to parse postal code to int
        	try{
        		Integer.parseInt(yearsLeftField.getText());
        	}catch(NumberFormatException e){
        		errorMessage += "No valid number (must be an integer)!\n";
        	}
        }if (pickUsedField.getText() == null || pickUsedField.getText().length() == 0) {
            errorMessage += "No valid pick!\n"; 
        }else{
        	try{
        		Integer.parseInt(pickUsedField.getText());
        	}catch(NumberFormatException e){
        		errorMessage += "No valid number (must be an integer)!\n";
        	}
        }if(positionField.getText() == null || positionField.getText().length() == 0){
        	errorMessage += "No valid position!\n";
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
}
