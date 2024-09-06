import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class TicTac {
	char[] boardArray = new char[9];
	Random random = new Random();
	boolean starter = random.nextBoolean();
	Scanner scanner = new Scanner(System.in);
	int inputIndex;
	int turnsCounter;

	public TicTac() {
		// TODO Auto-generated constructor stub

		printGame();
		theGame();

	}

	public void theGame() {
		try {
			inputIndex = scanner.nextInt();
			if (inputIndex > 0 && inputIndex < 10 && isPlayed() == false) {
				if (starter == true) {
					boardArray[inputIndex - 1] = 'o';
					printGame();
					starter = false;
					turnsCounter++;

				} else if (starter == false) {
					boardArray[inputIndex - 1] = 'x';
					printGame();
					starter = true;
					turnsCounter++;

				}
				if (isWinning() == true) {
					System.out.println("Yippy!!");
				} else if (turnsCounter == 9) {
					System.out.println("Draw!");
				} else {
					theGame();
				}

			} else if (inputIndex > 0 && inputIndex < 10 && isPlayed() == true) {
				System.out.println("No cheating!! you lose.");
			} else {
				System.out.println("Please enter numbers between 1-9.");
				theGame();
			}
		} catch (InputMismatchException e) {
			// TODO: handle exception
			System.out.println("Input DeeDee kub, no more game laew.");
		}
	}

	public boolean isWinning() {
		if (boardArray[0] == 'x' && boardArray[1] == 'x' && boardArray[2] == 'x'
				|| boardArray[3] == 'x' && boardArray[4] == 'x' && boardArray[5] == 'x'
				|| boardArray[6] == 'x' && boardArray[7] == 'x' && boardArray[8] == 'x'
				|| boardArray[0] == 'x' && boardArray[3] == 'x' && boardArray[6] == 'x'
				|| boardArray[1] == 'x' && boardArray[4] == 'x' && boardArray[7] == 'x'
				|| boardArray[2] == 'x' && boardArray[5] == 'x' && boardArray[8] == 'x'
				|| boardArray[0] == 'x' && boardArray[4] == 'x' && boardArray[8] == 'x'
				|| boardArray[2] == 'x' && boardArray[4] == 'x' && boardArray[6] == 'x') {
			return true;

		} else if (boardArray[0] == 'o' && boardArray[1] == 'o' && boardArray[2] == 'o'
				|| boardArray[3] == 'o' && boardArray[4] == 'o' && boardArray[5] == 'o'
				|| boardArray[6] == 'o' && boardArray[7] == 'o' && boardArray[8] == 'o'
				|| boardArray[0] == 'o' && boardArray[3] == 'o' && boardArray[6] == 'o'
				|| boardArray[1] == 'o' && boardArray[4] == 'o' && boardArray[7] == 'o'
				|| boardArray[2] == 'o' && boardArray[5] == 'o' && boardArray[8] == 'o'
				|| boardArray[0] == 'o' && boardArray[4] == 'o' && boardArray[8] == 'o'
				|| boardArray[2] == 'o' && boardArray[4] == 'o' && boardArray[6] == 'o') {
			return true;
		} else {
			return false;
		}
	}

	public boolean isPlayed() {
		if (boardArray[inputIndex - 1] == 'x' || boardArray[inputIndex - 1] == 'o') {
			return true;
		} else {
			return false;
		}
	}

	public void printBoard() {
		int indexCounter = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(boardArray[indexCounter]);
				indexCounter++;

			}
			System.out.println();
		}
	}

	public void printTurn() {
		if (starter == true) {
			System.out.println("<--");
			starter = false;
		} else {
			System.out.println("-->");
		}
	}

	public void printGame() {
		printTurn();
		printBoard();

	}

	public static void main(String[] args) {
		TicTac tacking = new TicTac();

	}
}