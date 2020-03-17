package com.madman.doc.docker;

public class DockerDemo {

    // * docker
    // * docker search 镜像名 ： docker search redis
    // * docker pull 镜像名:版本 docker pull redis / docker pull redis:4.0.1
    // * docker build -t xx/容器名:版本
    // * Dockerfile
    // * FROM centos
    // * MAINTAINER madman
    // * WORKDIR /xxx
    // * ADD package .path
    // * EXPOSE 90
    // * RUN ["echo", "building"]
    // * ENTRYPOINT ["ps"]
    // * CMD ["-ef"]
    // * //运行 link 单向通信
    // * docker run -p -d --name xxx --link app image
    // * //双向通信用bridge
    // * docker network ls 查看当前bridge
    // * docker network create -d bridge name 创建新网桥
    // * docker network connect my-bridge app-name
    // * docker run -v 宿主机路径:docker路径 将宿主机路径映射到docker路径下实现文件共享的功能
    // * docker create --name page -v 宿主机路径:docker路径 ..
    // * docker run --volumes-from page --name app-name 也可以先创建一个共享容器，然后使用--volumes-from xx挂载

    // docker-compose
    //

}
