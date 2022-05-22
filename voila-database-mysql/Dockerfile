FROM mysql

CMD ["--character-set-server=utf8mb4", "--collation-server=utf8mb4_general_ci", "--skip-character-set-client-handshake"]
# Copy the database initialize script: 
# Contents of /docker-entrypoint-initdb.d are run on mysqld startup
ADD  docker-entrypoint-initdb.d/ /docker-entrypoint-initdb.d/

# Default values for passwords and database name. Can be overridden on docker run
# ENV MYSQL_ROOT_PASSWORD=my-secret-pw # Not defaulted for security reasons!
ENV MYSQL_DATABASE=voiladb
ENV MYSQL_USER=voila
ENV MYSQL_PASSWORD=password
