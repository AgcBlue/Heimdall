package agc.heimdall.database;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import agc.heimdall.LogIngestion.LogIngestion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import agc.heimdall.database.log_events.LogEvent;
import agc.heimdall.database.log_events.LogEventRepository;

@Service
public class LogIngestionService 
{
    @Autowired
    private LogEventRepository repository;

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