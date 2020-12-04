package article;
import java.sql.ResultSet;
import java.sql.SQLException;

import board.RowMapper;

public class ArticleRowMapper implements RowMapper<Article> {

	@Override
	public Article getRow(ResultSet rs) throws SQLException {
	
		String title = rs.getString("title");
		int id = rs.getInt("id");
		String body = rs.getString("body");
		String nickname = rs.getString("nickname");
		int hit = rs.getInt("hit");
		String regDate = rs.getString("regDate");

		Article article = new Article();
		article.setTitle(title);
		article.setBody(body);
		article.setNickname(nickname);
		article.setId(id);
		article.setHit(hit);
		article.setRegDate(regDate);
		
		return article;
	}
	
}
