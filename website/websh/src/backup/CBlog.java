package backup;

import java.io.Console;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.dao.s.SPage;
import com.dao.s.SPageManager;
import com.dao.s.SUser;

import base.HibernateUtil;

public class CBlog {
	
	public CBlog() {
		
	}
	
	public List getBlog() {
		SPageManager mgr = new SPageManager();
		
		List spages = mgr.selectAllUnderCon("","order by time desc");
		List back = new ArrayList();
		
		for (int i = 0; i < spages.size(); i++) {
			String[] p = new String[9];
			SPage spage = (SPage) spages.get(i);
			SUser suser = spage.getSuser();
			if(spage != null){
				p[0] = spage.getId() + "";
				p[1] = spage.getBoardId() + "";
				p[2] = spage.getUrl();
				p[3] = spage.getTitle();
				p[4] = spage.getPageNum() + "";
				p[5] = spage.getCreateTime().toString();
				p[6] = spage.getReplyNum() + "";
				p[7] = spage.getCreator() + "";
				if(suser==null)
					p[8] = spage.getCreator() + "";
				else
					p[8] = suser.getName();
			}
			back.add(p);
		}
		
		return back;
		
	}
	
	
	public void addBlog(long creator) {
		SPageManager mgr = new SPageManager();
		//
	}
	
	public String getTime(){
		return new Date().toString();
	}
	
	public static void main(String args[]) {
		CBlog c = new CBlog();
		List blogs = c.getBlog();
		
		String[] blog = (String[])blogs.get(0);
		System.out.println(blog[3]);
		String back = "";
		int x = blog[3].lastIndexOf("[");
		System.out.println(x);
		if(x>0){
			int y = blog[3].lastIndexOf("]");
			String z1 = blog[3].substring(x+1,x+2);
			String z2 = blog[3].substring(y-1,y);
			try{
				Integer.valueOf(z1);
				Integer.valueOf(z2);
				blog[3] = blog[3].substring(0,x);
			} catch(Exception e){e.printStackTrace();}
		}
		System.out.println(blog[3]);
	}
	
}
