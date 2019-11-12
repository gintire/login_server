FROM openjdk:8-jdk-alpine
MAINTAINER gintire <jin3670@gmail.com>

#ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/myservice/myservice.jar"]

# Add Maven dependencies (not shaded into the artifact; Docker-cached)
#ADD target/lib           /usr/share/myservice/lib
# Add the service itself
VOLUME /tmp
ADD /target/gintonicshop.jar gintonicshop.jar
ENTRYPOINT ["java","-jar","gintonicshop.jar"]
