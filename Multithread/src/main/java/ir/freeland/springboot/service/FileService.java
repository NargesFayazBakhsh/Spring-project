package ir.freeland.springboot.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class FileService {
	
    public List<String[]> readCsvFile(String filePath, int startLine, int chunkSize) throws IOException, CsvException {
        List<String[]> chunk = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> allLines = reader.readAll();
            // Ensure that startLine and chunkSize are within bounds
            int endLine = Math.min(startLine + chunkSize, allLines.size());
            for (int i = startLine; i < endLine; i++) {
                chunk.add(allLines.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error reading CSV file");
        }
        return chunk;
    }


}
