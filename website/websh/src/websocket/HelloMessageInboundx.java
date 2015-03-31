package websocket;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

@WebServlet("/DemoWebSocket")
public class HelloMessageInboundx extends WebSocketServlet {
	private static final long serialVersionUID = 1L;
	private final Set<DemoMessageInbound> connections = new CopyOnWriteArraySet<DemoMessageInbound>();

	@Override
	protected StreamInbound createWebSocketInbound(String subProtocol,
			HttpServletRequest request) {
		return new DemoMessageInbound();
	}

	private final class DemoMessageInbound extends MessageInbound {
		@Override
		protected void onOpen(WsOutbound outbound) {
			connections.add(this);
			String message = "websocket�Ѿ���";
			broadcast(message);
		}

		@Override
		protected void onClose(int status) {
			connections.remove(this);
			String message = "webSocket�ر�";
			broadcast(message);
		}

		@Override
		protected void onBinaryMessage(ByteBuffer message) throws IOException {
			// �����ﴦ�����������
		}

		@Override
		protected void onTextMessage(CharBuffer message) throws IOException {
			// ���ﴦ������ı�����
			String filteredMessage = message.toString();
			broadcast(filteredMessage);
		}

		// �����ݴ��ؿͻ���
		private void broadcast(String message) {
			for (DemoMessageInbound connection : connections) {
				try {
					CharBuffer buffer = CharBuffer.wrap(message);
					connection.getWsOutbound().writeTextMessage(buffer);
				} catch (IOException ignore) {
					// Ignore
				}
			}
		}
	}
}
