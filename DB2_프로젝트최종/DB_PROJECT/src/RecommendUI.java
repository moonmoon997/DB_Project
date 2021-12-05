import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class RecommendUI extends JFrame{
	String[] midc=null;
	
	JButton recommend_btn = new JButton("메뉴추천");
	JButton favorite_btn = new JButton("등급찾기");
	JButton review_btn = new JButton("리뷰");
	JButton randomdice_btn=new JButton("메뉴추천");
	JTextField foodfield = new JTextField(30);
	JComboBox rescbb;
	JComboBox maincbb;
	JComboBox midcbb;
	
	public RecommendUI() throws SQLException{
		setTitle("메뉴추천프로그램");
		Container c = getContentPane();
		c.setLayout(null);
		setSize(600, 400);
		
		Foodlist fl=new Foodlist();
		Food[] food=fl.get_Foodlist();
		//
		List<String> mainc = new ArrayList<String>();
		mainc.add("");
		for(int i=0;i<food.length;i++) {
			if(!mainc.contains(food[i].get_maincatagory()))
				mainc.add(food[i].get_maincatagory());
		}
		Collections.sort(mainc);
		maincbb = new JComboBox(mainc.toArray());
		//
		List<String> midc = new ArrayList<String>();
		for(int i=0; i<food.length;i++) {
			if (food[i].get_maincatagory().equals(maincbb.getSelectedItem().toString()) && !midc.contains(food[i].get_middleclass()))
				midc.add(food[i].get_middleclass());
		}
		Collections.sort(midc);
		midcbb = new JComboBox(mainc.toArray());
		
		rescbb=new JComboBox();
		
		JLabel lb1=new JLabel("대분류 : ");
		JLabel lb2=new JLabel("중분류 : ");
		JLabel lb3=new JLabel("음식점목록");
		
		//�슂�냼�쐞移섏��젙
		recommend_btn.setBounds(0,0,150,50);
		favorite_btn.setBounds(150,0,150,50);
		review_btn.setBounds(300,0,150,50);
		maincbb.setBounds(150,120,150,50);
		midcbb.setBounds(150,220,150,50);
		randomdice_btn.setBounds(380, 120,100,50);
		foodfield.setBounds(350,220,100,50);
		foodfield.setEnabled(false);
		rescbb.setBounds(460,220,100,50);
		lb1.setBounds(80,130,70,30);
		lb2.setBounds(80,230,70,30);
		lb3.setBounds(470,190,70,30);
		
		
		//甕곌쑵�뱣占쎌뵠甕겹끋�뱜占쎈뼎疫뀐옙
		recommend_btn.addActionListener(new ButtonAction());
		favorite_btn.addActionListener(new ButtonAction());
		review_btn.addActionListener(new ButtonAction());
		randomdice_btn.addActionListener(new ButtonAction());
		maincbb.addItemListener(new ItemAction());
				
		//占쎌뒄占쎈꺖 �빊遺쏙옙
		c.add(recommend_btn);
		c.add(favorite_btn);
		c.add(review_btn);
		c.add(randomdice_btn);
		c.add(maincbb);
		c.add(midcbb);			
		c.add(foodfield);		
		c.add(rescbb);
		c.add(lb3);
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
				midcbb.removeAllItems();
				try {
					Foodlist fl=new Foodlist();
					Food[] food = fl.get_Foodlist();					
					List<String> midc=new ArrayList<String>();
					midc.add("");
					for(int i=0; i<food.length; i++) {
						if(food[i].get_maincatagory().equals(maincbb.getSelectedItem().toString()) && !midc.contains(food[i].get_middleclass()))
							midc.add(food[i].get_middleclass());
					}
					for(int i=0; i<midc.toArray().length; i++) {
						midcbb.addItem(midc.toArray()[i]);
					}
				} catch (SQLException e) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private class ButtonAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//占쎌삏占쎈쑁甕곌쑵�뱣 占쎄깻�뵳占�
			if(e.getSource()==randomdice_btn) { 
				rescbb.removeAllItems();
				//control 占쎄깻占쎌삋占쎈뮞 占쎄문占쎄쉐
				String maincatagory = maincbb.getSelectedItem().toString();
				String middleclass = midcbb.getSelectedItem().toString();
				RecommendSystem rs=new RecommendSystem();
				RestaurantSystem rss=new RestaurantSystem();
				try {
					Food finalresult = rs.recommend(maincatagory, middleclass);
					String[] resresult = rss.recommend(finalresult.get_maincatagory());
					foodfield.setText(finalresult.get_foodname());
					for(int i=0; i<resresult.length; i++) {
						rescbb.addItem(resresult[i]);
					}
					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
			
			////////////////占쎄맒占쎈뼊甕곌쑵�뱣//////////////////////
			//占쎄맒占쎈뼊 筌롫뗀�뤀�빊遺우퓝甕곌쑵�뱣 占쎄깻�뵳占�
			else if(e.getSource()==recommend_btn) {
				try {
					RecommendUI RUI = new RecommendUI();
					dispose();
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
			//占쎄맒占쎈뼊 筌앸Þ爰쇽㎕�뼐由계린袁る뱣 占쎄깻�뵳占�
			else if(e.getSource()==favorite_btn) {
				try {
					FavoriteUI UI = new FavoriteUI();;
					dispose();
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
			//占쎄맒占쎈뼊 �뵳�됰윮甕곌쑵�뱣 占쎄깻�뵳占�
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
		RecommendUI RUI = new RecommendUI();
	}
}
