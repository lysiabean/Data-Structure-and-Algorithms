//Siying Li, CSE 373A Winter 2015
//Assignment 1
//An ArrayQueue is a simple data structure that allows elements to be enqueued and dequeued in a way that 
//preserves the order in which they were input. When it is empty, front will have passed back in the 
//array (circularly). Front and back pointers may overlap and move from the end of the array to the beginning. 

public class ArrayQueue {
	private String[] queueArray;
	private int size;
	private int front;
	private int back;
	
	//Constructs the class
	public ArrayQueue(){
		queueArray = new String[100];
		size = 0;
		front = 0;
		back = -1;
	}
	
	//Constructs the class with parameters
	public ArrayQueue(int startSize){
		queueArray = new String[startSize];
		size = 0;
		front = 0;
		back = -1;
	}
	/**
	 * @function returns the number of elements in the queue
	 * @return size
	 */
	public int getSize(){
		return size;
	}
	/**
	 * @function adds a string to the end of the queue
	 * @param toEnqueue: the input to be inserted
	 */
	//throws an error message when the array is already full
	public void enqueue(String toEnqueue){
		if(size == queueArray.length) {
			System.out.println("Array is already full. Unable to enqueue another string.");
		} else {
			size++;
			back++;
			if(back >= queueArray.length) {
				back = 0;		
			}
			queueArray[back] = toEnqueue;
		}
	}
	
	/**
	 * @function removes the string from the front of the queue
	 * @return the string from the front of the queue
	 */
	//returns null if empty
	public String dequeue(){
		String toDequeue = "";
		if(size == 0) {
			return null;
		} else {
			toDequeue = queueArray[front];
			front++;
			if(front >= queueArray.length) {
				front = 0;
			}
			size--;
		}
		return toDequeue;
	}
	
	/**
	 * 
	 * @return returns true if the queue is empty, false otherwise
	 */
	public boolean isEmpty(){
		return (size == 0);
	}

	/**
	 * 
	 * @return returns true if the queue is full, false otherwise
	 */
	public boolean isFull(){
		return (size == queueArray.length);
	}
	
}
