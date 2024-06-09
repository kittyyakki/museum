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

	/**
	 * 데이터베이스 연결을 위한 Connection 객체를 반환합니다.
	 * 
	 * 반환된 Connection 객체를 사용한 후에는 반드시 close() 메서드를 호출하여 자원을 반환해야 합니다.
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
			DataSource ds = (DataSource) initContext.lookup("java:/comp/env/jdbc/MysqlDB/museum");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 주어진 Connection, PreparedStatement, ResultSet 객체를 닫습니다.
	 */
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * SQL 쿼리를 실행하고 영향을 받은 행의 수를 반환합니다.
	 */
	public static int executeUpdate(String query) {
		return executeUpdate(query, pstmt -> {
		});
	}

	/**
	 * SQL 쿼리를 실행하고 영향을 받은 행의 수를 반환합니다.
	 * 
	 * @param paramSetter SQL 쿼리를 준비하는 람다식
	 */
	public static int executeUpdate(String query, ParameterSetter paramSetter) {
		int result = 0;

		try {
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);

			paramSetter.accept(pstmt);
			result = pstmt.executeUpdate();

			close(conn, pstmt, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * SQL 쿼리를 실행하고 결과를 반환합니다.
	 * 
	 * @param resultMapper SQL 쿼리 결과를 매핑하는 람다식
	 */
	public static <T> List<T> executeSelect(String query, ResultMapper<T> resultMapper) {
		return executeSelect(query, pstmt -> {
		}, resultMapper);
	}

	/**
	 * SQL 쿼리를 실행하고 결과를 반환합니다.
	 * 
	 * @param paramSetter  SQL 쿼리를 준비하는 람다식
	 * @param resultMapper SQL 쿼리 결과를 매핑하는 람다식
	 */
	public static <T> List<T> executeSelect(String query, ParameterSetter paramSetter, ResultMapper<T> resultMapper) {
		List<T> list = new ArrayList<>();

		try {
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);

			paramSetter.accept(pstmt);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(resultMapper.apply(rs));
			}

			close(conn, pstmt, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * SQL 쿼리를 실행하고 결과 중 첫 번째 행을 반환합니다.
	 * 
	 * @param resultMapper SQL 쿼리 결과를 매핑하는 람다식
	 */
	public static <T> T executeSelectOne(String query, ResultMapper<T> resultMapper) {
		return executeSelectOne(query, pstmt -> {
		}, resultMapper);
	}

	/**
	 * SQL 쿼리를 실행하고 결과 중 첫 번째 행을 반환합니다.
	 * 
	 * @param paramSetter  SQL 쿼리를 준비하는 람다식
	 * @param resultMapper SQL 쿼리 결과를 매핑하는 람다식
	 */
	public static <T> T executeSelectOne(String query, ParameterSetter paramSetter, ResultMapper<T> resultMapper) {
		List<T> list = executeSelect(query, paramSetter, resultMapper);
		return list.isEmpty() ? null : list.get(0);
	}

	/**
	 * SQL 프로시저 쿼리를 실행하고 결과를 반환합니다.
	 * 
	 * @param resultMapper 프로시저 결과를 추출하는 람다식
	 */
	public static <T> T executeCall(String query, CallParameterSetter paramSetter, CallResultMapper<T> resultMapper) {
		T result = null;

		try {
			Connection conn = getConnection();
			CallableStatement pstmt = conn.prepareCall(query);

			paramSetter.accept(pstmt);
			pstmt.execute();

			result = resultMapper.extract(pstmt);

			close(conn, pstmt, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * {@link java.util.function.Consumer}의 역할을 수행
	 * 
	 * {@link java.sql.PreparedStatement}를 처리하는 메서드에 대한 명시적인 의미를 부여하고,
	 * {@link java.sql.SQLException}을 던질 수 있도록 하기 위해 별도로 정의한 함수형 인터페이스입니다.
	 */
	@FunctionalInterface
	public interface ParameterSetter {
		void accept(PreparedStatement pstmt) throws SQLException;
	}

	/**
	 * {@link java.util.function.Function}의 역할을 수행
	 * 
	 * {@link java.sql.ResultSet}를 처리하고 결과를 반환하는 메서드에 대한 명시적인 의미를 부여하고,
	 * {@link java.sql.SQLException}을 던질 수 있도록 하기 위해 별도로 정의한 함수형 인터페이스입니다.
	 */
	@FunctionalInterface
	public interface ResultMapper<T> {
		T apply(ResultSet rs) throws SQLException;
	}

	/**
	 * {@link java.util.function.Consumer}의 역할을 수행
	 * 
	 * {@link java.sql.CallableStatement}를 처리하는 메서드에 대한 명시적인 의미를 부여하고,
	 * {@link java.sql.SQLException}을 던질 수 있도록 하기 위해 별도로 정의한 함수형 인터페이스입니다.
	 */
	@FunctionalInterface
	public interface CallParameterSetter {
		void accept(CallableStatement cstmt) throws SQLException;
	}

	/**
	 * {@link java.util.function.Function}의 역할을 수행
	 * 
	 * {@link java.sql.CallableStatement}를 처리하고 결과를 반환하는 메서드에 대한 명시적인 의미를 부여하고,
	 * {@link java.sql.SQLException}을 던질 수 있도록 하기 위해 별도로 정의한 함수형 인터페이스입니다.
	 */
	@FunctionalInterface
	public interface CallResultMapper<T> {
		T extract(CallableStatement cstmt) throws SQLException;
	}

}
