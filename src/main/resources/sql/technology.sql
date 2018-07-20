#sql("details")
    select id, name, characteristic, detail, main_disease from technology
#end

#sql("techList")
    select id, name, characteristic, detail from technology
#end

#sql("techDetails")
    select id, name, characteristic, detail, main_disease from technology
     #for(x:cond)
     #(for.index == 0 ? "where": "and") #(x.key) #para(x.value)
     #end
#end

