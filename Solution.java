import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int size_firstSequence = scanner.nextInt();
		int size_secondSequence = scanner.nextInt();

		int[] firstSequence = new int[size_firstSequence];
		int[] secondSequence = new int[size_secondSequence];

		for (int i = 0; i < size_firstSequence; i++) {
			firstSequence[i] = scanner.nextInt();
		}
		for (int i = 0; i < size_secondSequence; i++) {
			secondSequence[i] = scanner.nextInt();
		}
		scanner.close();

		int[] results = longestCommonSubsequence(firstSequence, secondSequence);
		printLongestCommonSubsequence(results);
	}

	/**
	 * Comparing the elements of both sequences and storing the length of the common
	 * subsequences in a matrix format.
	 * 
	 * @return A double array of integers representing the length of the common
	 *         subsequences.
	 */
	public static int[][] extractLengthOfCommonSubsequences(int[] firstSequence, int[] secondSequence) {

		/**
		 * A matrix for storing the length of the common subsequences.
		 * 
		 * The algorithm calculating the common subsequences requires that the first row
		 * and column have values of zero. Thus the rows and columns of the matrix have
		 * a length of the corresponding sequence plus 1.
		 */
		int[][] lengthOfsubsequences = new int[firstSequence.length + 1][secondSequence.length + 1];

		for (int i = 1; i <= firstSequence.length; i++) {
			for (int j = 1; j <= secondSequence.length; j++) {
				if (firstSequence[i - 1] == secondSequence[j - 1]) {
					lengthOfsubsequences[i][j] = lengthOfsubsequences[i - 1][j - 1] + 1;
				} else {
					lengthOfsubsequences[i][j] = Math.max(lengthOfsubsequences[i - 1][j],
							lengthOfsubsequences[i][j - 1]);
				}
			}
		}
		return lengthOfsubsequences;
	}

	/**
	 * Tracing the path of the longest common subsequence and storing it in an array
	 * of integers.
	 * 
	 * Although there might be many different subsequences with the found maximum
	 * length, the value of the of the matrix cell at the intersection of the last
	 * row and column will always contain the length of one such subsequence.
	 * 
	 * Thus the algorithm starts tracing the values of the longest common
	 * subsequence from this cell.
	 * 
	 * @return An array of integers containing the values of the longest common
	 *         subsequence (or one such subsequence, in case of many).
	 */
	public static int[] longestCommonSubsequence(int[] firstSequence, int[] secondSequence) {

		int[][] lengthOfsubsequences = extractLengthOfCommonSubsequences(firstSequence, secondSequence);
		int lengthOfLongestCommonSubsequence = lengthOfsubsequences[firstSequence.length][secondSequence.length];
		/**
		 * An array storing the values of the longest common subsequence.
		 */
		int[] longestCommonSubsequence = new int[lengthOfLongestCommonSubsequence];

		int rows = firstSequence.length;
		int columns = secondSequence.length;
		int index = lengthOfLongestCommonSubsequence - 1;

		while (rows > 0 && columns > 0) {
			if (firstSequence[rows - 1] == secondSequence[columns - 1]) {
				longestCommonSubsequence[index] = firstSequence[rows - 1];
				rows--;
				columns--;
				index--;
			} else if (lengthOfsubsequences[rows][columns - 1] > lengthOfsubsequences[rows - 1][columns]) {
				columns--;
			} else {
				rows--;
			}
		}
		return longestCommonSubsequence;
	}

	/**
	 * Prints the values of the longest common subsequence.
	 */
	public static void printLongestCommonSubsequence(int[] longestCommonSubsequence) {
		for (int i = 0; i < longestCommonSubsequence.length; i++) {
			System.out.print(longestCommonSubsequence[i] + " ");
		}
	}
}
