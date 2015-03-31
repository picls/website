package com;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dao.s.SPage;
import com.dao.s.SPageManager;
import com.dao.s.SUser;

public class CPage {

	public List getPage() {
		SPageManager mgr = new SPageManager();

		List spages = mgr.selectAllUnderCon("where board_id <> 101", "order by time desc");
		List back = new ArrayList();

		for (int i = 0; i < spages.size(); i++) {
			String[] p = new String[9];
			SPage spage = (SPage) spages.get(i);
			SUser suser = spage.getSuser();
			System.out.println(spage.toString());
			//System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			//System.out.println(suser.toString());
			if (spage != null) {
				p[0] = spage.getId() + "";
				p[1] = spage.getBoardId() + "";
				p[2] = spage.getUrl();
				p[3] = spage.getTitle();
				p[4] = spage.getPageNum() + "";
				p[5] = spage.getCreateTime().toString();
				p[6] = spage.getReplyNum() + "";
				p[7] = suser.getId() + "";//spage.getCreator() + "";
				if (suser == null)
					p[8] = spage.getCreator() + "";
				else
					p[8] = suser.getName();
			}
			back.add(p);
		}
		return back;
	}

	public String[] getHotPage() {
		return this.getHotPage(10);
	}

	public String[] getHotPage(int num) {
		SPageManager mgr = new SPageManager();

		List result = null;
		int days = 0;
		while (true) {
			result = mgr.selectAllUnderCon("where time >= " + getDate(days),
					"order by reply_num desc limit " + num);
			if ((result != null) && (result.size() != 0))
				break;
			days++;
		}
		
		int l = 0;
		if(result.size() >= 10)
			l = 10;
		else 
			l = result.size();
		String[] back = new String[l];
		for (int i = 0; i < l; i++) {
			back[i] = ((SPage) result.get(i)).toString("|");
			back[i] += "|" + ((SPage) result.get(i)).getId();
		}
		return back;
	}

	public static String getDate() {
		return getDate(0);
	}

	public static String getDate(int num) {
		Date d = new Date();
		//System.out.println(d.getTime());
		d = new Date(d.getTime() + 1000 * 60 * 60 * 24 * num);
		//System.out.println(d.getTime());

		String year = (d.getYear() + 1900) + "";
		String month = (d.getMonth() + 1) + "";
		if (month.length() == 1)
			month = "0" + month;
		String day = d.getDate() + "";
		return year + month + day;
	}

	public String getTime() {
		return new Date().toString();
	}

	public static void main(String args[]) {
		CPage c = new CPage();
/*		String[] s = c.getHotPage(10);
		System.out.println(s[0]);
		System.out.println(s[0].split("\\|")[7]);*/
		List l = c.getPage();
	}
}
