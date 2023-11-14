package com.dobby.dobby.dao;

import com.dobby.dobby.common.Common;
import com.dobby.dobby.vo.manager.ManageCompanyCustomerInfoVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ManageCompanyCustomerInfoDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    // 관리자 페이지 개인 회원 정보 관리
    public List<ManageCompanyCustomerInfoVO> getInfo() {
        List<ManageCompanyCustomerInfoVO> list = new ArrayList<>();
        String sql = null;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            sql = "SELECT " +
                    "CUSTOMER.ID AS ID, " +
                    "COMPANY.ID AS COMPANY_ID, " +
                    "COMPANY.NAME AS COMPANY_NAME, " +
                    "COMPANY.CEO AS CEO, " +
                    "COMPANY.SIZE_SCALE AS SIZE_SCALE, " +
                    "COMPANY.BUSINESS_CATEGORY AS BUSINESS_CATEGORY, " +
                    "COMPANY.URL AS URL, " +
                    "CUSTOMER.CONTACT_NUMBER AS CUSTOMER_CONTACT_NUMBER, " +
                    "COMPANY.CONTACT_NUMBER AS COMPANY_CONTACT_NUMBER, " +
                    "CUSTOMER.EMAIL AS EMAIL, " +
                    "CUSTOMER.PASSWORD AS PASSWORD, " +
                    "CUSTOMER.ROLE AS ROLE, " +
                    "CUSTOMER.IS_ACTIVE AS IS_ACTIVE " +
                    "FROM CUSTOMER " +
                    "INNER JOIN COMPANY ON CUSTOMER.COMPANY_ID = COMPANY.ID " +
                    "WHERE CUSTOMER.ROLE = 'company'" +
                    "ORDER BY DECODE(IS_ACTIVE, 'inactive', 0, 'active', 1, 'quit', 2), id DESC";
            rs = stmt.executeQuery(sql);
            System.out.println(rs);

            while(rs.next()) {
                String id = rs.getString("ID");
                String companyId = rs.getString("COMPANY_ID");
                String companyName = rs.getString("COMPANY_NAME");
                String ceo = rs.getString("CEO");
                String sizeScale = rs.getString("SIZE_SCALE");
                String businessCategory = rs.getString("BUSINESS_CATEGORY");
                String companyUrl = rs.getString("URL");
                String customerContact = rs.getString("CUSTOMER_CONTACT_NUMBER");
                String companyContact = rs.getString("COMPANY_CONTACT_NUMBER");
                String email = rs.getString("EMAIL");
                String password = rs.getString("PASSWORD");
                String role = rs.getString("ROLE");
                String isActive = rs.getString("IS_ACTIVE");


                ManageCompanyCustomerInfoVO vo = new ManageCompanyCustomerInfoVO();
                vo.setId(id);
                vo.setCompanyId(companyId);
                vo.setCompanyName(companyName);
                vo.setCeo(ceo);
                vo.setSizeScale(sizeScale);
                vo.setBusinessCategory(businessCategory);
                vo.setCompanyUrl(companyUrl);
                vo.setCustomerContact(customerContact);
                vo.setCompanyContact(companyContact);
                vo.setEmail(email);
                vo.setPassword(password);
                vo.setRole(role);
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
