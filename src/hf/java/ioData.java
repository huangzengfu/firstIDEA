package hf.java;

import java.io.*;

/**
 * @author hf
 * @create 2020-09-19 9:54
 */
public class ioData {

    public static void main(String[] args){
        ioExec ioExec = new ioExec();
        ioExec.saveDataToFile("ioTestData","testData");
    }

}

class ioExec{
    public  void saveDataToFile(String fileName, String data){
        BufferedWriter writer = null;
        String destDirName="d:\\jsonfile";
        File dir = new File(destDirName);
        if (dir.exists()) {
            System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        //创建目录
        if (dir.mkdirs()) {
            System.out.println("创建目录" + destDirName + "成功！");
        } else {
            System.out.println("创建目录" + destDirName + "失败！");
        }
        File file = new File(destDirName+ fileName + ".txt");
        System.out.println(file+"file");
        //如果文件不存在，则新建一个
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(fileName + ".txt文件不存在");
        }
        //写入
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,false), "UTF-8"));
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("文件写入成功！");
    }


    /**
     * //获取数
     * @param fileName 文件名称
     */
    public String String (String fileName) {

        //为了确保文件一定在之前是存在的，将字符串路径封装成File对象
        File file = new File("d:\\jsonfile\\" + fileName+ ".txt");
        if(!file.exists()){
            //throw new RuntimeException("要读取的文件不存在");
            return null;
        }
        BufferedReader reader = null;
        String laststr = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null){
                laststr += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("文件读取成功");
        return laststr;
    }
}