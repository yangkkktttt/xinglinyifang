#sql("randList")
    select id, name, glory from doctor order by rand() limit 6
#end

#sql("findByTech")
    select id, name from doctor
        #for(x:cond)
            #(for.index == 0 ? "where" : "and") #(x.key) #para(x.value)
        #end
#end

#sql("search")
    select id, name, addres, field from doctor
        #for(x:cond)
            #(for.index == 0 ? "where" : "and") #(x.key) #para(x.value)
        #end
#end

#sql("findByName")
    select id, name, addres, field from doctor
        #for(x:cond)
            #(for.index == 0 ? "where" : "and") #(x.key) like concat('%', #para(x.value), '%')
        #end
#end

