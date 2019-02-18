package noko;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) throws FileNotFoundException {
		File f = new File("text.txt");
		
		Scanner s = new Scanner(f);
		
		while (s.hasNextLine())
			System.out.println(s.nextLine());
	}
	
	private void readFile(File file) throws FileNotFoundException {
		FileReader reader = new FileReader(file);
		BufferedReader br = new BufferedReader(reader);
		
		br.lines().forEach((String line) -> {
			
		});
		
	}
	
}
