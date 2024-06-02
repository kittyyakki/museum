package com.team4.museum.dao;

import static com.team4.museum.util.Db.executeSelect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.team4.museum.util.Paging;
import com.team4.museum.util.Db;
import com.team4.museum.vo.ArtworkVO;
import com.team4.museum.vo.MemberGalleryVO;

public class MemberGalleryDao {
	
	private MemberGalleryDao() {
	}

	private static MemberGalleryDao mgdao = new MemberGalleryDao();

	public static MemberGalleryDao getInstance() {
		return mgdao;
	}

	public MemberGalleryVO getMemberGalleryOne(int mseq) {
		return Db.executeSelectOne("select * from member_gallery_view where mseq=?",
				pstmt -> pstmt.setInt(1, mseq),
				rs -> {
					MemberGalleryVO mgvo = new MemberGalleryVO();
					mgvo.setMseq(mseq);
					mgvo.setAuthorId(rs.getString("author_id"));
					mgvo.setAuthorName(rs.getString("author_name"));
					mgvo.setTitle(rs.getString("title"));
					mgvo.setWritedate(rs.getDate("writedate"));
					mgvo.setContent(rs.getString("content"));
					mgvo.setReadcount(rs.getInt("readcount"));
					mgvo.setImage(rs.getString("image"));
					mgvo.setSavefilename(rs.getString("savefilename"));
					mgvo.setLikecount(rs.getInt("likecount"));
					return mgvo;
				});
	}

	public int insertMemberGallery(MemberGalleryVO mgvo) {
		return Db.executeUpdate(
				"insert into member_gallery(author, title, content, image, savefilename) values(?, ?, ?, ?, ?)",
				pstmt -> {
					pstmt.setString(1, mgvo.getAuthorId());
					pstmt.setString(2, mgvo.getTitle());
					pstmt.setString(3, mgvo.getContent());
					pstmt.setString(4, mgvo.getImage());
					pstmt.setString(5, mgvo.getSavefilename());
				});
	}

	public void updateMemberGallery(MemberGalleryVO mgvo) {
		Db.executeUpdate("update member_gallery set title=?, content=?, image=?, savefilename=? where mseq=?",
				pstmt -> {
					pstmt.setString(1, mgvo.getTitle());
					pstmt.setString(2, mgvo.getContent());
					pstmt.setString(3, mgvo.getImage());
					pstmt.setString(4, mgvo.getSavefilename());
					pstmt.setInt(5, mgvo.getMseq());
				});
	}
	
	public void deleteMemberGallery(int mseq) {
		Db.executeUpdate("delete from member_gallery where mseq=?",
				pstmt -> pstmt.setInt(1, mseq));
	}

	
	
	
	
}