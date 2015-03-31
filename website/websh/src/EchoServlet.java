import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;

/**
 * 
 * @author piaohailin
 * 2012-09-08
 *
 */
@WebServlet(urlPatterns = "/echo.ws")
// 处理WebSocket的Servlet需要继承自WebSocketServlet，这一点和7.0.27仍然一样
public class EchoServlet extends WebSocketServlet {
    // Log
    private Logger logger = Logger.getLogger(EchoServlet.class.getName());

    @Override
    // 与7.0.27不同的，Tomcat改变了createWebSocketInbound方法的定义，增加了一个HttpServletRequest参数
    // 这样我们也可以从request参数中获取更多请求方的信息
    protected StreamInbound createWebSocketInbound(String subProtocol,
                                                   HttpServletRequest request) {
        // Log
        //        logger.setLevel(java.util.logging.Level.ALL);
        logger.info("request ws servlet");

        // 方法仍然是返回一个StreamInbound实例，这里采用实现他的子类MessageInbound
        // 只用实现下面四个事件处理函数(其实onClose和onOpen有缺省实现)
        return new MessageInbound() {
            // WebSocket关闭事件，参数status应该来自org.apache.catalina.websocket.Constants中定义的几个常量，可以参考文档或者核对一下Tomcat的源码
            @Override
            protected void onClose(int status) {
                // Log
                logger.info("Web Socket Closed: " + status);
            }

            // WebSocket握手完成，创建完毕，WsOutbound用于向客户端发送数据
            @Override
            protected void onOpen(WsOutbound outbound) {
                // Log
                logger.info("Web Socket Open!");
            }

            // 有二进制消息数据到达，暂时没研究出这个函数什么情况下触发，js的WebSocket按理说应该只能send文本信息才对
            @Override
            protected void onBinaryMessage(ByteBuffer buffer) throws IOException {
                // Log
                logger.info("Binary Message Receive: " + buffer.remaining());
                // Nothing
            }

            // 有文本消息数据到达
            @Override
            protected void onTextMessage(CharBuffer buffer) throws IOException {
                // Log
                logger.info("Text Message Receive: " + buffer.remaining());
                // getWsOutbound可以返回当前的WsOutbound，通过他向客户端回传数据，这里采用的是nio的CharBuffer
                getWsOutbound().writeTextMessage(buffer);
                
                //模拟服务器通知浏览器，从浏览器就会看，每3秒收到1条服务器消息
                //                for (int i = 0; i < 3; i++) {
                //                    try {
                //                        Thread.sleep(3000);
                //                    } catch (InterruptedException e) {
                //                        // TODO Auto-generated catch block
                //                        e.printStackTrace();
                //                    }
                //                    getWsOutbound().writeTextMessage(CharBuffer.wrap(String.valueOf(i).subSequence(0, 1)));
                //                }

            }
        };
    }
}
