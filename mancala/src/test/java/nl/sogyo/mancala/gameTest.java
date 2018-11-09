package nl.sogyo.mancala;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import org.junit.Test;

public class gameTest {
	
	@Test
		public void checkFieldKalahas() {
			Kalaha kalaha = new Kalaha();
			assertTrue(kalaha.getFirstKalaha().getContainerAtDistance(14).equals(kalaha.getFirstKalaha()));
			assertTrue(kalaha.getFirstKalaha().getContainerAtDistance(7) instanceof Kalaha);	
		}
	
	@Test
		public void checkFieldCups() {
			Kalaha kalaha = new Kalaha();
			assertTrue(kalaha.getFirstKalaha().getContainerAtDistance(6) instanceof Cup);
			assertTrue(kalaha.getFirstKalaha().getContainerAtDistance(8) instanceof Cup);
			assertTrue(kalaha.getFirstKalaha().getContainerAtDistance(13) instanceof Cup);
			assertTrue(kalaha.getFirstKalaha().getContainerAtDistance(1) instanceof Cup);
		}
	
	@Test
		public void checkFieldAssignmentOfPlayers() {
			Kalaha kalaha = new Kalaha();
			Player player = kalaha.getFirstKalaha().getOwner();
			Player player2 = kalaha.getFirstKalaha().getContainerAtDistance(7).getOwner();
			assertTrue(kalaha.getFirstKalaha().getContainerAtDistance(1).getOwner().equals(player2));
			assertTrue(kalaha.getFirstKalaha().getContainerAtDistance(6).getOwner().equals(player2));
			assertTrue(kalaha.getFirstKalaha().getContainerAtDistance(7).getOwner().equals(player2));
			assertTrue(kalaha.getFirstKalaha().getContainerAtDistance(8).getOwner().equals(player));
			assertTrue(kalaha.getFirstKalaha().getContainerAtDistance(13).getOwner().equals(player));
			assertTrue(kalaha.getFirstKalaha().getContainerAtDistance(0).getOwner().equals(player));
		}
	
	 @Test
		public void checkFieldStonesInCups() {
		 	Kalaha kalaha = new Kalaha();
		 	assertTrue(kalaha.getFirstKalaha().getContainerAtDistance(1).getStones() == 4);
		 	assertTrue(kalaha.getFirstKalaha().getContainerAtDistance(8).getStones() == 4);
	 	}
	 
	@Test
		public void checkFieldStonesInKalahas() {
    		Kalaha kalaha = new Kalaha();
    		assertTrue(kalaha.getFirstKalaha().getStones() == 0);
    		assertTrue(kalaha.getFirstKalaha().getContainerAtDistance(7).getStones() == 0);
    	}
	
	@Test
		public void checkStealing() {
			Kalaha kalaha = new Kalaha();
			Cup cup = (Cup)kalaha.getFirstKalaha().getNextContainer();
			Cup oppositeCup = (Cup)kalaha.getFirstKalaha().getContainerAtDistance(13);
			cup.CheckForStealingStones();
			assertTrue(cup.getStones() == 0 & oppositeCup.getStones() == 0 && cup.getOwnersKalaha().getStones() == 5);
		}	
	
	@Test
		public void checkGiveAwayAndPassStonesThroughOwnKalaha() {
			Kalaha kalaha = new Kalaha();
			Cup cup = (Cup) kalaha.getFirstKalaha().getContainerAtDistance(4);
			cup.getOwner().switchTurn();
			cup.giveAwayStones();
			assertTrue(cup.getStones() == 0);
			assertTrue(kalaha.getFirstKalaha().getContainerAtDistance(9).getStones() == 4);
			assertTrue(kalaha.getFirstKalaha().getContainerAtDistance(8).getStones() == 5);
			assertTrue(kalaha.getFirstKalaha().getContainerAtDistance(7).getStones() == 1);
		}

	@Test
		public void checkGiveAwayAndPassStonesThroughOtherPlayersKalaha() {
			Kalaha kalaha = new Kalaha();
			Cup cup = (Cup) kalaha.getFirstKalaha().getContainerAtDistance(4);
			cup.giveAwayStones();
			assertTrue(cup.getStones() == 0);
			assertTrue(kalaha.getFirstKalaha().getContainerAtDistance(10).getStones() == 4);
			assertTrue(kalaha.getFirstKalaha().getContainerAtDistance(9).getStones() == 5);
			assertTrue(kalaha.getFirstKalaha().getContainerAtDistance(7).getStones() == 0);
		}
	
	@Test
		public void checkEndInOwnKalaha() {
			Kalaha kalaha = new Kalaha();
			kalaha.getFirstKalaha().getOwner().switchTurn();
			Cup cup = (Cup)kalaha.getFirstKalaha().getNextContainer().getNextContainer().getNextContainer();
			cup.giveAwayStones();
			assertTrue(cup.getOwner().hasTurn());
		}
	
	@Test
		public void checkNoStealingFromOwnCups() {
			Kalaha kalaha = new Kalaha();
			Cup cup = (Cup)kalaha.getFirstKalaha().getContainerAtDistance(8);
			cup.giveAwayStones();
			cup = (Cup)kalaha.getFirstKalaha().getContainerAtDistance(4);
			cup.giveAwayStones();
			cup = (Cup)kalaha.getFirstKalaha().getContainerAtDistance(6);
			assertTrue(cup.getStones() == 5);
		}
}

