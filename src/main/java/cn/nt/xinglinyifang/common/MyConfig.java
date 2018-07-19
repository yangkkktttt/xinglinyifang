package cn.nt.xinglinyifang.common;

import com.jfinal.config.*;
import com.jfinal.kit.PropKit;
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

    }

    @Override
    public void configEngine(Engine me) {

    }

    @Override
    public void configPlugin(Plugins me) {

    }

    @Override
    public void configInterceptor(Interceptors me) {

    }

    @Override
    public void configHandler(Handlers me) {

    }
}
