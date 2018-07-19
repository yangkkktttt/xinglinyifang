package cn.nt.xinglinyifang.controller;

import com.jfinal.core.Controller;
/*
 * create by 凶残的小跟班
 */
public class IllnessController extends Controller {
    //疑难杂症
    public void list(){
        renderJson();
    }
    //症状详情
    public void details(){
        renderJson();
    }
}
