package backup;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import com.dao.s.SPost;
import com.dao.s.SPostManager;

public class GetPost {

	public GetPost() {
		
	}
	
	public List getPost() {
/*		Writer w = System.console().writer();
		try{w.write(new Date().toString());} catch(IOException e){e.printStackTrace();}
		System.console().flush();*/
		SPostManager mgr = new SPostManager();
		mgr.setCloseOnce(false);
		
		List posts = mgr.selectAllByPropertyUnderCon("page_id", "1498307", "order by time");
		List back = new ArrayList();
		// mgr.deleteAllByProperty("title", "test");
		for (int i = 0; i < posts.size(); i++) {
			SPost spost = (SPost) posts.get(i);
			String cid = spost.getId() + "";
			String ctitle = spost.getContent();
			String creator = spost.getCreator() + "";
			String time = spost.getCreateTime().toString();
			back.add(cid+" "+ctitle+" "+creator+" "+time);
		}
		
		return back;
		
	}
	
	public void addBlog(long creator) {
		SPostManager mgr = new SPostManager();
		//mgr.insert(pageId, creator, content, source);
	}
	
	public String getTime(){
		return new Date().toString();
	}
	
	public static void main(String args[]) {
		//Session session = HibernateUtil.currentSession();
		GetPost gp = new GetPost();
		List posts = gp.getPost();
		for (int i = 0; i < posts.size(); i++) {
			String result = (String)posts.get(i);
			System.out.println("<p>"+result+"</p>");
		}
	}
}
