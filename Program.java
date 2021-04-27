package �̽���_�߰����_A_0427;

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
				
				System.out.println("��ȭ �ڵ� : "+mid+", ������ �ڵ� : "+cid+", Ƽ�� ���� : "+ticketcount);
				
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
			System.out.println("���� �Ǿ����ϴ�.");
			
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
			System.out.println("�����Ǿ����ϴ�.");
			
			
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
				System.out.println("ID : "+id+", �̸� : "+title+", ��� : "+grade);
				
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
			
			System.out.println("�߰��Ǿ����ϴ�.");
			
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
				System.out.println("��ȭ ID : "+idmovie+", ��ȭ�̸� : "+title+", ��� : "+grade);
				
				
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
		 * System.out.println("id : "+personManager.get(i).getId()+", �̸� : "
		 * +personManager.get(i).getName()+", ��ȭ��ȣ : "+personManager.get(i).getTel()
		 * +" ���� : "+personManager.get(i).getKind());
		 */
		
		do {
		System.out.println("=====��ȭ�������α׷�=====");
		System.out.println();
		System.out.println("1.�α��� 2.����");
		System.out.print("�Է� : ");
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
		System.out.println("======�α���=====");
		System.out.print("�̸� : ");
		name=sc.next();
		System.out.print("��ȭ��ȣ : ");
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
		   
		   System.out.println("�ش��ϴ� �̸��� ��ȭ��ȣ�� ���� �� �����ϴ�.");
		   mainMenu();
		}

	
	public void adminMenu(person p) {
		
		int sel = 0;
		do {
		System.out.println("["+p.getName()+"] �����ڴ��� �α����ϼ̽��ϴ�.");
		
		System.out.println("=====�����ڸŴ�======");
		System.out.println("1. ��ȭ���� 2. ������ 3. �����ܰ�� �̵�");
		System.out.println("==================");
	 	System.out.print("������ �Ŵ� : ");
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
		    	System.out.print("1.��ȭ��� 2.��޺��� 3.��ü��ȭ����Ʈ, 4.��޺� ��ȸ, 5.�����ܰ�� �̵�");
		    	System.out.println("==========");
		    	System.out.print("������ �Ŵ� : ");
		    	sel = sc.nextInt();
		    	
		    	switch(sel) {
		    	
		    	case 1:
		    		
		    		System.out.print("����� ��ȭ id : ");
		    		String id = sc.next();
		    		System.out.print("����� ��ȭ �̸� : ");
		    		String name = sc.next();
		    		System.out.print("����� ��ȭ ��� : ");
		    		String grade = sc.next();
		    		
		    		addMovie(id,name,grade);
		    		admin_movieMenu(p);
		    		break;
		    		
		    	case 2: 
		    		System.out.print("����� ������ ��ȭ �̸� : ");
		    		String mname = sc.next();
		    		System.out.print("������ ��ȭ ��� : ");
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
		    		System.out.print("��ȸ�� ��� : ");
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
		
		System.out.println("=====�����ڸŴ�=====");
		System.out.println("1. ��ü������Ʈ 2.�����ܰ�� �̵�");
		System.out.println("=================");
		
		System.out.print("������ �Ŵ� : ");
		sel = sc.nextInt();
		
		switch(sel) {
		
		case 1:
			for(int i=0;i<personManager.size();i++) {
				
				System.out.println("ID : "+personManager.get(i).getId()+", �̸� : "+personManager.get(i).getName()+", ��ȭ��ȣ : "+personManager.get(i).getTel()+", ���� : "+personManager.get(i).getKind());
				
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
		System.out.println(p.getName()+"���� �α��� �ϼ̽��ϴ�.");
		
		do {
			
			System.out.println("=============");
			System.out.println("1.���� 2.���ฮ��Ʈ ��ȸ, 3.���ܰ�� �̵�");
			System.out.println("=============");
			System.out.print("�Է� : ");
			ins = sc.nextInt();
			
			switch(ins) {
			
			case 1:
				System.out.print("��ȭ ID : ");
				String mid = sc.next();
				System.out.print("Ƽ�� ���� : ");
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
