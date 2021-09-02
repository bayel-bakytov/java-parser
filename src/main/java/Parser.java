import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Parser {

    public static Document getPage() throws IOException {
        String url = "https://enter.kg/computers/noutbuki_bishkek";
        Document page = Jsoup.connect(url).get();
        return page;
    }

    public static void main(String[] args)  {

    }

}
