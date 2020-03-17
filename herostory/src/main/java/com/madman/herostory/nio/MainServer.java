package com.madman.herostory.nio;

import io.netty.channel.nio.NioEventLoopGroup;

public class MainServer {

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

    }

}
