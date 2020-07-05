package github.fetcher.services;

import github.fetcher.model.Branch;
import github.fetcher.model.Repository;
import github.fetcher.model.StatusCode;
import github.fetcher.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataCollectionService {

    final static Logger logger = LoggerFactory.getLogger(DataCollectionService.class);

    public ResponseEntity<?> getUserdata(String username) {
        URL url = null;
        try {
            url = new URL("https://api.github.com/users/"+username+"/repos");
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
        }

        assert url != null;
        List<Repository> repos = new ArrayList<>();
        try (
                InputStream stream = url.openStream();
             JsonReader reader = Json.createReader(stream)
        ){
            JsonArray results = reader.readArray();
            results.getValuesAs(JsonObject.class).stream()
                    .filter(e -> !e.getBoolean("fork"))
                    .forEach(obj -> repos.add(getRepo(obj)));
        } catch (IOException e) {
            if (e.getMessage().contains("403")) {
                return new ResponseEntity<>(StatusCode.builder()
                        .status(HttpStatus.FORBIDDEN.value())
                        .Message("API rate limit exceeded")
                        .build(),HttpStatus.FORBIDDEN);
            } else {
                return new ResponseEntity<>(StatusCode.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .Message("User not Found")
                        .build(),HttpStatus.NOT_FOUND);
            }
        }
        logger.info("Successfully requested userdata for: "+username);
        return new ResponseEntity<>(User.builder().repositories(repos).build(), HttpStatus.OK);
    }

    private Repository getRepo(JsonObject object) {
        String branchURL = object.getString("branches_url");
        branchURL = branchURL.replace("{/branch}","");
        URL url = null;
        try {
            url = new URL(branchURL);
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
        }

        assert url != null;
        return Repository.builder()
                .name(object.getString("name"))
                .ownerLogin(object.getJsonObject("owner").getString("login"))
                .branches(getBranchInfo(url))
                .build();
    }

    private List<Branch> getBranchInfo(URL url) {
        List<Branch> result = new ArrayList<>();

        try (InputStream is = url.openStream();
             JsonReader rdr = Json.createReader(is)) {
            JsonArray results = rdr.readArray();
            results.getValuesAs(JsonObject.class).parallelStream()
                    .forEach(object -> result.add(
                            Branch.builder()
                                    .name(object.getString("name"))
                                    .sha(object.getJsonObject("commit").getString("sha"))
                                    .build()));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return result;

    }
}
