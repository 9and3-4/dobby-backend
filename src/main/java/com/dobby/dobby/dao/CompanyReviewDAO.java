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




//            // 2. 리뷰 조회
//            sql = "SELECT " +
//                    "REVIEW.ID AS REVIEW_ID, " +
//                    "REVIEW.CUSTOMER_ID AS CUSTOMER_ID, " +
//                    "CUSTOMER.NICK_NAME AS CUSTOMER_NICK_NAME, " +
//                    "COMPANY.ID AS COMPANY_ID, " +
//                    "COMPANY.NAME AS COMPANY_NAME, " +
//                    "REVIEW.RATING, " +
//                    "REVIEW.CONTENT, " +
//                    "REVIEW.WRITE_DATE " +
//                    "FROM REVIEW " +
//                    "JOIN CUSTOMER ON REVIEW.CUSTOMER_ID = CUSTOMER.ID " +
//                    "JOIN COMPANY ON REVIEW.COMPANY_ID = COMPANY.ID";
//            rs = stmt.executeQuery(sql);
//
//            while (rs.next()) {
//                String compapyId = rs.getString("ID");
//                String companyName = rs.getString("COMPANY_NAME");
//                String sizeScale = rs. getString("SIZE_SCALE");
//                String ceo = rs.getString("CEO");
//                String contactNumber = rs.getString("CONTACT_NUMBER");
//                String url = rs.getString("URL");
//                String businessCategory = rs.getString("BUSINESS_CATEGORY");
//                String address = rs.getString("ADDRESS");
//                int foundedYear = rs.getInt("FOUNDED_YEAR");
//                int staffSize = rs.getInt("STAFF_SIZE");
//                int annualIncome = rs.getInt("ANNUAL_INCOME");
//                String profile = rs.getString("PROFILE");
//                String logo = rs.getString("LOGO");
//
//
//                // CompanyReviewVO에 기업 정보 설정
//                CompanyReviewVO vo = new CompanyReviewVO();
//                vo.setCompanyId(compapyId);
//                vo.setCompanyName(companyName);
//                vo.setSizeScale(sizeScale);
//                vo.setCeo(ceo);
//                vo.setContactNumber(contactNumber);
//                vo.setUrl(url);
//                vo.setBusinessCategory(businessCategory);
//                vo.setAddress(address);
//                vo.setFoundedYear(foundedYear);
//                vo.setStaffSize(staffSize);
//                vo.setAnnualIncome(annualIncome);
//                vo.setProfile(profile);
//                vo.setLogo(logo);
//                // 리스트에 추가
//                list.add(vo);
//            }
//
//            // 3. 게시글 조회
//            sql = "SELECT " +
//                    "POST.ID AS POST_ID, " +
//                    "POST.CUSTOMER_ID AS CUSTOMER_ID, " +
//                    "CUSTOMER.NICK_NAME AS CUSTOMER_NICKNAME, " +
//                    "COMPANY.NAME AS COMPANY_NAME, " +
//                    "POST.TITLE, " +
//                    "POST.CONTENT, " +
//                    "POST.WRITE_DATE, " +
//                    "POST.VIEW_COUNT, " +
//                    "POST.LIKE_COUNT " +
//                    "FROM POST " +
//                    "JOIN CUSTOMER ON POST.CUSTOMER_ID = CUSTOMER.ID " +
//                    "JOIN COMPANY ON CUSTOMER.COMPANY_ID = COMPANY.ID " +
//                    "WHERE POST.TOPIC_ID = 14";
//            rs = stmt.executeQuery(sql);
//
//            while (rs.next()) {
//                CompanyReviewVO postInfo = new CompanyReviewVO();
//                postInfo.setPostId(rs.getString("POST_ID"));
//                postInfo.setCustomerId(rs.getString("CUSTOMER_ID"));
//                postInfo.setCustomerNickName(rs.getString("CUSTOMER_NICKNAME"));
//                postInfo.setCompanyName(rs.getString("COMPANY_NAME"));
//                postInfo.setPostTitle(rs.getString("TITLE"));
//                postInfo.setPostContent(rs.getString("CONTENT"));
//                postInfo.setPostWriteDate(rs.getTimestamp("WRITE_DATE"));
//                postInfo.setViewCount(rs.getInt("VIEW_COUNT"));
//                postInfo.setLikeCount(rs.getInt("LIKE_COUNT"));
//
//                list.add(postInfo);
//            }
//
//            // 4. 채용 정보 조회
//            sql = "SELECT " +
//                    "JOBPOSTING.ID AS JOBPOSTING_ID, " +
//                    "JOBPOSTING.COMPANY_ID AS COMPANY_ID, " +
//                    "COMPANY.NAME AS COMPANY_NAME, " +
//                    "JOBPOSTING.TITLE, " +
//                    "JOBPOSTING.DESCRIPTION, " +
//                    "JOBPOSTING.QUALIFICATION, " +
//                    "JOBPOSTING.DEADLINE, " +
//                    "JOBPOSTING.IMAGE " +
//                    "FROM JOBPOSTING " +
//                    "JOIN COMPANY ON JOBPOSTING.COMPANY_ID = COMPANY.ID";
//            rs = stmt.executeQuery(sql);
//
//            while (rs.next()) {
//                CompanyReviewVO jobPostingInfo = new CompanyReviewVO();
//                jobPostingInfo.setJobPostingId(rs.getString("JOBPOSTING_ID"));
//                jobPostingInfo.setCompanyId(rs.getString("COMPANY_ID"));
//                jobPostingInfo.setCompanyName(rs.getString("COMPANY_NAME"));
//                jobPostingInfo.setJobPostingTitle(rs.getString("TITLE"));
//                jobPostingInfo.setDescription(rs.getString("DESCRIPTION"));
//                jobPostingInfo.setQualification(rs.getString("QUALIFICATION"));
//                jobPostingInfo.setDeadline(rs.getTimestamp("DEADLINE"));
//                jobPostingInfo.setJobPostingImg(rs.getString("IMAGE"));
//
//                list.add(jobPostingInfo);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            Common.close(rs);
//            Common.close(stmt);
//            Common.close(conn);
//        }
//
//        return list;
//    }
//}
