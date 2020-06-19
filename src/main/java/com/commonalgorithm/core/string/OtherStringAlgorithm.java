package com.commonalgorithm.core.string;

public class OtherStringAlgorithm {

	public static boolean isInterleave(String s1, String s2, String s3) {
		if (s1.length() + s2.length() != s3.length()) {
			return false;
		}
		return isInterleaveHelper(s1, 0, s2, 0, s3, 0);
	}

	public static boolean isInterleaveHelper(String s1, int i, String s2, int j, String s3, int k) {
		
		if (k == 0) {
			
		}else {
			
		}
		if (s1.charAt(i) != s3.charAt(k) && s2.charAt(j) != s3.charAt(k)) {
			return false;
		}
		if (s1.charAt(i) == s3.charAt(k) && s2.charAt(j) != s3.charAt(k)) {
			return isInterleaveHelper(s1,i-1,s2,j,s3,k-1) || isInterleaveHelper(s1,i,s2,j-1,s3,k-1);
		}
		if (s1.charAt(i) != s3.charAt(k) && s2.charAt(j) == s3.charAt(k)) {
			if ( j+1 < s2.length()) {
				return isInterleaveHelper(s1, i, s2, j + 1, s3, k + 1);
			}else {
				return false;
			}
		}
		if (s1.charAt(i) == s3.charAt(k) && s2.charAt(j) == s3.charAt(k)) {
			if ( i+1 < s1.length() && j+1 <s2.length()) {
				return isInterleaveHelper(s1, i + 1, s2, j, s3, k + 1)
						|| isInterleaveHelper(s1, i, s2, j + 1, s3, k + 1);
			}else if(i+1 < s1.length() && j+1 >= s2.length()) {
				return isInterleaveHelper(s1, i+1, s2, j , s3, k + 1);
			}else if( i+1 >= s1.length() && j+1 < s2.length()) {
				return isInterleaveHelper(s1, i, s2, j + 1, s3, k + 1);
			}else {
				return true;
			}
		}
		return false;
		
	}

	public static void main(String[] args) {
		String s1 = "a";
		String t1 = "b";

		System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
	}
}
