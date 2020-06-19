package com.commonalgorithm.core.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StringDictionaryAlgorithm {

	public static int wordSubsequence(String s, String[] wordDict) {
		Set<String> result = new HashSet<String>();
		Map<String, Integer> duplicate = new HashMap();
		Map<String, Integer> positions = new HashMap();
		for (String word : wordDict) {
			Integer count = duplicate.get(word);
			if (count == null) {
				count = 0;
			}
			duplicate.put(word, ++count);
			positions.put(word, 0);
		}
		for (int i = 0; i < s.length(); i++) {
			for (String word : wordDict) {
				if (result.contains(word)) {
					continue;
				}
				Integer position = positions.get(word);
				if (position == word.length()) {
					result.add(word);
				} else {
					if (s.charAt(i) == word.charAt(position)) {
						positions.put(word, position + 1);
					}
				}
			}
		}
		for (String word : positions.keySet()) {
			if (positions.get(word) == word.length()) {
				result.add(word);
			}
		}
		int count = 0;
		for (String re : result) {
			count = count + duplicate.get(re);
			
		}
		return count;
	}

	public static List<String> wordSubsequence1(String s, Set<String> wordDict) {
		// write your code hereL
		List<String> result = new ArrayList<String>();
		Map<String, Integer> positions = new HashMap();
		for (String word : wordDict) {
			positions.put(word, 0);
		}
		for (int i = 0; i < s.length(); i++) {
			for (String word : wordDict) {
				Integer position = positions.get(word);
				if (position == word.length()) {
					result.add(word);
					positions.remove(word);
				} else {
					if (s.charAt(i) == word.charAt(position)) {
						positions.put(word, position + 1);
					}
				}
			}
			if (result.size() > 0) {
				wordDict.removeAll(result);
			}
		}
		for (String word : positions.keySet()) {
			if (positions.get(word) == word.length()) {
				result.add(word);
			}
		}
		return result;
	}

	public static void main(String[] args) {
		
		String[] x = {"a","bb","acd","ace"};
		String y = "abcde";
		String[] ss = { "qlhxagxdq", "qlhxagxdq", "lhyiftwtut", "yfzwraahab" };
		String s = "qlhxagxdqh";
		Set<String> set = new HashSet<>();
		for (String str : ss) {
			set.add(str);
		}
		System.out.println(wordSubsequence(y , x ));
	}
}
