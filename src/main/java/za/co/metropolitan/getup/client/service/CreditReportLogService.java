package za.co.metropolitan.getup.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.metropolitan.getup.client.modelRepository.client.CreditReportLogRepository;
import za.co.metropolitan.getup.client.dto.CreditReportLogDTO;
import za.co.metropolitan.getup.client.model.client.CreditReportLog;

import java.sql.Timestamp;

@Service
public class CreditReportLogService {

    @Autowired
    private CreditReportLogRepository creditReportLogRepository;

    public Boolean saveFreeCreditReportLog(CreditReportLogDTO logEntry) throws Exception {
        Boolean updated = false;
        CreditReportLog creditReportLog = new CreditReportLog();

        java.util.Date date = new java.util.Date();
        Timestamp ts = new Timestamp(date.getTime());

        creditReportLog.setCreditReportRequestAt(ts);
        creditReportLog.setCpbEndPoint(logEntry.getCpbEndPoint());
        creditReportLog.setCpbRequestRef(logEntry.getCpbRequestRef());
        creditReportLog.setGetupClientNumber(logEntry.getGetupClientNumber());
        creditReportLog.setIpAddressRequestFrom(logEntry.getIpAddressRequestFrom());
        creditReportLog.setCpbHttpCode(logEntry.getCpbHttpCode());
        creditReportLog.setCpbResponseMsg(logEntry.getCpbResponseMsg());

        CreditReportLog savedLog = creditReportLogRepository.saveAndFlush(creditReportLog);

        if (savedLog != null) {
            updated = true;
        }
        return updated;

    }
}
