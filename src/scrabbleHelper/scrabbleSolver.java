package scrabbleHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class scrabbleSolver {
	
	

	List<Integer> alphabetScore = new ArrayList<>(Arrays.asList(
			1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10));
	ArrayList<String> wordList = new ArrayList<String>();


	
	public void createWordList(){
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File("src/scrabbleHelper/sowpods.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        String str;
        try {
			while((str=br.readLine())!=null) {
//				if(str.length()<=7)
					wordList.add(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public Integer computeScore(String word) {
		Integer score=0;
		for(int i=0; i<word.length(); i++){
			score += alphabetScore.get(word.charAt(i)-65);
		}
		return score;
	}

	
	public boolean isPossible(String word, List<Character> rack){
		int wildCards = 0;
		int freqMap[] = new int[26];
		for(Character character: rack) {
			if (character != '*') {
				freqMap[(int)(character)-65] +=1;
			}else {
				wildCards += 1;
			}
		}
		
		for(int i=0; i<word.length(); i++) {
			if (freqMap[(int)word.charAt(i) - 65] !=0) {
				freqMap[(int)word.charAt(i) - 65]  -= 1;
			}
			else if (wildCards <= 0 )
				return false;
			else
				wildCards -= 1;
		}
		
		
		return true;
	}
	
	public String maximumScoringWord(List<Character> rack){
		createWordList();
		int maxScore =0;
		String maxScoringWord = null;
		for(String word: wordList){
			if(isPossible(word,rack)&&computeScore(word)>maxScore){
				maxScore = computeScore(word);
				maxScoringWord = word;
			}
			
		}
		return maxScoringWord;
		
	}
	
	public scrabbleSolver() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		List<Character> rack = new ArrayList<>(Arrays.asList('A','R','D','E','N','X','Y'));
		scrabbleSolver obj = new scrabbleSolver();
		//System.out.println(obj.isPossible("DOGG", rack));
		System.out.println(obj.computeScore("DOG"));
		String maxWord = obj.maximumScoringWord(rack);
		System.out.println(maxWord);
		System.out.println(obj.computeScore(maxWord));
	}	

}
