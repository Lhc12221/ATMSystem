package atmsystem;

import javax.swing.*;


public class text {
        public static void main(String[] args) {
            JFrame show=new JFrame("չʾ");
            show.setSize(600, 600);
            show.setLocation(200, 50);
            //����ͼƬ��·����(���·�����߾���·��������ͼƬ����"java��Ŀ��"���ļ���)
            String path = "E:\\ATMSystem\\image\\2.png";
            ImageIcon background = new ImageIcon(path);
            //�ѱ���ͼƬ��ʾ��һ����ǩ����
            JLabel label = new JLabel(background);
            //�ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������
            label.setBounds(0, 0,show.getWidth(), show.getHeight());
            //�����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��
            JPanel imagePanel = (JPanel) show.getContentPane();
            imagePanel.setOpaque(false);
            //�ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����
            show.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
            show.setVisible(true);
            show.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }
}
