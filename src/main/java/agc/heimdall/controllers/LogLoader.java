package agc.heimdall.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import agc.heimdall.logingestion.LogHandler;
import agc.heimdall.logingestion.LogIngestion;
import agc.heimdall.database.LogIngestionService;
import agc.heimdall.database.beans.LogEvent;

import org.springframework.ui.Model;

@Controller
public class LogLoader 
{
    @Autowired
    LogIngestion log;

    @Autowired
    LogIngestionService logService;

    @GetMapping("/Heimdall/LogLoader")
    public String logLoader(Model model) 
    {
        log.readLog(new LogHandler() 
        {
            public void handle(LogEvent logEvent)
            {
                logService.saveLog(logEvent);
            }
        });
        model.addAttribute("message", "You updated the log database!");
        return "LogLoader";
    }

}
