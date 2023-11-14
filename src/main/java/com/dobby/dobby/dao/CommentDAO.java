package com.dobby.dobby.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.dobby.dobby.common.Common;
import com.dobby.dobby.vo.CommentVO;

public class CommentDAO {

    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;
    // 댓글 목록
    public List<CommentVO> getCommentList(Long boardId) {
        List<CommentVO> commentList = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM REPLY WHERE POST_ID = " + boardId;
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                CommentVO commentVO = new CommentVO();
                commentVO.setId(rs.getLong("ID"));
                commentVO.setPostId(rs.getLong("POST_ID"));
                commentVO.setCustomerId(rs.getLong("CUSTOMER_ID"));
                commentVO.setContent(rs.getString("CONTENT"));
                commentVO.setWriteDate(rs.getDate("WRITE_DATE"));
                commentList.add(commentVO);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return commentList;
    }
    // 댓글 상세
    public CommentVO getCommentDetail(Long boardId, Long replyId) {
        CommentVO commentVO = new CommentVO();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM REPLY WHERE ID = " + boardId + " AND POST_ID = " + replyId;
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                commentVO.setId(rs.getLong("ID"));
                commentVO.setPostId(rs.getLong("POST_ID"));
                commentVO.setCustomerId(rs.getLong("CUSTOMER_ID"));
                commentVO.setContent(rs.getString("CONTENT"));
                commentVO.setWriteDate(rs.getDate("WRITE_DATE"));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return commentVO;
    }
    // 댓글 등록
    public boolean insertComment(CommentVO commentVO) {
        boolean isInsert = false;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            // CommentVO에서 PostId가 null이 아닌 경우에만 작업을 수행
            if (commentVO.getId() != null) {
                String sql = "INSERT INTO REPLY VALUES(REPLY_ID_SEQ.NEXTVAL, ?, ?, ?, SYSDATE)";
                System.out.println("sql 확인 : "+sql);
                pStmt = conn.prepareStatement(sql);
//                Long customerId = commentVO.getCustomerId() != null ? commentVO.getCustomerId() : 0L;
                pStmt.setLong(1, commentVO.getId());
                pStmt.setLong(2, commentVO.getCustomerId());
                pStmt.setString(3, commentVO.getContent());
                int result = pStmt.executeUpdate();
                if(result == 1) isInsert = true;
            } else {
                System.out.println("댓글 인서트 불가");
            }


//            String sql = "INSERT INTO REPLY (ID, CUSTOMER_ID, POST_ID, WRITE_DATE, CONTENT) " +
//                    "VALUES(reply_id_sequence.NEXTVAL, ?, ?, SYSDATE, ?)";
//            pStmt = conn.prepareStatement(sql);
//            pStmt.setString(1, commentVO.getCustomerId());
//            pStmt.setLong(2, commentVO.getPostId());
//            pStmt.setString(3, commentVO.getContent());

//            conn = Common.getConnection();
//            String sql = "INSERT INTO REPLY (ID, CUSTOMER_ID, POST_ID, WRITE_DATE, CONTENT) " +
//                    "VALUES(reply_sequence.NEXTVAL, ?, ?, SYSDATE, ?)";
//            pStmt = conn.prepareStatement(sql);
//            pStmt.setString(1, commentVO.getCustomerId());
//            pStmt.setLong(2, commentVO.getPostId());
//            pStmt.setString(3, commentVO.getContent());

//            int result = pStmt.executeUpdate();
//            if(result == 1) isInsert = true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
        return isInsert;
    }
    // 댓글 수정
    public boolean updateComment(CommentVO commentVO) {
        String sql = "UPDATE COMMENTS SET CONTENT = ? WHERE COMMENT_ID = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, commentVO.getContent());
            pStmt.setLong(2, commentVO.getId());
            int result = pStmt.executeUpdate();
            if(result == 1) return true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
        return false;
    }
    // 댓글 삭제
    public boolean deleteComment(Long id) {
        String sql = "DELETE FROM REPLY WHERE ID = ?";
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
}
