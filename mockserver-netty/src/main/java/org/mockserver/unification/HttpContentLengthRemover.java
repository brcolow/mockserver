package org.mockserver.unification;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.DefaultHttpMessage;
import io.netty.util.ReferenceCountUtil;

import java.util.List;

/**
 * @author jamesdbloom
 */
@ChannelHandler.Sharable
public class HttpContentLengthRemover extends MessageToMessageEncoder<DefaultHttpMessage> {
    private static final String CONTENT_LENGTH = "Content-Length";

    @Override
    protected void encode(ChannelHandlerContext ctx, DefaultHttpMessage defaultHttpMessage, List out) {
        if (defaultHttpMessage.headers().contains(CONTENT_LENGTH, "", true)) {
            defaultHttpMessage.headers().remove(CONTENT_LENGTH);
        }
        ReferenceCountUtil.retain(defaultHttpMessage);
        out.add(defaultHttpMessage);
    }
}
