package edu.pitt.cs;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.*;
import org.mockito.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

public class CoffeeMakerQuestTest {

	CoffeeMakerQuest cmq;
	Player player;
	ArrayList<Room> rooms;

	@Before
	public void setup() {
		// 0. Turn on bug injection for Player and Room.
		//Config.setBuggyPlayer(true);
		//Config.setBuggyRoom(true);

		// TODO: 1. Create a Player with no items (no coffee, no cream, no sugar)
		// and assign to player.
		// player =  Mockito.mock(Player.class);
		// Mockito.when(player.checkCoffee()).thenReturn(false);
		// Mockito.when(player.checkSugar()).thenReturn(false);
		// Mockito.when(player.checkCream()).thenReturn(false);
		player = Mockito.mock(Player.class);

		// TODO: 2. Create 6 rooms as specified in rooms.config and add to rooms list.
		Room roomOne = Mockito.mock(Room.class);
		Mockito.when(roomOne.getFurnishing()).thenReturn("Quaint sofa");
		Mockito.when(roomOne.getAdjective()).thenReturn("Small");
		Mockito.when(roomOne.getItem()).thenReturn(Item.CREAM);
		Mockito.when(roomOne.getNorthDoor()).thenReturn("Magenta");


		Room roomTwo = Mockito.mock(Room.class);
		Mockito.when(roomTwo.getFurnishing()).thenReturn("Sad record player");
		Mockito.when(roomTwo.getAdjective()).thenReturn("Funny");
		Mockito.when(roomTwo.getItem()).thenReturn(Item.NONE);
		Mockito.when(roomTwo.getNorthDoor()).thenReturn("Beige");
		Mockito.when(roomTwo.getSouthDoor()).thenReturn("Massive");


		Room roomThree = Mockito.mock(Room.class);
		Mockito.when(roomThree.getFurnishing()).thenReturn("Tight pizza");
		Mockito.when(roomThree.getAdjective()).thenReturn("Refinanced");
		Mockito.when(roomThree.getItem()).thenReturn(Item.COFFEE);
		Mockito.when(roomThree.getNorthDoor()).thenReturn("Dead");
		Mockito.when(roomThree.getSouthDoor()).thenReturn("Smart");


		Room roomFour = Mockito.mock(Room.class);
		Mockito.when(roomFour.getFurnishing()).thenReturn("Flat energy drink");
		Mockito.when(roomFour.getAdjective()).thenReturn("Dumb");
		Mockito.when(roomFour.getItem()).thenReturn(Item.NONE);
		Mockito.when(roomFour.getNorthDoor()).thenReturn("Vivacious");
		Mockito.when(roomFour.getSouthDoor()).thenReturn("Slim");


		Room roomFive = Mockito.mock(Room.class);
		Mockito.when(roomFive.getFurnishing()).thenReturn("Beautiful bag of money");
		Mockito.when(roomFive.getAdjective()).thenReturn("Bloodthirsty");
		Mockito.when(roomFive.getItem()).thenReturn(Item.NONE);
		Mockito.when(roomFive.getNorthDoor()).thenReturn("Purple");
		Mockito.when(roomFive.getSouthDoor()).thenReturn("Sandy");


		Room roomSix = Mockito.mock(Room.class);
		Mockito.when(roomSix.getFurnishing()).thenReturn("Perfect air hockey table");
		Mockito.when(roomSix.getAdjective()).thenReturn("Rough");
		Mockito.when(roomSix.getItem()).thenReturn(Item.SUGAR);
		Mockito.when(roomSix.getSouthDoor()).thenReturn("Minimalist");


		rooms = new ArrayList<Room>();
		rooms.add(roomOne);
		rooms.add(roomTwo);
		rooms.add(roomThree);
		rooms.add(roomFour);
		rooms.add(roomFive);
		rooms.add(roomSix);






		// 3. Create Coffee Maker Quest game using player and rooms, and assign to cmq.
		cmq = CoffeeMakerQuest.createInstance(player, rooms);
	}

	@After
	public void tearDown() {
		cmq = null;
		player = null;
		rooms = null;
	}

	/**
	 * Test case for String getInstructionsString().
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 * Execution steps: Call cmq.getInstructionsString().
	 * Postconditions: Return value is " INSTRUCTIONS (N,S,L,I,D,H) > ".
	 * </pre>
	 */
	@Test
	public void testGetInstructionsString() {
		String retValue = cmq.getInstructionsString();
		assertEquals("Reuturn value of getInstructionsString was wrong", " INSTRUCTIONS (N,S,L,I,D,H) > ",retValue);

	}

	/**
	 * Test case for Room getCurrentRoom().
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 * Execution steps: Call cmq.getCurrentRoom().
	 * Postconditions: Return value is rooms.get(0).
	 * </pre>
	 */
	@Test
	public void testGetCurrentRoom() {
		assertEquals("Return Value of cmq.getCurrentRoom() isn't rooms.get(0)", rooms.get(0), cmq.getCurrentRoom());
	}

	/**
	 * Test case for void setCurrentRoom(Room room) and Room getCurrentRoom().
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 * Execution steps: Call cmq.setCurrentRoom(rooms.get(2)).
	 *                  Call cmq.getCurrentRoom().
	 * Postconditions: Return value of cmq.setCurrentRoom(rooms.get(2)) is true. 
	 *                 Return value of cmq.getCurrentRoom() is rooms.get(2).
	 * </pre>
	 */
	@Test
	public void testSetCurrentRoom() {
		// TODO
		boolean bool = cmq.setCurrentRoom(rooms.get(2));
		Room test = cmq.getCurrentRoom();

		assertTrue("setCurrentRoom(rooms.get(2)) was false",bool);
		assertEquals("cmq.getCurrentRoom() and rooms.get(2) wern't equal", rooms.get(2),test );
	}

	/**
	 * Test case for boolean areDoorsPlacedCorrectly() when check succeeds.
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 * Execution steps: Call cmq.areDoorsPlacedCorrectly().
	 * Postconditions: Return value of cmq.areDoorsPlacedCorrectly() is true.
	 * </pre>
	 */
	@Test
	public void testAreDoorsPlacedCorrectly() {
		boolean bool = cmq.areDoorsPlacedCorrectly();
		assertTrue("Return value for areDoorsPlacedCorrectly() was true",bool);
	}

	/**
	 * Test case for boolean areDoorsPlacedCorrectly() when check fails.
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 *                rooms.get(3) is modified so that it has no south door.
	 * Execution steps: Call cmq.areDoorsPlacedCorrectly().
	 * Postconditions: Return value of cmq.areDoorsPlacedCorrectly() is false.
	 * </pre>
	 */
	@Test
	public void testAreDoorsPlacedCorrectlyMissingSouthDoor() {
		Room testRoom = Mockito.mock(Room.class);
		Mockito.when(testRoom.getFurnishing()).thenReturn("Flat energy drink");
		Mockito.when(testRoom.getAdjective()).thenReturn("Dumb");
		Mockito.when(testRoom.getItem()).thenReturn(Item.NONE);
		Mockito.when(testRoom.getNorthDoor()).thenReturn("Vivacious");
		rooms.set(3, testRoom);

		boolean bool = cmq.areDoorsPlacedCorrectly();
		assertFalse("Return value for areDoorsPlacedCorrectly() was true ", bool);
	}

	/**
	 * Test case for boolean areRoomsUnique() when check fails.
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 *                rooms.get(2) is modified so that its adjective is modified to "Small".
	 * Execution steps: Call cmq.areRoomsUnique().
	 * Postconditions: Return value of cmq.areRoomsUnique() is false.
	 * </pre>
	 */
	@Test
	public void testAreRoomsUniqueDuplicateAdjective() {
		Room testRoom = Mockito.mock(Room.class);
		Mockito.when(testRoom.getFurnishing()).thenReturn("Tight pizza");
		Mockito.when(testRoom.getAdjective()).thenReturn("Small");
		Mockito.when(testRoom.getItem()).thenReturn(Item.COFFEE);
		Mockito.when(testRoom.getNorthDoor()).thenReturn("Dead");
		Mockito.when(testRoom.getSouthDoor()).thenReturn("Smart");
		rooms.set(2, testRoom);

		boolean bool = cmq.areRoomsUnique();

		assertFalse("The cmq.areRoomsUnique() call was true", bool);



		
	}

	/**
	 * Test case for String processCommand("I").
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 *                Player has no items.
	 * Execution steps: Call cmq.processCommand("I").
	 * Postconditions: Return value is "YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n".
	 * </pre>
	 */
	@Test
	public void testProcessCommandI() {
		Mockito.when(player.getInventoryString()).thenReturn("YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n");

		String ret = cmq.processCommand("I");
		assertEquals("Return from processing 'I' was incorrect\n", "YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n", ret);
	}

	/**
	 * Test case for String processCommand("l").
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 * Execution steps: Call cmq.processCommand("l").
	 * Postconditions: Return value is "There might be something here...\nYou found some creamy cream!\n".
	 *                 Item.CREAM has been added to the Player.
	 * </pre>
	 */
	@Test
	public void testProcessCommandLCream() {
		//Mockito.when(player.checkCream()).thenReturn(true);

		String retVal = cmq.processCommand("l");
		if(retVal == "There might be something here...\nYou found some creamy cream!\n"){
			Mockito.when(player.checkCream()).thenReturn(true);
		}//end if 
		assertEquals("The return value of processing the command 'l' was wrong","There might be something here...\nYou found some creamy cream!\n", retVal);	
		assertTrue("The player doesn't have cream", player.checkCream());
	}

	/**
	 * Test case for String processCommand("n").
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 *                cmq.setCurrentRoom(rooms.get(3)) has been called.
	 * Execution steps: Call cmq.processCommand("n").
	 *                  Call cmq.getCurrentRoom().
	 * Postconditions: Return value of cmq.processCommand("n") is "".
	 *                 Return value of cmq.getCurrentRoom() is rooms.get(4).
	 * </pre>
	 */
	@Test
	public void testProcessCommandN() {
		cmq.setCurrentRoom(rooms.get(3));
		String empty = cmq.processCommand("n");
		Room retRoom = cmq.getCurrentRoom();

		assertEquals("Return value of  cmq.processCommand('n') isn't empty","",empty );
		assertEquals("Return value of cmq.getCurrentRoom() isn't rooms.get(4)", rooms.get(4), retRoom );

		
	}

	/**
	 * Test case for String processCommand("s").
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 * Execution steps: Call cmq.processCommand("s").
	 *                  Call cmq.getCurrentRoom().
	 * Postconditions: Return value of cmq.processCommand("s") is "A door in that direction does not exist.\n".
	 *                 Return value of cmq.getCurrentRoom() is rooms.get(0).
	 * </pre>
	 */
	@Test
	public void testProcessCommandS() {
		String retString = cmq.processCommand("s");
		Room retRoom = cmq.getCurrentRoom();

		assertEquals("Return value of cmq.processCommand('s') isn't correct","A door in that direction does not exist.\n", retString);
		assertEquals("Return value of cmq.getCurrentRoom() isn't rooms.get(0)",rooms.get(0), retRoom );
	}

	/**
	 * Test case for String processCommand("D").
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 * Execution steps: Call cmq.processCommand("D").
	 *                  Call cmq.isGameOver().
	 * Postconditions: Return value of cmq.processCommand("D") is "YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n\nYou drink thin air and can only dream of coffee. You cannot study.\nYou lose!\n".
	 *                 Return value of cmq.isGameOver() is true.
	 * </pre>
	 */
	@Test
	public void testProcessCommandDLose() {
		Mockito.when(player.checkCoffee()).thenReturn(false);
		Mockito.when(player.checkCream()).thenReturn(false);
		Mockito.when(player.checkSugar()).thenReturn(false);
		Mockito.when(player.getInventoryString()).thenReturn("YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n");

		String retString = cmq.processCommand("D");
		boolean retBoolean = cmq.isGameOver();
		

		assertEquals("Return value of cmq.processCommand('D') is wrong","YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n\nYou drink thin air and can only dream of coffee. You cannot study.\nYou lose!\n", retString);
		assertTrue("Return value of cmq.isGameOver() is false.", retBoolean);


	}

	/**
	 * Test case for String processCommand("D").
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 *                Player has all 3 items (coffee, cream, sugar).
	 * Execution steps: Call cmq.processCommand("D").
	 *                  Call cmq.isGameOver().
	 * Postconditions: Return value of cmq.processCommand("D") is "You have a cup of delicious coffee.\nYou have some fresh cream.\nYou have some tasty sugar.\n\nYou drink the beverage and are ready to study!\nYou win!\n".
	 *                 Return value of cmq.isGameOver() is true.
	 * </pre>
	 */
	@Test
	public void testProcessCommandDWin() {
		Mockito.when(player.checkCoffee()).thenReturn(true);
		Mockito.when(player.checkCream()).thenReturn(true);
		Mockito.when(player.checkSugar()).thenReturn(true);
		Mockito.when(player.getInventoryString()).thenReturn("You have a cup of delicious coffee.\nYou have some fresh cream.\nYou have some tasty sugar.\n");

		String retString = cmq.processCommand("D");
		Boolean retBoolean = cmq.isGameOver();

		assertEquals("Return value of cmq.processCommand('D') is wrong","You have a cup of delicious coffee.\nYou have some fresh cream.\nYou have some tasty sugar.\n\nYou drink the beverage and are ready to study!\nYou win!\n", retString);
		assertTrue("Return value of cmq.isGameOver() is false.", retBoolean);

	}

	// TODO: Put in more unit tests of your own making to improve coverage!

}
