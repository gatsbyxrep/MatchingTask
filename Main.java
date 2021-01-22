/******************************************************************************

							Online Java Compiler.
				Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

public class Main
{
	public static int[][] readMatrixFromFile(String fileName) {
		Scanner input = new Scanner (new File(fileName));
		int rows = 0;
		int columns = 0;
		while(input.hasNextLine()) {
			++rows;
			Scanner colReader = new Scanner(input.nextLine());
			while(colReader.hasNextInt()) {
				++columns;
			}
		}
		int[][] a = new int[rows][columns];
		
		input.close();
	
		input = new Scanner(new File(fileName));
		for(int i = 0; i < rows; ++i) {
			for(int j = 0; j < columns; ++j) {
				if(input.hasNextInt())
					a[i][j] = input.nextInt();
			}
		}
		return a;
	}
	public static void main(String[] args) {
		int[][] sourceMatrix;
		try {
			sourceMatrix = readMatrixFromFile("matrix.in");
		}
		catch(IOException e) {
			System.out.println("Can't read matrix");
		}
		Matching matching = new Matching(sourceMatrix);
	}
}
