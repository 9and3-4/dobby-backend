package com.dobby.dobby.controller;

import com.dobby.dobby.dao.CustomerDAO;
import com.dobby.dobby.vo.CustomerVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class CustomerController {

    // GET : 회원 조회
    @GetMapping("/member")
    public ResponseEntity<List<CustomerVO>> memberList(@RequestParam String name) {
        System.out.println("NAME : " + name);
        CustomerDAO dao = new CustomerDAO();
        List<CustomerVO> list = dao.memberSelect(name);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // POST : 로그인
    @PostMapping("/login")
    public ResponseEntity<Boolean> memberLogin(@RequestBody Map<String, String> loginData) {
        String user = loginData.get("id");
        String pwd = loginData.get("pwd");
        System.out.println("ID : " + user);
        System.out.println("PWD : " + pwd);
        CustomerDAO dao = new CustomerDAO();
        boolean result = dao.loginCheck(user, pwd);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // GET : 가입 여부 확인
    @GetMapping("/check")
    public ResponseEntity<Boolean> memberCheck(@RequestParam String id) {
        System.out.println("회원 가입 여부 확인 ID : " + id);
        CustomerDAO dao = new CustomerDAO();
        boolean isTrue = dao.regMemberCheck(id);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }


}
