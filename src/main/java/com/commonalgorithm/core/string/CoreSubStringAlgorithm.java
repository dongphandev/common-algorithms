package com.commonalgorithm.core.string;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CoreSubStringAlgorithm {

	public static int lengthOfLongestSubstringWithKDistinct(String s, int k) {
		int result = 0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		int start = 0;
		for (int end = 0; end < s.length(); end++) {
			char c = s.charAt(end);
			Integer position = map.get(c);
			if (position == null) {
				if (map.size() == k - 1) {
					if (result < (end - start)) {
						result = end - start;
					}
				} else if (map.size() == k) {
					for (; start < end; start++) {
						int count = map.get(s.charAt(start));
						count--;
						if (count == 0) {
							map.remove(s.charAt(start));
							start++;
							break;
						}else {
							map.put(c, count);
						}
					}
				}
				map.put(c, 1);
			} else {
				map.put(c, map.get(c) + 1);
			}
		}
		return result;
	}

	public int lengthOfLongestSubstringatMostKDistinct(String s, int k) {
		int result = 0;
		int i = 0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();

		for (int j = 0; j < s.length(); j++) {
			char c = s.charAt(j);
			if (map.containsKey(c)) {
				map.put(c, map.get(c) + 1);
			} else {
				map.put(c, 1);
			}

			if (map.size() <= k) {
				result = Math.max(result, j - i + 1);
			} else {
				while (map.size() > k) {
					char l = s.charAt(i);
					int count = map.get(l);
					if (count == 1) {
						map.remove(l);
					} else {
						map.put(l, map.get(l) - 1);
					}
					i++;
				}
			}

		}

		return result;
	}

	public static int longestSubstringWithoutRepeatingCharacters(String input) {
		if (input == null || input.length() == 0) {
			return 0;
		}
		if (input.length() == 1) {
			return 1;
		}

		int start = 0;
		int end = 1;
		int record = 0;
		int[] positions = new int[95];
		Arrays.fill(positions, -1);
		positions[input.charAt(0) - ' '] = 0;
		for (; end < input.length(); end++) {
			Integer position = positions[input.charAt(end) - ' '];
			if (position >= 0) {
				if (end - start > record) {
					record = end - start;
				}
				for (int j = start; j < position; j++) {
					positions[input.charAt(j) - ' '] = -1;
				}
				start = position + 1;
			} else {
				if (end - start + 1 > record) {
					record = end - start + 1;
				}
			}
			positions[input.charAt(end) - ' '] = end;

		}
		return record;

	}

	public static String smallestSubsequence(String text) {
		if (text == null || text.length() == 0) {
			return "";
		}
		String re = "";
		Set<Character> unique = new HashSet<Character>();
		for (int i = 0; i < text.length(); i++) {
			unique.add(text.charAt(i));
		}
		Map<Character, List<Integer>> map = new HashMap<Character, List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		list.add(0);
		map.put(text.charAt(0), list);
		int start = 0;
		int record = Integer.MAX_VALUE;
		for (int end = 1; end < text.length(); end++) {
			char c = text.charAt(end);
			List<Integer> l = map.get(c);
			if (l == null) {
				l = new ArrayList<Integer>();
				map.put(c, l);
			}
			l.add(end);
			if ((map.size() == unique.size())) {
				if ((c == text.charAt(start))) {
					do {
						if (l.size() == 1) {
							break;
						} else {
							l.remove(0);
							start++;
							l = map.get(text.charAt(start));
						}
					} while ((end - start) > unique.size());
				}

				if (end - start < record) {
					record = end - start;
					Set<Character> tem = new HashSet<>();
					StringBuffer buf = new StringBuffer();
					for (int i = start; i <= end; i++) {
						char x = text.charAt(i);
						if (!tem.contains(x)) {
							tem.add(x);
							buf.append(x);
						}
					}
					re = buf.toString();
				}
			}
		}
		return re;
	}

	public static int countDistinctSubsequences(String s) {
		if (s.length() == 0) {
			return 0;
		}
		BigInteger[] resultNoDuplicated = new BigInteger[s.length()];
		resultNoDuplicated[0] = BigInteger.valueOf(1);
		Map<Character, Integer> mostrecentCharSofar = new HashMap<Character, Integer>();
		Map<Character, BigInteger> totalDuplicatedSofar = new HashMap<Character, BigInteger>();
		totalDuplicatedSofar.put(s.charAt(0), BigInteger.valueOf(0));
		mostrecentCharSofar.put(s.charAt(0), 0);
		for (int i = 1; i < s.length(); i++) {
			resultNoDuplicated[i] = resultNoDuplicated[i - 1].add(resultNoDuplicated[i - 1]).add(BigInteger.valueOf(1));
			char c = s.charAt(i);
			Integer mostrecentChar = mostrecentCharSofar.get(c);
			if (mostrecentChar != null) {
				BigInteger currentDuplicated = BigInteger.valueOf(1);
				if (mostrecentChar >= 1) {
					currentDuplicated = resultNoDuplicated[mostrecentChar]
							.subtract(resultNoDuplicated[mostrecentChar - 1]);
				}
				BigInteger total = totalDuplicatedSofar.get(c);
				total = total.add(currentDuplicated);
				resultNoDuplicated[i] = resultNoDuplicated[i].subtract(total);
				totalDuplicatedSofar.put(c, total);
			} else {
				totalDuplicatedSofar.put(s.charAt(i), BigInteger.valueOf(0));
			}
			mostrecentCharSofar.put(c, i);
		}
		return resultNoDuplicated[s.length() - 1].mod(BigInteger.valueOf(10).pow(9).add(BigInteger.valueOf(7l)))
				.intValue();
	}

	public static int countAllAllSubSequenceInString(String target, String pattern) {

		int n = target.length();
		int m = pattern.length();
		int[][] result = new int[n][m];
		int c = 0;
		for (int i = 0; i < n; i++) {
			if (target.charAt(i) == pattern.charAt(0)) {
				c++;
			}
			result[i][0] = c;
		}
		if (target.charAt(0) == pattern.charAt(0)) {
			result[0][0] = 1;
		}
		for (int i = 1; i < m; i++) {
			result[0][i] = 0;
		}
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				char cn = target.charAt(i);
				char cm = pattern.charAt(j);
				if (cn == cm) {
					result[i][j] = result[i - 1][j] + result[i - 1][j - 1];
				} else {
					result[i][j] = result[i - 1][j];
				}
			}
		}
		return result[n - 1][m - 1];

	}

	public static String minWindow(String text, String t) {
		if (text == null || text.length() == 0 || (text.length() < t.length())) {
			return "";
		}

		int[] target = new int[58];
		Arrays.fill(target, 0);
		int totalTaget = 0;
		for (int i = 0; i < t.length(); i++) {
			target[t.charAt(i) - 'A'] = target[t.charAt(i) - 'A'] + 1;
		}
		for (int i = 0; i < 58; i++) {
			if (target[i] > 0) {
				totalTaget++;
			}
		}
		int start = 0;
		for (; start < text.length(); start++) {
			if (target[text.charAt(start) - 'A'] > 0) {
				break;
			}
		}
		int record = Integer.MAX_VALUE;
		int end = start;
		int total = 0;
		String result = "";
		int[] windows = new int[58];
		Arrays.fill(windows, 0);
		for (; end < text.length(); end++) {
			if (target[text.charAt(end) - 'A'] == 0) {
				continue;
			}
			char currentChar = text.charAt(end);
			windows[currentChar - 'A'] = windows[currentChar - 'A'] + 1;

			if (windows[currentChar - 'A'] == target[currentChar - 'A']) {
				total++;
			}
			if (windows[currentChar - 'A'] >= target[currentChar - 'A']) {
				if ((currentChar == text.charAt(start))) {
					do {
						char startChar = text.charAt(start);
						if (target[startChar - 'A'] == 0) {
							start++;
							continue;
						}
						if ((windows[startChar - 'A'] <= target[startChar - 'A'])) {
							break;
						} else {
							start++;
							windows[startChar - 'A'] = windows[startChar - 'A'] - 1;
						}
					} while (start < end);
				}

				if ((end - start) < record) {
					if (total == totalTaget) {
						record = end - start;
						result = text.substring(start, end + 1);
					}
				}
			}
		}

		return result;
	}

	public static void main(String[] args) {
		String s1 = "a";
		String t1 = "b";
		Integer x =  2147483640;
		Integer y =  2147483630;
		Integer z = x+y;
		System.out.println(z);
	}

}
