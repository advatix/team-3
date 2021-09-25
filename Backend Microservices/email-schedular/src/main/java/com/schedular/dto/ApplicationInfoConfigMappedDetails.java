package com.schedular.dto;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.schedular.domain.ApplicationInfo;
import com.schedular.domain.embeddables.ApplicationBasicInfo;
import com.schedular.domain.embeddables.ApplicationId;

@Entity
@Table(name = ApplicationInfo.TABLE_NAME)
public class ApplicationInfoConfigMappedDetails implements Serializable {

	private static final long serialVersionUID = -1796480177233750266L;

	@EmbeddedId
	private ApplicationId id;

	@Embedded
	private ApplicationBasicInfo basicInfo;

	public ApplicationId getId() {
		return id;
	}

	public void setId(ApplicationId id) {
		this.id = id;
	}

	public ApplicationBasicInfo getBasicInfo() {
		return basicInfo;
	}

	public void setBasicInfo(ApplicationBasicInfo basicInfo) {
		this.basicInfo = basicInfo;
	}

	@Override
	public String toString() {
		return "ApplicationInfoConfigMappedDetails [id=" + id + ", basicInfo=" + basicInfo + "]";
	}

}
