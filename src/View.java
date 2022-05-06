import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class View extends FlowPane {

    private TilePane teamPanel;
    private Button btnClear, btnExit, btnSave, btnGenerate, btnView, btnPlayers;
    private TextArea[] textTeams;
    private TitledPane[] teamNumbers;

    public Button getBtnClear(){return btnClear;}
    public Button getBtnExit() {
        return btnExit;
    }
    public Button getBtnGenerate() {
        return btnGenerate;
    }
    public Button getBtnSave(){
        return btnSave;
    }
    public Button getBtnPlayers() {
        return btnPlayers;
    }
    public Button getBtnView() {
        return btnView;
    }
    public TextArea[] getTextTeams() { return textTeams; }
    public TitledPane[] getTeamNumbers() { return teamNumbers;}

    public View(){
        // Declare and initialize image object
        ImageView ivBanner = new ImageView(new Image("file:images/battle_royale.jpg"));
        ImageView ivLogo = new ImageView(new Image("file:images/fortnite_logo.png", 175, 130, true, true));

        // title Pane for each team
        teamPanel = new TilePane();
        teamPanel.setPrefRows(4);
        teamPanel.setPrefColumns(4);
        teamPanel.setHgap(10);
        teamPanel.setVgap(10);
        teamPanel.setPadding(new Insets(10, 10, 10, 10));
        teamPanel.setStyle("-fx-background-color: rgb(118,201,241)");


        // style the flowPane
        setStyle("-fx-background-color: rgb(118,201,241)");
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(15, 10, 10, 10));
        setVgap(10);

        // buttons
        btnClear = new Button("CLEAR");
        setButton(btnClear);

        btnExit = new Button("EXIT");
        setButton(btnExit);

        btnPlayers = new Button("GET PLAYERS");
        setButton(btnPlayers);


        btnView = new Button("VIEW PLAYERS");
        setButton(btnView);

        btnSave = new Button("SAVE");
        setButton(btnSave);
        btnSave.setDisable(true);

        btnGenerate = new Button("GENERATE");
        setButton(btnGenerate);
        btnGenerate.setDisable(true);


        // text areas in the title pane
        textTeams = new TextArea[16];
        teamNumbers = new TitledPane[16];
        setTeamPanel();



        VBox sidePanel = new VBox();
        sidePanel.setStyle("-fx-background-color: rgb(118,201,241)");
        sidePanel.setPadding(new Insets(10, 10, 10, 10));
        sidePanel.setSpacing(15);
        sidePanel.setAlignment(Pos.TOP_CENTER);
        sidePanel.getChildren().addAll(btnPlayers, btnView, btnGenerate, btnSave, btnClear, btnExit, ivLogo);

        getChildren().addAll(ivBanner, teamPanel, sidePanel);
    }

    public void setButton(Button b){
        b.setPrefSize(150, 50);
        b.setFocusTraversable(false);
    }

    public void setTeamPanel(){
        for (int i = 0; i < textTeams.length; i++){
            teamNumbers[i] = new TitledPane();
            teamNumbers[i].setText("TEAM " + (i+1));
            teamNumbers[i].setCollapsible(false);
            teamNumbers[i].setFont(Font.font("Rockwell", FontWeight.BOLD, 14));

            textTeams[i] = new TextArea();
            textTeams[i].setPrefSize(175, 100);
            textTeams[i].setMaxSize(175, 100);
            textTeams[i].setEditable(false);
            textTeams[i].setFont(Font.font("Consolas", 11));
            teamNumbers[i].setContent(textTeams[i]);
            teamPanel.getChildren().add(teamNumbers[i]);
        }
    }

}
