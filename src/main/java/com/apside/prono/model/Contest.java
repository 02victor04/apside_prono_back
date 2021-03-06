package com.apside.prono.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONTEST")
public class Contest {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "ID")
		private Long id;
		private String label;
		
		
		public Contest() {
			super();
		}


		public Contest(Long id, String label) {
			super();
			this.id = id;
			this.label = label;
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
		
		
		
}
