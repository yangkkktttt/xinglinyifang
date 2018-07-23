package cn.nt.xinglinyifang.service;

import cn.nt.xinglinyifang.model.Disease;
import cn.nt.xinglinyifang.model.Doctor;
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
public class DiseaseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalTechService.class);

    private DiseaseService() {}

    /**
     * 私有构造方法，使用getInstance()方法创建对象
     * @return DiseaseService对象
     */
    public static DiseaseService getInstance() {
        return new DiseaseService();
    }

    /**
     * 获取疾病列表
     * 每条记录包含信息：症状id，症状名name(dis_name)，症状简介intro(symptons_of_disease + test_standard)
     * @return 疾病list
     */
    public List<Record> getDiseaseList() {
        String sql = Db.getSql("disease.findList");
        return Db.find(sql);
    }

    /**
     * 根据疾病id获取疾病的详细信息
     * 信息包括：疾病id，疾病名name(dis_name)，症状简介intro(symptons_of_disease + test_standard)
     * 注：获取典型案例使用MedicalTechService中的getMedicalCase()方法
     *    获取疾病医生及技法的方法在下面
     * @param id 疾病id
     * @return 疾病记录
     */
    public Disease getById(int id) {
        Kv cond = Kv.by("id=", id);
        SqlPara sqlPara = Db.getSqlPara("disease.find", Kv.by("cond", cond));
        return Disease.dao.findFirst(sqlPara);
    }

    /**
     * 根据疾病名称返回Doctor和Technology对象信息的Map
     * 包含信息有：id，name
     * @param id 疾病id
     * @return 包含Doctor Technology对象信息的Map
     */
    public Map<String, Object> getDocAndTech(int id) {
        Map<String, Object> docAndTech = new HashMap<>(2);

        //根据疾病id获取Disease对象
        Kv cond = Kv.by("id=", id);
        SqlPara sqlPara = Db.getSqlPara("disease.find", Kv.by("cond", cond));
        Disease disease = Disease.dao.findFirst(sqlPara);

        //获取疾病名称
        String name = disease.get("dis_name");

        //根据疾病名称模糊查询技法
        Kv cond1 = Kv.by("disease", name);
        SqlPara sqlPara1 = Db.getSqlPara("technology.findByDisease", cond1);
        Technology tech = Technology.dao.findFirst(sqlPara1);
        docAndTech.put("tech", tech);

        //根据技法名称获取医生
        Kv cond2 = Kv.by("id=", tech.get("outer_id"));
        SqlPara sqlPara2 = Db.getSqlPara("doctor.findByTech", Kv.by("cond", cond2));
        docAndTech.put("doc", Doctor.dao.findFirst(sqlPara2));

        return docAndTech;
    }
}
