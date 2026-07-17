package agc.heimdall.database.test;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestTableRepository extends JpaRepository<TestTable, Long> 
{
    
}