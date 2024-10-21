package ir.freeland.springboot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ir.freeland.springboot.service.CsvProcessor;


@SpringBootApplication
public class MultithreadApplication implements CommandLineRunner{
	
	@Autowired
	private CsvProcessor csv;

	public static void main(String[] args) {
		SpringApplication.run(MultithreadApplication.class, args);

	}
	@Override
	public void run(String...args) throws Exception{
		String accuntsFilePath = "path/to/account.csv";
		String customersFilePath = "path/to/costomer.csv";
		System.out.println("csv processing is started");
		
		csv.processCsvInChunksAc(accuntsFilePath, 1000 );
		csv.processCsvInChunksCu(customersFilePath, 500 );
		
		System.out.println("csv processing is complet");
	}
}
