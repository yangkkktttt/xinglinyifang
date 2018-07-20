package cn.nt.xinglinyifang.service;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;

/**
 * Service工具类
 * @author 74123
 */
public class CommonService {
    /**
     * 根据type和outer_id获取图片的url
     * @param id 医生id
     * @return 头像url
     */
    public static String getImgUrl(int type ,int id) {
        Kv cond = Kv.by("type=", type).set("outer_id=", id);
        SqlPara sqlPara = Db.getSqlPara("relationship.find", Kv.by("cond", cond));
        return String.valueOf(Db.findFirst(sqlPara));
    }
}
