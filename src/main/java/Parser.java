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
        int i = 0;
        for (Element n : names) {
            Row row = sheet.createRow(i);
            Cell name = row.createCell(0);
            name.setCellValue(n.text());
            i++;
        }

    }

}
