package atmsystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class AdministratorUI {
    //定义容器
	public static JFrame jframe_1=new JFrame("登录");
	public static JFrame jframe_2=new JFrame("管理员界面");
    //登录界面的文本框和密码框
	public static JTextField jtext=new JTextField(15);
	public static JPasswordField jpassword=new JPasswordField(15);
    //定义面板
	public static JPanel jpanel_1=new JPanel(new FlowLayout());//流式布局
	public static JPanel jpanel_2=new JPanel(null);//空布局
    //定义文本区用于显示信息
	public static JTextArea jtextArea=new JTextArea();
	

	
//	登录界面
	public static void init() {
        //窗口大小
		jframe_1.setSize(760,700);
        //布局
		//jframe_1.setLayout(new FlowLayout(FlowLayout.CENTER,360,30));
		jframe_1.setLayout(null);
        //设置背景
		String path = "C:\\Users\\mazhi\\Desktop\\ATM\\ATMSystem\\image\\2.png";
		ImageIcon background = new ImageIcon(path);
        //把背景图片显示在一个标签里面
		JLabel label = new JLabel(background);
        //把标签的大小位置设置为图片刚好填充整个面板
		label.setBounds(0, 0,jframe_1.getWidth(), jframe_1.getHeight());
        //把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		JPanel imagePanel = (JPanel) jframe_1.getContentPane();
		imagePanel.setOpaque(false);
        //把背景图片添加到分层窗格的最底层作为背景
		jframe_1.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        //窗口不可调整
		jframe_1.setResizable(false);
        //关闭窗口则退出程序
		jframe_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置图标
		Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\mazhi\\Desktop\\ATM\\ATMSystem\\image\\1.png");
		jframe_1.setIconImage(icon);
        //标签
		JLabel jlabel=new JLabel("  ATM模拟程序  ");
		JLabel jlabel_1=new JLabel("卡  号：");
		JLabel jlabel_2=new JLabel("密  码：");
        //设置密码框显示为*
		jpassword.setEchoChar('*');
        //字体
		Font font=new Font("宋体",Font.BOLD,32);
		Font font_1=new Font("宋体",Font.BOLD,25);
		Font font_2=new Font("宋体",Font.BOLD,22);
		jlabel.setFont(font);
		jlabel_1.setFont(font_1);
		jlabel_2.setFont(font_1);
		jtext.setFont(font_2);
		jpassword.setFont(font_2);
		//定义按钮
		JButton jbutton_1=new JButton("管理员登录");
		JButton jbutton_2=new JButton("用户登录");
		jbutton_1.setFont(font_1);
		jbutton_2.setFont(font_1);
        //加入容器
		jframe_1.add(jlabel);
		jframe_1.add(jlabel_1);
		jframe_1.add(jtext);
		jframe_1.add(jlabel_2);
		jframe_1.add(jpassword);
		jframe_1.add(jbutton_1);
		jframe_1.add(jbutton_2);
		jlabel.setBounds(265,80,500,50);
		jlabel_1.setBounds(165,160,300,30);
		jtext.setBounds(265,160,300,50);
		jlabel_2.setBounds(165,320,300,30);
		jpassword.setBounds(265,320,300,50);
		jbutton_1.setBounds(140,480,200,30);
		jbutton_2.setBounds(390,480,200,30);
		
        //注册 管理员登录界面 的监听
		jbutton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//点击了管理员登录界面
                //检查卡号和密码
                //将卡号和密码复制给MysqlOperation下的user和password
				MysqlOperation.user=jtext.getText();
				//jpassword.getPassword()获得的数据为char类型，需要转换为String
				String password=new String(jpassword.getPassword());
				MysqlOperation.password=password;
                //判断卡号和密码是否正确
                //如果卡号或密码错误，则会在MysqlOperation中的getConnection()方法下出现异常
				if(MysqlOperation.getConnection()!=null) {
					JOptionPane.showMessageDialog(
	                        null,
	                        "登录成功!\n欢迎使用ATM管理员系统！",
	                        "提示",
	                        JOptionPane.INFORMATION_MESSAGE
	                );
                    //结束登录界面
					jframe_1.dispose();
                    //初始化管理员界面
					AdministratorUI.init_1();
				}
			}
		});
        //注册 用户登录界面 的监听
		jbutton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String password=new String(jpassword.getPassword());//暂时存储密码
                //判断是否存在当前用户以及密码是否正确
				if(MysqlOperation.selectUserText(jtext.getText())) {
					if(password.equals(MysqlOperation.selectPasswordText(jbutton_2))) {
						JOptionPane.showMessageDialog(
		                        null,
		                        "登录成功!\n欢迎使用ATM模拟程序！",
		                        "提示",
		                        JOptionPane.INFORMATION_MESSAGE
		                );
                        //结束登录界面
						jframe_1.dispose();
                        //初始化用户界面
						UserUI.init();
					}else {
                        //提示登录失败
						JOptionPane.showMessageDialog(
		                        null,
		                        "密码错误！\n请重新输入！",
		                        "警告",
		                        JOptionPane.WARNING_MESSAGE//图标解释
		                );
					}
				}else {
                    //提示登录失败
					JOptionPane.showMessageDialog(
	                        null,
	                        "不存在当前用户！\n请联系管理员注册！",
	                        "警告",
	                        JOptionPane.WARNING_MESSAGE
	                );
				}

			}
		});
        //窗口居中
		jframe_1.setLocationRelativeTo(null);
        //窗口显示
		jframe_1.setVisible(true);
	
	}
	
//管理员界面
	public static void init_1 () {
        //窗口大小
		jframe_2.setSize(810,600);
        //空布局
		jframe_2.setLayout(null);
        //窗口不可调整
		jframe_2.setResizable(false);
        //关闭窗口则退出程序
		jframe_2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //菜单栏
		JMenuBar bar=new JMenuBar();
        //一级菜单
		JMenu men_1=new JMenu("基本操作");
		JMenu men_2=new JMenu("返回操作");
        //子菜单
		JMenuItem item_1=new JMenuItem("添加用户");
		JMenuItem item_2=new JMenuItem("修改用户");
		JMenuItem item_3=new JMenuItem("删除用户");
		JMenuItem item_4=new JMenuItem("查询用户");
		JMenuItem item_5=new JMenuItem("回到登录");
		
        //定义字体
		Font font=new Font("黑体",Font.PLAIN,15);
		
        //设置菜单字体
		men_1.setFont(font);
		men_2.setFont(font);
		item_1.setFont(font);
		item_2.setFont(font);
		item_3.setFont(font);
		item_4.setFont(font);
		item_5.setFont(font);
        //加入
		men_1.add(item_1);
		men_1.add(item_2);
		men_1.add(item_3);
		men_1.add(item_4);
		men_2.add(item_5);
		bar.add(men_1);
		bar.add(men_2);
		//创建的菜单栏添加到主窗口jframe_2中
		jframe_2.setJMenuBar(bar);
	
        //设置面板位置、大小、颜色
		jpanel_1.setBounds(0,0,200,600);
		jpanel_2.setBounds(210,0,600,600);
		jpanel_1.setBackground(Color.LIGHT_GRAY);
		jpanel_2.setBackground(Color.LIGHT_GRAY);
		jframe_2.add(jpanel_1);
		jframe_2.add(jpanel_2);
		
		//设置文本区不能编辑
		jtextArea.setEditable(false);
		//将jtextArea作为可滚动面板jsp的显示区域
		JScrollPane jsp=new JScrollPane(jtextArea);
		jsp.setSize(585,340);
		jpanel_2.add(jsp);
		
        //窗口居中
		jframe_2.setLocationRelativeTo(null);
        //窗口显示
		jframe_2.setVisible(true);
		
//注册 添加用户 按钮的监听
		item_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
                //调用方法
				AdministratorUI.init_2();
			}
		});
//注册 修改用户 按钮的监听
		item_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AdministratorUI.init_3();
			}
		});
//注册 删除用户 按钮的监听
		item_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AdministratorUI.init_4();
			}
		});
//注册 查询用户 按钮的监听
		item_4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AdministratorUI.init_5();
			}
		});
//注册 回到登录 按钮的监听
		item_5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
                //结束当前界面
				jframe_2.dispose();
                //返回登录界面
				jframe_1.setVisible(true);
                //清空文本框
				jtext.setText("");
				jpassword.setText("");
			}
		});
	}
	
//添加 用户界面
	public static void init_2() {
        //清空组件
		jpanel_1.removeAll();
        //刷新面板
		jpanel_1.updateUI();
        //用户信息
		String userInformation[]= {"姓 名：","性 别：","电 话：","卡 号：","密 码：","余额："};
        //标签
		JLabel jlabel[]=new JLabel[userInformation.length];
        //文本框
		JTextField jtextfield[]=new JTextField[userInformation.length];
        //实例化
		for(int i=0;i<userInformation.length;i++) {
			jlabel[i]=new JLabel(userInformation[i]);
			jpanel_1.add(jlabel[i]);
			jtextfield[i]=new JTextField(12);
			jpanel_1.add(jtextfield[i]);
		}
        //按钮
		JButton jbutton_1=new JButton("提交");
		JButton jbutton_2=new JButton("清除");
        //加入按钮
		jpanel_1.add(jbutton_1);
		jpanel_1.add(jbutton_2);
//注册 提交按钮 的监听
		jbutton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
                //调用数据库方法，添加学生
				MysqlOperation.addUser(jbutton_1, jtextfield);
			}
		});
//注册 清除按钮 的监听
		jbutton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
                //清空文本框
				for(int i=0;i<jtextfield.length;i++) {
					jtextfield[i].setText("");
				}
			}
		});
	}
	
//修改 用户界面
	public static void init_3() {
        //清空组件
		jpanel_1.removeAll();
        //刷新面板
		jpanel_1.updateUI();
        //字体
		Font font=new Font("黑体",Font.PLAIN,15);
		//用户信息
		String userInformation[]= {"姓 名：","性 别：","电 话：","卡 号：","密 码：","余额："};
		//标签
		JLabel jlabel[]=new JLabel[userInformation.length];
        //文本框
		JTextField jtextfield[]=new JTextField[userInformation.length];
        //按钮
		JButton jbutton_3=new JButton("修改");
		JButton jbutton_4=new JButton("清除");
		jbutton_3.setFont(font);
		jbutton_4.setFont(font);
		
        //实例化
		for(int i=0;i<userInformation.length;i++) {
			jlabel[i]=new JLabel(userInformation[i]);
			jtextfield[i]=new JTextField(12);
		}
        //加入组件
		jpanel_1.add(jlabel[3]);
		jpanel_1.add(jtextfield[3]);
		jpanel_1.add(jbutton_3);
		jpanel_1.add(jbutton_4);
		JLabel L_1=new JLabel("卡号不能被修改!");
		JLabel L_2=new JLabel("请在下面输入更新的信息：");
		JLabel L_3=new JLabel("不用的更新属性的不要填！！！");
		jpanel_1.add(L_1);
		jpanel_1.add(L_2);
		jpanel_1.add(L_3);
		jpanel_1.add(jlabel[0]);
		jpanel_1.add(jtextfield[0]);
		jpanel_1.add(jlabel[1]);
		jpanel_1.add(jtextfield[1]);
		jpanel_1.add(jlabel[2]);
		jpanel_1.add(jtextfield[2]);
		jpanel_1.add(jlabel[4]);
		jpanel_1.add(jtextfield[4]);
		jpanel_1.add(jlabel[5]);
		jpanel_1.add(jtextfield[5]);
//注册 修改按钮 的监听
		jbutton_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
                //调用数据库方法
				MysqlOperation.updateUser(jbutton_3, jtextfield);
			}
		});
        //注册 清除按钮 的监听
		jbutton_4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jtextfield[3].setText("");
			}
		});
	}
	
//删除 用户界面
	public static void init_4() {
        //清空组件
		jpanel_1.removeAll();
        //刷新面板
		jpanel_1.updateUI();
        //字体
		Font font=new Font("黑体",Font.PLAIN,15);
        //标签
		JLabel jlabel_1=new JLabel("卡 号：");
        //文本框
		JTextField jtextfield_1=new JTextField(10);
        //添加到面板
		jpanel_1.add(jlabel_1);
		jpanel_1.add(jtextfield_1);
		
        //按钮
		JButton jbutton_5=new JButton("删除");
		JButton jbutton_6=new JButton("清除");
		jbutton_5.setFont(font);
		jbutton_6.setFont(font);
		jpanel_1.add(jbutton_5);
		jpanel_1.add(jbutton_6);
        //注册 删除按钮 的监听
		jbutton_5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
            //确认对话框
			if(JOptionPane.showConfirmDialog(null,"用户的所有信息都将被删除！\n确认删除吗？","提示",
					JOptionPane.OK_OPTION,JOptionPane.QUESTION_MESSAGE)==0) {
                //确认删除，则调用数据库方法
				MysqlOperation.deleteUser(jbutton_5, jtextfield_1);
				}else {
					JOptionPane.showMessageDialog(
	                        null,
	                        "用户取消！\n未删除当前用户！",
	                        "警告",
	                        JOptionPane.WARNING_MESSAGE
	                );
				}
			}
		});
//注册 清除按钮 的监听
		jbutton_6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jtextfield_1.setText("");
			}
		});
	}
	
//查询 学生界面
	public static void init_5() {
        //清空组件
		jpanel_1.removeAll();
        //刷新面板
		jpanel_1.updateUI();
        //字体
		Font font=new Font("黑体",Font.PLAIN,15);
        //用户信息
		String userInformation[]= {"姓 名：","性 别：","电 话：","卡 号：","密 码：","余额："};
        //标签
		JLabel jlabel[]=new JLabel[userInformation.length];
        //文本框
		JTextField jtextfield[]=new JTextField[userInformation.length];
        //按钮
		JButton jbutton_8=new JButton("查询");
		JButton jbutton_9=new JButton("清除");
		JButton jbutton_10=new JButton("输出全部");
		jbutton_8.setFont(font);
		jbutton_9.setFont(font);
		jbutton_10.setFont(font);
		
        //实例化
		for(int i=0;i<userInformation.length;i++) {
			jlabel[i]=new JLabel(userInformation[i]);
            //jlabel[i].setFont(font);
			jtextfield[i]=new JTextField(12);
            //设置文本框不可编辑
			if(i!=3) {
				jtextfield[i].setEditable(false);
			}
		}
		jpanel_1.add(jlabel[3]);
		jpanel_1.add(jtextfield[3]);
		jpanel_1.add(jbutton_8);
		jpanel_1.add(jbutton_9);
		JLabel L_1=new JLabel("    查询结果如下：    ");
		L_1.setFont(font);
		jpanel_1.add(L_1);
		jpanel_1.add(jlabel[0]);
		jpanel_1.add(jtextfield[0]);
		jpanel_1.add(jlabel[1]);
		jpanel_1.add(jtextfield[1]);
		jpanel_1.add(jlabel[2]);
		jpanel_1.add(jtextfield[2]);
		jpanel_1.add(jlabel[4]);
		jpanel_1.add(jtextfield[4]);
		jpanel_1.add(jlabel[5]);
		jpanel_1.add(jtextfield[5]);
		jpanel_1.add(jbutton_10);
        //注册 查询按钮 的监听
		jbutton_8.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
                //调用数据库方法
				MysqlOperation.selectUser(jbutton_8, jtextfield);
			}
		});
        //注册 清除按钮 的监听
		jbutton_9.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jtextfield[3].setText("");
			}
		});
        //注册 输出全部按钮 的监听
		jbutton_10.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//调用数据库方法
				MysqlOperation.showUser(jtextArea);
			}
		});
	}
}
