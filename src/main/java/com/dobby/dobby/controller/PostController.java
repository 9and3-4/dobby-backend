package com.dobby.dobby.controller;


import com.dobby.dobby.dao.BoardDAO;
import com.dobby.dobby.dao.PostDAO;
import com.dobby.dobby.vo.BoardVO;
import com.dobby.dobby.vo.PostVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dobby.dobby.common.Common.CORS_ORIGIN;

@CrossOrigin(origins = CORS_ORIGIN)
@RestController
@RequestMapping("/post")
public class PostController {


    // GET : 게시글 상세 조회
    @GetMapping("/detail/{id}")
    public ResponseEntity<BoardVO> boardDetail(@PathVariable Long id) {
        BoardDAO dao = new BoardDAO();
        BoardVO boardVO = dao.selectBoardDetail(id);
        return new ResponseEntity<>(boardVO, HttpStatus.OK);
    }

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
