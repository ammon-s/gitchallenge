package github.fetcher.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StatusCode {
    private int status;
    private String Message;
}
