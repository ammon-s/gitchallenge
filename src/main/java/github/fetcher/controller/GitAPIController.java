package github.fetcher.controller;

import github.fetcher.services.DataCollectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class GitAPIController {
    final static Logger logger = LoggerFactory.getLogger(GitAPIController.class);
    private final DataCollectionService dataCollectorService;

    public GitAPIController(DataCollectionService dataCollectionService) {
        this.dataCollectorService = dataCollectionService;
    }

    @RequestMapping(value = "/user/{username}",method= RequestMethod.GET,
            produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserData(@PathVariable("username")String username, HttpServletRequest request) {
        logger.info(request.getRemoteAddr() + " requested userdate for: "+username);
        return dataCollectorService.getUserdata(username);
    }

}
