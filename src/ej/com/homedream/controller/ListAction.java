package ej.com.homedream.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.homedream.comm.Action;
import com.homedream.comm.ActionForward;

import ej.com.homedream.dto.MemberDTO;
import ej.com.homedream.service.MemberService;

public class ListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		request.setCharacterEncoding("utf-8");
		
		int currpage=1;
		String curr=request.getParameter("curr");
		if(curr!=null)
		{
			currpage=Integer.parseInt(curr);
		}
		
				
		MemberService service=MemberService.getService();//BoardService���� �̱����������� ­����
		
		//�˻�����
				String search=request.getParameter("search");//list.jsp���� �޾ƿ��°�
				String txtsearch=request.getParameter("txtsearch");//list.jsp���� �޾ƿ��°�
						if(search==null)//null��ó��
							search="";
						if(txtsearch==null)
							txtsearch="";
				//�˻���
		//����¡����
		int totalcount=service.getCount(search, txtsearch);//��ü�ڷ�
		System.out.println("totalcount: "+totalcount);
		int countperpage=15;//���������� ������ �ڷ�
		int totalpage=(int)Math.ceil((float)totalcount/countperpage);
		int startrow=(currpage-1)*countperpage+1;
		int endrow=startrow+countperpage-1;
		if(endrow>totalcount) endrow=totalcount;

		//����¡ ��
		//������
		int blockcount=5;
		int startblock=(currpage-1)/blockcount*blockcount+1;
		int endblock=startblock+blockcount-1;
		System.out.println("startblock:"+startblock);
		System.out.println("endblock:"+endblock);
		if(endblock>totalpage) endblock=totalpage;
		//����	
		
		
		
		List<MemberDTO> list=service.getList(startrow,endrow,search,txtsearch);//�̰� �� ��ü �������°Ű�(����¡����)
		
		
		//List<MemberDTO> slist=service.getselectedList();//�����Ѱ� ����Ʈ�� �ִ°�. 2����
		
		request.setAttribute("list", list);
		request.setAttribute("currpage", currpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startblock", startblock);
		request.setAttribute("endblock", endblock);
		request.setAttribute("search",search);
		request.setAttribute("txtsearch", txtsearch);
		//request.setAttribute("slist", slist);//2����
		
		String[] sCheck=request.getParameterValues("select");
		//forward�� �ѱ��
		ActionForward f=new ActionForward();
		f.setForward(true);//forward�� �������̵�
		f.setUrl("/list2.jsp");//ej_list.jsp�� �ѱ�. �ٵ� �� �� jsp���� �𸣰���. ������ �����
		
		return f;
		
	
	}
	

}
