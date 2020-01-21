package ej.com.homedream.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.homedream.comm.Action;
import com.homedream.comm.ActionForward;

import ej.com.homedream.service.OrderService;



public class UpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String status1=request.getParameter("status");//��� insert.jsp��������
		String orderno1=request.getParameter("orderno");//list jsp���� ������
		int status=0; 
		int orderno=0;
		
		if(status1!=null&&!"".equals(status1))
			status=Integer.parseInt(status1);
		if(orderno1!=null&&!"".equals(orderno1))
			orderno=Integer.parseInt(orderno1);
		System.out.println("status"+status);
		System.out.println("orderno: "+orderno);
		OrderService service=OrderService.getService();
		
		service.update(orderno,status);
		
		ActionForward f=new ActionForward();
		f.setForward(true);//forward�� �������̵� 
		f.setUrl("/ej_order/ej_update.jsp");//��� �ٷ� .do��
		//f.setUrl("ej_list.do");
		request.setAttribute("orderno", orderno);//��� �Ѱ��� 
		//forward�� setAttributem��
	
		return f;
		
	}

}
