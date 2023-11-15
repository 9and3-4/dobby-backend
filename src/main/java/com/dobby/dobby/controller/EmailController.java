package com.dobby.dobby.controller;

import com.dobby.dobby.dao.CustomerDAO;
import com.dobby.dobby.vo.CustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;
import java.util.UUID;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/mail")
    public ResponseEntity<Boolean> sendMail(@RequestParam String id) {
        boolean isSent = false;
        System.out.println("인증 번호 받을 email : " + id);
        // 임의의 인증 번호 생성
        Random random = new Random();
        int min = 111111;
        int max = 999999;
        String tempPw = String.valueOf(random.nextInt(max - min + 1) + min);
        System.out.println("인증 번호 : " + tempPw);

        // 이메일에 들어갈 내용
        String htmlContent = "<div style=\"text-align: center; display:flex; justify-contents:center; text-align:center;\">"
                + "<p style=\"font-size: 16px;\">9와 3/4 승강장 이메일 인증 번호입니다.</p>"
                + "<div style=\"font-size:20px; font-style:bold; width: 100px; height:50px; border: 1px solid #c6c6c6;\">" + tempPw + "</div>"
                + "<p style=\"font-size: 12px;\">인증 번호를 입력해주세요.</p>"
                + "</div>";

        // 임시 비밀번호를 이메일로 전송

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        try {
            helper.setFrom("dobby22023@naver.com");
            helper.setTo(id);
            helper.setSubject("Dobby 이메일 인증");
            helper.setText(htmlContent, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(mimeMessage);
        isSent = true;
        System.out.println("isSent : " + isSent);

    return new ResponseEntity<>(isSent,HttpStatus.OK);
    }
}
