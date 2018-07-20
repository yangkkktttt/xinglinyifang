package cn.nt.xinglinyifang.common;

import cn.nt.xinglinyifang.model.*;
import cn.nt.xinglinyifang.controller.*;
import com.jfinal.config.*;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;



public class MyConfig extends JFinalConfig {
    @Override
    public void configConstant(Constants me) {
        me.setUrlParaSeparator("&");
        PropKit.use("config.properties");
        me.setDevMode(true);
    }

    @Override
    public void configRoute(Routes me) {
        me.add("/user",UserController.class);
        me.add("/activity",ActivityController.class);
        me.add("/home",HomeController.class);
        me.add("/physician",PhysicianController.class);
        me.add("/search",SearchController.class);
        me.add("/tech",TechController.class);

    }

    @Override
    public void configEngine(Engine me) {

    }

    @Override
    public void configPlugin(Plugins me) {
        //druid 数据源插件
        DruidPlugin dp = new DruidPlugin(PropKit.get("url"),PropKit.get("username"),PropKit.get("password"));
        dp.setDriverClass(PropKit.get("driverClass"));
        dp.set(PropKit.getInt("initialSize"), PropKit.getInt("minIdle"), PropKit.getInt("maxActive"));
        dp.setMaxWait(PropKit.getInt("maxWait"));
        me.add(dp);
        //ARP插件
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        me.add(arp);
        arp.setBaseSqlTemplatePath(PathKit.getWebRootPath());
        arp.addSqlTemplate("sql/all.sql");
        arp.addMapping("activation_record", Activation.class);
        arp.addMapping("disease", Disease.class);
        arp.addMapping("doctor", Doctor.class);
        arp.addMapping("medical_case", MedicalCase.class);
        arp.addMapping("medicine", Medicine.class);
        arp.addMapping("common_picture", Picture.class);
        arp.addMapping("dd_relationship", Relationship.class);
        arp.addMapping("technology", Technology.class);
        arp.addMapping("common_video", Video.class);
    }

    @Override
    public void configInterceptor(Interceptors me) {

    }

    @Override
    public void configHandler(Handlers me) {

    }
}
