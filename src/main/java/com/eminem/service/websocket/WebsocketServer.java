package com.eminem.service.websocket;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;

/**
 * Created by jsecode01 on 2017/6/9.
 */

@Component
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
                serverSocketChannel1.socket().bind(new InetSocketAddress("localhost",port1));
                //将选择器注册到通道上，返回一个选择键
                //OP_ACCEPT用于套接字接受操作的操作集位
                serverSocketChannel1.register(selector, SelectionKey.OP_ACCEPT);

                //初始化第二个服务
                serverSocketChannel2=ServerSocketChannel.open();
                serverSocketChannel2.configureBlocking(false);
                serverSocketChannel2.socket().bind(new InetSocketAddress("localhost",port2));
                serverSocketChannel2.register(selector,SelectionKey.OP_ACCEPT);

            }catch (Exception e){
             e.printStackTrace();
            }
        }


    /**
     * 这个方法是连接
     * 客户端连接服务器
     * @param key
     * @throws IOException
     */
    public void accept(SelectionKey key) throws IOException{
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            if(server.equals(serverSocketChannel1)){
                socketChannel1=server.accept();
                socketChannel1.configureBlocking(false);
                //OP_READ用于读取操作的操作集位
                socketChannel1.register(selector,SelectionKey.OP_READ);

            }else {
                socketChannel2=server.accept();
                socketChannel2.configureBlocking(false);
                //OP_READ用于读取操作的操作集位
                socketChannel2.register(selector,SelectionKey.OP_READ);

            }
        }

    /**
     *从通道中读取数据,并且判断是给那个服务通道的
     * @param key
     * @throws IOException
     */
        public void read(SelectionKey key) throws IOException {
            byteBuffer.clear();
            //通过选择键来找到之前注册的通道
            //但是这里注册的是ServerSocketChannel为什么会返回一个SocketChannel？？
            SocketChannel channel = (SocketChannel)key.channel();
            //从通道里面读取数据到缓冲区并返回读取字节数
             int count = channel.read(byteBuffer);
             if(count==-1){
                 key.channel().close();
                 key.cancel();
                 return;
             }
             //将数据从缓冲区中拿出来
            String input= new String(byteBuffer.array() ).trim();
            //那么现在判断是连接的那种服务
            if(channel.equals(socketChannel1)){
                System.out.println("欢迎您使用服务A");
                System.out.println("您的输入为："+input);
            }else {
                System.out.println("欢迎您使用服务B");
                System.out.println("您的输入为："+input);
            }

        }

        public void start(){
            while (true){
                try {
                    System.out.println("running ... ");
                    //选择一组键，其相应的通道已为 I/O 操作准备就绪。
                    selector.select();
                    //返回此选择器的已选择键集
                    //public abstract Set<SelectionKey> selectedKeys()
                    Iterator selectKeys = selector.selectedKeys().iterator();
                    while (selectKeys.hasNext()){
                        System.out.println("running2... ");
                        SelectionKey key = (SelectionKey) selectKeys.next();
                        //然后将它从返回键队列中删除
                        selectKeys.remove();
                        if(!key.isValid()){
                            continue;
                        }
                        if(key.isAcceptable()){
                            //如果遇到请求那么就响应
                            accept(key);
                        }else if(key.isReadable()){
                            //读取客户端的数据
                            read(key);

                        }
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }


}
