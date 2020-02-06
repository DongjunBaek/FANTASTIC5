package member.model.service;

import java.sql.Connection;
import static common.JDBCTemplate.*;

import member.model.dao.MemberDAO;
import member.model.vo.Member;

/**
 * @author Minhee
	만든날짜 : 200203
	목적 : 서비스단
 *
 */
public class MemberService {

	public Member selectOne(String memberId) {
		Connection conn = getConnection();
		Member m = new MemberDAO().selectOne(conn, memberId);
		close(conn);
		return m;
	}

}
