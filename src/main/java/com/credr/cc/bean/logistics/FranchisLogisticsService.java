package com.credr.cc.bean.logistics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("franchise/logistics/")
public class FranchisLogisticsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FranchisLogisticsService.class);

    @Autowired
    FranchisLogisticsMgr showRoomDeliveryLogisticsMgr;

    @GetMapping("/dashboard")
    FranchiseLogisticsDashBoardResponse logisticDashboard(@RequestHeader Map<String, String> headers) {
        LOGGER.warn("@@@@@@@@headers:" + headers);
        //acceptig the headers for request validation
        return showRoomDeliveryLogisticsMgr.findActiveRecords(headers);
    }

    @PostMapping("/dashboard")
    FranchiseLogisticsDashBoardResponse logisticsDashboard(@RequestBody FranchiseLogisticsSearchCriteria criteria, @RequestHeader Map<String, String> headers) {
        LOGGER.warn("@@@@@@@@@@@logisticsDashboard - criteria:" + criteria);
        return showRoomDeliveryLogisticsMgr.findActiveRecords(headers, criteria);
    }

    @GetMapping("/assignRunner")
    AssignRunnerResponse loadRunnerDetailsForRequest(@RequestParam String orderId, @RequestHeader Map<String, String> headers) {
        return showRoomDeliveryLogisticsMgr.findActiveRunners(orderId, headers);
    }

    @PostMapping("/assignRunner")
    AssignRunnerResponse updateRunnerDetailsForPostRequest(@RequestBody AssignRunnerRequest assignRunnerRequest, @RequestHeader Map<String, String> headers) {
        LOGGER.warn("@@@@@@@@@updateRunnerDetailsForPostRequest:");
        return showRoomDeliveryLogisticsMgr.updateTransitInfoRequest(assignRunnerRequest);
    }

    @PostMapping("/update/status")
    AssignRunnerResponse updateStatusForRequest(@RequestBody @Valid AssignRunnerRequest assignRunnerRequest, @RequestHeader Map<String, String> headers) {
        return showRoomDeliveryLogisticsMgr.updateTransitInfoRequest(assignRunnerRequest);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<Object> handleMethodArgumentNotValid(HttpMessageNotReadableException ex,
                                                               HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation Failed", ex.getCause().toString());
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
