# Form
Modern replacement for ODK Collect forms on Android.

# Format

Simple Form object containing an array of fields:

```json
{
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
  "interactive": false,
  "type": "CURRENT_DATE"
}
```

```json
{
  "id": "Q4",
  "title": "Did anyone go fishing for octopus today?",
  "type": "TOGGLE_BUTTON",
  "options": [
    {
      "id": "Q4N",
      "title": "No"
    },
    {
    "id": "Q4Y",
    "title": "Yes"
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
      "title": "Person A"
    },
    {
      "id": "PERSONB",
      "title": "Person B"
    },
    {
      "id": "PERSONC",
      "title": "Person C"
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
  "type": "TOGGLE_BUTTON",
  "options": [
    {
      "id": "PTRANSNO",
      "title": "No"
    },
    {
    "id": "PTRANSYES",
    "title": "Yes"
    }
  ],
  subfields: [
    {
      "id": "Q3",
      "title": "Transport Method",
      "subtitle": "Select all transport methods you used today",
      "type": "MULTI_CHOICE",
      "options": [
        {
          "id": "PRIVTRANSA",
          parent: "PTRANSNO",
          "title": "Walking"
        },
        {
          "id": "PRIVTRANSB",
          parent: "PTRANSNO",
          "title": "Cycling"
        },
        {
          "id": "PRIVTRANSC",
          parent: "PTRANSNO",
          "title": "Car"
        },
        {
          "id": "POBTRANSA",
          parent: "PTRANSYES",
          "title": "Bus"
        },
        {
          "id": "POBTRANSB",
          parent: "PTRANSYES",
          "title": "Train"
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
      "title": "No"
    },
    {
    "id": "PTRANSYES",
    "title": "Yes"
    }
  ],
  subfields: [
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




