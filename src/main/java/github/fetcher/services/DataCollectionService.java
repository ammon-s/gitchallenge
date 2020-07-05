package github.fetcher.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DataCollectionService {

    public ResponseEntity<?> getUserdata(String username) {
        return new ResponseEntity<>("Initial", HttpStatus.OK);
    }
}
