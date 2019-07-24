package com.apside.prono.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "EVENT")
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	private String label;
	private Date eventDate;
	private Date openDate;
	private Date closeDate;
	private Long coeff;
	@ManyToOne
	private Contest Contest;
	
	
	public Event() {
		super();
	}
	public Event(Long id, String label, Date eventDate, Date openDate, Date closeDate, Long coeff,
			com.apside.prono.model.Contest contest) {
		super();
		this.id = id;
		this.label = label;
		this.eventDate = eventDate;
		this.openDate = openDate;
		this.closeDate = closeDate;
		this.coeff = coeff;
		Contest = contest;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public Date getOpenDate() {
		return openDate;
	}
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	public Date getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}
	public Long getCoeff() {
		return coeff;
	}
	public void setCoeff(Long coeff) {
		this.coeff = coeff;
	}
	public Contest getContest() {
		return Contest;
	}
	public void setContest(Contest contest) {
		Contest = contest;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Event [id=");
		builder.append(id);
		builder.append(", label=");
		builder.append(label);
		builder.append(", eventDate=");
		builder.append(eventDate);
		builder.append(", openDate=");
		builder.append(openDate);
		builder.append(", closeDate=");
		builder.append(closeDate);
		builder.append(", coeff=");
		builder.append(coeff);
		builder.append(", Contest=");
		builder.append(Contest);
		builder.append("]");
		return builder.toString();
	}
	
	
}