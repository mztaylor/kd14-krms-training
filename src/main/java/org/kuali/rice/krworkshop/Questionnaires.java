package org.kuali.rice.krworkshop;

import java.util.ArrayList;
import java.util.List;

public class Questionnaires {
	// Constants (these names must match created Agenda/Rule names!)
	public static final String NAMESPACE = "KRA-TRNG";
	public static final String AGENDA_NAME = "ApplicationQuestions";

	public static final String MATH_RULE = "Math Rule";
	public static final String BIZ_RULE = "Business Rule";
	public static final String CS_RULE = "Computer Science Rule";
	public static final String MUSIC_RULE = "Music Rule";

	// Public accessors to questionnaires
	public static List<TrainingApplicationQuestion> getCommonQuestions(){
		return buildQuestionnaire(commonQuestions);		
	}
	
	public static List<TrainingApplicationQuestion> getBusinessQuestions(){
		return buildQuestionnaire(businessQuestions);
	}
	
	public static List<TrainingApplicationQuestion> getMathQuestions(){
		return buildQuestionnaire(mathQuestions);
	}
	
	public static List<TrainingApplicationQuestion> getMusicQuestions(){
		return buildQuestionnaire(musicQuestions);
	}
	
	public static List<TrainingApplicationQuestion> getComputerQuestions(){
		return buildQuestionnaire(computerQuestions);
	}
	
	
	// private question strings
	private static final String[] commonQuestions = {
		"Are you a returning student?",
		"Will you be living on campus?"
	};
	private static final String[] businessQuestions = {
		"What is your desired major?"
	};
	
	private static final String[] mathQuestions = {
		"SAT Math Score?",
		"Do you have any math credits to transfer?"
	};
	
	private static final String[] musicQuestions = {
		"What instrument(s) do you wish to study?",
		"How much experience playing your primary instrument?"
	};

	private static final String[] computerQuestions = {
		"Do you own a laptop?",
		"What operating systems are you familiar with?",
		"Who was the communications officer in the original Star Trek series?"
	};

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
