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

	/**
	 * 문의글 목록을 조회한다.
	 * 
	 * @param pagination 페이지 정보
	 * @return 문의글 목록
	 */
	public List<QnaVO> selectQna(Pagination pagination) {
		return executeSelect(
				"SELECT * FROM qna ORDER BY qseq DESC LIMIT ? OFFSET ?",
				pagination::applyTo,
				QnaDao::extractQnaVO);
	}

	/**
	 * 답변 존재 여부에 따른 문의글 목록을 조회한다.
	 * 
	 * @param pagination 페이지 정보
	 * @param isReply    답변이 있는지 여부 (Y: 답변 있음, N: 답변 없음)
	 * @return 문의글 목록
	 */
	public List<QnaVO> selectQna(Pagination pagination, String isReply) {
		String query;
		if (isReply.equals("Y")) {
			query = "SELECT * FROM qna WHERE COALESCE (reply, '') <> '' ORDER BY qseq DESC LIMIT ? OFFSET ?";
		} else {
			query = "SELECT * FROM qna WHERE COALESCE (reply, '') = '' ORDER BY qseq DESC LIMIT ? OFFSET ?";
		}
		return executeSelect(query, pagination::applyTo, QnaDao::extractQnaVO);
	}

	/**
	 * 문의글을 조회한다.
	 * 
	 * @param qseq 문의글 번호 (qna sequence)
	 * @return 문의글 정보
	 */
	public QnaVO getQna(int qseq) {
		return executeSelectOne(
				"SELECT * FROM qna WHERE qseq = ?",
				pstmt -> pstmt.setInt(1, qseq),
				QnaDao::extractQnaVO);
	}

	/**
	 * 문의글을 등록한다.
	 * 
	 * @param qvo 문의글 정보 (qna sequence)
	 * @return 등록된 문의글 번호
	 */
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

		return executeSelectOne(
				"SELECT LAST_INSERT_ID() AS qseq",
				rs -> rs.getInt("qseq"));
	}

	/**
	 * 문의글을 수정한다.
	 * 
	 * @param qvo 문의글 정보 (qna sequence)
	 * @return 수정된 문의글 번호
	 */
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

	/**
	 * 문의글을 삭제한다.
	 * 
	 * @param qseq 문의글 번호 (qna sequence)
	 */
	public void deleteQna(int qseq) {
		executeUpdate(
				"DELETE FROM qna WHERE qseq = ?",
				pstmt -> pstmt.setInt(1, qseq));
	}

	/**
	 * 문의글에 답변을 등록한다.
	 * 
	 * @param qseq  문의글 번호 (qna sequence)
	 * @param reply 답변 내용
	 */
	public void updateQnaReply(int qseq, String reply) {
		executeUpdate(
				"UPDATE qna SET reply = ? WHERE qseq = ?",
				pstmt -> {
					pstmt.setString(1, reply);
					pstmt.setInt(2, qseq);
				});
	}

	/**
	 * 문의글 총 개수를 조회한다.
	 * 
	 * @return 문의글 총 개수
	 */
	public int getAllCount() {
		return executeSelectOne("SELECT COUNT(*) FROM qna", rs -> rs.getInt(1));
	}

	/**
	 * 문의글 검색 결과를 조회한다.
	 * 
	 * @param pagination 페이지 정보
	 * @param searchWord 검색어
	 * @return 문의글 목록
	 */
	public Object searchQna(Pagination pagination, String searchWord) {
		return executeSelect(
				"SELECT * FROM qna "
						+ " WHERE title LIKE CONCAT('%', ?, '%') OR content LIKE CONCAT('%', ?, '%') "
						+ " ORDER BY qseq DESC LIMIT ? OFFSET ?",
				pstmt -> {
					pstmt.setString(1, searchWord);
					pstmt.setString(2, searchWord);
					pagination.applyTo(pstmt, 3, 4);
				},
				QnaDao::extractQnaVO);
	}

	/**
	 * 문의글 정보를 ResultSet에서 추출한다.
	 * 
	 * @param rs ResultSet 객체
	 * @return QnaVO 문의글 정보 객체
	 */
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
