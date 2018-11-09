package nl.sogyo.mancala;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Assert;
import org.junit.Test;

public class CupTest {
 
	@Test
		public void checkDistanceCupToKalaha() {
			Kalaha kalaha = new Kalaha();
			Cup cup = (Cup) kalaha.getFirstKalaha().getContainerAtDistance(1);
			assertTrue(cup.distanceCupToKalaha() == 6);
			cup = (Cup) kalaha.getFirstKalaha().getContainerAtDistance(10);
			assertTrue(cup.distanceCupToKalaha() == 4);
		}
	
	@Test
		public void checkgetOppositeCup() {
			Kalaha kalaha = new Kalaha();
			Cup cup = (Cup) kalaha.getFirstKalaha().getContainerAtDistance(1);
			assertTrue(kalaha.getFirstKalaha().getContainerAtDistance(13).equals(cup.getOppositeCup()));
			cup = (Cup) kalaha.getFirstKalaha().getContainerAtDistance(10);
			assertTrue(kalaha.getFirstKalaha().getContainerAtDistance(4).equals(cup.getOppositeCup()));
		}
	
	@Test
		public void checkGetOwnersKalaha() {
			Kalaha kalaha = new Kalaha();
			Cup cup = (Cup)kalaha.getFirstKalaha().getContainerAtDistance(2);
			assertTrue(cup.getOwnersKalaha().equals(kalaha.getFirstKalaha().getContainerAtDistance(7)));
		}
	
	@Test
		public void isValidMove() {
		Kalaha kalaha = new Kalaha();
		Cup cup = (Cup)kalaha.getFirstKalaha().getNextContainer();
		cup.giveAwayStones();
		assertFalse(cup.isValidMove());
	}
}
