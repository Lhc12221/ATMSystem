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
	
//数据库的用户名和密码
	public static String user="root";
	public static String password="123456";
	
	//数据库连接方法，自动创建数据库和表
		public static Connection getConnection() {
			Connection con=null;
			Statement stat=null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url_1="jdbc:mysql://localhost:3306?characterEncoding=utf8&useSSL=false";
				String url_2="jdbc:mysql://localhost:3306/mydatabase?characterEncoding=utf8&useSSL=false";
				
                //建立连接
				con=DriverManager.getConnection(url_1,user,password);
                //创建数据库mydatabase
				stat=(Statement)con.createStatement();
				String sql_1="create database if not exists mydatabase;";
                //执行语句创建数据库mydatabase
				stat.executeUpdate(sql_1);
                //建立新的连接用于在mydatabase下创建alluser表和actionrecord表
				con=DriverManager.getConnection(url_2,user,password);
				String sql_2="create table if not exists alluser( name varchar(20), "
						+ "sex varchar(20), phone varchar(20), cardnumber varchar(40) primary key, "
						+ "password varchar(30),money int);";
				String sql_3="create table if not exists actionrecord( time DATETIME,"
						+ "cardnumber varchar(40),operation varchar(200),size varchar(30),"
						+ "foreign key(cardnumber) references alluser(cardnumber));";
				stat=(Statement)con.createStatement();
//				执行语句创建表
				stat.executeUpdate(sql_2);
				stat.executeUpdate(sql_3);
				
			}catch (SQLException e) {
//				处理异常但不打印异常
//				e.printStackTrace();
				JOptionPane.showMessageDialog(
                        null,
                        "用户名或密码错误！\n请重新输入！",
                        "警告",
                        JOptionPane.WARNING_MESSAGE
                );
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
//			返回连接
			return con;
		}
		
//		添加用户
		public static void addUser(JButton jbutton_1,JTextField jtextfield[]) {
			Connection con=null;
			Statement stat=null;
			String sql=null;
			try {
//				建立数据库连接
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql="insert into alluser values('"+jtextfield[0].getText()+"','"
						+jtextfield[1].getText()+"',"+jtextfield[2].getText()+","
						+jtextfield[3].getText()+",'"+jtextfield[4].getText()+"',"
						+Integer.parseInt(jtextfield[5].getText())+");";
				/*
				* jtextfield[0]:姓名
				* jtextfield[1]:性别
				* jtextfield[2]:电话
				* jtextfield[3]:卡号
				* jtextfield[4]:密码
				* jtextfield[5]:余额
				* */
//				执行语句
				stat.executeUpdate(sql);
//				提示成功
				String length = jtextfield[3].getText();
				if(length.length()==9){
					//提示成功
					JOptionPane.showMessageDialog(
							null,
							"添加用户信息成功！",
							"提示",
							JOptionPane.INFORMATION_MESSAGE
					);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				处理异常情况，如卡号重复，不打印异常
//				e.printStackTrace();
				JOptionPane.showMessageDialog(
                        jbutton_1,
                        "卡号可能重复！\n添加用户信息失败！",
                        "警告",
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
		
//		修改用户
		public static void updateUser(JButton jbutton_3,JTextField jtextfield[]) {
			Connection con=null;
			Statement stat=null;
			String sql=null;
			try {
//				建立数据库连接
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				//判断是否为空,空值不用修改
				if(!jtextfield[0].getText().equals("")) {
					sql="update alluser set name='"+jtextfield[0].getText()
							+"'"+" where cardnumber='"+jtextfield[3].getText()+"';";
					//执行语句
					stat.executeUpdate(sql);
				}
				if(!jtextfield[1].getText().equals("")) {
					sql="update alluser set sex='"+jtextfield[1].getText()+"'"
							+" where cardnumber='"+jtextfield[3].getText()+"';";
					//执行语句
					stat.executeUpdate(sql);
				}
				if(!jtextfield[2].getText().equals("")) {
					sql="update alluser set phone='"+jtextfield[2].getText()+"'"
							+" where cardnumber='"+jtextfield[3].getText()+"';";
					//执行语句
					stat.executeUpdate(sql);
				}
				if(!jtextfield[4].getText().equals("")) {
					sql="update alluser set password='"+jtextfield[4].getText()+"'"
							+" where cardnumber='"+jtextfield[3].getText()+"';";
					//执行语句
					stat.executeUpdate(sql);
				}
				if(!jtextfield[5].getText().equals("")) {
					sql="update alluser set money='"+jtextfield[5].getText()+"'"
							+" where cardnumber='"+jtextfield[3].getText()+"';";
					//执行语句
					stat.executeUpdate(sql);
				}
//				提示成功
				JOptionPane.showMessageDialog(
                        jbutton_3,
                        "修改用户信息成功！",
                        "提示",
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
		
//		删除用户
		public static void deleteUser(JButton jbutton_5,JTextField jtextfield_1) {
			Connection con=null;
			Statement stat=null;
			String sql_1=null;
			String sql_2=null;
			try {
//				建立数据库连接
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
//				由于actionrecord表的外键参考alluser，要先删除外键，才能删除主键
//				删除用户会删除所有信息
				sql_1="delete from actionrecord where cardnumber ='"+jtextfield_1.getText()+"';";
				sql_2="delete from alluser where cardnumber ='"+jtextfield_1.getText()+"';";
//				执行语句
				stat.executeUpdate(sql_1);
				stat.executeUpdate(sql_2);
//				提示成功
				JOptionPane.showMessageDialog(
                        jbutton_5,
                        "删除用户信息成功！",
                        "提示",
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
		
//		查询用户
		public static void selectUser(JButton jbutton_8,JTextField jtextfield[]) {
			Connection con=null;
			Statement stat=null;
			ResultSet rs=null;
			String sql=null;
			try {
//				建立数据库连接
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql="select * from alluser where cardnumber ='"+jtextfield[3].getText()+"';";
//				执行语句
				rs = stat.executeQuery(sql);
//				将结果输出到文本框中
				rs.next();
				jtextfield[0].setText(rs.getString(1));
				jtextfield[1].setText(rs.getString(2));
				jtextfield[2].setText(rs.getString(3));
				jtextfield[4].setText(rs.getString(5));
				jtextfield[5].setText(rs.getString(6));
//				提示成功
				JOptionPane.showMessageDialog(
                        jbutton_8,
                        "查询学生信息成功！",
                        "提示",
                        JOptionPane.INFORMATION_MESSAGE
                );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				未查询到当前用户，出现异常，处理异常但不打印异常
//				e.printStackTrace();
				JOptionPane.showMessageDialog(
                        jbutton_8,
                        "未查询到当前用户信息！",
                        "警告",
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
		
//		输出全部
		public static void showUser(JTextArea jtextArea) {
			Connection con=null;
			Statement stat=null;
			ResultSet rs=null;
			String sql=null;
			try {
//				建立数据库连接
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql="select * from alluser ;";
//				执行语句
				rs = stat.executeQuery(sql);
//				用户信息
				String userInformation[]= {"姓 名：","性 别：","电 话：","卡 号：","密 码：","余额："};
//				输出之前先清空文本区
				jtextArea.setText("");
				//字体
				Font font=new Font("宋体",Font.BOLD,20);
				jtextArea.setFont(font);
//				将结果输出到文本区中
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
//		分割线，以下为用户界面的数据库方法
//		*********************************************************************************************************************
//		*********************************************************************************************************************
//		检查账户
		public static boolean selectUserText(String user) {
			Connection con=null;
			Statement stat=null;
			ResultSet rs=null;
			String sql=null;
			boolean mark=false;//登录的判断标志
			try {
//				建立数据库连接
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql="select * from alluser where cardnumber ='"+user+"';";
//				执行语句
				rs = stat.executeQuery(sql);
//				如果存在当前用户，则rs.next()为true
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
		
//		检查密码
		public static String selectPasswordText(JButton jbutton_2) {
			Connection con=null;
			Statement stat=null;
			ResultSet rs=null;
			String sql=null;
			String password=null;
			try {
//				建立数据库连接
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql="select password from alluser where cardnumber ='"+AdministratorUI.jtext.getText()+"';";
//				执行语句
				rs = stat.executeQuery(sql);
				rs.next();
//				获得密码
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
//			返回密码
			return password;
		}
		
//		查询金额
		public static int selectMoney(String cardnumber) {
			Connection con=null;
			Statement stat=null;
			ResultSet rs=null;
			String sql=null;
			int money = 0;
			try {
//				建立数据库连接
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql="select money from alluser where cardnumber ='"+cardnumber+"';";
//				执行语句
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
		
//		取款 的数据库方法
		public static void withdraw(int money_1,int money_2) {
			Connection con=null;
			Statement stat=null;
			String sql_1=null;
			String sql_2=null;
			try {
//				建立数据库连接
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql_1="update alluser set money="+money_2
						+" where cardnumber='"+AdministratorUI.jtext.getText()+"';";
//				记录取款数据
				sql_2="insert into actionrecord VALUES (NOW(),'"+AdministratorUI.jtext.getText()+"','取款','"
						+money_1+"元');";
//				执行语句
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
		
//		存款 的数据库方法
		public static void deposit(int money_1,int money_2) {
			Connection con=null;
			Statement stat=null;
			String sql_1=null;
			String sql_2=null;
			try {
//				建立数据库连接
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql_1="update alluser set money="+money_2
						+" where cardnumber='"+AdministratorUI.jtext.getText()+"';";
//				记录取款数据
				sql_2="insert into actionrecord VALUES (NOW(),'"+AdministratorUI.jtext.getText()+"','存款','"
						+money_1+"元');";
//				执行语句
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
		
//		修改密码 的数据库方法
		public static void changePassword(String password) {
			Connection con=null;
			Statement stat=null;
			String sql_1=null;
			String sql_2=null;
			try {
//				建立数据库连接
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql_1="update alluser set password='"+password
						+"' where cardnumber='"+AdministratorUI.jtext.getText()+"';";
//				记录数据
				sql_2="insert into actionrecord VALUES (NOW(),'"+AdministratorUI.jtext.getText()+"','修改密码','"
						+"成功！');";
//				执行语句
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
		
//		转账 的数据库方法
		public static void transfer(String cardnumber,int money_1,int money_2) {
			Connection con=null;
			Statement stat=null;
			String sql_1=null;
			String sql_2=null;
			String sql_3=null;
			String sql_4=null;
			try {
//				建立数据库连接
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql_1="update alluser set money='"+money_2
						+"' where cardnumber='"+AdministratorUI.jtext.getText()+"';";
//				记录数据
				sql_2="insert into actionrecord VALUES (NOW(),'"+AdministratorUI.jtext.getText()+"','向卡号："+cardnumber+" 转账','"
						+money_1+"元');";
//				计算目标账户的余额和转入余额的总和
				money_2 = money_1+MysqlOperation.selectMoney(cardnumber);
				sql_3="update alluser set money='"+money_2
						+"' where cardnumber='"+cardnumber+"';";
//				记录数据
				sql_4="insert into actionrecord VALUES (NOW(),'"+cardnumber+"','收到卡号："+AdministratorUI.jtext.getText()+" 转入','"
						+money_1+"元');";
//				执行语句
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
		
//		查询当前账户的操作信息
		public static String[][] showNowUser(JTextArea jtextArea) {
			Connection con=null;
			Statement stat_1=null;
			Statement stat_2=null;
			ResultSet rs_1=null;
			ResultSet rs_2=null;
			String sql_1=null;
			String sql_2=null;
			try {
//				建立数据库连接
				con=MysqlOperation.getConnection();
				stat_1=(Statement)con.createStatement();
				stat_2=(Statement)con.createStatement();
//				自然连接，查询两张表
				sql_1="select alluser.cardnumber,alluser.`name`,alluser.sex,alluser.phone,alluser.money,"
						+ "actionrecord.time,actionrecord.operation,actionrecord.size"
						+ " from alluser NATURAL JOIN actionrecord"
						+ " where actionrecord.cardnumber='"+AdministratorUI.jtext.getText()+"';";
				sql_2="select count(cardnumber) from actionrecord "
						+ " where cardnumber='"+AdministratorUI.jtext.getText()+"';";
//				执行语句
				rs_1 = stat_1.executeQuery(sql_1);
				rs_2 = stat_2.executeQuery(sql_2);
				rs_2.next();
//				暂时存储数据的二维数组
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
		
//		保存用户信息
		public static void saveUser() {
			Connection con=null;
			Statement stat=null;
			ResultSet rs=null;
			String sql=null;
			//建立输出
			FileOutputStream out=null;
			try {
//				建立数据库连接
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
//				自然连接，查询两张表
				sql="select alluser.cardnumber,alluser.`name`,alluser.sex,alluser.phone,alluser.money,"
						+ "actionrecord.time,actionrecord.operation,actionrecord.size"
						+ " from alluser NATURAL JOIN actionrecord"
						+ " where actionrecord.cardnumber='"+AdministratorUI.jtext.getText()+"';";
//				执行语句
				rs = stat.executeQuery(sql);
				//设置路径文件名,true为 添加方式
				out =new FileOutputStream("D:\\userMessage.txt",true);
				String student=null;
				String explain="本次保存信息如下：\n";
				byte buf[] =explain.getBytes();
				out.write(buf);
				while(rs.next()) {
					//创建缓冲区,写入数据
					byte buffer[]=null;
					student ="卡号："+ rs.getString(1)+"  ||  "+"姓名："+rs.getString(2)
					+"  ||  "+"性别："+rs.getString(3)+"  ||  "+"电话："+rs.getString(4)
					+"  ||  "+"余额："+rs.getString(5)+"  ||  "+"时间："+rs.getString(6)
					+"  ||  "+"操作："+rs.getString(7)+"  ||  "+"数量："+rs.getString(8)
					+"\n";
					buffer =student.getBytes();
					out.write(buffer);
				}
				JOptionPane.showMessageDialog(null, "保存成功！！！\n保存路径为：D:\\\\userMessage.txt",
						"提示",JOptionPane.PLAIN_MESSAGE);
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
