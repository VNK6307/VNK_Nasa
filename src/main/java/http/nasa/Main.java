package http.nasa;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Main {
    public static ObjectMapper mapper = new ObjectMapper();
    public static final String REMOTE_SERVICE_URI = "https://api.nasa.gov/planetary/apod?api_key=AWGeqlPYRHC9ZxwVsgjoUPKBogPKwIL3CwJvxRFe";

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setUserAgent("NASA image")
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        HttpGet request = new HttpGet(REMOTE_SERVICE_URI);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

        CloseableHttpResponse response = httpClient.execute(request);
//        Arrays.stream(response.getAllHeaders()).forEach(System.out::println);

        Answer answer = mapper.readValue(
                response.getEntity().getContent(),
                new TypeReference<>() {
                }
        );
        System.out.println(answer);

        String filePath = answer.getUrl();
        System.out.println("Адрес для скачивания изображения: " + filePath);

        HttpGet fileRequest = new HttpGet(filePath);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.IMAGE_JPEG.getMimeType());// todo  разобраться с APPLICATION_JSON
        CloseableHttpResponse fileResponse = httpClient.execute(fileRequest);
        String[] parts = filePath.split("/");
        String fileName = parts[parts.length - 1];
        System.out.println("Изображение будет сохранено под именем: " + fileName);

        File file = new File(fileName);
        OutputStream writer = new FileOutputStream(file);
        writer.write(fileResponse.getEntity().getContent().readAllBytes());
        writer.flush();

        response.close();
        fileResponse.close();
        httpClient.close();
        writer.close();
    }
}