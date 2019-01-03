package braingain.modell;

import java.util.ArrayList;

/**
 * The Class Question.
 */
public class Question {
	
	/** The question. */
	private String question;
	
	/** The answers of the Question. */
	private ArrayList<String> answers = new ArrayList<String>();
	
	/**
	 * Instantiates a new question.
	 *
	 * @param question the question
	 * @param answer One answer to the given Question
	 */
	public Question(String question, String answer) {
		this.question = question;
		answers.add(answer);
	}
	
	/**
	 * Instantiates a new question.
	 *
	 * @param question the question
	 * @param answers An Array of Answers to the Question
	 */
	public Question(String question, String[] answers) {
		this.question = question;
		for(int i = 0; i < answers.length; i++) {
			this.answers.add(answers[i]);
		}
	}
	
	/**
	 * Instantiates a new question.
	 *
	 * @param question the question
	 * @param answers An ArrayList of Answers
	 */
	public Question(String question, ArrayList<String> answers) {
		this.question = question;
		this.answers = answers;
	}
	
	/**
	 * Gets the question.
	 *
	 * @return the question
	 */
	public String getQuestion() {
		return this.question;
	}
	
	/**
	 * Gets the ArrayList of Answers.
	 *
	 * @return the ArrayList of Answers
	 */
	public ArrayList<String> getAnswersArrayList(){
		return this.answers;
	}
	
	/**
	 * Gets the Array of Answers.
	 *
	 * @return the Array of Answers
	 */
	public String[] getAnswersArray() {
		return this.answers.toArray(new String[answers.size()]);
	}
	
	/**
	 * Gets one random right Answer.
	 *
	 * @return one random Answer
	 */
	public String getRightAnswer() {
		return answers.get((int) (Math.random()*answers.size()));
	}
	
}
