package com.gtotek.kidquiz.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.gtotek.kidquiz.dao.Letter;
import com.gtotek.kidquiz.dao.QuestionEntity;

public class LetterUtil {

	private static final String[] alphabet = { "A", "B", "C", "D", "E", "F",
			"G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
			"T", "U", "V", "X", "Y" };

	public static List<Letter> generateSuggestion(QuestionEntity questionEntity) {
		List<Letter> letters = new ArrayList<Letter>();

		String dapAnKoDau = trimAll(questionEntity.getDapAnKoDau());

		int size = dapAnKoDau.length();

		for (int i = 0; i < size; ++i) {
			String letter = String.valueOf(dapAnKoDau.charAt(i));
			letters.add(new Letter(i, letter));
		}

//		Random random = new Random();
//
//		int len = alphabet.length;
//
//		letters.add(new Letter(size + 1, alphabet[random.nextInt(len)]));
//		letters.add(new Letter(size + 2, alphabet[random.nextInt(len)]));
//		letters.add(new Letter(size + 3, alphabet[random.nextInt(len)]));
//		letters.add(new Letter(size + 4, alphabet[random.nextInt(len)]));
//
//		int dapAnLen = letters.size();
//
//		int excess = 0;
//		int increment = 5;
//
//		if (dapAnLen < 14) {
//			excess = 14 - dapAnLen;
//		} else if (dapAnLen < 21) {
//			excess = 21 - dapAnLen;
//		} else if (dapAnLen < 28) {
//			excess = 28 - dapAnLen;
//		}
//		
//		excess += increment;
//
//		for (int i = increment; i < excess; i++) {
//			letters.add(new Letter(size + increment, alphabet[random
//					.nextInt(len)]));
//		}

		Collections.shuffle(letters);

		return letters;
	}
	
	public static final String trimAll(String s) {
		String string = s.replaceAll("\\s+", "");
		return string;
	}

	public static List<Letter> getEmptyAnswerDefault(
			QuestionEntity questionEntity) {
		String dapAnKoDau = questionEntity.getDapAnKoDau();

		List<Letter> letters = new ArrayList<Letter>();

		for (int i = 0; i < dapAnKoDau.length(); ++i) {
			letters.add(new Letter(""));
		}

		return letters;
	}
}
