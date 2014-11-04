/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.rice.krworkshop.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.rice.krworkshop.Questionnaires;
import org.kuali.rice.krworkshop.TrainingApplicationForm;
import org.kuali.rice.krworkshop.service.KrmsRulesExecutionService;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.krms.api.KrmsApiServiceLocator;
import org.kuali.rice.krms.api.engine.*;
import org.kuali.rice.krms.api.repository.rule.RuleDefinition;
import org.kuali.rice.krms.framework.engine.BasicRule;
import org.kuali.rice.krms.framework.type.ValidationActionTypeService;

import java.util.*;

public class KrmsRulesExecutionServiceImpl implements KrmsRulesExecutionService {
    
    protected final Log LOG = LogFactory.getLog(KrmsRulesExecutionServiceImpl.class);

	/*
	 * Executes a KRMS agenda containing a set of rules related to the student application.
	 * 
	 * @return Map of rule name / result value pairs. 
	 */
	public Map <String, Boolean> executeAgenda(String agendaName, Facts.Builder facts){
		// Build selection criteria used by KRMS to choose agenda and setup execution environment
	    Map<String, String> contextQualifiers = new HashMap<String, String>();
	    Map<String,String> agendaQualifiers = new HashMap<String,String>();
	    contextQualifiers.put("namespaceCode", Questionnaires.NAMESPACE);
	    agendaQualifiers.put("name", agendaName);	    
		SelectionCriteria selectionCriteria = SelectionCriteria.createCriteria(null, contextQualifiers, agendaQualifiers);

		// Turn the KRMS logger on
		ExecutionOptions xOptions = new ExecutionOptions();
		xOptions.setFlag(ExecutionFlag.LOG_EXECUTION, true);

		// Execute the Agenda and get the results
		EngineResults results = KrmsApiServiceLocator.getEngine().execute(selectionCriteria, facts.build(), xOptions);

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
}
