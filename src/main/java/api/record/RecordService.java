package api.record;

import api.config.CustomUserDetailRepository;
import api.config.users.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RecordService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserService userService;
    @Autowired
    private RecordRepository recordRepository;
    @Autowired
    CustomUserDetailRepository customUserDetailRepository;

    public List<Record> getRecords() {
        return recordRepository.findAll();
    }

    public RecordDto getRecordDtoById(Long id) {
        Optional<Record> recordOptional = recordRepository.findById(id);
        if (recordOptional.isPresent()) {
            return modelMapper.map(recordOptional.get(), RecordDto.class);
        }
        return null;
    }

    public RecordDto saveRecord(Record record) throws ParseException {
        record.setActive(true);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date recordDate = new Date();
        recordDate = simpleDateFormat.parse(simpleDateFormat.format(recordDate));
        record.setDate(recordDate);
        //record.setCustomer(customUserDetailRepository.findByUserName('%'+record.getCustomerName()+'%'));
        record.setNotebook(userService.getCurrentUSer().getNotebook());
        record.setProducer( userService.getCurrentUSer());
        recordRepository.save(record);
        return modelMapper.map(record, RecordDto.class);

    }

    public void passiveRecord(Long id) {
        Optional<Record> recordOptional = recordRepository.findById(id);
        if (recordOptional.isPresent()) {
            recordOptional.get().setActive(false);
            recordRepository.save(recordOptional.get());
        }
    }

    public void approveRecord(Record record) {
        record.setStatus(RecordStatus.Approved);
        recordRepository.save(record);

    }

    public List<Record> getActiveRecordListByDate(Date date) {
        return recordRepository.getActiveRecordListByDate(date);
    }


    public List<Record> getWaitingRecords() {
        RecordStatus status = RecordStatus.Waiting;
        return recordRepository.getWaitingRecord(status);
    }
}
