package com.dobby.dobby.dao;

import com.dobby.dobby.common.Common;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.*;
import java.util.Map;

public class AdvertisementDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;
    public boolean adRegister(String id, String image, Timestamp startDate, Timestamp endDate, String adFee) {

        int result = 0;
        String sql = "INSERT INTO ADVERTISEMENT(ID, COMPANY_ID, IMAGE, START_DATE, END_DATE, AD_FEE) " +
                "VALUES (ADVERTISEMENT_ID_SEQ.NEXTVAL, (SELECT COMPANY_ID FROM CUSTOMER WHERE EMAIL = ?), ?, ?, ?, ?)";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, id);
            pStmt.setString(2, image);
            pStmt.setTimestamp(3, startDate);
            pStmt.setTimestamp(4, endDate);
            pStmt.setString(5, adFee);
            result = pStmt.executeUpdate();
            System.out.println("광고 신청 결과 확인 : " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);

        if(result == 1) return true;
        else return false;
    }
}
