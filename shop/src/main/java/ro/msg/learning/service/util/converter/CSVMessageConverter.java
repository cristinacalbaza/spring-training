package ro.msg.learning.service.util.converter;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class CSVMessageConverter<T> {

    private CsvMapper mapper = new CsvMapper();
    private CsvSchema schema;

    public List<T> fromCsv(Class<T> tClass, InputStream inputStream) throws IOException {
        schema = mapper.schemaFor(tClass).withHeader();
        MappingIterator<T> iterator = mapper.readerFor(tClass).with(schema).readValues(inputStream);

        return iterator.readAll();
    }

    public void toCsv(Class<T> tClass, List<T> list, OutputStream outputStream) throws IOException {
        schema = mapper.schemaFor(tClass).withHeader();
        ObjectWriter writer = mapper.writer(schema.withLineSeparator("\n"));
        writer.writeValue(outputStream, list);
    }
}
