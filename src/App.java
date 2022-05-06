import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

// controller class
public class App extends Application {
    private View view;
    private Tournament model;
    private FileChooser fc;

    @Override
    public void start(Stage primaryStage) throws Exception {

        view = new View();
        model = new Tournament();

        view.getBtnPlayers().setOnAction(e-> getPlayers(primaryStage));
        view.getBtnGenerate().setOnAction(e->generatePlayers());
        view.getBtnSave().setOnAction(e->saveTeams(primaryStage));
        view.getBtnClear().setOnAction(e->clearTeams());
        view.getBtnExit().setOnAction(e->exit());

        primaryStage.setResizable(false);
        primaryStage.setTitle("Fortnite Team Tournament");
        primaryStage.setScene(new Scene(view, 1000,750));
        primaryStage.show();
        primaryStage.setOnCloseRequest(e->exit());
    }

    private void generatePlayers(){
        // enable save button
        view.getBtnSave().setDisable(false);
        Player [] playerRemoved = model.getPlayerRemoved();
        model.initPlayers(); // initialize player variables

        if (model.getRemainder()==0){
            outputTeams();
        }
        else{
            playerRemoved[0] = outputTeams(model.getRemainder());
        }

        double num = model.generateRating();

        TextArea[] textTeams = view.getTextTeams();
        int tiers = model.getTiers();

        textTeams[tiers].appendText(String.format("%-40s%-3s",
                playerRemoved[0].getName(), playerRemoved[0].getRating()) + "\n");
        textTeams[tiers].appendText(String.format("%-40s%-3s",
                playerRemoved[1].getName(), playerRemoved[1].getRating()) + "\n");
        textTeams[tiers].appendText(String.format("%-40s%-3s","TEAM RATING:", Math.round(num)));
    }

    private void getPlayers(Stage p){
        view.getBtnGenerate().setDisable(false);

        fc = new FileChooser();
        fc.setInitialDirectory(new File("."));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter
                ("Text Files (*.txt)", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*"));
        // Initialize the file object
        File f = fc.showOpenDialog(p);
        view.getBtnGenerate().setDisable(false); // unable generate btn

        // read from file
        String [] info;
        String line;

        try{
            // read from a comma-delimited text file if it's not null
            if (f !=null){
                BufferedReader br = new BufferedReader(new BufferedReader(new FileReader(f)));

                line = br.readLine();
                // read till you reach eof
                while (line !=null){
                    info = line.split(",");
                    Player player = new Player(info[1], info[0], Integer.parseInt(info[2]), info[3]);
                    model.players.add(player);
                    line = br.readLine();
                }
                br.close();
            }
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    private void saveTeams(Stage primaryStage){
        File f;
         //Prompt the user to save it to a word document
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Word document (*.doc)", "*.doc"),
                new FileChooser.ExtensionFilter("All Files", "*"));
        // Open a show save dialog
        f = fc.showSaveDialog(primaryStage);


        // Declare and give a default value to the buffered writer
        BufferedWriter bw = null;

        // Write to the file if it is not null
        if (f != null) {
            try {

                bw = new BufferedWriter(new FileWriter (f));
                TextArea [] textTeams = view.getTextTeams();
                int tiers = model.getTiers();

                // Write to the word document
                for (int i = 0; i < tiers; i++) {
                    int num = i + 1;
                    bw.write("TEAM #" + num);

                    bw.newLine();
                    bw.newLine();
                    // Write the teams
                    bw.write(textTeams[i].getText());
                    // Skip two lines
                    bw.newLine();
                    bw.newLine();
                }

                if (model.getRemainder() != 0) {
                    bw.write("TEAM #" + tiers);
                    bw.newLine();
                    bw.newLine();
                    bw.write(textTeams[tiers].getText());
                    bw.newLine();
                    bw.newLine();
                }
                // Close the buffer writer
                bw.close();
            }
            catch (IOException ex) {
                System.err.print(ex);
            }
        }
    }

    private void exit(){
        File f = new File("playerlist.txt");
        // rewrite to the text file

        try{
            Collections.shuffle(model.players);
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            for (int i =0; i < model.players.size();i++){
                bw.write(model.players.get(i).getLastName() + ","
									+ model.players.get(i).getFirstName() + "," +
									model.players.get(i).getRating() + ","+ model.players.get(i).getTier());
                bw.newLine();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }

        // Confirm the user's decision to exit
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Fortnite tournament");
        alert.setContentText("Are you sure you want to exit?");
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            System.exit(0);
            Platform.exit();
        }
    }

    private void clearTeams(){
        // clear all text areas
        TextArea[] textTeams = view.getTextTeams();
        for (int i =0; i < textTeams.length; i++){
            textTeams[i].clear();
        }
    }

    // Output 4 players in each teams if the number of players is a multiple of 4
    public void outputTeams () {

        // Set the second, third, and fourth index for the players of each team
        int tiers = model.getTiers();
        Player [] p = model.getPlayers();
        double [] totalRating = model.getTotalRating();


        int s = tiers * 2-1;
        int t = (tiers * 2);
        int f = (tiers * 4) -1;

        TextArea[] textTeams = view.getTextTeams();
        for (int i = 0; i < tiers; i++){
            textTeams[i].clear();
            // Output the players in a balanced way
            textTeams[i].appendText(String.format("%-40s%-3s",
                    p[i].getName(),
                    p[i].getRating()) + "\n");

            textTeams[i].appendText(String.format("%-40s%-3s",
                    p[s].getName(), p[s].getRating()) + "\n");

            textTeams[i].appendText(String.format("%-40s%-3s",
                    p[t].getName(), p[t].getRating()) + "\n");

            textTeams[i].appendText(String.format("%-40s%-3s",
                    p[f].getName(), p[f].getRating()) + "\n");

            // Calculate their team rating
            totalRating[i] = p[f].getRating() +
                    p[s].getRating() + p[t].getRating() + model.players.get(i).getRating();

            double num = totalRating[i] / 4;

            // Output team rating
            textTeams[i].appendText(String.format("\n%-40s%-3s",
                    "TEAM RATING:", Math.round(num)));

            // Decrease or increase the indexes
            s--;
            f--;
            t++;
        }
    }

    // Output players in a balanced way if the number of players is not a multiple of 4
    public Player outputTeams(int r) {

        Player[] playerRemoved = model.getPlayerRemoved();
        int tiers = model.getTiers();
        double [] totalRating = model.getTotalRating();

        List<Player> Play = model.getPlay();

        // Set the second, third, and fourth index for the players of each team
        int s, t,f;
        s = 0;
        t = 0;
        f = 0;

        // Set player to be put in the last team
        int x = tiers * 2;

        if (r == 1)
        {
            s = tiers * 2-1;
            // Skip player that are being removed
            t = (tiers * 2) + 1;
            f = (tiers * 4);
        }
        if (r == 2)
        {
            s = tiers * 2 -1;
            // Skip the player that are being removed
            t = (tiers * 2) + 1;
            f = (tiers * 4)+1;

        }
        if (r == 3) {
            s = tiers * 2-1;
            // Skip player that are being removed
            t = (tiers * 2) + 1;

            f = (tiers * 4) + 2;

        }
        // Add this player to the last team
        playerRemoved[0] = Play.get(x);
        // Set it to null so that he cannot be used anymore
        Play.set(x, null);

        TextArea[] textTeams = view.getTextTeams();

        for (int i = 0; i < tiers; i++){

            // Clear the text areas
            textTeams[i].clear();
            textTeams[tiers].clear();

            // Output the players in a balanced way
            textTeams[i].appendText(String.format("%-40s%-3s",
                    Play.get(i).getName(),
                    Play.get(i).getRating()) + "\n");

            // Add the player if it is not null
            if (Play.get(s) != null) {
                textTeams[i].appendText(String.format("%-40s%-3s",
                        Play.get(s).getName(),
                        Play.get(s).getRating()) + "\n");
            }

            if (i == tiers-1) {
                // Remove the last player from the last foursome

                playerRemoved[1] = Play.get(f);
                Play.set(f, null);
                // if the remainder is 3
                // Replace the fourth player and add them to the last team

                if (r == 3) {
                    Play.set(f, Play.get(f-1));
                }
                // Replace the third plyer and add them to the last team
                if (r == 2 || r == 3) {
                    playerRemoved[2] = Play.get(t);
                    Play.set(t, null);
                    t = t+1;
                }
            }
            // Output the third player on each team
            if (Play.get(t)!= null) {
                textTeams[i].appendText(String.format("%-40s%-3s",
                        Play.get(t).getName(),
                        Play.get(t).getRating()) + "\n");
            }
            // Output the fourth player on each team
            if (Play.get(f) != null) {
                textTeams[i].appendText(String.format("%-40s%-3s",
                        Play.get(f).getName(),
                        Play.get(f).getRating()) + "\n");
            }
            double num = 0;

            // Calculate their team rating
            if (r <=3 && r >=1) {
                try {
                    // If the index is less than the second last team's index
                    if (i < tiers-1)
                    {
                        totalRating[i] = Play.get(f).getRating() +
                                Play.get(s).getRating() + Play.get(t).getRating() + Play.get(i).getRating();
                        num = totalRating[i] / 4;
                    }
                    // Output the total Rating for the second last team
                    else if (i == tiers-1 && (r <= 3))
                    {
                        // add three players to the second last team
                        totalRating[i] = Play.get(t).getRating() + Play.get(i).getRating() + Play.get(s).getRating();
                        num = totalRating [i] / 3;

                        // Add four players to the second last team
                        if (r == 3)
                        {
                            totalRating[i] = Play.get(t).getRating() +
                                    Play.get(i).getRating() + Play.get(s).getRating() + Play.get(f).getRating();

                            num = totalRating[i] /4;
                        }
                    }
                }
                // catch a NullPointerException
                catch(NullPointerException ex) {
                    System.out.println("Null Pointer Exception");
                }
            }
            // Output team rating
            textTeams[i].appendText(String.format("\n%-40s%-3s",
                    "TEAM RATING:",  Math.round(num)));

            // Decrease or increase the indexes
            s--;
            f--;
            t++;
        }
        // Return the first player on the last team
        return playerRemoved[0];
    }
    public static void main(String[] args){
        App.launch(args);
    }
}
