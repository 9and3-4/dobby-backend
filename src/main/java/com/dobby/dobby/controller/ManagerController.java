package com.dobby.dobby.controller;

import com.dobby.dobby.dao.CompanyReviewDAO;
import com.dobby.dobby.dao.ManageCompanyCustomerInfoDAO;
import com.dobby.dobby.dao.ManagerUserCustomerInfoDAO;
import com.dobby.dobby.vo.CompanyInfoVO;
import com.dobby.dobby.vo.manager.ManageCompanyCustomerInfoVO;
import com.dobby.dobby.vo.manager.ManagerUserCustomerInfoVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        ManageCompanyCustomerInfoDAO dao = new ManageCompanyCustomerInfoDAO();
        List<ManageCompanyCustomerInfoVO> list = dao.getInfo();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
