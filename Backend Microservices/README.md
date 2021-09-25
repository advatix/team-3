# SpringBoot-Microservices
> [SpringBoot Microservices Project](https://www.youtube.com/watch?v=BnknNTN8icw)

## Sequence to Start Project
| Seq No. | Service Name | Description |
| ------ | ------ | ------ |
| 1. | ✨Service Registry✨ | That will register all the webservice endpoints. |
| 2. | [✨Cloud Config Server✨](https://cloud.spring.io/spring-cloud-config/multi/multi__spring_cloud_config_server.html) | That will download eureka server OR registry service configurations from git. |
| 3. | Hystrix Dashboard | URL's `http://localhost:8761/` `http://localhost:9191/actuator/hystrix.stream` `http://localhost:9295/` |
| 4. | Cloud Gateway | That will load balace all the request and helps in not to use local host and port number in all micro requests. This will also controlls the **Circuit Breaker** which will help to controll the requests if any of the registered service goes down. |
| 5. | Account Service | Account Info Api's |
| 6. | User Service | User Api's |
| 7. | [Zipkin Server](https://zipkin.io/pages/quickstart.html) | `http://localhost:9411/zipkin` |

**Replace Git Username and Password in Cloud Config Server application.yml file**

## Api Endpoints with Request Json
| Request Type | Api Name | Curl Request |
| ------ | ------ | ------ |
| POST | Create Customer | ```curl --location --request POST 'localhost:9191/api/customer/create' --header 'Content-Type: application/json' --data-raw '{ "customerCode": "XPDEL", "customerName": "XPDEL", "customerAddress": "USA" }'``` |
| GET | Get Customer Details By Id | ```curl --location --request GET 'localhost:9191/api/customer/1'``` |
| POST | Create User | ```curl --location --request POST 'localhost:9191/api/user/createUser' --header 'Content-Type: application/json' --data-raw '{ "firstName": "Ankush", "lastName": "Goyal", "email": "agoyal@advatix.com", "customerId": 1 }'``` |
| GET | Get User Details And Mapped Customer Details By Id | ```curl --location --request GET 'localhost:9191/api/user/1''``` |

## Setup Lambok SLF4J ##
https://stackoverflow.com/questions/3418865/cannot-make-project-lombok-work-on-eclipse/3425327#3425327
1. Goto project's lib folder
2. Locate Lombok.xx.jar
3. Right Click on Jar
4. Run as Java Application, This will launch Lombok screen.
5. Specify location
6. And specify location of "Eclipse.ini" file.(Eclipse neon on Mac osX has it at -> "<Eclipse_installation_path>/jee-neon/Eclipse.app/Contents/Eclipse/Eclipse.ini")