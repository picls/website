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
// ����WebSocket��Servlet��Ҫ�̳���WebSocketServlet����һ���7.0.27��Ȼһ��
public class EchoServlet extends WebSocketServlet {
    // Log
    private Logger logger = Logger.getLogger(EchoServlet.class.getName());

    @Override
    // ��7.0.27��ͬ�ģ�Tomcat�ı���createWebSocketInbound�����Ķ��壬������һ��HttpServletRequest����
    // ��������Ҳ���Դ�request�����л�ȡ�������󷽵���Ϣ
    protected StreamInbound createWebSocketInbound(String subProtocol,
                                                   HttpServletRequest request) {
        // Log
        //        logger.setLevel(java.util.logging.Level.ALL);
        logger.info("request ws servlet");

        // ������Ȼ�Ƿ���һ��StreamInboundʵ�����������ʵ����������MessageInbound
        // ֻ��ʵ�������ĸ��¼�������(��ʵonClose��onOpen��ȱʡʵ��)
        return new MessageInbound() {
            // WebSocket�ر��¼�������statusӦ������org.apache.catalina.websocket.Constants�ж���ļ������������Բο��ĵ����ߺ˶�һ��Tomcat��Դ��
            @Override
            protected void onClose(int status) {
                // Log
                logger.info("Web Socket Closed: " + status);
            }

            // WebSocket������ɣ�������ϣ�WsOutbound������ͻ��˷�������
            @Override
            protected void onOpen(WsOutbound outbound) {
                // Log
                logger.info("Web Socket Open!");
            }

            // �ж�������Ϣ���ݵ����ʱû�о����������ʲô����´�����js��WebSocket����˵Ӧ��ֻ��send�ı���Ϣ�Ŷ�
            @Override
            protected void onBinaryMessage(ByteBuffer buffer) throws IOException {
                // Log
                logger.info("Binary Message Receive: " + buffer.remaining());
                // Nothing
            }

            // ���ı���Ϣ���ݵ���
            @Override
            protected void onTextMessage(CharBuffer buffer) throws IOException {
                // Log
                logger.info("Text Message Receive: " + buffer.remaining());
                // getWsOutbound���Է��ص�ǰ��WsOutbound��ͨ������ͻ��˻ش����ݣ�������õ���nio��CharBuffer
                getWsOutbound().writeTextMessage(buffer);
                
                //ģ�������֪ͨ���������������ͻῴ��ÿ3���յ�1����������Ϣ
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
