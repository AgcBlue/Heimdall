package agc.heimdall.LogIngestion;

import java.beans.ConstructorProperties;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import agc.heimdall.properties.*;

/**
 * @author agc
*/
public class LogIngestion 
{
    private static final Logger logger = LogManager.getLogger(LogIngestion.class);

    private final static String FILE_PATH = "heimdall.properties";
    private final static String FILE = "file";
    private final static String REGEX = "regex";
    private final static String HEADER = "header";

    List<LogConfig> logConfig = new ArrayList<>();

    public List<LogConfig> getLogConfig()
    {
        return logConfig;
    }

    public LogIngestion() throws IOException 
    {
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

    private void dynamicLineHandler(String line, String regex, String header)
    {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        matcher.matches();

        for (int i = 1; i <= matcher.groupCount(); i++)
        {
            System.out.println(matcher.group(i));
        }
        
    }

    public void readLog()
    {
        for(var log : logConfig)
        {
            try(BufferedReader reader = new BufferedReader(new FileReader(log.getFile())))
            {
                String line;
                while((line = reader.readLine()) != null)
                {
                    dynamicLineHandler(line, log.getRegex(), log.getHeader());  
                }
            }
            catch(IOException e)
            {
                logger.trace("Error at reading the file: {}" + e.getMessage());
            }
        }
    }
}
