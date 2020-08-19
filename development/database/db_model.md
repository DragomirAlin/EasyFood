## Databse Model 
### Version 1.0
//// -- Tables and References

// Creating tables
Table users as U {
  id int [pk, increment] // auto-increment
  first_name varchar
  last_name varchar
  age int
  height double
  weight double
  location varchar
  physical_activity varchar
  
  }

Table products {
  id int [pk, increment] // auto-increment
  nameProduct varchar
  caloriesPer100grams int
  proteins double
  carbohydrates double
  fat double
  shop varchar
  price double
  user_id int [ref: > users.id]
  
 }

Code: https://dbdiagram.io/