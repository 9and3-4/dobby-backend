package com.dobby.dobby.dao;

import com.dobby.dobby.common.Common;
import com.dobby.dobby.vo.CompanyVO;
import com.dobby.dobby.vo.CustomerVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO {

    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;
    // 회원의 회사 조회
    public List<CompanyVO> companySelect(String getName) {
        List<CompanyVO> list = new ArrayList<>();
        String sql = null;
        System.out.println("EMAIL : " + getName);
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            sql = "SELECT CO.ID AS ID, " +
                    "CO.NAME AS NAME, " +
                    "CO.SIZE_SCALE AS SIZESCALE, " +
                    "CO.CEO AS CEO, " +
                    "CO.CONTACT_NUMBER AS CONTACTNUMBER, " +
                    "CO.URL AS URL, " +
                    "CO.BUSINESS_CATEGORY AS BUSINESSCATEGORY, " +
                    "CO.ADDRESS AS ADDRESS, " +
                    "CO.FOUNDED_YEAR AS FOUNDEDYEAR, " +
                    "CO.STAFF_SIZE AS STAFFSIZE, " +
                    "CO.ANNUAL_INCOME AS ANNUALINCOME, " +
                    "CO.PROFILE AS PROFILE, " +
                    "CO.LOGO AS LOGO, " +
                    "CO.IS_ACTIVE AS ISACTIVE " +
                    "FROM CUSTOMER C " +
                    "JOIN COMPANY CO ON C.COMPANY_ID = CO.ID " +
                    "WHERE C.EMAIL =" +  "'" + getName + "'";


            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                String id = rs.getString("ID");
                String name = rs.getString("NAME");
                String sizeScale = rs.getString("SIZESCALE");
                String ceo = rs.getString("CEO");
                String contactNumber = rs.getString("CONTACTNUMBER");
                String url = rs.getString("URL");
                String businessCategory = rs.getString("BUSINESSCATEGORY");
                String address = rs.getString("ADDRESS");
                String foundedYear = rs.getString("FOUNDEDYEAR");
                String staffSize = rs.getString("STAFFSIZE");
                String annualIncome = rs.getString("ANNUALINCOME");
                String profile = rs.getString("PROFILE");
                String logo = rs.getString("LOGO");
                String isActive = rs.getString("ISACTIVE");

                CompanyVO vo = new CompanyVO();
                vo.setId(id);
                vo.setName(name);
                vo.setSizeScale(sizeScale);
                vo.setCeo(ceo);
                vo.setContactNumber(contactNumber);
                vo.setUrl(url);
                vo.setBusinessCategory(businessCategory);
                vo.setAddress(address);
                vo.setFoundedYear(foundedYear);
                vo.setStaffSize(staffSize);
                vo.setAnnualIncome(annualIncome);
                vo.setProfile(profile);
                vo.setLogo(logo);
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
