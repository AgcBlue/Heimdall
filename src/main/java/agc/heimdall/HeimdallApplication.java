package agc.heimdall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import agc.heimdall.LogIngestion.LogIngestion;

@SpringBootApplication
public class HeimdallApplication 
{

	public static void main(String[] args) 
	{
		System.out.println("MERGE");
		LogIngestion LOGGS = new LogIngestion();
		
		//SpringApplication.run(HeimdallApplication.class, args);
		//System.out.println("Hello World!");
	}

}
