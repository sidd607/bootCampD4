package scrabbleHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class scrabbleSolver {
	
	

	List<Integer> alphabetScore = new ArrayList<>(Arrays.asList(
			1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10));
	ArrayList<String> wordList;


	
	public void createWordList(HashMap<Character, Integer> conditions){
		BufferedReader br = null;
		wordList = new ArrayList<>();
		try {
			br = new BufferedReader(new FileReader(new File("src/scrabbleHelper/sowpods.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        String str;
        try {
			while((str=br.readLine())!=null) {

				if (isValid(str, conditions)){
					wordList.add(str);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean isValid(String word, HashMap<Character, Integer> conditions) {
		
		if (conditions == null)
			return true;
		
		for (Character key: conditions.keySet()){
			try {
			
				if (word.charAt(conditions.get(key)) != key)
					return false;
			} catch (Exception e) {
				return false;
			}
		}
		
		return true;
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
	
	private List<Character> modifyRack(List<Character> rack, HashMap<Character, Integer> conditions) {
		if (conditions == null){
			return rack;
		}
		
		for (Character key: conditions.keySet()){
			rack.add(key);
		}
		System.out.println(rack.size());
		return rack;
	}
	
	public String maximumScoringWord(List<Character> rack, HashMap<Character, Integer> conditions){
		createWordList(conditions);
		rack = modifyRack(rack, conditions);
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
		List<Character> rack = new ArrayList<>(Arrays.asList('A','D','E','N','M','D'));
		HashMap<Character, Integer> positions =  new HashMap<>();
		positions.put('G', 0);
		positions.put('R', 2);
		scrabbleSolver obj = new scrabbleSolver();
		//System.out.println(obj.isPossible("DOGG", rack));
		System.out.println(obj.computeScore("DOG"));
		String maxWord = obj.maximumScoringWord(rack, null);
		System.out.println(maxWord);
		System.out.println(obj.computeScore(maxWord));
		
		maxWord = obj.maximumScoringWord(rack, positions);
		System.out.println(maxWord);
		System.out.println(obj.computeScore(maxWord));
		
	}


	private void createWordListForConstraint() {
		// TODO Auto-generated method stub
		
	}


}
