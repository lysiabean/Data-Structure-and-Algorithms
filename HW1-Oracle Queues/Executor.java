//Siying Li, CSE 373A Winter 2015
//Assignment 1
//This program is a set of oracles that can read files and answer questions in the files. Questions are  
//answered in Round-Robin fashion by oracles until all questions are answered.

public class Executor {

	public static void main(String[] args) {
		Utility.init(); // initializes file readers
		String[] questions = Utility.readQuestions(); //reads question.txt file into questions array
		String[] answers = Utility.readAnswers(); // reads answers.txt file into answers array
		
		int numOracles = answers.length; //finds the number of oracles
		ArrayQueue[] oracles = new ArrayQueue[numOracles];
		for(int i = 0; i < questions.length; i++) {
			int order = Utility.random(numOracles);
			if(oracles[order] == null) {
				oracles[order] = new ArrayQueue();
			}
			oracles[order].enqueue(questions[i]);
		}
		int questionCount = 0;
		while(questionCount < questions.length) {
			for(int i = 0; i < numOracles; i++) {
				if((oracles[i] != null) && (!oracles[i].isEmpty())) {
					questionCount++;
					String question = oracles[i].dequeue();
					String answer = answers[i];
					System.out.println(question + ":  " + answer);
				} 
			}
		}	
	}
}

