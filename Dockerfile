# 使用官方的 OpenJDK 11 镜像作为基础镜像
FROM openjdk:17-jre

# 暴露应用程序的端口
EXPOSE 8255

# 将本地的 Spring Boot 可执行 JAR 复制到容器中的 /app 目录下
COPY target/Bud-Student-0.0.1-SNAPSHOT.jar /app/bud.jar

# 设置容器的工作目录
WORKDIR /app

# 运行 Spring Boot 应用程序
CMD ["java", "-jar", "bud.jar"]
