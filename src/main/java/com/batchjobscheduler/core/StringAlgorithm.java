package com.batchjobscheduler.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringAlgorithm {
	
	public List<String> findAndReplacePattern(String[] words, String pattern) {
		List<String> result = new ArrayList<String>();
		Map<Character, Integer> parternPosition = new HashMap<Character, Integer>();
		Map<Character, Integer> wordPosition = new HashMap<Character, Integer>();
		for (int k = 0; k < words.length; k++) {
			String word = words[k];
			if (word.length() != pattern.length()) {
				continue;
			}
			int startIndex = 0;
			int x = pattern.charAt(startIndex) - word.charAt(startIndex);
			char start = pattern.charAt(startIndex);
			int i = 1;
			parternPosition.put(pattern.charAt(0), 0);
			wordPosition.put(word.charAt(0), 0);
			for (; i < pattern.length(); i++) {
				if (start != pattern.charAt(i)) {
					if (wordPosition.get(word.charAt(i)) != parternPosition.get(pattern.charAt(i))) {
						break;
					}
					parternPosition.put(pattern.charAt(i), i);
					wordPosition.put(word.charAt(i), i);
					start = pattern.charAt(i);
					startIndex = i;
					x = pattern.charAt(i) - word.charAt(i);
				} else {
					if ((pattern.charAt(i) - word.charAt(i)) != x) {
						break;
					}
				}
			}
			if (i == pattern.length()) {
				result.add(word);
			}
			parternPosition.clear();
			wordPosition.clear();
		}
		return result;
	}

	public static void main(String[] args) {
		StringAlgorithm d = new StringAlgorithm();
		String[] words = { "xyx", "mee" };
		System.out.println(d.findAndReplacePattern(words, "abb"));

	}
}
