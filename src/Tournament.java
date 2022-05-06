import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tournament {

    public static List<Player> players;
    private int remainder, tiers;
    private Player[] p;
    private Player[] playerRemoved;
    private List <Player> Play = new ArrayList <Player> ();
    private double [] totalRating;


    public Tournament(){
        players = new ArrayList<>();
        remainder =0;
        tiers = 0;
        playerRemoved = new Player [3];
        for (int i = 0; i < playerRemoved.length; i++) { playerRemoved[i] = new Player(); }
    }


    public void initPlayers(){
        Play.clear();

        Player.sortby(Player.SORT_BY_RATING);
        Collections.sort(players, Collections.reverseOrder());
        // Declare array of team rating integers
        totalRating = new double [16];
        tiers = players.size()/4;
        remainder = players.size() % 4;

        p = new Player[64];

        for (int i = 0; i < players.size(); i++) {
            p[i] = players.get(i);
            // Add players to the Arraylist that serves to move players when the teams are uneven
            Play.add(players.get(i));
        }
    }

    public double generateRating(){

        double num = 0;
        // total team rating
        if (remainder ==1){
            totalRating[tiers] = playerRemoved[0].getRating()+ playerRemoved[1].getRating();
            num = totalRating[tiers] /2;
        }
        else{
            totalRating[tiers] = playerRemoved[0].getRating() + playerRemoved[1].getRating()+ playerRemoved[2].getRating();
            num = totalRating[tiers] /3;
        }

        return num;
    }

    public double [] getTotalRating(){return totalRating;}
    public List<Player> getPlay(){return Play;}
    public int getTiers(){return tiers;}
    public int getRemainder(){return remainder;}
    public Player[] getPlayerRemoved(){return playerRemoved;}
    public Player[] getPlayers(){return p;}


}
