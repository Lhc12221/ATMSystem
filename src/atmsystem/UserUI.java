package atmsystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;


public class UserUI {
    //定义窗口
	public static JFrame jframe_3=new JFrame();
    //定义文本区用于显示信息
	public static JTextArea jtextArea_1=new JTextArea();
	
    //用户主界面
	public static void init() {
        //设置窗口名
		jframe_3.setTitle("用户界面");
        //窗口大小
		jframe_3.setSize(1000,800);
        //空布局
		jframe_3.setLayout(null);
        //窗口不可调整
		jframe_3.setResizable(false);
        //关闭窗口则退出程序
		jframe_3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置图标
		Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\mazhi\\Desktop\\ATM\\ATMSystem\\image\\1.png");
		jframe_3.setIconImage(icon);
		
        //设置文本区不能编辑
		jtextArea_1.setEditable(false);
		jtextArea_1.setLineWrap(true);        //激活自动换行功能 
		jtextArea_1.setWrapStyleWord(true);   // 激活断行不断字功能
		//标签
		JLabel jlabel=new JLabel("卡  号：" );
		String str = AdministratorUI.jtext.getText();
		JTextArea jTextArea = new JTextArea(str);
		jTextArea.setBackground(Color.LIGHT_GRAY);
		jTextArea.setEditable(false);
        //将jtextArea_1作为可滚动面板jsp的显示区域
		JScrollPane jsp=new JScrollPane(jtextArea_1);
		jsp.setBounds(150,80,700,600);
		jframe_3.add(jsp);
		
        //按钮名
		String jbuttonName[] = {"取款","存款","查询","安全退出","修改密码","转账","查询记录","导出记录"};
        //定义按钮
		JButton jbutton[]=new JButton[jbuttonName.length];
        //实例化
		for(int i=0;i<jbuttonName.length;i++) {
			jbutton[i]=new JButton(jbuttonName[i]);
		}

		Font font=new Font("宋体",Font.BOLD,25);
		Font font1=new Font("宋体",Font.BOLD,32);
		//设置字体大小
		jbutton[0].setFont(font);
		jbutton[1].setFont(font);
		jbutton[2].setFont(font);
		jbutton[3].setFont(font);
		jbutton[4].setFont(font);
		jbutton[5].setFont(font);
		jbutton[6].setFont(font);
		jbutton[7].setFont(font);
		jlabel.setFont(font1);
		jTextArea.setFont(font1);

        //设置按钮和标签的大小和位置
		jbutton[0].setBounds(0,100,150,55);
		jbutton[1].setBounds(0,250,150,55);
		jbutton[2].setBounds(0,400,150,55);
		jbutton[3].setBounds(0,550,150,55);
		jbutton[4].setBounds(850,100,150,55);
		jbutton[5].setBounds(850,250,150,55);
		jbutton[6].setBounds(850,400,150,55);
		jbutton[7].setBounds(850,550,150,55);
		jlabel.setBounds(250,20,150,30);
		jTextArea.setBounds(410,20,200,30);
        //将按钮添加到窗口
		jframe_3.add(jbutton[0]);
		jframe_3.add(jbutton[1]);
		jframe_3.add(jbutton[2]);
		jframe_3.add(jbutton[3]);
		jframe_3.add(jbutton[4]);
		jframe_3.add(jbutton[5]);
		jframe_3.add(jbutton[6]);
		jframe_3.add(jbutton[7]);
		jframe_3.add(jlabel);
		jframe_3.add(jTextArea);
		
        //注册所有按钮的监听
        //取款按钮
		jbutton[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				@SuppressWarnings("unused")
				int money_1 = 0;//取款金额
				int money_2 = 0;//余额
				@SuppressWarnings("unused")
				String str = null;
                //弹出提示界面
				str = JOptionPane.showInputDialog(
						null,
						"请输入取款金额：",
						"提示",
						JOptionPane.QUESTION_MESSAGE);		//输入对话框
                //如果str为空则说明用户取消取款，结束此方法
				if(str==null) {
					return ;
				}
				money_1=Integer.parseInt(str);
				money_2=MysqlOperation.selectMoney(AdministratorUI.jtext.getText());
                //判断金额是否规范
				if(money_1 % 100 == 0&&money_1 <=5000) {
                    //判断余额是否足够
					if(money_1<money_2) {
						money_2 = money_2-money_1;
                        //取款，调用数据库方法
						MysqlOperation.withdraw(money_1,money_2);
                        //向文本区添加信息
						UserUI.jtextArea_1.append("交易记录："+UserUI.time()+"  卡号："+AdministratorUI.jtext.getText()
						+"  取款"+money_1+"元\n");
						jtextArea_1.setFont(font);
                        //提示
						JOptionPane.showMessageDialog(
		                        null,
		                        "取款"+money_1+"元成功！",
		                        "提示",
		                        JOptionPane.INFORMATION_MESSAGE
		                );
					}else {
                        //提示余额不足
						JOptionPane.showMessageDialog(
		                        null,
		                        "您的余额不足！",
		                        "警告",
		                        JOptionPane.WARNING_MESSAGE
		                );
					}
				}else {
                    //提示
					JOptionPane.showMessageDialog(
	                        null,
	                        "取款金额要为100的倍数且总额不超过5000元！",
	                        "警告",
	                        JOptionPane.WARNING_MESSAGE
	                );
				}
			}
		});
        //存款按钮
		jbutton[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				@SuppressWarnings("unused")
				int money_1 = 0;//存款金额
				int money_2 = 0;//余额
				@SuppressWarnings("unused")
				String str = null;
                //弹出提示界面
				str = JOptionPane.showInputDialog(null,"请输入存款金额：",
						"提示",JOptionPane.QUESTION_MESSAGE);		//输入对话框
//				如果str为空则说明用户取消存款，结束此方法
				if(str==null) {
					return ;
				}
				money_1=Integer.parseInt(str);
				money_2=MysqlOperation.selectMoney(AdministratorUI.jtext.getText());
//				判断金额是否规范
				if(money_1 > 0&&money_1 % 100 == 0) {
					money_2 = money_2+money_1;
//					存款，调用数据库方法
					MysqlOperation.deposit(money_1,money_2);
//					向文本区添加信息
					UserUI.jtextArea_1.append("交易记录："+UserUI.time()+"  卡号："+AdministratorUI.jtext.getText()
					+"  存款"+money_1+"元\n");
					jtextArea_1.setFont(font);
//					提示
					JOptionPane.showMessageDialog(
		                       null,
		                       "存款"+money_1+"元成功！",
		                       "提示",
		                       JOptionPane.INFORMATION_MESSAGE
		                );
				}else {
//					提示
					JOptionPane.showMessageDialog(
	                        null,
	                        "金额必须大于0且为100的整数倍！",
	                        "警告",
	                        JOptionPane.WARNING_MESSAGE
	                );
				}
			}
		});
//		查询按钮
		jbutton[2].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				提示

			}
		});
//		安全退出按钮
		jbutton[3].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				提示
				JOptionPane.showMessageDialog(
	                       null,
	                       "感谢您的使用！\n再见！",
	                       "提示",
	                       JOptionPane.INFORMATION_MESSAGE
	                );
//				退出程序
				System.exit(0);
			}
		});
//		修改密码按钮
		jbutton[4].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				暂时存储密码
				String password_1=null;
				String password_2=null;
				int i=1;
//				提示注意当前环境
				JOptionPane.showMessageDialog(
	                       null,
	                       "请确保当前环境安全再修改密码！",
	                       "警告",
	                       JOptionPane.WARNING_MESSAGE
	                );
//				提示输入新的密码
				password_1=JOptionPane.showInputDialog(null,"请输入新密码：",
						"提示",JOptionPane.QUESTION_MESSAGE);		//输入对话框
//				如果用户取消则结束方法
				if(password_1==null) {
					return;
				}
//				检查密码是否规范
				char first=password_1.charAt(0);
				if(password_1.length()>=6) {
					for(i=1;i<password_1.length();i++) {
						if(password_1.charAt(i)!=first) {
//							密码规范,则提示确认密码
							password_2=JOptionPane.showInputDialog(null,"请确认密码：",
									"提示",JOptionPane.QUESTION_MESSAGE);		//输入对话框
//							如果用户取消则结束方法
							if(password_2==null) {
								return;
							}
							break;
						}
					}
//					如果密码的所有位完全相同，提示密码不规范
					if(i==password_1.length()) {
						JOptionPane.showMessageDialog(
			                       null,
			                       "密码不能每一位都相同！\n修改密码失败！",
			                       "提示",
			                       JOptionPane.WARNING_MESSAGE
			                );
//						向文本区添加信息
						UserUI.jtextArea_1.append("交易记录："+UserUI.time()+"  卡号："+AdministratorUI.jtext.getText()
						+" 修改密码失败！\n");
//						结束程序
						return;
					}
					
				}else {
//					提示密码不足6位
					JOptionPane.showMessageDialog(
		                       null,
		                       "密码不足6位！\n修改失败！",
		                       "警告",
		                       JOptionPane.WARNING_MESSAGE
		                );
//					向文本区添加信息
					UserUI.jtextArea_1.append("交易记录："+UserUI.time()+"  卡号："+AdministratorUI.jtext.getText()
					+" 修改密码失败！\n");
					jtextArea_1.setFont(font);
//					结束方法
					return;
				}
//				判断两次密码的输入是否一致
				if(password_1.equals(password_2)) {
//					调用数据库方法修改密码
					MysqlOperation.changePassword(password_2);
//					提示修改密码成功
					JOptionPane.showMessageDialog(
		                       null,
		                       "修改密码成功！",
		                       "提示",
		                       JOptionPane.INFORMATION_MESSAGE
		                );
//					向文本区添加信息
					UserUI.jtextArea_1.append("交易记录："+UserUI.time()+"  卡号："+AdministratorUI.jtext.getText()
					+" 修改密码成功！\n");
					jtextArea_1.setFont(font);
				}else {
					JOptionPane.showMessageDialog(
		                       null,
		                       "两次密码不一致！\n修改密码失败！",
		                       "提示",
		                       JOptionPane.WARNING_MESSAGE
		                );
				}
			}
		});
//		转账按钮
		jbutton[5].addActionListener(new ActionListener() {
			
			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String cardnumber=null;//存储目标卡号
				int money_1 = 0;//存储转账金额
				int money_2 = MysqlOperation.selectMoney(AdministratorUI.jtext.getText());//存储当前账户余额
//				提示输入目标卡号
				cardnumber=JOptionPane.showInputDialog(null,"请输入目标卡号：",
						"提示",JOptionPane.QUESTION_MESSAGE);		//输入对话框
//				如果用户取消则结束方法
				if(cardnumber==null) {
					return;
				}
//				判断账户是否为自己
				if(cardnumber.equals(AdministratorUI.jtext.getText())) {
//					提示不能向自己转账
					JOptionPane.showMessageDialog(
		                       null,
		                       "不能向自己转账！\n转账失败！",
		                       "提示",
		                       JOptionPane.WARNING_MESSAGE
		                );
//					结束函数
					return;
				}
//				检查当前账户是否存在,不能向自己转账
				if(MysqlOperation.selectUserText(cardnumber)) {
//					存在当前账户，则提示输入转账金额
					String str=JOptionPane.showInputDialog(null,"请输入转账金额：",
							"提示",JOptionPane.QUESTION_MESSAGE);	//输入对话框
//					如果用户取消则结束方法
					if(str==null) {
						return;
					}
					money_1 = Integer.parseInt(str);
//					检查当前账户余额是否足够
					if(money_1>MysqlOperation.selectMoney(AdministratorUI.jtext.getText())) {
//						提示余额不足
						JOptionPane.showMessageDialog(
			                       null,
			                       "余额不足！\n转账失败！",
			                       "提示",
			                       JOptionPane.WARNING_MESSAGE
			                );
//						向文本区添加信息
						UserUI.jtextArea_1.append("交易记录："+UserUI.time()+"  卡号："+AdministratorUI.jtext.getText()
						+"  转账"+money_1+"元失败！\n");
						jtextArea_1.setFont(font);
//						结束方法
						return;
					}else if(money_1 % 100==0&&money_1<=5000){
//						调用数据库方法，向目标账户转账
						money_2 = money_2 - money_1;
						MysqlOperation.transfer(cardnumber, money_1,money_2);
//						提示转账成功
						JOptionPane.showMessageDialog(
			                       null,
			                       "转账成功！",
			                       "提示",
			                       JOptionPane.INFORMATION_MESSAGE
			                );
//						向文本区添加信息
						UserUI.jtextArea_1.append("交易记录："+UserUI.time()+"  卡号："+AdministratorUI.jtext.getText()
						+"  转账"+money_1+"元成功！\n");
						jtextArea_1.setFont(font);
					}else {
//						提示转账金额要为100的倍数且不能大于5000
						JOptionPane.showMessageDialog(
			                       null,
			                       "转账金额要为100的倍数且不能大于5000！\n转账失败！",
			                       "提示",
			                       JOptionPane.WARNING_MESSAGE
			                );
//						向文本区添加信息
						UserUI.jtextArea_1.append("交易记录："+UserUI.time()+"  卡号："+AdministratorUI.jtext.getText()
						+"  转账"+money_1+"元失败！\n");
						jtextArea_1.setFont(font);
					}
				}else {
//					提示不存在当前用户
					JOptionPane.showMessageDialog(
							null,
	                        "未查询到当前用户信息！\n转账失败！",
	                        "警告",
	                        JOptionPane.WARNING_MESSAGE
	                );
				}
			}
		});
//		查询记录
		jbutton[6].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				UserUI.showNowUser();
			}
		});
//		导出记录
		jbutton[7].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MysqlOperation.saveUser();
			}
		});
//		窗口居中
		jframe_3.setLocationRelativeTo(null);
//		窗口显示
		jframe_3.setVisible(true);
	}
	
//	获得时间
	public static String time() {
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
		String nowTime=dateTime.format(formatter);
		return nowTime;
	}
	
//	查询信息界面
	public static void showNowUser() {
		
		JFrame jf = new JFrame("全部信息");
//		关闭则隐藏并释放当前窗口
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // 创建内容面板
        JPanel panel = new JPanel();

        // 表头（列名）
        String columnNames[] = {"卡号", "姓名", "性别", "电话", "余额", "时间","操作","数量"};

//         表格所有行数据
//		调用数据库方法显示全部信息
        String rowData[][]=MysqlOperation.showNowUser(jtextArea_1);

        // 创建一个表格，指定 表头 和 所有行数据
        JTable table = new JTable(rowData, columnNames);

        // 设置表格内容颜色
        table.setForeground(Color.BLACK);                   // 字体颜色
        table.setFont(new Font(null, Font.PLAIN, 14));      // 字体样式
        table.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        table.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        table.setGridColor(Color.GRAY);                     // 网格颜色

        // 设置表头
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // 设置表头名称字体样式
        table.getTableHeader().setForeground(Color.RED);                // 设置表头名称字体颜色
        table.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        table.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列

        // 设置行高
        table.setRowHeight(30);

        // 第一列列宽设置为130
        table.getColumnModel().getColumn(0).setPreferredWidth(130);
        table.getColumnModel().getColumn(1).setPreferredWidth(50);
        table.getColumnModel().getColumn(2).setPreferredWidth(20);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getColumnModel().getColumn(4).setPreferredWidth(80);
        table.getColumnModel().getColumn(5).setPreferredWidth(160);
        table.getColumnModel().getColumn(6).setPreferredWidth(220);
        table.getColumnModel().getColumn(7).setPreferredWidth(80);

        // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
        table.setPreferredScrollableViewportSize(new Dimension(1000, 500));

        // 把 表格 放到 滚动面板 中（表头将自动添加到滚动面板顶部）
        JScrollPane scrollPane = new JScrollPane(table);

        // 添加 滚动面板 到 内容面板
        panel.add(scrollPane);

        // 设置 内容面板 到 窗口
        jf.setContentPane(panel);

        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
	}
	
}
