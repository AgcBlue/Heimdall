package agc.heimdall;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;

import agc.heimdall.database.LogIngestionService;
import agc.heimdall.database.beans.LogEvent;
import agc.heimdall.logingestion.LogHandler;
import agc.heimdall.logingestion.LogIngestion;
import agc.heimdall.properties.*;

@SpringBootTest
class TestLogIngestion
{

    @Autowired
    private LogIngestion loggs;

    @Autowired
    LogIngestion log;

    @Autowired
    LogIngestionService logService;

	@Test
	public void mapTest(TestReporter reporter) throws Exception
    {
        List<LogConfig> logConfig = loggs.getLogConfig();

        assertEquals("/home/agc/Documents/test_log.log", logConfig.get(0).getFile());

        assertEquals("^(\\S+)\\s+(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)$", logConfig.get(0).getRegex());

        assertEquals("date ip username action status", logConfig.get(0).getHeader());
	}	

    @Test
    public void dataBaseTest(TestReporter reporter) throws Exception
    {
        log.readLog(new LogHandler() 
        {
            public void handle(LogEvent logEvent)
            {
                logService.saveLog(logEvent);
                assertEquals("192.168.1.10", logEvent.getIp());
                assertEquals("admin", logEvent.getUsername());
                assertEquals("LOGIN_SUCCESS", logEvent.getAction());
                assertEquals("OK", logEvent.getStatus());
            }
        });
    }
}


