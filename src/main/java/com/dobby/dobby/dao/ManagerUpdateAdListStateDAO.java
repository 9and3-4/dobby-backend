package com.dobby.dobby.dao;

import com.dobby.dobby.common.Common;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ManagerUpdateAdListStateDAO {
    private Connection conn = null;
    private PreparedStatement pStmt = null;

    public boolean setIsActive(String isActive, String id) {
        boolean isUpdate = false;
        String sql = null;
        try {
            conn = Common.getConnection();
            sql = "UPDATE ADVERTISEMENT SET ISENABLED = ? WHERE id = ?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, isActive);
            pStmt.setString(2, id);

            int result = pStmt.executeUpdate();
            if(result == 1) {
                isUpdate = true;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
        return isUpdate;
    }
}
