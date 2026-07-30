package agc.heimdall.database;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import agc.heimdall.logingestion.LogIngestion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import agc.heimdall.database.beans.LogEvent;
import agc.heimdall.database.repos.LogEventRepository;

@Service
public class LogIngestionService 
{
    private final LogEventRepository repository;

    private static final Logger logger = LogManager.getLogger(LogIngestion.class);

    public LogIngestionService(LogEventRepository repository) 
    {
        this.repository = repository;
    }

    public LogEvent saveLog(LogEvent logEvent) 
    {
        return repository.save(logEvent); 
    }

    public java.util.List<LogEvent> getAllLogs() 
    {
        return repository.findAll(); 
    }
}