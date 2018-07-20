package cn.nt.xinglinyifang.service;

import cn.nt.xinglinyifang.model.Technology;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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
//        final Kv cond = Kv.by("id", id);
//        SqlPara sqlPara = Db.getSqlPara("technology.techDetails", Kv.by("cond", cond));
//        System.out.println(sqlPara);
//        return Technology.dao.findFirst(sqlPara);
//        String sql = Db.getSql("technology.details");
//        System.out.println(sql);
        return Technology.dao.findFirst("select id, name, characteristic, detail, main_disease from technology where id = " + id);
    }

    /**
     * 根据技法id获取技法的图片地址
     * @param id 技法id
     * @return 技法的图片url List
     */
    public List<Record> getImgById(int id) {
        return CommonService.getImgUrlList(TECH_TYPE, id);
    }
}
