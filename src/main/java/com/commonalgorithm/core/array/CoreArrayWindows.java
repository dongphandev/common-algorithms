package com.commonalgorithm.core.array;

import java.util.HashMap;
import java.util.Map;

public class CoreArrayWindows {
	
	public static int allUniqueElementOfSlidingWindow(int[] nums, int k) {
		int start = 0;
		int sumSofar = 0;
		Map<Integer,Integer> map = new HashMap<>();
		
		for (; start < k || start < nums.length; start++) {
			Integer count = map.get(nums[start]);
			if (count == null) {
				count = 1;
				sumSofar = sumSofar + 1;
			}else if(count ==1) {
				sumSofar = sumSofar - 1;
				count++;
			}else {
				count++;
			}
			map.put( nums[start], count);
		}
		int sumPreviousWindow = sumSofar;
		for (; start < nums.length; start++) {
			int sumOfThisWindows = sumPreviousWindow;
			Integer count1 = map.get(nums[start - k]);
			if (count1 == 1 ) {
				sumOfThisWindows = sumOfThisWindows - 1;
				map.remove(nums[start - k]);
			}else if(count1 == 2) {
				sumOfThisWindows = sumOfThisWindows + 1;
				map.put( nums[start - k ], count1 - 1);
			}else {
				map.put( nums[start - k ], count1 - 1);
			}
			
			Integer count2 = map.get(nums[start]);
			if (count2 == null ) {
				sumOfThisWindows = sumOfThisWindows + 1;
				count2 = 1;
			}else if(count2 == 1) {
				sumOfThisWindows = sumOfThisWindows - 1;
				count2++; 
			}else {
				count2++;
			}
			map.put( nums[start], count2);
			sumSofar = sumOfThisWindows +sumSofar;
			sumPreviousWindow = sumOfThisWindows;
		}
		
		return sumSofar;
		
	}
	public static int[] allMaxElementOfSlidingWindow(int[] nums, int k) {
		if (k > nums.length || k < 1) {
			return (new int[] {});
		}
		if (k == 1) {
			return nums;
		}

		int firstMax = Integer.MIN_VALUE;
		int secondMax = Integer.MIN_VALUE;
		if (nums[0] > nums[1]) {
			firstMax = nums[0];
			secondMax = nums[1];
		} else {
			firstMax = nums[1];
			secondMax = nums[0];
		}
		int[] result = new int[nums.length - k + 1];
		int i = 2;
		for (; i < k; i++) {
			if (nums[i] > firstMax) {
				secondMax = firstMax;
				firstMax = nums[i];
			} else if (nums[i] < secondMax) {
				// do nothing
			} else {
				secondMax = nums[i];
			}
		}
		int count = 0;
		result[count++] = firstMax;
		for (; i < nums.length; i++) {
			if (nums[i - k] == firstMax) {// remove FirstMax
				if (nums[i] > secondMax) {
					result[count++] = nums[i];
					firstMax = nums[i];
					// second no change
				} else {
					int temSecondMax = (int) Integer.MIN_VALUE;
					for (int j = i - k + 1; j <= i; j++) {
						if ((nums[j] != secondMax) && (nums[j] > temSecondMax)) {
							temSecondMax = nums[j];
						}
					}
					if (temSecondMax > secondMax) {
						firstMax = temSecondMax;
						result[count++] = firstMax;
					}else {
						result[count++] = secondMax;
						firstMax = secondMax;
						secondMax = temSecondMax;
					}
				}

			} else if (nums[i - k] == secondMax) {// remove secondMax
				if (nums[i] > firstMax) {
					result[count++] = nums[i];
					secondMax = firstMax;
					firstMax = nums[i];
				} else {
					result[count++] = firstMax;
					int temSecondMax = Integer.MIN_VALUE;
					for (int j = i - k + 1; j <= i; j++) {
						if ((nums[j] != firstMax) && (nums[j] > temSecondMax)) {
							temSecondMax = nums[j];
						}
					}
					secondMax = temSecondMax;
				}
			} else {
				if (nums[i] > firstMax) {
					result[count++] = nums[i];
					secondMax = firstMax;
					firstMax = nums[i];
				} else if (nums[i] < secondMax) {
					result[count++] = firstMax;
				} else {
					result[count++] = firstMax;
					secondMax = nums[i];
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		int[] a = {1,2,1,3,3};
		System.out.println(allUniqueElementOfSlidingWindow(a,3));
	}
}
