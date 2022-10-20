FROM java:8

#WORKDIR /home

#刷新包缓存 并且 安装wget工具
#RUN yum install -y tar

#RUN echo $PWD

#COPY /home/jdk-8u181-linux-x64.tar.gz jdk-8u181-linux-x64.tar.gz

# 安装jdk1.8
#RUN tar -zxvf jdk-8u181-linux-x64.tar.gz

# 配置环境变量
# set jdk
#ENV JAVA_HOME /home/jdk1.8.0_181
#ENV JRE_HOME $JAVA_HOME/jre
#ENV CLASSPATH .:$JAVA_HOME/lib:$JRE_HOME/lib
#ENV PATH $PATH:$JAVA_HOME/bin
ENV TZ=Asia/Shanghai

ADD target/*.jar /usr/local/app-car.jar

CMD ["nohup","java","-jar","/usr/local/app-car.jar","&"]