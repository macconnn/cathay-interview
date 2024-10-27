# Eden Cathay interview 

## Guide
### APIs spec
```
GET    /api/currency/{code}
POST   /api/currency
PUT    /api/currency/{code}
DELETE /api/currency/{code}
```
```
GET    /api/coindeck/original
GET    /api/coindeck/modified
```

## Dependencies 
* JDK 1.8
* maven 3.9.6

## Schema
```
DROP TABLE IF EXISTS currency;
CREATE TABLE currency (
    c_id INT AUTO_INCREMENT  PRIMARY KEY,
    code CHAR(3) NOT NULL,
    symbol VARCHAR(255) NOT NULL,
    rate VARCHAR(255) DEFAULT NULL,
    description VARCHAR(255) NOT NULL,
    rate_float DECIMAL(10, 4) NOT NULL
);
```
## Data
```
INSERT INTO currency (code, symbol, rate, description, rate_float)
VALUES
    ('GBP', '&pound;', '51,683.046', 'British Pound Sterling', 51683.0455),
    ('EUR', '&euro;', '62,020.994', 'Euro', 62020.9944),
    ('USD', '&#36;', '66,986.431', 'United States Dollar', 66986.4305);
```


## Getting started
* execute program
* testing 
### execute program
follow these ways below
* launch project with IDE (intellij or eclipse)
* cd to target file and execute cathay-interview-0.0.1-SNAPSHOT.jar
```
java -jar cathay-interview-0.0.1-SNAPSHOT.jar
```
### testing
```
mvn test
```
