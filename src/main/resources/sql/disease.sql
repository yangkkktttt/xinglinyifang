#sql("findList")
    select id, dis_name, symptons_of_disease, test_standard from disease
#end

#sql("find")
    select id, dis_name, symptons_of_disease, test_standard from disease
        #for(x:cond)
            #(for.index == 0 ? "where" : "and") #(x.key) #para(x.value)
        #end
#end