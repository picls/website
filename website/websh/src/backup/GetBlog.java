package backup;

import java.io.Console;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.dao.Blog;
import com.dao.BlogManager;
import base.HibernateUtil;

public class GetBlog {
	
	public GetBlog() {
		
	}
	
	public List getBlog() {
/*		Writer w = System.console().writer();
		try{w.write(new Date().toString());} catch(IOException e){e.printStackTrace();}
		System.console().flush();*/
		BlogManager mgr = new BlogManager();
		mgr.setCloseOnce(false);
		
		List blogs = mgr.selectAll();
		
		for (int i = 0; i < blogs.size(); i++) {
			Blog blog = (Blog) blogs.get(i);
			String cid = blog.getId() + "";
			String ctitle = blog.getTitle();
			String creator = blog.getCreator() + "";
			String time = blog.getCreateTime().toString();
		}

		// mgr.deleteAllByProperty("title", "test");
		
		return blogs;
		
	}
	
	public void addBlog(long creator) {
		BlogManager mgr = new BlogManager();
		mgr.insert("test", creator, "");
	}
	
	public String getTime(){
		return new Date().toString();
	}
	
	public static void main(String args[]) {
		//Session session = HibernateUtil.currentSession();
	}
	
}
