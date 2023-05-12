package atmsystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class AdministratorUI {
    //��������
	public static JFrame jframe_1=new JFrame("��¼");
	public static JFrame jframe_2=new JFrame("����Ա����");
    //��¼������ı���������
	public static JTextField jtext=new JTextField(15);
	public static JPasswordField jpassword=new JPasswordField(15);
    //�������
	public static JPanel jpanel_1=new JPanel(new FlowLayout());//��ʽ����
	public static JPanel jpanel_2=new JPanel(null);//�ղ���
    //�����ı���������ʾ��Ϣ
	public static JTextArea jtextArea=new JTextArea();
	

	
//	��¼����
	public static void init() {
        //���ڴ�С
		jframe_1.setSize(760,700);
        //����
		//jframe_1.setLayout(new FlowLayout(FlowLayout.CENTER,360,30));
		jframe_1.setLayout(null);
        //���ñ���
		String path = "C:\\Users\\mazhi\\Desktop\\ATM\\ATMSystem\\image\\2.png";
		ImageIcon background = new ImageIcon(path);
        //�ѱ���ͼƬ��ʾ��һ����ǩ����
		JLabel label = new JLabel(background);
        //�ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������
		label.setBounds(0, 0,jframe_1.getWidth(), jframe_1.getHeight());
        //�����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��
		JPanel imagePanel = (JPanel) jframe_1.getContentPane();
		imagePanel.setOpaque(false);
        //�ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����
		jframe_1.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        //���ڲ��ɵ���
		jframe_1.setResizable(false);
        //�رմ������˳�����
		jframe_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//����ͼ��
		Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\mazhi\\Desktop\\ATM\\ATMSystem\\image\\1.png");
		jframe_1.setIconImage(icon);
        //��ǩ
		JLabel jlabel=new JLabel("  ATMģ�����  ");
		JLabel jlabel_1=new JLabel("��  �ţ�");
		JLabel jlabel_2=new JLabel("��  �룺");
        //�����������ʾΪ*
		jpassword.setEchoChar('*');
        //����
		Font font=new Font("����",Font.BOLD,32);
		Font font_1=new Font("����",Font.BOLD,25);
		Font font_2=new Font("����",Font.BOLD,22);
		jlabel.setFont(font);
		jlabel_1.setFont(font_1);
		jlabel_2.setFont(font_1);
		jtext.setFont(font_2);
		jpassword.setFont(font_2);
		//���尴ť
		JButton jbutton_1=new JButton("����Ա��¼");
		JButton jbutton_2=new JButton("�û���¼");
		jbutton_1.setFont(font_1);
		jbutton_2.setFont(font_1);
        //��������
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
		
        //ע�� ����Ա��¼���� �ļ���
		jbutton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//����˹���Ա��¼����
                //��鿨�ź�����
                //�����ź����븴�Ƹ�MysqlOperation�µ�user��password
				MysqlOperation.user=jtext.getText();
				//jpassword.getPassword()��õ�����Ϊchar���ͣ���Ҫת��ΪString
				String password=new String(jpassword.getPassword());
				MysqlOperation.password=password;
                //�жϿ��ź������Ƿ���ȷ
                //������Ż�������������MysqlOperation�е�getConnection()�����³����쳣
				if(MysqlOperation.getConnection()!=null) {
					JOptionPane.showMessageDialog(
	                        null,
	                        "��¼�ɹ�!\n��ӭʹ��ATM����Աϵͳ��",
	                        "��ʾ",
	                        JOptionPane.INFORMATION_MESSAGE
	                );
                    //������¼����
					jframe_1.dispose();
                    //��ʼ������Ա����
					AdministratorUI.init_1();
				}
			}
		});
        //ע�� �û���¼���� �ļ���
		jbutton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String password=new String(jpassword.getPassword());//��ʱ�洢����
                //�ж��Ƿ���ڵ�ǰ�û��Լ������Ƿ���ȷ
				if(MysqlOperation.selectUserText(jtext.getText())) {
					if(password.equals(MysqlOperation.selectPasswordText(jbutton_2))) {
						JOptionPane.showMessageDialog(
		                        null,
		                        "��¼�ɹ�!\n��ӭʹ��ATMģ�����",
		                        "��ʾ",
		                        JOptionPane.INFORMATION_MESSAGE
		                );
                        //������¼����
						jframe_1.dispose();
                        //��ʼ���û�����
						UserUI.init();
					}else {
                        //��ʾ��¼ʧ��
						JOptionPane.showMessageDialog(
		                        null,
		                        "�������\n���������룡",
		                        "����",
		                        JOptionPane.WARNING_MESSAGE//ͼ�����
		                );
					}
				}else {
                    //��ʾ��¼ʧ��
					JOptionPane.showMessageDialog(
	                        null,
	                        "�����ڵ�ǰ�û���\n����ϵ����Աע�ᣡ",
	                        "����",
	                        JOptionPane.WARNING_MESSAGE
	                );
				}

			}
		});
        //���ھ���
		jframe_1.setLocationRelativeTo(null);
        //������ʾ
		jframe_1.setVisible(true);
	
	}
	
//����Ա����
	public static void init_1 () {
        //���ڴ�С
		jframe_2.setSize(810,600);
        //�ղ���
		jframe_2.setLayout(null);
        //���ڲ��ɵ���
		jframe_2.setResizable(false);
        //�رմ������˳�����
		jframe_2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //�˵���
		JMenuBar bar=new JMenuBar();
        //һ���˵�
		JMenu men_1=new JMenu("��������");
		JMenu men_2=new JMenu("���ز���");
        //�Ӳ˵�
		JMenuItem item_1=new JMenuItem("����û�");
		JMenuItem item_2=new JMenuItem("�޸��û�");
		JMenuItem item_3=new JMenuItem("ɾ���û�");
		JMenuItem item_4=new JMenuItem("��ѯ�û�");
		JMenuItem item_5=new JMenuItem("�ص���¼");
		
        //��������
		Font font=new Font("����",Font.PLAIN,15);
		
        //���ò˵�����
		men_1.setFont(font);
		men_2.setFont(font);
		item_1.setFont(font);
		item_2.setFont(font);
		item_3.setFont(font);
		item_4.setFont(font);
		item_5.setFont(font);
        //����
		men_1.add(item_1);
		men_1.add(item_2);
		men_1.add(item_3);
		men_1.add(item_4);
		men_2.add(item_5);
		bar.add(men_1);
		bar.add(men_2);
		//�����Ĳ˵�����ӵ�������jframe_2��
		jframe_2.setJMenuBar(bar);
	
        //�������λ�á���С����ɫ
		jpanel_1.setBounds(0,0,200,600);
		jpanel_2.setBounds(210,0,600,600);
		jpanel_1.setBackground(Color.LIGHT_GRAY);
		jpanel_2.setBackground(Color.LIGHT_GRAY);
		jframe_2.add(jpanel_1);
		jframe_2.add(jpanel_2);
		
		//�����ı������ܱ༭
		jtextArea.setEditable(false);
		//��jtextArea��Ϊ�ɹ������jsp����ʾ����
		JScrollPane jsp=new JScrollPane(jtextArea);
		jsp.setSize(585,340);
		jpanel_2.add(jsp);
		
        //���ھ���
		jframe_2.setLocationRelativeTo(null);
        //������ʾ
		jframe_2.setVisible(true);
		
//ע�� ����û� ��ť�ļ���
		item_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
                //���÷���
				AdministratorUI.init_2();
			}
		});
//ע�� �޸��û� ��ť�ļ���
		item_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AdministratorUI.init_3();
			}
		});
//ע�� ɾ���û� ��ť�ļ���
		item_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AdministratorUI.init_4();
			}
		});
//ע�� ��ѯ�û� ��ť�ļ���
		item_4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AdministratorUI.init_5();
			}
		});
//ע�� �ص���¼ ��ť�ļ���
		item_5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
                //������ǰ����
				jframe_2.dispose();
                //���ص�¼����
				jframe_1.setVisible(true);
                //����ı���
				jtext.setText("");
				jpassword.setText("");
			}
		});
	}
	
//��� �û�����
	public static void init_2() {
        //������
		jpanel_1.removeAll();
        //ˢ�����
		jpanel_1.updateUI();
        //�û���Ϣ
		String userInformation[]= {"�� ����","�� ��","�� ����","�� �ţ�","�� �룺","��"};
        //��ǩ
		JLabel jlabel[]=new JLabel[userInformation.length];
        //�ı���
		JTextField jtextfield[]=new JTextField[userInformation.length];
        //ʵ����
		for(int i=0;i<userInformation.length;i++) {
			jlabel[i]=new JLabel(userInformation[i]);
			jpanel_1.add(jlabel[i]);
			jtextfield[i]=new JTextField(12);
			jpanel_1.add(jtextfield[i]);
		}
        //��ť
		JButton jbutton_1=new JButton("�ύ");
		JButton jbutton_2=new JButton("���");
        //���밴ť
		jpanel_1.add(jbutton_1);
		jpanel_1.add(jbutton_2);
//ע�� �ύ��ť �ļ���
		jbutton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
                //�������ݿⷽ�������ѧ��
				MysqlOperation.addUser(jbutton_1, jtextfield);
			}
		});
//ע�� �����ť �ļ���
		jbutton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
                //����ı���
				for(int i=0;i<jtextfield.length;i++) {
					jtextfield[i].setText("");
				}
			}
		});
	}
	
//�޸� �û�����
	public static void init_3() {
        //������
		jpanel_1.removeAll();
        //ˢ�����
		jpanel_1.updateUI();
        //����
		Font font=new Font("����",Font.PLAIN,15);
		//�û���Ϣ
		String userInformation[]= {"�� ����","�� ��","�� ����","�� �ţ�","�� �룺","��"};
		//��ǩ
		JLabel jlabel[]=new JLabel[userInformation.length];
        //�ı���
		JTextField jtextfield[]=new JTextField[userInformation.length];
        //��ť
		JButton jbutton_3=new JButton("�޸�");
		JButton jbutton_4=new JButton("���");
		jbutton_3.setFont(font);
		jbutton_4.setFont(font);
		
        //ʵ����
		for(int i=0;i<userInformation.length;i++) {
			jlabel[i]=new JLabel(userInformation[i]);
			jtextfield[i]=new JTextField(12);
		}
        //�������
		jpanel_1.add(jlabel[3]);
		jpanel_1.add(jtextfield[3]);
		jpanel_1.add(jbutton_3);
		jpanel_1.add(jbutton_4);
		JLabel L_1=new JLabel("���Ų��ܱ��޸�!");
		JLabel L_2=new JLabel("��������������µ���Ϣ��");
		JLabel L_3=new JLabel("���õĸ������ԵĲ�Ҫ�����");
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
//ע�� �޸İ�ť �ļ���
		jbutton_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
                //�������ݿⷽ��
				MysqlOperation.updateUser(jbutton_3, jtextfield);
			}
		});
        //ע�� �����ť �ļ���
		jbutton_4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jtextfield[3].setText("");
			}
		});
	}
	
//ɾ�� �û�����
	public static void init_4() {
        //������
		jpanel_1.removeAll();
        //ˢ�����
		jpanel_1.updateUI();
        //����
		Font font=new Font("����",Font.PLAIN,15);
        //��ǩ
		JLabel jlabel_1=new JLabel("�� �ţ�");
        //�ı���
		JTextField jtextfield_1=new JTextField(10);
        //��ӵ����
		jpanel_1.add(jlabel_1);
		jpanel_1.add(jtextfield_1);
		
        //��ť
		JButton jbutton_5=new JButton("ɾ��");
		JButton jbutton_6=new JButton("���");
		jbutton_5.setFont(font);
		jbutton_6.setFont(font);
		jpanel_1.add(jbutton_5);
		jpanel_1.add(jbutton_6);
        //ע�� ɾ����ť �ļ���
		jbutton_5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
            //ȷ�϶Ի���
			if(JOptionPane.showConfirmDialog(null,"�û���������Ϣ������ɾ����\nȷ��ɾ����","��ʾ",
					JOptionPane.OK_OPTION,JOptionPane.QUESTION_MESSAGE)==0) {
                //ȷ��ɾ������������ݿⷽ��
				MysqlOperation.deleteUser(jbutton_5, jtextfield_1);
				}else {
					JOptionPane.showMessageDialog(
	                        null,
	                        "�û�ȡ����\nδɾ����ǰ�û���",
	                        "����",
	                        JOptionPane.WARNING_MESSAGE
	                );
				}
			}
		});
//ע�� �����ť �ļ���
		jbutton_6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jtextfield_1.setText("");
			}
		});
	}
	
//��ѯ ѧ������
	public static void init_5() {
        //������
		jpanel_1.removeAll();
        //ˢ�����
		jpanel_1.updateUI();
        //����
		Font font=new Font("����",Font.PLAIN,15);
        //�û���Ϣ
		String userInformation[]= {"�� ����","�� ��","�� ����","�� �ţ�","�� �룺","��"};
        //��ǩ
		JLabel jlabel[]=new JLabel[userInformation.length];
        //�ı���
		JTextField jtextfield[]=new JTextField[userInformation.length];
        //��ť
		JButton jbutton_8=new JButton("��ѯ");
		JButton jbutton_9=new JButton("���");
		JButton jbutton_10=new JButton("���ȫ��");
		jbutton_8.setFont(font);
		jbutton_9.setFont(font);
		jbutton_10.setFont(font);
		
        //ʵ����
		for(int i=0;i<userInformation.length;i++) {
			jlabel[i]=new JLabel(userInformation[i]);
            //jlabel[i].setFont(font);
			jtextfield[i]=new JTextField(12);
            //�����ı��򲻿ɱ༭
			if(i!=3) {
				jtextfield[i].setEditable(false);
			}
		}
		jpanel_1.add(jlabel[3]);
		jpanel_1.add(jtextfield[3]);
		jpanel_1.add(jbutton_8);
		jpanel_1.add(jbutton_9);
		JLabel L_1=new JLabel("    ��ѯ������£�    ");
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
        //ע�� ��ѯ��ť �ļ���
		jbutton_8.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
                //�������ݿⷽ��
				MysqlOperation.selectUser(jbutton_8, jtextfield);
			}
		});
        //ע�� �����ť �ļ���
		jbutton_9.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jtextfield[3].setText("");
			}
		});
        //ע�� ���ȫ����ť �ļ���
		jbutton_10.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//�������ݿⷽ��
				MysqlOperation.showUser(jtextArea);
			}
		});
	}
}
