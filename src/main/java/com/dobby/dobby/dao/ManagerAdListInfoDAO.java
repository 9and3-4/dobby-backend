package com.dobby.dobby.dao;

import com.dobby.dobby.common.Common;
import com.dobby.dobby.vo.manager.ManagerAdListInfoVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ManagerAdListInfoDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    // 관리자 페이지 채용 공고 정보 조회
    public List<ManagerAdListInfoVO> getInfo() {
        List<ManagerAdListInfoVO> list = new ArrayList<>();
        String sql =
                "SELECT " +
                "A.ID AS ADVERTISEMENT_ID, " +
                "A.COMPANY_ID, " +
                "A.IMAGE, " +
                "A.START_DATE, " +
                "A.END_DATE, " +
                "A.AD_FEE, " +
                "A.ISENABLED, " +
                "C.ID AS COMPANY_ID, " +
                "C.NAME AS COMPANY_NAME " +
                "FROM ADVERTISEMENT A " +
                "JOIN COMPANY C ON A.COMPANY_ID = C.ID";

        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ManagerAdListInfoVO vo = new ManagerAdListInfoVO();
                vo.setAdvertisementID(rs.getString("ADVERTISEMENT_ID"));
                vo.setCompanyId(rs.getString("COMPANY_ID"));
                vo.setCompanyName(rs.getString("COMPANY_NAME"));
                vo.setImage(rs.getString("IMAGE"));
                vo.setStartDate(rs.getTimestamp("START_DATE"));
                vo.setEndDate(rs.getTimestamp("END_DATE"));
                vo.setAdFee(rs.getString("AD_FEE"));
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




