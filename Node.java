/* * * * * * * * * * * * * *
 * Class: Bioinformatics   *
 * Date: 11/16/2018        *
 * Names: Ronnie King      *
 *        Nathan Layfield  *
 * * * * * * * * * * * * * */
 
 class Node{

	private Node CHILDREN[] = new Node[10];
	private int CHILDREN_LEN = 0;
	private String sequence;
	private int score;
	
	
	public Node(String sequence){
		
		this.sequence = sequence;
		
	}
	
	//setters
	public void setSequence(String sequence)	{		this.sequence = sequence;	}
	public void setScore(int score)		{	this.score = score;	}
	
	//getters
	public String getSequence()	{	return sequence; 	}
	public int getScore()	{	return score; 	}
	
	//functions
	public void addChild(Node tmp){
		
		CHILDREN[CHILDREN_LEN] = tmp;
		
	}
	
 }