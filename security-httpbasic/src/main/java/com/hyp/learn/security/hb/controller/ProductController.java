package com.hyp.learn.security.hb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hyp
 * Project name is learn-security
 * Include in com.hyp.learn.security.hb.controller
 * hyp create at 19-12-30
 **/
@Controller
@RequestMapping("/product")
public class ProductController {


    /**
     * 商品添加
     */
    @RequestMapping("/index")
    public String index(){
        return "index";
    }


    /**
     * 商品添加
     */
    @RequestMapping("/add")
    public String add(){
        return "product/productAdd";
    }

    /**
     * 商品修改
     */
    @RequestMapping("/update")
    public String update(){
        return "product/productUpdate";
    }

    /**
     * 商品修改
     */
    @RequestMapping("/list")
    public String list(){
        return "product/productList";
    }

    /**
     * 商品删除
     */
    @RequestMapping("/delete")
    public String delete(){
        return "product/productDelete";
    }


}