/**
 * Copyright 2005-2012 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.rice.krworkshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.web.controller.UifControllerBase;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.kuali.rice.krms.api.KrmsApiServiceLocator;
import org.kuali.rice.krms.api.engine.EngineResults;
import org.kuali.rice.krms.api.engine.ExecutionFlag;
import org.kuali.rice.krms.api.engine.ExecutionOptions;
import org.kuali.rice.krms.api.engine.Facts;
import org.kuali.rice.krms.api.engine.ResultEvent;
import org.kuali.rice.krms.api.engine.SelectionCriteria;
import org.kuali.rice.krms.framework.engine.BasicRule;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: Brian
 * Date: 10/3/12
 * Time: 10:58 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/collegeapp")
public class TrainingApplicationController extends UifControllerBase {

	@Override
	protected UifFormBase createInitialForm() {
		// Create a form and set generic, default questions
		TrainingApplicationForm form = new TrainingApplicationForm();
		form.setQuestions( Questionnaires.getCommonQuestions() );

		return form;
	}

	/**
	 * Refreshes the questionnaire portion of the student application.
	 *  
	 * @param form KualiForm backing screen data
	 * @param result binding result status
	 * @param request 
	 * @param response
	 * @return ModelAndView to redisplay the page
	 */
	@RequestMapping(params = "methodToCall=refreshQuestionnaire")
	public ModelAndView refreshQuestionnaire(@ModelAttribute("KualiForm") UifFormBase form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) {
		TrainingApplicationForm tForm = (TrainingApplicationForm) form;

		// Execute the agenda and get the results
		Map <String, Boolean> ruleResults = executeQuestionnaireAgenda(tForm);		
		
        // add questions based on rule results
        if (ruleResults.get(Questionnaires.MATH_RULE) != null && ruleResults.get(Questionnaires.MATH_RULE).booleanValue()){
        	tForm.addQuestions(Questionnaires.getMathQuestions());
        }
        if (ruleResults.get(Questionnaires.MUSIC_RULE) != null && ruleResults.get(Questionnaires.MUSIC_RULE).booleanValue()){
        	tForm.addQuestions(Questionnaires.getMusicQuestions());
        }
        if (ruleResults.get(Questionnaires.BIZ_RULE) != null && ruleResults.get(Questionnaires.BIZ_RULE).booleanValue()){
        	tForm.addQuestions(Questionnaires.getBusinessQuestions());
        }
        if (ruleResults.get(Questionnaires.CS_RULE) != null && ruleResults.get(Questionnaires.CS_RULE).booleanValue()){
        	tForm.addQuestions(Questionnaires.getComputerQuestions());
        }
        
		return getModelAndView(form);
	}
	
	/*
	 * Executes a KRMS agenda containing a set of rules related to the student application.
	 * 
	 * @return Map of rule name / result value pairs. 
	 */
	private Map <String, Boolean> executeQuestionnaireAgenda(TrainingApplicationForm form){
		// Build selection criteria used by KRMS to choose agenda and setup execution environment
	    Map<String, String> contextQualifiers = new HashMap<String, String>();
	    Map<String,String> agendaQualifiers = new HashMap<String,String>();
	    contextQualifiers.put("namespaceCode", Questionnaires.NAMESPACE);
	    agendaQualifiers.put("name", Questionnaires.AGENDA_NAME);	    
		SelectionCriteria selectionCriteria = SelectionCriteria.createCriteria(null, contextQualifiers, agendaQualifiers);

		// The Facts.Builder holds our inputs for the KRMS engine
		Facts.Builder factsBuilder = Facts.Builder.create();
		factsBuilder.addFact("college", form.getCollege());
		factsBuilder.addFact("campus", form.getCampus());	

		// Turn the KRMS logger on
		ExecutionOptions xOptions = new ExecutionOptions();
		xOptions.setFlag(ExecutionFlag.LOG_EXECUTION, true);

		// Execute the Agenda and get the results
		EngineResults results = KrmsApiServiceLocator.getEngine().execute(selectionCriteria, factsBuilder.build(), xOptions);

		// Pull out the individual rule results from the EngineResults collection object
        Map <String, Boolean> ruleResults = new HashMap<String, Boolean>();		
        if (results.getResultsOfType(ResultEvent.RULE_EVALUATED) != null && results.getResultsOfType(ResultEvent.RULE_EVALUATED).size() > 0) {
            for (ResultEvent resultEvent : results.getResultsOfType(ResultEvent.RULE_EVALUATED)) {
            	
                String ruleName = ((BasicRule)resultEvent.getSource()).getName();
                ruleResults.put(ruleName, resultEvent.getResult());
            }
        }
        
        return ruleResults;
	}
	
/*    public ModelAndView submit(@ModelAttribute("KualiForm") UifFormBase uifForm, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) {
        //set View to readOnly
        uifForm.getView().setReadOnly(true);
        //Put a message in the MessageMap to display on the page
        GlobalVariables.getMessageMap().putInfo("Training-CollegeApplicationPage", "message.route.successful");

        return getUIFModelAndView(uifForm);
    }*/
}
