package com.padana.api;

import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping(value = "checkDocument/{title}/{author}", method = RequestMethod.GET)
	@ResponseBody
	public String checkDocument(@PathVariable String title, @PathVariable String author) {

		Document doc = (Document) genericService.getFiltered(Document.class, "docTitle", title, "docAuthor", author);

		if (doc != null) {
			StringBuilder sw = new StringBuilder();
			sw.append("{").append("ticket_id: ").append("\"").append(doc.getDocTicketId()).append("\"").append("}\n");
			if (doc.getDocStatus().equals(Constants.STATUS_PENDING)) {
				return sw.toString();
			} else {
				sw.append("{").append("title: ").append("\"").append(doc.getDocTitle()).append("\"")
						.append(", author: ").append("\"").append(doc.getDocAuthor()).append("\"");
				if (doc.getDocTopic() != null)
					sw.append(", topic: ").append("\"").append(doc.getDocTopic()).append("\"");

				sw.append("}");
				return sw.toString();
			}
		}
		return Constants.DOCUMENT_NOT_EXISTENT;

	}

	@RequestMapping(value = "checkByTicket/{ticket}", method = RequestMethod.GET)
	@ResponseBody
	public String checkByTicket(@PathVariable String ticket) {

		Document doc = (Document) genericService.getFiltered(Document.class, "docTicketId", ticket);

		if (doc != null) {
			StringBuilder sw = new StringBuilder();
			sw.append("{").append("status: ").append(doc.getDocStatus()).append("}\n");

			if (doc.getDocStatus().equals(Constants.STATUS_PENDING)) {
				return sw.toString();
			} else {
				sw.append("{").append("title: ").append("\"").append(doc.getDocTitle()).append("\"")
						.append(", author: ").append("\"").append(doc.getDocAuthor()).append("\"");
				if (doc.getDocTopic() != null)
					sw.append(", topic: ").append("\"").append(doc.getDocTopic()).append("\"");

				sw.append("}");

				sw.append("{").append("content: ").append("\"").append(doc.getDocTopic().isEmpty() ? "journal" : "book")
						.append("\"").append(", title: ").append("\"").append(doc.getDocTitle()).append("\"")
						.append(", author: ").append("\"").append(doc.getDocAuthor()).append("\"");
				if (doc.getDocTopic() != null)
					sw.append(", topic: ").append("\"").append(doc.getDocTopic()).append("\"");

				sw.append("}");
				return sw.toString();
			}
		}
		return Constants.DOCUMENT_NOT_EXISTENT;

	}
}
