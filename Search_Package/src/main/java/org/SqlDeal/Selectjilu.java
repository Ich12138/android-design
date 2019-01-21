package org.SqlDeal;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


public class Selectjilu {
    private static Logger logger = Logger.getLogger(Selectjilu.class);
    public static String selectjilu(String uuid){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String expCode = null;
        String expNo = null;
        String state = null;
        Map res = new HashMap();
        Gson gson = new Gson();
        try {

            conn = JDBCUtils_JDBC.getConnection();
            String sql = "Select *from jilu where uuid ='"+uuid+"';";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                 expCode = rs.getString("expCode");
                 expNo   = rs.getString("expNo");

                }
           res.put("expCode",expCode);
            res.put("expNo",expNo);
             state = gson.toJson(res);
            logger.info("从数据库查出来的expCodea和expNo转成 JSON字符串"+state);



        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils_JDBC.release(conn,stmt);
        }
        return state;

    }
}
