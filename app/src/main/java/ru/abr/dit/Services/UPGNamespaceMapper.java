package ru.abr.dit.Services;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;
import org.springframework.stereotype.Service;

@Service
public class UPGNamespaceMapper extends NamespacePrefixMapper {

    private static final String UPG_PREFIX = "upg"; // DEFAULT NAMESPACE
    private static final String UPG_URI = "http://bssys.com/upg/request";

    private static final String XSI_PREFIX = "xsi";
    private static final String XSI_URI = "http://www.w3.org/2001/XMLSchema-instance";

    @Override
    public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
        if(UPG_URI.equals(namespaceUri)) {
            return UPG_PREFIX;
        } else if(XSI_URI.equals(namespaceUri)) {
            return XSI_PREFIX;
        }
        return suggestion;
    }

    @Override
    public String[] getPreDeclaredNamespaceUris() {
        return new String[] { UPG_URI, XSI_URI };
    }
}
