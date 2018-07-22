package cn.nt.xinglinyifang.service;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;

import java.util.List;

/**
 * Service工具类
 * @author 74123
 */
public class CommonService {
    /**
     * 根据type和outer_id获取一张图片的url
     * @param type 1：医生；2：医技；3：药物；4：活动
     * @param id id
     * @return 图片url
     */
    public static String getImgUrl(int type ,int id) {
        Kv cond = Kv.by("type=", type).set("outer_id=", id);
        SqlPara sqlPara = Db.getSqlPara("relationship.find", Kv.by("cond", cond));
        return String.valueOf(Db.findFirst(sqlPara));
    }

    /**
     * 根据type和outer_id获取图片的url列表
     * @param type 1：医生；2：医技；3：药物；4：活动
     * @param id id
     * @return 图片url列表
     */
    public static List<Record> getImgUrlList(int type, int id) {
        Kv cond = Kv.by("type=", type).set("outer_id=", id);
        SqlPara sqlPara = Db.getSqlPara("relationship.find", Kv.by("cond", cond));
        return Db.find(sqlPara);
    }

    /**
     * 根据type和outer_id获取一个视频
     * @param type 1：医生；2：医技；3：药物；4：活动
     * @param id id
     * @return 视频的url
     */
    public static String getVidUrl(int type, int id) {
        Kv cond = Kv.by("type=", type).set("outer_id=", id);
        SqlPara sqlPara = Db.getSqlPara("relationship.findVid", Kv.by("cond", cond));
        return String.valueOf(Db.findFirst(sqlPara));
    }
}
