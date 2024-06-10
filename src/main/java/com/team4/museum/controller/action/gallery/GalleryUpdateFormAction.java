package com.team4.museum.controller.action.gallery;

import java.io.IOException;

import com.team4.museum.controller.action.Action;
import com.team4.museum.dao.MemberGalleryDao;
import com.team4.museum.util.MultipartFileInfo;
import com.team4.museum.vo.MemberGalleryVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GalleryUpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberGalleryDao mgdao = MemberGalleryDao.getInstance();
		MemberGalleryVO mgvo = new MemberGalleryVO();
		int mseq = Integer.parseInt(request.getParameter("mseq"));
		mgvo.setMseq(mseq);
		mgvo.setTitle(request.getParameter("title"));
		mgvo.setContent(request.getParameter("content"));
		mgvo.setAuthorId(request.getParameter("authorid"));

		MultipartFileInfo info = MultipartFileInfo.getFromRequest(request, "static/image/gallery");
		if(info.getFileName().equals("") || info.getFileName() == null) {
			mgdao.updateMemberGalleryWithoutImg(mgvo);
		}else {
			mgvo.setImage(info.getFileName());
			mgvo.setSavefilename(info.getSaveFileName());
			mgdao.updateMemberGallery(mgvo);
		}

		System.out.println("getFileName : " + info.getFileName());
		System.out.println("getSaveFileName : " + info.getSaveFileName());

		request.getRequestDispatcher("museum.do?command=galleryView&mseq=" + mseq).forward(request, response);

	}

}
