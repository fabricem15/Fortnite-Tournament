public class Player implements Comparable <Player> {


	private String last, first, fullName, tier, nameDisplayed;
	private int rating;
	public static int SORT_BY_NAME;
	public static int SORT_BY_RATING;
	public static int sortValue;

	/**
	 * This no-arg constructor provides the user with initial values for their class variables.
	 * 
	 */
	public Player() {
		last = "Scott";
		first = "Michael";
		fullName = "Michael Scott";
		rating = 0;
		tier = "scout";
		
		SORT_BY_NAME = 0;
		SORT_BY_RATING = 1;

	}
	/**
	 * This constructor allows the user to specify the first name, last name, rating and tier.
	 * @param fname - String
	 * @param lName - String
	 * @param rate  - int
	 * @param tier  - String
	 */
	public Player(String fname, String lName, int rate, String tier){

		last = lName;
		first = fname;
		rating = rate;
		
		nameDisplayed = last + ", " + first;
				
		this.tier = tier;

		fullName = first + " " + last;
		SORT_BY_NAME = 0;
		SORT_BY_RATING = 1;

	}
	/**
	 * This method sets the player's full name to the specified String.
	 * @param fName - String
	 */
	public void setName(String fName){

		fullName = fName;
	}
	/**
	 * This method sets the player's first name to the specified String.
	 * @param firstName - String
	 */
	public void setFirstName(String firstName){
		
		first = firstName;
	}
	public void setLastName(String lName){
		last = lName;
	}
	public void setRating(int rat){
		rating = rat;
	}
	/**
	 * This method returns the first and last name of the player separated by a space character.
	 * @return fullName - String
	 */
	public String getName(){
		return fullName;
	}
	/**
	 * This method returns the player's first name.
	 * @return first - String
	 */
	public String getFirstName(){
		return first;
	}
	/**
	 * This method returns the player's last name.
	 * @return last - String
	 */
	public String getLastName(){
		return last;
	}
	/**
	 * This method returns the player's last name followed by a comma and their first name.
	 * @return nameDisplayed - String
	 */
	public String getNameDisplayed () {
		
		return nameDisplayed;
	}
	/**
	 * This method returns the player's rating.
	 * @return rating - int
	 */
	public int getRating(){
		return rating;
	}
	/**
	 * This method returns the player's tier.
	 * @return tier - int
	 */
	public String getTier (){
		return tier;
	}
	/**
	 * This method allows the user to choose one of two ways to sort their list of players. 
	 * @param sortBy - int
	 */
	public static void sortby (int sortBy) {
		
		sortValue = sortBy;
	}
	/**
	 * 
	 * This method implemented by the comparable class allows the user to sort their players. It is where the comparison takes place.
	 */
	public int compareTo(Player p) {
		int comparison = 0;

		if (sortValue == SORT_BY_NAME){
			comparison = nameDisplayed.compareTo(p.getNameDisplayed());
		}
		else if (sortValue == SORT_BY_RATING){
			comparison = Integer.valueOf(rating).compareTo(p.getRating());
		}
		 
		return comparison;
	}
	
	/**
	 * This method returns the player class to string.
	 * @return 
	 */
	public String toString() {
		
		return String.format("%-30s%-10s%-10s",nameDisplayed, rating, tier);
	}

					
}
