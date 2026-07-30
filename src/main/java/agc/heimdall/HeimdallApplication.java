package agc.heimdall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import agc.heimdall.logingestion.LogHandler;
import agc.heimdall.logingestion.LogIngestion;
import agc.heimdall.database.repos.LogEventRepository;

import agc.heimdall.database.LogIngestionService;
import agc.heimdall.database.beans.LogEvent;

@SpringBootApplication
public class HeimdallApplication 
{
	public static void main(String[] args) throws Exception
	{
		System.out.println("MERGE");
		
		ConfigurableApplicationContext context = SpringApplication.run(HeimdallApplication.class, args);
		LogIngestion log = context.getBean(LogIngestion.class);
		LogEventRepository logService = context.getBean(LogEventRepository.class);

		System.out.println("HELLO NEW YORK");
	}
}
