package cn.nt.xinglinyifang.service;

import cn.nt.xinglinyifang.model.Technology;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
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
}
