package org.kuali.rice.krworkshop.rules;

import org.kuali.rice.core.api.exception.RiceRuntimeException;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.engine.RouteContext;
import org.kuali.rice.kew.framework.support.krms.RulesEngineExecutor;
import org.kuali.rice.krad.maintenance.MaintenanceDocumentBase;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krms.api.engine.Engine;
import org.kuali.rice.krms.api.engine.EngineResults;
import org.kuali.rice.krms.api.engine.Facts;
import org.kuali.rice.krms.api.engine.SelectionCriteria;

import java.util.HashMap;
import java.util.Map;

/**
 * RulesEngineExecutor to process application rules related to routing
 */
public class ApplicationRulesEngineExecutor implements RulesEngineExecutor {

    // qualifier keys:
    private static final String NAMESPACE_CODE = "namespaceCode";
    private static final String NAME = "name";

    private static final String CONTEXT_NAMESPACE_CODE = "KRA-TRNG";
    private static final String CONTEXT_NAME = "StudentApplicationWorkflow";
    private static final String AGENDA_NAME = "Student Application Workflow";

    private static final String XML_TERM_NAME = "maintDocContent";

    @Override
    public EngineResults execute(RouteContext routeContext, Engine engine) {
        System.err.println("!!!!! calling " + this.getClass().getSimpleName() + ".execute() !!!!!");

        // TODO: select the appropriate Context and Agenda

        Map<String, String> contextQualifiers = new HashMap<String, String>();
        Map<String, String> agendaQualifiers = new HashMap<String, String>();

        SelectionCriteria selectionCriteria = SelectionCriteria.createCriteria(null, contextQualifiers, agendaQualifiers);

        // build prerequisite facts
        Facts.Builder factsBuilder = Facts.Builder.create();

        // TODO: Add a fact to the Facts.Builder

        // TODO: return the results of engine.execute(...) instead
        return null;
    }

    private String getMaintenanceDocumentXml(RouteContext routeContext) {
        String maintDocContent = null;

        try {
            MaintenanceDocumentBase maintDoc = (MaintenanceDocumentBase) KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderId(routeContext.getDocument().getDocumentId());
            maintDocContent = maintDoc.getXmlDocumentContents();
        } catch (WorkflowException e) {
            throw new RiceRuntimeException(e);
        }

        return maintDocContent;
    }
}
