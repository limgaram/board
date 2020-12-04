package article;
import java.util.ArrayList;

import board.DBUtil;

public class ArticleDao {

	private DBUtil db = new DBUtil();
	
	public ArrayList<Article> getArticles() {
		String sql = "SELECT a.*, m.nickname nickname FROM article a INNER JOIN `member` m ON a.mid = m.id";
		return db.getRows(sql, new ArticleRowMapper());
	}
	
	public int updateArticle(String title, String body, int aid) {
		String sql = "update article set title = ?, body = ? where id = ?";
		return db.updateQuery(sql, title, body, aid);
	}
	
	public int deleteArticle(int aid) {
		String sql = "delete from article where id = ?";
		return db.updateQuery(sql, aid);
	}
	
	public int insertArticle(String title, String body) {
		String sql = "insert into article set title = ?, body = ?, nickname = '익명', regDate = NOW(), hit = 0";
		return db.updateQuery(sql, title, body);
	}
	
	public Article getArticleById(int aid) {
		String sql = "select * from article where id = ?";
		return db.getRow(sql, new ArticleRowMapper(), aid);
	}
	
	public int insertReply(int aid, String body) {
		String sql = "insert into reply set aid = ?, body = ?, writer = '익명', regDate = NOW()";
		return db.updateQuery(sql, aid, body);
	}

	public ArrayList<Reply> getRepliesByArticleId(int id) {
		String sql = "select * from reply where aid = ?";
		return db.getRows(sql, new ReplyRowMapper(), id);
	}

	public Article getArticleByTitle(String title) {
		String sql = "select * from article where title = ? ";
		return db.getRow(sql, new ArticleRowMapper(), title);
	}

	public Article getArticleByBody(String body) {
		String sql = "select * from article where body = ? ";
		return db.getRow(sql, new ArticleRowMapper(), body);
		
	}

	public Article getArticleByWriter(String writer) {
		String sql = "select * from article where writer = ? ";
		return db.getRow(sql, new ArticleRowMapper(), writer);
		
	}
	
	public Article getArticleByTitleAndBody(String title, String body) {
		String sql = "select * from article where title = ?, body = ?";
		return db.getRow(sql, new ArticleRowMapper(), title, body);
	}
	

}
