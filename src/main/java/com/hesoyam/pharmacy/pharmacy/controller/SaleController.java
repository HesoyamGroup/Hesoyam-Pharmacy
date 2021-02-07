package com.hesoyam.pharmacy.pharmacy.controller;

import com.hesoyam.pharmacy.pharmacy.dto.SalesDTO;
import com.hesoyam.pharmacy.pharmacy.service.ISaleService;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.util.report.ReportResult;
import com.hesoyam.pharmacy.util.report.ReportType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value = "/sales", produces = MediaType.APPLICATION_JSON_VALUE)
public class SaleController {
    @Autowired
    private ISaleService saleService;

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("/appointment")
    public ResponseEntity<SalesDTO> getAppointmentSalesReportByAdministrator(@AuthenticationPrincipal User user, @RequestParam ReportType type){
        try{
            ReportResult reportResult = saleService.getAppointmentSalesReportByAdministrator(user, type);
            return ResponseEntity.ok().body(new SalesDTO(reportResult));
        } catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        } catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("/medicine")
    public ResponseEntity<SalesDTO> getMedicineSalesReportByAdministrator(@AuthenticationPrincipal User user, @RequestParam ReportType type){
        return ResponseEntity.ok().build();
    }
}
