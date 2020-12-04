package board;

import java.util.ArrayList;
import java.util.Scanner;
import article.Article;
import article.ArticleDao;
import article.Reply;
import member.Member;
import member.MemberDao;

public class App {

	private ArticleDao articleDao = new ArticleDao();
	private MemberDao memberDao = new MemberDao();
	private Scanner sc = new Scanner(System.in);
	private Member loginedMember = null;
	private String cmd = "";

	public void start() {

		while (true) {

			inputCommand();

			if (cmd.equals("list")) {
				list();
			} else if (cmd.equals("update")) {
				updateArticle();
			} else if (cmd.equals("delete")) {
				deleteArticle();
			} else if (cmd.equals("add")) {
				addArticle();
			} else if (cmd.equals("read")) {
				readArticle();
			} else if (cmd.equals("signup")) {
				signup();
			} else if (cmd.equals("signin")) {
				login();
			} else if (cmd.equals("search")) {
				articleSearch();
			}

			else {
				notACommand();
			}
		}
	}

	private void articleSearch() {
		System.out.println("검색 항목을 선택해주세요 : ");
		System.out.println("1. 제목, 2. 내용, 3. 제목 + 내용, 4. 작성자");
		int ch = Integer.parseInt(sc.nextLine());

		if (ch == 1) {
			System.out.println("검색할 제목 키워드를 입력해주세요 : ");
			String title = sc.nextLine();
			articleDao.getArticleByTitle(title);
			
		} else if (ch == 2) {
			System.out.println("검색할 내용 키워드를 입력해주세요 : ");
			String body = sc.nextLine();
			articleDao.getArticleByBody(body);

		} else if (ch == 3) {
			System.out.println("검색할 제목과 내용 키워드를 입력해주세요 : ");
			String title = sc.nextLine();
			String body = sc.nextLine();
			articleDao.getArticleByTitleAndBody(title, body);
			

		} else if (ch == 4) {
			System.out.println("검색할 작성자 키워드를 입력해주세요 : ");
			String writer = sc.nextLine();
			articleDao.getArticleByWriter(writer);
		}
	}
	

	public void login() {
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pw = sc.nextLine();

		Member target = memberDao.getMemberByLoginIdAndLoginPw(id, pw);

		if (target == null) {
			System.out.println("잘못된 회원정보 입니다.");
		} else {
			System.out.println(target.getNickname() + "님! 반갑습니다!!");
			loginedMember = target;
		}

	}

	public void signup() {
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pw = sc.nextLine();
		System.out.print("닉네임 : ");
		String nm = sc.nextLine();

		memberDao.insertMember(id, pw, nm);
	}

	public void readArticle() {
		System.out.print("상세보기할 게시물 번호 : ");
		int aid = Integer.parseInt(sc.nextLine());

		Article article = articleDao.getArticleById(aid);

		if (article == null) {
			System.out.println("없는 게시물입니다.");
		} else {
			ArrayList<Reply> replies = articleDao.getRepliesByArticleId(article.getId());
			printArticle(article, replies);

			while (true) {
				System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 좋아요, 3. 수정, 4. 삭제, 5. 목록으로) : ");
				int dcmd = Integer.parseInt(sc.nextLine());
				if (dcmd == 1) {
					System.out.print("내용을 입력해주세요 :");
					String body = sc.nextLine();
					articleDao.insertReply(article.getId(), body);
					ArrayList<Reply> replies2 = articleDao.getRepliesByArticleId(article.getId());
					printArticle(article, replies2);
				} else {
					break;
				}
			}
		}
	}

	public void addArticle() {
		System.out.print("제목 : ");
		String title = sc.nextLine();
		System.out.print("내용 : ");
		String body = sc.nextLine();

		articleDao.insertArticle(title, body);
	}

	public void deleteArticle() {
		System.out.print("삭제할 게시물 번호 : ");
		int aid = Integer.parseInt(sc.nextLine());
		articleDao.deleteArticle(aid);
	}

	public void updateArticle() {
		System.out.print("수정할 게시물 번호 : ");
		int aid = Integer.parseInt(sc.nextLine());

		System.out.print("제목 : ");
		String title = sc.nextLine();
		System.out.print("내용 : ");
		String body = sc.nextLine();
		articleDao.updateArticle(title, body, aid);
	}

	public void list() {
		ArrayList<Article> articles = articleDao.getArticles();
		printArticles(articles);
	}

	public void inputCommand() {
		if (loginedMember == null) {
			System.out.println("명령어를 입력해주세요.");
		} else {

			String loginedUserInfo = String.format("[%s(%s)]", loginedMember.getLoginId(), loginedMember.getNickname());
			System.out.println("명령어를 입력해주세요." + loginedUserInfo);
		}
		cmd = sc.nextLine();
	}

	public void notACommand() {
		System.out.println("올바른 명령어가 아닙니다.");
	}

	public void printArticles(ArrayList<Article> articles) {
		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);

			System.out.println("번호 : " + article.getId());
			System.out.println("제목 : " + article.getTitle());
			System.out.println("작성자 : " + article.getNickname());
			System.out.println("등록날짜 : " + article.getRegDate());
			System.out.println("조회수 : " + article.getHit());
			System.out.println("=============================");
		}
	}

	public void printArticle(Article article, ArrayList<Reply> replies) {
		System.out.println("번호 : " + article.getId());
		System.out.println("제목 : " + article.getTitle());
		System.out.println("내용 : " + article.getBody());
		System.out.println("==== 댓글 ====");
		for (int i = 0; i < replies.size(); i++) {
			System.out.println("내용 : " + replies.get(i).getBody());
			System.out.println("작성자 : " + replies.get(i).getWriter());
			System.out.println("=============================");
		}

	}
}
