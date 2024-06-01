package com.team4.museum.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import com.team4.museum.util.Db;
import com.team4.museum.util.Paging;
import com.team4.museum.vo.NoticeVO;


import static com.team4.museum.util.Db.*;


final public class NoticeDAO {

	private NoticeDAO() {
	}

	private static final NoticeDAO instance = new NoticeDAO();

	public static NoticeDAO getInstance() {
		return instance;
	}

	/* paging 파라미터 추가 */
	public List<NoticeVO> selectNoticeList() {
		return executeSelect(
				"SELECT * FROM notice",
				NoticeDAO::extractNoticeVO);
	}

	public List<NoticeVO> selectNoticeList(Paging paging) {
		return executeSelect(
				"SELECT * FROM notice LIMIT ? OFFSET ?",
				pstmt -> {
					pstmt.setInt(1, paging.getDisplayRow());
					pstmt.setInt(2, paging.getStartNum() - 1);
				},
				NoticeDAO::extractNoticeVO);
	}

	public NoticeVO selectNotice(int nseq) {
		return executeSelectOne(
				"SELECT * FROM notice WHERE nseq = ?",
				pstmt -> pstmt.setInt(1, nseq), NoticeDAO::extractNoticeVO);
	}
	
	   public List<NoticeVO> selectCategoryNotice(String category) {
	        return executeSelect("SELECT * FROM notice WHERE category = ?",
	                pstmt -> pstmt.setString(1, category),
	                NoticeDAO::extractNoticeVO
	        );
	    }

	public int insertNotice(NoticeVO notice) {
		return executeUpdate(
				"INSERT INTO notice (title, author, content, category) VALUES (?, ?, ?, ?)",
				pstmt -> {
					pstmt.setString(1, notice.getTitle());
					pstmt.setString(2, notice.getAuthor());
					pstmt.setString(3, notice.getContent());
					pstmt.setString(4, notice.getCategory());
				});
	}

	public int updateNotice(NoticeVO notice) {
		return executeUpdate(
				"UPDATE notice SET title = ?, author = ?, content = ?, category = ? WHERE nseq = ?",
				pstmt -> {
					pstmt.setString(1, notice.getTitle());
					pstmt.setString(2, notice.getAuthor());
					pstmt.setString(3, notice.getContent());
					pstmt.setString(4, notice.getCategory());
					pstmt.setInt(5, notice.getNseq());
				});
	}

	public int deleteNotice(int nseq) {
		return executeUpdate(
				"DELETE FROM notice WHERE nseq = ?",
				pstmt -> pstmt.setInt(1, nseq));
	}

	public int getNoticeCount() {
		return executeSelectOne(
				"SELECT COUNT(*) FROM notice",
				rs -> rs.getInt(1));
	}

	private static NoticeVO extractNoticeVO(ResultSet rs) throws SQLException {
		NoticeVO notice = new NoticeVO();
		notice.setNseq(rs.getInt("nseq"));
		notice.setTitle(rs.getString("title"));
		notice.setAuthor(rs.getString("author"));
		notice.setWritedate(rs.getDate("writedate"));
		notice.setContent(rs.getString("content"));
		notice.setReadcount(rs.getInt("readcount"));
		notice.setCategory(rs.getString("category"));
		return notice;
	}

	public int getNoticeAllCount() {
		return executeSelectOne(
				"SELECT COUNT(*) AS cnt FROM notice",
				rs -> rs.getInt("cnt"));
	}

	public int getReplyCount(Object num) {
		int count = 0;
		Db.getConnection();
		String sql =  "select count(*) as cnt from reply where noticeNseq=?";
		return count;
	}

	public void plusReadCount(int nseq) {
		Db.getConnection();
		String sql = "update notice set readcount=readcount+1 where nseq=?";
	}


}
