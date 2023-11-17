package com.dobby.dobby.dao;

import com.dobby.dobby.common.Common;
import com.dobby.dobby.vo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyReviewDAO {

    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;


    // 1. 기업 정보 조회
    public List<CompanyInfoVO> companyInfoSelect(String getId) {
        List<CompanyInfoVO> list = new ArrayList<>();
        String sql = null;
        System.out.println("ID : " + getId);
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            sql = "SELECT ID, NAME, SIZE_SCALE, CEO, CONTACT_NUMBER, URL, BUSINESS_CATEGORY, ADDRESS, FOUNDED_YEAR, STAFF_SIZE, ANNUAL_INCOME, PROFILE, LOGO " +
                    "FROM COMPANY WHERE ID = " + getId;
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String id = rs.getString("ID");
                String companyName = rs.getString("NAME");
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

                // CompanyInfoVO에 기업 정보 설정
                CompanyInfoVO vo = new CompanyInfoVO();
                vo.setId(id);
                vo.setName(companyName);
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
                // 리스트에 추가
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


    // 2. 모든 기업 정보 조회
    public List<CompanyInfoVO> companyInfoallSelect() {
        List<CompanyInfoVO> list = new ArrayList<>();
        String sql = null;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            sql = "SELECT ID, NAME, SIZE_SCALE, CEO, CONTACT_NUMBER, URL, BUSINESS_CATEGORY, ADDRESS, FOUNDED_YEAR, STAFF_SIZE, ANNUAL_INCOME, PROFILE, LOGO " +
                    "FROM COMPANY ";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String id = rs.getString("ID");
                String companyName = rs.getString("NAME");
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

                // CompanyInfoVO에 기업 정보 설정
                CompanyInfoVO vo = new CompanyInfoVO();
                vo.setId(id);
                vo.setName(companyName);
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
                // 리스트에 추가
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

    // 3. 현직자들이 작성한 회사 리뷰 조회
    public List<CompanyFeedbackVO> companyFeedbackSelect(String getCompanyId) {
        List<CompanyFeedbackVO> list = new ArrayList<>();
        String sql = null;
        System.out.println("COMPANYID : " + getCompanyId);
        try{
            conn = Common.getConnection();
            stmt = conn.createStatement();
            sql = "SELECT * FROM REVIEW WHERE COMPANY_ID = " + "'" + getCompanyId + "'";
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                String id = rs.getString("ID");
                String companyId = rs.getString("COMPANY_ID");
                int rating = rs.getInt("RATING");
                String content = rs.getString("CONTENT");
                Timestamp writeDate = rs.getTimestamp("WRITE_DATE");

                // CompanyFeedback에 기업 리뷰 담기
                CompanyFeedbackVO vo = new CompanyFeedbackVO();
                vo.setId(id);
                vo.setCompanyId(companyId);
                vo.setRating(rating);
                vo.setContent(content);
                vo.setWriteDate(writeDate);
                // 리스트에 추가
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e)  {
            e.printStackTrace();
        }
        return list;
    }

    // 4. 회사 아이디로 게시판 들고 오기
    public List<CompanyPostVO> companyPostSelect(String getCompanyId) {
        List<CompanyPostVO> list = new ArrayList<>();
        String sql = null;
        System.out.println("COMPANYID : " + getCompanyId);
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            sql = "SELECT P.*, CO.ID AS COMPANY_ID FROM POST P " +
                    "JOIN CUSTOMER C ON P.CUSTOMER_ID = C.ID " +
                    "JOIN COMPANY CO ON C.COMPANY_ID = CO.ID " +
                    "WHERE CO.ID = '" + getCompanyId + "'" + "AND P.TOPIC_ID <> 14" ;
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                String id = rs.getString("ID");
                String customerId = rs.getString("CUSTOMER_ID");
                String topicId = rs.getString("TOPIC_ID");
                String title = rs.getString("TITLE");
                String content = rs.getString("CONTENT");
                Timestamp writeDate = rs.getTimestamp("WRITE_DATE");
                int viewCount = rs.getInt("VIEW_COUNT");
                int likeCount = rs.getInt("LIKE_COUNT");

                // CompanyPostVO에 게시글 정보 담기
                CompanyPostVO vo = new CompanyPostVO();
                vo.setId(id);
                vo.setCustomerId(customerId);
                vo.setTopicId(topicId);
                vo.setTitle(title);
                vo.setContent(content);
                vo.setWriteDate(writeDate);
                vo.setViewCount(viewCount);
                vo.setLikeCount(likeCount);
                //리스트에 추가
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch  (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 5. 회사가 올린 채용 공고 게시판 가져오기
    public List<CompanyJobPostingVO> companyJobPostingSelect(String getCompanyId) {
        List<CompanyJobPostingVO> list = new ArrayList<>();
        String sql = null;
        System.out.println("COMPANYID : " + getCompanyId);
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            sql = "SELECT * FROM JOBPOSTING WHERE COMPANY_ID = " + getCompanyId;
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                String id = rs.getString("ID");
                String companyId = rs.getString("COMPANY_ID");
                String title = rs.getString("TITLE");
                String description = rs.getString("DESCRIPTION");
                String qualification = rs.getString("QUALIFICATION");
                Timestamp deadline = rs.getTimestamp("DEADLINE");
                String image = rs.getString("IMAGE");

                // CompanyJobPostingVO 채용 공고 담기
                CompanyJobPostingVO vo = new CompanyJobPostingVO();
                vo.setId(id);
                vo.setCompanyId(companyId);
                vo.setTitle(title);
                vo.setDescription(description);
                vo.setQualification(qualification);
                vo.setDeadline(deadline);
                vo.setImage(image);
                //리스트에 추가
                list.add(vo);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }



}


