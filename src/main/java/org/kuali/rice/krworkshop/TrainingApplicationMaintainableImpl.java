/**
 * Copyright 2005-2013 The Kuali Foundation
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

import org.kuali.rice.krad.maintenance.MaintainableImpl;
import org.kuali.rice.krad.uif.component.Component;
import org.kuali.rice.krad.uif.util.ProcessLogger;
import org.kuali.rice.krad.uif.view.View;
import org.kuali.rice.krad.uif.view.ViewModel;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.kuali.rice.krms.api.engine.EngineResults;
import org.kuali.rice.krms.api.engine.ResultEvent;
import org.kuali.rice.krms.api.engine.Term;
import org.kuali.rice.krms.framework.engine.Proposition;
import org.kuali.rice.krworkshop.krms.KRMSEvaluator;
import org.kuali.rice.krworkshop.krms.RulesExecutionConstants;
import org.kuali.rice.krworkshop.krms.TestScoreTermResolver;
import org.kuali.rice.krms.framework.engine.Agenda;
import org.kuali.rice.krms.framework.engine.AgendaTreeEntry;
import org.kuali.rice.krms.framework.engine.BasicAgenda;
import org.kuali.rice.krms.framework.engine.BasicAgendaTree;
import org.kuali.rice.krms.framework.engine.BasicAgendaTreeEntry;
import org.kuali.rice.krms.framework.engine.ComparableTermBasedProposition;
import org.kuali.rice.krms.framework.engine.expression.ComparisonOperator;
import org.kuali.rice.krms.api.repository.RuleManagementService;
import org.kuali.rice.krms.api.repository.agenda.AgendaDefinition;
import org.kuali.rice.krms.framework.engine.Action;
import org.kuali.rice.krms.framework.engine.Agenda;
import org.kuali.rice.krms.api.engine.Term;
import org.kuali.rice.krms.api.engine.TermResolver;
import org.kuali.rice.krms.framework.engine.Rule;
import org.kuali.rice.krms.framework.engine.BasicRule;
import org.kuali.rice.krms.impl.repository.KrmsRepositoryServiceLocator;
import org.kuali.rice.krms.impl.util.KrmsServiceLocatorInternal;

import com.sun.tools.jdi.LinkedHashMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class TrainingApplicationMaintainableImpl extends MaintainableImpl {

    /** KRMS Evaluator **/
    private KRMSEvaluator krmsEvaluator = new KRMSEvaluator();
    private RuleManagementService ruleManagementService;
    //private static final Term testScoreTerm = new Term("testScore");
	

	

    
	/** TODO: review class and determine needed changes
      public void performApplyModel(View view, Object model) {
     
        ProcessLogger.trace("apply-model:" + view.getId());

        // get action flag and edit modes from authorizer/presentation controller
        retrieveEditModesAndActionFlags(view, (UifFormBase) model);

        // set view context for conditional expressions
        setViewContext(view, model);

        ProcessLogger.trace("apply-comp-model:" + view.getId());
        Map<String, Integer> visitedIds = new HashMap<String, Integer>();
        performComponentApplyModel(view, view, model, visitedIds);
        ProcessLogger.trace("apply-model-end:" + view.getId());

        // apply default values if they have not been applied yet
        if (!((ViewModel) model).isDefaultsApplied()) {
            applyDefaultValues(view, view, model);
            ((ViewModel) model).setDefaultsApplied(true);
        }
    }
    **/
	
	public String evaluateApplicationStatus(
			TrainingApplication trainingApplication) {
//		ComparisonOperator operatorGreaterThan = ComparisonOperator.GREATER_THAN;
//		Proposition Prop = new ComparableTermBasedProposition(
//				operatorGreaterThan, testScoreTerm, Integer.valueOf(0));
//		Rule rule = new BasicRule("r1", Prop, null);
//
//		BasicAgendaTree agendaTree = new BasicAgendaTree(
//				new BasicAgendaTreeEntry(rule));
//		Agenda agenda = new BasicAgenda(Collections.singletonMap(
//				AgendaDefinition.Constants.EVENT, "Test Score Agenda"),
//				agendaTree);
		
		AgendaDefinition agendaDefinition = this.getRuleManagementService().getAgendaByNameAndContextId(RulesExecutionConstants.TEST_SCORE_AGENDA, RulesExecutionConstants.CONTEXT_1);

		Agenda agenda = KrmsRepositoryServiceLocator.getKrmsRepositoryToEngineTranslator().translateAgendaDefinition(agendaDefinition);

		Map<String, Object> executionFacts = new LinkedHashMap();
		executionFacts.put(RulesExecutionConstants.TEST_SCORE, trainingApplication.getTestScore());
		Map<String, String> agendaQualifiers = Collections.emptyMap();

		EngineResults engineResults = this.krmsEvaluator.evaluateAgenda(agenda,
				executionFacts, agendaQualifiers);
		List<ResultEvent> events = engineResults
				.getResultsOfType(ResultEvent.RULE_EVALUATED);//ResultEvent.PROPOSITION_EVALUATED
		boolean result = false;
		for (ResultEvent e : events) {
			result = e.getResult();
		}
		if (result) {

			return "Success - Test Score received";

		}

		else {

			return "unsuccessful - Test score not received";

		}
	}
    
    public RuleManagementService getRuleManagementService() {
        if (ruleManagementService == null) {
            ruleManagementService = KrmsServiceLocatorInternal.getService(
                    "ruleManagementService");
        }
        return ruleManagementService;
    }  
}
