/*
孟渝桓 18221785
2020移动平台程序设计
 */
package chris.accountms.NoteFolder;

public class Note {
    private String title;   //标题
    private String content; //内容
    private String times;   //时间
    private int ids;        //编号

    public Note(String ti, int id, String con, String time) {
        this.ids = id;
        this.title = ti;
        this.content = con;
        this.times = time;
    }

    public Note(String ti, String con, String time) {
        this.title = ti;
        this.content = con;
        this.times = time;
    }

    public Note(int i, String ti, String time) {
        this.ids = i;
        this.title = ti;
        this.times = time;
    }

    public Note(String ti, String con) {
        this.title = ti;
        this.content = con;
    }

    public int getIds() {
        return ids;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getTimes() {
        return times;
    }
}