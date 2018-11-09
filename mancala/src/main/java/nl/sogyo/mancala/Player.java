package nl.sogyo.mancala;


public class Player {
	private boolean hasTurn;
	private Player opponent;
	private String name;
	
	public Player(boolean hasTurn){
		this.hasTurn = hasTurn;	
		if(hasTurn) {
			this.setName("Player 1");
			Player player = new Player(false);
			this.opponent = player;
			player.opponent = this;
			this.opponent.setName("Player 2");
		}
	}
	
	void setOpponent(Player opponent) {
		this.opponent = opponent;
	}
	
	public Player getOpponent() {
		return opponent;
	}
	
	public String getName() {
		return this.name;
	}
	
	void setName(String name) {
		this.name = name;
	}
	
	public boolean hasTurn() {
		return this.hasTurn;
	}
	
	void switchTurn() {
		this.hasTurn = !this.hasTurn;
		opponent.hasTurn = !opponent.hasTurn;
	}
	
}
