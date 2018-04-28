package com.info.database;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.Statement;

/**
 * ���ݿ������
 * 
 *
 */

public class DataBase {

	// ���ݿ��ַ��jdbc:mysql://����������:�˿ں�/���ݿ����ơ�
	private String url = "jdbc:mysql://127.0.0.1:3306/infomanage?useUnicode=true&characterEncoding=utf-8";

	// �û���
	private String user = "root";

	// �û�����
	private String pwd = "123456";

	// ���ݿ����Ӷ���
	private java.sql.Connection conn;

	// ���ݿ�����ִ�ж���
	private PreparedStatement pstmt;

	// ���ݿⷵ�ؽ��
	private java.sql.ResultSet rs;

	// ��̬�����
	static {
		// 1����������
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	// 2����������
	private void getConnection() {
		if (conn == null) {
			try {
				conn = DriverManager.getConnection(url, user, pwd);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// ִ�ж���������
	public java.sql.ResultSet executeQuery(String query, List<Object> params) {
		getConnection();
		try {
			// 3����������ִ�ж���
			pstmt = conn.prepareStatement(query);
			// 4��ִ��
			if (params != null && params.size() > 0) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(i + 1, params.get(i));
				}
			}
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	// ִ��д��������
	public int executeUpdate(String query, List<Object> params) {
		int result = 0;
		getConnection();
		try {
			// 3����������ִ�ж���
			pstmt = conn.prepareStatement(query);
			// 4��ִ��
			if (params != null && params.size() > 0) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(i + 1, params.get(i));
				}
			}
			// 5��������
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6���ͷ���Դ
			this.close();
		}
		return result;
	}

	// ִ��д��������
	public Long executeUpdateBack(String query, List<Object> params) {
		getConnection();
		try {
			// 3����������ִ�ж���
			pstmt = conn.prepareStatement(query, java.sql.Statement.RETURN_GENERATED_KEYS);
			// 4��ִ��
			if (params != null && params.size() > 0) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(i + 1, params.get(i));
				}
			}
			// 5��������
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			 if (rs.next()) {
				return rs.getLong(1);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6���ͷ���Դ
			this.close();
		}
		return null;
	}

	// �ر���Դ
	public void close() {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}