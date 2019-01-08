package braingain.modell;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * The Class Question.
 */
public class Question implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The question. */
	private String question;

	/** The keywords of the answer. */
	private ArrayList<String> keyWords = new ArrayList<String>();

	/**
	 * The answer
	 */
	private String answer;

	/**
	 * Instantiates a new question.
	 *
	 * @param question
	 *            the question
	 * @param answer
	 *            One answer to the given Question
	 */
	public Question(String question, String answer) {
		this.question = question;
		keyWords.add(answer);
	}

	/**
	 * Instantiates a new question.
	 *
	 * @param question
	 *            the question
	 * @param answers
	 *            An ArrayList of Answers
	 */
	public Question(String question, String answer, ArrayList<String> keyWords) {
		this.question = question;
		this.answer = answer;
		this.keyWords = keyWords;
	}

	public boolean checkAnswer(String answer) {
		boolean isRightAnswer = true;
		
		String[] answerWords = answer.split(" ");
		for(String s : answerWords) {
			s.toLowerCase();
		}
		
		ArrayList<String> answerWordsArrayList = (ArrayList<String>) Arrays.asList(answerWords);
		
		if (keyWords != null) {
			Iterator<String> it = keyWords.iterator();
			while (it.hasNext()) {
				if (!answerWordsArrayList.contains(it.next().toLowerCase())) {
					isRightAnswer = false;
				}
			}

		}else {
			if(!answerWordsArrayList.contains(this.answer.toLowerCase())) {
				isRightAnswer = false;
			}
		}

		return isRightAnswer;
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
	 * Gets the Keywords of the answer of the Question
	 * 
	 * @return the keyWords
	 */
	protected ArrayList<String> getKeyWords() {
		return keyWords;
	}

	protected String getAnswer() {
		return answer;
	}

}

