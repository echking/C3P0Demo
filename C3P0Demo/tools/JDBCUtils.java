package com.C3P0Demo.tools;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author ECHK1NG
 * @time 2022/10/14 9:14
 */
public class JDBCUtils {
    /**
     * 连接池
     */
    private static ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource("igeekTest");

    /**
     * 数据源
     */
    public static DataSource getDataSource(){
        return comboPooledDataSource;
    }

    /**
     * 连接对象
     */
    public static Connection getConnection(){
        Connection conn=null;
        try{
            conn=getDataSource().getConnection();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
}

