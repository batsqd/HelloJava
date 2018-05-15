package getImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import org.jsoup.*;
import org.jsoup.nodes.Document;

class DownloadImage {

    /**
     * @param args
     * @throws Exception
     */
    private static final String neteasy_music_product_detail_url_prefix
            ="http://music.163.com/store/product/detail?id=";
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // http://music.163.com/store/product/detail?id=6575089
        System.out.println("开始下载图片！");
        int id=1000;
        String savePath="D:\\homework_pics";

        int count=0;
        while(id<=5000){
            String neteasy_music_product_detail_url=neteasy_music_product_detail_url_prefix+id;
            String imageUrl=getImageUrl(neteasy_music_product_detail_url);
            if(!"error".equals(imageUrl)){
                try {
                    downloadImage(imageUrl,id+".jpg",savePath);
                } catch (Exception e) {
                    System.out.println(id+".jpg下载失败！");
                }
                count++;
                if(count%10==0){
                    System.out.println("已完成下载"+count);
                }
            }else{
                System.out.println(id+".jpg下载失败！");
            }

            if(count>=50){
                System.out.println("已经下载了"+count+"张图片！");
                break;
            }
            id++;
        }


        System.out.println("下载图片结束！");
    }



    public static String getImageUrl(String link){
        String imageUrl=null;
        try {
            Document doc = Jsoup.connect(link).get();
            //<meta property="og:image" content="http://p3.music.126.net/lhLBWIueDvNw0NYhr3dnzw==/18690598162610450.jpg" />
            imageUrl = doc.select("meta[property=og:image]").get(0).attr("content"); // 具有 href 属性的链接

        } catch (Exception e) {
            System.out.println("网络请求异常："+e.getMessage());
            return "error";
        }
        return imageUrl;
    }
    public static void downloadImage(String imageUrl, String filename,String savePath) throws Exception {
        // 构造URL
        URL url = new URL(imageUrl);
        // 打开连接
        URLConnection con = url.openConnection();
        //设置请求超时为5s
        con.setConnectTimeout(5*1000);
        // 输入流
        InputStream is = con.getInputStream();

        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        File sf=new File(savePath);
        if(!sf.exists()){
            sf.mkdirs();
        }
        OutputStream os = new FileOutputStream(sf.getPath()+"\\"+filename);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
    }

}
