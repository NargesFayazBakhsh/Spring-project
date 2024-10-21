package ir.freeland.springboot.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.hibernate.tool.schema.internal.IndividuallySchemaValidatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opencsv.exceptions.CsvException;

import ir.freeland.springboot.validator.AcccountValidator;
import ir.freeland.springboot.validator.CustomerValidator;

@Component 
public class CsvProcessor {
	private final FileService fileService;

    @Autowired
    public CsvProcessor(FileService fileService) {
        this.fileService = fileService;
    }
    public void processCsvInChunksAc(String filePath, int chunkSize ) {
        try {
            // First, read the file to determine the total number of rows
            List<String[]> allRows = fileService.readCsvFile(filePath, 0, Integer.MAX_VALUE);
            int totalRows = allRows.size();
            // Define the number of threads
            int nThreads = 4;  // Or adjust based on your system capabilities
            ExecutorService executorService = Executors.newFixedThreadPool(nThreads);

            // Process data in chunks
            for (int startLine = 0; startLine < totalRows; startLine += chunkSize) {
                int finalStartLine = startLine;
                executorService.submit(() -> {
                    try {
                        // Get the chunk for this thread
                        List<String[]> chunk = fileService.readCsvFile(filePath, finalStartLine, chunkSize);
                        // Process the chunk
                        processChunkAc(chunk);
                    } catch (IOException | CsvException e) {
                        e.printStackTrace();
                    }
                });
            }

            // Shutdown the executor and await termination
            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.HOURS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
    	
        public void processCsvInChunksCu(String filePath, int chunkSize ) {    	
            try {
                // First, read the file to determine the total number of rows
                List<String[]> allRows = fileService.readCsvFile(filePath, 0, Integer.MAX_VALUE);
                int totalRows = allRows.size();
                // Define the number of threads
                int nThreads = 4;  // Or adjust based on your system capabilities
                ExecutorService executorService = Executors.newFixedThreadPool(nThreads);

                // Process data in chunks
                for (int startLine = 0; startLine < totalRows; startLine += chunkSize) {
                    int finalStartLine = startLine;
                    executorService.submit(() -> {
                        try {
                            // Get the chunk for this thread
                            List<String[]> chunk = fileService.readCsvFile(filePath, finalStartLine, chunkSize);
                            // Process the chunk
                            processChunkCu(chunk);
                        } catch (IOException | CsvException e) {
                            e.printStackTrace();
                        }
                    });
                }

                // Shutdown the executor and await termination
                executorService.shutdown();
                executorService.awaitTermination(1, TimeUnit.HOURS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        	
    }

    private void processChunkAc(List<String[]> chunk) {
        // Implement your logic for processing each chunk
        for (String[] row : chunk) {
        	if(AcccountValidator.isValid(row))
        		saveToDatabase(row);
        	else
        		writeToErrorFile(row);
        }
    }
    
    private void processChunkCu(List<String[]> chunk) {
        // Implement your logic for processing each chunk
        for (String[] row : chunk) {
        	if(CustomerValidator.isValid(row))
        		saveToDatabase(row);
        	else
        		writeToErrorFile(row);
        }
    }
    
    private void saveToDatabase(String[] row) {
    	
    }

    private void writeToErrorFile(String[] row) {
    	
    }
    
}
