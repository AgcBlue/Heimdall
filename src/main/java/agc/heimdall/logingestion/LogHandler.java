package agc.heimdall.logingestion;

import agc.heimdall.database.beans.LogEvent;

public interface LogHandler
{
    default void handle(LogEvent event){};
}
