package com.dobby.dobby.controller;

import com.dobby.dobby.dao.CompanyDAO;
import com.dobby.dobby.dao.CustomerDAO;
import com.dobby.dobby.vo.CompanyVO;
import com.dobby.dobby.vo.CustomerVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class CompanyController {
    // GET : 회원 조회
    @GetMapping("/customercompany")
    public ResponseEntity<List<CompanyVO>> memberList(@RequestParam String email) {
        System.out.println("EMAIL : " + email);
        CompanyDAO dao = new CompanyDAO();
        List<CompanyVO> list = dao.companySelect(email);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
