package com.megasyariah.megasyariah.bookservice;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StrictDateDeserializer extends JsonDeserializer<Date> {

    private static final SimpleDateFormat FORMAT =
            new SimpleDateFormat("yyyy-MM-dd");

    static {
        FORMAT.setLenient(false);
    }

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {

        try {
            return FORMAT.parse(p.getText());
        } catch (Exception e) {
            throw new RuntimeException("Date must be in yyyy-MM-dd format");
        }
    }
}
