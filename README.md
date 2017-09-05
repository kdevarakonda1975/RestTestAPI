# RestTestAPI
The code consists of 3 API testing using RestAssured. Primarily used the public APIs Country,Weather and Customer. 
the below APIs are used for testing.

Country API -[click here] (http://services.groupkt.com/country/get/all)

Weather API-[click here] (http://api.openweathermap.org/data/2.5/weather?q=Frisco,US&appid=9732a8a54f898ecaafd9c1e72f8b6e31)

Customer API-[click here] (http://www.thomas-bayer.com/sqlrest/CUSTOMER/10)

# Steps for executing
1.	Download project source files from github
2.	Unzip the folder to the your working location
3.	C:\Users\<Username>\project name of the folder RestTestAPI
4.	Go to command prompt
5.	Run the below command
5.1	C: \Users\<username> RestTestAPI> mvn clean install
# Notes:
1. There is one negative test case which throws error on console which is expected. It is not a code issue.
2. for Weather API, I have registered to get the appid.

 
