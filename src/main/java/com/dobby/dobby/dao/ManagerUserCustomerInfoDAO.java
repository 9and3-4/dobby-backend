package com.dobby.dobby.dao;
import com.dobby.dobby.common.Common;
import com.dobby.dobby.vo.manager.ManagerUserCustomerInfoVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ManagerUserCustomerInfoDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    // 관리자 페이지 개인 회원 정보 관리
    public List<ManagerUserCustomerInfoVO> getInfo() {
        List<ManagerUserCustomerInfoVO> list = new ArrayList<>();
        String sql = null;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            sql = "SELECT " +
                    "CUSTOMER.ID AS ID, " +
                    "CUSTOMER.NAME AS NAME, " +
                    "CUSTOMER.NICK_NAME AS NICKNAME, " +
                    "CUSTOMER.EMAIL AS EMAIL, " +
                    "CUSTOMER.PASSWORD AS PASSWORD, " +
                    "CUSTOMER.ROLE AS ROLE, " +
                    "COMPANY.NAME AS COMPANY_NAME, " +
                    "CUSTOMER.IS_ACTIVE AS IS_ACTIVE " +
                    "FROM CUSTOMER " +
                    "INNER JOIN COMPANY ON CUSTOMER.COMPANY_ID = COMPANY.ID " +
                    "WHERE CUSTOMER.ROLE = 'user'";

            rs = stmt.executeQuery(sql);
            System.out.println(rs);

            while(rs.next()) {
                String id = rs.getString("ID");
                String name = rs.getString("NAME");
                String nickName = rs.getString("NICKNAME");
                String email = rs.getString("EMAIL");
                String password = rs.getString("PASSWORD");
                String role = rs.getString("ROLE");
                String companyName = rs.getString("COMPANY_NAME");
                String isActive = rs.getString("IS_ACTIVE");

                ManagerUserCustomerInfoVO vo = new ManagerUserCustomerInfoVO();
                vo.setId(id);
                vo.setName(name);
                vo.setNickName(nickName);
                vo.setEmail(email);
                vo.setPassword(password);
                vo.setRole(role);
                vo.setCompanyName(companyName);
                vo.setIsActive(isActive);

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
