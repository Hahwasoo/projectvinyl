package yi.com.homedream.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.homedream.comm.Action;
import com.homedream.comm.ActionForward;

import yi.com.homedream.dto.YIMemberDTO;
import yi.com.homedream.dto.YIOrderlistDTO;
import yi.com.homedream.service.YIMemberService;

public class YIModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("userId");
		System.out.println("id:"+id);
		ActionForward f=new ActionForward();
		if(id==null)
		{
			f.setForward(false);
			f.setUrl("yilogin.do");
			
		}
		else
		{
			List<YIMemberDTO> list=new ArrayList<>();
			YIMemberService service=YIMemberService.getService();
			list=service.memberInfo(id);
			request.setAttribute("list", list);
			
			f.setForward(true);
			f.setUrl("/yi_member/membermodify.jsp");
		}
		
		return f;
	}

}
