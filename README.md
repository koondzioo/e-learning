## RUN CONTAINERS
    * docker-compose up -d --build
## LOGS
    *  docker-compose logs -f

## STOP CONTAINERS
    *  docker-compose stop

## CLEAN CONTAINERS
    * docker system prune
    
## ENDPOINTS

    User Service: 
    GET http://localhost:8100/user --> return all created users
    POST http://localhost:8100/user  {firstName: "name", lastName: "name"} --> create user
    http://localhost:8100/user/status --> return "OK USER SERVICE"
    http://localhost:8100/user/send --> sending information to video service
    
    
    Video Service:
    http://localhost:8200/video --> return 'OK VIDEO SERVICE'
    http://localhost:8200/video/send --> sending information to user service
        
    Api Gateway  
    http://localhost:8090/**  all of the above endpoints  
    
    Eureka Server
    http://localhost:8761 ---> eureka server

    RabbitMQ - with deifinitions queues and exchanges
    http://localhost:15672/
    user: guest
    pw: guest

    ADD REACT ENDPOINT
    http://localhost:3000/