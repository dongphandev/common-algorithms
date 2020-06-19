package com.commonalgorithm.core.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.openqa.selenium.remote.server.handler.GetCurrentUrl;

public class CoreContinuousSubArray {

	public int subarraysDivByK(int[] a, int k) {
		if (a == null || a.length == 0) {
			return 0;
		}
		int currentValue = 0;
		int[] divTable = new int[a.length];
		Map<Integer, Integer> notDiv = new HashMap<>();
		if (a[0] % k == 0) {
			divTable[0] = 1;
			currentValue = 1;
		} else {
			divTable[0] = 0;
			notDiv.put(a[0], 1);
		}
		for (int i = 1; i < a.length; i++) {
			if (a[i] % k == 0) {
				divTable[i] = divTable[i - 1] + 1;
				currentValue = currentValue + divTable[i];
			} else {
				divTable[i] = 0;
				Map<Integer, Integer> notDivTem = new HashMap<>();
				for (Integer e : notDiv.keySet()) {
					int x = (e + a[i]);
					if (x % k == 0) {
						divTable[i] = divTable[i] + notDiv.get(e);
					} else {
						notDivTem.put(x, notDiv.get(e));
					}
				}
				notDiv = notDivTem;
				notDiv.put(a[i], divTable[i - 1] + 1);
				currentValue = currentValue + divTable[i];
			}
		}
		return currentValue;

	}

	public class Record {
		public int value;
		public int first;
		public int second;
	}

	public class RecordComparator implements Comparator<Record> {

		// Overriding compare()method of Comparator
		// for descending order of cgpa
		public int compare(Record s1, Record s2) {
			return s1.value - s2.value;
		}
	}

	public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (nums1.length == 0 || nums2.length == 0) {
			return result;
		}

		PriorityQueue<Record> minHeap = new PriorityQueue<Record>(100, new RecordComparator());
		for (int i = 0; i < nums1.length; i++) {
			Record re = new Record();
			re.first = i;
			re.second = 0;
			re.value = nums1[i] + nums2[0];
			minHeap.add(re);
		}
		int first = 0;
		int second = 1;
		while (minHeap.size() > 0 && result.size() < k) {
			Record minRecord = minHeap.poll();
			List<Integer> result1 = new ArrayList<>();
			result1.add(nums1[minRecord.first]);
			result1.add(nums2[minRecord.second]);
			result.add(result1);
			first = minRecord.first;
			second = minRecord.second + 1;
			while (second < nums2.length && result.size() < k) {
				int value = nums1[first] + nums2[second];
				Record re = minHeap.peek();
				if (re == null || value < re.value) {
					List<Integer> l = new ArrayList<>();
					l.add(nums1[first]);
					l.add(nums2[second]);
					result.add(l);
					second++;
				} else {
					Record newRe = new Record();
					newRe.first = first;
					newRe.second = second;
					newRe.value = value;
					minHeap.add(newRe);
					break;
				}
			}
		}
		return result;
	}

	public static int subarraysWithKDistinct(int[] nums, int k) {
		if (nums.length < k) {
			return 0;
		}
		int start = 0;
		int end = 0;
		int result = 0;
		Map<Integer, Integer> unique = new HashMap<>();
		while (unique.size() < k && end < nums.length) {
			Integer count = unique.get(nums[end]);
			if (count == null) {
				count = 1;
			} else {
				count++;
			}
			unique.put(nums[end], count);
			end++;
		}
		Map<Integer, Integer> temCount = new HashMap<>(2);
		if (unique.size() == k) {
			int tem = start;
			while (unique.size() == k) {
				result++;
				if (unique.get(nums[tem]) - 1 == 0) {
					break;
				} else {
					if (!temCount.containsKey(nums[tem])) {
						temCount.put(nums[tem], unique.get(nums[tem]));
					}
					unique.put((nums[tem]), unique.get(nums[tem]) - 1);
					tem++;
				}
			}
			for (Integer key : temCount.keySet()) {
				unique.put(key, temCount.get(key));
			}
			temCount.clear();
		}

		for (; end < nums.length; end++) {
			int x = nums[end];
			int tem = start;
			if (unique.containsKey(x)) {
				unique.put(x, unique.get(x) + 1);
				while (unique.size() == k) {
					result++;
					if (unique.get(nums[tem]) - 1 == 0) {
						break;
					} else {
						if (!temCount.containsKey(nums[tem])) {
							temCount.put(nums[tem], unique.get(nums[tem]));
						}
						unique.put((nums[tem]), unique.get(nums[tem]) - 1);
						tem++;
					}
				}
			} else {
				unique.put(x, 1);
				while (unique.size() > k) {
					if (unique.get(nums[tem]) - 1 == 0) {
						unique.remove(nums[tem]);
						tem++;
					} else {
						unique.put((nums[tem]), unique.get(nums[tem]) - 1);
						tem++;
					}
				}
				start = tem;
				while (unique.size() == k) {
					result++;
					if (unique.get(nums[tem]) - 1 == 0) {
						break;
					} else {
						if (!temCount.containsKey(nums[tem])) {
							temCount.put(nums[tem], unique.get(nums[tem]));
						}
						unique.put((nums[tem]), unique.get(nums[tem]) - 1);
						tem++;
					}
				}
			}
			for (Integer key : temCount.keySet()) {
				unique.put(key, temCount.get(key));
			}
			temCount.clear();
		}
		return result;
	}

	public int numSubarrayBoundedMax(int[] a, int l, int r) {
		int result = 0;
		Integer maxLessThanL = 0;
		Integer maxValid = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] < l) {
				result = result + maxValid;
				maxLessThanL++;
			} else if (a[i] > r) {
				maxValid = 0;
				maxLessThanL = 0;
			} else {
				maxValid = maxValid + maxLessThanL + 1;
				result = result + maxValid;
				maxLessThanL = 0;
			}
		}
		return result;

	}

	public int shortestSubarray(int[] a, int k) {
		if (a.length == 1 && a[0] >= k) {
			return 1;
		}
		int start = 0;
		int record = Integer.MAX_VALUE;
		int[] sumSofar = new int[a.length];
		int offset = 0;
		sumSofar[0] = a[0];
		for (int end = 1; end < a.length; end++) {
			if (sumSofar[end - 1] + a[end] < a[end]) {
				sumSofar[end] = a[end];
				start = end;
			} else {
				sumSofar[end] = sumSofar[end] + a[end];
			}
			if (sumSofar[end] - offset >= k) {
				int delta = sumSofar[end] - k;
				for (int j = end - 1; j >= start; j--) {
					if (sumSofar[j] <= delta) {// remove from j
						start = j + 1;
						offset = sumSofar[j];
						break;
					}
				}
				if (record > (end - start + 1)) {
					record = (end - start + 1);
				}
			}
		}
		if (record == Integer.MAX_VALUE) {
			return -1;
		}
		return record;

	}

	public int sumSubarrayMins(int[] a) {
		for (int i = 0; i < a.length; i++) {

		}
		return 0;

	}

	public int numberOfSubarrays(int[] a, int k) {
		int currentResult = 0;
		int count = 0;
		int first = 0, second = 0;
		while (second < a.length && a[second] % 2 == 0) {
			second++;//
		}
		for (int i = second; i < a.length; i++) {
			if (a[i] % 2 != 0) {
				count++;
				if (count == k) {
					currentResult = currentResult + (second - first + 1);
					if (count > k) {
						second++;
						first = second;
						while (second < a.length && a[second] % 2 == 0) {
							second++;
						}
						count--;
					}
					currentResult = currentResult + (second - first + 1);
				}
			} else {
				if (count == k) {
					currentResult = currentResult + (second - first + 1);
				}
			}
		}

		return currentResult;
	}

	public int longestSubstring(String s, int k) {
		return k;

	}

	public int findMaxLength(int[] a) {
		int result = 0;
		int delta0And1 = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < a.length; i++) {
			if (a[i] == 0) {
				delta0And1 = delta0And1 + 1;
			} else {
				delta0And1 = delta0And1 - 1;
			}
			if (delta0And1 == 0) {
				result = i + 1;
			} else {
				Integer x = map.get(delta0And1);
				if (x != null) {
					if (i - x > result) {
						result = i - x;
					}
				} else {
					map.put(delta0And1, i);
				}
			}

		}
		return result;

	}

	public boolean canJump(int[] nums) {
		return true;
	}

	public static void main(String[] args) {

		int[][] a = { {1,4},{4,5}};
		CoreContinuousSubArray core = new CoreContinuousSubArray();
		
		//System.out.println(core.merge(a));
	}
}
