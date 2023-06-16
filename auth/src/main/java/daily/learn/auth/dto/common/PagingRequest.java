package daily.learn.auth.dto.common;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Pageable;

@Data
@SuperBuilder
public class PagingRequest {
    private Pageable pageable;
}
