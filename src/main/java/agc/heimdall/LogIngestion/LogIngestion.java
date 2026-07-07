package agc.heimdall.LogIngestion;

import java.beans.ConstructorProperties;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author agc
*/
public class LogIngestion 
{
    private final static String FILE_PATH = "heimdall.properties";
    private static final Logger logger = LogManager.getLogger(LogIngestion.class);
    private final static String FILE = "file";
    private final static String CONFIG = "config";
    private Map<String, String> logConfig = new HashMap<>();

    public LogIngestion() 
    {
        Properties props = new Properties();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(FILE_PATH)) 
        {
            if (input == null) 
            {
                throw new FileNotFoundException("heimdall.properties was not found");
            }
            props.load(input);
        } 
        catch (IOException e) 
        {
            throw new ExceptionInInitializerError(e);
        }

        int index = 0;
        while(props.getProperty("file" + index) != null)
        {
            logConfig.put(props.getProperty(FILE + index), props.getProperty(CONFIG + index));
            index++;
        }
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

    public Map<String, String> getLogConfig()
    {
        return logConfig;
    }

    private void dynamicLineHandler(String line)
    {
        List<Pattern> datePatterns = List.of
        (
            Pattern.compile("\\d{2,4}(-?/?){1}\\d{2}-?/\\d{2,4}T?t? ?\\d{2}:\\d{2}:\\d{2} ?Z?z?A?P?M*)")
        );
    }

    public void readLog()
    {
        for(var log : logConfig.entrySet())
        {
            try(BufferedReader reader = new BufferedReader(new FileReader(log.getValue())))
            {
                String line;

                while((line = reader.readLine()) != null)
                {
                    lineHandler(line);  
                }
            }
            catch(IOException e)
            {
                logger.trace("Error at reading the file: {}" + e.getMessage());
            }
        }
    }
}
