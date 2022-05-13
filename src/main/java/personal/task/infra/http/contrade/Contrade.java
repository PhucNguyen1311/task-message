package personal.task.infra.http.contrade;

import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;

@Component
public class Contrade {

    private static final Logger LOGGER = LoggerFactory.getLogger(Contrade.class);

    @Value("${comtrade.base.url}")
    private String COMTRADE_BASE_URL;

    @Value("${output.directory}")
    private String outputResponseDirectory;


    public HttpURLConnection makeConnection(PayloadRequest payloadRequest) throws IOException, URISyntaxException {
        URL url = buildURL(payloadRequest);
        HttpURLConnection con = initializeHttpURLConnection(url);
        return con;
    }

    private URL buildURL(PayloadRequest payloadRequest) throws URISyntaxException, MalformedURLException {
        URIBuilder builder = new URIBuilder();
        builder.setScheme("http");
        builder.setHost(COMTRADE_BASE_URL);
        builder.setPath("/get");
        builder.setParameter("max", "502");
        builder.setParameter("type", payloadRequest.getDatatype());
        builder.setParameter("freq", "A");
        builder.setParameter("px", "PS");
        builder.setParameter("ps", payloadRequest.getPeriod());
        builder.setParameter("r", "all");
        builder.setParameter("p", "0");
        builder.setParameter("rg", "all");
        builder.setParameter("cc", "TOTAL");
        return builder.build().toURL();
    }

    private HttpURLConnection initializeHttpURLConnection(URL url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        int status = con.getResponseCode();
        if (status == HttpURLConnection.HTTP_MOVED_TEMP || status == HttpURLConnection.HTTP_MOVED_PERM) {
            String location = con.getHeaderField("Location");
            URL newUrl = new URL(location);
            con = (HttpURLConnection) newUrl.openConnection();
        }
        con.setInstanceFollowRedirects(false);
        con.setRequestMethod("GET");

        return con;
    }

    public String getResponseString(HttpURLConnection con) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        LOGGER.info("content : " + content);
        return content.toString();
    }

    public String getFileName() {
        return String.format("%s-%s.json", outputResponseDirectory, LocalDateTime.now());
    }
}
