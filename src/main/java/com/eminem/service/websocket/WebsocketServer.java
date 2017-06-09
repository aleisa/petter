package com.eminem.service.websocket;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;

/**
 * Created by jsecode01 on 2017/6/9.
 */
public class WebsocketServer {
        //第一个端口
        private int port1=8099;
        //第二个端口
        private int port2=9099;
        //第一个服务通道
        private ServerSocketChannel serverSocketChannel1;
        //第二个服务通道
        private ServerSocketChannel serverSocketChannel2;
        //连接1
        private SocketChannel socketChannel1;
        //连接2
        private SocketChannel socketChannel2;

    //选择器，主要用来监控各个通道的事件
        private Selector selector;
        //缓冲
        private ByteBuffer byteBuffer;
        public WebsocketServer(){

        }
        public void init(){
            try{
                //创建选择器
                this.selector = SelectorProvider.provider().openSelector();
                //打开第一个通道
                serverSocketChannel1.configureBlocking(false);
                //绑定套接字
                serverSocketChannel1.socket().bind(new InetSocketAddress(""));
            }catch (Exception e){

            }


        }

}
