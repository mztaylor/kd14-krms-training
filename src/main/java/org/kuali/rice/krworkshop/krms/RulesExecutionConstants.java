package org.kuali.rice.krworkshop.krms;

import org.kuali.rice.krms.api.engine.Term;

@SuppressWarnings("unused")
public class RulesExecutionConstants {

    private RulesExecutionConstants() {}
    /**
     * Rule execution related Term's  
     */

    public static final Term TEST_SCORE_TERM = new Term("testScore");
   

    /**
     * properties
     */

    // these are used as qualifiers for selecting agenda!?!? 
    public static final String DOCTYPE_CONTEXT_QUALIFIER = "docTypeName";
    public static final String STUDENT_ELIGIBILITY_DOCTYPE = "Student.Eligibility";
    public static final String TEST_SCORE_AGENDA= "Test Score Agenda";
    public static final String CONTEXT_1= "CONTEXT1";
    public static final String TEST_SCORE= "testScore";

}

