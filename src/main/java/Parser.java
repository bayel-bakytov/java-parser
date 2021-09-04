import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {

    public static Document getPage() throws IOException {
        String url = "https://enter.kg/computers/noutbuki_bishkek";
        Document page = Jsoup.connect(url).get();
        return page;
    }

    public static void main(String[] args) throws IOException  {

        Document page = getPage();
        Element dives = page.select("div[id=content]").first();
        Elements names = dives.select("span[rel=tooltip]");
        Elements prices = dives.select("span[class=price]");

        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Information");
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();

        for (Element n : names) {
            list.add(n.text());
        }
        for (Element price : prices) {
           list2.add(price.text());
        }
        for (int i = 0;i < list.size();i++) {
           Row row = sheet.createRow(i);
           Cell cell = row.createCell(0);
           cell.setCellValue(list.get(i));
           Cell cell1 = row.createCell(1);
           cell1.setCellValue(list2.get(i));
        }
        try {
            FileOutputStream file = new FileOutputStream("C:\\Users\\user\\Desktop\\java-parser\\4.xlsx");
            workbook.write(file);
            file.close();
            System.out.println("Файл создан");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!!!");
        }
    }

}
