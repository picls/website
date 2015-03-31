package com;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dao.s.SPost;
import com.dao.s.SPostManager;
import com.dao.s.SUser;

public class CPost {

	public CPost() {

	}

	public List getPost(String id) {
		return this.getPost(id, "|");
	}

	public List getPost(String id, String delimiter) {
		/*
		 * Writer w = System.console().writer(); try{w.write(new
		 * Date().toString());} catch(IOException e){e.printStackTrace();}
		 * System.console().flush();
		 */
		SPostManager mgr = new SPostManager();
		mgr.setCloseOnce(false);

		List posts = mgr.selectAllByPropertyUnderCon("page_id", id,
				"order by time");
		List back = new ArrayList();
		// mgr.deleteAllByProperty("title", "test");
		for (int i = 0; i < posts.size(); i++) {
			SPost spost = (SPost) posts.get(i);
			SUser suser = spost.getSuser();
			String username = suser.getName();
			// String cid = spost.getId() + "";
			String ctitle = spost.getContent();
			String creator = suser.getId() + "";
			String time = spost.getCreateTime().toString();
			if((time == null)||(time == "")) 
				time = " ";
			//System.out.println(creator + delimiter + username + delimiter + ctitle + delimiter + time);
			back.add(creator + delimiter + username + delimiter + ctitle + delimiter + time);
		}

		return back;

	}

	public void addBlog(long creator) {
		SPostManager mgr = new SPostManager();
		// mgr.insert(pageId, creator, content, source);
	}

	public String getTime() {
		return new Date().toString();
	}

	public static void main(String args[]) {
		// Session session = HibernateUtil.currentSession();
		CPost cp = new CPost();
		List posts = cp.getPost("433460");
		for (int i = 0; i < posts.size(); i++) {
			String result = (String) posts.get(i);
			//System.out.println(result);
			//System.out.println(result.split("\\|")[0]);
		}
	}
}
