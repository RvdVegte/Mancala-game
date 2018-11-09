package nl.sogyo.mancala;

public class Cup extends Container{
		
	public Cup(Player owner, int counter, Kalaha firstKalaha) {
		this.setStones(4);
		this.setOwner(owner);
		counter++;
		if(counter == 7) {
			this.setNextContainer(new Kalaha(this.getOwner()));
		}
		else if(counter == 14) {
			this.setNextContainer(firstKalaha);
		}
		else {
			this.setNextContainer(new Cup(this.getOwner(), counter, firstKalaha));
		}
	}
	
	int distanceCupToKalaha() {
		Container container = this;
		int counter = 0;
		while(!(container instanceof Kalaha)) {
			counter += 1;
			container = container.getNextContainer();
		}
		return counter;
	}
	
	Cup getOppositeCup() {
		int distanceToOppositeCup = 2 * distanceCupToKalaha();
		Container container = this;
		for(int x = 0; x < distanceToOppositeCup; x++) {
			container = container.getNextContainer();
		}
		Cup cup = (Cup) container;
		return cup;
	}
	
	public void giveAwayStones() {
		int passedStones = this.getStones();
		if(passedStones > 0) {
			this.setStones(0);
			getNextContainer().passStones(passedStones);
		}
	}
	
	void passStones(int passedStones) {
		this.setStones(this.getStones() + 1);
		passedStones -= 1;
		if(passedStones > 0) {
			getNextContainer().passStones(passedStones);
		}
		else if (this.getStones() == 1 & this.getOwner().hasTurn()){
			CheckForStealingStones();
		}
		else {
			this.getOwner().switchTurn();
		}
	}
	
	Kalaha getOwnersKalaha() {
		Container container = this;
		while(!(container instanceof Kalaha)){
			container = container.getNextContainer();
		}
		return (Kalaha) container;
	}
	
	void giveStonesToKalaha(int stones) {
		Kalaha kalaha = getOwnersKalaha();
		kalaha.setStones(kalaha.getStones() + stones);
		this.setStones(0);
	}
	
	void CheckForStealingStones() {
		int totalStones = 1 + getOppositeCup().getStones();
		if(totalStones > 1) {
			giveStonesToKalaha(totalStones);
			getOppositeCup().setStones(0);
		}
		this.getOwner().switchTurn();
	}	
	
	public boolean isValidMove() {
		if(this.getStones() > 0 && this.getOwner().hasTurn()) {
			return true;
			}
		return false;
	}
}

