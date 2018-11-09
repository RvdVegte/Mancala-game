package nl.sogyo.game;

import java.util.Scanner;

import nl.sogyo.mancala.Container;
import nl.sogyo.mancala.Cup;
import nl.sogyo.mancala.Kalaha;
import nl.sogyo.mancala.Player;

public class Controller
{
	private Kalaha firstKalaha;
	private Player activePlayer;	
		
	public Controller() {
		this.firstKalaha = new Kalaha();
		setActivePlayer();
		printField();
	}
	
	public Kalaha getFirstKalaha() {
		return firstKalaha;
	}
	
	public Player getActivePlayer() {
		return activePlayer;
	}
	
	private void printField() {
		Container container = firstKalaha;
		System.out.println("        Player 1");
		System.out.println(" <- 6  5  4  3  2  1 <-");
		System.out.println(" __ __ __ __ __ __ __ __");
		System.out.println("|  |  |  |  |  |  |  |  |");
		System.out.print("|  ");
		printfieldHelper(-13, -7);
		System.out.print("|  |\n");
		printfieldHelper(0, 1);
		System.out.print("|-----------------");
		printfieldHelper(7, 8);
		System.out.print("|\n|  ");
		printfieldHelper(1, 7);
		System.out.print("|  |\n");
		System.out.println("|__|__|__|__|__|__|__|__|\n");	
		System.out.println(" -> 1  2  3  4  5  6 ->");
		System.out.println("        Player 2\n");
	}
	
	private void printfieldHelper(int a, int b) {
		Container container = firstKalaha;
		for(int x = a; x < b; x++) {
			container = firstKalaha.getContainerAtDistance(Math.abs(x));
			int stonesNumber = container.getStones();
			String stones = Integer.toString(stonesNumber);
			if(stonesNumber < 10) {
				stones = stones + " ";
			}
			System.out.print("|" + stones);
			container = container.getNextContainer();
		}
	}
	
	public void playGame() {
		Cup selectedCup;
		while(!isGameOver()){
			System.out.println(activePlayer.getName() + ", it is your turn.");
			selectedCup = getInputForTurn();
			selectedCup.giveAwayStones();
			printField();
			setActivePlayer();
		}
	}
	
	private void setActivePlayer() {
		if(firstKalaha.getOwner().hasTurn()) {
			activePlayer = firstKalaha.getOwner();
		}
		else {
			activePlayer = firstKalaha.getOwner().getOpponent();
		}
	}
	
	private boolean isGameOver() {
		boolean gameOver = firstKalaha.hasGameEnded();
		if(gameOver) {
			printField();
			printWinner();
		}
		return gameOver;
	}
	
	private Cup getInputForTurn() {
		Cup cup;
		do {
			cup = SelectCupFromInput(getInputNumber());
		}while(!cup.isValidMove());
		return cup;
	}

	private Cup SelectCupFromInput(int input) {
		Cup cup = (Cup)firstKalaha.getContainerAtDistance(input);
		if(!(cup.getOwner().hasTurn())) {
			cup = (Cup)firstKalaha.getContainerAtDistance(input + 7);
		}
		return cup;
	}	
	
	private int getInputNumber() {
		Scanner input = new Scanner(System.in);
		int cupNumber;
		do {
			System.out.println(activePlayer.getName() + ", choose a cup.");
			while(!input.hasNextInt()) {    
				System.out.println("Invalid input. Choose a number between 1 and 6.");
				input.next();
			}
			cupNumber = input.nextInt();
			input.nextLine();
		}while(!(cupNumber > 0 && cupNumber < 7));
		return cupNumber;
	}
	
	private void printWinner() {
		String winner = firstKalaha.determineWinner();
		System.out.println(winner + " won the game.\nFinal score:\n\tPlayer 1: " + firstKalaha.getFinalScore() + "\n\tPlayer 2: " + (48 - firstKalaha.getFinalScore()));
	}

}