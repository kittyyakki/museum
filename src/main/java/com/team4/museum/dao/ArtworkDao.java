package com.team4.museum.dao;

import static com.team4.museum.util.Db.executeSelect;
import static com.team4.museum.util.Db.executeSelectOne;
import static com.team4.museum.util.Db.executeUpdate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.team4.museum.util.Pagination;
import com.team4.museum.vo.ArtworkVO;

public class ArtworkDao {

	private ArtworkDao() {
	}

	private static final ArtworkDao instance = new ArtworkDao();

	public static ArtworkDao getInstance() {
		return instance;
	}

	/* 관리자용 로직 ============================================> */
	/** <관리자용> 예술품 등록 */
	public int insertArtwork(ArtworkVO artwork) {
		return executeUpdate(
				"INSERT INTO artwork (name, category, artist, year, material, size, displayyn, content, image, savefilename) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
				pstmt -> {
					pstmt.setString(1, artwork.getName());
					pstmt.setString(2, artwork.getCategory());
					pstmt.setString(3, artwork.getArtist());
					pstmt.setString(4, artwork.getYear());
					pstmt.setString(5, artwork.getMaterial());
					pstmt.setString(6, artwork.getSize());
					pstmt.setString(7, artwork.getDisplayyn());
					pstmt.setString(8, artwork.getContent());
					pstmt.setString(9, artwork.getImage());
					pstmt.setString(10, artwork.getSavefilename());
				});
	}

	/** 관리자 페이지 검색 조회 */
	public List<ArtworkVO> searchArtworkAdmin(Pagination pagination, String searchWord) {
		return executeSelect(
				"SELECT * FROM artwork " + " WHERE (name LIKE CONCAT('%', ?, '%') OR artist LIKE CONCAT('%', ?, '%')) "
						+ " ORDER BY aseq DESC LIMIT ? OFFSET ?",
				pstmt -> {
					pstmt.setString(1, searchWord);
					pstmt.setString(2, searchWord);
					pagination.applyTo(pstmt, 3, 4);
				}, ArtworkDao::extractArtworkVO);
	}

	/** <관리자용> 예술품 삭제 */
	public void deleteArtwork(Integer aseq) {
		executeUpdate("DELETE FROM artwork WHERE aseq = ?", pstmt -> pstmt.setInt(1, aseq));
	}

	/** <관리자용> 예술품 수정 */
	public int updateArtwork(ArtworkVO avo) {
		return executeUpdate(
				"UPDATE artwork SET artist=?, name=?, year=?, material=?, size=?, category=?, displayyn=?, content=?, image=?, savefilename=? "
						+ " WHERE aseq=?",
				pstmt -> {
					pstmt.setString(1, avo.getArtist());
					pstmt.setString(2, avo.getName());
					pstmt.setString(3, avo.getYear());
					pstmt.setString(4, avo.getMaterial());
					pstmt.setString(5, avo.getSize());
					pstmt.setString(6, avo.getCategory());
					pstmt.setString(7, avo.getDisplayyn());
					pstmt.setString(8, avo.getContent());
					pstmt.setString(9, avo.getImage());
					pstmt.setString(10, avo.getSavefilename());
					pstmt.setInt(11, avo.getAseq());
				});
	}

	/** <관리자용> 예술품 공개여부 전환 */
	public int displayChangeArtwork(int aseq) {
		int result = 0;
		ArtworkVO avo = executeSelectOne("SELECT * FROM artwork WHERE aseq=" + aseq, ArtworkDao::extractArtworkVO);
		if (avo.isDisplay()) {
			result = executeUpdate("UPDATE artwork SET displayyn='N' WHERE aseq=" + aseq);
		} else {
			result = executeUpdate("UPDATE artwork SET displayyn='Y' WHERE aseq=" + aseq);
		}
		return result;
	}

	/** 관리자 페이지의 예술품 목록을 공개여부 기준으로 필터링 */
	public List<ArtworkVO> selectArtworkAsDisplayyn(Pagination pagination, String displayState) {
		return executeSelect("SELECT * FROM artwork WHERE displayyn=? ORDER BY aseq DESC LIMIT ? OFFSET ?", pstmt -> {
			pstmt.setString(1, displayState);
			pagination.applyTo(pstmt, 2, 3);
		}, ArtworkDao::extractArtworkVO);
	}
	/* <========================================================================= */
	public List<ArtworkVO> searchPublicArtwork(String searchWord, Pagination pagination) {
		return executeSelect("SELECT * FROM artwork "
				+ " WHERE (name LIKE CONCAT('%', ?, '%') OR artist LIKE CONCAT('%', ?, '%')) AND displayyn='Y' "
				+ "ORDER BY aseq DESC LIMIT ? OFFSET ?", pstmt -> {
					pstmt.setString(1, searchWord);
					pstmt.setString(2, searchWord);
					pagination.applyTo(pstmt, 3, 4);
				}, ArtworkDao::extractArtworkVO);
	}

	public List<ArtworkVO> selectPublicArtworkByCategory(String category, Pagination pagination) {
		return executeSelect(
				"SELECT * FROM artwork WHERE category=? AND displayyn='Y' " + "ORDER BY aseq DESC LIMIT ? OFFSET ?",
				pstmt -> {
					pstmt.setString(1, category);
					pagination.applyTo(pstmt, 2, 3);
				}, ArtworkDao::extractArtworkVO);
	}

	public List<ArtworkVO> selectPublicArtwork(Pagination pagination) {
		return executeSelect("SELECT * FROM artwork WHERE displayyn='Y' " + "ORDER BY aseq DESC LIMIT ? OFFSET ?",
				pagination::applyTo, ArtworkDao::extractArtworkVO);
	}

	public ArtworkVO selectArtworkOne(int aseq) {
		return executeSelectOne("SELECT * FROM artwork WHERE aseq=?", pstmt -> pstmt.setInt(1, aseq),
				ArtworkDao::extractArtworkVO);
	}

	public List<ArtworkVO> selectArtwork(Pagination pagination) {
		return executeSelect("SELECT * FROM artwork ORDER BY aseq DESC LIMIT ? OFFSET ?", pagination::applyTo,
				ArtworkDao::extractArtworkVO);
	}

	/* 페이징처리를 위한 카운트 메서드들 */
	public int getAllCount() {
		return executeSelectOne("SELECT COUNT(*) as cnt FROM artwork", rs -> rs.getInt("cnt"));
	}
	
	public int getDisplayCount() {
		return executeSelectOne("SELECT COUNT(*) as cnt FROM artwork WHERE displayyn='Y'", rs -> rs.getInt("cnt"));
	}
	
	public int getSearchCount(String searchWord) {
		return executeSelectOne("SELECT COUNT(*) as cnt FROM artwork "
				+ "WHERE (name LIKE CONCAT('%', ?, '%') OR artist LIKE CONCAT('%', ?, '%')) "
				+ "AND displayyn='Y'",
				pstmt->{
					pstmt.setString(1, searchWord);
					pstmt.setString(2, searchWord);
				},rs -> rs.getInt("cnt"));
	}
	
	public int getCategoryCount(String category) {
		return executeSelectOne("SELECT COUNT(*) as cnt FROM artwork WHERE category=? AND displayyn='Y'",
				pstmt->	pstmt.setString(1, category),
				rs -> rs.getInt("cnt"));
	}

	public static ArtworkVO extractArtworkVO(ResultSet rs) throws SQLException {
		ArtworkVO avo = new ArtworkVO();
		avo.setAseq(rs.getInt("aseq"));
		avo.setName(rs.getString("name"));
		avo.setCategory(rs.getString("category"));
		avo.setArtist(rs.getString("artist"));
		avo.setYear(rs.getString("year"));
		avo.setMaterial(rs.getString("material"));
		avo.setSize(rs.getString("size"));
		avo.setDisplayyn(rs.getString("displayyn"));
		avo.setContent(rs.getString("content"));
		avo.setImage(rs.getString("image"));
		avo.setSavefilename(rs.getString("savefilename"));
		avo.setIndate(rs.getDate("indate"));
		return avo;
	}
}