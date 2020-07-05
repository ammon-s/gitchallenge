package github.fetcher.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Branch {
    private String name;
    private String sha;
}
