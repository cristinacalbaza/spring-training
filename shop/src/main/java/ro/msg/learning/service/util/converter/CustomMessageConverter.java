package ro.msg.learning.service.util.converter;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class CustomMessageConverter extends AbstractGenericHttpMessageConverter {

    private final CSVMessageConverter csvMessageConverter;

    public CustomMessageConverter(){
        super(new MediaType("text", "csv"));

        this.csvMessageConverter = new CSVMessageConverter<>();
    }

    @Override
    protected void writeInternal(Object o, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        List<Object> arrayList;

        if (o instanceof List)
            arrayList = new ArrayList<>((ArrayList<Object>) o);
        else {
            arrayList = Collections.singletonList(o);
        }

        csvMessageConverter.toCsv(type.getClass(), arrayList, outputMessage.getBody());
    }

    @Override
    protected Object readInternal(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return csvMessageConverter.fromCsv(clazz, inputMessage.getBody());
    }

    @Override
    public Object read(Type type, Class contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return readInternal(contextClass, inputMessage);
    }
}
