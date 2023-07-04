package entity;

import com.list.realtimenews.dto.RssDto;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table("rss_item")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class RssItem {

    @Id
    private Long id;
    private String title;
    private String link;
    private String description;
    private String author;
    private Long guid;
    private String comments;
    private String pubDate;

    public static RssItem of(RssDto.Item item) {
        return RssItem.builder()
                .title(item.getTitle())
                .link(item.getLink())
                .description(item.getDescription())
                .author(item.getAuthor())
                .guid(item.getGuid())
                .comments(item.getComments())
                .pubDate(item.getPubDate())
                .build();
    }

    public static List<RssItem> convertToRssItems(List<RssDto.Item> items) {
        List<RssItem> rssItems = new ArrayList<>();
        for (RssDto.Item item : items) {
            RssItem rssItem = RssItem.of(item);
            rssItems.add(rssItem);
        }
        return rssItems;
    }
}
