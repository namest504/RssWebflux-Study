package entity;

import com.list.realtimenews.dto.RssDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("rss_channel")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class RssChannel {

    @Id
    private Long id;

    @Column("title")
    private String title;

    @Column("link")
    private String link;

    @Column("description")
    private String description;

    @Column("language")
    private String language;

    @Column("pubDate")
    private String pubDate;

    @Column("lastBuildDate")
    private String lastBuildDate;

    public static RssChannel of(RssDto.Channel channel) {
        return RssChannel.builder()
                .title(channel.getTitle())
                .link(channel.getLink())
                .description(channel.getDescription())
                .language(channel.getLanguage())
                .pubDate(channel.getPubDate())
                .lastBuildDate(channel.getLastBuildDate())
                .build();
    }
}
