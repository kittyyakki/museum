package com.team4.museum.dao;

import static com.team4.museum.util.Db.executeCall;
import static com.team4.museum.util.Db.executeSelect;
import static com.team4.museum.util.Db.executeSelectOne;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import com.team4.museum.util.Pagination;
import com.team4.museum.vo.FavoriteVO;

public class FavoriteDao {

	private FavoriteDao() {
	}

	private static final FavoriteDao instance = new FavoriteDao();

	public static FavoriteDao getInstance() {
		return instance;
	}

	public List<FavoriteVO> selectFavorite(String memberId, Pagination pagination) {
		return executeSelect(
				"SELECT * FROM favorite_view WHERE member_id = ? ORDER BY aseq DESC LIMIT ? OFFSET ?",
				pstmt -> {
					pstmt.setString(1, memberId);
					pstmt.setInt(2, pagination.getLimit());
					pstmt.setInt(3, pagination.getOffset());
				},
				FavoriteDao::extractFavoriteVO);
	}

	public Boolean toggleFavorite(String memberId, int aseq) {
		return "added".equals(executeCall(
				"{CALL toggle_favorite_artwork(?, ?, ?)}",
				pstmt -> {
					pstmt.setString(1, memberId);
					pstmt.setInt(2, aseq);
				},
				3,
				Types.VARCHAR));
	}

	public int getCount(String memberId) {
		return executeSelectOne(
				"SELECT COUNT(*) as cnt FROM favorite_view WHERE member_id = ?",
				rs -> rs.getInt("cnt"));
	}

	private static FavoriteVO extractFavoriteVO(ResultSet rs) throws SQLException {
		FavoriteVO fvo = FavoriteVO.fromArtwork(ArtworkDao.extractArtworkVO(rs));
		fvo.setMemberId(rs.getString("member_id"));
		return fvo;
	}
}