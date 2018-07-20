#sql("find")
    SELECT image_url FROM common_picture
        #for(x:cond)
            #(for.index == 0 ? "where" : "and") #(x.key) #para(x.value)
        #end
#end