import com.tableau.api.TsResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;

public class Workbooks {
    final private static String SERVER_URL = "http://tableau-server.com";

    //  Create the HTTP Client
    CloseableHttpClient client = HttpClientBuilder.create().build();

    public TsResponse getWorkbooks() throws IOException, JAXBException {

        HttpResponse response = client.execute(new HttpGet(SERVER_URL + "/api/3.14/sites/site-name/workbooks"));
        var stringEntity = EntityUtils.toString(response.getEntity());

        JAXBContext jaxbContext = JAXBContext.newInstance(TsResponse.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        return (TsResponse) jaxbUnmarshaller.unmarshal(new StringReader(stringEntity));

    }

}
