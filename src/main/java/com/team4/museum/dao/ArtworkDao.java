package com.team4.museum.dao;

import static com.team4.museum.util.Db.executeSelect;
import static com.team4.museum.util.Db.executeSelectOne;
import static com.team4.museum.util.Db.executeUpdate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

	/** 기본적인 전체조회 */
	public List<ArtworkVO> selectArtworkAdmin() {
		return executeSelect("SELECT * FROM artwork", ArtworkDao::extractArtworkVO);
	}

	/** 관리자용 검색조회(admin 페이지, 예술품 페이지의 관리자 로그인) */
	// 어차피 관리자는 admin 페이지에서 displayyn이 N인 데이터의 조회가 가능한데
	// 굳이 artworkList에서 관리자용 로직(loginUser가 관리자일 때 Y,N 모두 표시)을 따로 처리해야 할까
	public List<ArtworkVO> searchArtworkAdmin(String searchWord) {
		return executeSelect(
				"SELECT * FROM artwork WHERE name LIKE CONCAT('%', ?, '%') OR artist LIKE CONCAT('%', ?, '%')",
				pstmt -> {
					pstmt.setString(1, searchWord);
					pstmt.setString(2, searchWord);
				},
				ArtworkDao::extractArtworkVO);
	}

	/** 위 검색조회와 같지만 페이징 처리가 가능 -> 모두 페이징 처리를 하고 위 코드를 삭제하는게 맞음 */
	public List<ArtworkVO> searchArtworkAdmin(Pagination pagination, String searchWord) {
		return executeSelect(
				"SELECT * FROM artwork "
						+ " WHERE name LIKE CONCAT('%', ?, '%') OR artist LIKE CONCAT('%', ?, '%') "
						+ " ORDER BY aseq DESC LIMIT ? OFFSET ?",
				pstmt -> {
					pstmt.setString(1, searchWord);
					pstmt.setString(2, searchWord);
					pagination.applyTo(pstmt, 3, 4);
				},
				ArtworkDao::extractArtworkVO);
	}

	/** 카테고리를 기준으로 전체 조회 */
	public List<ArtworkVO> selectCategoryArtworkAdmin(String category) {
		return executeSelect(
				"SELECT * FROM artwork WHERE category=?",
				pstmt -> pstmt.setString(1, category),
				ArtworkDao::extractArtworkVO);
	}

	/** 관리자용 예술품 삭제 */
	public void deleteArtwork(Integer aseq) {
		executeUpdate("DELETE FROM artwork WHERE aseq = ?", pstmt -> pstmt.setInt(1, aseq));

	}

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

	public int displayChangeArtwork(int aseq) {
		int result = 0;
		ArtworkVO avo = executeSelectOne("SELECT * FROM artwork WHERE aseq=" + aseq, ArtworkDao::extractArtworkVO);
		if (avo.getDisplayyn().equals("Y")) {
			result = executeUpdate("UPDATE artwork SET displayyn='N' WHERE aseq=" + aseq);
		} else {
			result = executeUpdate("UPDATE artwork SET displayyn='Y' WHERE aseq=" + aseq);
		}
		return result;
	}

	public List<ArtworkVO> selectArtworkAsDisplayyn(Pagination pagination, String displayState) {
		return executeSelect(
				"SELECT * FROM artwork WHERE displayyn=? ORDER BY aseq DESC LIMIT ? OFFSET ?",
				pstmt -> {
					pstmt.setString(1, displayState);
					pagination.applyTo(pstmt, 2, 3);
				},
				ArtworkDao::extractArtworkVO);
	}
	/* <========================================================================= */

	public List<ArtworkVO> searchArtwork(String searchWord) {
		return executeSelect(
				"SELECT * FROM artwork WHERE (name LIKE CONCAT('%', ?, '%') OR artist LIKE CONCAT('%', ?, '%')) AND displayyn='Y'",
				pstmt -> {
					pstmt.setString(1, searchWord);
					pstmt.setString(2, searchWord);
				}, ArtworkDao::extractArtworkVO);
	}

	public List<ArtworkVO> selectCategoryArtwork(String category) {
		return executeSelect(
				"SELECT * FROM artwork WHERE category=? AND displayyn='Y'",
				pstmt -> pstmt.setString(1, category),
				ArtworkDao::extractArtworkVO);
	}

	public List<ArtworkVO> selectArtwork() {
		return executeSelect("SELECT * FROM artwork WHERE displayyn='Y'", ArtworkDao::extractArtworkVO);
	}

	public ArtworkVO selectArtworkOne(int aseq) {
		return executeSelectOne(
				"SELECT * FROM artwork WHERE aseq=?",
				pstmt -> pstmt.setInt(1, aseq),
				ArtworkDao::extractArtworkVO);
	}

	public List<ArtworkVO> selectArtwork(Pagination pagination) {
		return executeSelect(
				"SELECT * FROM artwork ORDER BY aseq DESC LIMIT ? OFFSET ?",
				pagination::applyTo,
				ArtworkDao::extractArtworkVO);
	}

	public int getAllCount() {
		return executeSelectOne(
				"SELECT COUNT(*) as cnt FROM artwork",
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