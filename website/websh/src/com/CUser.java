package com;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dao.s.SPage;
import com.dao.s.SUser;
import com.dao.s.SUserManager;
import com.dao.s.SPost;
import com.dao.s.SPostManager;

public class CUser {

	public List getUser() {
		SUserManager mgr = new SUserManager();

		List susers = mgr.selectAll();
		return susers;
	}

	public String[] getUserOnly(String username) {
		String[] back = new String[6];
		SUserManager mgr = new SUserManager();
		SUser suser = mgr.selectOneByProperty("name", username);
		if (suser != null) {
			back[0] = suser.getName();
			back[1] = suser.getNickname();
			back[2] = suser.getLevel();
			back[3] = suser.getArticle() + "";
			back[4] = suser.getIntegration() + "";
			back[5] = suser.getConstellation();
		} else
			for (int i = 0; i < 6; i++)
				back[i] = "";
		return back;
	}

	public String[] getUserPost(String username) {
		SUserManager umgr = new SUserManager();
		SUser suser = umgr.selectOneByProperty("name", username);
		long user_id = suser.getId();
		return this.getUserPostById(user_id + "");
	}
	
	public String[] getUserPostById(String user_id) {
		List result = new ArrayList();
		SPostManager pmgr = new SPostManager();
		result = pmgr.selectAllByProperty("creator", user_id);
		String[] back = new String[result.size()];
		SPost spost = new SPost();
		SPage spage = new SPage();
		SUser suser = new SUser();
		for (int i = 0; i < result.size(); i++) {
			spost = (SPost)result.get(i);
			spage = spost.getSpage();
			suser = spost.getSuser();
			back[i] += spage.getId() + "|";
			back[i] += spage.getTitle() + "|";
			back[i] += suser.getName() + "|";
			back[i] += spost.getContent() + "|";
			back[i] += spost.getCreateTime() + "|";
			back[i] += spost.getSource();
			if(back[i].endsWith("|"))
				back[i] = back[i] + "none";
		}
		return back;
	}

	public String getTime() {
		return new Date().toString();
	}

	public static void main(String args[]) {
/*		String xxx = "1|2|3|";
		System.out.println(xxx.split("\\|").length);
		System.exit(0);*/
				
		// Session session = HibernateUtil.currentSession();
		CUser c = new CUser();
		//String[] info = c.getUserPost("toobee");
		String[] info = c.getUserPostById("1530");
		int l = info.length;
		if(l>20) l = 20;
		for(int i=0;i<l;i++){
			//System.out.println(info[i]);
			String[] s = info[i].split("\\|");
			//System.out.println(s.length);
			//s[4] = "1";
/*			System.out.print(s[0]+"   ");
			System.out.print(s[1]+"   ");
			System.out.print(s[2]+"   ");
			System.out.print(s[3]+"   ");
			System.out.println(s[4]+"   ");
			System.out.println("文章："+s[0]+"<br>");
			System.out.println("内容："+s[2]+"<br>");
			System.out.println("时间："+s[3]+"<br>");
			System.out.println("来源："+s[4]+"<br>");*/
		}
/*		String s[] = c.getUserOnly("admin");
		for (int i = 0; i < s.length; i++)
			System.out.println(s[i]);*/
	}

}
