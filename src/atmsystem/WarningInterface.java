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
        //窗口
        JFrame jframe=new JFrame("欢迎");
        //设置图标
        Image icon = Toolkit.getDefaultToolkit().getImage("E:\\ATMSystem\\image\\1.png");
        jframe.setIconImage(icon);
        //背景图片
        //窗口大小
        jframe.setSize(1000,800);
        //布局
        jframe.setLayout(null);
        //窗口不可调整
        jframe.setResizable(false);
        //窗口居中
        jframe.setLocationRelativeTo(null);
        //设置窗体可见
        jframe.setVisible(true);
        //关闭窗口则退出程序
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //文本域
        JTextArea jTextArea = new JTextArea();
        //信息
        BufferedReader br = new BufferedReader(new FileReader("E:\\ATMSystem\\bin\\data.txt"));
        int len = 0;
        while ((len=br.read()) != -1){
            String demo = br.readLine();
            jTextArea.append(demo);
        }
        br.close();
        //设置文本区不能编辑
        jTextArea.setEditable(false);
        jTextArea.setLineWrap(true);        //激活自动换行功能
        //将jtextArea_1作为可滚动面板sp的显示区域
        JScrollPane jsp=new JScrollPane(jTextArea);
        jsp.setBounds(50,50,900,600);
        //按钮
        JButton jButton1 = new JButton("取消");
        JButton jButton2 = new JButton("同意");
        JCheckBox jCheckBox = new JCheckBox("我已同意并阅读以上条款协议");
        Font font_1=new Font("宋体",Font.BOLD,25);
        Font font_2=new Font("宋体",Font.BOLD,28);
        Font font_3=new Font("宋体",Font.BOLD,15);
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




        //同意监听
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException interruptedException) {
//                    interruptedException.printStackTrace();
//                }
                if (jCheckBox.isSelected()){
                    //结束登录界面
                    jframe.dispose();
                    AdministratorUI.init();
                }else {
                    JOptionPane.showMessageDialog(
                            null,
                            "请阅读以上条款\n" +
                                    "阅读完成请打钩",
                            "提示",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }

            }
        });

        //取消监听
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
