package member;

import board.DBUtil;

public class MemberDao {
	private DBUtil db = new DBUtil();

	public void insertMember(String id, String pw, String nm) {
		String sql = "insert into member set loginId = ?, loginPw = ?, nickname = ?, regDate = NOW()";
		db.updateQuery(sql, id, pw, nm);
	}

	public Member getMemberByLoginIdAndLoginPw(String id, String pw) {
		String sql = "select * from member where loginId = ? and loginPw = ?";
		return db.getRow(sql, new MemberRowMapper(), id, pw);
	}
	
}
