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
	private int index;
	
	
	public Node(){
		
	}
	
	//setters
	public void setSequence(String sequence)	{	this.sequence = sequence;	}
	public void setScore(int score)				{	this.score = score;			}
	public void setIndex(int index)				{	this.index = index;			}
	
	//getters
	public String getSequence()	{	return sequence; 		}
	public int getScore()		{	return score; 			}
	public int getIndex()		{	return index; 			}
	public int getChildrenLen()	{	return CHILDREN_LEN;	}
	public Node[] getChildren()	{	return CHILDREN;		}
	
	//functions
	public void addChild(Node tmp){
		
		CHILDREN[CHILDREN_LEN] = tmp;
		CHILDREN_LEN++;
		
	}
	
 }