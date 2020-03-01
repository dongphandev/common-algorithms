package com.commonalgorithm.core.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringSubsequenceAlgorithm {

	public static int countNumberOfDistinctSubsequencesEqualsToGivenString(String source, String givenString) {
		
		return 0;
	}
	

	private  static int countNumberOfDistinctSubsequencesEqualsToGivenStringHelper(String source,int i, String givenString, int j) {
		
		return 0;
	}

	public static String minimumWindowSubsequenc(String source, String target) {

		Map<Character, List<Integer>> positionsOfChar = new HashMap<>();
		for (int i = 0; i < source.length(); i++) {
			List<Integer> positions = positionsOfChar.get(source.charAt(i));
			if (positions == null) {
				positions = new ArrayList<>();
				positionsOfChar.put(source.charAt(i), positions);
			}
			positions.add(i);
		}

		List<Integer> positionOfFirstChar = positionsOfChar.get(target.charAt(0));
		if (positionOfFirstChar == null) {
			return "";
		}
		int startResult = 0;
		int endResult = 0;
		int record = Integer.MAX_VALUE;
		String result = "";
		for (int i = 0; i < positionOfFirstChar.size(); i++) {
			startResult = positionOfFirstChar.get(i);
			endResult = startResult;
			boolean isExisted = true;
			for (int j = 1; j < target.length(); j++) {
				List<Integer> positions = positionsOfChar.get(target.charAt(j));
				if (positions == null) {
					break;
				}
				int index = Collections.binarySearch(positions, endResult + 1);
				if (index >= 0) {
					endResult++;
					continue;
				} else {
					int insert = 0 - (1 + index);
					if (insert == 0) {
						endResult = positions.get(insert);
					} else if (insert == positions.size()) {
						isExisted = false;
						break;
					} else {
						endResult = positions.get(insert);
					}

				}
			}
			if (!isExisted) {
				break;
			}
			if (endResult - startResult < record) {
				record = endResult - startResult;
				result = source.substring(startResult, endResult + 1);
			}
		}
		return result;

	}

	public static void main(String[] args) {
		List x = new ArrayList();
		x.add(1);
		x.add(5);
		x.add(9);
		x.add(9);
		x.add(12);
		System.out.println(0 - (1 + Collections.binarySearch(x, 16)));
		System.out.println(minimumWindowSubsequenc("cnhczmccqouqadqtmjjzl", "mm"));
	}
}
