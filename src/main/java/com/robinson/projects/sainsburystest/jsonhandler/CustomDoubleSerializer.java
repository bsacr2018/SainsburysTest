package com.robinson.projects.sainsburystest.jsonhandler;

import java.io.IOException;
import java.util.Formatter;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomDoubleSerializer extends JsonSerializer<Double> {
    @Override
    public void serialize(Double value, JsonGenerator jgen, SerializerProvider provider) 
    		throws IOException, JsonGenerationException {
        if (null == value) {
            jgen.writeNull();
        } else {
        	String truncatedString = truncateDouble(value, 2);        
            jgen.writeNumber(truncatedString);
        }
    }
    
	public static String truncateDouble(double value, int precision) {
    	StringBuilder sb = new StringBuilder();
    	Formatter formatter = new Formatter(sb);
    	formatter.format("%."+precision+"f", value);
    	String truncatedString = new String(sb.toString());
    	formatter.close();
    	return truncatedString;
	}
}
