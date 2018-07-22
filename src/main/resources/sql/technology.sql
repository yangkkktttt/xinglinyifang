#sql("techList")
    select id, name, characteristic, detail from technology
#end

#sql("details")
    select id, name, characteristic, detail, main_disease from technology where id = ?
#end

#sql("findByDisease")
    select id, name from technology where main_disease like concat('%', #para(disease), '%')
#end

