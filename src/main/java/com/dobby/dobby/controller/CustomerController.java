package com.dobby.dobby.controller;

import com.dobby.dobby.dao.CustomerDAO;
import com.dobby.dobby.vo.CustomerVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.dobby.dobby.common.Common.CORS_ORIGIN;

@CrossOrigin(origins = CORS_ORIGIN)
@RestController
@RequestMapping("/users")
public class CustomerController {

    // GET : 회원 조회
    @GetMapping("/member")
    public ResponseEntity<List<CustomerVO>> memberList(@RequestParam String name) {
        System.out.println("NAME : " + name);
        CustomerDAO dao = new CustomerDAO();
        List<CustomerVO> list = dao.memberSelect(name);
        for(CustomerVO e : list) {
            System.out.println(e.getContactNumber());
            System.out.println(e.getEmail());
        }
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
    public ResponseEntity<Boolean> memberCheck(@RequestParam String id, String url) {
        System.out.println("회원 가입 여부 확인 ID : " + id + ", Url : " + url);
        CustomerDAO dao = new CustomerDAO();
        boolean isTrue = dao.regMemberCheck(id, url);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }

    // POST : 회원 가입
    @PostMapping("/new")
    public ResponseEntity<Boolean> memberRegister(@RequestBody Map<String, String> regData) {
        String getId = regData.get("id");
        String getPwd = regData.get("pwd");
        String getName = regData.get("name");
        String getNickName = regData.get("nickName");
        String getRole = regData.get("role");
        CustomerDAO dao = new CustomerDAO();
        boolean isTrue = dao.memberRegister(getId, getPwd, getName, getNickName, getRole);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }
    // POST : 회원 가입
    @PostMapping("/comnew")
    public ResponseEntity<Boolean> comMemberRegister(@RequestBody Map<String, String> regData) {
        String getId = regData.get("id");
        String getPwd = regData.get("pwd");
        String getName = regData.get("name");
        String getPhone = regData.get("phone");
        String getRole = regData.get("role");
        CustomerDAO dao = new CustomerDAO();
        boolean isTrue = dao.comMemberRegister(getId, getPwd, getName, getPhone, getRole);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }

    // POST : 개인 회원 정보 수정
    @PostMapping("/memUpdate")
    public ResponseEntity<Boolean> memUpdate(@RequestBody Map<String, String> regData) {
        String getId = regData.get("id");
        String getPwd = regData.get("pwd");
        String getName = regData.get("name");
        String getNickName = regData.get("nickName");
        CustomerDAO dao = new CustomerDAO();
        boolean isTrue = dao.memUpdate(getId, getName, getNickName, getPwd);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }
    // POST : 개인 회원 정보 수정
    @PostMapping("/memUpdate2")
    public ResponseEntity<Boolean> memUpdate2(@RequestBody Map<String, String> regData) {
        String getId = regData.get("id");
        String getPwd = regData.get("pwd");
        String getName = regData.get("name");
        String getPhone = regData.get("phone");
        CustomerDAO dao = new CustomerDAO();
        boolean isTrue = dao.memUpdate2(getId, getName, getPhone, getPwd);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }

    // POST : 회원 탈퇴
    @PostMapping("/delete")
    public ResponseEntity<Boolean> memberDelete(@RequestBody Map<String, String> delData) {
        String getId = delData.get("id");
        CustomerDAO dao = new CustomerDAO();
        boolean isTrue = dao.memberDelete(getId);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }

}
