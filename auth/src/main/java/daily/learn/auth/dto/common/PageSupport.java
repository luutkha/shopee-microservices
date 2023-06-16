package daily.learn.authen.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageSupport<T> {
    public static final String FIRST_PAGE_NUM = "0";
    public static final String DEFAULT_PAGE_SIZE = "20";
    private final List<T> content;
    private final int pageNumber;
    private final int pageSize;
    private final long totalElements;
}
