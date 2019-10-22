#!/usr/bin/env bash
aliyunImgname=registry.cn-hangzhou.aliyuncs.com/engine/spring-boot-example:latest


docker tag sha256:0d7686a92cb584efd6cd35b774c2c5dc7355c98bc2b6ef258c0e345c984e7ed1 $aliyunImgname

docker push $aliyunImgname

# 运行
#docker run --name spring-boot-example -d -p 8080:8080 $aliyunImgname

#docker run --name spring-boot-example -d -p 8080:8080 registry.cn-hangzhou.aliyuncs.com/engine/spring-boot-example:latest