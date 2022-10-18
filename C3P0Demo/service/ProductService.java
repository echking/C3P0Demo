package com.C3P0Demo.service;

import com.C3P0Demo.dao.ProductDao;
import com.C3P0Demo.domain.Product;
import com.C3P0Demo.tools.JDBCUtils;
//com.igeekTest.tools;
import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author ECHK1NG
 * @time 2022/10/14 16:23
 */
public class ProductService {
    private ProductDao productDao=new ProductDao();

    /**
     *     查询方法
     */
    public Product select(int id){
        Connection conn=null;
        Product p=null;
        try {
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            p=productDao.selectProduct(conn,id);
            DbUtils.commitAndCloseQuietly(conn);
        }catch (SQLException e){
            DbUtils.rollbackAndCloseQuietly(conn);
            e.printStackTrace();
        }
        return p;
    }

    /**
     * 添加方法
     */
    public int add(Product p){
        Connection conn=null;
        int temp=-1;
        try{
            conn=JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            temp =productDao.addProduct(conn,p);
            DbUtils.commitAndCloseQuietly(conn);
        }catch (SQLException e){
            DbUtils.rollbackAndCloseQuietly(conn);
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * 修改方法
     */
    public int modify(int pid,String pname,int price,String category_id){
        Connection conn=null;
        int temp=-1;
        try{
            conn=JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            temp=productDao.modifyProduct(conn,pid,pname,price,category_id);
            DbUtils.commitAndCloseQuietly(conn);
        }catch (SQLException e){
            DbUtils.rollbackAndCloseQuietly(conn);
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * 删除单个商品方法
     * @param id
     */
    public int deleteOne(int id){
        Connection conn=null;
        int temp=-1;
        try{
            conn=JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            temp=productDao.delectOneProduct(conn,id);
            DbUtils.commitAndCloseQuietly(conn);
        }catch (SQLException e){
            DbUtils.rollbackAndCloseQuietly(conn);
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * 删除多个商品方法
     * @param list
     */
    public int delete(ArrayList<Product> list){
        Connection conn=null;
        int temp=-1;
        try{
            conn=JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            temp=productDao.deleteProduct(conn,list);
            DbUtils.commitAndCloseQuietly(conn);
        }catch (SQLException e){
            DbUtils.rollbackAndCloseQuietly(conn);
            e.printStackTrace();
        }
        return temp;
    }
}
