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
  "id": "qwerty0123456",
  "title": "Octopus Monitoring",
  "date": "05092016",
  "time": "10:49",
  "author": "Jonathan Fisher",
  "contact": "jonathan@fiskur.eu",
  "startGroupId": "start_group",
  "groups": [
    {
      "id": "start_group",
      "fields": [
        {
          "id": "Q1",
          "title": "Todays Date",
          "type": "CURRENT_DATE"
        }
      ]
    }
  ]
}
```

A Field is a query, question, radio buttons, checkboxes, or a non interactive section (text, or todays date):

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
  "startGroupId": "start_group",
  "groups": [
    {
      "id": "start_group",
      "fields": [
        {
          "id": "fish_today",
          "type": "BINARY_CHOICE",
          "text": "Did anyone go fishing for octopus today?",
          "choices":
          [
            {
              "id": "fish_today_no",
              "text": "No",
              "subgroupId": "fish_today_no_subgroup"
            },
            {
              "id": "fish_today_yes",
              "text": "Yes",
              "subgroupId": "fish_today_yes_subgroup"
            }
          ]
        }
      ]
    },
    {
      "id": "fish_today_no_subgroup",
      "fields": [
        {
          "id": "fish_today_no_label",
          "type": "STATIC_TEXT",
          "title": "Fishing Today: No"
        }
      ]
    },
    {
      "id": "fish_today_yes_subgroup",
      "fields": [
        {
          "id": "fish_today_yes_label",
          "type": "STATIC_TEXT",
          "title": "Fishing Today: Yes"
        }
      ]
    }
  ]
}
```

## Available Fields

### Static Text

Use for titles, notes, explanations - any extra instruction for the end user, there's 3 fields you can use, all optional:

```json
{
  "id": "demo_intro",
  "title": "Form",
  "subtitle": "A simple replacement for ODK Collect",
  "text": "Hello world!",
  "type": "STATIC_TEXT"
}
```

### Text Input

Free text input from the user. 

```json
{
  "id": "TXT01",
  "text": "Sous collector name:",
  "type": "FREE_TEXT"
}
```

Config options: `singleline`, `persist`, and either: `numeric`, `email`, or `phonenumber`:

```json
{
  "id": "TXT01",
  "text": "Sous collector name:",
  "type": "FREE_TEXT",
  "config": "singleline|persist"
}
```


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

### Image

```json
{
  "id": "image1",
  "type": "IMAGE",
  "url": "http://fiskur.eu/apps/blueventures/oct_mantle.png"
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

### Binary Yes/No Buttons

A field that allows a binary decision but displays a more prominent ui than two radio buttons.

```json
{
  "id": "WORK_NORM",
  "type": "BINARY_CHOICE",
  "text": "Did you work as normal today?",
  "choices":
  [
    {
      "id": "WORK_NORM_NO",
      "text": "No"
    },
    {
      "id": "WORK_NORM_YES",
      "text": "Yes"
    }
  ]
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












