package member;
import java.sql.ResultSet;
import java.sql.SQLException;

import board.RowMapper;

public class MemberRowMapper implements RowMapper<Member> {

	
	public MemberRowMapper() {
		System.out.println("dfdfdfdfdf");
	}
	@Override
	public Member getRow(ResultSet rs) throws SQLException {
		
		Member member = new Member();
		member.setId(rs.getInt("id"));
		member.setLoginId(rs.getString("loginId"));
		member.setLoginPw(rs.getString("loginPw"));
		member.setNickname(rs.getString("nickname"));
		member.setRegDate(rs.getString("regDate"));
		
		return member;
	}

}
