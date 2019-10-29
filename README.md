# One Kind Window
![images](https://github.com/KennySoh/Onekindapp-android-app/blob/master/sample-project2.png)  
  
One Kind Window is an IOT Modular Gardening Sytem speared headed by upstart urband farming community, [One Kind House.](https://www.youtube.com/watch?v=x3CNF_Mzjzg&t=4s). The project was exeuted with a cross-pillar team that includes mechnical, electrical, software and architecture students! 

## Hardware
| Outer Form       | Inner Form         | 
| ------------- |:-------------:| 
| ![images](https://github.com/KennySoh/Onekindapp-android-app/blob/master/sample-project3.png)       | ![images](https://github.com/KennySoh/Onekindapp-android-app/blob/master/sample-project5.png) |

## Software
Have a look at the application!   
https://www.youtube.com/watch?v=BzXPvuvuLuM&t=1s  

The table below list out all the User Interface Screen
  
| Onboarding     | 
| ------------- |
| ![images](https://github.com/KennySoh/Onekindapp-android-app/blob/master/ui-1.png)       |

| Data Visualisation    | 
| ------------- |
| Light sensor
  ![images](https://github.com/KennySoh/Onekindapp-android-app/blob/master/ui-2.png)       |
| Water Level sensor
![images](https://github.com/KennySoh/Onekindapp-android-app/blob/master/ui-3.png)       |

| Picture Diary Journal    | 
| ------------- |
| ![images](https://github.com/KennySoh/Onekindapp-android-app/blob/master/ui-4.png)       |

| Seed Scanner     | 
| ------------- |
| ![images](https://github.com/KennySoh/Onekindapp-android-app/blob/master/ui-5.png)       |

| Real-Time Notification    | 
| ------------- |
| ![images](https://github.com/KennySoh/Onekindapp-android-app/blob/master/ui-6.png)       |

### Overall System Architecture
![images](https://github.com/KennySoh/Onekindapp-android-app/blob/master/sample-project4.png) 

#### Sensor to cloud
The image below details how arduino sensors data was stored onto the No-sql cloud databse.
![images](https://github.com/KennySoh/Onekindapp-android-app/blob/master/db1.png) 
  
The following schema was used to ensure every copy based on timestamp was recorded. 
 
| No-SQL     | Schema used       | 
| ------------- |:-------------:| 
| ![images](https://github.com/KennySoh/Onekindapp-android-app/blob/master/db2.png)     | ![images](https://github.com/KennySoh/Onekindapp-android-app/blob/master/db3.png)  |
 
#### Cloud to Android APP
Aws restful end points was created to access the No-sql cloud database. The android application was then able to access this end points. 
![images](https://github.com/KennySoh/Onekindapp-android-app/blob/master/db4.png) 

#### Real-Time Notification
When water level sensor is low, a real-time notification is triggered to the applicaiton. 
![images](https://github.com/KennySoh/Onekindapp-android-app/blob/master/db7.png) 
![images](https://github.com/KennySoh/Onekindapp-android-app/blob/master/db8.png) 

#### Seed Scanner Journal
The following details the SQL db schema to store diary journal & Seed Information
![images](https://github.com/KennySoh/Onekindapp-android-app/blob/master/db6.png) 

