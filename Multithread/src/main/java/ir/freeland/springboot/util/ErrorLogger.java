package ir.freeland.springboot.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ErrorLogger {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static synchronized void logError(String fileName, long recordNumber, String errorCode, String errorDescription) {

        Map<String, Object> errorRecord = new HashMap<>();
        errorRecord.put("FILE_NAME", fileName);
        errorRecord.put("RECORD_NUMBER", recordNumber);
        errorRecord.put("ERROR_CODE", errorCode);
        errorRecord.put("ERROR_DESCRIPTION", errorDescription);
        errorRecord.put("ERROR_DATE", new Date());

        try (FileWriter file = new FileWriter("error.json", true)) {
            objectMapper.writeValue(file, errorRecord);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


