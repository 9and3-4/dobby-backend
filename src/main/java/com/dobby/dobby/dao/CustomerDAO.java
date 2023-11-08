package com.dobby.dobby.dao;

import com.dobby.dobby.common.Common;
import com.dobby.dobby.vo.CustomerVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

        private Connection conn = null;
        private Statement stmt = null;
        private ResultSet rs = null;
        private PreparedStatement pStmt = null;

        // 회원 가입 여부 확인
        public boolean regMemberCheck(String id) {
            boolean isNotReg = false;
            try {
                conn = Common.getConnection();
                stmt = conn.createStatement();
                String sql = "SELECT * FROM CUSTOMER WHERE EMAIL = " + "'" + id +"'";
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
    // 회원 역할 조회(개인인지 기업인지)







}
