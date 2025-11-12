# Getting Started

### Connect to existing local DB
Create new application.properties file in [Resources folder](./src/main/resources).

Copy contents from [application.properties.env](./src/main/resources/application.properties.env)
file to the new file and change appropriate values listed between \<\>

### Setting up the DB schema
By running the application, hibernate should create appropriate tables in the connected DB.
If there are issues with creating DB tables there is a [file](./src/main/resources/db/migration/mysql/V1__initiate_db_tables.sql)
in the [migration folder](./src/main/resources/db/migration/mysql) that contains queries to create table structure.

Table values can be found in [another file](./src/main/resources/db/migration/mysql/V1__insert_init_values.sql)
in the [migration folder](./src/main/resources/db/migration/mysql) and running and commiting 
queries in sequence will inject some data in the tables for testing.

### API routes

- #### Get All Assigned eLearning Components for Current User
  - ##### Method : GET
  - Returns list of eLearning components that the current user is subscribed to.
  - Route is mapped to ``` /project/restapi/lms/my-elearings ``` and requires no parameters.
  - Requires Basic Auth type authorization with the correct username and password to access resource.
  - Allows for API pagination through ``` pageNum ``` and ``` pageSize ``` GET parameters (example ``` ?pageNum=4&pageSize=10 ```).
  - Example of output: 
  ``` 
  {"data": [
      {
          "id": "eLearningComponentId123",
          "name": "Introduction to Scrum",
          "type": "Course",
          "assignedDates": {
              "startDate": "2025-11-11",
              "endDate": "2026-11-11"
              },
          "userStatus": "Booked",
          "imageUrl": "http://example.com/images/scrum_course.jpg"
      },
      {
          "id": "eLearningComponentId456",
          "name": "Agile Methodologies Overview Video",
          "type": "Media",
          "assignedDates": {
              "startDate": "2025-11-11",
              "endDate": "2026-11-11"
              },
          "userStatus": "InProgress",
          "imageUrl": "http://example.com/images/agile_video.png"
      }
    ]} 
   ```
   - Example of output with pagination:
   ``` \{
     "data": [
     {
         "id": "eLearningComponentId123",
         "name": "Introduction to Scrum",
         "type": "Course",
         "assignedDates": {
             "startDate": "2025-11-11",
             "endDate": "2026-11-11"
           },
         "userStatus": "Booked",
         "imageUrl": "http://example.com/images/scrum_course.jpg"
     }
     ],
     "pagination": {
         "pageNum": 1,
         "pageSize": 1,
         "total": 2
       }
     }
  ```


- #### Get Detailed Information for a Specific eLearning Component
    - ##### Method : GET
    - Returns details about a single specific eLearning component the current user is subscribed to.
    - Route is mapped to ``` /project/restapi/lms/my-elearings/{componentID} ``` with the ``` componentID ``` representing eLearning component ID.
    - Requires Basic Auth type authorization with the correct username and password to access resource.
    - Example of output: 
    ``` 
    {"details": {
        "id": "eLearningComponentId123",
        "name": "Introduction to Scrum",
        "description": "This course provides a comprehensive introduction to the Scrum framework...",
        "type": "Course",
        "availableDates": {
            "startDate": "2025-11-11",
            "endDate": "2026-11-11"
        },
        "userStatus": "Booked",
        "imageUrl": "http://example.com/images/scrum_course.jpg",
        "metaTags": [
            "Framework",
            "Scrum",
            "Project Management",
            "Agile"
        ],
        "duration": "8 hours",
        "category": "Software Development"
        }
    }
    ```