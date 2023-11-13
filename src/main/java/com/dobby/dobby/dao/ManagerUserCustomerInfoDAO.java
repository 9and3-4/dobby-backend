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
                    "CUSTOMER.NAME AS CUSTOMERNAME, " +
                    "CUSTOMER.NICK_NAME AS CUSTOMERNICKNAME, " +
                    "CUSTOMER.EMAIL AS CUSTOMEREMAIL, " +
                    "CUSTOMER.PASSWORD AS CUSTOMERPASSWORD, " +
                    "CUSTOMER.ROLE AS CUSTOMERROLE, " +
                    "COMPANY.NAME AS COMPANYNAME, " +
                    "CUSTOMER.IS_ACTIVE AS CUSTOMERISACTIVE " +
                    "FROM CUSTOMER " +
                    "INNER JOIN COMPANY ON CUSTOMER.COMPANY_ID = COMPANY.ID " +
                    "WHERE CUSTOMER.ROLE = 'user'";

            rs = stmt.executeQuery(sql);
            System.out.println(rs);

            while(rs.next()) {
                String name = rs.getString("CUSTOMERNAME");
                String nickName = rs.getString("CUSTOMERNICKNAME");
                String email = rs.getString("CUSTOMEREMAIL");
                String password = rs.getString("CUSTOMERPASSWORD");
                String role = rs.getString("CUSTOMERROLE");
                String companyName = rs.getString("COMPANYNAME");
                String isActive = rs.getString("CUSTOMERISACTIVE");

                ManagerUserCustomerInfoVO vo = new ManagerUserCustomerInfoVO();
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
