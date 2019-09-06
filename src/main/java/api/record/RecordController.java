package api.record;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/debit/record")

public class RecordController {
    @Autowired
    private RecordService recordService;

    @GetMapping("/get")
    public List<Record> getRecords()  {
        return recordService.getRecords();
    }

    @GetMapping("/get-record-dto/{id}")
    public RecordDto getRecordDtoById(@PathVariable Long id) {
        return recordService.getRecordDtoById(id);
    }

    @PostMapping("/save")
    public RecordDto saveRecord(@RequestBody Record record) throws ParseException {
        return recordService.saveRecord(record);
    }

    @PutMapping("/passive/{id}")
    public void passiveRecord(@PathVariable Long id) {
        recordService.passiveRecord(id);
    }

    @PutMapping("/approve")
    public void approveRecord(@RequestBody Record record) {
        recordService.approveRecord(record);
    }

    @GetMapping("/get-active-list/{date}")
    public List<Record> getActiveRecordListByDate(@PathVariable  @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        return recordService.getActiveRecordListByDate(date);
    }
    @GetMapping("/get-waiting-list/waiting")
    public List<Record> getWaitingRecord(){
        return recordService.getActiveRecordListByDate();
    }



}

