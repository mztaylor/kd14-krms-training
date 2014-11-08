package org.kuali.rice.krworkshop;

import java.util.ArrayList;
import java.util.List;

public enum Questionnaires {
	COMMON("Are you a returning student?",
			"Will you be living on campus?"),
	
	BIZ_RULE("What is your desired major?"),
	
	CS_RULE("Do you own a laptop?",
			"What operating systems are you familiar with?",
			"Who was the communications officer in the original Star Trek series?"),
	
	MATH_RULE("SAT Math Score?",
			"Do you have any math credits to transfer?"),
			
	MUSIC_RULE("What instrument(s) do you wish to study?",
			"How much experience playing your primary instrument?");

	private final String[] questions;

	private Questionnaires(String... questions){
		this.questions = questions;
	}	
	public String[] getQuestions(){
		return this.questions;
	}

	// Constants (these names must match created Agenda/Rule names!)
	public static final String NAMESPACE = "KRA-TRNG";
	public static final String AGENDA_NAME = "ApplicationQuestions";
	
	// Public access to questionnaires
	public static List<TrainingApplicationQuestion> getQuestions(String ruleName){
		List<TrainingApplicationQuestion> questionnaire = buildQuestionnaire(valueOf(ruleName).questions);
		return questionnaire;
	}
	
    /**
     * Helper method to convert string questions into a List <TrainingApplicationQuestion>	
     * @param questions string array containing questions
     * @return
     */
	private static List<TrainingApplicationQuestion> buildQuestionnaire(String[] questions){
		List<TrainingApplicationQuestion> questionnaire = new ArrayList <TrainingApplicationQuestion> ();
		for (String question : questions){
			TrainingApplicationQuestion q = new TrainingApplicationQuestion(question, null);
			questionnaire.add(q);
		}
		return questionnaire;
	}

	
}
