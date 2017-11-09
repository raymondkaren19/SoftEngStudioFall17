# Derived from https://hub.docker.com/r/jared314/docker-ubuntu-14-04-java8/~/dockerfile/

FROM ubuntu:trusty

RUN apt-get update && apt-get -y upgrade && apt-get -y install software-properties-common && add-apt-repository ppa:webupd8team/java -y && apt-get update

RUN (echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections) && apt-get install -y oracle-java8-installer oracle-java8-set-default

ENV JAVA_HOME /usr/lib/jvm/java-8-oracle
ENV PATH $JAVA_HOME/bin:$PATH

# Install Maven and Gradle

RUN apt-get -y install maven wget unzip
RUN wget https://services.gradle.org/distributions/gradle-4.3-bin.zip
RUN mkdir /opt/gradle
RUN unzip -d /opt/gradle gradle-4.3-bin.zip
RUN export PATH=$PATH:/opt/gradle/gradle-4.3/bin

# Derived from https://hub.docker.com/r/aallam/tomcat-mysql/~/dockerfile/
ENV DEBIAN_FRONTEND noninteractive

ENV TOMCAT_MAJOR_VERSION=8
ENV TOMCAT_VERSION=8.5.14
ENV TOMCAT_HOME=/opt/tomcat

RUN apt-get update && \
  apt-get -yq install mysql-server supervisor && \
  rm -rf /var/lib/apt/lists/*

WORKDIR /tmp

RUN groupadd tomcat && \
    useradd -s /bin/false -g tomcat -d $TOMCAT_HOME tomcat && \
    mkdir $TOMCAT_HOME && \
    wget archive.apache.org/dist/tomcat/tomcat-$TOMCAT_MAJOR_VERSION/v$TOMCAT_VERSION/bin/apache-tomcat-$TOMCAT_VERSION.tar.gz && \
    tar xzvf apache-tomcat-8*tar.gz -C $TOMCAT_HOME --strip-components=1 && \
    chown -R tomcat:tomcat $TOMCAT_HOME && \
    chmod -R g+r $TOMCAT_HOME/conf && \
    chmod g+x $TOMCAT_HOME/conf && \
    rm -rf apache-tomcat-$TOMCAT_VERSION.tar.gz

WORKDIR /

ADD bind_0.cnf /etc/mysql/conf.d/bind_0.cnf
ADD mysql-run.sh /mysql-run.sh
ADD tomcat-run.sh /tomcat-run.sh
ADD run.sh /run.sh
ADD supervisord-mysql.conf /etc/supervisor/conf.d/supervisord-mysql.conf
ADD supervisord-tomcat.conf /etc/supervisor/conf.d/supervisord-tomcat.conf
ADD settings.xml $TOMCAT_HOME/conf/settings.xml
ADD tomcat-users.xml $TOMCAT_HOME/conf/tomcat-users.xml
ADD context.xml $TOMCAT_HOME/webapps/manager/META-INF/context.xml
RUN chmod 755 /*.sh

VOLUME ["/var/lib/mysql"]

## SSH Set up

RUN apt-get update && \
    apt-get install -y \
    autoconf \
    sudo \
    openssh-server \
    && \
    apt-get clean 

# Setup Users
RUN groupadd -r ssh-docker && useradd -r -g ssh-docker ssh-docker
RUN sudo adduser ssh-docker sudo

# Setup SSH server options
RUN mkdir /var/run/sshd
RUN echo "AllowGroups ssh-docker" >> /etc/ssh/sshd_config
RUN echo 'ssh-docker:devPassword' | chpasswd

# SSH login fix. Otherwise user is kicked off after login
RUN sed 's@session\s*required\s*pam_loginuid.so@session optional pam_loginuid.so@g' -i /etc/pam.d/sshd

ENV NOTVISIBLE "in users profile"
RUN echo "export VISIBLE=now" >> /etc/profile
ADD supervisord-sshd.conf /etc/supervisor/conf.d/supervisord-sshd.conf

EXPOSE 22 3306 8080

ENTRYPOINT ["/run.sh"]
