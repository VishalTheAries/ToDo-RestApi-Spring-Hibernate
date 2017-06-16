package com.todo.serializer;


import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.time.LocalDate;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.time.format.DateTimeFormatter;
import java.io.IOException;

public class LocalDateSerializer extends StdSerializer<LocalDate>{
    public LocalDateSerializer() {
        super(LocalDate.class);
    }
    
    @Override
    public void serialize(LocalDate value, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeString(value.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }
    
}
