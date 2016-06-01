import java.util.Random;

public class Dice {
	public int sides;
	Random random;
	
	public Dice(int sides) {
		this.sides=sides;
		random = new Random();
	}
	
	public int roll(Player player) {
		int squares = 0;
		System.out.println(player.name + " throwing dice..");
		Game.pause();
		squares = getRandomNumber();
		System.out.println(player.name + " throws " + squares);
		Game.pause();
		return squares;
	}
	
	public int getRandomNumber() {
		int result = random.nextInt(sides+1);
		if (result == 0) {
			result = getRandomNumber();
		}
		return result;
	}
	
	public int getSides() {
		return sides;
	}

	public void setSides(int sides) {
		this.sides = sides;
	}
}
