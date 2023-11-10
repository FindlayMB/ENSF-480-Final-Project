
# Ideas
Put your ideas here and we can discus them.



### User login
Could also create users on MySQL and they have to connect to the database.  
This approach would be better since we can limit what they are capable of doing to the database.

  
### Plane layout info  
- rows with emergency exits
- number of isles
- number of seats in each column


## Database design

-> registered users and admins  
-> used for authentication
- TABLE: user info
    - user id -> unique
    - username
    - password
    - name
    - email
    - payment info
    - membership -> boolean
    - company credit card

- TABLE: info about planes
    - plane id -> unique
    - plane type
    - layout
    - number of regular seats
    - number of comfort seats
    - number of business seats  

- TABLE: info about flights
    - flight id -> unique
    - destination airport -> store as the airport code
    - arrival time/date
    - origin airport -> store as the airport code
    - departure time/date
    - crew id
    - plane id

- TABLE: passenger list
    - flight code -> group by
    - user id -> unique

- TABLE: crew info
    - crew id -> unique

- TABLE: info about airports
    - airport code -> should be unique
    - airport name
    
- TABLE: info on company credit cards
    - card number -> unique
    - user id
    - other information
