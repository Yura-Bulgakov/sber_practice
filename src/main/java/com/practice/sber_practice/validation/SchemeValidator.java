package com.practice.sber_practice.validation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Set;

public class SchemeValidator {


    public static boolean validateRequestScheme(String stringRequest) throws IOException {
        boolean result;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance( SpecVersion.VersionFlag.V4 );

        try (
                InputStream schemaStream = SchemeValidator.class.getResourceAsStream("/json/scheme/request/scheme_service_request.json")
        ){
            System.out.println("schemaStream: " + schemaStream);
            JsonNode schemaJson = objectMapper.readTree(new InputStreamReader(schemaStream, StandardCharsets.UTF_8));
            JsonSchema schema = schemaFactory.getSchema(schemaJson);

            JsonNode json = objectMapper.readTree(stringRequest);

            Set<ValidationMessage> validationMessages = schema.validate( json );

            if(validationMessages.isEmpty()){
                result = true;
                System.out.println( "There is no validation errors" );
            } else {
                result = false;
                validationMessages.forEach(vm -> System.out.println(vm.getMessage()));
            }
        }
        return result;
    }


}
