package io.fiskur.form.creator.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.fiskur.form.Field;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

  /**
   * An array of sample (dummy) items.
   */
  public static final List<Field> ITEMS = new ArrayList<Field>();

  /**
   * A map of sample (dummy) items, by ID.
   */
  public static final Map<String, Field> ITEM_MAP = new HashMap<String, Field>();

  private static final int COUNT = 3;

  static {
      Field testFieldA = new Field();
      testFieldA.id = "123";
      testFieldA.title = "testFieldA";
      testFieldA.type = Field.TYPE_STATIC_TEXT;
      addItem(testFieldA);

      Field testFieldB = new Field();
      testFieldB.id = "321";
      testFieldB.title = "testFieldB";
      testFieldB.type = Field.TYPE_FREE_TEXT;
      addItem(testFieldB);
  }

  private static void addItem(Field item) {
    ITEMS.add(item);
    ITEM_MAP.put(item.id, item);
  }
}
