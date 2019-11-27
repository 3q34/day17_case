package cn.itcast.sunnet.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Created by cdx on 2019/11/4.
 * desc:
 */
@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    private static final String TAG = "CheckCodeServlet";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1创建图片到内存中
        int width = 80;
        int height = 30;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //2美化图片


        Graphics g = image.getGraphics();

        //填充背景色
        g.setColor(Color.gray);
        g.fillRect(0, 0, width, height);

        //画边框
        g.setColor(Color.black);
        g.drawRect(0, 0, width - 1, height - 1);
        g.setColor(Color.yellow);

        //String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random ran = new Random();
        String code = "";
        //循环4次生成随机字符
        for (int i = 1; i <= 4; i++) {
            //生成随机角标
            int index = ran.nextInt(str.length());
            //获取字符
            String ss = String.valueOf(str.charAt(index));


            code += ss;
            //String ss1=str.charAt(index)+"";
            //写验证码
            g.drawString(ss, width / 5 * i, (height / 2)+5);

        }

       req.getSession().setAttribute("code", code);
        //画干扰线
//        g.setColor(Color.green);
//        //随机生成坐标点
//        for (int i = 0; i < 10; i++) {
//            int x1 = ran.nextInt(width);
//            int x2 = ran.nextInt(width);
//            int y1 = ran.nextInt(height);
//            int y2 = ran.nextInt(height);
//
//            g.drawLine(x1, y1, x2, y2);
//
//        }

        //3将图片输出到页面显示


        ImageIO.write(image, "jpg", resp.getOutputStream());
    }
}
