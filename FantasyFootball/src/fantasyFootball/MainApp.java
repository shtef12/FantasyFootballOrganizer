package fantasyFootball;

import java.io.IOException;

import fantasyFootball.controller.GUIController;
import fantasyFootball.controller.PlayerEditDialogController;
import fantasyFootball.controller.TeamEditDialogController;
import fantasyFootball.model.LeagueManager;
import fantasyFootball.model.TablePlayer;
import fantasyFootball.model.Team;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

	private LeagueManager lm = new LeagueManager();
	private Stage primaryStage;
	private AnchorPane rootLayout;
	private ObservableList<TablePlayer> keeperData = FXCollections.observableArrayList(lm.getKeepers());
	private ObservableList<Team> teamData = FXCollections.observableArrayList(lm.getTeams());
	private GUIController gui = new GUIController();

	public MainApp(){
		
	}
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/GUI.fxml"));
			rootLayout = (AnchorPane) loader.load();
			primaryStage.setTitle("Fantasy Football Manager");
			
			//this.primaryStage.getIcons().add(new Image("file:resources/images/addressBookImg.png"));
			this.primaryStage.setScene(new Scene(rootLayout,600,400));
			gui = loader.getController();
			gui.setMainApp(this);
			
			this.primaryStage.setMinWidth(800);
	        this.primaryStage.setMinHeight(600);
	        this.primaryStage.show();
	        
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public boolean showEditPlayerDialog(TablePlayer player){
		try{
			//load fxml file and create stage
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PlayerEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			//create dialog stage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Keeper");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			//set the person into controller
			PlayerEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPerson(player);
			
			//show dialog and wait until user closes it
			dialogStage.showAndWait();
			
			return controller.isOkClicked();
		}catch (IOException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean showEditTeamDialog(Team team){
		try{
			//load fxml file and create stage
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/TeamEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			//create dialog stage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Team");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			//set the person into controller
			TeamEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setTeam(team);
			
			//show dialog and wait until user closes it
			dialogStage.showAndWait();
			
			return controller.isOkClicked();
		}catch (IOException e){
			e.printStackTrace();
			return false;
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
	
	public ObservableList<TablePlayer> getKeeperObservableList(){
		return keeperData;
	}
	
	public ObservableList<Team> getTeamObservableList(){
		return teamData;
	}
	
	public LeagueManager getLeagueManager(){
		return lm;
	}
	
	public void setKeepersObsList(ObservableList<TablePlayer> list){
		keeperData = list;
	}
	
	public void refreshTable(){
		//keeperData.removeAll(keeperData);
		ObservableList<TablePlayer> data = FXCollections.observableList(lm.getKeepers());
		gui.getTable().setItems(data);
	}
}
