package io.fiskur.form;

import android.content.Context;

public interface FieldListener {
  void choiceSelected(Context context, String fieldId, String choiceId, String subgroupId);
}
