FROM openjdk:8

#Uncomment below and Comment above line(i.e. FROM openjdk:8) for OS specific (e.g. Alpine OS ) docker base image
#FROM openjdk:8-jdk-alpine

# can be passed during Docker build as build time environment for github branch to pickup configuration from.
ARG spring_config_label

# can be passed during Docker build as build time environment for spring profiles active 
ARG active_profile

# can be passed during Docker build as build time environment for config server URL 
ARG spring_config_url

# environment variable to pass active profile such as DEV, QA etc at docker runtime
ENV active_profile_env=${active_profile}

# environment variable to pass github branch to pickup configuration from, at docker runtime
ENV spring_config_label_env=${spring_config_label}

# environment variable to pass spring configuration url, at docker runtime
ENV spring_config_url_env=${spring_config_url}

COPY ./target/id-repository-identity-service-*.jar id-repository-identity-service.jar

ADD http://13.71.87.138:8040/artifactory/libs-release-local/io/mosip/kernel/kernel-ref-idobjectvalidator/0.9.1/kernel-ref-idobjectvalidator-0.9.1.jar kernel-ref-idobjectvalidator-0.9.1.jar

EXPOSE 8090

CMD if [ "$active_profile_env" = "c3a9236e12bb95f086275ff4647a7909dc8a0636e44b1a00f033a0c181255fc9" ]; then \
    wget 'http://13.71.87.138:8040/artifactory/libs-release-local/io/mosip/testing/glowroot.zip' ; \
    apt-get update && apt-get install -y unzip ; \
    unzip glowroot.zip ; \
    rm -rf glowroot.zip ; \
    
    sed -i 's/<service_name>/id-repository-identity-service/g' glowroot/glowroot.properties ; \
    java -Dloader.path=/kernel-ref-idobjectvalidator-0.9.1.jar -jar -javaagent:glowroot/glowroot.jar -Dspring.cloud.config.label="${spring_config_label_env}" -Dspring.profiles.active="${active_profile_env}"  -Dspring.cloud.config.uri="${spring_config_url_env}" id-repository-identity-service.jar ; \
    else \
    java -Dloader.path=/kernel-ref-idobjectvalidator-0.9.1.jar -jar -Dspring.cloud.config.label="${spring_config_label_env}" -Dspring.profiles.active="${active_profile_env}"  -Dspring.cloud.config.uri="${spring_config_url_env}" id-repository-identity-service.jar ; \
    fi


#CMD ["java","-Dloader.path=/kernel-ref-idobjectvalidator-0.9.1.jar","-jar","-Dspring.cloud.config.label=${spring_config_label_env}","-Dspring.profiles.active=${active_profile_env}","-Dspring.cloud.config.uri=${spring_config_url_env}","id-repository-identity-service.jar"]
