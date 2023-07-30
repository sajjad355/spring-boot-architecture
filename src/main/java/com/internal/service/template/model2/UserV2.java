package com.internal.service.template.model2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "il_user")
public class UserV2{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
	private long userId;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(nullable = true, length = 200)
	private String password;

	@Column(nullable = true, length = 50)
	private String email;

	@Column(nullable = false, length = 50, unique = true)
	private String userName;

	@Column(nullable = false)
	private boolean active;

	@Column(nullable = false, length = 50)
	private String editedBy;

	@UpdateTimestamp
	private Date modifiedOn;

	@Column(nullable = false)
	String role;
}
