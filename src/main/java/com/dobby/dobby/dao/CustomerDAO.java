package com.dobby.dobby.dao;

import com.dobby.dobby.common.Common;
import com.dobby.dobby.vo.CustomerVO;

import java.awt.desktop.SystemEventListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

        private Connection conn = null;
        private Statement stmt = null;
        private ResultSet rs = null;
        private PreparedStatement pStmt = null;

        // 회원 가입 여부 확인
        public boolean regMemberCheck(String id, String url) {
            boolean isNotReg = false;
            try {
                conn = Common.getConnection();
                stmt = conn.createStatement();
                String sql = "SELECT * FROM CUSTOMER INNER JOIN COMPANY ON CUSTOMER.EMAIL = " + "'" + id + "'" + " OR COMPANY.URL = " + "'" + url + "'";

                rs = stmt.executeQuery(sql);
                if(rs.next()) isNotReg = false;
                else isNotReg = true;
            } catch(Exception e) {
                e.printStackTrace();
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
            return isNotReg; // 가입 되어 있으면 false, 가입이 안되어 있으면 true
        }
        // 로그인 체크
        public boolean loginCheck(String id, String pwd) {
            try {
                conn = Common.getConnection();
                stmt = conn.createStatement(); // Statement 객체 얻기
                String sql = "SELECT * FROM CUSTOMER WHERE EMAIL = " + "'" + id + "'";
                rs = stmt.executeQuery(sql);

                while(rs.next()) { // 읽은 데이타가 있으면 true
                    String sqlId = rs.getString("EMAIL"); // 쿼리문 수행 결과에서 ID값을 가져 옴
                    String sqlPwd = rs.getString("PASSWORD");
                    System.out.println("EMAIL : " + sqlId);
                    System.out.println("PASSWORD : " + sqlPwd);
                    if(id.equals(sqlId) && pwd.equals(sqlPwd)) {
                        Common.close(rs);
                        Common.close(stmt);
                        Common.close(conn);
                        return true;
                    }
                }
                Common.close(rs);
                Common.close(stmt);
                Common.close(conn);
            } catch(Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        // 회원정보 조회
        public List<CustomerVO> memberSelect(String getName) {
            List<CustomerVO> list = new ArrayList<>();
            String sql = null;
            System.out.println("EMAIL : " + getName);
            try {
                conn = Common.getConnection();
                stmt = conn.createStatement();
                if(getName.equals("ALL")) sql = "SELECT * FROM CUSTOMER";
                else sql = "SELECT * FROM CUSTOMER WHERE EMAIL = " + "'" + getName + "'";
                rs = stmt.executeQuery(sql);

                while(rs.next()) {
                    String id = rs.getString("ID");
                    String companyId = rs.getString("COMPANY_ID");
                    String name = rs.getString("NAME");
                    String nickName = rs.getString("NICK_NAME");
                    String contactNumber = rs.getString("CONTACT_NUMBER");
                    String email = rs.getString("EMAIL");
                    String password = rs.getString("PASSWORD");
                    String role = rs.getString("ROLE");
                    String isActive = rs.getString("IS_ACTIVE");

                    CustomerVO vo = new CustomerVO();
                    vo.setId(id);
                    vo.setCompanyId(companyId);
                    vo.setName(name);
                    vo.setNickName(nickName);
                    vo.setContactNumber(contactNumber);
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

    // COMPANY에서 EMAIL로 ID 추출
    public String companyEmailId(String getName) {
            String companyId = getName;
            System.out.println("getName : " + getName);
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String findCompanyIdQuery = "SELECT ID FROM COMPANY WHERE URL LIKE '%" + getName + "%'";
            rs = stmt.executeQuery(findCompanyIdQuery);

            while(rs.next()) {
                companyId = rs.getString("ID");
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("companyId : " + companyId);
        return companyId;
    }

    // 개인 회원 가입
    public boolean memberRegister( String id, String pwd, String name, String nickName, String role) {
        int result = 0;
        String idEmail = id.substring(id.indexOf("@")+1);
        String companyId = companyEmailId(idEmail);
        System.out.println("companyEmailId : " + companyId);
        String sql = "INSERT INTO CUSTOMER (ID, COMPANY_ID, EMAIL, PASSWORD, NAME, NICK_NAME, ROLE, IS_ACTIVE) VALUES (CUSTOMER_ID_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, 'active')";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, companyId);
            pStmt.setString(2, id);
            pStmt.setString(3, pwd);
            pStmt.setString(4, name);
            pStmt.setString(5, nickName);
            pStmt.setString(6, role);

            System.out.println(companyId+id+pwd+name+nickName);
            result = pStmt.executeUpdate();
            System.out.println("회원 가입 DB 결과 확인 : " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);

        if(result == 1) return true;
        else return false;
    }
    // 기업 개인 회원 가입
    public boolean comMemberRegister(String id, String pwd, String name, String phone, String role) {
        int result = 0;
        String idEmail = id.substring(id.indexOf("@")+1);
        String companyId = companyEmailId(idEmail);
        System.out.println("companyEmailId : " + companyId);
        String sql = "INSERT INTO CUSTOMER (ID, COMPANY_ID, EMAIL, PASSWORD, NAME, CONTACT_NUMBER, ROLE, IS_ACTIVE) VALUES (CUSTOMER_ID_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, 'inactive')";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, companyId);
            pStmt.setString(2, id);
            pStmt.setString(3, pwd);
            pStmt.setString(4, name);
            pStmt.setString(5, phone);
            pStmt.setString(6, role);

            System.out.println(companyId+id+pwd+name+phone);
            result = pStmt.executeUpdate();
            System.out.println("기업 개인 회원 가입 결과 확인 : " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);

        if(result == 1) return true;
        else return false;
    }

    // POST : 개인 정보 수정
    public boolean memUpdate(String id, String name, String nickName, String pwd) {
        String sql = "UPDATE CUSTOMER SET NAME = ?, NICK_NAME = ?, PASSWORD = ? WHERE EMAIL = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, name);
            pStmt.setString(2, nickName);
            pStmt.setString(3, pwd);
            pStmt.setString(4, id);

            int result = pStmt.executeUpdate();
            System.out.println(name+ nickName+ pwd+ id);
            System.out.println("RESULT : " + result);
            if(result == 1) return true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
        return false;
    }
    // POST : 기업 개인 정보 수정
    public boolean memUpdate2(String id, String name, String phone, String pwd) {
        String sql = "UPDATE CUSTOMER SET NAME = ?, CONTACT_NUMBER = ?, PASSWORD = ? WHERE EMAIL = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, name);
            pStmt.setString(2, phone);
            pStmt.setString(3, pwd);
            pStmt.setString(4, id);

            int result = pStmt.executeUpdate();
            System.out.println(name+ phone+ pwd+ id);
            System.out.println("RESULT : " + result);
            if(result == 1) return true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
        return false;
    }

    // 회원 탈퇴
    public boolean memberDelete(String id) {
        int result = 0;
        String sql = "UPDATE CUSTOMER SET IS_ACTIVE = 'quit' WHERE EMAIL = ?";

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
