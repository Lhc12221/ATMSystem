package atmsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class WarningInterface {
    public static void welcome() throws IOException {
        //����
        JFrame jframe=new JFrame("��ӭ");
        //����ͼ��
        Image icon = Toolkit.getDefaultToolkit().getImage("E:\\ATMSystem\\image\\1.png");
        jframe.setIconImage(icon);
        //����ͼƬ
        //���ڴ�С
        jframe.setSize(1000,800);
        //����
        jframe.setLayout(null);
        //���ڲ��ɵ���
        jframe.setResizable(false);
        //���ھ���
        jframe.setLocationRelativeTo(null);
        //���ô���ɼ�
        jframe.setVisible(true);
        //�رմ������˳�����
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //�ı���
        JTextArea jTextArea = new JTextArea();
        //��Ϣ
        BufferedReader br = new BufferedReader(new FileReader("E:\\ATMSystem\\bin\\data.txt"));
        int len = 0;
        while ((len=br.read()) != -1){
            String demo = br.readLine();
            jTextArea.append(demo);
        }
        br.close();
        //�����ı������ܱ༭
        jTextArea.setEditable(false);
        jTextArea.setLineWrap(true);        //�����Զ����й���
        //��jtextArea_1��Ϊ�ɹ������sp����ʾ����
        JScrollPane jsp=new JScrollPane(jTextArea);
        jsp.setBounds(50,50,900,600);
        //��ť
        JButton jButton1 = new JButton("ȡ��");
        JButton jButton2 = new JButton("ͬ��");
        JCheckBox jCheckBox = new JCheckBox("����ͬ�Ⲣ�Ķ���������Э��");
        Font font_1=new Font("����",Font.BOLD,25);
        Font font_2=new Font("����",Font.BOLD,28);
        Font font_3=new Font("����",Font.BOLD,15);
        jButton1.setFont(font_1);
        jButton2.setFont(font_1);
        jTextArea.setFont(font_2);
        jCheckBox.setFont(font_3);


        jframe.add(jCheckBox);
        jframe.add(jButton1);
        jframe.add(jButton2);
        jframe.add(jsp);
        //jframe.add(jpanel);
        jButton1.setBounds(200,700,100,50);
        jButton2.setBounds(700,700,100,50);
        jCheckBox.setBounds(200,660,300,30);




        //ͬ�����
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException interruptedException) {
//                    interruptedException.printStackTrace();
//                }
                if (jCheckBox.isSelected()){
                    //������¼����
                    jframe.dispose();
                    AdministratorUI.init();
                }else {
                    JOptionPane.showMessageDialog(
                            null,
                            "���Ķ���������\n" +
                                    "�Ķ�������",
                            "��ʾ",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }

            }
        });

        //ȡ������
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jframe.dispose();
            }
        });
    }

//    public static void main(String[] args) throws IOException {
//        welcome();
//    }


}
