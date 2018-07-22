#sql("find")
    select activity_name, time, place, personnel, doctor, technology, activity_summary from activation_record
    #for(x:cond)
            #(for.index == 0 ? "where" : "and") #(x.key) #para(x.value)
    #end
#end