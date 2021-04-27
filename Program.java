
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	ArrayList<person> personManager = new ArrayList<person>();
	Scanner sc = new Scanner(System.in);
	
	public void getReservation(String id) {
		
		getConnection();
		
		try {
			
			String sql = "select * from reservation where cid='"+id+"'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				String mid = rs.getString("idmovie");
				String cid = rs.getString("cid");
				String ticketcount = rs.getString("ticketcount");
				
				System.out.println("영화 코드 : "+mid+", 예매자 코드 : "+cid+", 티켓 개수 : "+ticketcount);
				
			}
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	public void reserveTicket(String id,String cid,String ticket) {
		
		getConnection();
		
		try {
			
			String sql = "insert into reservation(idmovie,cid,ticketcount) values('"+id+"','"+cid+"','"+ticket+"')";
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("예약 되었습니다.");
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void updateMovie(String name,String grade) {
		
		getConnection();
		
		try {
			
			String sql = "update movie set grade='"+grade+"' where title='"+name+"'";
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("수정되었습니다.");
			
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	public void viewMovieByGrade(String t) {
		
		getConnection();
		
		try {
			
			String sql = "select * from movie where grade='"+t+"'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				
				String id = rs.getString("idmovie");
				String title = rs.getString("title");
				String grade = rs.getString("grade");
				System.out.println("ID : "+id+", 이름 : "+title+", 등급 : "+grade);
				
			}
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	public void addMovie(String id,String name,String grade) {
		
		getConnection();
		
		try {
			
			String sql = "insert into movie(idmovie,title,grade) values('"+id+"','"+name+"','"+grade+"')";
			stmt  = conn.createStatement();
			stmt.executeUpdate(sql);
			
			System.out.println("추가되었습니다.");
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	public void getConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		}catch(ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		try {
			
			String url = "jdbc:mysql://localhost:3306/moviepolya";
			String user="root";
			String password = "1234";
			
			conn = DriverManager.getConnection(url, user, password);
			
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void printAllMovie() {
		
		getConnection();
		
		try {
			
			String sql = "select * from movie";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				String idmovie = rs.getString("idmovie");
				String title = rs.getString("title");
				String grade = rs.getString("grade");
				System.out.println("영화 ID : "+idmovie+", 영화이름 : "+title+", 등급 : "+grade);
				
				
			}
			
			
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
			
	}
	
	public void getUserInfo() {
		
		getConnection();
		
		try {
			
			String sql = "select * from customera";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				String id = rs.getString("cid");
				String name = rs.getString("cname");
				String tel = rs.getString("ctel");
				String kind = rs.getString("ckind");
				person a = new person(id,name,tel,kind);
				personManager.add(a);
				
			}
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
	
	}
	
	public void mainMenu() {
		int ans = 0;
		
		/*
		 * for(int i=0;i<personManager.size();i++)
		 * System.out.println("id : "+personManager.get(i).getId()+", 이름 : "
		 * +personManager.get(i).getName()+", 전화번호 : "+personManager.get(i).getTel()
		 * +" 구분 : "+personManager.get(i).getKind());
		 */
		
		do {
		System.out.println("=====영화관리프로그램=====");
		System.out.println();
		System.out.println("1.로그인 2.종료");
		System.out.print("입력 : ");
		ans = sc.nextInt();
		
	
			
		switch(ans) {
		
		case 1:
			login();
		break;
			
		case 2:
			sc.close();
			System.exit(0);
		break;
		
		
		}
			
			
			
			
		
		
		}while(ans != 1);
	}
	
	
	public void login() {
		String name,tel;
		System.out.println("======로그인=====");
		System.out.print("이름 : ");
		name=sc.next();
		System.out.print("전화번호 : ");
		tel=sc.next();
		
		   for(int i=0;i<personManager.size();i++) {
			
			if(personManager.get(i).getName().equals(name)) {
	         if(personManager.get(i).getTel().equals(tel)) {
	        if(personManager.get(i).getKind().equals("m")) {
	        	adminMenu(personManager.get(i));
	        }else { userMenu(personManager.get(i)); }
	        }
	         }
			}
		   
		   System.out.println("해당하는 이름과 전화번호를 잦을 수 없습니다.");
		   mainMenu();
		}

	
	public void adminMenu(person p) {
		
		int sel = 0;
		do {
		System.out.println("["+p.getName()+"] 관리자님이 로그인하셨습니다.");
		
		System.out.println("=====관리자매뉴======");
		System.out.println("1. 영화관리 2. 고객관리 3. 이전단계로 이동");
		System.out.println("==================");
	 	System.out.print("관리자 매뉴 : ");
		sel = sc.nextInt();
		
		switch(sel) {
		
		case 1:
			admin_movieMenu(p);
		break;
		
		case 2:
			admin_userMenu(p);
			break; 
			
		case 3:
			mainMenu();
			break;
		
		}

		}while(sel == 3);
	   
		
	}
	
	public void admin_movieMenu(person p) {
		
		int sel;
		
		 do {
		    	
		    	System.out.println("==========");
		    	System.out.print("1.영화등록 2.등급변경 3.전체영화리스트, 4.등급별 조회, 5.이전단계로 이동");
		    	System.out.println("==========");
		    	System.out.print("관리자 매뉴 : ");
		    	sel = sc.nextInt();
		    	
		    	switch(sel) {
		    	
		    	case 1:
		    		
		    		System.out.print("등록할 영화 id : ");
		    		String id = sc.next();
		    		System.out.print("등록할 영화 이름 : ");
		    		String name = sc.next();
		    		System.out.print("등록할 영화 등급 : ");
		    		String grade = sc.next();
		    		
		    		addMovie(id,name,grade);
		    		admin_movieMenu(p);
		    		break;
		    		
		    	case 2: 
		    		System.out.print("등급을 변경할 영화 이름 : ");
		    		String mname = sc.next();
		    		System.out.print("변경할 영화 등급 : ");
		    		String mgrade = sc.next();
		    		updateMovie(mname,mgrade);
		    		admin_movieMenu(p);
		    		
		    		break;
		    	case 3:
		    		printAllMovie();
		    		admin_movieMenu(p);
		    		break;
		    		
		  
		    	case 4:
		    		String n;
		    		System.out.print("조회할 등급 : ");
		    		n=sc.next();
		    		viewMovieByGrade(n);
		    		admin_movieMenu(p);
		    	
		    		break;
		    		
		    	case 5:
		    	mainMenu(); break;
		    	
		    	
		    	
		    	}
		    	
		    }while(sel == 5);
		

	}
	
	public void admin_userMenu(person p) {
		int sel = 0;
		do {
		
		System.out.println("=====관리자매뉴=====");
		System.out.println("1. 전체고객리스트 2.이전단계로 이동");
		System.out.println("=================");
		
		System.out.print("관리자 매뉴 : ");
		sel = sc.nextInt();
		
		switch(sel) {
		
		case 1:
			for(int i=0;i<personManager.size();i++) {
				
				System.out.println("ID : "+personManager.get(i).getId()+", 이름 : "+personManager.get(i).getName()+", 전화번호 : "+personManager.get(i).getTel()+", 구분 : "+personManager.get(i).getKind());
				
			}
			admin_userMenu(p);
			break;
		case 2:
			adminMenu(p);
			break;
		
		}
		
		}while(sel == 2);
	}
	
	public void userMenu(person p) {
		int ins = 0;
		System.out.println(p.getName()+"님이 로그인 하셨습니다.");
		
		do {
			
			System.out.println("=============");
			System.out.println("1.예매 2.예약리스트 조회, 3.전단계로 이동");
			System.out.println("=============");
			System.out.print("입력 : ");
			ins = sc.nextInt();
			
			switch(ins) {
			
			case 1:
				System.out.print("영화 ID : ");
				String mid = sc.next();
				System.out.print("티켓 개수 : ");
				String mtk = sc.next();
				
				reserveTicket(mid,p.getId(),mtk);
				userMenu(p);
				break;
				
			case 2:
				
				getReservation(p.getId());
				userMenu(p);
				break;
				
			case 3:
				mainMenu();
				break;
			
			
			}
			
		}while(ins == 3);
		
		
		
		
	}
	
	
	
	public static void main(String[] args) {
		
		
		Program prg = new Program();
		prg.getUserInfo();
		prg.mainMenu();
		
		
	}
	
	
	

}
