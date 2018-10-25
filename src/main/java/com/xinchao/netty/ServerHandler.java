package com.xinchao.netty;

import com.xinchao.entity.User;
import com.xinchao.service.UserService;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.ChannelHandler.Sharable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Sharable
public class ServerHandler extends ChannelHandlerAdapter {
    @Resource
    private UserService userService;

    /**
     * 获取数据
     * @param ctx 上下文
     * @param msg 获取的数据
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        List<User> users = userService.selectAll();
        users.forEach(user-> System.out.println(user.toString()));
        //不需要再将msg转成ByteBuf类型了，直接可以强转成String类型也可以直接输出msg，因为msg已经是String类型了
        /*
        ByteBuf readMessage= (ByteBuf) msg;
        System.out.println(readMessage.toString(CharsetUtil.UTF_8));
        */
        String data = (String) msg;
        System.out.println("Client["+ctx.name()+"]"+new String(data).trim());

        ctx.writeAndFlush(Unpooled.copiedBuffer("服务器反馈$_".getBytes()));
                //.addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
