package com.dobby.dobby.dao;

import com.dobby.dobby.common.Common;
import com.dobby.dobby.vo.CompanyAdListVO;
import com.dobby.dobby.vo.CompanyJobListVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class CompanyMyPageDAO {

    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    // 1. 기업 마이페이지 내 채용 공고 리스트 조회
    public List<CompanyJobListVO> companyJobListSelect(String getCompanyId) {
        List<CompanyJobListVO> list = new ArrayList<>();
        String sql = null;
        System.out.println("COMPANYID : " + getCompanyId);
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
//            sql = "SELECT * FROM JOBPOSTING WHERE COMPANY_ID = ?";
            sql = "SELECT * FROM JOBPOSTING";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String id = rs.getString("ID");
                String companyId = rs.getString("COMPANY_ID");
                String title = rs.getString("TITLE");
                String description = rs.getString("DESCRIPTION");
                String qualification = rs.getString("QUALIFICATION");
                Timestamp deadLine = rs.getTimestamp("DEADLINE");
                String image = rs.getString("IMAGE");

                // CompanyJobListVO에 기업 정보 설정
                CompanyJobListVO vo = new CompanyJobListVO();
                vo.setId(id);
                vo.setCompanyId(companyId);
                vo.setTitle(title);
                vo.setDescription(description);
                vo.setQualification(qualification);
                vo.setDeadLine(deadLine);
                vo.setImage(image);
                // 리스트에 추가
                list.add(vo);
                System.out.println(list);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    // 2. 기업 마이 페이지 내 광고 리스트 조회
    public List<CompanyAdListVO> companyAdListSelect(String getCompanyId) {
        List<CompanyAdListVO> list = new ArrayList<>();
        String sql = null;
        System.out.println("COMPANYID : " + getCompanyId);
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            sql = "SELECT * FROM ADVERTISEMENT WHERE COMPANY_ID IN (SELECT COMPANY_ID FROM CUSTOMER WHERE EMAIL = '" + getCompanyId + "')";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String id = rs.getString("ID");
                String companyId = rs.getString("COMPANY_ID");
                String image = rs.getString("IMAGE");
                Timestamp startDate = rs.getTimestamp("START_DATE");
                Timestamp endDate = rs.getTimestamp("END_DATE");
                String adfee = rs.getString("AD_FEE");

                // COMPANYADLISTVO 에 광고 내용 담기
                CompanyAdListVO vo = new CompanyAdListVO();
                vo.setId(id);
                vo.setCompanyId(companyId);
                vo.setImage(image);
                vo.setStartDate(startDate);
                vo.setEndDate(endDate);
                vo.setAdfee(adfee);
                // 리스트에 추가
                list.add(vo);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return list;
    }
}

