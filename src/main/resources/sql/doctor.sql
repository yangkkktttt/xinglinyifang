#sql("randList")
    select id, name, glory from doctor order by rand() limit 6
#end

#sql("findByTech")
    select id, name from doctor where field LIKE concat('%', #para(field), '%')
#end

