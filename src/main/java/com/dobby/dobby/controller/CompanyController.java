package com.dobby.dobby.controller;

import com.dobby.dobby.dao.CompanyDAO;
import com.dobby.dobby.dao.CustomerDAO;
import com.dobby.dobby.vo.CompanyVO;
import com.dobby.dobby.vo.CustomerVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.dobby.dobby.common.Common.CORS_ORIGIN;

@CrossOrigin(origins = CORS_ORIGIN)
@RestController
@RequestMapping("/company")
public class CompanyController {
    // GET : 회원의 회사 조회
    @GetMapping("/customercompany")
    public ResponseEntity<List<CompanyVO>> memberList(@RequestParam String email) {
        System.out.println("EMAIL : " + email);
        CompanyDAO dao = new CompanyDAO();
        List<CompanyVO> list = dao.companySelect(email);
        System.out.println("listlist : " + list);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // GET : 모든 회사 조회
    @GetMapping("/companylist")
    public ResponseEntity<List<CompanyVO>> companyList() {
        CompanyDAO dao = new CompanyDAO();
        List<CompanyVO> list = dao.companyListSelect("ALL");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // POST : 회원 가입
    @PostMapping("/new")
    public ResponseEntity<Boolean> companyRegister(@RequestBody Map<String, String> regData) {
        String getCompanyName = regData.get("companyName");
        String getSizeScale = regData.get("sizeScale");
        String getCeo = regData.get("ceo");
        String getContactNumber = regData.get("contactNumber");
        String getUrl = regData.get("url");
        String getCategory = regData.get("category");
        String getAddress = regData.get("address");
        String getYear = regData.get("year");
        String getStaff = regData.get("staff");
        String getIncome = regData.get("income");
        String getProfile = regData.get("profile");
        String getLogo = regData.get("logo");
        CompanyDAO dao = new CompanyDAO();
        boolean isTrue = dao.companyRegister(getCompanyName, getSizeScale, getCeo, getContactNumber, getUrl,getCategory,getAddress,getYear,getStaff,getIncome,getProfile,getLogo);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }

    // POST : 기업 정보 수정
    @PostMapping("/update")
    public ResponseEntity<Boolean> companyUpdate(@RequestBody Map<String, String> regData) {
        String getId = regData.get("id");
        String getCompanyName = regData.get("companyName");
        String getSizeScale = regData.get("sizeScale");
        String getCeo = regData.get("ceo");
        String getContactNumber = regData.get("contactNumber");
        String getCategory = regData.get("category");
        String getAddress = regData.get("address");
        String getYear = regData.get("year");
        String getStaff = regData.get("staff");
        String getIncome = regData.get("income");
        String getProfile = regData.get("profile");
        String getLogo = regData.get("logo");
        CompanyDAO dao = new CompanyDAO();
        boolean isTrue = dao.companyUpdate(getCompanyName, getSizeScale, getCeo, getContactNumber,getCategory,getAddress,getYear,getStaff,getIncome,getProfile,getLogo, getId);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }

    // POST : 기업 탈퇴
    @PostMapping("/delete")
    public ResponseEntity<Boolean> companyDelete(@RequestBody Map<String, String> delData) {
        String getId = delData.get("id");
        CompanyDAO dao = new CompanyDAO();
        boolean isTrue = dao.companyDelete(getId);
        System.out.println("isTrue : " + isTrue);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }

}