package agc.heimdall;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;
import org.springframework.boot.test.context.SpringBootTest;
import agc.heimdall.LogIngestion.*;
import agc.heimdall.properties.*;

@SpringBootTest
class TestLogIngestion
{
	@Test
	public void mapTest(TestReporter reporter) throws Exception
    {
		LogIngestion loggs = new LogIngestion();
        List<LogConfig> logConfig = loggs.getLogConfig();

        assertEquals("test/test0", logConfig.get(0).getFile());
        assertEquals("test/test0/test1", logConfig.get(1).getFile());

        assertEquals("abc", logConfig.get(0).getRegex());
        assertEquals("def", logConfig.get(1).getRegex());

        assertEquals("hea", logConfig.get(0).getHeader());
        assertEquals("der", logConfig.get(1).getHeader());
	}	
}


