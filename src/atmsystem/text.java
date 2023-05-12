package atmsystem;

import javax.swing.*;


public class text {
        public static void main(String[] args) {
            JFrame show=new JFrame("展示");
            show.setSize(600, 600);
            show.setLocation(200, 50);
            //背景图片的路径。(相对路径或者绝对路径。本例图片放于"java项目名"的文件下)
            String path = "E:\\ATMSystem\\image\\2.png";
            ImageIcon background = new ImageIcon(path);
            //把背景图片显示在一个标签里面
            JLabel label = new JLabel(background);
            //把标签的大小位置设置为图片刚好填充整个面板
            label.setBounds(0, 0,show.getWidth(), show.getHeight());
            //把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
            JPanel imagePanel = (JPanel) show.getContentPane();
            imagePanel.setOpaque(false);
            //把背景图片添加到分层窗格的最底层作为背景
            show.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
            show.setVisible(true);
            show.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }
}
