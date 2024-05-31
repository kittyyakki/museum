package com.team4.museum.dao;

import static com.team4.museum.util.Db.executeSelect;
import static com.team4.museum.util.Db.executeSelectOne;
import static com.team4.museum.util.Db.executeUpdate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.team4.museum.dao.QnaDao;

import com.team4.museum.vo.QnaVO;

import com.team4.museum.util.Paging;

public class QnaDao {

	private QnaDao() {
	}

	private static final QnaDao instance = new QnaDao();

	public static QnaDao getInstance() {
		return instance;
	}

	public List<QnaVO> selectQna(Paging paging) {
		return executeSelect(
				"SELECT * FROM qna ORDER BY qseq DESC LIMIT ? OFFSET ?",
				pstmt -> {
					pstmt.setInt(1, paging.getDisplayRow());
					pstmt.setInt(2, paging.getStartNum() - 1);
				},
				QnaDao::extractQnaVO);
	}

	public QnaVO getQna(int qseq) {
		return executeSelectOne(
				"SELECT * FROM qna WHERE qseq = ?",
				pstmt -> pstmt.setInt(1, qseq),
				QnaDao::extractQnaVO);
	}

	public void insertQna(QnaVO qvo) {
		executeUpdate(
				"INSERT INTO qna (qseq, title, content, email, pwd, phone, reply, publicyn) "
						+ "VALUES (?, ?, ?, ?, ? ,?)",
				pstmt -> {
					pstmt.setInt(1, qvo.getQseq());
					pstmt.setString(2, qvo.getTitle());
					pstmt.setString(3, qvo.getContent());
					pstmt.setString(4, qvo.getEmail());
					pstmt.setString(5, qvo.getPwd());
					pstmt.setString(6, qvo.getPhone());
					pstmt.setString(7, qvo.getReply());
					pstmt.setString(8, qvo.getPublicyn().toString());
				});
	}

	public void updateQna(QnaVO qvo) {
		executeUpdate(
				"UPDATE qna SET title = ?, content = ?, email = ?, pwd = ?, phone = ?, reply = ?, publicyn = ?"
						+ "WHERE qseq = ?",
				pstmt -> {
					pstmt.setString(1, qvo.getTitle());
					pstmt.setString(2, qvo.getContent());
					pstmt.setString(3, qvo.getEmail());
					pstmt.setString(4, qvo.getPwd());
					pstmt.setString(5, qvo.getPhone());
					pstmt.setString(6, qvo.getReply());
					pstmt.setInt(7, qvo.getQseq());
					pstmt.setString(8, qvo.getPublicyn());
				});
	}

	public void updateQnaReply(int qseq, String reply) {
		executeUpdate(
				"UPDATE qna SET reply = ? WHERE qseq = ?",
				pstmt -> {
					pstmt.setString(1, reply);
					pstmt.setInt(2, qseq);
				});
	}

	public int getAllCount() {
		return executeSelectOne(
				"SELECT COUNT(*) AS cnt FROM qna",
				rs -> rs.getInt("cnt"));
	}

	private static QnaVO extractQnaVO(ResultSet rs) throws SQLException {
		QnaVO qvo = new QnaVO();
		qvo.setQseq(rs.getInt("qseq"));
		qvo.setTitle(rs.getString("title"));
		qvo.setContent(rs.getString("content"));
		qvo.setEmail(rs.getString("email"));
		qvo.setPwd(rs.getString("pwd"));
		qvo.setPhone(rs.getString("phone"));
		qvo.setReply(rs.getString("reply"));
		qvo.setWritedate(rs.getDate("writedate"));
		qvo.setPublicyn(rs.getString("publicyn"));
		return qvo;
	}
}
