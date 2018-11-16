/* * * * * * * * * * * * * *
 * Class: Bioinformatics   *
 * Date: 11/16/2018        *
 * Names: Ronnie King      *
 *        Nathan Layfield  *
 * * * * * * * * * * * * * */

import java.io.*;
import java.util.*;
import java.lang.*;

public class Main{

	//global variables
	static Node NODES[] = new Node[10];
	static int NODE_LEN = 0;

	public static void main(String[] args){
		
		String option = "";

		do{

			//ask the user for file name and populate appropriate array
				option = getFileName();

			//
			if(!(option.toUpperCase()).equals("Q")){
				printNodes();
			}

		}
		while(!(option.toUpperCase()).equals("Q"));
			System.out.println("Exiting the program...");

	}

	public static String getFileName(){

		Scanner keyboard = new Scanner(System.in);
		String input = "";

		System.out.println("\nPlease enter the file you would like to open:");
		System.out.println("\t[Q] to quit the program");

		//get the responce
		input = keyboard.nextLine();

		//if the user chooses to quit, return Q
		if( input.toUpperCase().equals("Q") )
			return input;

		else
			input = readFile(input);

		return input;

	}

	public static String readFile(String fileName){

		FileReader file = null;
		String line = "";
		String input = "";
		int flag = 0;
		Scanner scan = new Scanner(System.in);
		Node tmp = null;

		//error trap an incorrect file
		while(flag == 0){

			try{
				file = new FileReader(fileName);
				flag = 1;
			}

			catch(FileNotFoundException ex){

				System.out.println("\n" + fileName + " was not found. Please enter a different file.");
				System.out.println("[Q] to quit");
				fileName = scan.nextLine();

				//if the user decides to quit, return
				if(fileName.toUpperCase().equals("Q"))
					return "Q";

			}
		}

		try{

			//Creating the Buffered Reader to check for end of file
			BufferedReader br = new BufferedReader(file);

			//Read the contents of the file to create the return string
			while((line = br.readLine()) != null){
					
				tmp= new Node(line);
				NODES[NODE_LEN] = tmp;
				NODE_LEN++;
				
			}

			file.close();

		}

		catch(Exception er){
			System.out.println("Error opening file. Quitting....");
			return "Q";
		}

		return input;

	}
	
	//For debug
	public static void printNodes(){
		for(int i = 0; i < NODE_LEN; i++)
			System.out.print("\nNode: " + i + "\nSequence: " + 
								NODES[i].getSequence() + "\nScore: " + 
								NODES[i].getScore() + "\n");
	}


}
