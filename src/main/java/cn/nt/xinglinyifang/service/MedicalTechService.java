package cn.nt.xinglinyifang.service;

import cn.nt.xinglinyifang.model.Doctor;
import cn.nt.xinglinyifang.model.MedicalCase;
import cn.nt.xinglinyifang.model.Technology;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 74123
 */
public class MedicalTechService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalTechService.class);

    private static final int TECH_TYPE = 2;

    private static final int MEDICAL_TYPE = 3;

    private MedicalTechService() {}

    /**
     * 私有构造方法，使用getInstance()方法创建对象
     * @return MedicalService的对象
     */
    public static MedicalTechService getInstance() {
        return new MedicalTechService();
    }

    /**
     * 获取民间技法的List，包含信息：技法id，技法名称name，技法简介intro（characteristic + detail），技法图片pic
     * @return list
     */
    public List<Record> getTechs() {
        String sql = Db.getSql("technology.techList");
        List<Record> list = Db.find(sql);
        for (Record r : list) {
            int id = r.getInt("id");
            String pic = CommonService.getImgUrl(TECH_TYPE, id);
            r.set("pic", pic);
        }
        return list;
    }

    /**
     * 根据id获取Technology的信息，包括，技法id，name，简介intro（characteristic + detail），主治疾病illness（main_disease）
     * @param id 技法id
     * @return Technology信息
     */
    public Technology getById(int id) {
        String sql = Db.getSql("technology.details");
        return Technology.dao.findFirst(sql, id);
    }

    /**
     * 根据技法id获取技法的图片地址
     * @param id 技法id
     * @return 技法的图片url List
     */
    public List<Record> getImgById(int id) {
        return CommonService.getImgUrlList(TECH_TYPE, id);
    }

    /**
     * 根据关键字类型（0：技术，1：疾病，2：医生 ） 和对应id返回一条案例
     * @param type 0：技术，1：疾病，2：医生
     * @param key id
     * @return 返回一个MedicalCase对象
     */
    public MedicalCase getMedicalCase(int type, int key) {
        String kind;
        if (type == 0) {
            kind = "technology_id";
        } else if (type == 1) {
            kind = "disease_id";
        } else {
            kind = "doctor_id";
        }

        Kv cond = Kv.by(kind+"=", key);

        SqlPara sqlPara = Db.getSqlPara("medicalCase.find", Kv.by("cond", cond));

        return MedicalCase.dao.findFirst(sqlPara);
    }

    /**
     * 根据搜索关键词查找相应的 技法
     * 通过技法获取相应的 outer_id找到对应医生（医生相关操作在DoctorService）
     * @param key 搜索关键词
     * @return 搜索结果List
     */
    public List<Technology> search(String key) {

        //模糊查询技法表中的，name，method，main_effect，main_disease，detail栏，返回查找到的技法列表
        Kv cond = Kv.by("name", key).set("method", key).set("main_effect", key).set("main_disease", key)
                .set("detail", key);
        SqlPara sqlPara = Db.getSqlPara("technology.search", Kv.by("cond", cond));

        return Technology.dao.find(sqlPara);
    }

}
