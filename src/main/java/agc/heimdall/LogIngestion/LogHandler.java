package agc.heimdall.LogIngestion;

import agc.heimdall.database.log_events.LogEvent;

public interface LogHandler
{
    default void handle(LogEvent event){};
}
