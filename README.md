# Form  

### Note. in dev, not ready for use.

Modern replacement for [ODK Collect](https://opendatakit.org/use/collect/) forms on Android.

## Android Usage

Create a Form object from a String of json in the [format detailed below](https://github.com/fiskurgit/Form#format):

```java
Form form = FormApi.getInstance().createForm(JSON_STRING);
```

Once you have a Form object you can create the user interface by passing a ```LinearLayout```, your implementation should wrap this layout in a ```ScrollView```:

```java
LinearLayout formHolder = (LinearLayout) findViewById(R.id.form_holder);
FormApi.getInstance().buildViews(context, form, formHolder);
```

## Format

Simple Form object containing an array of fields:

```json
{
  "id": "QWERT098765",
  "title": "Octopus Monitoring",
  "date": "05092016",
  "time": "10:49",
  "author": "Jonathan Fisher",
  "contact": "jonathan@fiskur.eu",
  "fields": [
    {
      "...":, "..."
    }
  ]
}
```

A Field is a query, question, radio buttons, checkboxes, or a non interactive section (text, or todays date):

```json
{
  "id": "Q1",
  "title": "Todays Date",
  "type": "CURRENT_DATE"
}
```

```json
{
  "id": "Q4",
  "title": "Did anyone go fishing for octopus today?",
  "type": "BINARY_CHOICE",
  "options": [
    {
      "id": "Q4N",
      "text": "No"
    },
    {
    "id": "Q4Y",
    "text": "Yes"
    }
  ]
}
```

```json
{
  "id": "Q3",
  "title": "Data Collector Name",
  "subtitle": "Choose your name from the list",
  "type": "SINGLE_CHOICE",
  "options": [
    {
      "id": "PERSONA",
      "text": "Person A"
    },
    {
      "id": "PERSONB",
      "text": "Person B"
    },
    {
      "id": "PERSONC",
      "text": "Person C"
    }
  ]
}
```

## Nesting

Different paths can be displayed based on the response to a single, multi-choice, or binary field. In order to keep the json relatively flat in structure a choice object defines a subgroup id which is displayed when that option is selected. Subgroups are held in an array at the top level, this way you can create complex form graphs without confusing heavily indented nested json.

Simple example with a single top-level field showing two alternative paths a form could take:

```json
{
  "id": "DEMO001",
  "title": "Octopus Monitoring",
  "date": "02112016",
  "time": "11:32",
  "author": "Jonathan Fisher",
  "contact": "jonathan@fiskur.eu",
  "fields": [
    {
      "id": "DEMO001_FISHTODAY",
      "type": "BINARY_CHOICE",
      "text": "Did anyone go fishing for octopus today?",
      "choices":
      [
        {
          "id": "DEMO001_FISHTODAY_NO",
          "text": "No",
          "subgroupId": "DEMO001_FISHTODAY_NO_SG"
        },
        {
          "id": "DEMO001_FISHTODAY_YES",
          "text": "Yes",
          "subgroupId": "DEMO001_FISHTODAY_YES_SG"
        }
      ]
    }
  ],
  "subgroups": [
    {
      "id": "DEMO001_FISHTODAY_NO_SG",
      "fields": [
        {
          "id": "S1",
          "type": "STATIC_TEXT",
          "title": "Fishing Today: No"
        }
      ]
    },
    {
      "id": "DEMO001_FISHTODAY_YES_SG",
      "fields": [
        {
          "id": "S2",
          "type": "STATIC_TEXT",
          "title": "Fishing Today: Yes"
        }
      ]
    }
  ]
}
```

## Available Fields

### Divider

A simple non-interactive horizontal divider to seperate two other fields.

```json
{
  "id": "D1",
  "type": "DIVIDER"
}
```

### Spacer

Invisible field to provide vertical spacing where needed.

```json
{
  "id": "SP1",
  "type": "SPACER",
  "config": "160"
}
```

### Current Date

A non-interactive field that displays, and returns, the current date.

```json
{
  "id": "D1",
  "type": "CURRENT_DATE",
  "text": "Today's Date"
}
```

### Date Picker

```json
{
  "id": "DOB1",
  "type": "DATE",
  "text": "Date of Birth"
}
```

### Time Picker

```json
{
  "id": "WAKE1",
  "type": "TIME",
  "text": "What time did you wake up?"
}
```

### Single Choice (Radio Group)

```
{
  "id": "GENDER1",
  "type": "SINGLE_CHOICE",
  "text": "What gender do you identify with?",
  "choices": [
    {
      "id": "GENA",
      "text": "female"
    },
    {
      "id": "GENB",
      "text": "male"
    },
    {
      "id": "GENC",
      "text": "unspecified"
    },
    {
      "id": "GEND",
      "text": "intersex"
    },
    {
      "id": "GENE",
      "text": "prefer not to say"
    }
  ]
}
```

### Multiple Choice (Checkboxes)

```json
{
  "id": "MUSIC1",
  "type": "MULTI_CHOICE",
  "text": "Which music genres do you enjoy?",
  "choices": [
    {
      "id": "GENA",
      "text": "Avant-Garde"
    },
    {
      "id": "GENB",
      "text": "Baroque"
    },
    {
      "id": "GENC",
      "text": "Chamber Music"
    },
    {
      "id": "GEND",
      "text": "Choral"
    },
    {
      "id": "GENE",
      "text": "Classical Crossover"
    },
    {
      "id": "GENF",
      "text": "Minimalism"
    },
    {
      "id": "GENG",
      "text": "Modern Composition"
    },
    {
      "id": "GENH",
      "text": "Opera"
    },
    {
      "id": "GENI",
      "text": "Romantic"
    }
  ]
}
```

## Localisation

An implementation of this library has a requirement for relatively obscure local/regional dialects, Android's i18n isn't sufficient so a simple dictionary api is used, if the key isn't found in the dictionary the text is displayed as-is:

```json
{
  "id": "DOB1",
  "type": "DATE",
  "text": "{DOB}"
}
```

Example dictionary:

```json
"dictionary": [
    {
      "languageId": "english",
      "dictionary": [
        {
          "key": "{DOB}",
          "text": "Date of Birth"
        },
        {
          "key": "{FORENAME_QUESTION}",
          "text": "What is your first name?"
        }
      ]
    },
      {
      "languageId": "spanish",
      "dictionary": [
        {
          "key": "{DOB}",
          "text": "Fecha de nacimiento"
        },
        {
          "key": "{FORENAME_QUESTION}",
          "text": "¿cuál es tu primer nombre?"
        }
      ]
    }
  ]
}
```

### Android Implementation Details

Some notes on how the json is interpreted, the Form object is created by serialising with Gson:

```java
public Form createForm(String jsonForm){
  Gson gson = new GsonBuilder().create();
  return gson.fromJson(jsonForm, Form.class);
}
```

The implementation then passes a LinearLayout to buildView which creates the ui:

```java
public void buildViews(Context context, Form form, LinearLayout root){
//...
```

If a field contains subfields (if a choice object contains a subgroupId) then an empty LinearLayout is added beneath ready to populate when the users selected a choice, the subfield holder layout tag is the field id plus '_holder':

```java
if(field.hasSubfields()){
  LinearLayout subfieldHolder = new LinearLayout(context);
  subfieldHolder.setOrientation(LinearLayout.VERTICAL);
  subfieldHolder.setTag(String.format("%s_holder", field.id));

  LinearLayout.LayoutParams subfieldHolderLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
  subfieldHolder.setLayoutParams(subfieldHolderLayoutParams);
  root.addView(subfieldHolder);
}
```












