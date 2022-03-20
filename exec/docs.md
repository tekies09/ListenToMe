## 빌드 및 배포
1. Spring boot  
Vue2  
JAVA : openjdk version "1.8.0_192"  
InteliJ IDEA 2021.3.1  
VSCode 1.64.2  
mysql:5.7  
jenkins:lts-jdk8  

2. <br>nginx 파일  
```
server {
        listen 80 default_server;
        listen [::]:80 default_server;

        server_name i6a605.p.ssafy.io;
        return 301 https://$server_name$request_uri;

        index index.html index.htm ;
}
server{
        listen 443 ssl;
        listen [::]:443 ssl;

        server_name i6a605.p.ssafy.io;

        ssl_certificate /etc/letsencrypt/live/i6a605.p.ssafy.io/fullchain.pem;
        ssl_certificate_key /etc/letsencrypt/live/i6a605.p.ssafy.io/privkey.pem;

        root /var/www/html/dist;
        index index.html;
        location / {
                        try_files $uri $uri/ /index.html;
        }
        location /api {
        proxy_pass http://localhost:8399/api;
        proxy_redirect off;
        charset utf-8;

        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
        proxy_set_header X-NginX-Proxy true;
        }
}
```
3. 특이사항  
 SSL 발급받기
 ```
 sudo apt-get update -y & sudo apt-get install letsencrypt -y
sudo letsencrypt certonly --standalone -d [도메인 네임]
 ```

 pem 파일 pkcs12 변환
 ```
 openssl pkcs12  -export -in fullchain.pem \ 
                -inkey privkey.pem \ 
                -out keystore.p12 -name tomcat \ 
                -CAfile chain.pem \ 
                -caname root
 ```
4. DB 접속 정보  
mysql
사용 db : listentome_db  
계정 : root / di349C28

<br>

## 외부 서비스 정보
외부 서비스 사용 x