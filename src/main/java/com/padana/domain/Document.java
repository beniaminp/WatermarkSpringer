package com.padana.domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.tomcat.util.bcel.classfile.Constant;

import com.padana.util.Constants;

@Entity
@Table(name = "DOCUMENT")
public class Document implements Serializable{

	private static final long serialVersionUID = 5226697724849120011L;
	
	@Id
	@Column(name = "DOC_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long docId;
	
	@Column(name = "TITLE")
	private String docTitle;
	
	@Column(name = "AUTHOR")
	private String docAuthor;
	
	@Column(name = "TOPIC")
	private String docTopic;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name = "WAT_DOC_ID", referencedColumnName = "WATERMARK_ID")
	private Watermark watermark;
	
	@Column(name = "TICKET_ID")
	private String docTicketId;
	
	@Column(name = "STATUS", columnDefinition="DEFAULT '"+Constants.STATUS_PENDING+"'")
	private String docStatus;

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	public String getDocTitle() {
		return docTitle;
	}

	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}

	public String getDocAuthor() {
		return docAuthor;
	}

	public void setDocAuthor(String docAuthor) {
		this.docAuthor = docAuthor;
	}

	public Watermark getWatermark() {
		return watermark;
	}

	public void setWatermark(Watermark watermark) {
		this.watermark = watermark;
	}
	

	public String getDocTopic() {
		return docTopic;
	}

	public void setDocTopic(String docTopic) {
		this.docTopic = docTopic;
	}

	public String getDocTicketId() {
		return docTicketId;
	}

	public void setDocTicketId(String docTicketId) {
		this.docTicketId = docTicketId;
	}

	public String getDocStatus() {
		return docStatus;
	}

	public void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((docAuthor == null) ? 0 : docAuthor.hashCode());
		result = prime * result + ((docId == null) ? 0 : docId.hashCode());
		result = prime * result + ((docTitle == null) ? 0 : docTitle.hashCode());
		result = prime * result + ((docTopic == null) ? 0 : docTopic.hashCode());
		result = prime * result + ((watermark == null) ? 0 : watermark.hashCode());
		result = prime * result + ((docTicketId == null) ? 0 : docTicketId.hashCode());
		result = prime * result + ((docStatus == null) ? 0 : docStatus.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Document other = (Document) obj;
		if (docAuthor == null) {
			if (other.docAuthor != null)
				return false;
		} else if (!docAuthor.equals(other.docAuthor))
			return false;
		if (docId == null) {
			if (other.docId != null)
				return false;
		} else if (!docId.equals(other.docId))
			return false;
		if (docTitle == null) {
			if (other.docTitle != null)
				return false;
		} else if (!docTitle.equals(other.docTitle))
			return false;
		if (docTopic == null) {
			if (other.docTopic != null)
				return false;
		} else if (!docTopic.equals(other.docTopic))
			return false;
		if (watermark == null) {
			if (other.watermark != null)
				return false;
		} else if (!watermark.equals(other.watermark))
			return false;
		if (docTicketId == null) {
			if (other.docTicketId != null)
				return false;
		} else if (!docTicketId.equals(other.docTicketId))
			return false;
		if (docStatus == null) {
			if (other.docStatus != null)
				return false;
		} else if (!docStatus.equals(other.docStatus))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Document [docId=" + docId + ", docTitle=" + docTitle + ", docAuthor=" + docAuthor + ", docTopic="
				+ docTopic + ", watermark=" + watermark + ", docTicketId=" + docTicketId + ", docStatus=" + docStatus
				+ "]";
	}


	
}
