package com.dobby.dobby.controller;


import com.dobby.dobby.dao.PostDAO;
import com.dobby.dobby.vo.PostVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/post")
public class PostController {
    //GET: 게시글 조회
    @GetMapping("/mypostlist")
    public ResponseEntity<List<PostVO>> myPostList(@RequestParam String email) {
        System.out.println("EMAIL : " + email);
        PostDAO dao = new PostDAO();
        List<PostVO> list = dao.myPostSelect(email);
        // 프론트로 보내줌
        return new ResponseEntity<>(list, HttpStatus.OK) ;
    }

}
