import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class FavoriteUI extends JFrame{
	
	JButton recommend_btn = new JButton("메뉴추천");
	JButton favorite_btn = new JButton("등급찾기");
	JButton review_btn = new JButton("리뷰");
	JButton randomdice_btn=new JButton("메뉴추천");
	JTextField gradefield = new JTextField(30);
	JComboBox usercbb;

	
	public FavoriteUI() throws SQLException{
		setTitle("메뉴추천프로그램");
		Container c = getContentPane();
		c.setLayout(null);
		setSize(600, 400);
		
		Userslist ul =new Userslist();
		Users[] users=ul.get_Userslist();
		//
		List<String> userval = new ArrayList<String>();
		userval.add("");
		for(int i=0;i<users.length;i++) {
			if(!userval.contains(users[i].get_name()))
				userval.add(users[i].get_name());
		}
		Collections.sort(userval);
		usercbb = new JComboBox(userval.toArray());
		
		JLabel lb1=new JLabel("사용자목록 : ");
		JLabel lb2=new JLabel("사용자등급");
		
		//요소위치지정
		recommend_btn.setBounds(0,0,150,50);
		favorite_btn.setBounds(150,0,150,50);
		review_btn.setBounds(300,0,150,50);
		usercbb.setBounds(80,200,70,30);
		gradefield.setBounds(300,200,70,30);
		gradefield.setEnabled(false);
		lb1.setBounds(80,130,70,30);
		lb2.setBounds(300,130,70,30);
		
		
		//踰꾪듉 씠踰ㅽ듃 떖湲 
		recommend_btn.addActionListener(new ButtonAction());
		favorite_btn.addActionListener(new ButtonAction());
		review_btn.addActionListener(new ButtonAction());
		usercbb.addItemListener(new ItemAction());
				
		// 슂 냼 異붽 
		c.add(recommend_btn);
		c.add(favorite_btn);
		c.add(review_btn);
		c.add(usercbb);		
		c.add(gradefield);		
		c.add(lb1);
		c.add(lb2);
				
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private class ItemAction implements ItemListener{
		int kk=0;
		
		public void itemStateChanged(ItemEvent arg0) {
			kk++;
			if(kk%2==0) {
				
				try {
					Userslist ul =new Userslist();
					Users[] users=ul.get_Userslist();
					String gradeval = new String();
					for(int i=0; i<users.length; i++) {
						if(users[i].get_name().equals(usercbb.getSelectedItem().toString()))
							gradeval = users[i].get_grade();
					}
					gradefield.setText(gradeval);
				} catch (SQLException e) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	private class ButtonAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// 옖 뜡踰꾪듉  겢由 
			if(e.getSource()==recommend_btn) {
				try {
					RecommendUI rui = new RecommendUI();
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
			else if(e.getSource()==favorite_btn) {
				try {
					FavoriteUI favui = new FavoriteUI();
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
			// 긽 떒 由щ럭踰꾪듉  겢由 
			else if(e.getSource()==review_btn) {
				try {
					ReviewUI RevUI = new ReviewUI();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		}
	}
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		FavoriteUI UI = new FavoriteUI();
	}
}