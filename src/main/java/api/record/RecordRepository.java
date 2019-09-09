package api.record;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record,Long> {

    // @Query(value = "select * FROM Record r WHERE cast(r.date As Date )= cast(?1 As Date ) AND r.active=true",nativeQuery = true)
    @Query("FROM Record r WHERE r.date = ?1 AND r.active=true")
    public  List<Record> getActiveRecordListByDate(Date date);


    @Query("FROM Record r  WHERE r.status = ?1 ")
    public  List<Record> getWaitingRecord(RecordStatus status);




}
