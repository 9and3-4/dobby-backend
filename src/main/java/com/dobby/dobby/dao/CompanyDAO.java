package com.dobby.dobby.dao;

import com.dobby.dobby.common.Common;
import com.dobby.dobby.vo.CompanyVO;
import com.dobby.dobby.vo.CustomerVO;
import org.springframework.beans.factory.config.CustomEditorConfigurer;

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
    // 모든 기업 정보 조회
    public List<CompanyVO> companyListSelect(String getName) {
        List<CompanyVO> list = new ArrayList<>();
        String sql = null;
        System.out.println("EMAIL : " + getName);
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            if(getName.equals("ALL")) sql = "SELECT * FROM COMPANY";
            else sql = "SELECT * FROM COMPANY WHERE EMAIL = " + "'" + getName + "'";
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                String id = rs.getString("ID");
                String name = rs.getString("NAME");
                String sizeScale = rs.getString("SIZE_SCALE");
                String ceo = rs.getString("CEO");
                String contactNumber = rs.getString("CONTACT_NUMBER");
                String url = rs.getString("URL");
                String businessCategory = rs.getString("BUSINESS_CATEGORY");
                String address = rs.getString("ADDRESS");
                String foundedYear = rs.getString("FOUNDED_YEAR");
                String staffSize = rs.getString("STAFF_SIZE");
                String annualIncome = rs.getString("ANNUAL_INCOME");
                String profile = rs.getString("PROFILE");
                String logo = rs.getString("LOGO");
                String isActive = rs.getString("IS_ACTIVE");

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

    // 기업 정보 수정
    public boolean companyUpdate(String companyName, String sizeScale, String ceo, String contactNumber, String category, String address,String year,String staff, String income, String profile, String logo, String id) {
        List<CompanyVO> list = new ArrayList<>();
        String sql = null;
        sql = "UPDATE COMPANY SET NAME = ?, SIZE_SCALE = ?, CEO = ?, CONTACT_NUMBER = ?, BUSINESS_CATEGORY = ?, ADDRESS = ?, FOUNDED_YEAR = ?, STAFF_SIZE = ?, ANNUAL_INCOME = ?, PROFILE = ?, LOGO = ?, IS_ACTIVE = 'inactive' WHERE ID IN (SELECT COMPANY_ID FROM CUSTOMER WHERE EMAIL = ?)";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, companyName);
            pStmt.setString(2, sizeScale);
            pStmt.setString(3, ceo);
            pStmt.setString(4, contactNumber);
            pStmt.setString(5, category);
            pStmt.setString(6, address);
            pStmt.setString(7, year);
            pStmt.setString(8, staff);
            pStmt.setString(9, income);
            pStmt.setString(10, profile);
            pStmt.setString(11, logo);
            pStmt.setString(12, id);



            int result = pStmt.executeUpdate();
            System.out.println("RESULT : " + result);
            if(result == 1) return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
        return false;
    }
    // 회원 가입
    public boolean companyRegister(String name, String sizeScale, String ceo, String contactNumber, String url, String category, String address, String year, String staff, String income, String profile, String logo) {
        int result = 0;
        String sql = "INSERT INTO COMPANY (ID, NAME, SIZE_SCALE, CEO, CONTACT_NUMBER, URL, BUSINESS_CATEGORY, ADDRESS, FOUNDED_YEAR, STAFF_SIZE, ANNUAL_INCOME, PROFILE, LOGO, IS_ACTIVE)"+
                "VALUES (COMPANY_ID_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,'inactive')";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, name);
            pStmt.setString(2, sizeScale);
            pStmt.setString(3, ceo);
            pStmt.setString(4, contactNumber);
            pStmt.setString(5, url);
            pStmt.setString(6, category);
            pStmt.setString(7, address);
            pStmt.setString(8, year);
            pStmt.setString(9, staff);
            pStmt.setString(10, income);
            pStmt.setString(11, profile);
            pStmt.setString(12, logo);
            System.out.println("logo : " + logo);
            result = pStmt.executeUpdate();
            System.out.println("기업(Company) 회원가입 결과 확인 : " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);

        if(result == 1) return true;
        else return false;
    }
    // 기업 탈퇴
    public boolean companyDelete(String id) {
        int result = 0;
        String sql = "UPDATE COMPANY SET IS_ACTIVE = 'quit' " +
                "WHERE ID IN (SELECT COMPANY_ID FROM CUSTOMER WHERE EMAIL = ?)";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, id);
            result = pStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
        if(result == 1) return true;
        else return false;
    }
}