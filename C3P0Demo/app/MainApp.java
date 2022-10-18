package com.C3P0Demo.app;

import com.C3P0Demo.domain.Product;
import com.C3P0Demo.service.ProductService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author ECHK1NG
 * @time 2022/10/14 9:16
 */
public class MainApp {
    static Scanner sc=new Scanner(System.in);
    static ProductService productService=new ProductService();
    public static void main(String[] args) {
        while(true){
            int option=option();
            if(option==1){
                selectMain();
            }else if(option==2){
                addMain();
            }else if(option==3){
                modifyMain();
            }else if(option==4){
                deleteMain();
            }else{
                System.exit(0);
            }
        }
    }

    /**
     * 功能菜单
     * @return
     */
    static int option(){
        System.out.println("-----功能表-----");
        System.out.println("请输入对应选项完成操作：");
        System.out.println("1.查询功能");
        System.out.println("2.增加功能");
        System.out.println("3.修改功能");
        System.out.println("4.删除功能");
        System.out.println("5.退出系统");
        int option=sc.nextInt();
        return option;
    }

    /**
     * 查询
     */
    static void selectMain(){
        System.out.println("-----请选择选项-----");
        System.out.println("-----1.按照id查询------");
        System.out.println("-----2.退出------");
        int temp=sc.nextInt();
        if(temp==2) main(null);
        System.out.println("请输入要查询的商品id：");
        int id=sc.nextInt();
        Product p = productService.select(id);
        System.out.println(p);
    }

    /**
     * 添加商品
     */
    static void addMain(){
        System.out.println("请输入要添加的商品id：");
        int id=sc.nextInt();
        System.out.println("请输入要添加的商品name：");
        String name=sc.next();
        System.out.println("请输入要添加的商品price：");
        int price=sc.nextInt();
        System.out.println("请输入要添加的商品category_id：");
        String category_id=sc.next();
        Product p=new Product(id,name,price,"1",category_id);
        int temp=productService.add(p);
        System.out.println(temp==1?("添加成功"+p):"添加失败");
    }

    /**
     * 修改商品
     */
    static void modifyMain(){
        System.out.println("请输入要修改的商品id：");
        int id=sc.nextInt();
        System.out.println("请输入修改后的的商品name：");
        String name=sc.next();
        System.out.println("请输入修改后的的商品price：");
        int price=sc.nextInt();
        System.out.println("请输入修改后的的商品category_id：");
        String category_id=sc.next();
        productService.modify(id,name,price,category_id);
    }

    /**
     * 删除功能
     */
    static void deleteMain(){
        System.out.println("请选择删除功能：");
        System.out.println("1.单个删除");
        System.out.println("2.批量删除");
        int option=sc.nextInt();
        if(option==1){
            System.out.println("请输入要删除的商品id：");
            int id=sc.nextInt();
            productService.deleteOne(id);
        }else if(option==2){
            ArrayList<Product> list=new ArrayList<>();
            while(true){
                System.out.println("请输入要删除的商品id：");
                int id=sc.nextInt();
                if(id==-1){
                    System.out.println("以下商品将被删除：");
                    Iterator it=list.iterator();
                    while(it.hasNext()){
                        Product p=(Product) it.next();
                        System.out.println(p);
                    }
                   productService.delete(list);
                    System.out.println("--------删除完成-------");
                    main(null);
                }else{
                    Product product=productService.select(id);
                    System.out.println(product);
                    list.add(product);
                }
            }
        }
    }
}
