package atmsystem;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mysql.jdbc.Statement;


public class MysqlOperation {
	
//���ݿ���û���������
	public static String user="root";
	public static String password="123456";
	
	//���ݿ����ӷ������Զ��������ݿ�ͱ�
		public static Connection getConnection() {
			Connection con=null;
			Statement stat=null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url_1="jdbc:mysql://localhost:3306?characterEncoding=utf8&useSSL=false";
				String url_2="jdbc:mysql://localhost:3306/mydatabase?characterEncoding=utf8&useSSL=false";
				
                //��������
				con=DriverManager.getConnection(url_1,user,password);
                //�������ݿ�mydatabase
				stat=(Statement)con.createStatement();
				String sql_1="create database if not exists mydatabase;";
                //ִ����䴴�����ݿ�mydatabase
				stat.executeUpdate(sql_1);
                //�����µ�����������mydatabase�´���alluser���actionrecord��
				con=DriverManager.getConnection(url_2,user,password);
				String sql_2="create table if not exists alluser( name varchar(20), "
						+ "sex varchar(20), phone varchar(20), cardnumber varchar(40) primary key, "
						+ "password varchar(30),money int);";
				String sql_3="create table if not exists actionrecord( time DATETIME,"
						+ "cardnumber varchar(40),operation varchar(200),size varchar(30),"
						+ "foreign key(cardnumber) references alluser(cardnumber));";
				stat=(Statement)con.createStatement();
//				ִ����䴴����
				stat.executeUpdate(sql_2);
				stat.executeUpdate(sql_3);
				
			}catch (SQLException e) {
//				�����쳣������ӡ�쳣
//				e.printStackTrace();
				JOptionPane.showMessageDialog(
                        null,
                        "�û������������\n���������룡",
                        "����",
                        JOptionPane.WARNING_MESSAGE
                );
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
//			��������
			return con;
		}
		
//		����û�
		public static void addUser(JButton jbutton_1,JTextField jtextfield[]) {
			Connection con=null;
			Statement stat=null;
			String sql=null;
			try {
//				�������ݿ�����
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql="insert into alluser values('"+jtextfield[0].getText()+"','"
						+jtextfield[1].getText()+"',"+jtextfield[2].getText()+","
						+jtextfield[3].getText()+",'"+jtextfield[4].getText()+"',"
						+Integer.parseInt(jtextfield[5].getText())+");";
				/*
				* jtextfield[0]:����
				* jtextfield[1]:�Ա�
				* jtextfield[2]:�绰
				* jtextfield[3]:����
				* jtextfield[4]:����
				* jtextfield[5]:���
				* */
//				ִ�����
				stat.executeUpdate(sql);
//				��ʾ�ɹ�
				String length = jtextfield[3].getText();
				if(length.length()==9){
					//��ʾ�ɹ�
					JOptionPane.showMessageDialog(
							null,
							"����û���Ϣ�ɹ���",
							"��ʾ",
							JOptionPane.INFORMATION_MESSAGE
					);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				�����쳣������翨���ظ�������ӡ�쳣
//				e.printStackTrace();
				JOptionPane.showMessageDialog(
                        jbutton_1,
                        "���ſ����ظ���\n����û���Ϣʧ�ܣ�",
                        "����",
                        JOptionPane.WARNING_MESSAGE
                );
			}finally {
				try {
					if(stat!=null)
						stat.close();
					if(con!=null)
						con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		
//		�޸��û�
		public static void updateUser(JButton jbutton_3,JTextField jtextfield[]) {
			Connection con=null;
			Statement stat=null;
			String sql=null;
			try {
//				�������ݿ�����
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				//�ж��Ƿ�Ϊ��,��ֵ�����޸�
				if(!jtextfield[0].getText().equals("")) {
					sql="update alluser set name='"+jtextfield[0].getText()
							+"'"+" where cardnumber='"+jtextfield[3].getText()+"';";
					//ִ�����
					stat.executeUpdate(sql);
				}
				if(!jtextfield[1].getText().equals("")) {
					sql="update alluser set sex='"+jtextfield[1].getText()+"'"
							+" where cardnumber='"+jtextfield[3].getText()+"';";
					//ִ�����
					stat.executeUpdate(sql);
				}
				if(!jtextfield[2].getText().equals("")) {
					sql="update alluser set phone='"+jtextfield[2].getText()+"'"
							+" where cardnumber='"+jtextfield[3].getText()+"';";
					//ִ�����
					stat.executeUpdate(sql);
				}
				if(!jtextfield[4].getText().equals("")) {
					sql="update alluser set password='"+jtextfield[4].getText()+"'"
							+" where cardnumber='"+jtextfield[3].getText()+"';";
					//ִ�����
					stat.executeUpdate(sql);
				}
				if(!jtextfield[5].getText().equals("")) {
					sql="update alluser set money='"+jtextfield[5].getText()+"'"
							+" where cardnumber='"+jtextfield[3].getText()+"';";
					//ִ�����
					stat.executeUpdate(sql);
				}
//				��ʾ�ɹ�
				JOptionPane.showMessageDialog(
                        jbutton_3,
                        "�޸��û���Ϣ�ɹ���",
                        "��ʾ",
                        JOptionPane.INFORMATION_MESSAGE
                );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(stat!=null)
						stat.close();
					if(con!=null)
						con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
//		ɾ���û�
		public static void deleteUser(JButton jbutton_5,JTextField jtextfield_1) {
			Connection con=null;
			Statement stat=null;
			String sql_1=null;
			String sql_2=null;
			try {
//				�������ݿ�����
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
//				����actionrecord�������ο�alluser��Ҫ��ɾ�����������ɾ������
//				ɾ���û���ɾ��������Ϣ
				sql_1="delete from actionrecord where cardnumber ='"+jtextfield_1.getText()+"';";
				sql_2="delete from alluser where cardnumber ='"+jtextfield_1.getText()+"';";
//				ִ�����
				stat.executeUpdate(sql_1);
				stat.executeUpdate(sql_2);
//				��ʾ�ɹ�
				JOptionPane.showMessageDialog(
                        jbutton_5,
                        "ɾ���û���Ϣ�ɹ���",
                        "��ʾ",
                        JOptionPane.INFORMATION_MESSAGE
                );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(stat!=null)
						stat.close();
					if(con!=null)
						con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
//		��ѯ�û�
		public static void selectUser(JButton jbutton_8,JTextField jtextfield[]) {
			Connection con=null;
			Statement stat=null;
			ResultSet rs=null;
			String sql=null;
			try {
//				�������ݿ�����
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql="select * from alluser where cardnumber ='"+jtextfield[3].getText()+"';";
//				ִ�����
				rs = stat.executeQuery(sql);
//				�����������ı�����
				rs.next();
				jtextfield[0].setText(rs.getString(1));
				jtextfield[1].setText(rs.getString(2));
				jtextfield[2].setText(rs.getString(3));
				jtextfield[4].setText(rs.getString(5));
				jtextfield[5].setText(rs.getString(6));
//				��ʾ�ɹ�
				JOptionPane.showMessageDialog(
                        jbutton_8,
                        "��ѯѧ����Ϣ�ɹ���",
                        "��ʾ",
                        JOptionPane.INFORMATION_MESSAGE
                );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				δ��ѯ����ǰ�û��������쳣�������쳣������ӡ�쳣
//				e.printStackTrace();
				JOptionPane.showMessageDialog(
                        jbutton_8,
                        "δ��ѯ����ǰ�û���Ϣ��",
                        "����",
                        JOptionPane.WARNING_MESSAGE
                );
			}finally {
				try {
					if(rs!=null)
						rs.close();
					if(stat!=null)
						stat.close();
					if(con!=null)
						con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
//		���ȫ��
		public static void showUser(JTextArea jtextArea) {
			Connection con=null;
			Statement stat=null;
			ResultSet rs=null;
			String sql=null;
			try {
//				�������ݿ�����
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql="select * from alluser ;";
//				ִ�����
				rs = stat.executeQuery(sql);
//				�û���Ϣ
				String userInformation[]= {"�� ����","�� ��","�� ����","�� �ţ�","�� �룺","��"};
//				���֮ǰ������ı���
				jtextArea.setText("");
				//����
				Font font=new Font("����",Font.BOLD,20);
				jtextArea.setFont(font);
//				�����������ı�����
				while(rs.next()) {
					jtextArea.append(userInformation[0]+rs.getString(1)+"  ||  ");
					jtextArea.append(userInformation[1]+rs.getString(2)+"  ||  ");
					jtextArea.append(userInformation[2]+rs.getString(3)+"  ||  ");
					jtextArea.append(userInformation[3]+rs.getString(4)+"  ||  ");
					jtextArea.append(userInformation[4]+rs.getString(5)+"  ||  ");
					jtextArea.append(userInformation[5]+rs.getString(6)+"\n");
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(rs!=null)
						rs.close();
					if(stat!=null)
						stat.close();
					if(con!=null)
						con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
//		�ָ��ߣ�����Ϊ�û���������ݿⷽ��
//		*********************************************************************************************************************
//		*********************************************************************************************************************
//		����˻�
		public static boolean selectUserText(String user) {
			Connection con=null;
			Statement stat=null;
			ResultSet rs=null;
			String sql=null;
			boolean mark=false;//��¼���жϱ�־
			try {
//				�������ݿ�����
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql="select * from alluser where cardnumber ='"+user+"';";
//				ִ�����
				rs = stat.executeQuery(sql);
//				������ڵ�ǰ�û�����rs.next()Ϊtrue
				mark = rs.next();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(rs!=null)
						rs.close();
					if(stat!=null)
						stat.close();
					if(con!=null)
						con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			return mark;
		}
		
//		�������
		public static String selectPasswordText(JButton jbutton_2) {
			Connection con=null;
			Statement stat=null;
			ResultSet rs=null;
			String sql=null;
			String password=null;
			try {
//				�������ݿ�����
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql="select password from alluser where cardnumber ='"+AdministratorUI.jtext.getText()+"';";
//				ִ�����
				rs = stat.executeQuery(sql);
				rs.next();
//				�������
				password = rs.getString(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(rs!=null)
						rs.close();
					if(stat!=null)
						stat.close();
					if(con!=null)
						con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
//			��������
			return password;
		}
		
//		��ѯ���
		public static int selectMoney(String cardnumber) {
			Connection con=null;
			Statement stat=null;
			ResultSet rs=null;
			String sql=null;
			int money = 0;
			try {
//				�������ݿ�����
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql="select money from alluser where cardnumber ='"+cardnumber+"';";
//				ִ�����
				rs = stat.executeQuery(sql);
				rs.next();
				money = Integer.parseInt(rs.getString(1));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(rs!=null)
						rs.close();
					if(stat!=null)
						stat.close();
					if(con!=null)
						con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			return money;
		}
		
//		ȡ�� �����ݿⷽ��
		public static void withdraw(int money_1,int money_2) {
			Connection con=null;
			Statement stat=null;
			String sql_1=null;
			String sql_2=null;
			try {
//				�������ݿ�����
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql_1="update alluser set money="+money_2
						+" where cardnumber='"+AdministratorUI.jtext.getText()+"';";
//				��¼ȡ������
				sql_2="insert into actionrecord VALUES (NOW(),'"+AdministratorUI.jtext.getText()+"','ȡ��','"
						+money_1+"Ԫ');";
//				ִ�����
				stat.executeUpdate(sql_1);
				stat.executeUpdate(sql_2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(stat!=null)
						stat.close();
					if(con!=null)
						con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
//		��� �����ݿⷽ��
		public static void deposit(int money_1,int money_2) {
			Connection con=null;
			Statement stat=null;
			String sql_1=null;
			String sql_2=null;
			try {
//				�������ݿ�����
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql_1="update alluser set money="+money_2
						+" where cardnumber='"+AdministratorUI.jtext.getText()+"';";
//				��¼ȡ������
				sql_2="insert into actionrecord VALUES (NOW(),'"+AdministratorUI.jtext.getText()+"','���','"
						+money_1+"Ԫ');";
//				ִ�����
				stat.executeUpdate(sql_1);
				stat.executeUpdate(sql_2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(stat!=null)
						stat.close();
					if(con!=null)
						con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
//		�޸����� �����ݿⷽ��
		public static void changePassword(String password) {
			Connection con=null;
			Statement stat=null;
			String sql_1=null;
			String sql_2=null;
			try {
//				�������ݿ�����
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql_1="update alluser set password='"+password
						+"' where cardnumber='"+AdministratorUI.jtext.getText()+"';";
//				��¼����
				sql_2="insert into actionrecord VALUES (NOW(),'"+AdministratorUI.jtext.getText()+"','�޸�����','"
						+"�ɹ���');";
//				ִ�����
				stat.executeUpdate(sql_1);
				stat.executeUpdate(sql_2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(stat!=null)
						stat.close();
					if(con!=null)
						con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
//		ת�� �����ݿⷽ��
		public static void transfer(String cardnumber,int money_1,int money_2) {
			Connection con=null;
			Statement stat=null;
			String sql_1=null;
			String sql_2=null;
			String sql_3=null;
			String sql_4=null;
			try {
//				�������ݿ�����
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql_1="update alluser set money='"+money_2
						+"' where cardnumber='"+AdministratorUI.jtext.getText()+"';";
//				��¼����
				sql_2="insert into actionrecord VALUES (NOW(),'"+AdministratorUI.jtext.getText()+"','�򿨺ţ�"+cardnumber+" ת��','"
						+money_1+"Ԫ');";
//				����Ŀ���˻�������ת�������ܺ�
				money_2 = money_1+MysqlOperation.selectMoney(cardnumber);
				sql_3="update alluser set money='"+money_2
						+"' where cardnumber='"+cardnumber+"';";
//				��¼����
				sql_4="insert into actionrecord VALUES (NOW(),'"+cardnumber+"','�յ����ţ�"+AdministratorUI.jtext.getText()+" ת��','"
						+money_1+"Ԫ');";
//				ִ�����
				stat.executeUpdate(sql_1);
				stat.executeUpdate(sql_2);
				stat.executeUpdate(sql_3);
				stat.executeUpdate(sql_4);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(stat!=null)
						stat.close();
					if(con!=null)
						con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
//		��ѯ��ǰ�˻��Ĳ�����Ϣ
		public static String[][] showNowUser(JTextArea jtextArea) {
			Connection con=null;
			Statement stat_1=null;
			Statement stat_2=null;
			ResultSet rs_1=null;
			ResultSet rs_2=null;
			String sql_1=null;
			String sql_2=null;
			try {
//				�������ݿ�����
				con=MysqlOperation.getConnection();
				stat_1=(Statement)con.createStatement();
				stat_2=(Statement)con.createStatement();
//				��Ȼ���ӣ���ѯ���ű�
				sql_1="select alluser.cardnumber,alluser.`name`,alluser.sex,alluser.phone,alluser.money,"
						+ "actionrecord.time,actionrecord.operation,actionrecord.size"
						+ " from alluser NATURAL JOIN actionrecord"
						+ " where actionrecord.cardnumber='"+AdministratorUI.jtext.getText()+"';";
				sql_2="select count(cardnumber) from actionrecord "
						+ " where cardnumber='"+AdministratorUI.jtext.getText()+"';";
//				ִ�����
				rs_1 = stat_1.executeQuery(sql_1);
				rs_2 = stat_2.executeQuery(sql_2);
				rs_2.next();
//				��ʱ�洢���ݵĶ�ά����
				String rowData[][] = new String[Integer.parseInt(rs_2.getString(1))][100];
				int i=0;
				while(rs_1.next()) {
					for(int j=0;j<8;j++) {
						rowData[i][j] = new String(rs_1.getString(j+1));
//						System.out.println(rowData[i][j]);
					}
					i++;
				}
				return rowData;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(rs_1!=null)
						rs_1.close();
					if(rs_2!=null)
						rs_2.close();
					if(stat_1!=null)
						stat_1.close();
					if(stat_2!=null)
						stat_2.close();
					if(con!=null)
						con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			return null;
		}
		
//		�����û���Ϣ
		public static void saveUser() {
			Connection con=null;
			Statement stat=null;
			ResultSet rs=null;
			String sql=null;
			//�������
			FileOutputStream out=null;
			try {
//				�������ݿ�����
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
//				��Ȼ���ӣ���ѯ���ű�
				sql="select alluser.cardnumber,alluser.`name`,alluser.sex,alluser.phone,alluser.money,"
						+ "actionrecord.time,actionrecord.operation,actionrecord.size"
						+ " from alluser NATURAL JOIN actionrecord"
						+ " where actionrecord.cardnumber='"+AdministratorUI.jtext.getText()+"';";
//				ִ�����
				rs = stat.executeQuery(sql);
				//����·���ļ���,trueΪ ��ӷ�ʽ
				out =new FileOutputStream("D:\\userMessage.txt",true);
				String student=null;
				String explain="���α�����Ϣ���£�\n";
				byte buf[] =explain.getBytes();
				out.write(buf);
				while(rs.next()) {
					//����������,д������
					byte buffer[]=null;
					student ="���ţ�"+ rs.getString(1)+"  ||  "+"������"+rs.getString(2)
					+"  ||  "+"�Ա�"+rs.getString(3)+"  ||  "+"�绰��"+rs.getString(4)
					+"  ||  "+"��"+rs.getString(5)+"  ||  "+"ʱ�䣺"+rs.getString(6)
					+"  ||  "+"������"+rs.getString(7)+"  ||  "+"������"+rs.getString(8)
					+"\n";
					buffer =student.getBytes();
					out.write(buffer);
				}
				JOptionPane.showMessageDialog(null, "����ɹ�������\n����·��Ϊ��D:\\\\userMessage.txt",
						"��ʾ",JOptionPane.PLAIN_MESSAGE);
			}catch (Exception ex) {
				ex.printStackTrace();
			}finally {
				try {
					if(out!=null) 
					out.close();
				}
				catch(IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		
		
}
