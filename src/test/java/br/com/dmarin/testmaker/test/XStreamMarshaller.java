package br.com.dmarin.testmaker.test;

import java.util.List;

import org.springframework.http.MediaType;

import br.com.dmarin.testmaker.model.QuestionGroup;

import com.google.common.base.Preconditions;
import com.thoughtworks.xstream.XStream;

public final class XStreamMarshaller implements IMarshaller {

    private XStream xstream;

    public XStreamMarshaller() {
        super();

        xstream = new XStream();
        xstream.autodetectAnnotations(true);
        xstream.processAnnotations(QuestionGroup.class);
    }

    // API

    public final <T> String encode(final T resource) {
        Preconditions.checkNotNull(resource);
        return xstream.toXML(resource);
    }

    @SuppressWarnings("unchecked")
    public final <T> T decode(final String resourceAsString, final Class<T> clazz) {
        Preconditions.checkNotNull(resourceAsString);
        return (T) xstream.fromXML(resourceAsString);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> decodeList(final String resourcesAsString, final Class<T> clazz) {
        return this.decode(resourcesAsString, List.class);
    }

    public final String getMime() {
        return MediaType.APPLICATION_XML.toString();
    }

}
