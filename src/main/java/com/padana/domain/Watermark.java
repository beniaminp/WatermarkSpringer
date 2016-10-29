package com.padana.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WATERMARK")
public class Watermark implements Serializable{

	private static final long serialVersionUID = -4949226775287691488L;
	
	@Id
	@Column(name = "WATERMARK_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long watId;
	
	@Column(name = "CONTENT")
	private String watContent;
	
	@Column(name = "TITLE")
	private String watTitle;
	
	@Column(name = "AUTHOR")
	private String watAuthor;
	
	@Column(name = "TOPIC")
	private String watTopic;

	public Long getWatId() {
		return watId;
	}

	public void setWatId(Long watId) {
		this.watId = watId;
	}

	public String getWatContent() {
		return watContent;
	}

	public void setWatContent(String watContent) {
		this.watContent = watContent;
	}

	public String getWatTitle() {
		return watTitle;
	}

	public void setWatTitle(String watTitle) {
		this.watTitle = watTitle;
	}

	public String getWatAuthor() {
		return watAuthor;
	}

	public void setWatAuthor(String watAuthor) {
		this.watAuthor = watAuthor;
	}

	public String getWatTopic() {
		return watTopic;
	}

	public void setWatTopic(String watTopic) {
		this.watTopic = watTopic;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((watAuthor == null) ? 0 : watAuthor.hashCode());
		result = prime * result + ((watContent == null) ? 0 : watContent.hashCode());
		result = prime * result + ((watId == null) ? 0 : watId.hashCode());
		result = prime * result + ((watTitle == null) ? 0 : watTitle.hashCode());
		result = prime * result + ((watTopic == null) ? 0 : watTopic.hashCode());
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
		Watermark other = (Watermark) obj;
		if (watAuthor == null) {
			if (other.watAuthor != null)
				return false;
		} else if (!watAuthor.equals(other.watAuthor))
			return false;
		if (watContent == null) {
			if (other.watContent != null)
				return false;
		} else if (!watContent.equals(other.watContent))
			return false;
		if (watId == null) {
			if (other.watId != null)
				return false;
		} else if (!watId.equals(other.watId))
			return false;
		if (watTitle == null) {
			if (other.watTitle != null)
				return false;
		} else if (!watTitle.equals(other.watTitle))
			return false;
		if (watTopic == null) {
			if (other.watTopic != null)
				return false;
		} else if (!watTopic.equals(other.watTopic))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Watermark [watId=" + watId + ", watContent=" + watContent + ", watTitle="
				+ watTitle + ", watAuthor=" + watAuthor + ", watTopic=" + watTopic + "]";
	}
	
	
}
