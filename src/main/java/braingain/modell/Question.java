package braingain.modell;

import java.util.ArrayList;

public class Question {
	
	private String question;
	private ArrayList<String> answers = new ArrayList<String>();
	
	public Question(String question, String answer) {
		this.question = question;
		answers.add(answer);
	}
	
	public Question(String question, String[] answers) {
		this.question = question;
		for(int i = 0; i < answers.length; i++) {
			this.answers.add(answers[i]);
		}
	}
	
	public Question(String question, ArrayList<String> answers) {
		this.question = question;
		this.answers = answers;
	}
	
	public String getQuestion() {
		return this.question;
	}
	
	public ArrayList<String> getAnswers(){
		return this.answers;
	}
	
	public String getOneAnswer() {
		return answers.get(0);
	}
	
}
