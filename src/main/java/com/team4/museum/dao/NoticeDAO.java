package com.team4.museum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

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

	/*
	 * public NoticeVO selectNotice(int nseq) { return executeSelectOne(
	 * "SELECT * FROM notice WHERE nseq = ?", pstmt -> pstmt.setInt(1, nseq),
	 * NoticeDAO::extractNoticeVO); }
	 */

	public List<NoticeVO> selectCategoryNotice(String category) {
		return executeSelect("SELECT * FROM notice WHERE category = ?",
				pstmt -> pstmt.setString(1, category),
				NoticeDAO::extractNoticeVO);
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

	public int getReplyCount(int nseq) {
		int count = 0;
		con = Db.getConnection();
		String sql = "select count(*) as cnt from reply where nseq=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, nseq);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.close(con, pstmt, rs);
		}
		return count;
	}

	public void plusReadCount(int nseq) {
		con = Db.getConnection();
		String sql = "update notice set readcount=readcount+1 where nseq=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, nseq);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.close(con, pstmt, rs);
		}
	}

	public NoticeVO getNotice(int nseq) {
		/* ArrayList<NoticeVO> list = new ArrayList<NoticeVO>(); */
		NoticeVO nvo = null;
		// NoticeVO nvo = new NoticeVO();
		con = Db.getConnection();
		String sql = "select * from notice where nseq=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, nseq);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				nvo = new NoticeVO();
				nvo.setNseq(rs.getInt("nseq"));
				nvo.setTitle(rs.getString("title"));
				nvo.setAuthor(rs.getString("author"));
				nvo.setWritedate(rs.getDate("writedate"));
				nvo.setContent(rs.getString("content"));
				nvo.setReadcount(rs.getInt("readcount"));
				nvo.setCategory(rs.getString("category"));
				/*
				 * nvo.setImage( rs.getString("image") ); nvo.setSavefilename(
				 * rs.getString("savefilename"));
				 */
				// System.out.println(nvo.getTitle());

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.close(con, pstmt, rs);
		}
		return nvo;
	}

	public ArrayList<NoticeVO> getAllnoitce(Paging paging) {
		ArrayList<NoticeVO> list = new ArrayList<NoticeVO>();
		con = Db.getConnection();
		// String sql = "select * from board order by num desc";
		String sql = "select * from notice order by nseq desc limit ? offset ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, paging.getDisplayRow());
			pstmt.setInt(2, paging.getStartNum() - 1);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				NoticeVO nvo = new NoticeVO();
				nvo.setNseq(rs.getInt("nseq"));
				nvo.setTitle(rs.getString("title"));
				nvo.setAuthor(rs.getString("author"));
				nvo.setWritedate(rs.getDate("writedate"));
				nvo.setContent(rs.getString("content"));
				nvo.setReadcount(rs.getInt("readcount"));
				nvo.setCategory(rs.getString("category"));
				// System.out.println("list DAO OK : " + list);
				// System.out.println(nvo.getTitle());
				/*
				 * nvo.setImage( rs.getString("image") ); nvo.setSavefilename(
				 * rs.getString("savefilename"));
				 */

				list.add(nvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.close(con, pstmt, rs);
		}
		return list;
	}

}
