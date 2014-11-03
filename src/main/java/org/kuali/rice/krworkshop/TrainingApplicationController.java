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
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kuali.rice.krworkshop.service.KrmsRulesExecutionService;
import org.kuali.rice.krworkshop.service.impl.KrmsRulesExecutionServiceImpl;
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
    private KrmsRulesExecutionService krmsRulesExecutionService;
    
	@Override
	protected UifFormBase createInitialForm() {
		// Create a form and set generic, default questions
		TrainingApplicationForm form = new TrainingApplicationForm();
		form.setQuestions( Questionnaires.getQuestions(Questionnaires.COMMON.name()) );

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

		// Build up our facts
		Facts.Builder factsBuilder = Facts.Builder.create();
		factsBuilder.addFact("college", tForm.getCollege());
		factsBuilder.addFact("campus", tForm.getCampus());
		
		// Execute the agenda and get the results
		Map <String, Boolean> ruleResults = getKrmsRulesExecutionService().executeAgenda(Questionnaires.AGENDA_NAME, factsBuilder);	
		
        // add questions based on rule results
		for (Map.Entry<String, Boolean> entry : ruleResults.entrySet()){
			if (entry.getValue().booleanValue() == true){
				tForm.addQuestions(Questionnaires.getQuestions(entry.getKey()));
			}
		}
        
		return getModelAndView(form);
	}
	
    public KrmsRulesExecutionService getKrmsRulesExecutionService() {
    	if (krmsRulesExecutionService == null){
    		krmsRulesExecutionService = new KrmsRulesExecutionServiceImpl();
    	}
        return krmsRulesExecutionService;
    }

    public void setKrmsRulesExecutionService(KrmsRulesExecutionService krmsRulesExecutionService) {
        this.krmsRulesExecutionService = krmsRulesExecutionService;
    }
}
