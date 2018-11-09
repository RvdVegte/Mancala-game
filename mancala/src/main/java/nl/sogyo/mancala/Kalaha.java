package nl.sogyo.mancala;

public class Kalaha extends Container{
	private static Kalaha firstKalaha;
	private int finalScore;
	
	public Kalaha() {
		firstKalaha = this;
		Player player = new Player(true);
		this.setStones(0);
		this.setOwner(player);
		this.setNextContainer(new Cup(this.getOwner().getOpponent(), 1, firstKalaha));
	}
	
	public Kalaha(Player player) {
		this.setStones(0);
		this.setOwner(player);
		this.setNextContainer(new Cup(this.getOwner().getOpponent(), 8, firstKalaha));
	}
	
	Kalaha getFirstKalaha() {
		return firstKalaha;
	}
	
	public int getFinalScore() {
		return finalScore;
	}
	
	void setFinalScore(int finalScore) {
		this.finalScore = finalScore;
	}
	
	public Container getContainerAtDistance (int steps) {
		Container container = this;
		for(int x = 0; x < steps; x++) {
			container = container.getNextContainer();
		}
		return container;
	}

	private void addStones(int stones) {
		this.setStones(this.getStones() + stones);
	}
	
	void passStones(int passedStones) {
		if(this.getOwner().hasTurn()) {
			this.addStones(1);
			passedStones -= 1;
		}
		if(passedStones > 0) {
			getNextContainer().passStones(passedStones);
		}		
	}
	
	public String determineWinner() {
		this.setFinalScore(this.getStones());
		Kalaha kalahaOpponent = (Kalaha)getContainerAtDistance(7);
		kalahaOpponent.setFinalScore(48 - this.getFinalScore());
		if(this.getFinalScore() > kalahaOpponent.getFinalScore()) {
			return this.getOwner().getName();
		}
		else if(this.getFinalScore() < kalahaOpponent.getFinalScore()) {
			return this.getOwner().getOpponent().getName();
		}
		else {
			return "Nobody";
		}
	}
	
	int getTotalStonesOfPlayerInCups(Player player) {
		int totalStones = 0;
		Container container = this.getNextContainer();
		while(!(container.equals(this))) {
			if(container instanceof Cup && container.getOwner().equals(player)) {
				totalStones += container.getStones();
			}
			container = container.getNextContainer();
		}
		return totalStones;
	}
	
	public boolean hasGameEnded() {
		int totalStonesPlayer = getTotalStonesOfPlayerInCups(this.getOwner());
		int totalStonesOpponent = getTotalStonesOfPlayerInCups(this.getOwner().getOpponent());
		if((this.getOwner().hasTurn() && totalStonesPlayer == 0) || (this.getOwner().getOpponent().hasTurn() && totalStonesOpponent == 0)){
			emptyAllCups();
			return true;
		}
		return false;
	}
	
	void emptyAllCups() {
		Container container = this.getNextContainer();
		while(!container.equals(this)) {
			if(container instanceof Cup) {
				Cup cup = (Cup) container;
				cup.giveStonesToKalaha(cup.getStones());
			}
			container = container.getNextContainer();
		}
	}
}
