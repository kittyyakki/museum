package com.team4.museum.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Db {

	public static Connection getConnection() {
		Connection con = null;
		try {
			Context initContext = new InitialContext();
			DataSource ds = (DataSource) initContext.lookup("java:/comp/env/jdbc/MysqlDB/museum");
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int executeUpdate(String query) {
		return executeUpdate(query, pstmt -> {
		});
	}

	public static int executeUpdate(String query, StatementPreparer preparer) {
		int result = 0;
		try {
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);

			preparer.prepare(pstmt);
			result = pstmt.executeUpdate();

			close(con, pstmt, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static <T> List<T> executeSelect(String query, ResultSetExtractor<T> extractor) {
		return executeSelect(query, pstmt -> {
		}, extractor);
	}

	public static <T> List<T> executeSelect(String query, StatementPreparer preparer, ResultSetExtractor<T> extractor) {
		List<T> list = new ArrayList<>();
		try {
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);

			preparer.prepare(pstmt);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(extractor.extract(rs));
			}

			close(con, pstmt, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public static <T> T executeSelectOne(String query, ResultSetExtractor<T> extractor) {
		return executeSelectOne(query, pstmt -> {
		}, extractor);
	}

	public static <T> T executeSelectOne(String query, StatementPreparer preparer, ResultSetExtractor<T> extractor) {
		T result = null;
		try {
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);

			preparer.prepare(pstmt);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				result = extractor.extract(rs);
			}

			close(con, pstmt, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static <T> T executeCall(String query, CallStatementPreparer preparer, CallOutExtractor<T> extractor) {
		T result = null;
		try {
			Connection con = getConnection();
			CallableStatement pstmt = con.prepareCall(query);

			preparer.prepare(pstmt);
			pstmt.execute();

			result = extractor.extract(pstmt);

			close(con, pstmt, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@FunctionalInterface
	public interface StatementPreparer {
		void prepare(PreparedStatement pstmt) throws SQLException;
	}

	@FunctionalInterface
	public interface ResultSetExtractor<T> {
		T extract(ResultSet rs) throws SQLException;
	}

	@FunctionalInterface
	public interface CallStatementPreparer {
		void prepare(CallableStatement cstmt) throws SQLException;
	}

	@FunctionalInterface
	public interface CallOutExtractor<T> {
		T extract(CallableStatement cstmt) throws SQLException;
	}

}
