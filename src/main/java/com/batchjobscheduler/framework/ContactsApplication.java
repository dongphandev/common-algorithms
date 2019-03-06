package com.batchjobscheduler.framework;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author dongphan
 * 
 * Store all possible sus-strings of contact into a Map. This will ensure lookup operation is in O(1).
 */
public class ContactsApplication {

	public static final String ADD_OPERATION = "add";

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int numberOperations = Integer.parseInt(br.readLine());

		Map<String, Integer> allPossiblePartialContacts = new HashMap<String, Integer>();

		for (int i = 0; i < numberOperations; i++) {
			String[] input = br.readLine().split(" ");
			final String operation = input[0];
			final String contactName = input[1];
			if (ADD_OPERATION.equals(operation)) {
				for (int j = 1; j <= contactName.length(); j++) {
					final String partialContactName = contactName.substring(0, j);
					if (allPossiblePartialContacts.get(partialContactName) == null) {
						allPossiblePartialContacts.put(partialContactName, 1);
					} else {
						allPossiblePartialContacts.put(partialContactName, allPossiblePartialContacts.get(partialContactName) + 1);
					}
				}
			} else {//Find partial
				if (allPossiblePartialContacts.get(contactName) == null) {
					System.out.println(0);
				} else {
					System.out.println(allPossiblePartialContacts.get(input[1]));
				}

			}
		}
	}
}
