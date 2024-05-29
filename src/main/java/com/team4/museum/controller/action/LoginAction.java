package com.team4.museum.controller.action;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.team4.museum.dao.MemberDao;
import com.team4.museum.vo.MemberVO;

public class LoginAction implements Action{
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");
        MemberVO mvo = MemberDao.getInstance().getMember(id);

        String url = "loginForm.jsp";
        if (mvo == null) {
            request.setAttribute("message", "아이디가 없습니다"); 
        } else if (!mvo.getPwd().equals(pwd)) {
            request.setAttribute("message", "패스워드가 틀립니다");
        } else {
            url = "museum.do?command=index";
            request.getSession().setAttribute("loginUser", mvo);
        }

        request.getRequestDispatcher(url).forward(request, response);
    }
}
