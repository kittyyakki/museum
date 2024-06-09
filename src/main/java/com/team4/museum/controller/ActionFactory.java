package com.team4.museum.controller;

import com.team4.museum.controller.action.Action;
import com.team4.museum.controller.action.IndexAction;
import com.team4.museum.controller.action.admin.AdminAction;
import com.team4.museum.controller.action.admin.AdminArtworkListAction;
import com.team4.museum.controller.action.admin.AdminDeleteArtworkAction;
import com.team4.museum.controller.action.admin.AdminDeleteGalleryAction;
import com.team4.museum.controller.action.admin.AdminDeleteMemberAction;
import com.team4.museum.controller.action.admin.AdminDeleteNoticeAction;
import com.team4.museum.controller.action.admin.AdminDeleteQnaAction;
import com.team4.museum.controller.action.admin.AdminGalleryListAction;
import com.team4.museum.controller.action.admin.AdminMemberListAction;
import com.team4.museum.controller.action.admin.AdminNoticeListAction;
import com.team4.museum.controller.action.admin.AdminQnaListAction;
import com.team4.museum.controller.action.admin.GrantAdminRightsAction;
import com.team4.museum.controller.action.artwork.ArtworkDeleteAction;
import com.team4.museum.controller.action.artwork.ArtworkDisplaySetAction;
import com.team4.museum.controller.action.artwork.ArtworkListAction;
import com.team4.museum.controller.action.artwork.ArtworkUpdateAction;
import com.team4.museum.controller.action.artwork.ArtworkUpdateFormAction;
import com.team4.museum.controller.action.artwork.ArtworkViewAction;
import com.team4.museum.controller.action.artwork.ArtworkWriteAction;
import com.team4.museum.controller.action.artwork.ArtworkWriteFormAction;
import com.team4.museum.controller.action.gallery.GalleryDeleteAction;
import com.team4.museum.controller.action.gallery.GalleryListAction;
import com.team4.museum.controller.action.gallery.GalleryUpdateAction;
import com.team4.museum.controller.action.gallery.GalleryUpdateFormAction;
import com.team4.museum.controller.action.gallery.GalleryViewAction;
import com.team4.museum.controller.action.gallery.GalleryWriteAction;
import com.team4.museum.controller.action.gallery.GalleryWriteFormAction;
import com.team4.museum.controller.action.member.IdcheckFormAction;
import com.team4.museum.controller.action.member.JoinAction;
import com.team4.museum.controller.action.member.JoinFormAction;
import com.team4.museum.controller.action.member.LoginAction;
import com.team4.museum.controller.action.member.LoginFormAction;
import com.team4.museum.controller.action.member.LogoutAction;
import com.team4.museum.controller.action.member.MyPageAction;
import com.team4.museum.controller.action.member.MyPageEditAction;
import com.team4.museum.controller.action.member.MyPageEditFormAction;
import com.team4.museum.controller.action.member.MyPageFavoriteAction;
import com.team4.museum.controller.action.notice.DeleteNoticeAction;
import com.team4.museum.controller.action.notice.InsertNoticeAction;
import com.team4.museum.controller.action.notice.InsertNoticeFormAction;
import com.team4.museum.controller.action.notice.NoticeListAction;
import com.team4.museum.controller.action.notice.NoticeViewAction;
import com.team4.museum.controller.action.notice.NoticeViewWithoutCntAction;
import com.team4.museum.controller.action.notice.UpdateNoticeAction;
import com.team4.museum.controller.action.notice.UpdateNoticeFormAction;
import com.team4.museum.controller.action.qna.QnaListAction;
import com.team4.museum.controller.action.qna.QnaPwdCheckAction;
import com.team4.museum.controller.action.qna.QnaReplyAction;
import com.team4.museum.controller.action.qna.QnaViewAction;
import com.team4.museum.controller.action.qna.QnaWriteAction;
import com.team4.museum.controller.action.qna.QnaWriteFormAction;

public class ActionFactory {

	private ActionFactory() {
	}

	private static final ActionFactory instance = new ActionFactory();

	public static ActionFactory getInstance() {
		return instance;
	}

	public Action getAction(String command) {
		return switch (command != null ? command : "") {

		// index action
		case "", "index" -> new IndexAction();

		// member actions
		case "loginForm" -> new LoginFormAction();
		case "login" -> new LoginAction();
		case "logout" -> new LogoutAction();
		case "join" -> new JoinAction();
		case "joinForm" -> new JoinFormAction();
		case "idcheckForm" -> new IdcheckFormAction();
		case "mypage" -> new MyPageAction();
		case "mypageEditMemberForm" -> new MyPageEditFormAction();
		case "mypageEditMember" -> new MyPageEditAction();
		case "mypageFavorite" -> new MyPageFavoriteAction();

		// artwork actions
		case "artworkList" -> new ArtworkListAction();
		case "artworkView" -> new ArtworkViewAction();
		case "artworkWrite" -> new ArtworkWriteAction();
		case "artworkWriteForm" -> new ArtworkWriteFormAction();
		case "artworkUpdate" -> new ArtworkUpdateAction();
		case "artworkUpdateForm" -> new ArtworkUpdateFormAction();
		case "artworkDelete" -> new ArtworkDeleteAction();
		case "artworkDisplaySet" -> new ArtworkDisplaySetAction();

		// QnA actions
		case "qnaList" -> new QnaListAction();
		case "qnaPwdCheck" -> new QnaPwdCheckAction();
		case "qnaView" -> new QnaViewAction();
		case "qnaReply" -> new QnaReplyAction();
		case "qnaWriteForm" -> new QnaWriteFormAction();
		case "qnaWrite" -> new QnaWriteAction();

		// user gallery
		case "galleryList" -> new GalleryListAction();
		case "galleryView" -> new GalleryViewAction();
		case "galleryWriteForm" -> new GalleryWriteFormAction();
		case "galleryWrite" -> new GalleryWriteAction();
		case "galleryUpdateForm" -> new GalleryUpdateFormAction();
		case "galleryUpdate" -> new GalleryUpdateAction();
		case "galleryDelete" -> new GalleryDeleteAction();

		// notice
		case "noticeList" -> new NoticeListAction();
		case "noticeView" -> new NoticeViewAction();
		case "insertNoticeForm" -> new InsertNoticeFormAction();
		case "insertNotice" -> new InsertNoticeAction();
		case "updateNoticeForm" -> new UpdateNoticeFormAction();
		case "updateNotice" -> new UpdateNoticeAction();
		case "noticeViewWithoutCnt" -> new NoticeViewWithoutCntAction();
		case "deleteNotice" -> new DeleteNoticeAction();

		// admin
		case "admin" -> new AdminAction();
		case "grantAdminRights" -> new GrantAdminRightsAction();
		case "adminMemberList" -> new AdminMemberListAction();
		case "adminArtworkList" -> new AdminArtworkListAction();
		case "adminNoticeList" -> new AdminNoticeListAction();
		case "adminGalleryList" -> new AdminGalleryListAction();
		case "adminQnaList" -> new AdminQnaListAction();
		case "adminDeleteMember" -> new AdminDeleteMemberAction();
		case "adminDeleteArtwork" -> new AdminDeleteArtworkAction();
		case "adminDeleteNotice" -> new AdminDeleteNoticeAction();
		case "adminDeleteGallery" -> new AdminDeleteGalleryAction();
		case "adminDeleteQna" -> new AdminDeleteQnaAction();

		// default
		default -> null;

		};
	}

}
