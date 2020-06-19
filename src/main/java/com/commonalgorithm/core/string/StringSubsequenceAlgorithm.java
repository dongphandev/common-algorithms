package com.commonalgorithm.core.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringSubsequenceAlgorithm {
	public String removeDuplicateLetters(String s) {
		boolean[] charPosition = new boolean[s.length()];
		int[] charPostionInAssci = new int[27];
		int[] fre = new int[27];
		for (int i = 0; i < s.length(); i++) {
			fre[s.charAt(i) - 'a'] = i;
		}
		Arrays.fill(charPostionInAssci, -1);
		/// bcabc
		for (int i = 0; i < s.length(); i++) {
			if (charPostionInAssci[s.charAt(i) - 'a'] == -1) {
				charPostionInAssci[s.charAt(i) - 'a'] = i;
				charPosition[i] = true;
			} else {
				int previousIndex = charPostionInAssci[s.charAt(i) - 'a'];
				charPostionInAssci[s.charAt(i) - 'a'] = previousIndex;
				charPosition[i] = false;
				for (int j = previousIndex + 1; j < i; j++) {
					if (!charPosition[j]) {
						continue;
					}
					if (s.charAt(j) < s.charAt(i)) {
						charPosition[previousIndex] = false;
						charPosition[i] = true;
						charPostionInAssci[s.charAt(i) - 'a'] = i;
						break;
					}
					if ((s.charAt(i) < s.charAt(j)) && (fre[s.charAt(j) - 'a'] < i)) {
						charPosition[i] = false;
						charPostionInAssci[s.charAt(i) - 'a'] = previousIndex;
						break;
					}
				}
			}

		}
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			if (charPosition[i]) {
				buf.append(s.charAt(i));
			}
		}
		return buf.toString();
	}

	public static void main(String[] args) {

	}
}
