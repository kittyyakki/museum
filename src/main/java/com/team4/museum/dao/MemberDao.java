package com.team4.museum.dao;

import static com.team4.museum.util.Db.executeUpdate;
import static com.team4.museum.util.Db.executeSelectOne;
import com.team4.museum.dao.MemberDao;
import com.team4.museum.vo.MemberVO;

public class MemberDao {

	private MemberDao() {
	}

	private static final MemberDao itc = new MemberDao();

	public static MemberDao getInstance() {
		return itc;
	}

	public MemberVO getMember(String id) {
		return executeSelectOne("SELECT * FROM member WHERE id = ?", pstmt -> pstmt.setString(1, id), rs -> {
			MemberVO mvo = new MemberVO();
			mvo.setId(rs.getString("id"));
			mvo.setName(rs.getString("name"));
			mvo.setPwd(rs.getString("pwd"));
			mvo.setEmail(rs.getString("email"));
			mvo.setIndate(rs.getDate("indate"));
			mvo.setPhone(rs.getString("phone"));
			mvo.setAdminyn(rs.getString("adminyn"));
			return mvo;
		});
	}

	public int insertMember(MemberVO mvo) {
		return executeUpdate(
				"INSERT INTO member (id, name, pwd, email, phone)" + " VALUES ( ?, ?, ?, ?, ? )",
				pstmt -> {
					pstmt.setString(1, mvo.getId());
					pstmt.setString(2, mvo.getName());
					pstmt.setString(3, mvo.getPwd());
					pstmt.setString(4, mvo.getEmail());
					pstmt.setString(5, mvo.getPhone());
				});
	}

	public void updateMember(MemberVO mvo) {
		executeUpdate("UPDATE member SET pwd = ?, name = ?, email = ?, phone = ?, adminyn = ? WHERE id = ?", pstmt -> {
			pstmt.setString(1, mvo.getPwd());
			pstmt.setString(2, mvo.getName());
			pstmt.setString(3, mvo.getEmail());
			pstmt.setString(4, mvo.getPhone());
			pstmt.setString(5, mvo.getAdminyn());
			pstmt.setString(6, mvo.getId());
		});
	}

	public void deleteMember(String id) {
		executeUpdate("UPDATE member SET adminyn = 'N' WHERE id = ?", pstmt -> pstmt.setString(1, id));
	}
}
