package agc.heimdall.database.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import agc.heimdall.database.beans.LogEvent;

@Service
public interface LogEventRepository extends JpaRepository<LogEvent, Long> 
{
    
}