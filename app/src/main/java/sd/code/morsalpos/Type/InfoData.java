package sd.code.morsalpos.Type;

/**
 * Created by alaa on 1/27/2018.
 */

public class InfoData {

    int id;
    String title;
    String titleEn;

    public InfoData(int id, String title, String titleEn) {
        this.id = id;
        this.title = title;
        this.titleEn = titleEn;
    }

    public InfoData() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }
}
