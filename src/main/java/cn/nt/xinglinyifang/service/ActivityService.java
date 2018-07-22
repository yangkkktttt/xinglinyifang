package cn.nt.xinglinyifang.service;

import cn.nt.xinglinyifang.model.Activation;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 74123
 */
public class ActivityService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalTechService.class);

    private static final int ACTIVE_TYPE = 4;

    private ActivityService() {}

    /**
     * 私有构造方法，使用getInstance()方法创建对象
     * @return ActivityService对象
     */
    public static ActivityService getInstance() {
        return new ActivityService();
    }

    /**
     * 向数据表中插入一条活动记录
     * @param title 活动标题
     * @param dateTime 活动时间
     * @param participant 参与人员
     * @param address 地址
     * @param doc 医生
     * @param tech 技术
     * @param medicine 药物
     * @param summary 总结
     */
    public void addActivity(String title, LocalDateTime dateTime, String participant, String address,
                            String doc, String tech, String medicine, String summary) {

        Activation activation = new Activation().set("activity_name", title).set("time", dateTime).set("personnel", participant)
                .set("place", address).set("doctor", doc).set("technology", tech).set("medicine", medicine)
                .set("activity_summary", summary);

        activation.save();

        LOGGER.info("向 activation_record 表中插入一条活动记录：" + activation);
    }

    /**
     * 根据活动id获取活动的详细信息
     * 包括：活动名称name（activity_name），时间time，地点place，调研人员personnel，医生doctor，技法technology
     * 活动报告report（activity_summary）
     * @param id 活动id
     * @return Activation对象
     */
    public Activation getActiveById(int id) {
        Kv cond = Kv.by("id=", id);
        SqlPara sqlPara = Db.getSqlPara("activation.find", Kv.by("cond", cond));
        return Activation.dao.findFirst(sqlPara);
    }

    /**
     * 根据活动id返回一个包含图片url和视频url的Map
     * @param id 活动id
     * @return Map
     */
    public Map<String, String> getPicAndVidById(int id) {
        Map<String, String> picAndVid= new HashMap<>(2);
        String pic = CommonService.getImgUrl(ACTIVE_TYPE, id);
        picAndVid.put("pic", pic);
        String vid = CommonService.getVidUrl(ACTIVE_TYPE, id);
        picAndVid.put("video", vid);

        return picAndVid;
    }
}
