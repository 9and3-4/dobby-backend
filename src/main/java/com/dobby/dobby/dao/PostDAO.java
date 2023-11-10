package com.dobby.dobby.dao;

import com.dobby.dobby.common.Common;
import com.dobby.dobby.vo.PostVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    public List<PostVO> myPostSelect(String getEmail) {
        List<PostVO> list = new ArrayList<>();
        String sql = null;
        System.out.println("EMAIL : " + getEmail);
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            sql = "SELECT POST.*, CUSTOMER.NICK_NAME FROM POST " +
                    "INNER JOIN CUSTOMER ON POST.CUSTOMER_ID = CUSTOMER.ID " +
                    "WHERE CUSTOMER.EMAIL = '" + getEmail + "'";


            // 쿼리 결과값 가저옴
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                String id = rs.getString("ID");
                String customerId = rs.getString("CUSTOMER_ID");
                String topicId = rs.getString("TOPIC_ID");
                String title = rs.getString("TITLE");
                String content = rs.getString("CONTENT");
                String nickName = rs.getString("NICK_NAME");
                Timestamp writeDate = rs.getTimestamp("WRITE_DATE");
                int viewCount = rs.getInt("VIEW_COUNT");
                int likeCount = rs.getInt("LIKE_COUNT");

                PostVO vo = new PostVO();
                vo.setId(id);
                vo.setCustomerId(customerId);
                vo.setTopicId(topicId);
                vo.setTitle(title);
                vo.setContent(content);
                vo.setWriteDate(writeDate);
                vo.setViewCount(viewCount);
                vo.setLikeCount(likeCount);
                vo.setNickName(nickName);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
        }
    }



