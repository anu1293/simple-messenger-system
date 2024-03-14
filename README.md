### SIMPLE MESSENGER SYSTEM
# 1.  Build and Run


    docker command to build : docker build -t simple-messenger-system.jar .
    docker command to run :  docker run -d -p 8080:8080 {latest image}
# 2. Login into H2 Database


    open url :  localhost:8080/h2-console
    change JDBC URL to jdbc:h2:mem:sms
    username: anupam
    password: anupam

opening H2 console in a separate window will help checking how data is being stored and to check user ids which needs to be used in the apis

# 3. API Usages
    /auth/signup -> registers the user and returns JWT token which is needed to access all the other api's
    
    /auth/login -> log in user and returns JWT token which is needed to access all the other api's
    
    ALL THE BELOW API'S  need JWT token
    
    /auth/logout -> used to logout the user from the system by invalidating their token
    
    /users -> returns all the users in the system 
    
    /messages/getMessages -> fetches all unread messages which were sent to the user
    
    /messages/getMessages?friend={userName} -> fetches unread messages sent by a specific user. {pass username here instead of user id}
    Note:  once /messages/getMessages api is called, all the unread messages retrieved in that api call will be marked as read and will not appear in next subsequent api call
    
    /messages/{messageType}/send -> api to send message, message can be sent either to a group or a user individually, for sendnig message to group use messageType=GROUP and in the request body provide group_id under "to" field, similarly for individual message use messageType=DIRECT and user_id under "to" field
    
    /groups/create -> api to create group, user who is creating the group will be added to the group by the system, additional users ids can also be added to add users to the group
    
    /groups/{operation}/members -> api to add or remove users from a group, user/group should be registered in the system before they are being added, provide user_ids of the users in an array who you want to add or remove.  operation=ADD|REMOVE


# postman API collection
Can be imported from api-spec.yaml file directly

# Built Using
1. JAVA
2. SPRING BOOT 6
3. SPRING SECURITY 6
4. JWT token
5. H2 DB
6. DOCKER
7. OPEN API 3.0
8. JPA
9. Gradle 8




