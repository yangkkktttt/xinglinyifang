#sql("find")
    select id, name, age, sex, phone, form, treatment_experience, time, cycle, therapeutic_method, treatment_effect, sequela, medical_history, before_treatment, under_treatment, after_treatment from medical_case
        #for(x:cond)
             #(for.index == 0 ? "where" : "and") #(x.key) #para(x.value)
        #end
#end