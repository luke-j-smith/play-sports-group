# play-sports-group

This repository contains a RESTful API that provides the functionality to store, delete and search basic information relating to YouTube videos.

## Requirements

In order to build and run the application, the following are required:

1. OpenJDK version 11 or higher;
2. Maven; and
3. Docker.

## Build Steps

To build and run the application we can use the ``makefile`` in the root directory.

First we need to get the database running. We can do this using:
```
make run-database
```

Note that this command also builds the Docker image required to run the container. We can check that this is running as expected using:
```
docker exec -it psg-database bash
mysql -u root -p
```

The password is: ``rootpassword``, and we are expecting to see the two empty tables ``channels`` and ``videos``.
```
use mydb
show tables;
```

Once the database is running as expected, we can start the Java application using:
```
make run-application
```

### Defining Channels and Search Terms

The channels which we wish to search through and the search terms we want to use are defined in files that are located at ``play-sports-group/video_information_service/src/main/resources/search-properties/channel_list`` and ``play-sports-group/video_information_service/src/main/resources/search-properties/search_filter``, respectively.

These files can be edited, with each line corresponding to a distinct channel or search term.

## Usage

Once the application is up and running as expected, the first thing we need to do is retrieve all of the information from YouTube. To do this, we make the following request:
```
GET - http://localhost:8080/download-information/video
```

Once we have the information successfully saved in the database, we can query it with the following requests:

**View All Videos**
```
GET - http://localhost:8080/video/view-all
```

**View Individual Video**
```
GET - http://localhost:8080/video/view?id=33
```

**Delete Individual Video**
```
DELETE - http://localhost:8080/video/delete?id=35
```

**Search Videos**
```
GET - http://localhost:8080/video/search?find=Yates
```

### Notes

The data that is retrieved from YouTube and stored in the database is not persisted across sessions. Therefore, each time we run the application, the first thing we need to do is fetch all of the data.

## Next Steps

If I were to spend more time on the project, first I would look to increase the test coverage (as currently it is very much lacking) and second, I would look to containerize the Spring Boot Java application.
