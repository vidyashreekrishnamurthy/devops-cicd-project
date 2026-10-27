
FROM tomcat:10.1-jdk17-temurin

# Remove the default Tomcat web applications
RUN rm -rf /usr/local/tomcat/webapps/*

# Deploy our Maven-built application
COPY target/ROOT.war /usr/local/tomcat/webapps/ROOT.war

# Tomcat listens on port 8080
EXPOSE 8080

CMD ["catalina.sh", "run"]
