package member.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static common.JDBCTemplate.*;

import member.model.vo.Member;

/**
 * @author Minhee
	만든날짜 : 200203
	목적 : DAO
 *
 */
public class MemberDAO {
	
	private Properties prop = new Properties();
	
	public MemberDAO() {
		
		String fileName = MemberDAO.class.getResource("/sql/member/member-query.properties")
										 .getPath();
		
		try {
			
			prop.load(new FileReader(fileName));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Member selectOne(Connection conn, String memberId) {
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectOne");
		System.out.println("query="+query);
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				m = new Member();
				m.setMcode(rset.getInt("member_code"));
				m.setMemberId(rset.getString("member_id"));
				m.setPassword(rset.getString("member_password"));
				m.setPhone(rset.getString("member_phone"));
				m.setEmail(rset.getString("member_email"));
				m.setAddress(rset.getString("member_address"));
				m.setEnrollDate(rset.getDate("member_regdate"));
				m.setMile(rset.getDouble("member_mile"));
				m.setBirthDay(rset.getDate("member_birth"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		return m;
	}

}
