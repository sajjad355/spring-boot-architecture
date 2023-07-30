package com.internal.service.template.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "customer_id")
	private long customerId;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(nullable = true, length = 50)
	private String phone;

	@Column(nullable = false, length = 50)
	private String address;

	@UpdateTimestamp
	private Date modifiedOn;
}
