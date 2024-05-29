package com.team4.museum.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.team4.museum.vo.ReviewVO;
import static com.team4.museum.util.Db.*;

final public class ReviewDAO {

	private ReviewDAO() {
	}

	private static final ReviewDAO instance = new ReviewDAO();

	public static ReviewDAO getInstance() {
		return instance;
	}

	public List<ReviewVO> selectReviewList() {
		return executeSelect(
				"SELECT * FROM review",
				ReviewDAO::extractReviewVO);
	}

	public List<ReviewVO> selectReviewList(int aseq) {
		return executeSelect(
				"SELECT * FROM review WHERE aseq = ?",
				pstmt -> pstmt.setInt(1, aseq),
				ReviewDAO::extractReviewVO);
	}

	public ReviewVO selectReview(int rseq) {
		return executeSelectOne(
				"SELECT * FROM review WHERE rseq = ?",
				pstmt -> pstmt.setInt(1, rseq), ReviewDAO::extractReviewVO);
	}

	public int insertReview(ReviewVO review) {
		return executeUpdate(
				"INSERT INTO review (title, author, aseq, content) VALUES (?, ?, ?, ?)",
				pstmt -> {
					pstmt.setString(1, review.getTitle());
					pstmt.setString(2, review.getAuthor());
					pstmt.setInt(3, review.getAseq());
					pstmt.setString(4, review.getContent());
				});
	}

	public int updateReview(ReviewVO review) {
		return executeUpdate(
				"UPDATE review SET title = ?, author = ?, aseq = ?, content = ? WHERE rseq = ?",
				pstmt -> {
					pstmt.setString(1, review.getTitle());
					pstmt.setString(2, review.getAuthor());
					pstmt.setInt(3, review.getAseq());
					pstmt.setString(4, review.getContent());
					pstmt.setInt(5, review.getRseq());
				});
	}

	public int deleteReview(int rseq) {
		return executeUpdate(
				"DELETE FROM review WHERE rseq = ?",
				pstmt -> pstmt.setInt(1, rseq));
	}

	public int getReviewCount() {
		return executeSelectOne(
				"SELECT COUNT(*) FROM review",
				rs -> rs.getInt(1));
	}

	private static ReviewVO extractReviewVO(ResultSet rs) throws SQLException {
		ReviewVO review = new ReviewVO();
		review.setRseq(rs.getInt("rseq"));
		review.setTitle(rs.getString("title"));
		review.setAuthor(rs.getString("author"));
		review.setAseq(rs.getInt("aseq"));
		review.setWritedate(rs.getDate("writedate"));
		review.setContent(rs.getString("content"));
		return review;
	}

}
