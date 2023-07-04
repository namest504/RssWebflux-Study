package entity;

import com.list.realtimenews.dto.RssDto;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table("rss_image")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class RssImage {

    @Id
    @Column("rss_image_id")
    private Long id;

    private String title;
    private String url;
    private String link;
    private String description;

    public static RssImage of(RssDto.Image image) {
        return RssImage.builder()
                .title(image.getTitle())
                .url(image.getUrl())
                .link(image.getLink())
                .description(image.getDescription())
                .build();
    }
}
