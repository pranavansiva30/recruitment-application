Deployment:

       go inside recruitment-application 
    Method 1:
    needed sofware(maven ,java 1.8)

     run the command 
     
     local(default):
     mvn spring-boot:run
     
     
               
         
     
    type localhost:8080 in browser (localhost is host host name)

    method 2:

     needed sofware(maven ,java 1.8)

    run the command mvn clean install
    go inside recruitment-application/target
    
    local(default):
    java -jar recruitment-application-0.0.1-SNAPSHOT.jar
    
   
    
    
    type localhost:8080 in browser (localhost is host host name)

for stop service:
 ps -ef|grep recruitment-application-0.0.1-SNAPSHOT
 kill -9 <PID>



