package agc.heimdall.LogIngestion;

import java.beans.ConstructorProperties;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import agc.heimdall.properties.*;
import agc.heimdall.database.log_events.LogEvent;
import agc.heimdall.database.log_events.LogEventRepository;
import agc.heimdall.database.LogIngestionService;
import java.time.LocalDateTime;

/**
 * @author agc
*/  

@Component
public class LogIngestion
{
    private static final Logger logger = LogManager.getLogger(LogIngestion.class);

    private final static String FILE_PATH = "heimdall.properties";
    private final static String FILE = "file";
    private final static String REGEX = "regex";
    private final static String HEADER = "header";

    private final LogIngestionService logIngestionService;

    List<LogConfig> logConfig = new ArrayList<>();

    public List<LogConfig> getLogConfig()
    {
        return logConfig;
    }

    public LogIngestion(LogIngestionService logIngestionService) throws IOException 
    {
        this.logIngestionService = logIngestionService;

        Properties props = new Properties();

        InputStream input = getClass().getClassLoader().getResourceAsStream(FILE_PATH);
        props.load(input);

        

        int index = 0;
        while(props.getProperty(FILE + index) != null)
        {
            LogConfig temp = new LogConfig(props.getProperty(FILE + index), props.getProperty(REGEX + index), props.getProperty(HEADER + index));
            logConfig.add(temp);
            index++;
        }
        System.out.println(index);
    }

    private void lineHandler(String line)
    {       
        LinkedList<String> parsedLine = new LinkedList<>();
        String[] splitLine = line.split(" ");
        for(String s : splitLine)
        {
            parsedLine.add(s);
        }
    }

    private LogEvent dynamicLineHandler(String line, LogConfig log)
    {
        String regex = log.getRegex();
        String header = log.getHeader();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);

        if(matcher.matches() == true)
        {   
            if(matcher.groupCount() != log.headerCount())
            {
                logger.trace("Log entry does not respect the header: " + matcher.groupCount() + " != " + log.headerCount());
                return null;
            }
            
            List<String> parsedLine = new LinkedList<>();
            String[] splitLine = header.split(" ");

            for (int i = 1; i <= matcher.groupCount(); i++)
            {
                parsedLine.add(matcher.group(i));
            }


            LogEvent logEvent = new LogEvent();

            int i = 1;
            for(var s : splitLine)
            {
                switch (s) 
                {
                    case "date" -> logEvent.setEventTime(LocalDateTime.parse(matcher.group(i)));
                    case "ip" -> logEvent.setIp(matcher.group(i));
                    case "username" -> logEvent.setUsername(matcher.group(i));
                    case "action" -> logEvent.setAction(matcher.group(i));
                    case "status" -> logEvent.setStatus(matcher.group(i));
                    default -> logger.debug("Unknown token in header: {}", parsedLine.get(i));
                }
                i++;
            }

            return logEvent;
        }
        else
        {
            logger.trace("Invalid log entry: " + line);
            return null;
        }
    }

    public void readLog(LogHandler handler)
    {
        for(var log : logConfig)
        {
            try(BufferedReader reader = new BufferedReader(new FileReader(log.getFile())))
            {
                String line;
                while((line = reader.readLine()) != null)
                {
                    LogEvent event = dynamicLineHandler(line, log);
                    handler.handle(event);
                }
            }
            catch(IOException e)
            {
                logger.trace("Error at reading the file: {}" + e.getMessage());
            }
        }
    }
}
