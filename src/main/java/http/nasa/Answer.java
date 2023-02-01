package http.nasa;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Answer {
    private final String copyright;
    private final String date;
    private final String explanation;
    private final String hdurl;
    private final String mediaType;
    private final String serviceVersion;
    private final String title;
    private final String url;

    public Answer(
            @JsonProperty("copyright") String copyright,
            @JsonProperty("date") String date,
            @JsonProperty("explanation") String explanation,
            @JsonProperty("hdurl") String hdurl,
            @JsonProperty("media_type") String mediaType,
            @JsonProperty("service_version") String serviceVersion,
            @JsonProperty("title") String title,
            @JsonProperty("url") String url
    ) {
        this.copyright = copyright;
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.mediaType = mediaType;
        this.serviceVersion = serviceVersion;
        this.title = title;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "\n\tcopyright='" + copyright + '\'' +
                ",\n\tdate='" + date + '\'' +
                ",\n\texplanation='" + explanation + '\'' +
                ",\n\thdurl='" + hdurl + '\'' +
                ",\n\tmedia_type='" + mediaType + '\'' +
                ",\n\tservice_version='" + serviceVersion + '\'' +
                ",\n\ttitle='" + title + '\'' +
                ",\n\turl='" + url + '\'' +
                '}';
    }
}
