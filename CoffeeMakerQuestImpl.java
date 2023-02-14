package edu.pitt.cs;
import java.util.*;

public class CoffeeMakerQuestImpl implements CoffeeMakerQuest {

	// TODO: Add more member variables and methods as needed.
	private Room first;

	private Room sixth;
	private Player player;
	private ArrayList<Room> rooms;
	private Room curRoom = null;
	private boolean gameOver = false;


	/**
	 * Constructor. Rooms are laid out from south to north, such that the
	 * first room in rooms becomes the southernmost room and the last room becomes
	 * the northernmost room. Initially, the player is at the southernmost room.
	 * 
	 * @param player Player for this game
	 * @param rooms  List of rooms in this game
	 */
	CoffeeMakerQuestImpl(Player player, ArrayList<Room> rooms) {
		this.player = player;
		this.rooms = rooms;
		this.first = rooms.get(0);
		this.sixth = rooms.get(5);
		this.curRoom = rooms.get(0);
	}


	/**
	 * Whether the game is over. The game ends when the player drinks the coffee.
	 * 
	 * @return true if the game is over, false otherwise
	 */
	public boolean isGameOver() {
		return gameOver;

	}

	/**
	 * The method returns success if and only if: 1) The southernmost room has a
	 * north door only, 2) The northernmost room has a south door only, and 3) The
	 * rooms in the middle have both north and south doors. If there is only one
	 * room, there should be no doors.
	 * 
	 * @return true if check successful, false otherwise
	 */
	public boolean areDoorsPlacedCorrectly() {
		boolean ret = false;

		//System.out.println(first.getNorthDoor() != null && first.getSouthDoor() == null);
		//System.out.println(sixth.getNorthDoor() == null && sixth.getSouthDoor() != null);
		//System.out.println(areDoorsPlacedCorrectlyHelp());
		if(first.getNorthDoor() != null && first.getSouthDoor() == null){
			if(sixth.getNorthDoor() == null && sixth.getSouthDoor() != null){
				if(areDoorsPlacedCorrectlyHelp()){
					ret = true;
				}
			}
		}
		return ret;
	}
	/**
	 * The method loops through the middle doors of the arraylist of doors
	 * and checks to see if both doors exist. If not return false
	 * 
	 * @return true if the middle doors have both North and South Doors, false otherwise
	 */
	private boolean areDoorsPlacedCorrectlyHelp() {
		boolean ret = true;
		for(int i = 1;i<5;i++ ){
			if((rooms.get(i).getNorthDoor() == null) || (rooms.get(i).getSouthDoor() == null) ){
				ret = false;
				break;
			}
		}
		return ret;
	}


	/**
	 * Checks whether each room has a unique adjective and furnishing.
	 * 
	 * @return true if check successful, false otherwise
	 */

	public boolean areRoomsUnique() {
		ArrayList<String> adjs = new ArrayList<String>();
		ArrayList<String> furnishings = new ArrayList<String>();
		boolean ret = true;
		for(int i = 0; i<rooms.size();i++)
		{
			if(rooms.get(i).getAdjective() == null || rooms.get(i).getFurnishing() == null){
				ret = false;
				break;
			}

			 if(((adjs.contains(rooms.get(i).getAdjective()))) || ((furnishings.contains(rooms.get(i).getFurnishing())))){
			 	ret = false;
			 	break;
			 }
			
			adjs.add(rooms.get(i).getAdjective());
			furnishings.add(rooms.get(i).getFurnishing());
		}
		return ret;
	}

	/**
	 * Returns the room the player is currently in. If location of player has not
	 * yet been initialized with setCurrentRoom, returns null.
	 * 
	 * @return room player is in, or null if not yet initialized
	 */
	public Room getCurrentRoom() {
		return curRoom;
	}

	/**
	 * Set the current location of the player. If room does not exist in the game,
	 * then the location of the player does not change and false is returned.
	 * 
	 * @param room the room to set as the player location
	 * @return true if successful, false otherwise
	 */
	public boolean setCurrentRoom(Room room) {
		if(rooms.contains(room))
		{
			curRoom = room;
			return true;
		}
		else
			return false;
	}

	/**
	 * Get the instructions string command prompt. It returns the following prompt:
	 * " INSTRUCTIONS (N,S,L,I,D,H) > ".
	 * 
	 * @return comamnd prompt string
	 */
	public String getInstructionsString() {
		return " INSTRUCTIONS (N,S,L,I,D,H) > ";
	}

	/**
	 * Processes the user command given in String cmd and returns the response
	 * string. For the list of commands, please see the Coffee Maker Quest
	 * requirements documentation (note that commands can be both upper-case and
	 * lower-case). For the response strings, observe the response strings printed
	 * by coffeemaker.jar. The "N" and "S" commands potentially change the location
	 * of the player. The "L" command potentially adds an item to the player
	 * inventory. The "D" command drinks the coffee and ends the game. Make
	 * sure you use Player.getInventoryString() whenever you need to display
	 * the inventory.
	 * 
	 * @param cmd the user command
	 * @return response string for the command
	 */
	public String processCommand(String cmd) {
		String retS = "";
		cmd = cmd.toUpperCase();
		if(cmd.equals("N"))
		{
			int index = rooms.indexOf(getCurrentRoom());
			if((index+1)>5){
				retS = "A door in that direction does not exist.\n";
			}
			else{
				setCurrentRoom(rooms.get(index + 1));
			}
		}
		else if(cmd.equals("S"))
		{
			int index = rooms.indexOf(getCurrentRoom());
			if((index-1)<0){
				retS = "A door in that direction does not exist.\n";
			}
			else{
				setCurrentRoom(rooms.get(index - 1));
			}

		}
		else if(cmd.equals("L"))
		{
			if(getCurrentRoom().getItem() == Item.NONE){
				retS = "You don't see anything out of the ordinary.\n";
			}
			else{
				if(getCurrentRoom().getItem() == Item.SUGAR){
					retS = "There might be something here...\nYou found some sweet sugar!\n";
				}
				else if(getCurrentRoom().getItem() == Item.CREAM){
					retS = "There might be something here...\nYou found some creamy cream!\n";
				}
				else if(getCurrentRoom().getItem() == Item.COFFEE){
					retS = "There might be something here...\nYou found some caffeinated coffee!\n";
				}
				player.addItem(getCurrentRoom().getItem());
			}

		}
		else if(cmd.equals("I")){
			retS = player.getInventoryString();
		}
		else if(cmd.equals("D")){
			retS = player.getInventoryString();

			if((player.checkSugar()) && (player.checkCoffee()) && (player.checkCream())){
				retS += "\nYou drink the beverage and are ready to study!\nYou win!\n";
			}
			else if((!player.checkSugar()) && (!player.checkCoffee()) && (!player.checkCream())){
				retS += "\nYou drink thin air and can only dream of coffee. You cannot study.\nYou lose!\n";
			}//end
			else{
				retS += "\nYou refuse to drink this half-made sludge. You cannot study.\nYou lose!\n";
			}//end if

			gameOver = true;

		}
		else if(cmd.equals("H")){
			retS = "N - Go north\nS - Go south\nL - Look and collect any items in the room\nI - Show inventory of items collected\nD - Drink coffee made from items in inventory\n";
		}
		else{
			retS = "What?\n";
		}

		return retS;
	}

}
