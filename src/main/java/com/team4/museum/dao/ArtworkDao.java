package com.team4.museum.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.team4.museum.vo.ArtworkVO;
import static com.team4.museum.util.Db.*;

public class ArtworkDao {

	private ArtworkDao() {
	}

	private static final ArtworkDao instance = new ArtworkDao();

	public static ArtworkDao getInstance() {
		return instance;
	}

	public int insertArtwork(ArtworkVO artwork) {
		return executeUpdate(
				"INSERT INTO notice (name, kind, artist, year, material, size, display, content, image, savefilename) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
				pstmt -> {
					pstmt.setString(1, artwork.getName());
					pstmt.setString(2, artwork.getKind());
					pstmt.setString(3, artwork.getArtist());
					pstmt.setString(4, artwork.getYear());
					pstmt.setString(5, artwork.getMaterial());
					pstmt.setString(6, artwork.getSize());
					pstmt.setString(7, artwork.getDisplay());
					pstmt.setString(8, artwork.getContent());
					pstmt.setString(9, artwork.getImage());
					pstmt.setString(10, artwork.getSavefilename());
				});

	}

	public List<ArtworkVO> selectSearchArtwork(String searchWord) {
		return executeSelect(
				"SELECT * FROM artwork WHERE name LIKE CONCAT('%', ?, '%') OR artist LIKE CONCAT('%', ?, '%')",
				pstmt -> {
					pstmt.setString(1, searchWord);
					pstmt.setString(2, searchWord);
				},
				ArtworkDao::extractArtworkVO);
	}

	public List<ArtworkVO> selectCategoryArtwork(String kind) {
		return executeSelect("SELECT * FROM artwork WHERE kind=?",
				pstmt -> pstmt.setString(1, kind),
				ArtworkDao::extractArtworkVO);

	}

	public List<ArtworkVO> selectArtwork() {
		return executeSelect("SELECT * FROM artwork", ArtworkDao::extractArtworkVO);
	}

	private static ArtworkVO extractArtworkVO(ResultSet rs) throws SQLException {

		ArtworkVO avo = new ArtworkVO();
		avo.setAseq(rs.getInt("aseq"));
		avo.setName(rs.getString("name"));
		avo.setKind(rs.getString("kind"));
		avo.setArtist(rs.getString("artist"));
		avo.setYear(rs.getString("year"));
		avo.setMaterial(rs.getString("material"));
		avo.setSize(rs.getString("size"));
		avo.setDisplay(rs.getString("display"));
		avo.setContent(rs.getString("content"));
		avo.setImage(rs.getString("image"));
		avo.setSavefilename(rs.getString("savefilename"));
		avo.setIndate(rs.getDate("indate"));
		return avo;

	}

	public void deleteArtwork(Integer aseq) {
		executeUpdate("DELETE FROM artwork WHERE aseq = ?", pstmt -> pstmt.setInt(1, aseq));

	}
}