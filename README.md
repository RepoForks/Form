# Form
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
  "type": "YES_NO",
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

Sub-Fields can be displayed based on the response to a single, multi-choice, or binary field by specifying the id of the parent choice.  
Fields can rely on a parent or the answers within a field can specify the parent, this way you can display entirely different fields based on a response, or just change which choices to display.

### Nested Choices

```json
{
  "id": "Q4",
  "title": "Did you use public transport today?",
  "type": "YES_NO",
  "options": [
    {
      "id": "PTRANSNO",
      "text": "No"
    },
    {
    "id": "PTRANSYES",
    "text": "Yes"
    }
  ],
  "subfields": [
    {
      "id": "Q3",
      "title": "Transport Method",
      "subtitle": "Select all transport methods you used today",
      "type": "MULTI_CHOICE",
      "options": [
        {
          "id": "PRIVTRANSA",
          "parent": "PTRANSNO",
          "text": "Walking"
        },
        {
          "id": "PRIVTRANSB",
          "parent": "PTRANSNO",
          "text": "Cycling"
        },
        {
          "id": "PRIVTRANSC",
          "parent": "PTRANSNO",
          "text": "Car"
        },
        {
          "id": "POBTRANSA",
          "parent": "PTRANSYES",
          "text": "Bus"
        },
        {
          "id": "POBTRANSB",
          "parent": "PTRANSYES",
          "text": "Train"
        },
      ]
    }
  ]
}
```

### Nested Fields

```json
{
  "id": "Q4",
  "title": "Did you use public transport today?",
  "type": "TOGGLE_BUTTON",
  "options": [
    {
      "id": "PTRANSNO",
      "text": "No"
    },
    {
    "id": "PTRANSYES",
    "text": "Yes"
    }
  ],
  "subfields": [
    {
      "id": "Q3",
      "title": "Describe the public transport you used",
      "parent": "PTRANSYES",
      "type": "FREE_TEXT"
    },
    {
      "id": "Q3",
      "title": "Why didn't you use public transport?",
      "parent": "PTRANSNO",
      "type": "FREE_TEXT"
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








