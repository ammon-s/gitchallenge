package github.fetcher.controller;

import github.fetcher.services.DataCollectionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class GitAPIController {
    private final DataCollectionService dataCollectorService;

    public GitAPIController(DataCollectionService dataCollectionService) {
        this.dataCollectorService = dataCollectionService;
    }

    @RequestMapping(value = "/user/{username}",method= RequestMethod.GET,
            produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserData(@PathVariable("username")String username) {
        return dataCollectorService.getUserdata(username);
    }

}
