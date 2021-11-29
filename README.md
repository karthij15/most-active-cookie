## Most Active Cookie

### Overview
Write a command line program in your preferred language to process the log file and return the most active
cookie for a specific day. Please include a -f parameter for the filename to process and a -d parameter to
specify the date.
e.g. we’d execute your program like this to obtain the most active cookie for 9th Dec 2018.

$ ./[command] -f cookie_log.csv -d 2018-12-09

### Prerequisite
* Java 8

### Assumptions
* Header will not be present in log file
* If multiple cookies meet that criteria, please return all of them on separate lines.
* Cookies in the log file are sorted by timestamp (most recent occurrence is the first line of the file).

### Build 
```
cd most-active-cookie/
./gradlew clean --info build
```

### Run
```
java -jar build/libs/most-active-cookie-1.0-SNAPSHOT.jar -f "src/test/resources/cookie_log.csv" -d "2018-12-09"
```

