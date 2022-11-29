package za.co.metropolitan.getup.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.metropolitan.getup.client.dto.CreditReportLogDTO;
import za.co.metropolitan.getup.client.service.CreditReportLogService;

@RestController
@RequestMapping(value = "/CreditReport")
public class CreditReportLogController {

    @Autowired
    private CreditReportLogService creditReportLogService;

    @CrossOrigin
    @RequestMapping(value = "/RequestLog", method = RequestMethod.POST, consumes = "application/json")
    public Boolean logCreditReportRequest(@RequestBody CreditReportLogDTO request) throws Exception {
        return creditReportLogService.saveFreeCreditReportLog(request);
    }

}
