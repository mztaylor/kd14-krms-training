package org.kuali.rice.krworkshop.rules;

import org.kuali.rice.core.api.criteria.Transform;
import org.kuali.rice.core.api.exception.RiceRuntimeException;
import org.kuali.rice.core.api.util.xml.XmlHelper;
import org.kuali.rice.kew.rule.xmlrouting.XPathHelper;
import org.kuali.rice.krms.api.engine.TermResolutionException;
import org.kuali.rice.krms.api.engine.TermResolver;
import org.w3c.dom.Document;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A term resolver that retrieves its value via XPath from a prerequisite term containing XML content
 */
public class XPathTermResolver<T> implements TermResolver<T> {

    private final String termName;
    private final String xmlSourceTermName;
    private final String elementXPath;
    private final Transform<String, T> typeTransform;

    private static final Map<Class<?>, Transform> transformMap = new HashMap<Class<?>, Transform>();

    static {
        transformMap.put(Boolean.class, new Transform<String, Boolean>() {
            @Override
            public Boolean apply(String s) {
                return Boolean.valueOf(s);
            }
        });

        transformMap.put(String.class, new Transform<String, String>() {
            @Override
            public String apply(String s) {
                return s;
            }
        });
    }

    /**
     * Constructs an XPathTermResolver
     *
     * @param termName the name of the term that this resolves
     * @param xmlSourceTermName the term with the XML data that the XPath will be applied to
     * @param elementXPath the XPath expression that will resolve to the text value
     * @param outputType the type to convert the text to before returning it as teh term value
     */
    public XPathTermResolver(String termName, String xmlSourceTermName, String elementXPath, Class<T> outputType) {
        this.termName = termName;
        this.xmlSourceTermName = xmlSourceTermName;
        this.elementXPath = elementXPath;

        this.typeTransform = transformMap.get(outputType);

        if (this.typeTransform == null) {
            throw new RiceRuntimeException(outputType.getSimpleName() + " is not supported by " + getClass().getSimpleName());
        }
    }

    @Override
    public Set<String> getPrerequisites() {
        return Collections.singleton(xmlSourceTermName);
    }

    @Override
    public String getOutput() {
        return termName;
    }

    @Override
    public Set<String> getParameterNames() {
        return Collections.<String>emptySet();
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public T resolve(Map<String, Object> resolvedPrereqs, Map<String, String> parameters) throws TermResolutionException {
        String xml = resolvedPrereqs.get(xmlSourceTermName).toString();

        String xpathResult = getElementValue(xml, elementXPath);

        T result = typeTransform.apply(xpathResult);

        return result;
    }

    private String getElementValue(String docContent, String xpathExpression) {
        try {
            Document document = XmlHelper.trimXml(new ByteArrayInputStream(docContent.getBytes()));

            XPath xpath = XPathHelper.newXPath();
            String value = (String)xpath.evaluate(xpathExpression, document, XPathConstants.STRING);

            return value;

        } catch (Exception e) {
            throw new RiceRuntimeException();
        }
    }

}
