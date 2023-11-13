package com.dobby.dobby.controller;


import com.dobby.dobby.dao.BoardDAO;
import com.dobby.dobby.vo.BoardVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static com.dobby.dobby.common.Common.CORS_ORIGIN;


@CrossOrigin(origins = CORS_ORIGIN)
@RestController
@RequestMapping("/api/board")
public class BoardController {
    // GET : 게시글 목록 조회
    @GetMapping
    public ResponseEntity<List<BoardVO>> boardList() {
        BoardDAO dao = new BoardDAO();
        List<BoardVO> list = dao.selectBoardList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    // GET : 게시글 상세 조회
    @ResponseBody
    @GetMapping("/detail/{id}")
    public ResponseEntity<BoardVO> boardDetail(@PathVariable Long id) {
        System.out.println("게시글 조회 ID : " + id);
        BoardDAO dao = new BoardDAO();
        BoardVO boardVO = dao.selectBoardDetail(id);
        return new ResponseEntity<>(boardVO, HttpStatus.OK);
    }
    // POST : 게시글 등록
    @PostMapping("/new")
    public ResponseEntity<Boolean> boardRegister(@RequestBody BoardVO boardVO) {
        BoardDAO dao = new BoardDAO();
        boolean isTrue = dao.insertBoard(boardVO);
        System.out.println("게시글 등록 결과 : " + isTrue);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }
//    // PUT : 게시글 수정
//    @PutMapping("/{id}")
//    public ResponseEntity<Boolean> boardModify(@RequestBody BoardVO boardVO) {
//        BoardDAO dao = new BoardDAO();
//        boolean isTrue = dao.updateBoard(boardVO);
//        return new ResponseEntity<>(isTrue, HttpStatus.OK);
//    }
//    // DELETE : 게시글 삭제
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Boolean> boardDelete(@PathVariable Long id) {
//        BoardDAO dao = new BoardDAO();
//        boolean isTrue = dao.deleteBoard(id);
//        return new ResponseEntity<>(isTrue, HttpStatus.OK);
//    }
}