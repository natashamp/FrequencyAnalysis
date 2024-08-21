package cs166.hw1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class freqAnalysis {

	public freqAnalysis() {
	}

	
	public void freqAnalysis2(String frDescription, List<Character> encryptKey) throws FileNotFoundException {
		HashMap<Character, Integer> textMap = new HashMap<Character, Integer>();
		HashMap<Integer, List<Character>> lookupMap = new HashMap<Integer,  List<Character>>();
		HashMap<Character, Character> decryptMap = new HashMap<Character, Character>();

		FileReader frText = new FileReader(frDescription);
		Scanner scText = new Scanner(frText);

		while (scText.hasNextLine()) {
			String line = scText.nextLine();
			for (int i = 0; i < line.length(); i++) {
				char keys = line.charAt(i);
				if(keys != ' ') {
					if (textMap.containsKey(keys)) {
						
						textMap.put(keys, textMap.get(keys) + 1);
					} else {
						textMap.put(keys, 1);
					}
				}
			}
		}
		for (char i : textMap.keySet()) {
			System.out.println("letter: " + i + " freq: " + textMap.get(i));
		}

		for( Map.Entry<Character,Integer> entry : textMap.entrySet() ) {

			if( lookupMap.containsKey(entry.getValue())) {
				lookupMap.get(entry.getValue()).add(entry.getKey());
			}else {
				lookupMap.put(entry.getValue(), new ArrayList<Character>(List.of(entry.getKey())));
			}
			
		}
		
		ArrayList<Integer> freqList = new ArrayList<>(lookupMap.keySet());
		Collections.sort(freqList);
		Collections.reverse(freqList);
		int count =0;
		for (int num : freqList) {
				for(char i : lookupMap.get(num)) {
					decryptMap.put(i, encryptKey.get(count));
					count++;
				}
			}
           
        
		System.out.println("\nDecrypt Map: ");
		for (char i : decryptMap.keySet()) {
			System.out.println(" " + i + " => " + decryptMap.get(i));
		}
		
		System.out.print("\n\n\n");
		
		FileReader frDecrypt = new FileReader(frDescription);
		Scanner scDecrypt = new Scanner(frDecrypt);
		while (scDecrypt.hasNextLine()) {
			String lineDecrypt = scDecrypt.nextLine();
			for (int i = 0; i < lineDecrypt.length(); i++) {
				char key = lineDecrypt.charAt(i);
				if (key != ' ') {
					System.out.print(decryptMap.get(key));
				}else {
					System.out.print(" ");
				}
			}
			System.out.print("\n");
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		// cipher text 1 : "ciphertext-o1.txt"
		// cipher text 2 : "ciphertext-o2.txt"
		
		List<Character> encryptKey = new ArrayList<>(List.of(
				'E','T','A','O','I','N','S','R','H','D','L','U','C','M','F','Y','W','G','P','B','V','K','X','Q','J','Z'
						));
		List<Character> encryptKey1 = new ArrayList<>(List.of(
				'E','T','A','O','N','S','I','R','H','L','D','C','M','U','W','F','B','G','P','Y','V','K','Q','X','J','Z'
						));
		List<Character> encryptKey2 = new ArrayList<>(List.of(
				'E','T','A','R','O','I','N','S','H','D','L','G','M','F','U','C','W','B','P','Y','V','K','Z','Q','J','X'
						));
		freqAnalysis freqAnalysis = new freqAnalysis();
		
		freqAnalysis.freqAnalysis2("ciphertext-o1.txt", encryptKey1);
		
		System.out.println("\n");
		
	//	freqAnalysis.freqAnalysis2("ciphertext-o2.txt", encryptKey2);
	}
}
