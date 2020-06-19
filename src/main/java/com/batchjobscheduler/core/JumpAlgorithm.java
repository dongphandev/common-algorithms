package com.batchjobscheduler.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class JumpAlgorithm {

	public int deleteAndEarn(int[] nums) {
		int max = 0;
		Map<Integer, HashSet<Integer>> m = new HashMap<Integer,HashSet<Integer>>();
		for (int i = 0; i < nums.length; i++) {
			HashSet<Integer> positions = m.get(nums[i]);
			if(positions == null) {
				positions = new HashSet<Integer>();
				m.put(nums[i],positions);
			}
			positions.add(i);
		}
		for (int i = 0; i < nums.length; i++) {
			int tem = deleteAndEarnHelper(nums, i, new HashSet<Integer>());
			if (max < tem) {
				max = tem;
			}
		}
		return max;
	}

	public int deleteAndEarnHelper(int[] nums, int current, HashSet<Integer> done, Map<Integer, HashSet<Integer>> m) {
		if (done.size() == nums.length) {
			return 0;
		}
		int max = 0;
		int currentValue = nums[current];
		done.add(current);
		if(m.get(nums[current]-1) != null) {
			done.addAll(m.get(nums[current]-1));
		}
		if(m.get(nums[current]+1) != null) {
			done.addAll(m.get(nums[current]+1));
		}
		for (int i = current - 1; i >= 0; i--) {
			if (done.contains(nums[i])) {
				continue;
			}
			int tem = ( deleteAndEarnHelper(nums, i, done));
			if (max < tem) {
				max = tem;
			}
		}
		for (int i = current + 1; i < nums.length; i++) {
			if (done.contains(nums[i])) {
				continue;
			}
			int tem = ( deleteAndEarnHelper(nums, i, done));
			if (max < tem) {
				max = tem;
			}
		}
		done.remove(currentValue);
		done.remove(currentValue - 1);
		done.remove(currentValue + 1);
		return max+currentValue;
	}

	public static void main(String[] args) {
		JumpAlgorithm d = new JumpAlgorithm();
		int[] c = {3, 4, 2 };
		int[][] f1 = { { 0, 1, 100 }, { 1, 2, 100 }, { 0, 2, 500 } };
		System.out.println(d.deleteAndEarn(c));
	}
}
