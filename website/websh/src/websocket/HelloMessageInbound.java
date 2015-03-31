package websocket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.CharBuffer;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WsOutbound;

public class HelloMessageInbound extends StreamInbound {

	private String WS_NAME;
	private final String FORMAT = "%s : %s";
	private final String PREFIX = "ws_";
	private String sessionId = "";
	private Listener l = null;
	private String url;
	private String id;

	/*
	 * public HelloMessageInbound(int id, String _sessionId) { this.WS_NAME =
	 * PREFIX + id; this.sessionId = _sessionId; }
	 */

	public HelloMessageInbound(int id, HttpServletRequest request) {
		this.WS_NAME = PREFIX + id;
		this.sessionId = request.getSession().getId();
		this.url = request.getRequestURI().toString();
		this.id = request.getParameter("id");
	}

	@Override
	protected void onClose(int status) {
		System.out.println(String.format(FORMAT, WS_NAME, "closing ......"));
		super.onClose(status);
	}

	@Override
	protected void onOpen(WsOutbound outbound) {
		super.onOpen(outbound);
		try {
			send("hello, my name is " + WS_NAME);
			send("session id = " + this.sessionId);
			send(this.url);
			send(this.url);
			send(this.id);
			l = new Listener(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void send(String message) throws IOException {
		message = String.format(FORMAT, WS_NAME, message);
		System.out.println(message);
		getWsOutbound().writeTextMessage(CharBuffer.wrap(message));
	}

	public void outSend(String message) {
		try {
			this.send(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onTextData(Reader reader) throws IOException {
		char[] chArr = new char[1024];
		int len = reader.read(chArr);
		send(String.copyValueOf(chArr, 0, len));
	}

	@Override
	protected void onBinaryData(InputStream arg0) throws IOException {
	}
}

class ListenThread extends Thread {
	public String context = "";
	public HelloMessageInbound h = null;

	public ListenThread(HelloMessageInbound hx) {
		this.h = hx;
	}

	public String readFile(String name) {
		String back = "";
		File file = new File(name);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				back += tempString;
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return back;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			String temp_context = this.readFile("c://myworkspace//web//WebRoot//x.txt");
			if (!temp_context.equals(this.context)) {
				this.h.outSend(temp_context);
				this.context = temp_context;
			}
		}
	}
}

class Listener {
	public Listener() {
	}

	public Listener(HelloMessageInbound h) {
		this.startListen(h);
	}

	public void startListen(HelloMessageInbound h) {
		ListenThread lt = new ListenThread(h);
		Thread t1 = new Thread(lt);
		// t1.setDaemon(true);
		t1.start();
	}

}