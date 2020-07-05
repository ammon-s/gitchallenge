package github.fetcher.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class User {
    private List<Repository> repositories;
}
