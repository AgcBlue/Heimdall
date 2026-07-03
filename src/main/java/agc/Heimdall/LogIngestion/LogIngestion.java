package agc.Heimdall.LogIngestion;

import java.io.*;
import java.util.LinkedList;

public class LogIngestion 
{
    private String filePath;

    public LogIngestion(String filePath) 
    {
        this.filePath = filePath;
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

    public void readLog()
    {
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath)))
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
