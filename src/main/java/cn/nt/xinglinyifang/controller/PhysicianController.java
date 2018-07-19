package cn.nt.xinglinyifang.controller;

import com.jfinal.core.Controller;
/*
 * create by 凶残的小跟班
 */
public class PhysicianController extends Controller {
    //名医介绍
    public void list(){
        renderJson();
    }
    //医生详情
    public void details(){
        renderJson();
    }
}
