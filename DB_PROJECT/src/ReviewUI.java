import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class ReviewUI extends JFrame {
	Users us=new Users("member1", "member1", "회원1", "폰번1");
	
	JButton recommend_btn = new JButton("메뉴추천");
	JButton favorite_btn = new JButton("즐겨찾기");
	JButton board_btn = new JButton("리뷰	");
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
		
		//control �겢�옒�뒪 �깮�꽦 & �샇異�
		ReviewSystem rvs=new ReviewSystem(us);
		Reviewlist rl=rvs.updatePostlist();
		Review[] rll=rl.get_Reviewlist();
		
		String header[]= {"번호","내용","작성자", "음식점이름"};
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
		
		// 而щ읆 �궗�씠利� 吏��젙
		postlisttable.getColumnModel().getColumn(0).setPreferredWidth(40);
		postlisttable.getColumnModel().getColumn(1).setPreferredWidth(250);
		postlisttable.getColumnModel().getColumn(2).setPreferredWidth(70);
		postlisttable.getColumnModel().getColumn(3).setPreferredWidth(70);
		
		//肄ㅻ낫諛뺤뒪 �궡�슜吏��젙
		String[] kinds= {"내용", "음식점", "작성자"};
		search_CB=new JComboBox(kinds);
		
		//�슂�냼 �쐞移섏��젙
		recommend_btn.setBounds(0,0,150,50);
		favorite_btn.setBounds(150,0,150,50);
		board_btn.setBounds(300,0,150,50);
		search_LB.setBounds(180,60,50,30);
		search_CB.setBounds(235,60,70,30);
		search_TF.setBounds(315,60,150,30);
		search_btn.setBounds(480,60,80,30);
		write_btn.setBounds(490,380,80,30);
		pane.setBounds(15,100,560,270);
		
		//踰꾪듉�씠踰ㅽ듃�떖湲�
		recommend_btn.addActionListener(new ButtonAction());
		favorite_btn.addActionListener(new ButtonAction());
		board_btn.addActionListener(new ButtonAction());
		search_btn.addActionListener(new ButtonAction());
		write_btn.addActionListener(new ButtonAction());
		
		//�슂�냼 異붽�
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
			//寃��깋踰꾪듉 �겢由�
			if(e.getSource()==search_btn) {
				//control �겢�옒�뒪 �깮�꽦
				ReviewSystem BS=new ReviewSystem(us);
				
				str=search_CB.getSelectedItem().toString();
				String str2="%"+search_TF.getText()+"%";
				
				Reviewlist p;
				Review[] pll = null;
				try {
					pll = BS.searchPost(str, str2); //control �겢�옒�뒪 searchPost �샇異�
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
				// 而щ읆 �궗�씠利� 吏��젙
				postlisttable.getColumnModel().getColumn(0).setPreferredWidth(40);
				postlisttable.getColumnModel().getColumn(1).setPreferredWidth(300);
				postlisttable.getColumnModel().getColumn(2).setPreferredWidth(70);
				postlisttable.getColumnModel().getColumn(3).setPreferredWidth(70);
				pane.setViewportView(postlisttable);
			}
			//湲��옉�꽦踰꾪듉 �겢由�
			else if(e.getSource()==write_btn) {
				//WriteReviewUI WRUI=new WriteReviewUI(us);
				dispose();
			}
			
			////////////////�긽�떒踰꾪듉//////////////////////
			//�긽�떒 硫붾돱異붿쿇踰꾪듉 �겢由�
			else if(e.getSource()==recommend_btn) {
				try {
					RecommendUI RUI = new RecommendUI();
					dispose();
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
			//�긽�떒 利먭꺼李얘린踰꾪듉 �겢由�
			else if(e.getSource()==favorite_btn) {
				
			}
			//�긽�떒 寃뚯떆�뙋踰꾪듉 �겢由�
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
