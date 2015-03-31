package websocket;

import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.http.HttpServletRequest;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

public class HelloWebSocketServlet extends WebSocketServlet {
	private static final long serialVersionUID = 1L;

	private final AtomicInteger connectionIds = new AtomicInteger(0);
	@Override
	protected StreamInbound createWebSocketInbound(String arg0,
			HttpServletRequest request) {
		Enumeration e = request.getAttributeNames();
		while(e.hasMoreElements()){System.out.println(e.nextElement().toString());}
		e = request.getParameterNames();
		while(e.hasMoreElements()){System.out.println(e.nextElement().toString());}
		return new HelloMessageInbound(connectionIds.getAndIncrement(), request);
		//.getSession().getId()
	}
	
	
	  
      
    public static int ONLINE_USER_COUNT = 1;  
      
    public String getUser(HttpServletRequest request){  
        return (String) request.getSession().getAttribute("user");  
    }  
}
