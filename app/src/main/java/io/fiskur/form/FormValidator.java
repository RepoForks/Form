package io.fiskur.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FormValidator {

  List<String> ids = new ArrayList<>();

  public FormValidator(){

  }

  public ValidationResponse validateForm(Form form){
    ValidationResponse validation = new ValidationResponse();
    if(form != null){
      if(form.fields != null && form.fields.size() > 0){
        for(Field field : form.fields){
          getIds(field);
        }
        Set<String> dupes = findDuplicates(ids);
        if(dupes.size() > 0){
          validation.status = ValidationResponse.FORM_DUPLICATE_ID;
          String[] duplicates = new String[dupes.size()];
          int index = 0;
          for(String dupe : dupes){
            duplicates[index] = dupe;
            index++;
          }
          validation.duplicates = duplicates;
        }else{
          validation.status = ValidationResponse.FORM_OK;
        }
      }else{
        validation.status = ValidationResponse.FORM_NO_FIELDS;
      }
    }else{
      validation.status = ValidationResponse.FORM_NULL;
    }

    return validation;
  }

  private void getIds(Field field){
    ids.add(field.id);
    if(field.hasSubfields()){
      for(Field subfield : field.subfields){
        getIds(subfield);
      }
    }
  }

  private <T> Set<T> findDuplicates(Collection<T> list) {

    Set<T> duplicates = new HashSet<T>();
    Set<T> uniques = new HashSet<T>();

    for(T t : list) {
      if(!uniques.add(t)) {
        duplicates.add(t);
      }
    }

    return duplicates;
  }


}
