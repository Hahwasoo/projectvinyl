package ej.com.homedream.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.homedream.comm.Action;
import com.homedream.comm.ActionForward;

import ej.com.homedream.service.MemberService;

public class  InsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String memno1=request.getParameter("memNo");
		String addpoint1=request.getParameter("addpoint");
		System.out.println("memNo�� addpoint :"+memno1+addpoint1);
		/*int memno=Integer.parseInt(memno1);
		System.out.println("update memno:"+memno);*/
		
		MemberService service=MemberService.getService();
		//service.update(memno);
		
		
		ActionForward f=new ActionForward();
		f.setForward(true);//forward�� �������̵� �� send redirect�� ��
		f.setUrl("/ej_member/ej_insert.jsp");//��� �ٷ� .do��
		
		return f;
		
	}

}
