package org.kuali.rice.krworkshop.rules;

import org.kuali.rice.krms.api.engine.TermResolver;
import org.kuali.rice.krms.api.repository.term.TermResolverDefinition;
import org.kuali.rice.krms.framework.type.TermResolverTypeService;

import java.util.HashMap;
import java.util.Map;

/**
 * Type Service for Term Resolvers.  This simple implementation contains a statically defined registry.
 */
public class  ApplicationTermResolverTypeService implements TermResolverTypeService {

    private static final Map<String,TermResolver> termResolvers = new HashMap<String, TermResolver>();

    private static final String NCAA_PARTICIPATION_TERM_RESOLVER = "ncaaParticipationTermResolver";
    private static final String STUDENT_COUNTRY_CODE_TERM_RESOLVER = "studentCountryCodeTermResolver";

    private static final String XML_TERM = "maintDocContent";

    static {
        termResolvers.put(
                NCAA_PARTICIPATION_TERM_RESOLVER,
                new XPathTermResolver("ncaaParticipation", XML_TERM, "//newMaintainableObject//ncaaParticipation/text()", Boolean.class)
        );
        termResolvers.put(
                STUDENT_COUNTRY_CODE_TERM_RESOLVER,
                new XPathTermResolver("studentCountryCode", XML_TERM, "//newMaintainableObject//country/text()", String.class)
        );
    }

    @Override
    public TermResolver<?> loadTermResolver(TermResolverDefinition termResolverDefinition) {
        String termResolverName = termResolverDefinition.getName();

        TermResolver<?> result = termResolvers.get(termResolverName);

        return result;
    }
}
