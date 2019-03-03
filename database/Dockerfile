FROM mysql:8.0.15
  
# Environment variables
ENV MYSQL_ROOT_PASSWORD rootpassword
ENV MYSQL_USER springuser
ENV MYSQL_PASSWORD springuserpassword

COPY ./sql-scripts/ /docker-entrypoint-initdb.d/
