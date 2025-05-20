package com.blog.blog.utils;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 驗證碼生成器類，可生成數字、大寫、小寫字母及三者混合類型的驗證碼。 支持自定義驗證碼字符数量； 支持自定義驗證碼圖片的大小； 支持自定義需排除的特殊字符；
 * 支持自定義干擾線的数量； 支持自定義驗證碼圖文颜色
 * @author tangzhiqiang
 */
public class ValidateCode {

    /**
     * 驗證碼類型為僅數字 0~9
     */
    public static final int TYPE_NUM_ONLY = 0;

    /**
     * 驗證碼類型為僅字母，即大寫、小寫字母混合
     */
    public static final int TYPE_LETTER_ONLY = 1;

    /**
     * 驗證碼類型為數字、大寫字母、小寫字母混合
     */
    public static final int TYPE_ALL_MIXED = 2;

    /**
     * 驗證碼類型為數字、大寫字母混合
     */
    public static final int TYPE_NUM_UPPER = 3;

    /**
     * 驗證碼類型為數字、小寫字母混合
     */
    public static final int TYPE_NUM_LOWER = 4;

    /**
     * 驗證碼類型為僅大寫字母
     */
    public static final int TYPE_UPPER_ONLY = 5;

    /**
     * 驗證碼類型為僅小寫字母
     */
    public static final int TYPE_LOWER_ONLY = 6;

    private ValidateCode() {

    }

    /**
     * 生成驗證碼字符串
     *
     * @param type    驗證碼類型，参见本類的靜態属性
     * @param length  驗證碼长度，大于0的整数
     * @param exChars 需排除的特殊字符（僅对數字、字母混合型驗證碼有效，无需排除则為null）
     * @return 驗證碼字符串
     */
    public static String generateTextCode(int type, int length, String exChars) {

        if (length <= 0) {
            return "";
        }

        StringBuffer code = new StringBuffer();
        int i = 0;
        Random r = new Random();

        switch (type) {

            // 僅數字,排除特殊字符
            case TYPE_NUM_ONLY:
                while (i < length) {
                    int t = r.nextInt(10);
                    if (exChars == null || exChars.indexOf(t + "") < 0) {
                        code.append(t);
                        i++;
                    }
                }
                break;

            // 僅字母（即大寫字母、小寫字母混合）
            case TYPE_LETTER_ONLY:
                while (i < length) {
                    int t = r.nextInt(123);
                    boolean onlyLetters=(t >= 97 || (t >= 65 && t <= 90)) && (exChars == null || exChars.indexOf((char) t) < 0);
                    if (onlyLetters) {
                        code.append((char) t);
                        i++;
                    }
                }
                break;

            // 數字、大寫字母、小寫字母混合
            case TYPE_ALL_MIXED:
                while (i < length) {
                    int t = r.nextInt(123);
                    boolean withNumbersAndUpperLettersAndLowerLetters=(t >= 97 || (t >= 65 && t <= 90) || (t >= 48 && t <= 57))
                            && (exChars == null || exChars.indexOf((char) t) < 0);
                    if (withNumbersAndUpperLettersAndLowerLetters) {
                        code.append((char) t);
                        i++;
                    }
                }
                break;

            // 數字、大寫字母混合
            case TYPE_NUM_UPPER:
                while (i < length) {
                    int t = r.nextInt(91);
                    boolean withNumbersAndUpperLetters=(t >= 65 || (t >= 48 && t <= 57)) && (exChars == null || exChars.indexOf((char) t) < 0);
                    if (withNumbersAndUpperLetters) {
                        code.append((char) t);
                        i++;
                    }
                }
                break;

            // 數字、小寫字母混合
            case TYPE_NUM_LOWER:
                while (i < length) {
                    int t = r.nextInt(123);
                    boolean withNumbersAndLowerLetters=(t >= 97 || (t >= 48 && t <= 57)) && (exChars == null || exChars.indexOf((char) t) < 0);
                    if (withNumbersAndLowerLetters) {
                        code.append((char) t);
                        i++;
                    }
                }
                break;

            // 僅大寫字母
            case TYPE_UPPER_ONLY:
                while (i < length) {
                    int t = r.nextInt(91);
                    boolean onlyUpplerLetters=(t >= 65) && (exChars == null || exChars.indexOf((char) t) < 0);
                    if (onlyUpplerLetters) {
                        code.append((char) t);
                        i++;
                    }
                }
                break;

            // 僅小寫字母
            case TYPE_LOWER_ONLY:
                while (i < length) {
                    int t = r.nextInt(123);
                    boolean onlyLowerLetters=(t >= 97) && (exChars == null || exChars.indexOf((char) t) < 0);
                    if (onlyLowerLetters) {
                        code.append((char) t);
                        i++;
                    }
                }
                break;

            default:
                return "";

        }

        return code.toString();
    }

    /**
     * 已有驗證碼，生成驗證碼圖片
     *
     * @param textCode       文本驗證碼
     * @param width          圖片宽度
     * @param height         圖片高度
     * @param interLine      圖片中干擾線的條数
     * @param randomLocation 每個字符的高低位置是否隨機
     * @param backColor      圖片颜色，若為null，则采用隨機颜色
     * @param foreColor      字体颜色，若為null，则采用隨機颜色
     * @param lineColor      干擾線颜色，若為null，则采用隨機颜色
     * @return 圖片缓存对象
     */
    public static BufferedImage generateImageCode(String textCode, int width, int height, int interLine,
                                                  boolean randomLocation, Color backColor, Color foreColor, Color lineColor) {

        BufferedImage bim = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = bim.getGraphics();

        // 画背景圖
        g.setColor(backColor == null ? getRandomColor() : backColor);
        g.fillRect(0, 0, width, height);

        // 画干擾線
        Random r = new Random();
        if (interLine > 0) {

            int x = 0, y = 0, x1 = width, y1 = 0;
            for (int i = 0; i < interLine; i++) {
                g.setColor(lineColor == null ? getRandomColor() : lineColor);
                y = r.nextInt(height);
                y1 = r.nextInt(height);

                g.drawLine(x, y, x1, y1);
            }
        }

        // 字体大小為圖片高度的80%
        int fsize = (int) (height * 0.8);
        int fx = height - fsize;
        int fy = fsize;

        g.setFont(new Font("Default", Font.PLAIN, fsize));

        // 寫驗證碼字符.每個字符高低是否隨機
        for (int i = 0; i < textCode.length(); i++) {
            fy = randomLocation ? (int) ((Math.random() * 0.3 + 0.6) * height) : fy;
            g.setColor(foreColor == null ? getRandomColor() : foreColor);
            g.drawString(textCode.charAt(i) + "", fx, fy);
            fx += fsize * 0.9;
        }

        g.dispose();

        return bim;
    }

    /**
     * 生成圖片驗證碼
     *
     * @param type           驗證碼類型，参见本類的靜態属性
     * @param length         驗證碼字符长度，大于0的整数
     * @param exChars        需排除的特殊字符
     * @param width          圖片宽度
     * @param height         圖片高度
     * @param interLine      圖片中干擾線的條数
     * @param randomLocation 每個字符的高低位置是否隨機
     * @param backColor      圖片颜色，若為null，则采用隨機颜色
     * @param foreColor      字体颜色，若為null，则采用隨機颜色
     * @param lineColor      干擾線颜色，若為null，则采用隨機颜色
     * @return 圖片缓存对象
     */
    public static BufferedImage generateImageCode(int type, int length, String exChars, int width, int height,
                                                  int interLine, boolean randomLocation, Color backColor, Color foreColor, Color lineColor) {

        String textCode = generateTextCode(type, length, exChars);
        BufferedImage bim = generateImageCode(textCode, width, height, interLine, randomLocation, backColor, foreColor,
                lineColor);

        return bim;
    }

    /**
     * 产生隨機颜色
     *
     * @return
     */
    private static Color getRandomColor() {
        Random r = new Random();
        Color c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
        return c;
    }
}