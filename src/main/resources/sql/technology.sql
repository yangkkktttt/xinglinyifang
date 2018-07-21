#sql("techList")
    select id, name, characteristic, detail from technology
#end

#sql("details")
    select id, name, characteristic, detail, main_disease from technology where id = ?
#end

