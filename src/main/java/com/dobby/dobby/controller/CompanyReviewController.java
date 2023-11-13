package com.dobby.dobby.controller;


import com.dobby.dobby.dao.CompanyDAO;
import com.dobby.dobby.dao.CompanyReviewDAO;
import com.dobby.dobby.vo.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.ResourceBundle;

// 회사 소개 게시판
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/companyreview")
public class CompanyReviewController {
    // GET: 회사 정보 가져 오기
    @GetMapping("/companyinfo/{id}")
    public ResponseEntity<List<CompanyInfoVO>>companyInfo(@PathVariable String id) {
        System.out.println("ID : " + id);
        CompanyReviewDAO dao = new CompanyReviewDAO();
        List<CompanyInfoVO> list = dao.companyInfoSelect(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // GET: 회사 모든 정보 가져 오기
    @GetMapping("/companyinfoall")
    public ResponseEntity<List<CompanyInfoVO>>companyInfo(){
        CompanyReviewDAO dao = new CompanyReviewDAO();
        List<CompanyInfoVO> list = dao.companyInfoallSelect();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // GET : 회사에 대한 현직자가 작성한 리뷰 가져 오기
    @GetMapping("/companyfeedback/{companyId}")
    public ResponseEntity<List<CompanyFeedbackVO>>companyFeedBack(@PathVariable String companyId){
        CompanyReviewDAO dao = new CompanyReviewDAO();
        List<CompanyFeedbackVO> list = dao.companyFeedbackSelect(companyId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // GET :  회사에 대한 게시글 가져 오기
    @GetMapping("companypost/{companyId}")
    public ResponseEntity<List<CompanyPostVO>>companyPost(@PathVariable String companyId) {
        CompanyReviewDAO dao = new CompanyReviewDAO();
        List<CompanyPostVO> list = dao.companyPostSelect(companyId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // GET : 회사 채용 공고 게시글 가져 오기
    @GetMapping("companyjobposting/{companyId}")
    public ResponseEntity<List<CompanyJobPostingVO>>companyJobPosting(@PathVariable String companyId) {
        CompanyReviewDAO dao = new CompanyReviewDAO();
        List<CompanyJobPostingVO> list = dao.companyJobPostingSelect(companyId);
           return new ResponseEntity<>(list, HttpStatus.OK);

    }


}
