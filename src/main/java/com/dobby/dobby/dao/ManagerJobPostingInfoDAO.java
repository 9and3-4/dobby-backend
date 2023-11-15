package com.dobby.dobby.dao;

import com.dobby.dobby.common.Common;
import com.dobby.dobby.vo.manager.ManagerJobPostingInfoVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ManagerJobPostingInfoDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    // 관리자 페이지 채용 공고 정보 조회
    public List<ManagerJobPostingInfoVO> getInfo() {
        List<ManagerJobPostingInfoVO> list = new ArrayList<>();
        String sql = "SELECT\n" +
                "  jp.ID,\n" +
                "  jp.COMPANY_ID,\n" +
                "  c.NAME AS COMPANY_NAME,\n" +
                "  jp.TITLE,\n" +
                "  jp.DESCRIPTION,\n" +
                "  jp.QUALIFICATION,\n" +
                "  jp.DEADLINE,\n" +
                "  jp.IMAGE,\n" +
                "  jp.ISENABLED\n" +
                "FROM\n" +
                "  JOBPOSTING jp\n" +
                "JOIN\n" +
                "  COMPANY c ON jp.COMPANY_ID = c.ID " +
                "ORDER BY jp.ISENABLED ASC, jp.ID DESC";

        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ManagerJobPostingInfoVO vo = new ManagerJobPostingInfoVO();

                // ResultSet으로부터 데이터를 가져와서 VO에 설정
                vo.setJobPostingId(rs.getString("ID"));
                vo.setCompanyId(rs.getString("COMPANY_ID"));
                vo.setCompanyName(rs.getString("COMPANY_NAME"));
                vo.setTitle(rs.getString("TITLE"));
                vo.setDescription(rs.getString("DESCRIPTION"));
                vo.setQualification(rs.getString("QUALIFICATION"));
                vo.setDeadline(rs.getString("DEADLINE"));
                vo.setImage(rs.getString("IMAGE"));
                vo.setIsEnabled(rs.getInt("ISENABLED"));

                // 리스트에 VO 추가
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 자원 해제
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        }
        return list;
    }
}

