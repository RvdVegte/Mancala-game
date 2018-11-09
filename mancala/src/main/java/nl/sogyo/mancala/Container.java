package nl.sogyo.mancala;

public abstract class Container {
	private int stones;
	private Container nextContainer;
	private Player owner;
	
	public int getStones() {
		return stones;
	}
	
	protected void setStones(int stones) {
		if(stones >= 0) {
			this.stones = stones;
		}
	}
	
	public Player getOwner() {
		return this.owner;
	}
	
	protected void setOwner(Player owner) {
		this.owner = owner;
	}
	
	public Container getNextContainer() {
		return this.nextContainer;
	}
	
	protected void setNextContainer(Container nextContainer) {
		this.nextContainer = nextContainer;
	}

	abstract void passStones(int passedStones);
}
