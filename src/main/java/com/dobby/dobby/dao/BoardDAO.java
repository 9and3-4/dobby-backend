package com.dobby.dobby.dao;


import com.dobby.dobby.common.Common;
import com.dobby.dobby.vo.BoardVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;
//    // 게시글 등록
//    public boolean insertBoard(BoardVO boardVO) {
//        boolean isInsert = false;
//        try {
//            conn = Common.getConnection();
//            stmt = conn.createStatement();
//            String sql = "INSERT INTO BOARD (BOARD_ID, USER_ID, TITLE, CONTENT, REG_DATE, IMAGE_PATH) VALUES (board_sequence.NEXTVAL, ?, ?, ?, SYSDATE, ?)";
//            pStmt = conn.prepareStatement(sql);
//            pStmt.setString(1, boardVO.getUserId());
//            pStmt.setString(2, boardVO.getTitle());
//            pStmt.setString(3, boardVO.getContent());
//            pStmt.setString(4, boardVO.getImg());
//            int result = pStmt.executeUpdate();
//            if(result == 1) isInsert = true;
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//        Common.close(pStmt);
//        Common.close(conn);
//        return isInsert;
//    }
//    // 게시글 수정
//    public boolean updateBoard(BoardVO boardVO) {
//        String sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ? WHERE BOARD_ID = ?";
//        try {
//            conn = Common.getConnection();
//            pStmt = conn.prepareStatement(sql);
//            pStmt.setString(1, boardVO.getTitle());
//            pStmt.setString(2, boardVO.getContent());
//            pStmt.setLong(3, boardVO.getBoardId());
//            int result = pStmt.executeUpdate();
//            if(result == 1) return true;
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//        Common.close(pStmt);
//        Common.close(conn);
//        return false;
//    }
//    // 게시글 삭제
//    public boolean deleteBoard(Long id) {
//        String sql = "DELETE FROM BOARD WHERE BOARD_ID = ?";
//        try {
//            conn = Common.getConnection();
//            pStmt = conn.prepareStatement(sql);
//            pStmt.setLong(1, id);
//            int result = pStmt.executeUpdate();
//            if(result == 1) return true;
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//        Common.close(pStmt);
//        Common.close(conn);
//        return false;
//    }
    // 게시글 목록 조회
    public List<BoardVO> selectBoardList() {
        List<BoardVO> boardList = new ArrayList<>();
        String sql = null;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
//            sql = "SELECT * FROM POST ORDER BY ID DESC";
            sql = "SELECT * FROM POST " +
                    "INNER JOIN TOPIC ON POST.TOPIC_ID = TOPIC.ID " +
                    "ORDER BY POST.ID DESC ";

            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                BoardVO boardVO = new BoardVO();
                boardVO.setMajor(rs.getString("MAJOR_CATEGORY"));
                boardVO.setSub(rs.getString("SUB_CATEGORY"));
                boardVO.setBoardId(rs.getLong("ID"));
                boardVO.setUserId(rs.getString("TOPIC_ID"));
                boardVO.setTitle(rs.getString("TITLE"));
                boardVO.setContent(rs.getString("CONTENT"));
                boardVO.setRegDate(rs.getDate("WRITE_DATE"));
                boardList.add(boardVO);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return boardList;
    }
//    // 게시글 상세 조회
//    public BoardVO selectBoardDetail(Long id) {
//        BoardVO boardVO = new BoardVO();
//        String sql = null;
//        try {
//            conn = Common.getConnection();
//            stmt = conn.createStatement();
//            sql = "SELECT * FROM BOARD WHERE BOARD_ID = " + id;
//            rs = stmt.executeQuery(sql);
//            while(rs.next()) {
//                boardVO.setBoardId(rs.getLong("BOARD_ID"));
//                boardVO.setUserId(rs.getString("USER_ID"));
//                boardVO.setTitle(rs.getString("TITLE"));
//                boardVO.setContent(rs.getString("CONTENT"));
//                boardVO.setImg(rs.getString("IMAGE_PATH"));
//                boardVO.setRegDate(rs.getDate("REG_DATE"));
//            }
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//        Common.close(rs);
//        Common.close(stmt);
//        Common.close(conn);
//        return boardVO;
//    }
}