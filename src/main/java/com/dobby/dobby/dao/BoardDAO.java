package com.dobby.dobby.dao;


import com.dobby.dobby.common.Common;
import com.dobby.dobby.vo.BoardVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;


    // 대분류 조회
    public List<String> getMajorCategoryList() {
        List<String> majorCategoryList = new ArrayList<>();
        String sql = null;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            sql = "SELECT DISTINCT MAJOR_CATEGORY FROM TOPIC";
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                String majorCategory = rs.getString("MAJOR_CATEGORY");
                majorCategoryList.add(majorCategory);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);

        System.out.println(majorCategoryList + "불러옴");
        return majorCategoryList;
    }

    // 소분류 조회
    public List<String> getSubCategoryList(String majorCategory) {
        System.out.println("메이져 리스트 넣기 : " + majorCategory);
        List<String> subCategoryList = new ArrayList<>();
        String sql = null;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            sql = "SELECT SUB_CATEGORY FROM TOPIC WHERE MAJOR_CATEGORY = '" + majorCategory + "'";
//            sql = "SELECT ID FROM TOPIC where sub_category = ";
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                String subCategory = rs.getString("SUB_CATEGORY");
                System.out.println("subCa: " + subCategory);
                subCategoryList.add(subCategory);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return subCategoryList;
    }

       // 게시글 등록
    public boolean insertBoard(BoardVO boardVO) {
        boolean isInsert = false;
        conn = null;
        pStmt = null;
        try {
            conn = Common.getConnection();
//            stmt = conn.createStatement();
            conn.setAutoCommit(false); // 자동 커밋 비활성화
            String sql = "INSERT INTO POST (ID, CUSTOMER_ID,  TOPIC_ID,  TITLE, CONTENT, WRITE_DATE ) " +
                    "VALUES (POST_ID_SEQ.NEXTVAL, ?, (SELECT ID FROM TOPIC WHERE MAJOR_CATEGORY = ? AND SUB_CATEGORY = ?), ?, ?, SYSDATE)";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, boardVO.getCustomerId()); // 고객 ID를 설정해야 할 것입니다.
            pStmt.setString(2, boardVO.getMajor());
            pStmt.setString(3, boardVO.getSub());
            pStmt.setString(4, boardVO.getTitle());
            pStmt.setString(5, boardVO.getContent());
//            pStmt.setDate(5, boardVO.getWriteDate());
            int result = pStmt.executeUpdate();
            if (result == 1) {
                // 게시글이 성공적으로 삽입되면 대분류, 소분류를 추가로 삽입합니다.
//                String topicSql = "INSERT INTO TOPIC (ID, MAJOR_CATEGORY, SUB_CATEGORY) " +
//                        "VALUES (POST_ID_SEQ.CURRVAL, ?, ?)";
//
//                pStmt = conn.prepareStatement(topicSql);
//                pStmt.setString(1, boardVO.getMajor());
//                pStmt.setString(2, boardVO.getSub());
//                pStmt.executeUpdate();
                conn.commit(); // 모든 쿼리가 성공했을 때 커밋
                isInsert = true;
            } else {
                conn.rollback(); // 실패 시 롤백
            }
        } catch (Exception e) {
            e.printStackTrace();

            try {
                if (conn != null) {
                    conn.rollback(); // 예외 발생 시 롤백
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } finally {
            Common.close(pStmt);
            Common.close(conn);
        }
        return isInsert;
    }




    // 게시글 수정
    public boolean updateBoard(BoardVO boardVO) {
        String sql = "UPDATE POST SET TITLE = ?, CONTENT = ? WHERE ID = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, boardVO.getTitle());
            pStmt.setString(2, boardVO.getContent());
            pStmt.setLong(3, boardVO.getBoardId());
            int result = pStmt.executeUpdate();
            if(result == 1) return true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
        return false;
    }
    // 게시글 삭제
    public boolean deleteBoard(Long id) {
        String sql = "DELETE FROM BOARD WHERE BOARD_ID = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setLong(1, id);
            int result = pStmt.executeUpdate();
            if(result == 1) return true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
        return false;
    }
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
//                boardVO.setContent(rs.getString("CONTENT"));
                boardVO.setWriteDate(rs.getDate("WRITE_DATE"));
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
    // 게시글 상세 조회
    public BoardVO selectBoardDetail(Long id) {
        BoardVO boardVO = new BoardVO();
        String sql = null;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
//            sql = "SELECT * FROM POST WHERE ID = " + id;
            sql = "SELECT POST.*, TOPIC.MAJOR_CATEGORY, TOPIC.SUB_CATEGORY " +
                    "FROM POST " +
                    "INNER JOIN TOPIC ON POST.TOPIC_ID = TOPIC.ID " +
                    "WHERE POST.ID = " + id;
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                boardVO.setMajor(rs.getString("MAJOR_CATEGORY"));
                boardVO.setSub(rs.getString("SUB_CATEGORY"));
                boardVO.setBoardId(rs.getLong("ID"));
                boardVO.setUserId(rs.getString("TOPIC_ID"));
                boardVO.setTitle(rs.getString("TITLE"));
                boardVO.setContent(rs.getString("CONTENT"));
                boardVO.setWriteDate(rs.getDate("WRITE_DATE"));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return boardVO;
    }
}