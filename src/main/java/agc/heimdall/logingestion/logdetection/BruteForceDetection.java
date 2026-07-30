package agc.heimdall.logingestion.logdetection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agc.heimdall.database.LogIngestionService;
import agc.heimdall.database.beans.LogEvent;
import agc.heimdall.database.repos.LogEventRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class BruteForceDetection 
{
    @Autowired
    LogEventRepository repo;

    private long ind = 0;
    private Map<String, Integer> ipMap = new HashMap<>();
    private static final String[] failKeyWords = {"fail", "failed", "faillure", "null", "not", "rejected", "reject", "denied", "blocked"};

    public void chunkCheck()
    {
        List<LogEvent> allLoggs = repo.findByIdGreaterThan(ind);
        for(var line : allLoggs)
        {
            if(line.getAction().contains("login") == true)
            {
                for(String s : failKeyWords)
                {
                    if(line.getStatus().contains(s) == true)
                    {
                        if(ipMap.containsKey(line.getIp())) 
                        {
                            ipMap.put(line.getIp(), ipMap.get(line.getIp()) + 1);
                            checkIp(line.getIp());
                        }
                        else
                        {
                            ipMap.put(line.getIp(), 1);
                        }
                    }
                }
            }
            ind++;
        }
    }

    public void checkIp(String ip)
    {
        if(ipMap.get(ip) == 5)
        {
            //something, something
        }
    }

}
