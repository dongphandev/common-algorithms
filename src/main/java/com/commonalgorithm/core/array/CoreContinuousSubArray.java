package com.commonalgorithm.core.array;

import java.util.HashMap;
import java.util.Map;

public class CoreContinuousSubArray {
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

	public static void main(String[] args) {
		int[] a = { 2, 2, 1, 2, 2, 2, 1, 1 };

		System.out.println(subarraysWithKDistinct(a, 2));
	}
}
