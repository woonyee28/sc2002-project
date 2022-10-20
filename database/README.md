# MOBLIMA Schema

## Movies
0. (int) movieID - Primary Key
1. (String) title
2. (String) type
3. (String) synopsis
4. (int) rating
5. (String) showingStatus
6. (String) director
7. (ArrayList<String>) cast
8. (ArrayList<Review>) reviews

## Reviews
0. (int) reviewsID - Primary Key
1. (int) movieGoersID - Foreign Key to MovieGoers' Column 0 - movieGoersID
2. (double) rating
3. (String) reviews

## Staffs
0. (int) staffID - Primary Key
1. (String) name
2. (String) email
3. (String) passwordHashed
4. (String) role

## MovieGoers
0. (int) movieGoersID - Primary Key
1. (String) name
2. (String) email
3. (int) age
4. (String) passwordHashed - Using .hashCode() function
5. (int) mobileNumber
6. (String) TID

## Transactions
0. (String) TID
1. (int) movieGoersID - Foreign Key to MovieGoers' Column 0 - movieGoersID
2. (String) bookingDate
3. (String) bookingTime
4. (String) cinemaCode
5. (int) seatingNum
6. (int) price

## Cineplexes
0. (String) cineplexCode
1. (String) name
2. (ArrayList<Cinema>) cinemas

## Cinemas
0. (String) cinemaCode
1. (String) cinemaClass
2. (ArrayList<int>) seatingPlan
3. (ArrayList<Session>) sessions

## Sessions
0. (int) movieID - Foreign Key to Movies' Column 0 - movieID
1. (String) sessionDate
2. (String) sessionTime
3. (ArrayList<int>) seatingPlan

## HolidayData
0. (String) name
1. (String) date