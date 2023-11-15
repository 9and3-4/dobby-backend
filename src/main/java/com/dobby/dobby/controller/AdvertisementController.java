package com.dobby.dobby.controller;

import com.dobby.dobby.dao.AdvertisementDAO;
import com.dobby.dobby.dao.CompanyDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Map;

import static com.dobby.dobby.common.Common.CORS_ORIGIN;

@CrossOrigin(origins = CORS_ORIGIN)
@RestController
@RequestMapping("/advertisement")
public class AdvertisementController {
    // POST : 광고 신청
    @PostMapping("/new")
    public ResponseEntity<Boolean> adRegister(@RequestBody Map<String, String> regData) {
        String getId = regData.get("id");
        String getImage = regData.get("image");
        Timestamp getStartDate = Timestamp.valueOf(regData.get("startDate") + " 00:00:00");
        Timestamp getEndDate = Timestamp.valueOf(regData.get("endDate") + " 00:00:00");
        String getAdFee = regData.get("adFee");
        AdvertisementDAO dao = new AdvertisementDAO();
        boolean isTrue = dao.adRegister(getId, getImage, getStartDate, getEndDate, getAdFee);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }
}
