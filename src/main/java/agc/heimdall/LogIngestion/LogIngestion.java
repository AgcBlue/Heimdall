package agc.heimdall.LogIngestion;

import java.beans.ConstructorProperties;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;


public class LogIngestion 
{
    private String filePath = "heimdall";
    private Map<String, String> logConfig = new HashMap<>();
    private int logFileNumber = 0;


    public LogIngestion() 
    {
        Properties props = new Properties();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("heimdall.properties")) 
        {
            if (input == null) 
            {
                throw new RuntimeException("heimdall.properties was not found");
            }
            props.load(input);
        } 
        catch (IOException e) 
        {
            throw new RuntimeException("Error at loading properties: ", e);
        }

        populateMaps(props);
    }

    private void populateMaps(Properties props)
    {
        int index = 0;
        while(props.getProperty("file" + index) != null)
        {
            logConfig.put(props.getProperty("file" + index), props.getProperty("config" + index));
            index++;
        }

    }

    public void printLogInfo()
    {
        for(var log : logConfig.entrySet())
        {
            System.out.println(log.getKey() + " + " + log.getValue());
        }
    }

    private void lineHandler(String line)
    {
        LinkedList<String> parsedLine = new LinkedList<>();
        StringBuilder build = new StringBuilder();
        
        for(int i = 0; i < line.length(); i++)
        {
            if(line.charAt(i) == ' ')
            {
                parsedLine.add(build.toString());
            }
            else
            {
                build.append(line.charAt(i));
            }
        }
        parsedLine.add(build.toString());
    }

    private void dinamicLineHandler(String line)
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
                System.err.println("Error at reading the file: " + e.getMessage());
            }
        }
        
    }
}
