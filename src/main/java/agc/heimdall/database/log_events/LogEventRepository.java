package agc.heimdall.database.log_events;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LogEventRepository extends JpaRepository<LogEvent, Long> 
{
    
}