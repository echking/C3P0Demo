package com.C3P0Demo.dao;

import com.C3P0Demo.domain.Product;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author ECHK1NG
 * @time 2022/10/14 16:17
 */
public class ProductDao {
    /**
     * 查询方法
     * @param conn
     * @param id
     * @return
     */
    public Product selectProduct(Connection conn,int id){
        QueryRunner queryRunner=new QueryRunner();
        String sql="select * from product where pid=?";
        Object param=id;
        Product product=null;
        try {
            product = queryRunner.query(conn, sql, new BeanHandler<Product>(Product.class), param);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return product;
    }

    /**
     * 增加商品方法
     * @param conn
     * @param p
     * @return
     */
    public int addProduct(Connection conn,Product p){
        QueryRunner qr=new QueryRunner();
        String sql="insert into product(pid,pname,price,category_id) values (?,?,?,?)";
        Object[] params={p.getPid(),p.getPname(),p.getPrice(),p.getCategory_id()};
        int temp=-1;
        try{
            temp=qr.update(conn,sql,params);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * 修改商品方法
     * @param conn
     * @param pid
     * @param pname
     * @param price
     * @param category_id
     * @return
     */
    public int modifyProduct(Connection conn,int pid,String pname,int price,String category_id){
        QueryRunner qr=new QueryRunner();
        String sql="update product set pname=?,price=?,category_id=? where pid=?";
        Object[] params={pid,pname,category_id,pid};
        int temp=-1;
        try{
            temp=qr.update(conn,sql,params);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * 删除单个商品方法
     * @param conn
     * @param id
     * @return
     */
    public int delectOneProduct(Connection conn,int id){
        QueryRunner qr=new QueryRunner();
        String sql="delete from product where pid=?";
        Object param=id;
        int temp=-1;
        try{
            temp=qr.update(conn,sql,param);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return temp;
    }

    public int deleteProduct(Connection conn, ArrayList<Product> list){
        Iterator it= list.iterator();
        QueryRunner qr=new QueryRunner();
        String sql="delete from product where pid=?";
        while(it.hasNext()){
            Product p=(Product) it.next();
            Object param=p.getPid();
            try {
                qr.update(conn, sql, param);
            }catch (SQLException e){
                e.printStackTrace();
                return -1;
            }
        }
        return 1;
    }
}
