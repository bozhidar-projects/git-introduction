import java.util.Scanner;

public class DontGetAngryMenu {
	
	Scanner input;
	
	public DontGetAngryMenu() {
		input = new Scanner(System.in);
	}

	int getIntegerOption(){
		int result = input.nextInt();
		input.nextLine();
		return result;
	}
	
	String getStringOption(){
		String result = input.nextLine();
		return result;
	}
	
	void show(){
		System.out.println("==================");
		System.out.println("1.Start game");
		System.out.println("2.How to play");
		System.out.println("0.Exit");
		System.out.println("==================");
	}
}
