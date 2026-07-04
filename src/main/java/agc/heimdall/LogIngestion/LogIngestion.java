package agc.heimdall.LogIngestion;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;

public class LogIngestion 
{
    private String filePath = "heimdall.properties";
    private ArrayList<String> logFiles = new ArrayList<>();
    private ArrayList<String> logFileSettings = new ArrayList<>();
    private int logFileNumber = 0;

    public LogIngestion() 
    {
        InputStream is = LogIngestion.class.getClassLoader().getResourceAsStream(filePath);
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)))
        {
            String line;
            while((line = reader.readLine()) != null)
            {
                logFiles.add(line);
                line = reader.readLine();
                logFileSettings.add(line);
                logFileNumber++;
            }
        }
        catch(IOException e)
        {
            System.err.println("Error at reading the file: " + e.getMessage());
        }
    }

    public void printLogInfo()
    {
        for(int i = 0; i < logFileNumber; i++)
        {
            System.out.println(logFiles.get(i));
            System.out.println(logFileSettings.get(i));
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
        for(int i = 0; i < logFileNumber; i++)
        {
            try(BufferedReader reader = new BufferedReader(new FileReader(logFiles.get(i))))
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
