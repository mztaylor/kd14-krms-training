package org.kuali.rice.krworkshop.service;

import java.util.List;
import java.util.Map;

import org.kuali.rice.krms.api.engine.Facts;
import org.kuali.rice.krworkshop.TrainingApplicationForm;


public interface KrmsRulesExecutionService {
   
	/*
	 * Executes a KRMS agenda containing a set of rules related to the student application.
	 * 
	 * @return Map of rule name / result value pairs. 
	 */
	public Map <String, Boolean> executeAgenda(String agendaName, Facts.Builder facts);

}
