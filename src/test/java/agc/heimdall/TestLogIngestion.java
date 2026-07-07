package agc.heimdall;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;
import org.springframework.boot.test.context.SpringBootTest;
import agc.heimdall.LogIngestion.*;

@SpringBootTest
class TestLogIngestion
{
	@Test
	public void mapTest(TestReporter reporter) 
    {
		LogIngestion loggs = new LogIngestion();
        Map<String, String> map = loggs.getLogConfig();

        assertNotNull(map.containsKey("test/test0"));
        assertNotNull(map.containsKey("test/test0/test1"));
        assertEquals("abc", map.get("test/test0"));
        assertEquals("def", map.get("test/test0/test1"));
	}	

}


