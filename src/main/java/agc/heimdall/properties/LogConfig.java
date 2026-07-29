package agc.heimdall.properties;

import java.util.LinkedList;

public class LogConfig
{
    private String file;
    private String regex;
    private String header; 

    public LogConfig(){}
    public LogConfig(String file, String regex, String header)
    {
        this.file = file;
        this.regex = regex;
        this.header = header;
    }

    public void setFile(String file)
    {
        this.file = file;
    }
    public void setRegex(String regex)
    {
        this.file = regex;
    }
    public void setHeader(String header)
    {
        this.file = header;
    }

    public String getFile()
    {
        return this.file;
    }
    public String getRegex()
    {
        return this.regex;
    }
    public String getHeader()
    {
        return this.header;
    }

    public int headerCount()
    {
        int count = 0;
        String[] splitLine = header.split(" ");
        for(var s : splitLine)
        {
            count++;
        }
        return count;
    }
}