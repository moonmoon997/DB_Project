import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class ReviewUI extends JFrame {
	Users us=new Users("member1", "member1", "회원1", "폰번1");
	
	JButton recommend_btn = new JButton("메뉴추천");
	JButton favorite_btn = new JButton("즐겨찾기");
	JButton board_btn = new JButton("게시판");
	JLabel search_LB=new JLabel("검색   : ");
	JComboBox search_CB;
	JTextField search_TF=new JTextField(20);
	JButton search_btn=new JButton("검색");
	JButton write_btn=new JButton("글 작성");
	JTable postlisttable;
	JScrollPane pane;
	
	public ReviewUI() throws SQLException {
		setTitle("메뉴추천프로그램");
		Container c = getContentPane();
		c.setLayout(null);
		setSize(600, 465);
		
		//control 클래스 생성 & 호출
		ReviewSystem rvs=new ReviewSystem(us);
		Reviewlist rl=rvs.updatePostlist();
		Review[] rll=rl.get_Reviewlist();
		
		String header[]= {"번호", "내용", "작성자", "음식점이름"};
		String[][] conts;
		conts=new String[rll.length][header.length];
		
		for(int i=0; i<rll.length; i++) {
			conts[i][0]=Integer.toString(rll[i].get_viewnum());
			conts[i][1]=rll[i].get_content();
			conts[i][2]=rll[i].get_writer();
			conts[i][3]=rll[i].get_resname();
		}
		
		postlisttable=new JTable(conts, header);
		
		pane=new JScrollPane(postlisttable);		
		
		// 컬럼 사이즈 지정
		postlisttable.getColumnModel().getColumn(0).setPreferredWidth(40);
		postlisttable.getColumnModel().getColumn(1).setPreferredWidth(250);
		postlisttable.getColumnModel().getColumn(2).setPreferredWidth(70);
		postlisttable.getColumnModel().getColumn(3).setPreferredWidth(70);
		
		//콤보박스 내용지정
		String[] kinds= {"내용", "제목", "작성자"};
		search_CB=new JComboBox(kinds);
		
		//요소 위치지정
		recommend_btn.setBounds(0,0,150,50);
		favorite_btn.setBounds(300,0,150,50);
		board_btn.setBounds(450,0,150,50);
		search_LB.setBounds(180,60,50,30);
		search_CB.setBounds(235,60,70,30);
		search_TF.setBounds(315,60,150,30);
		search_btn.setBounds(480,60,80,30);
		write_btn.setBounds(490,380,80,30);
		pane.setBounds(15,100,560,270);
		
		//버튼이벤트달기
		recommend_btn.addActionListener(new ButtonAction());
		favorite_btn.addActionListener(new ButtonAction());
		board_btn.addActionListener(new ButtonAction());
		search_btn.addActionListener(new ButtonAction());
		write_btn.addActionListener(new ButtonAction());
		
		//요소 추가
		c.add(recommend_btn);
		c.add(favorite_btn);
		c.add(board_btn);
		c.add(search_TF);
		c.add(search_btn);
		c.add(search_LB);	
		c.add(pane);
		c.add(write_btn);
		c.add(search_CB);
		
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private class ButtonAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String str = "";
			//검색버튼 클릭
			if(e.getSource()==search_btn) {
				//control 클래스 생성
				ReviewSystem BS=new ReviewSystem(us);
				
				str=search_CB.getSelectedItem().toString();
				String str2="%"+search_TF.getText()+"%";
				
				Reviewlist p;
				Review[] pll = null;
				try {
					pll = BS.searchPost(str, str2); //control 클래스 searchPost 호출
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				String header[]= {"번호", "내용", "작성자", "음식점이름"};
				String[][] conts;
				conts=new String[pll.length][header.length];
				
				for(int i=0; i<pll.length; i++) {
					conts[i][0]=Integer.toString(pll[i].get_viewnum());
					conts[i][1]=pll[i].get_content();
					conts[i][2]=pll[i].get_writer();
					conts[i][3]=pll[i].get_resname();
				}
				
				postlisttable=new JTable(conts, header);	
				// 컬럼 사이즈 지정
				postlisttable.getColumnModel().getColumn(0).setPreferredWidth(40);
				postlisttable.getColumnModel().getColumn(1).setPreferredWidth(300);
				postlisttable.getColumnModel().getColumn(2).setPreferredWidth(70);
				postlisttable.getColumnModel().getColumn(3).setPreferredWidth(70);
				pane.setViewportView(postlisttable);
			}
			//글작성버튼 클릭
			else if(e.getSource()==write_btn) {
				//WriteReviewUI WRUI=new WriteReviewUI(us);
				dispose();
			}
			
			////////////////상단버튼//////////////////////
			//상단 메뉴추천버튼 클릭
			else if(e.getSource()==recommend_btn) {
				try {
					RecommendUI RUI = new RecommendUI();
					dispose();
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
			//상단 즐겨찾기버튼 클릭
			else if(e.getSource()==favorite_btn) {
				
			}
			//상단 게시판버튼 클릭
			else if(e.getSource()==board_btn) {
				try {
					ReviewUI ru = new ReviewUI();
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		ReviewUI ru = new ReviewUI();
	}

}
