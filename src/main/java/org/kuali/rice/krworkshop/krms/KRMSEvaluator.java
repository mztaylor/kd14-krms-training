/**
 */
package org.kuali.rice.krworkshop.krms;

import org.joda.time.DateTime;
import org.kuali.rice.krms.api.engine.Engine;
import org.kuali.rice.krms.api.engine.EngineResults;
import org.kuali.rice.krms.api.engine.ExecutionEnvironment;
import org.kuali.rice.krms.api.engine.ExecutionFlag;
import org.kuali.rice.krms.api.engine.ExecutionOptions;
import org.kuali.rice.krms.api.engine.ResultEvent;
import org.kuali.rice.krms.api.engine.SelectionCriteria;
import org.kuali.rice.krms.api.engine.TermResolver;
import org.kuali.rice.krms.framework.engine.Action;
import org.kuali.rice.krms.framework.engine.Agenda;
import org.kuali.rice.krms.framework.engine.AgendaTreeEntry;
import org.kuali.rice.krms.framework.engine.BasicAgenda;
import org.kuali.rice.krms.framework.engine.BasicAgendaTree;
import org.kuali.rice.krms.framework.engine.BasicAgendaTreeEntry;
import org.kuali.rice.krms.framework.engine.BasicContext;
import org.kuali.rice.krms.framework.engine.BasicRule;
import org.kuali.rice.krms.framework.engine.Context;
import org.kuali.rice.krms.framework.engine.ContextProvider;
import org.kuali.rice.krms.framework.engine.Proposition;
import org.kuali.rice.krms.framework.engine.PropositionResult;
import org.kuali.rice.krms.framework.engine.ProviderBasedEngine;
import org.kuali.rice.krms.framework.engine.result.BasicResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class  KRMSEvaluator {

	public static final String EXEMPTION_WAS_USED_MESSAGE_SUFFIX = " (exemption applied)";

    private List<TermResolver<?>> termResolvers;
    private static final TermResolver<Integer> testResolver = new TestScoreTermResolver<Integer>(RulesExecutionConstants.TEST_SCORE_TERM.getName(), 1);
    private ExecutionOptions executionOptions;

    private Map<String, String> contextQualifiers = Collections.singletonMap(RulesExecutionConstants.DOCTYPE_CONTEXT_QUALIFIER, RulesExecutionConstants.STUDENT_ELIGIBILITY_DOCTYPE);
    
    
    public KRMSEvaluator(){
    	
    }


    /**
     * execute the engine against a trivial context containing the given proposition.
     *
     * @param agenda
     * @param executionFacts
     * @return
     */
    public EngineResults evaluateAgenda(Agenda agenda, Map<String, Object> executionFacts, Map<String, String> agendaQualifiers) {
    	
    	List<TermResolver<?>> testResolvers = new ArrayList<TermResolver<?>>();
		testResolvers.add(testResolver);

        Context context = new BasicContext(Arrays.asList(agenda), testResolvers);
        ContextProvider contextProvider = new ManualContextProvider(context);
    
        ProviderBasedEngine engine = new ProviderBasedEngine();
        engine.setContextProvider(contextProvider);

        SelectionCriteria selectionCriteria = SelectionCriteria.createCriteria(new DateTime(), contextQualifiers, agendaQualifiers);

        return engine.execute(selectionCriteria, executionFacts, this.getExecutionOptions());
    }



    public void setTermResolvers(List<TermResolver<?>> termResolvers) {
        this.termResolvers = termResolvers;
    }

    public List<TermResolver<?>> getTermResolvers(){
        return termResolvers;
    }

    public void setExecutionOptions(ExecutionOptions executionOptions) {
        this.executionOptions = executionOptions;
    }

    private ExecutionOptions getExecutionOptions() {
        if(executionOptions == null) {
            executionOptions = new ExecutionOptions();
            executionOptions.setFlag(ExecutionFlag.LOG_EXECUTION, true);
            executionOptions.setFlag(ExecutionFlag.EVALUATE_ALL_PROPOSITIONS, true);
        }
        return executionOptions;
    }
}
