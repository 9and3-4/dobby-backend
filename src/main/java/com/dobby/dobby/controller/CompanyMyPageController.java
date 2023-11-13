package com.dobby.dobby.controller;


// 기업 마이페이지 내 채용 공고 & 광고 목록 가져오기

import com.dobby.dobby.dao.CompanyMyPageDAO;
import com.dobby.dobby.vo.CompanyJobListVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/companymypage")
public class CompanyMyPageController {
    // GET : 채용 공고 가져오기
    @GetMapping("companyjobposting")
    public ResponseEntity<List<CompanyJobListVO>>companyJobList(@RequestParam String companyId) {
        System.out.println("CompanyId : " + companyId);
        CompanyMyPageDAO dao = new CompanyMyPageDAO();
        List<CompanyJobListVO> list = dao.companyJobListSelect(companyId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
