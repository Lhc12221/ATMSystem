package atmsystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;


public class UserUI {
    //���崰��
	public static JFrame jframe_3=new JFrame();
    //�����ı���������ʾ��Ϣ
	public static JTextArea jtextArea_1=new JTextArea();
	
    //�û�������
	public static void init() {
        //���ô�����
		jframe_3.setTitle("�û�����");
        //���ڴ�С
		jframe_3.setSize(1000,800);
        //�ղ���
		jframe_3.setLayout(null);
        //���ڲ��ɵ���
		jframe_3.setResizable(false);
        //�رմ������˳�����
		jframe_3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//����ͼ��
		Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\mazhi\\Desktop\\ATM\\ATMSystem\\image\\1.png");
		jframe_3.setIconImage(icon);
		
        //�����ı������ܱ༭
		jtextArea_1.setEditable(false);
		jtextArea_1.setLineWrap(true);        //�����Զ����й��� 
		jtextArea_1.setWrapStyleWord(true);   // ������в����ֹ���
		//��ǩ
		JLabel jlabel=new JLabel("��  �ţ�" );
		String str = AdministratorUI.jtext.getText();
		JTextArea jTextArea = new JTextArea(str);
		jTextArea.setBackground(Color.LIGHT_GRAY);
		jTextArea.setEditable(false);
        //��jtextArea_1��Ϊ�ɹ������jsp����ʾ����
		JScrollPane jsp=new JScrollPane(jtextArea_1);
		jsp.setBounds(150,80,700,600);
		jframe_3.add(jsp);
		
        //��ť��
		String jbuttonName[] = {"ȡ��","���","��ѯ","��ȫ�˳�","�޸�����","ת��","��ѯ��¼","������¼"};
        //���尴ť
		JButton jbutton[]=new JButton[jbuttonName.length];
        //ʵ����
		for(int i=0;i<jbuttonName.length;i++) {
			jbutton[i]=new JButton(jbuttonName[i]);
		}

		Font font=new Font("����",Font.BOLD,25);
		Font font1=new Font("����",Font.BOLD,32);
		//���������С
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

        //���ð�ť�ͱ�ǩ�Ĵ�С��λ��
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
        //����ť��ӵ�����
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
		
        //ע�����а�ť�ļ���
        //ȡ�ť
		jbutton[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				@SuppressWarnings("unused")
				int money_1 = 0;//ȡ����
				int money_2 = 0;//���
				@SuppressWarnings("unused")
				String str = null;
                //������ʾ����
				str = JOptionPane.showInputDialog(
						null,
						"������ȡ���",
						"��ʾ",
						JOptionPane.QUESTION_MESSAGE);		//����Ի���
                //���strΪ����˵���û�ȡ��ȡ������˷���
				if(str==null) {
					return ;
				}
				money_1=Integer.parseInt(str);
				money_2=MysqlOperation.selectMoney(AdministratorUI.jtext.getText());
                //�жϽ���Ƿ�淶
				if(money_1 % 100 == 0&&money_1 <=5000) {
                    //�ж�����Ƿ��㹻
					if(money_1<money_2) {
						money_2 = money_2-money_1;
                        //ȡ��������ݿⷽ��
						MysqlOperation.withdraw(money_1,money_2);
                        //���ı��������Ϣ
						UserUI.jtextArea_1.append("���׼�¼��"+UserUI.time()+"  ���ţ�"+AdministratorUI.jtext.getText()
						+"  ȡ��"+money_1+"Ԫ\n");
						jtextArea_1.setFont(font);
                        //��ʾ
						JOptionPane.showMessageDialog(
		                        null,
		                        "ȡ��"+money_1+"Ԫ�ɹ���",
		                        "��ʾ",
		                        JOptionPane.INFORMATION_MESSAGE
		                );
					}else {
                        //��ʾ����
						JOptionPane.showMessageDialog(
		                        null,
		                        "�������㣡",
		                        "����",
		                        JOptionPane.WARNING_MESSAGE
		                );
					}
				}else {
                    //��ʾ
					JOptionPane.showMessageDialog(
	                        null,
	                        "ȡ����ҪΪ100�ı������ܶ����5000Ԫ��",
	                        "����",
	                        JOptionPane.WARNING_MESSAGE
	                );
				}
			}
		});
        //��ť
		jbutton[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				@SuppressWarnings("unused")
				int money_1 = 0;//�����
				int money_2 = 0;//���
				@SuppressWarnings("unused")
				String str = null;
                //������ʾ����
				str = JOptionPane.showInputDialog(null,"���������",
						"��ʾ",JOptionPane.QUESTION_MESSAGE);		//����Ի���
//				���strΪ����˵���û�ȡ���������˷���
				if(str==null) {
					return ;
				}
				money_1=Integer.parseInt(str);
				money_2=MysqlOperation.selectMoney(AdministratorUI.jtext.getText());
//				�жϽ���Ƿ�淶
				if(money_1 > 0&&money_1 % 100 == 0) {
					money_2 = money_2+money_1;
//					���������ݿⷽ��
					MysqlOperation.deposit(money_1,money_2);
//					���ı��������Ϣ
					UserUI.jtextArea_1.append("���׼�¼��"+UserUI.time()+"  ���ţ�"+AdministratorUI.jtext.getText()
					+"  ���"+money_1+"Ԫ\n");
					jtextArea_1.setFont(font);
//					��ʾ
					JOptionPane.showMessageDialog(
		                       null,
		                       "���"+money_1+"Ԫ�ɹ���",
		                       "��ʾ",
		                       JOptionPane.INFORMATION_MESSAGE
		                );
				}else {
//					��ʾ
					JOptionPane.showMessageDialog(
	                        null,
	                        "���������0��Ϊ100����������",
	                        "����",
	                        JOptionPane.WARNING_MESSAGE
	                );
				}
			}
		});
//		��ѯ��ť
		jbutton[2].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				��ʾ

			}
		});
//		��ȫ�˳���ť
		jbutton[3].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				��ʾ
				JOptionPane.showMessageDialog(
	                       null,
	                       "��л����ʹ�ã�\n�ټ���",
	                       "��ʾ",
	                       JOptionPane.INFORMATION_MESSAGE
	                );
//				�˳�����
				System.exit(0);
			}
		});
//		�޸����밴ť
		jbutton[4].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				��ʱ�洢����
				String password_1=null;
				String password_2=null;
				int i=1;
//				��ʾע�⵱ǰ����
				JOptionPane.showMessageDialog(
	                       null,
	                       "��ȷ����ǰ������ȫ���޸����룡",
	                       "����",
	                       JOptionPane.WARNING_MESSAGE
	                );
//				��ʾ�����µ�����
				password_1=JOptionPane.showInputDialog(null,"�����������룺",
						"��ʾ",JOptionPane.QUESTION_MESSAGE);		//����Ի���
//				����û�ȡ�����������
				if(password_1==null) {
					return;
				}
//				��������Ƿ�淶
				char first=password_1.charAt(0);
				if(password_1.length()>=6) {
					for(i=1;i<password_1.length();i++) {
						if(password_1.charAt(i)!=first) {
//							����淶,����ʾȷ������
							password_2=JOptionPane.showInputDialog(null,"��ȷ�����룺",
									"��ʾ",JOptionPane.QUESTION_MESSAGE);		//����Ի���
//							����û�ȡ�����������
							if(password_2==null) {
								return;
							}
							break;
						}
					}
//					������������λ��ȫ��ͬ����ʾ���벻�淶
					if(i==password_1.length()) {
						JOptionPane.showMessageDialog(
			                       null,
			                       "���벻��ÿһλ����ͬ��\n�޸�����ʧ�ܣ�",
			                       "��ʾ",
			                       JOptionPane.WARNING_MESSAGE
			                );
//						���ı��������Ϣ
						UserUI.jtextArea_1.append("���׼�¼��"+UserUI.time()+"  ���ţ�"+AdministratorUI.jtext.getText()
						+" �޸�����ʧ�ܣ�\n");
//						��������
						return;
					}
					
				}else {
//					��ʾ���벻��6λ
					JOptionPane.showMessageDialog(
		                       null,
		                       "���벻��6λ��\n�޸�ʧ�ܣ�",
		                       "����",
		                       JOptionPane.WARNING_MESSAGE
		                );
//					���ı��������Ϣ
					UserUI.jtextArea_1.append("���׼�¼��"+UserUI.time()+"  ���ţ�"+AdministratorUI.jtext.getText()
					+" �޸�����ʧ�ܣ�\n");
					jtextArea_1.setFont(font);
//					��������
					return;
				}
//				�ж���������������Ƿ�һ��
				if(password_1.equals(password_2)) {
//					�������ݿⷽ���޸�����
					MysqlOperation.changePassword(password_2);
//					��ʾ�޸�����ɹ�
					JOptionPane.showMessageDialog(
		                       null,
		                       "�޸�����ɹ���",
		                       "��ʾ",
		                       JOptionPane.INFORMATION_MESSAGE
		                );
//					���ı��������Ϣ
					UserUI.jtextArea_1.append("���׼�¼��"+UserUI.time()+"  ���ţ�"+AdministratorUI.jtext.getText()
					+" �޸�����ɹ���\n");
					jtextArea_1.setFont(font);
				}else {
					JOptionPane.showMessageDialog(
		                       null,
		                       "�������벻һ�£�\n�޸�����ʧ�ܣ�",
		                       "��ʾ",
		                       JOptionPane.WARNING_MESSAGE
		                );
				}
			}
		});
//		ת�˰�ť
		jbutton[5].addActionListener(new ActionListener() {
			
			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String cardnumber=null;//�洢Ŀ�꿨��
				int money_1 = 0;//�洢ת�˽��
				int money_2 = MysqlOperation.selectMoney(AdministratorUI.jtext.getText());//�洢��ǰ�˻����
//				��ʾ����Ŀ�꿨��
				cardnumber=JOptionPane.showInputDialog(null,"������Ŀ�꿨�ţ�",
						"��ʾ",JOptionPane.QUESTION_MESSAGE);		//����Ի���
//				����û�ȡ�����������
				if(cardnumber==null) {
					return;
				}
//				�ж��˻��Ƿ�Ϊ�Լ�
				if(cardnumber.equals(AdministratorUI.jtext.getText())) {
//					��ʾ�������Լ�ת��
					JOptionPane.showMessageDialog(
		                       null,
		                       "�������Լ�ת�ˣ�\nת��ʧ�ܣ�",
		                       "��ʾ",
		                       JOptionPane.WARNING_MESSAGE
		                );
//					��������
					return;
				}
//				��鵱ǰ�˻��Ƿ����,�������Լ�ת��
				if(MysqlOperation.selectUserText(cardnumber)) {
//					���ڵ�ǰ�˻�������ʾ����ת�˽��
					String str=JOptionPane.showInputDialog(null,"������ת�˽�",
							"��ʾ",JOptionPane.QUESTION_MESSAGE);	//����Ի���
//					����û�ȡ�����������
					if(str==null) {
						return;
					}
					money_1 = Integer.parseInt(str);
//					��鵱ǰ�˻�����Ƿ��㹻
					if(money_1>MysqlOperation.selectMoney(AdministratorUI.jtext.getText())) {
//						��ʾ����
						JOptionPane.showMessageDialog(
			                       null,
			                       "���㣡\nת��ʧ�ܣ�",
			                       "��ʾ",
			                       JOptionPane.WARNING_MESSAGE
			                );
//						���ı��������Ϣ
						UserUI.jtextArea_1.append("���׼�¼��"+UserUI.time()+"  ���ţ�"+AdministratorUI.jtext.getText()
						+"  ת��"+money_1+"Ԫʧ�ܣ�\n");
						jtextArea_1.setFont(font);
//						��������
						return;
					}else if(money_1 % 100==0&&money_1<=5000){
//						�������ݿⷽ������Ŀ���˻�ת��
						money_2 = money_2 - money_1;
						MysqlOperation.transfer(cardnumber, money_1,money_2);
//						��ʾת�˳ɹ�
						JOptionPane.showMessageDialog(
			                       null,
			                       "ת�˳ɹ���",
			                       "��ʾ",
			                       JOptionPane.INFORMATION_MESSAGE
			                );
//						���ı��������Ϣ
						UserUI.jtextArea_1.append("���׼�¼��"+UserUI.time()+"  ���ţ�"+AdministratorUI.jtext.getText()
						+"  ת��"+money_1+"Ԫ�ɹ���\n");
						jtextArea_1.setFont(font);
					}else {
//						��ʾת�˽��ҪΪ100�ı����Ҳ��ܴ���5000
						JOptionPane.showMessageDialog(
			                       null,
			                       "ת�˽��ҪΪ100�ı����Ҳ��ܴ���5000��\nת��ʧ�ܣ�",
			                       "��ʾ",
			                       JOptionPane.WARNING_MESSAGE
			                );
//						���ı��������Ϣ
						UserUI.jtextArea_1.append("���׼�¼��"+UserUI.time()+"  ���ţ�"+AdministratorUI.jtext.getText()
						+"  ת��"+money_1+"Ԫʧ�ܣ�\n");
						jtextArea_1.setFont(font);
					}
				}else {
//					��ʾ�����ڵ�ǰ�û�
					JOptionPane.showMessageDialog(
							null,
	                        "δ��ѯ����ǰ�û���Ϣ��\nת��ʧ�ܣ�",
	                        "����",
	                        JOptionPane.WARNING_MESSAGE
	                );
				}
			}
		});
//		��ѯ��¼
		jbutton[6].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				UserUI.showNowUser();
			}
		});
//		������¼
		jbutton[7].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MysqlOperation.saveUser();
			}
		});
//		���ھ���
		jframe_3.setLocationRelativeTo(null);
//		������ʾ
		jframe_3.setVisible(true);
	}
	
//	���ʱ��
	public static String time() {
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
		String nowTime=dateTime.format(formatter);
		return nowTime;
	}
	
//	��ѯ��Ϣ����
	public static void showNowUser() {
		
		JFrame jf = new JFrame("ȫ����Ϣ");
//		�ر������ز��ͷŵ�ǰ����
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // �����������
        JPanel panel = new JPanel();

        // ��ͷ��������
        String columnNames[] = {"����", "����", "�Ա�", "�绰", "���", "ʱ��","����","����"};

//         �������������
//		�������ݿⷽ����ʾȫ����Ϣ
        String rowData[][]=MysqlOperation.showNowUser(jtextArea_1);

        // ����һ�����ָ�� ��ͷ �� ����������
        JTable table = new JTable(rowData, columnNames);

        // ���ñ��������ɫ
        table.setForeground(Color.BLACK);                   // ������ɫ
        table.setFont(new Font(null, Font.PLAIN, 14));      // ������ʽ
        table.setSelectionForeground(Color.DARK_GRAY);      // ѡ�к�������ɫ
        table.setSelectionBackground(Color.LIGHT_GRAY);     // ѡ�к����屳��
        table.setGridColor(Color.GRAY);                     // ������ɫ

        // ���ñ�ͷ
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // ���ñ�ͷ����������ʽ
        table.getTableHeader().setForeground(Color.RED);                // ���ñ�ͷ����������ɫ
        table.getTableHeader().setResizingAllowed(false);               // ���ò������ֶ��ı��п�
        table.getTableHeader().setReorderingAllowed(false);             // ���ò������϶������������

        // �����и�
        table.setRowHeight(30);

        // ��һ���п�����Ϊ130
        table.getColumnModel().getColumn(0).setPreferredWidth(130);
        table.getColumnModel().getColumn(1).setPreferredWidth(50);
        table.getColumnModel().getColumn(2).setPreferredWidth(20);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getColumnModel().getColumn(4).setPreferredWidth(80);
        table.getColumnModel().getColumn(5).setPreferredWidth(160);
        table.getColumnModel().getColumn(6).setPreferredWidth(220);
        table.getColumnModel().getColumn(7).setPreferredWidth(80);

        // ���ù�������ӿڴ�С�������ô�С�������ݣ���Ҫ�϶����������ܿ�����
        table.setPreferredScrollableViewportSize(new Dimension(1000, 500));

        // �� ��� �ŵ� ������� �У���ͷ���Զ���ӵ�������嶥����
        JScrollPane scrollPane = new JScrollPane(table);

        // ��� ������� �� �������
        panel.add(scrollPane);

        // ���� ������� �� ����
        jf.setContentPane(panel);

        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
	}
	
}
