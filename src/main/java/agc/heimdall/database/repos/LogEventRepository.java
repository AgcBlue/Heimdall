package agc.heimdall.database.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import agc.heimdall.database.beans.LogEvent;

@Service
public interface LogEventRepository extends JpaRepository<LogEvent, Long> 
{
    List<LogEvent> findByIdGreaterThan(Long id);
}