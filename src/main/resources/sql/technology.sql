#sql("techList")
    select id, name, characteristic, detail from technology
#end

#sql("details")
    select id, name, characteristic, detail, main_disease from technology where id = ?
#end

#sql("findByDisease")
    select id, name, outer_id from technology where main_disease like concat('%', #para(disease), '%')
#end

#sql("search")
    select id, name, outer_id from technology
        #for(x:cond)
            #(for.index == 0 ? "where" : "or") #(x.key) like concat('%', #para(x.value), '%')
        #end
#end

