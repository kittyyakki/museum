package com.team4.museum.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.team4.museum.util.Pagination;
import com.team4.museum.util.Paging;
import com.team4.museum.vo.ArtworkVO;
import com.team4.museum.vo.QnaVO;

import static com.team4.museum.util.Db.*;

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
				"INSERT INTO artwork (name, category, artist, year, material, size, displayyn, content, image, savefilename) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
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

	public List<ArtworkVO> selectArtworkAdmin() {
		return executeSelect("SELECT * FROM artwork", ArtworkDao::extractArtworkVO);
	}

	public List<ArtworkVO> searchArtworkAdmin(String searchWord) {
		return executeSelect(
				"SELECT * FROM artwork WHERE name LIKE CONCAT('%', ?, '%') OR artist LIKE CONCAT('%', ?, '%')",
				pstmt -> {
					pstmt.setString(1, searchWord);
					pstmt.setString(2, searchWord);
				}, ArtworkDao::extractArtworkVO);
	}
	
	public List<ArtworkVO> searchArtworkAdmin(Pagination pagination, String searchWord) {
		return executeSelect(
				"SELECT * FROM artwork "
						+ " WHERE name LIKE CONCAT('%', ?, '%') OR artist LIKE CONCAT('%', ?, '%') "
						+ " ORDER BY aseq DESC LIMIT ? OFFSET ?",
				pstmt -> {
					pstmt.setString(1, searchWord);
					pstmt.setString(2, searchWord);
					pstmt.setInt(3, pagination.getLimit());
					pstmt.setInt(4, pagination.getOffset());
				}, ArtworkDao::extractArtworkVO);
	}

	public List<ArtworkVO> selectCategoryArtworkAdmin(String category) {
		return executeSelect("SELECT * FROM artwork WHERE category=?", pstmt -> pstmt.setString(1, category),
				ArtworkDao::extractArtworkVO);
	}

	public void deleteArtwork(Integer aseq) {
		executeUpdate("DELETE FROM artwork WHERE aseq = ?", pstmt -> pstmt.setInt(1, aseq));

	}
	
	public int updateArtwork(ArtworkVO avo) {
		return executeUpdate(
				"UPDATE artwork SET artist=?, name=?, year=?, material=?, size=?, category=?, displayyn=?, content=?, image=?, savefilename=? "
				+ " WHERE aseq=?",
				pstmt ->{
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
		if(avo.getDisplayyn().equals("Y")) {
			result = executeUpdate("UPDATE artwork SET displayyn='N' WHERE aseq=" + aseq);
		}else {
			result = executeUpdate("UPDATE artwork SET displayyn='Y' WHERE aseq=" + aseq);			
		}
		return result;
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
		return executeSelect("SELECT * FROM artwork WHERE category=? AND displayyn='Y'",
				pstmt -> pstmt.setString(1, category), ArtworkDao::extractArtworkVO);
	}

	public List<ArtworkVO> selectArtwork() {
		return executeSelect("SELECT * FROM artwork WHERE displayyn='Y'", ArtworkDao::extractArtworkVO);
	}

	public ArtworkVO selectArtworkOne(int aseq) {
		return executeSelectOne("SELECT * FROM artwork WHERE aseq=?", pstmt -> {
			pstmt.setInt(1, aseq);
		}, ArtworkDao::extractArtworkVO);
	}

	private static ArtworkVO extractArtworkVO(ResultSet rs) throws SQLException {

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

	public List<ArtworkVO> selectArtwork(Pagination pagination) {
			return executeSelect(
					"SELECT * FROM artwork ORDER BY aseq DESC LIMIT ? OFFSET ?",
					pstmt -> {
						pstmt.setInt(1, pagination.getLimit());
						pstmt.setInt(2, pagination.getOffset());
					},
					ArtworkDao::extractArtworkVO);
	}

	public int getAllCount() {
		return executeSelectOne("SELECT COUNT(*) as cnt FROM artwork", 
				rs -> rs.getInt("cnt"));
	}
}