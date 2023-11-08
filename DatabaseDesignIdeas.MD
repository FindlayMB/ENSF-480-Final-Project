
# Ideas
Put your ideas here and we can discus them.

For user login either use OAuth or store info in a database.  
Could also create users on MySQL and they have to connect to the database.  
This approach would be better since we can limit what they are capable of doing to the database.

## Database design

-> registered users and admins  
-> used for authentication
- TABLE: user info
    - user id
    - username
    - password
    - name
    - email
    - payment info
    - purchased flight

- TABLE: info about planes
    - plane id
    - plane type
    - layout
    - number of regular seats
    - number of business seats  

- TABLE: info about flights
    - flight code
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
    - crew id

- TABLE: info about airports
    - airport code -> should be unique
    - airport name
    