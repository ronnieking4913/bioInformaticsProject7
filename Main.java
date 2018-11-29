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

				//for debug
				//printNodes();
				populateNodeScores();
				printChildren();
				findPath();
			}

		}
		while(!(option.toUpperCase()).equals("Q"));
			System.out.println("Exiting the program...");

	}

	//find the path
	public static void findPath(){
		int parent=0;
		int child=0;
		int max=0;
		int temp=0;

		//get the max node. place to start
		//use DAG with lowest scores
		int graph[][] = new int[NODE_LEN][NODE_LEN];
		int sortGraph[][] = new int[NODE_LEN][NODE_LEN];
		int length = 0;

		for(int i = 0; i < NODE_LEN; i++){
			length = NODES[i].getChildrenLen();
			Node[] children= NODES[i].getChildren();
			for(int j = 0; j < length; j++){
				temp = children[j].getScore();
				graph[i][j]=temp;
				if(temp>max)
					max=temp;
			}
		}
		//edges = v-1
		for(int count=0;count<length;count++){
			for(int i = 0; i < NODE_LEN; i++){
				length = NODES[i].getChildrenLen();
				Node[] children= NODES[i].getChildren();
				for(int j = 0; j < length; j++)
					if (graph[i][j]==max){
						count=count+1;
						System.out.print("Edge: " + count +"\tNode: " + i +"\tDestination: " +j+ "\tDistance: " + graph[i][j] + "\n");
					}
			}
		}
	}


	public static int findScore(String first, String second){

		int max = 0;
		int score = 0;
		int cycle = 1;
		int firstPointer = 1;
		int secondPointer = 0;
		int firstLen = first.length();
		int secondLen = second.length();
		char firstArr[] = first.toCharArray();
		char secondArr[] = second.toCharArray();

		//loop through to get all of the alignments
		while(cycle < firstLen && cycle < secondLen){

			//loop through to get the score of the alignment
			while(firstPointer < firstLen && secondPointer < secondLen){

				//if the characters are equal add to the score
				if(firstArr[firstPointer] == secondArr[secondPointer]){

					//increase the score 
					score++;

				}

				//if the sequence is broken, set the score to 0 and exit the alignment
				else{

					score = 0;
					firstPointer = firstLen;

				}

				//increase the second pointer
				firstPointer++;
				secondPointer++;

			}

			//if the score is more than the max, make the score the max
			if(max < score)
				max = score;

			//increase the cycle
			cycle++;

			//reset the first, second pointer and score
			firstPointer = cycle;
			secondPointer = 0;
			score = 0;


		}

		return max;

	}

	//populate the children nodes to find the paths
	public static void populateNodeScores(){

		int score = 0;
		Node tmp = null;

		//go through each node
		for(int i = 0; i < NODE_LEN; i++){

			//go through the other nodes
			for(int j = 0; j < NODE_LEN; j++){

				//if i ==j, move on
				if(i == j){

				}

				//check the string
				else{

					score = findScore(NODES[i].getSequence(), NODES[j].getSequence());

					//if the score isnt 0, create the node, add the score and make a child
					if(score > 0){
						tmp = new Node();
						tmp.setScore(score);
						tmp.setIndex(j);
						NODES[i].addChild(tmp);
					}

					//for debug
					//System.out.println("\ni: " + i + " j: " + j +  " Score: " + score);
				}

			}
		}
		//find the scores
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
				tmp = new Node();
				tmp.setSequence(line);
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

	//for debug
	public static void printChildren(){

		int length = 0;

		for(int i = 0; i < NODE_LEN; i++){

			length = NODES[i].getChildrenLen();
			Node[] children= NODES[i].getChildren();
			System.out.println("Node " + i + ":");

			for(int j = 0; j < length; j++){
				System.out.print("\tChild: " + children[j].getIndex() + " score: " + children[j].getScore());
			}

			System.out.println();

		}

	}

}
