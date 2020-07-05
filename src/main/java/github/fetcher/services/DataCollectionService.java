package github.fetcher.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DataCollectionService {

    final static Logger logger = LoggerFactory.getLogger(DataCollectionService.class);

    public ResponseEntity<?> getUserdata(String username) {
        return new ResponseEntity<>("Initial", HttpStatus.OK);
    }
}
