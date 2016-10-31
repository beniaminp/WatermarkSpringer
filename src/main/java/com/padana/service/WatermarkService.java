package com.padana.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.padana.domain.Document;
import com.padana.domain.Watermark;
import com.padana.util.Constants;

@Component
public class WatermarkService{
	@Autowired
	GenericService genericService;
	
	private static final Logger log = LoggerFactory.getLogger(WatermarkService.class);

    @Scheduled(fixedDelay = 20000)
    public void setWatermark() {
    	log.info("Search for documents to set watermark");
    	List<Document> documents = genericService.getListFiltered(Document.class, "docStatus", Constants.STATUS_PENDING);
    	log.info("Found "+documents.size()+ " without watermark");
    	for(Document doc: documents){
    		doc.setWatermark(new Watermark(doc.getDocTopic() != null ? "book" : "journal", doc.getDocTitle(), doc.getDocAuthor(), doc.getDocTopic()));
    		doc.setDocStatus(Constants.STATUS_DONE);
    		genericService.update(doc);
    		log.info("Updated watermark for docId: "+doc.getDocId());
    	}
    	log.info("Wait 20 sec...");
    }
}
