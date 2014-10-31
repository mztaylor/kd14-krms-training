package org.kuali.rice.krworkshop.rules;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.core.api.exception.RiceRuntimeException;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.engine.RouteContext;
import org.kuali.rice.kew.framework.support.krms.RulesEngineExecutor;
import org.kuali.rice.krad.maintenance.MaintenanceDocumentBase;
import org.kuali.rice.krad.service.KRADServiceLocator;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krms.api.engine.Engine;
import org.kuali.rice.krms.api.engine.EngineResults;
import org.kuali.rice.krms.api.engine.Facts;
import org.kuali.rice.krms.api.engine.SelectionCriteria;
import org.kuali.rice.krworkshop.TrainingApplication;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * RulesEngineExecutor to process NCAA rules
 */
public class ApplicationRulesEngineExecutor implements RulesEngineExecutor {

    private static final String CONTEXT_NAMESPACE_CODE = "KRA-TRNG";
    private static final String CONTEXT_NAME = "StudentApplication";
    private static final String AGENDA_NAME = "Student Application";

    @Override
    public EngineResults execute(RouteContext routeContext, Engine engine) {
        Map<String, String> contextQualifiers = new HashMap<String, String>();
        contextQualifiers.put("namespaceCode", CONTEXT_NAMESPACE_CODE);
        contextQualifiers.put("name", CONTEXT_NAME);

        SelectionCriteria sectionCriteria = SelectionCriteria.createCriteria(null, contextQualifiers, Collections.<String,String>singletonMap("name", AGENDA_NAME));

        // extract facts from routeContext

        Facts.Builder factsBuilder = Facts.Builder.create();

        try {
            MaintenanceDocumentBase maintDoc = (MaintenanceDocumentBase) KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderId(routeContext.getDocument().getDocumentId());
            String maintDocContent = maintDoc.getXmlDocumentContents();

            if (StringUtils.isNotEmpty(maintDocContent)) {
                factsBuilder.addFact("maintDocContent", maintDocContent);
            }

        } catch (WorkflowException e) {
            throw new RiceRuntimeException(e);
        }

        return engine.execute(sectionCriteria, factsBuilder.build(), null);
    }
}
