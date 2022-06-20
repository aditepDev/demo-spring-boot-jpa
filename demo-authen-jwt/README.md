# authen-demo

![image](https://github.com/aditepDev/demo-authen-jwt/blob/main/ref/Authorization%20Server.drawio.png?raw=true)

## installation step.
```

cmd
1. git clone git@github.com:aditepDev/demo-authen-jwt.git
2. cd demo-authen-jwt
3. docker-compose up

postman 

file postman
1. download : " https://github.com/aditepDev/demo-authen-jwt/blob/main/ref/authen.postman_collection.json "
or
1.1 ref/ authen.postman_collection.json

2. import


สมัครสมาชิก
- register : Silver
- register : Gold
- register : Platinum
- register : error code

เข้าสู่ระบบ
- login

ดึงข้อมูสมาชิก
- member detail : เพิ่ม Headers "Authorization Bearer {access_token}"

ต่ออายุ token
- refreshToken : เพิ่ม Headers "Authorization Bearer {refresh_token}"

```

### SQL database

``` User H2 is free SQL database  with Spring Boot ```

### Member type classify
```
salary < 15000 : error code "register.salary.low.rate"

salary < 30000 : member_type "Silver"

salary <= 50000 : member_type "Gold"

salary > 50000 : member_type "Platinum"
```

### generate reference code : Member Code

 ``` "member_code": "202205313293"  YYYYMMDD + phoneNumber(-4)```


### JWT

```
expiration

 "access_token": 15 MN
 "refresh_token": 30 D

```

### data sensitive  

` ข้อมูล salary ปกติจะไม่แสดง `


## POST /register


payload
```json
{
    "username":"user",
    "password":"1234",
    "first_name":"aditep",
    "last_name":"campira",
    "phone":"0801953293",
    "address":"udonthani",
    "salary": 30001
}
```
response created 201

```json
{
    "member_type": "Gold",
    "access_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlNjk5N2JiZC03ZjkxLTQ1MmMtYmE3Ni01OTcwMmUxMTAyZTAiLCJleHAiOjE2NTQwMTU0MDgsImlhdCI6MTY1NDAxNDUwOH0.hOAknXFtI-qEnSBivcWIKFor8zpM94n_fCr4_P2S-08",
    "refresh_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlNjk5N2JiZC03ZjkxLTQ1MmMtYmE3Ni01OTcwMmUxMTAyZTAiLCJleHAiOjE2NTY2MDY1MDgsImlhdCI6MTY1NDAxNDUwOH0.dFiQdE66oz-rIif6RHDW9T9LBlkMD3gPx7as0nPjsSk",
    "address": "udonthani",
    "phone": "0801953293",
    "member_code": "202205313293",
    "name": "aditep campira",
    "salary": 30001.0
}
```

## POST /login

payload 
```json
{
     "username":"user",
     "password":"1234"
}
```

response OK 200
```json
{
    "member_type": "Gold",
    "access_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlNjk5N2JiZC03ZjkxLTQ1MmMtYmE3Ni01OTcwMmUxMTAyZTAiLCJleHAiOjE2NTQwMTU0MDgsImlhdCI6MTY1NDAxNDUwOH0.hOAknXFtI-qEnSBivcWIKFor8zpM94n_fCr4_P2S-08",
    "refresh_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlNjk5N2JiZC03ZjkxLTQ1MmMtYmE3Ni01OTcwMmUxMTAyZTAiLCJleHAiOjE2NTY2MDY1MDgsImlhdCI6MTY1NDAxNDUwOH0.dFiQdE66oz-rIif6RHDW9T9LBlkMD3gPx7as0nPjsSk",
    "address": "udonthani",
    "phone": "0801953293",
    "member_code": "202205313293",
    "name": "aditep campira",
    "salary": 30001.0
}
```

## POST /refreshToken

Hasder 
```Authorization Bearer refresh token```

response OK 200
```json
{
    "access_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0ZTNjMjA1MS03YTExLTQ1YWMtYWMwNy1jMDFhMjE4YjVjNDkiLCJleHAiOjE2NTQ4MzIyMTUsImlhdCI6MTY1MjI0MDIxNX0.B_G3RQlfW-ESY9zeVhAfB4ImaIibHoWrM-1614txYYM",
    "refresh_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiZXhwIjoxNjUyMDg2MzcwLCJpYXQiOjE2NTIwODU0NzB9.4Uj1RP7E4K8YH5tTZYuS1Pcwy4MNy8YOVWpWWJa4rSc"
}
```
## GET /member

Hasder 
```Authorization Bearer access token```

response OK 200
```json
{
    "first_name":"aditep",
    "last_name":"campira",
    "phone":"0801953293",
    "address":"udonthani",
    "salary": 30001,
    "member_type" : "Gold",
    "member_code" : "202205313293"
 }
```
