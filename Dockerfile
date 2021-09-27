# ####################################################################
# Build
FROM gradle:jdk8-openj9 AS build-env
WORKDIR /lab-pnim1858/
COPY . ./
RUN gradle war

# ROOT.war to tomcat
FROM tomcat:9.0.20-jre8-alpine
RUN rm -rf webapps/ROOT
COPY --from=build-env /lab-pnim1858/web/build/libs/ROOT.war ./webapps