package agc.heimdall.database.test;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "log_events")
public class TestTable 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time_stamp")
    private LocalDateTime eventTime;

    private String ip;
    private String username;
    private String action;
    private String status;

    public Long getId() 
    { 
        return id; 
    }
    public void setId(Long id) 
    { 
        this.id = id; 
    }

    public LocalDateTime getEventTime() 
    { 
        return eventTime; 
    }
    public void setEventTime(LocalDateTime eventTime) 
    { 
        this.eventTime = eventTime; 
    }

    public String getIp() 
    { 
        return ip; 
    }
    public void setIp(String ip) 
    { 
        this.ip = ip; 
    }

    public String getUsername() 
    { 
        return username; 
    }
    public void setUsername(String username) 
    { 
        this.username = username; 
    }

    public String getAction() 
    { 
        return action; 
    }
    public void setAction(String action) 
    { 
        this.action = action; 
    }

    public String getStatus() 
    { 
        return status; 
    }
    public void setStatus(String status) 
    { 
        this.status = status; 
    }
}


