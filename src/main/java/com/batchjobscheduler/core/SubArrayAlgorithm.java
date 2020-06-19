package com.batchjobscheduler.core;

import java.util.ArrayList;
import java.util.List;

public class SubArrayAlgorithm {
	/**
	 * Kadane’s Algorithm to find largest sum of continuous array.
	 * 
	 * @param a
	 * @return
	 */
	public int maxSubArraySum(int a[]) {
		int max_so_far = a[0];
		int curr_max = a[0];

		for (int i = 1; i < a.length; i++) {
			curr_max = Math.max(a[i], curr_max + a[i]);
			max_so_far = Math.max(max_so_far, curr_max);
		}
		return max_so_far;
	}

	public int splitArray(int[] nums, int m) {
		int[] re = new int[nums.length - m];
		int max = nums[0];
		for (int i = 1; i < m; i++) {
			if (max < nums[i]) {
				max = nums[i];
			}
		}
		re[m - 1] = max;
		for (int i = m; i < nums.length; i++) {
			if (nums[i] >= re[m - 1]) {
				re[i] = nums[i];
			} else {
				int x = nums[i];
				int j = i - 1;
				while ((j) >= m && (x + nums[j]) < re[i]) {
					j--;
				}

			}
		}
		return m;
	}

	public List<Integer> diffWaysToCompute(String input) {
		List<String> operators = new ArrayList<String>();
		for (int i = 0; i < input.length(); i++) {
			if (!Character.isDigit(input.charAt(i))) {
				operators.add(input.substring(i, i + 1));
			} else {
				int start = i;
				while ((i + 1) < input.length() && Character.isDigit(input.charAt(i + 1))) {
					i++;
				}
				operators.add(input.substring(start, i + 1));
			}
		}
	
		List<Integer> l1 = new ArrayList<Integer>();
		l1.add(Integer.parseInt(operators.get(0)));
		List<Integer> l2 = new ArrayList<Integer>();
		l2.add(calculate(Integer.parseInt(operators.get(0)), Integer.parseInt(operators.get(2)),
				operators.get(1).charAt(0)));
		for (int i = 4; i < operators.size(); i = i + 2) {
			//2-1-1
			List<Integer> current = new ArrayList<Integer>();
			int num3 = Integer.parseInt(operators.get(i));
			for (int k = 0; k < l2.size(); k++) {
				current.add(calculate(l2.get(k),num3, operators.get(i - 1).charAt(0)));
			}
			int num2 = calculate(Integer.parseInt(operators.get(i - 2)),num3, operators.get(i - 1).charAt(0));
			for (int k = 0; k < l1.size(); k++) {
				current.add(calculate(l1.get(k),num2,operators.get(i - 3).charAt(0)));
			}
			List<Integer> tem = l2;
			l2=current;
			l1 = tem;
			//2*34-4*54
		}
		return l2;
	}
	public List<Integer> diffWaysToComputeHelper(List<String> operators, int i){
		List<Integer> result  = new ArrayList();
		if(i ==0) {
			result.add(Integer.parseInt(operators.get(0)));
			return result;
		}
		if(i == (operators.size()-1)) {
			result.add(Integer.parseInt(operators.get(operators.size()-1)));
			return result;
		}
		
		List<Integer> l1 = diffWaysToComputeHelper(operators, i-1);
		List<Integer> l2 = diffWaysToComputeHelper(operators, i+1);
		for(int k = 0; k<l1.size();k++) {
			for(int j = 0; j < l2.size();j++) {
				calculate(l1.get(k),l2.get(j),operators.get(i).charAt(0));
			}
		}
	}
	private int calculate(int num1, int num2, char operator) {
		switch (operator) {
		case '+':
			return num1 + num2;
		case '-':
			return num1 - num2;
		case '*':
			return num1 * num2;
		}
		return 0;
	}

	public static void main(String[] args) {
		SubArrayAlgorithm d = new SubArrayAlgorithm();
		char[][] a = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'E', 'S' }, { 'A', 'D', 'E', 'E' } };
		int[][] b = { { 4, 5 }, { 4, 6 }, { 6, 7 }, { 2, 3 }, { 1, 1 } };
		int[] c = { 3, 5, 1 };
		System.out.println(d.diffWaysToCompute("2*3-4*5"));
		// System.out.println("1".substring(0,1));
	}
}
