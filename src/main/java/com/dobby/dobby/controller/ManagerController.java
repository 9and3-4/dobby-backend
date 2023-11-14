package com.dobby.dobby.controller;

import com.dobby.dobby.dao.*;
import com.dobby.dobby.vo.manager.ManageCompanyCustomerInfoVO;
import com.dobby.dobby.vo.manager.ManagerAdListInfoVO;
import com.dobby.dobby.vo.manager.ManagerJobPostingInfoVO;
import com.dobby.dobby.vo.manager.ManagerUserCustomerInfoVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/manager")
public class ManagerController {
    @GetMapping("/userinfo")
    public ResponseEntity<List<ManagerUserCustomerInfoVO>> managerUserInfoList() {
        ManagerUserCustomerInfoDAO dao = new ManagerUserCustomerInfoDAO();
        List<ManagerUserCustomerInfoVO> list = dao.getInfo();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/companyinfo")
    public ResponseEntity<List<ManageCompanyCustomerInfoVO>> managerCompanyInfoList() {
        ManagerCompanyCustomerInfoDAO dao = new ManagerCompanyCustomerInfoDAO();
        List<ManageCompanyCustomerInfoVO> list = dao.getInfo();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/jobpostinfo")
    public ResponseEntity<List<ManagerJobPostingInfoVO>> managerJobPostingInfoList() {
        ManagerJobPostingInfoDAO dao = new ManagerJobPostingInfoDAO();
        List<ManagerJobPostingInfoVO> list = dao.getInfo();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/adlistinfo")
    public ResponseEntity<List<ManagerAdListInfoVO>> managerAdListInfoList() {
        ManagerAdListInfoDAO dao = new ManagerAdListInfoDAO();
        List<ManagerAdListInfoVO> list = dao.getInfo();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // POST : 로그인
    @PostMapping("/state")
    public ResponseEntity<Boolean> memberLogin(@RequestBody Map<String, String> cutomerData) {
        String isActive = cutomerData.get("isActive");
        String id = cutomerData.get("id");
        System.out.println("ISACTIVE : " + isActive);
        System.out.println("ID : " + id);
        ManagerUpdateStateDAO dao = new ManagerUpdateStateDAO();
        boolean result = dao.setIsActive(isActive, id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
