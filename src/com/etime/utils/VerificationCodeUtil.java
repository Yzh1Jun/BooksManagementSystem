package com.etime.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

//验证码校验
public class VerificationCodeUtil {

    Random random = new Random();
    /**
     * 获得数字
     * @return
     */
    public char getNumber() {
        int math = random.nextInt(57 - 48 + 1)+48;
        return (char)math;
    }
    /**
     * 获得大写字母
     * @return
     */
    public char getBigChar() {
        int math = random.nextInt(90 - 65 + 1)+65;
        return (char)math ;
    }
    /**
     * 获得小写字母
     * @return
     */
    public char getLittleChar() {
        int math = random.nextInt(122 - 97 + 1)+ 97;
        return (char)math;
    }
    /**
     * 设置概率,获取字符(数字,大小写字母)
     * @return
     */
    public char getRandomChar() {
        int math = random.nextInt(9);
        if( math <= 2) {
            return getNumber();
        }else if( math <= 5){
            return getBigChar();
        }else {
            return getLittleChar();
        }
    }
    /**
     * 获得指定数量的验证码
     * @param number 验证码位数
     * @return
     */
    public String getVC(int number) {
        char []c = new char[number];
        for (int i = 0; i < number; i++) {
            c[i] = getRandomChar();
        }
        String code = new String(c);
        return code;
    }

    /**
     * 发送验证码到本地
     * @param code 验证码
     */
    public void sendVC(String code){
        byte[] bytes = code.getBytes();
        String path = "D:"+ File.separator+"VerificationCode.txt";
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream!=null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * @param vc 发送的验证码
     * @param inVC 输入的验证码
     * @return 返回值为1表示校验成功，返回值为-1表示检验失败
     */
    public int judgeVerificationCode(String vc,String inVC){
        if (vc.equals(inVC)){
            return 1;
        }
        return -1;
    }
}
