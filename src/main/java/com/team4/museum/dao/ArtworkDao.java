package com.team4.museum.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Date;

import com.team4.museum.vo.ArtworkVO;
import static com.team4.museum.util.Db.*;

public class ArtworkDao {
	
	private ArtworkDao(){}
	
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
    
    
    
    public List<ArtworkVO> selectKindArtwork(String kind) {
    	return executeSelect("Select * From ArtWork where kind =? ",
    			pstmt -> pstmt.setString(1,kind),
    			ArtworkDao::extractArtworkVO
   			);
    	
    	
    }
    
    private static ArtworkVO extractArtworkVO(ResultSet rs) throws SQLException {
    
    	ArtworkVO pvo = new ArtworkVO();
        pvo.setAseq(rs.getInt("aseq"));
        pvo.setName(rs.getString("name"));
        pvo.setKind(rs.getString("kind"));
        pvo.setArtist(rs.getString("artist"));
        pvo.setYear(rs.getString("year"));
        pvo.setMaterial(rs.getString("material"));
        pvo.setSize(rs.getString("size"));
        pvo.setDisplay(rs.getString("display"));
        pvo.setContent(rs.getString("content"));
        pvo.setImage(rs.getString("image"));
        pvo.setSavefilename(rs.getString("savefilename"));
        pvo.setIndate(rs.getDate("indate"));
        return pvo;
        
        
        
    }
    
    public void deleteArtwork(Integer aseq) {
    	executeUpdate("DELETE FROM artwork WHERE aseq = ?", pstmt -> pstmt.setInt(1, aseq));
       
    }
}