package com.spaghettisoft.component.hangman;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Player ani = new Player("Ani");
		System.out.println(ani.guess());
		
		char[] life = ani.getLifes();
		
		System.out.println(Arrays.toString(life));
		
		ani.munisLife();
		ani.munisLife();
		ani.munisLife();
		System.out.println(ani + "\n" + Arrays.toString(life));
//		File f = new File("dict.txt.txt");
//		System.out.println(f.exists()+ " get absolute path : "+ f.getAbsoluteFile());
		WordToGuess word = new WordToGuess();
		System.out.println(Arrays.toString(WordToGuess.AVAILABLE_WORDS));
		System.out.println(word.getRandomWord());
		System.out.println(word.hideWord());
		Random r = new Random();
		int a = r.nextInt(2) + 1;
		System.out.println(a);

	}

}
