package com.team4.museum.dao;

import static com.team4.museum.util.Db.executeSelect;
import static com.team4.museum.util.Db.executeSelectOne;
import static com.team4.museum.util.Db.executeUpdate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.team4.museum.util.Pagination;
import com.team4.museum.vo.QnaVO;

public class QnaDao {

	private QnaDao() {
	}

	private static final QnaDao instance = new QnaDao();

	public static QnaDao getInstance() {
		return instance;
	}
	
	public List<QnaVO> selectQna() {
		return executeSelect(
				"SELECT * FROM qna",
				QnaDao::extractQnaVO);
	}

	public List<QnaVO> selectQna(Pagination pagination) {
		return executeSelect(
				"SELECT * FROM qna ORDER BY qseq DESC LIMIT ? OFFSET ?",
				pstmt -> {
					pstmt.setInt(1, pagination.getLimit());
					pstmt.setInt(2, pagination.getOffset());
				},
				QnaDao::extractQnaVO);
	}
	
	public List<QnaVO> selectQna(Pagination pagination, String isReply) {

		if(isReply.equals("Y")) {
			return executeSelect(
					"SELECT * FROM qna WHERE reply IS NOT NULL ORDER BY qseq DESC LIMIT ? OFFSET ?",
					pstmt -> {
						pstmt.setInt(1, pagination.getLimit());
						pstmt.setInt(2, pagination.getOffset());
					},
					QnaDao::extractQnaVO);			
		}else {
			return executeSelect(
					"SELECT * FROM qna WHERE reply IS NULL ORDER BY qseq DESC LIMIT ? OFFSET ?",
					pstmt -> {
						pstmt.setInt(1, pagination.getLimit());
						pstmt.setInt(2, pagination.getOffset());
					},
					QnaDao::extractQnaVO);
		}
	}

	public QnaVO getQna(int qseq) {
		return executeSelectOne(
				"SELECT * FROM qna WHERE qseq = ?",
				pstmt -> pstmt.setInt(1, qseq),
				QnaDao::extractQnaVO);
	}

	public int insertQna(QnaVO qvo) {
		executeUpdate(
				"INSERT INTO qna (title, content, email, phone, publicyn, pwd) VALUES (?, ?, ?, ?, ? ,?)",
				pstmt -> {
					pstmt.setString(1, qvo.getTitle());
					pstmt.setString(2, qvo.getContent());
					pstmt.setString(3, qvo.getEmail());
					pstmt.setString(4, qvo.getPhone());
					pstmt.setString(5, qvo.getPublicyn());
					pstmt.setString(6, qvo.getPwd());
				});

		return executeSelectOne("SELECT LAST_INSERT_ID() AS qseq", rs -> rs.getInt("qseq"));
	}

	public int updateQna(QnaVO qvo) {
		executeUpdate(
				"UPDATE qna SET title = ?, content = ?, email = ?, phone = ?, publicyn = ?, pwd = ? WHERE qseq = ?",
				pstmt -> {
					pstmt.setString(1, qvo.getTitle());
					pstmt.setString(2, qvo.getContent());
					pstmt.setString(3, qvo.getEmail());
					pstmt.setString(4, qvo.getPhone());
					pstmt.setString(5, qvo.getPublicyn());
					pstmt.setString(6, qvo.getPwd());
					pstmt.setInt(7, qvo.getQseq());
				});

		return qvo.getQseq();
	}

	public void deleteQna(int qseq) {
		executeUpdate(
				"DELETE FROM qna WHERE qseq = ?",
				pstmt -> pstmt.setInt(1, qseq));
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
