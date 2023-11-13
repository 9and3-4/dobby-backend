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
                    "COMPANY.NAME AS COMPANYNAME, " +
                    "COMPANY.CEO AS COMPANYCEO, " +
                    "COMPANY.SIZE_SCALE AS COMPANYSIZESCALE, " +
                    "COMPANY.BUSINESS_CATEGORY AS COMPANYBUSINESSCATEGORY, " +
                    "COMPANY.URL AS COMPANYURL, " +
                    "CUSTOMER.CONTACT_NUMBER AS CUSTOMERCONTACTNUMBER, " +
                    "CUSTOMER.EMAIL AS CUSTOMEREMAIL, " +
                    "CUSTOMER.PASSWORD AS CUSTOMERPASSWORD, " +
                    "CUSTOMER.ROLE AS CUSTOMERROLE, " +
                    "CUSTOMER.IS_ACTIVE AS CUSTOMERISACTIVE " +
                    "FROM CUSTOMER " +
                    "INNER JOIN COMPANY ON CUSTOMER.COMPANY_ID = COMPANY.ID " +
                    "WHERE CUSTOMER.ROLE = 'company'";
            rs = stmt.executeQuery(sql);
            System.out.println(rs);

            while(rs.next()) {
                String companyName = rs.getString("COMPANYNAME");
                String ceo = rs.getString("COMPANYCEO");
                String sizeScale = rs.getString("COMPANYSIZESCALE");
                String businessCategory = rs.getString("COMPANYBUSINESSCATEGORY");
                String companyUrl = rs.getString("COMPANYURL");
                String customerContact = rs.getString("CUSTOMERCONTACTNUMBER");
                String email = rs.getString("CUSTOMEREMAIL");
                String password = rs.getString("CUSTOMERPASSWORD");
                String role = rs.getString("CUSTOMERROLE");
                String isActive = rs.getString("CUSTOMERISACTIVE");


                ManageCompanyCustomerInfoVO vo = new ManageCompanyCustomerInfoVO();
                vo.setCompanyName(companyName);
                vo.setCeo(ceo);
                vo.setSizeScale(sizeScale);
                vo.setBusinessCategory(businessCategory);
                vo.setCompanyUrl(companyUrl);
                vo.setCustomerContact(customerContact);
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
