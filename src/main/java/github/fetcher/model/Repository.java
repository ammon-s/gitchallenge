package github.fetcher.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Repository {
    private String name;
    private String ownerLogin;
    private List<Branch> branches;
}
