package com.padana.api;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.padana.domain.Document;
import com.padana.service.GenericService;
import com.padana.util.Constants;

@RestController
public class WaterMarkController {

	private static final Logger log = LoggerFactory.getLogger(WaterMarkController.class);

	@Autowired
	GenericService genericService;

	@RequestMapping("/service")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@RequestMapping(value = "checkDocument/{title}/{author}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String checkDocument(@PathVariable String title, @PathVariable String author) {

		Document doc = (Document) genericService.getFiltered(Document.class, "docTitle", title, "docAuthor", author);

		if (doc != null) {
			StringBuilder sw = new StringBuilder();
			sw.append("{").append("ticket_id: ").append("\"").append(doc.getDocTicketId()).append("\"").append("},\n");
			if (doc.getDocStatus().equals(Constants.STATUS_PENDING)) {
				return sw.toString();
			} else {
				sw.append("{document: ").append("{").append("title: ").append("\"").append(doc.getDocTitle())
						.append("\"").append(", author: ").append("\"").append(doc.getDocAuthor()).append("\"");
				if (doc.getDocTopic() != null)
					sw.append(", topic: ").append("\"").append(doc.getDocTopic()).append("\"");

				sw.append("}}");
				return sw.toString();
			}
		}
		return Constants.DOCUMENT_NOT_EXISTENT;

	}

	@RequestMapping(value = "checkByTicket/{ticket}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String checkByTicket(@PathVariable String ticket) {

		Document doc = (Document) genericService.getFiltered(Document.class, "docTicketId", ticket);

		if (doc != null) {
			StringBuilder sw = new StringBuilder();
			sw.append("{").append("status: ").append("\"").append(doc.getDocStatus()).append("\"}\n");

			if (doc.getDocStatus().equals(Constants.STATUS_PENDING)) {
				return sw.toString();
			} else {
				sw.append("{").append("document: ").append("{").append("title: ").append("\"").append(doc.getDocTitle())
						.append("\"").append(", author: ").append("\"").append(doc.getDocAuthor()).append("\"");
				if (doc.getDocTopic() != null)
					sw.append(", topic: ").append("\"").append(doc.getDocTopic()).append("\"");

				sw.append("}}");

				sw.append("{").append("watermark: ").append("{").append("content: ").append("\"")
						.append(doc.getDocTopic() != null ? "journal" : "book").append("\"").append(", title: ")
						.append("\"").append(doc.getDocTitle()).append("\"").append(", author: ").append("\"")
						.append(doc.getDocAuthor()).append("\"");
				if (doc.getDocTopic() != null)
					sw.append(", topic: ").append("\"").append(doc.getDocTopic()).append("\"");

				sw.append("}}");
				return sw.toString();
			}
		}
		return Constants.DOCUMENT_NOT_EXISTENT;
	}

	@RequestMapping(value = "addDocuments", method = RequestMethod.POST)
	public ResponseEntity<Void> addDocument(@RequestBody Document doc) {
		if (genericService.getFiltered(Document.class, "docTitle", doc.getDocTitle(), "docAuthor",
				doc.getDocAuthor()) != null) {
			log.info("A document with title " + doc.getDocTitle() + " and author: " + doc.getDocAuthor()
					+ " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		doc.setDocStatus(Constants.STATUS_PENDING);
		doc.setDocTicketId(UUID.randomUUID().toString().substring(0, 8));
		genericService.save(Document.class, doc);

		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "addBulkDocuments", method = RequestMethod.POST)
	public ResponseEntity<Void> addBulkDocuments(@RequestBody List<Document> docs) {
		for (Document doc : docs) {
			if (genericService.getFiltered(Document.class, "docTitle", doc.getDocTitle(), "docAuthor",
					doc.getDocAuthor()) != null) {
				log.info("Document with title " + doc.getDocTitle() + " and author: " + doc.getDocAuthor()
						+ " already exist");
			}
			doc.setDocStatus(Constants.STATUS_PENDING);
			doc.setDocTicketId(UUID.randomUUID().toString().substring(0, 8));
			genericService.save(Document.class, doc);
		}
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
}
