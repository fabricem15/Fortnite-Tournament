import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class PlayerList extends Stage {

	private Button btnRemove, btnAdd, btnSort;
	private ObservableList<Player> list;
	private ListView<Player> listview;
	private Alert alert;
	private Stage primaryStage;
	private Player p;
	private boolean valid;
	private boolean cancelled;
	public PlayerList(Stage ps) {

		// Initialize image and imageview objects
		Image imgBanner = new Image("file:images/fortnite.png", 200, 100, true, true);
		ImageView ivBanner = new ImageView(imgBanner);

		alert = new Alert(AlertType.NONE);
		alert.setHeaderText(null);
		// Set primary stage
		primaryStage = ps;

		valid = false;
		// Initialize observable list

//		list = FXCollections.observableArrayList(Main.players);

		listview = new ListView<Player>();
		listview.setPrefSize(350, 500);
		listview.setItems(list);
		listview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		btnRemove = new Button("REMOVE");
		btnRemove.setPrefSize(100, 40);

//		btnRemove.setOnAction(new EventHandler<ActionEvent> (){
//			public void handle (ActionEvent e){
//				Alert alert = new Alert(AlertType.CONFIRMATION);
//				alert.setHeaderText(null);
//
//				// If the user has not selected a player
//				if (listview.getSelectionModel().getSelectedItem() == null){
//					alert.setAlertType(AlertType.ERROR);
//					alert.setContentText("Please select a player to remove!");
//					alert.getButtonTypes().clear();
//					alert.getButtonTypes().add(ButtonType.OK);
//					alert.showAndWait();
//
//				}
//				// If the user has selected an player
//				else
//				{
//					if (Main.players.size() > 32)
//					{
//
//						// Confirm the user's decision
//						alert.getButtonTypes().clear();
//						alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
//						String name = listview.getSelectionModel().getSelectedItem().getName();
//						alert.setContentText("Are you sure you want to remove " + name + "?");
//						Optional <ButtonType> result = alert.showAndWait();
//
//						// If the user clicks yes
//						// Remove player from the list
//						if (result.get() == ButtonType.YES){
//
//							Player pRemove = listview.getSelectionModel().getSelectedItem();
//							list.remove(pRemove);
//							Main.players.remove(pRemove);
//
//						}
//					}
//					// If the number of players in the tournament is 32
//					else
//					{
//						alert.setAlertType(AlertType.WARNING);
//						alert.setContentText("No more players can be removed.\n"
//								+ "The tournament has reached its minimum capacity of 32 players.");
//						alert.getButtonTypes().clear();
//						alert.getButtonTypes().add(ButtonType.OK);
//						alert.showAndWait();
//					}
//
//				}
//
//			}
//		});

		btnAdd = new Button("ADD");
		btnAdd.setPrefSize(100, 40);
		btnAdd.setOnAction(new EventHandler<ActionEvent> (){
			public void handle(ActionEvent e){

				// Initialize booleans;
				boolean exists = false;
				cancelled = false;

				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText(null);

				String fName = "";
				String lName = "";
				int rating = 0;

//				if (Main.players.size() < 64){
//
//					TextInputDialog adding = new TextInputDialog();
//					adding.setHeaderText(null);
//					adding.setTitle("Add Player");
//
//					// Change the background colour of the text dialog
//					adding.getDialogPane().setStyle("-fx-background-color: beige");
//
//					try {
//
//						// Continuously prompt the user for a name as long as the textInput dialog is empty
//						do {
//
//							adding.setContentText("Enter player's first name:");
//							Optional<String> result = adding.showAndWait();
//
//
//							// If the user clicked ok
//							if (result.isPresent())
//							{
//								fName = result.get();
//								cancelled = false;
//								// If the user left the dialog empty
//								if (fName.equals("")) {
//									valid = false;
//									alert.setContentText("Please enter a name!");
//									alert.showAndWait();
//								}
//								// If the user entered a valid name
//								else {
//									valid = true;
//								}
//							}
//							// If the user clicked cancel
//							else
//							{
//								cancelled = true;
//							}
//
//						}
//						while (!valid && cancelled == false);
//						// Clear the text area
//						adding.getEditor().clear();
//
//						// Prompt the user for a last name
//						if (cancelled == false) {
//
//							do {
//
//								adding.setContentText("Enter player's last name:");
//								Optional <String> result = adding.showAndWait();
//
//								// If the user clicked ok
//								if (result.isPresent())
//								{
//									lName = result.get();
//									cancelled = false;
//									// If the text area is empty
//									if (lName.equals(""))
//									{
//										valid = false;
//										alert.setContentText("Please enter a name!");
//										alert.showAndWait();
//									}
//									else
//									{
//										valid = true;
//									}
//								}
//								// If the user clicked cancel
//								else
//								{
//									cancelled = true;
//								}
//							}
//							while (!valid && cancelled == false);
//						}
//
//
//
//						// Prompt the user for a rating
//						if (cancelled == false) {
//							do {
//								// Clear text area
//								adding.getEditor().clear();
//								adding.setContentText("Enter player's rating(0-100):");
//
//								Optional <String> result = adding.showAndWait();
//
//								try {
//									// If the user clicked ok
//									if (result.isPresent())
//									{
//										cancelled = false;
//										// if the text area was left empty
//										if (result.get().equals(""))
//										{
//											valid = false;
//											alert.setContentText("Please enter a number!");
//											alert.showAndWait();
//										}
//										// if the user input is not empty
//										else
//										{
//											// Catch an input greater than 100 or smaller than 0
//											rating = Integer.parseInt(result.get());
//											// if the user entered an invalid rating
//											if (rating < 0 || rating > 100)
//											{
//												alert.setContentText("Please enter a valid number.");
//												alert.showAndWait();
//												valid = false;
//											}
//											// else stop the loop
//											else
//											{
//												valid = true;
//											}
//										}
//									}
//									else
//									{
//										cancelled = true;
//									}
//
//								}
//								// Catch a number format exception if the user entered a different type of data
//								catch(NumberFormatException ex) {
//									valid = false;
//									alert.setContentText("Please enter a valid number!");
//									alert.showAndWait();
//
//								}
//							}
//							while(!valid && cancelled == false);
//						}
//					}
//					catch (NullPointerException ex) {
//						ex.printStackTrace();
//					}
//
//					String tier = "";
//
//					// If the rating is less than 50
//					if (rating < 50) {
//						tier = "Scout";
//					}
//					// If the user's rating is between 50 and 59
//					else if (rating >= 50 && rating <= 59){
//						tier = "Ranger";
//					}
//					// If the user's rating is between 60 and 69
//					else if (rating >= 60 && rating <= 69) {
//						tier = "Agent";
//					}
//					// If the user's rating is between 70 and 79
//					else if (rating >= 70 && rating <= 79) {
//						tier = "Epic";
//					}
//					// If the user's rating is above 80
//					else if (rating >= 80) {
//						tier = "Legend";
//					}
//
//					p = new Player(fName, lName, rating, tier);
//
//					// Make sure the player's name is not part of the list before adding
//					// If it is, tell them that the player already exists
//					for (int i = 0; i < Main.players.size(); i++){
//						if (Main.players.get(i).getName().equalsIgnoreCase(p.getName())){
//							exists = true;
//							String name = Main.players.get(i).getName();
//							alert.setContentText(name + " already exists!");
//							alert.showAndWait();
//
//						}
//					}
//					// Add the player to the list if they are not part of the list
//					if (exists == false && cancelled == false){
//						list.add(p);
//						Main.players.add(p);
//
//					}
//				}
//				// If the maximum number of players have been reached
//				else {
//					alert.setContentText("No more players can be added.\n"
//							+ "The tournament has reached its capacity of 64 players.");
//					alert.setAlertType(AlertType.WARNING);
//					alert.showAndWait();
//
//				}
			}
		});
//
		btnSort = new Button("SORT");
		btnSort.setPrefSize(100, 40);

		btnSort.setOnAction(new EventHandler<ActionEvent> (){
			public void handle(ActionEvent e){

				// Prompt the user to specify how they want the list sorted
				Alert sort = new Alert(AlertType.CONFIRMATION);
				sort.setContentText("How would you like the list sorted?");
				sort.setHeaderText(null);
				sort.getButtonTypes().clear();
				// Add 2 buttons
				ButtonType btnname = new ButtonType("Name");
				ButtonType btnRating = new ButtonType("Rating");
				sort.getButtonTypes().addAll(btnname, btnRating);

//				Optional <ButtonType> result = sort.showAndWait();
//				// Sort by name if they clicked button name
//				if (result.get()== btnname){
//					Player.sortby(Player.SORT_BY_NAME);
//					Collections.sort(Main.players);
//					list = FXCollections.observableArrayList(Main.players);
//					listview.setItems(list);
//
//				}
//				// Sort by rating if they clicked button rating
//				// If two players have the same rating
//				// Sort by name first, then by sort by rating
//				else {
//					Player.sortby(Player.SORT_BY_NAME);
//					Collections.sort(Main.players);
//					Player.sortby(Player.SORT_BY_RATING);
//					Collections.sort(Main.players, Collections.reverseOrder());
//
//					list = FXCollections.observableArrayList(Main.players);
//					listview.setItems(list);
//				}
//
			}
		});

		TitledPane listPane = new TitledPane(String.format("%-30s%-10s%-10s",
				"NAME", "RATING", "TIER"), listview);
		listPane.setFont(Font.font("Consolas", FontWeight.BOLD, 16));
		listPane.setCollapsible(false);

		FlowPane root = new FlowPane();
		root.setStyle("-fx-background-color: rgb(118,201,241)");
		root.setPadding(new Insets(20, 10, 20, 10));
		root.setHgap(10);
		root.setVgap(15);
		root.setAlignment(Pos.TOP_CENTER);
		root.getChildren().addAll(ivBanner, listPane, btnRemove, btnAdd, btnSort);

		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		setTitle("Player List");
		setScene(scene);
		show();
		setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent e) {
				primaryStage.show();
			}
		});
	}
}

