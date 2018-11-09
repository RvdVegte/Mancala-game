package nl.sogyo.mancala;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class PlayerTest {

	@Test
		public void CheckSwitchTurn() {
			Player player = new Player(true);
			assertTrue(player.hasTurn());
			assertFalse(player.getOpponent().hasTurn());
			player.switchTurn();
			assertFalse(player.hasTurn());
			assertTrue(player.getOpponent().hasTurn());
		}
}