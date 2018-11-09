package nl.sogyo.mancala;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import org.junit.Test;

public class KalahaTest {
	
	@Test
		public void checkGetContainerAtDistance() {
			Kalaha kalaha = new Kalaha();
			assertTrue(kalaha.getFirstKalaha().getContainerAtDistance(7) instanceof Kalaha);
		}
	
	@Test
		public void checkGetTotalStonesOfPlayerInCups() {
			Kalaha kalaha = new Kalaha();
			int totalStones = kalaha.getFirstKalaha().getTotalStonesOfPlayerInCups(kalaha.getFirstKalaha().getOwner());
			int totalStonesOpponent = kalaha.getFirstKalaha().getTotalStonesOfPlayerInCups(kalaha.getFirstKalaha().getOwner().getOpponent());
			assertTrue(totalStones == 24 & totalStonesOpponent == 24);
		}
	
	@Test
		public void checkEmptyAllCups() {
			Kalaha kalaha = new Kalaha();
			kalaha.getFirstKalaha().emptyAllCups();
			assertTrue(kalaha.getFirstKalaha().getStones() == 24);
			assertTrue(kalaha.getFirstKalaha().getContainerAtDistance(7).getStones() == 24);
		}
	
	@Test
		public void checkHasGameEnded() {
			Kalaha kalaha = new Kalaha();
			kalaha.getFirstKalaha().emptyAllCups();
			assertTrue(kalaha.getFirstKalaha().hasGameEnded());
		}
		
	@Test
		public void checkDetermineWinner() {
			Kalaha kalaha = new Kalaha();
			kalaha.getFirstKalaha().setStones(25); 
			assertTrue(kalaha.getFirstKalaha().determineWinner().equals("Player 1"));
			kalaha.getFirstKalaha().setStones(23); 
			assertTrue(kalaha.getFirstKalaha().determineWinner().equals("Player 2"));
			kalaha.getFirstKalaha().setStones(24); 
			assertTrue(kalaha.getFirstKalaha().determineWinner().equals("Nobody"));
		}
}
