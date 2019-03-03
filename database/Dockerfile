FROM mysql:8.0.15
  
ENV MYSQL_ROOT_PASSWORD rootpassword

COPY ./sql-scripts/ /docker-entrypoint-initdb.d/
